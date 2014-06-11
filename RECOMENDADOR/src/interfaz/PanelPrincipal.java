package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import recomendador.ViviendasRecommender;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.test.recommenders.rec1.Houses1;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda.EstadoVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda.TipoVivienda;

@SuppressWarnings("serial")
public class PanelPrincipal extends JPanel{
	//Busqueda
	private JButton boton_buscar;
	private int superficie,habitaciones,banios,precio;
	private EstadoVivienda estado;
	private TipoVivienda tipo;
	
	
	private ArrayList<String> valores;
	
	public PanelPrincipal(){
			/*atributos ppales*/
			this.setVisible(true);
			this.setEnabled(true);
			this.setSize(300, 700);
			
			/*añadimos los paneles al ppal*/
			this.add(getPanelTipo());
			this.add(getPanelEstado());
			this.add(getPanelSuperficie());
			this.add(getPanelHabitaciones());
			this.add(getPanelBanios());
			this.add(getPanelPrecio());
			
			/*añadimos los botones*/
			boton_buscar = new JButton("buscar");
			ActionListener actionBuscar = new ActionListener() {
				public void actionPerformed(ActionEvent ev){
					ViviendasRecommender recommender = new ViviendasRecommender();
					DescripcionVivienda hd = new DescripcionVivienda();
				    
				    /*n(new Average());
					simConfig.addMapping(new Attribute("superficie", DescripcionVivienda.class), new Table("jcolibri/test/recommenders/housesData/area.csv"));
					simConfig.addMapping(new Attribute("habitaciones", DescripcionVivienda.class), new McSherryMoreIsBetter(0,0));
					simConfig.addMapping(new Attribute("precio", DescripcionVivienda.class), new InrecaLessIsBetter(2000, 0.5));
					simConfig.addMapping(new Attribute("estado", DescripcionVivienda.class), new Equal());
					simConfig.addMapping(new Attribute("localizacion", DescripcionVivienda.class), new Equal());
					simConfig.addMapping(new Attribute("banios",*/
				    hd.setSuperficie(superficie);
				    hd.setBanios(banios);
				    hd.setHabitaciones(habitaciones);
				    hd.setEstado(estado);
				    hd.setTipo(tipo);
				    hd.setPrecio(precio);
				    recommender.setBase(hd);
					recommender.go();	
				} 
			};
			this.add(boton_buscar);
			boton_buscar.addActionListener(actionBuscar);
			
			}
	
		public ArrayList<String> getValorAtributos(){
			return this.valores;
			
		}
		
