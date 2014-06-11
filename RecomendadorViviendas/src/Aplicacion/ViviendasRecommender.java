package Aplicacion;

import java.util.Collection;

import es.ucm.fdi.isbc.viviendas.ViviendasConnector;
import es.ucm.fdi.isbc.viviendas.representacion.Coordenada;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasBasicos;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasFinca;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasOtros;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda.EstadoVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda.TipoVivienda;
import jcolibri.casebase.IDIndexedLinealCaseBase;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.PlainTextConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.extensions.recommendation.casesDisplay.DisplayCasesTableMethod;
import jcolibri.extensions.recommendation.casesDisplay.UserChoice;
import jcolibri.extensions.recommendation.conditionals.BuyOrQuit;
import jcolibri.method.gui.formFilling.ObtainQueryWithFormMethod;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Table;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaLessIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.McSherryMoreIsBetter;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.test.recommenders.housesData.HouseDescription;

public class ViviendasRecommender implements StandardCBRApplication{
	
	Connector _connector;
	CBRCaseBase _caseBase;
	
	/** KNN config */ //siguiendo el ejemplo de recomendaores
    NNConfig simConfig;
	
	/*hacemos singleton segun el manual*/
	private static	ViviendasRecommender _instance=null;
	public static ViviendasRecommender getInstance(){
		if	( _instance == null)
			_instance = new ViviendasRecommender();
		return	_instance;
		}
		
	
	public ViviendasRecommender(){
	}
	
	
	/*configures the application: case base, connectors, etc*/
	@Override
	public void configure() throws ExecutionException {
		_connector = new ViviendasConnector();
		
		// Create a data base connector
		// Init the ddbb connector with the config file
		_connector.initFromXMLfile(jcolibri.util.FileIO
				.findFile("jcolibri/test/recommenders/housesData/plaintextconfig.xml"));
		
		// Create a Lineal case base for in-memory organization
		_caseBase = new LinealCaseBase();
		
		simConfig = new NNConfig();
		// Set the average() global similarity function for the description of the case
		/*estos son todos los atributos de las casas: los añado segun el ejemplo, los que no añado los dejo comentados
		 
		simConfig.setDescriptionSimFunction(new Average());
		simConfig.addMapping(new Attribute("superficie", DescripcionVivienda.class), new Table("jcolibri/test/recommenders/housesData/area.csv"));
		NO SE COMO PONERLES LUEGO UN PESO, HAY QUE SEPARAR LA DECLARACIÓN
		*/
		Attribute habitaciones = new Attribute("habitaciones", DescripcionVivienda.class);
		Attribute precio = new Attribute("precio", DescripcionVivienda.class);
		Attribute estado = new Attribute("estado", DescripcionVivienda.class);
		Attribute localizacion = new Attribute("localizacion", DescripcionVivienda.class);
		Attribute banios = new Attribute("banios", DescripcionVivienda.class);
		Attribute id = new Attribute("id", DescripcionVivienda.class);
		Attribute titulo = new Attribute("titulo", DescripcionVivienda.class);
		Attribute urlFoto = new Attribute("urlFoto", DescripcionVivienda.class);
		Attribute url = new Attribute("url", DescripcionVivienda.class);
		Attribute descripcion = new Attribute("descripcion", DescripcionVivienda.class);
		Attribute extrasOtros = new Attribute("extrasOtros", DescripcionVivienda.class);
		Attribute extrasBasicos = new Attribute("extrasBasicos", DescripcionVivienda.class);
		Attribute extrasFinca = new Attribute("extrasFinca", DescripcionVivienda.class);
		Attribute precioZona = new Attribute("precioZona", DescripcionVivienda.class);
		Attribute precioMedio = new Attribute("precioMedio", DescripcionVivienda.class);
		Attribute coordenada = new Attribute("coordenada", DescripcionVivienda.class);


		simConfig.setDescriptionSimFunction(new Average());
		
		/*SIMILARITY.LOCAL
 * Equal(): This function returns 1 if both individuals are equal, otherwise returns 0
 * EnumCyclicDistance(): This function computes the similarity between two enum values as their cyclic distance.
 * EnumDistance(): This function returns the similarity of two enum values as the their distance sim(x,y)=|ord(x) - ord(y)|
 * EqualsStringIgnoreCase(): This function returns 1 if both String are the same despite case letters, 0 in the other case
 * Interval():  This function returns the similarity of two number inside an interval sim(x,y)=1-(|x-y|/interval)
 * MaxString(): This function returns a similarity value depending of the biggest substring that belong to both strings.
 * Table(): Similarity function that uses a table to obtain the similarity between two values. 
 * 			Allowed values are Strings or Enums. 
 * 			The table is read from a text file with the following format:
				 * <ul>
				 * <li>1st line: coma separated n categories
				 * <li>following n lines: n double values separated by comma. 
				 * </ul> 
	Threshold(): This function returns 1 if the difference between two numbers is less than a threshold, 0 in the other case.
	SIMILARITY.LOCAL.RECOMMENDER
	InrecaLessIsBetter(): This function returns the similarity of two numbers (or enums) following the INRECA - Less is Better formula
 *							sim(c.a,q.a)= if(c.a < q.a) then 1 else  jump (max(a) - c.a) / (max(a) - q.a)
 * 							jump and max(a) must be defined by the designer.
 * InrecaMoreIsBetter():This function returns the similarity of two numbers (or Enums) following the INRECA - More is Better formulae
 * 							sim(c.a,q.a)= if(c.a > q.a) then 1 else  jump * (1- (q.a - c.a) / q.a))
 * 							jump must be defined by the designer.
 * McSherryLessIsBetter():This function returns the similarity of two numbers following the McSherry - Less is Better formulae
 * 							sim(c.a,q.a)= (max(a) - c.a) / (max(a)-min(a))
 * 							min(a) and max(a) must be defined by the designer. q.a is not taken into account.
 * McSherryMoreIsBetter(): This function returns the similarity of two numbers following  the McSherry - More is Better formulae
							 sim(c.a,q.a)= 1 - ((max(a) - c.a) / (max(a)-min(a)))
							 min(a) and max(a) must be defined by the designer. q.a is not taken into account.
 */
		
		simConfig.addMapping(habitaciones, new Equal());
		simConfig.addMapping(precio, new Equal());
		simConfig.addMapping(estado, new Equal());
		simConfig.addMapping(localizacion, new Equal());
		simConfig.addMapping(banios, new Equal());
		simConfig.addMapping(id, new Equal());
		simConfig.addMapping(titulo, new Equal());
		simConfig.addMapping(urlFoto, new Equal());
		simConfig.addMapping(url, new Equal());
		simConfig.addMapping(descripcion, new Equal());
		simConfig.addMapping(extrasOtros, new Equal());
		simConfig.addMapping(extrasBasicos, new Equal());
		simConfig.addMapping(extrasFinca, new Equal());
		simConfig.addMapping(precioZona, new Equal());
		simConfig.addMapping(precioMedio, new Equal());
		simConfig.addMapping(coordenada, new Equal());
		
		
		simConfig.setWeight(habitaciones, 1.0);
		simConfig.setWeight(precio, 1.0);
		simConfig.setWeight(estado, 1.0);
		simConfig.setWeight(localizacion, 1.0);
		simConfig.setWeight(banios, 1.0);
		simConfig.setWeight(id, 1.0);
		simConfig.setWeight(titulo, 1.0);
		simConfig.setWeight(urlFoto, 1.0);
		simConfig.setWeight(url, 1.0);
		simConfig.setWeight(descripcion, 1.0);
		simConfig.setWeight(extrasOtros, 1.0);
		simConfig.setWeight(extrasBasicos, 1.0);
		simConfig.setWeight(extrasFinca, 1.0);
		simConfig.setWeight(precioZona, 1.0);
		simConfig.setWeight(precioMedio, 1.0);
		simConfig.setWeight(coordenada, 1.0);
		
	}
	/*
	Runs the precyle where typically cases are read and
	organized into a case base .
	@ return The created case base with the cases in the
	storage.
	 */
	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		// Load cases from connector into the case base
		_caseBase.init(_connector);		
		// Print the cases
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		return _caseBase;
		
	}
	
	
	/*Executes a CBR cycle with the given query. */
	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		/*ciclo del ejemplo del recomendador

		// Select cases
		Collection<CBRCase> retrievedCases = SelectCases.selectTopK(eval, 54);

		for(CBRCase c: retrievedCases){
			resultBusqueda.add((AnimalDescription) c.getDescription());
		}
*/
		// Obtain query
//		ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query,null,null);
		
		// Execute KNN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		
		// Select cases
		Collection<CBRCase> retrievedCases = SelectCases.selectTopK(eval, 5);//el numero q ponemos ahi da error-> error 0..4  Error getting value from object
																			 // erroral mostrar en el siguiente método, no se muestran bien los atributos
																			//supongo q habra q hacer otra tabla
		// Display cases
		UserChoice choice = DisplayCasesTableMethod.displayCasesInTableSelectCase(retrievedCases);//DisplayCasesMethod.displayCases(retrievedCases);

		// Buy or Quit
		if(BuyOrQuit.buyOrQuit(choice))
		    System.out.println("Finish - User Buys: "+choice.getSelectedCase());
		
		else
		    System.out.println("Finish - User Quits");
		
	}

	/*Runs the code to shut down the application. Typically
	it closes the connector */
	@Override
	public void postCycle() throws ExecutionException {
		// TODO Auto-generated method stub
		_connector.close();
	}

}
