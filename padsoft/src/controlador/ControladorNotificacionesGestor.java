package controlador;

import vista.*;

import java.awt.event.ActionEvent;

import sistema.SkyCompass;

/**
 * Clase que representa el controlador de la vista de filtrado de los gestores.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControladorNotificacionesGestor extends ControladorNotificaciones {

    /**
     * Constructor que inicializa el controlador con la vista del gestor y la aplicación.
     * 
     * @param vista Vista específica de notificaciones para gestores
     * @param app   Instancia principal de la aplicación
     */
    public ControladorNotificacionesGestor(NotificacionesGestor vista, SkyCompass app) {
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
