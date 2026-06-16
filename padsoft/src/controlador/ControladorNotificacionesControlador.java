package controlador;

import vista.*;

import java.awt.event.ActionEvent;

import vista.VentanaPrincipal;
import sistema.SkyCompass;

/**
 * Clase que representa el controlador de la vista de notificaciones controlador.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControladorNotificacionesControlador extends ControladorNotificaciones {

    /**
     * Constructor que inicializa el controlador con la vista específica y la aplicación.
     * 
     * @param vista Vista de notificaciones específica para el controlador
     * @param app   Instancia principal de la aplicación
     * 
     */

    public ControladorNotificacionesControlador(NotificacionesControlador vista, SkyCompass app) {
        super(vista, app);
    }

    /**
     * Maneja los eventos de acción. En este caso, responde al botón "Volver"
     * cambiando la vista al panel principal del operador.
     * 
     * @param e Evento de acción recibido
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        vista.getbotonVolver().addActionListener(ev -> vista.getFrame().changeVisiblePanel(
                VentanaPrincipal.PRINCIPALOPERADOR));
    }
}
