package controlador;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import sistema.SkyCompass;
import vista.*;

/**
 * Clase que representa el controlador de la vista de reseatear
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlResetear implements ActionListener {

    private VentanaPrincipal frame;
    private SkyCompass app;
    private Resetear vista;

    /**
     * Constructor de la clase ControlLoginUsuario.
     * 
     * @param frame ventana principal
     * @param app   aplicacion
     * @param vista vista
     */
    public ControlResetear(VentanaPrincipal frame, SkyCompass app, Resetear vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Método que se encarga de gestionar el evento de pulsar el botón
     * en la vista de login. Según el botón pulsado, cambia el panel visible
     * por el correspondiente al gestor, el operador o el controlador.
     * 
     * @param e evento al pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
        } else if (e.getActionCommand().equals("Resetear")) {
            this.resetearOperadores();
            vista.update();
            this.actualizarOperadores();
        }
    }

    
    /**
     * Actualiza la lista de operadores en la vista de resetear
     */
    public void actualizarOperadores() {
        vista.setOperadoresBloqueados(app.getOperadores());
    }

    /**
     * Realiza el reseteo de numero de fallos de un operador
     */
    private void resetearOperadores() {
        List<String> dnis = vista.getDnisSeleccionados();
        for (String dni : dnis) {
            app.resetear(dni);
        }

        if(dnis.size() > 0) {
            JOptionPane.showMessageDialog(vista, "Operadores reseteados", "Operadores reseteados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
