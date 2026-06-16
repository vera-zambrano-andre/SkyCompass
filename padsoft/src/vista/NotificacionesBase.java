package vista;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

import usuario.Notificacion;

/**
 * Clase abstracta que representa la vista de la tabla de vuelos para filtrado.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public abstract class NotificacionesBase extends JPanel {

    
    /**
     * Actualiza la tabla con la lista de notificaciones.
     * @param notificaciones lista de notificaciones a mostrar
     */
    public abstract void actualizarTabla(List<Notificacion> notificaciones);

    /**
     * Devuelve el bot n "Volver" para asignar controladores o acceder desde otras clases.
     * @return JButton del bot n Volver
     */
    public abstract JButton getbotonVolver();

    /**
     * Devuelve la referencia a la ventana principal.
     * @return VentanaPrincipal ventana principal
     */
    public abstract VentanaPrincipal getFrame();
}
