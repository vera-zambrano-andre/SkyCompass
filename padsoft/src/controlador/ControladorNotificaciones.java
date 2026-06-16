package controlador;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import vista.NotificacionesBase;
import sistema.SkyCompass;
import usuario.Notificacion;

/**
 * Clase que representa el controlador de notificaciones de todos los usuarios.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public abstract class ControladorNotificaciones implements ActionListener {
    protected final NotificacionesBase vista;
    protected final SkyCompass app;

    /**
     * Constructor que inicializa la vista y la aplicación.
     * También añade el ActionListener al botón "Volver".
     *
     * @param vista Vista base de notificaciones
     * @param app   Instancia principal de la aplicación
     */
    public ControladorNotificaciones(NotificacionesBase vista, SkyCompass app) {
        this.vista = vista;
        this.app = app;
        vista.getbotonVolver().addActionListener(this);
    }

    /**
     * Carga y muestra las notificaciones del usuario actualmente logueado.
     * Si no hay usuario logueado, muestra un mensaje en consola.
     *
     */
    public void cargarNotificaciones() {
        if (app.getUsuarioLogeado() != null) {
            List<Notificacion> notificaciones = app.getUsuarioLogeado().getNotificaciones();

            System.out.println("Cargando notificaciones: " + notificaciones.size());
            for (Notificacion n : notificaciones) {
                System.out.println(" - " + n);
            }

            vista.actualizarTabla(notificaciones);
        } else {
            System.out.println("No hay usuario logueado.");
        }
    }
}