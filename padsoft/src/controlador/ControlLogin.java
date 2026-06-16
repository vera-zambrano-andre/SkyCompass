package controlador;

import java.awt.event.*;
import vista.*;

/**
 * Clase que representa el controlador de la vista de login.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlLogin implements ActionListener{

    private VentanaPrincipal frame;
	
    /**
     * Constructor de la clase ControlLogin
     * @param frame ventana principal
     */
	public ControlLogin(VentanaPrincipal frame) {
		this.frame = frame;
	}

    /**
     * Método que se encarga de gestionar el evento de pulsar el botón
     * en la vista de login. Según el botón pulsado, cambia el panel visible
     * por el correspondiente al gestor, el operador o el controlador.
     * @param e evento al pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.BIENVENIDA);
        }
        else if(e.getActionCommand().equals("Gestor")) {
            frame.changeVisiblePanel(VentanaPrincipal.LOGINGESTOR);
        }
        else if(e.getActionCommand().equals("Operador")) {
            frame.changeVisiblePanel(VentanaPrincipal.LOGINOPERADOR);
        }
        else if(e.getActionCommand().equals("Controlador")) {
            frame.changeVisiblePanel(VentanaPrincipal.LOGINCONTROLADOR);
        }
        
    }
}
