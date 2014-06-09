package es.ucm.fdi.isbc.viviendas;

import interfaz.VentanaPrimcipal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import Aplicacion.ViviendasRecommender;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseBaseFilter;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.InitializingException;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.SolucionVivienda;

public class ViviendasConnector implements Connector {
	

	@Override
	public void initFromXMLfile(URL file) throws InitializingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		ArrayList<CBRCase> cases = new ArrayList<CBRCase>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("viviendas"));
			String line = null;
			while ((line=reader.readLine())!=null)
			{
				DescripcionVivienda vivienda = new DescripcionVivienda(line);
				SolucionVivienda solucion = new SolucionVivienda();
				solucion.setId(vivienda.getId());
				solucion.setPrecio(vivienda.getPrecio());
				
				CBRCase _case = new CBRCase();
				_case.setDescription(vivienda);
				_case.setSolution(solucion);
				cases.add(_case);
				
			}
			reader.close();

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Testing method
	 * @param args
	 */
	public static void main(String[] args){
		ViviendasConnector vc = new ViviendasConnector();
		Collection<CBRCase> cases = vc.retrieveAllCases();
		/*for(CBRCase c : cases)
			System.out.println(c);*/
		ViviendasRecommender viviendasRecommender = ViviendasRecommender.getInstance();
		
		try{
		// Configure the application
			viviendasRecommender.configure();
		//Execute the Precycle
			viviendasRecommender.preCycle();
		// Create  the frame that obtains the query
		/*QueryDialog qf =	new	QueryDialog(main);//en el tutorial main es un frame
		 */
			VentanaPrimcipal v = new VentanaPrimcipal();//es en la q creamos el fromulario
		// Main CBR cycle
		boolean cont = true;
		while(cont){
			// Show the query frame
			//qf.setVisible(true);
			//v.setVisible(true);//lo tenemos siempre a true en su clase
			
			// Obtain the query
		//	CBRQuery query = qf.getQuery();
			// Call the cycle
			viviendasRecommender.cycle(query);
			// Ask if continue
			int	ans = javax.swing.JOptionPane.showConfirmDialog(null,"CBR cycle	finished,query	again?","Cycle	finished",	javax.swing.JOptionPane.YES_NO_OPTION);
			cont=(ans==javax.swing.JOptionPane.YES_OPTION);		
			}
		// Executepostcycle
		viviendasRecommender.postCycle();
		}
		catch (Exception e){
		//Errors
		org.apache.commons.logging.LogFactory.getLog(ViviendasRecommender.class).error(e);
		javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
	}

}
