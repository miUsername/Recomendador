package interfaz;

import javax.swing.JFrame;

public class VentanaPrimcipal extends JFrame{
	public PanelPrincipal panelPPal;
	
	public VentanaPrimcipal(){
		this.panelPPal=new PanelPrincipal();
		this.setVisible(true);
		this.setSize(500, 500);
		this.add(panelPPal);
	}
}
