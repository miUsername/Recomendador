package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPrincipal extends JPanel{
	//Busqueda
	
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

			JLabel jLabel = new JLabel("Superficie: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("hasta 70 m2");
			comboBox.addItem("70-100 m2");
			comboBox.addItem("100-150 m2");
			comboBox.addItem("150-200 m2");
			comboBox.addItem("más de 200 m2");
		
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "hasta 70 m2": break;
							case "70-100 m2": break;
							case "100-150 m2": break;
							case "150-200 m2": break;
							case "más de 200 m2": break;
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
			JLabel jLabel = new JLabel("Estado: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
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
			JLabel jLabel = new JLabel("Habitaciones: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("Estudio");
			comboBox.addItem("1");
			comboBox.addItem("2");
			comboBox.addItem("3");
			comboBox.addItem("4 o más");
		
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "Estudio": break;
							case "1": break;
							case "2": break;
							case "3": break;
							case "4 o más": break;
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
			JLabel jLabel = new JLabel("Baños: ");		
			final JComboBox<String> comboBox = new JComboBox<String>() ; 
			comboBox.addItem("1");
			comboBox.addItem("2");
			comboBox.addItem("3 o más");
		
			ActionListener actionL = new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if (e.getSource() == comboBox){
						switch ((String)comboBox.getSelectedItem()){
							case "1": break;
							case "2": break;
							case "3 o más": break;
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
