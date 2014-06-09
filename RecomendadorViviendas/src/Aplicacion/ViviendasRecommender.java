package Aplicacion;

import es.ucm.fdi.isbc.viviendas.ViviendasConnector;
import jcolibri.casebase.IDIndexedLinealCaseBase;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;

public class ViviendasRecommender implements StandardCBRApplication{
	
	Connector _connector;
	CBRCaseBase _caseBase;
	//aun no visto
	NNConfig simConfig;
	
	/*hacemos singleton segun el manual*/
	private static	ViviendasRecommender _instance=null;
	public static ViviendasRecommender getInstance(){
		if	( _instance == null)
			_instance = new ViviendasRecommender();
		return	_instance;
		}
		
	
	private ViviendasRecommender(){
	}
	
	
	/*configures the application: case base, connectors, etc*/
	@Override
	public void configure() throws ExecutionException {
		// TODO Auto-generated method stub
		/*en el tutorial inicializa aqui las ventanas que utiliza ??????*/
		_connector = new ViviendasConnector();
		//copiado, aun no se xq esta hasta ^^^^
		//_caseBase = new CachedLinealCaseBase(); la de ellas, prefiero:
		_caseBase = new LinealCaseBase();
		simConfig = new NNConfig();

		//fijamos la funion de similitud global
		simConfig.setDescriptionSimFunction(new Average());
		//^^^^^^^
		
		/*
		 * esto tampoco lo encontre en el manual de moemnto, pero parece logico q este aqui
		 * //indicamos los atributos para la comparacion
		Attribute tipo = new Attribute("tipo",AnimalDescription.class);
		Attribute raza = new Attribute("raza",AnimalDescription.class);
		Attribute sexo = new Attribute("sexo",AnimalDescription.class);
		Attribute edad = new Attribute("edad",AnimalDescription.class);
		Attribute tam = new Attribute("tam",AnimalDescription.class);
		Attribute esterilizado = new Attribute("esterilizado",AnimalDescription.class);

		// fijamos las funciones de similitud locales
		simConfig.addMapping(tipo, new Equal());
		simConfig.setWeight(tipo, 1.0);
		simConfig.addMapping(raza, new SimRaza());
		simConfig.setWeight(raza, 0.6);
		simConfig.addMapping(sexo, new SimSexo());
		simConfig.setWeight(sexo, 0.5);
		simConfig.addMapping(edad, new SimEdad());
		simConfig.setWeight(edad, 0.7);
		simConfig.addMapping(tam, new SimTamanio());
		simConfig.setWeight(tam, 0.9);
		simConfig.addMapping(esterilizado, new Equal());
		simConfig.setWeight(esterilizado, 0.2);*/
	}
	/*
	Runs the precyle where typically cases are read and
	organized into a case base .
	@ return The created case base with the cases in the
	storage.
	 */
	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);		
		//no cogemos el tutorial el println porq ya se hace en l main
		return _caseBase;
		
	}
	
	
	/*Executes a CBR cycle with the given query. */
	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	/*Runs the code to shut down the application. Typically
	it closes the connector */
	@Override
	public void postCycle() throws ExecutionException {
		// TODO Auto-generated method stub
		_connector.close();
	}

}
