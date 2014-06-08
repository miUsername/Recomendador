package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ucm.fdi.isbc.viviendas.representacion.Coordenada;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasBasicos;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasFinca;
import es.ucm.fdi.isbc.viviendas.representacion.ExtrasOtros;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda.EstadoVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda.TipoVivienda;

public class PanelPrincipal extends JPanel{
	//Busqueda
	
	/*
	 * Atributos de las viviendas para buscar:
	 * TipoVivienda {Atico, Plantabaja, Piso, Loft, Casaadosada, CasaChalet, Duplex, Estudio, Fincarustica, Apartamento}
	   EstadoVivienda { Muybien, Reformado, Areformar, Casinuevo, Bien};	
		Integer superficie;
		Integer habitaciones;
		Integer banios;	
		Coordenada coordenada;	
		Integer precio;
		ExtrasFinca extrasFinca;
		ExtrasBasicos extrasBasicos;
		ExtrasOtros extrasOtros;
	 * **/
		//private JCheckBox todos;
		private JComboBox tipoV;
		private JComboBox estadoV;
		private JComboBox superficie;
		private JComboBox habitaciones; 
		private JComboBox banios;
		private JCheckBox coordenada;
		private JCheckBox precio;
		private JCheckBox extrasFinca;
		private JCheckBox extrasBasicos;
		private JCheckBox extrasOtros;
		
		public PanelPrincipal(){
			/*atributos ppales*/
			this.setVisible(true);
			this.setEnabled(true);
			this.setSize(300, 700);
			
			/*a�adimos los paneles al ppal*/
			this.add(getPanelTipo());
			this.add(getPanelEstado());
			this.add(getPanelSuperficie());
			this.add(getPanelHabitaciones());
			this.add(getPanelBanios());
			}
		
		//elegir tipoV
		private JPanel getPanelTipo(){
			final JPanel  panel= new JPanel();
			/*panelTipoV.setLayout(new BoxLayout(panelTipoV, BoxLayout.LINE_AXIS));
			panelTipoV.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			panelTipoV.add(Box.createRigidArea(new Dimension(10, 10)));
			panelTipoV.setBackground(Color.WHITE);	
*/
			JLabel jLabel = new JLabel("Tipo: ");		
			final JComboBox comboBox = new JComboBox() ; 
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
		
			
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "Atico": break;
							case "Plantabaja": break;
							case "Piso": break;
							case "Loft": break;
							case "Adosado": break;
							case "Chalet": break;
							case "Duplex": break;
							case "Estudio": break;
							case "Finca": break;
							case "Apartamento": break;
						
						}
					/*	if ((String)comboBoxtipoV.getSelectedItem() == )
							lista = BaseAnimalDescription.getInstance().getListRazasPerro();
						if ((String)animal.getSelectedItem() == "Gato")
							lista = BaseAnimalDescription.getInstance().getListRazasGato();
						for (String aux : lista){
							raza.addItem(aux);
						}*/
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
			/*panelTipoV.setLayout(new BoxLayout(panelTipoV, BoxLayout.LINE_AXIS));
			panelTipoV.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			panelTipoV.add(Box.createRigidArea(new Dimension(10, 10)));
			panelTipoV.setBackground(Color.WHITE);	
*/
			JLabel jLabel = new JLabel("Superficie: ");		
			final JComboBox comboBox = new JComboBox() ; 
			comboBox.addItem("hasta 70 m2");
			comboBox.addItem("70-100 m2");
			comboBox.addItem("100-150 m2");
			comboBox.addItem("150-200 m2");
			comboBox.addItem("m�s de 200 m2");
		
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "hasta 70 m2": break;
							case "70-100 m2": break;
							case "100-150 m2": break;
							case "150-200 m2": break;
							case "m�s de 200 m2": break;
						}
					/*	if ((String)comboBoxtipoV.getSelectedItem() == )
							lista = BaseAnimalDescription.getInstance().getListRazasPerro();
						if ((String)animal.getSelectedItem() == "Gato")
							lista = BaseAnimalDescription.getInstance().getListRazasGato();
						for (String aux : lista){
							raza.addItem(aux);
						}*/
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
			/*panelTipoV.setLayout(new BoxLayout(panelTipoV, BoxLayout.LINE_AXIS));
			panelTipoV.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			panelTipoV.add(Box.createRigidArea(new Dimension(10, 10)));
			panelTipoV.setBackground(Color.WHITE);	
*/
			JLabel jLabel = new JLabel("Estado: ");		
			final JComboBox comboBox = new JComboBox() ; 
			comboBox.addItem("Muy Bien");
			comboBox.addItem("Reformado");
			comboBox.addItem("A reformar");
			comboBox.addItem("Casi nuevo");
			comboBox.addItem("Bien");
		
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "Muy Bien": break;
							case "Reformado": break;
							case "A reformar": break;
							case "Casi nuevo": break;
							case "Bien": break;
						}
					/*	if ((String)comboBoxtipoV.getSelectedItem() == )
							lista = BaseAnimalDescription.getInstance().getListRazasPerro();
						if ((String)animal.getSelectedItem() == "Gato")
							lista = BaseAnimalDescription.getInstance().getListRazasGato();
						for (String aux : lista){
							raza.addItem(aux);
						}*/
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
			/*panelTipoV.setLayout(new BoxLayout(panelTipoV, BoxLayout.LINE_AXIS));
			panelTipoV.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			panelTipoV.add(Box.createRigidArea(new Dimension(10, 10)));
			panelTipoV.setBackground(Color.WHITE);	
*/
			JLabel jLabel = new JLabel("Habitaciones: ");		
			final JComboBox comboBox = new JComboBox() ; 
			comboBox.addItem("Estudio");
			comboBox.addItem("1");
			comboBox.addItem("2");
			comboBox.addItem("3");
			comboBox.addItem("4 o m�s");
		
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "Estudio": break;
							case "1": break;
							case "2": break;
							case "3": break;
							case "4 o m�s": break;
						}
					/*	if ((String)comboBoxtipoV.getSelectedItem() == )
							lista = BaseAnimalDescription.getInstance().getListRazasPerro();
						if ((String)animal.getSelectedItem() == "Gato")
							lista = BaseAnimalDescription.getInstance().getListRazasGato();
						for (String aux : lista){
							raza.addItem(aux);
						}*/
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
			/*panelTipoV.setLayout(new BoxLayout(panelTipoV, BoxLayout.LINE_AXIS));
			panelTipoV.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			panelTipoV.add(Box.createRigidArea(new Dimension(10, 10)));
			panelTipoV.setBackground(Color.WHITE);	
*/
			JLabel jLabel = new JLabel("Ba�os: ");		
			final JComboBox comboBox = new JComboBox() ; 
			comboBox.addItem("1");
			comboBox.addItem("2");
			comboBox.addItem("3 o m�s");
		
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "1": break;
							case "2": break;
							case "3 o m�s": break;
						}
					/*	if ((String)comboBoxtipoV.getSelectedItem() == )
							lista = BaseAnimalDescription.getInstance().getListRazasPerro();
						if ((String)animal.getSelectedItem() == "Gato")
							lista = BaseAnimalDescription.getInstance().getListRazasGato();
						for (String aux : lista){
							raza.addItem(aux);
						}*/
					} 
				}
			};
			comboBox.addActionListener(actionL);
			
			panel.add(jLabel);
			panel.add(comboBox);
			return panel;
		}	
		
}