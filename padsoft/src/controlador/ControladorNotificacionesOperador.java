package controlador;

import vista.NotificacionesOperador;

import java.awt.event.ActionEvent;

import vista.VentanaPrincipal;
import sistema.SkyCompass;

/**
 * Clase que representa el controlador de la vista de filtrado de los operador.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControladorNotificacionesOperador extends ControladorNotificaciones {

    /**
     * Constructor que inicializa el controlador con la vista del operador y la aplicación.
     * 
     * @param vista Vista específica de notificaciones para operadores
     * @param app   Instancia principal de la aplicación
     */
    public ControladorNotificacionesOperador(NotificacionesOperador vista, SkyCompass app) {
        super(vista, app);
    }

    /**
     * Maneja los eventos de acción. En este caso, se encarga de responder al botón "Volver"
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