		//elegir tipoV
		private JPanel getPanelTipo(){
			final JPanel  panel= new JPanel();
			JLabel jLabel = new JLabel("Tipo: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("Atico");
			comboBox.addItem("Plantabaja");
			comboBox.addItem("Piso");
			comboBox.addItem("Loft");
			comboBox.addItem("Adosado");
			comboBox.addItem("Chalet");
			comboBox.addItem("Duplex");
			comboBox.addItem("Estudio");
			comboBox.addItem("Finca");
			comboBox.addItem("Apartamento");
		
			tipo = TipoVivienda.Apartamento;
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch((String)comboBox.getSelectedItem()){
						case "Atico": tipo = TipoVivienda.Atico; break;
						case "Plantabaja": tipo = TipoVivienda.Plantabaja; break;
						case "Piso": tipo = TipoVivienda.Piso; break;
						case "Loft": tipo = TipoVivienda.Loft; break;
						case "Adosado": tipo = TipoVivienda.Casaadosada; break;
						case "Chalet": tipo = TipoVivienda.CasaChalet; break;
						case "Duplex": tipo = TipoVivienda.Duplex; break;
						case "Estudio": tipo = TipoVivienda.Estudio; break;
						case "Finca": tipo = TipoVivienda.Fincarustica; break;
						case "Apartamento": tipo = TipoVivienda.Apartamento; break;
						
						}
						
						}
					} 
			};
			comboBox.addActionListener(actionL);
			
			panel.add(jLabel);
			panel.add(comboBox);
			return panel;
		}
			
		private JPanel getPanelSuperficie(){
			final JPanel  panel= new JPanel();

			JLabel jLabel = new JLabel("Superficie: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("hasta 70 m2");
			comboBox.addItem("70-100 m2");
			comboBox.addItem("100-150 m2");
			comboBox.addItem("150-200 m2");
			comboBox.addItem("más de 200 m2");
			 superficie = 85;
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
						switch ((String)comboBox.getSelectedItem()){
							case "hasta 70 m2": superficie = 60; break;//ver los vañpres iniciales de las superficies, a lo iguakl es mejor un jText
							case "70-100 m2": superficie = 85; break;
							case "100-150 m2": superficie = 120; break;
							case "150-200 m2": superficie = 170; break;
							case "más de 200 m2": superficie = 210; break;
							} 
					}
			};
			comboBox.addActionListener(actionL);
			
			panel.add(jLabel);
			panel.add(comboBox);
			return panel;
		}	
		private JPanel getPanelPrecio(){
			final JPanel  panel= new JPanel();
			JLabel jLabel = new JLabel("Precio: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("100000 - 150000");
			comboBox.addItem("150000 - 200000");
			comboBox.addItem("200000 - 250000");
			comboBox.addItem("mas de 250000");
			precio = 150000;
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					//Muybien, Reformado, Areformar, Casinuevo, Bien
					switch((String)comboBox.getSelectedItem()){
					case ("100000 - 150000"): precio = 150000; break;
					case ("150000 - 200000"): precio = 200000;break;
					case ("200000 - 250000"): precio = 250000;break;
					case ("mas de 250000"): precio = 300000;break;
					
					}
				}
			};
			comboBox.addActionListener(actionL);
			
			panel.add(jLabel);
			panel.add(comboBox);
			return panel;
		}		
		private JPanel getPanelEstado(){
			final JPanel  panel= new JPanel();
			JLabel jLabel = new JLabel("Estado: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("Muy Bien");
			comboBox.addItem("Reformado");
			comboBox.addItem("A reformar");
			comboBox.addItem("Casi nuevo");
			comboBox.addItem("Bien");
			estado = EstadoVivienda.Bien;
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					//Muybien, Reformado, Areformar, Casinuevo, Bien
					switch((String)comboBox.getSelectedItem()){
					case ("Muy Bien"): estado = EstadoVivienda.Muybien; break;
					case ("Reformado"): estado = EstadoVivienda.Reformado;break;
					case ("A reformar"): estado = EstadoVivienda.Areformar;break;
					case ("Casi nuevo"): estado = EstadoVivienda.Casinuevo;break;
					case ("Bien"): estado = EstadoVivienda.Bien;break;
					
					}
				}
			};
			comboBox.addActionListener(actionL);
			
			panel.add(jLabel);
			panel.add(comboBox);
			return panel;
		}	
		
		private JPanel getPanelHabitaciones(){
			final JPanel  panel= new JPanel();
			JLabel jLabel = new JLabel("Habitaciones: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("Estudio");
			comboBox.addItem("1");
			comboBox.addItem("2");
			comboBox.addItem("3");
			comboBox.addItem("4 o más");
			habitaciones=1;
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
						switch ((String)comboBox.getSelectedItem()){
							case "Estudio": habitaciones=0; break;
							case "1": habitaciones=1; break;
							case "2": habitaciones=2; break;
							case "3": habitaciones=3; break;
							case "4 o más": habitaciones=4; break;
						}
					} 
			};
			comboBox.addActionListener(actionL);
			
			panel.add(jLabel);
			panel.add(comboBox);
			return panel;
		}	
		
		private JPanel getPanelBanios(){
			final JPanel  panel= new JPanel();
			JLabel jLabel = new JLabel("Baños: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("1");
			comboBox.addItem("2");
			comboBox.addItem("3 o más");
			banios =1;
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
						switch ((String)comboBox.getSelectedItem()){
							case "1": banios =1; break;
							case "2": banios =2; break;
							case "3 o más": banios =3; break;
						}
				}
			};
			comboBox.addActionListener(actionL);
			
			panel.add(jLabel);
			panel.add(comboBox);
			return panel;
		}
}
