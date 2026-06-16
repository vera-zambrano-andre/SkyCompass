package controlador;

import java.awt.event.*;

import vista.VentanaPrincipal;

/**
 * Clase que representa el controlador de la vista de bienvenida.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlBienvenida implements ActionListener{

    private VentanaPrincipal frame;
	
    /**
     * Constructor de la clase ControlBienvenida.
     * @param frame ventana principal
     */
	public ControlBienvenida(VentanaPrincipal frame) {
		this.frame = frame;
	}
    
    /**
     * Método que se encarga de gestionar el evento de pulsar el botón
     * "Comenzar" en la vista de bienvenida. Cambia el panel visible por el de login.
     * @param e evento de pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changeVisiblePanel(VentanaPrincipal.LOGIN);
    }
}
