package recomendador;

import java.util.Collection;

import es.ucm.fdi.isbc.viviendas.ViviendasConnector;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasBasicos;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasFinca;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasOtros;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.extensions.recommendation.casesDisplay.DisplayCasesTableMethod;
import jcolibri.extensions.recommendation.casesDisplay.UserChoice;
import jcolibri.extensions.recommendation.conditionals.BuyOrQuit;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.selection.SelectCases;

public class ViviendasRecommender implements StandardCBRApplication{
	
	Connector _connector;
	CBRCaseBase _caseBase;
	CaseComponent base;
	
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
		/*estos son todos los atributos de las casas:
		 
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
		Attribute precioZona = new Attribute("precioZona", DescripcionVivienda.class);
		Attribute precioMedio = new Attribute("precioMedio", DescripcionVivienda.class);
		Attribute coordenada = new Attribute("coordenada", DescripcionVivienda.class);
		Attribute extrasOtros = new Attribute("extrasOtros", DescripcionVivienda.class);
		Attribute extrasBasicos = new Attribute("extrasBasicos", DescripcionVivienda.class);
		Attribute extrasFinca = new Attribute("extrasFinca", DescripcionVivienda.class);


		simConfig.setDescriptionSimFunction(new Average());

		
		simConfig.addMapping(habitaciones, new Similitud("habitaciones"));
		simConfig.addMapping(precio, new Similitud("precio"));
		simConfig.addMapping(estado, new Similitud("estado"));
		simConfig.addMapping(banios,new Similitud("banios"));
		simConfig.addMapping(id, new Similitud("id"));
		simConfig.addMapping(url, new Similitud("url"));
		simConfig.addMapping(urlFoto, new Similitud("urlFoto"));
		simConfig.addMapping(descripcion, new Similitud("descripcion"));
		simConfig.addMapping(titulo, new Similitud("titulo"));
		simConfig.addMapping(localizacion,new Similitud("localizacion"));
		simConfig.addMapping(precioZona, new Similitud("precioZona"));
		simConfig.addMapping(precioMedio, new Similitud("precioMedio"));
		simConfig.addMapping(coordenada,new Similitud("coordenada"));
		simConfig.addMapping(extrasOtros, new SimilitudExtras("extrasOtros"));
		simConfig.addMapping(extrasBasicos, new SimilitudExtras("extrasBasicos"));
		simConfig.addMapping(extrasFinca, new SimilitudExtras("extrasFinca"));

		
		
		simConfig.setWeight(habitaciones, 1.0);
		simConfig.setWeight(precio, 1.0);
		simConfig.setWeight(estado, 1.0);
		simConfig.setWeight(banios, 0.9);
		simConfig.setWeight(id, 0.0);
		simConfig.setWeight(urlFoto,0.5);
		simConfig.setWeight(titulo, 0.3);
		simConfig.setWeight(url, 0.2);
		simConfig.setWeight(descripcion, 0.4);
		simConfig.setWeight(localizacion, 0.7);
		simConfig.setWeight(precioZona, 0.6);
		simConfig.setWeight(precioMedio, 0.6);
		simConfig.setWeight(titulo, 0.2);
		simConfig.setWeight(coordenada, 1.0);
		simConfig.setWeight(extrasOtros, 0.6);
		simConfig.setWeight(extrasBasicos, 0.8);
		simConfig.setWeight(extrasFinca, 0.7);
		
		
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

		for(CBRCase c: retrievedCases){
			resultBusqueda.add((AnimalDescription) c.getDescription());
		}
*/
		// Obtain query
//		ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query,null,null);
		//llamamos a nuestra interfaz
		//VentanaPrimcipal v = new VentanaPrimcipal();
		
		
		// Execute KNN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		
		// Select cases
		Collection<CBRCase> retrievedCases = SelectCases.selectTopK(eval, 900);//5=numero de casos iniciales que se muestran al usuario
		// Display cases
		UserChoice choice = DisplayCasesTableMethod.displayCasesInTableSelectCase(retrievedCases);//Method.displayCases(retrievedCases);

		// Buy or Quit
		if(BuyOrQuit.buyOrQuit(choice)){
		    System.out.println("Finish - User Buys: "+choice.getSelectedCase());
		    base = (CaseComponent) choice.getSelectedCase().getDescription();
		    go();
		    
		}
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


	public void go() {
		
		try {
			
			configure();
			preCycle();
		    
		    CBRQuery query = new CBRQuery();
		   
		    query.setDescription(base);
		    
		    cycle(query);
		    
		    postCycle();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public void setBase(DescripcionVivienda hd){
		base = hd;
	}

}
