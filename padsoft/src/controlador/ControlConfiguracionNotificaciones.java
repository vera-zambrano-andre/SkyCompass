package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sistema.SkyCompass;
import usuario.Gestor;
import vista.ConfiguracionNotificaciones;
import vista.VentanaPrincipal;
import vuelo.EstadoVuelo;
import vuelo.Vuelo;

/**
 * Clase que representa el controlador de la vista de configuracion de
 * notificaciones
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlConfiguracionNotificaciones implements ActionListener {

    private VentanaPrincipal frame;
    private ConfiguracionNotificaciones vista;
    private SkyCompass app;
    private Gestor gestor;


    /**
     * Constructor de la clase ControlConfiguracionNotificaciones
     * 
     * @param frame   Ventana principal de la aplicación
     * @param vista   Vista de configuración de notificaciones
     * @param app     Instancia del sistema principal SkyCompass
     */
    public ControlConfiguracionNotificaciones(VentanaPrincipal frame, ConfiguracionNotificaciones vista,
            SkyCompass app) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!(app.getUsuarioLogeado() instanceof Gestor)) {
            return;
        }

        gestor = (Gestor) app.getUsuarioLogeado();

        if (e.getActionCommand().equals("Cancelar")) {

            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);

            this.limpiar();

            return;
        }

        if (e.getActionCommand().equals("Aceptar")) {

            if (vista.isNotificacionesActivadas()) {
                gestor.activarNotificaciones();
            } else {
                gestor.desactivarNotificaciones();
                JOptionPane.showMessageDialog(vista,
                        " Las notificaciones han sido desactivadas, por lo cuál no recibirás ninguna (independientemente de los vuelos y estados seleccionados)");
                frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                this.limpiar();
                return;
            }

            gestor.getVuelosNotificaciones().clear();
            gestor.getCambiosEstadoNotificaciones().clear();

            if (vista.isNotificacionesActivadas()) {
                for (Vuelo v : vista.getVuelosSeleccionados()) {
                    gestor.addVueloNotificacion(v);
                }
                for (EstadoVuelo ev : vista.getEstadosSeleccionados()) {
                    gestor.addCambiosEstadoNotificaciones(ev.name());

                }
            }

            if (gestor.getVuelosNotificaciones().isEmpty() && gestor.getCambiosEstadoNotificaciones().isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Al no haber elementos seleccionados, se recibirán notificaciones de todos los vuelos y estados.");
                frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                this.limpiar();
                return;
            }

            System.out.println(gestor.getVuelosNotificaciones());
            System.out.println(gestor.getCambiosEstadoNotificaciones());
            System.out.println(gestor.getNotificacionesActivadas());
            JOptionPane.showMessageDialog(vista, "Se han activado las notificaciones de los elementos seleccionados.");
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);

            this.limpiar();
        }
    }

    /**
     * Restablece el estado de la vista de configuración de notificaciones.
     * 
     */

    public void limpiar() {
        if (gestor.getNotificacionesActivadas()) {
            vista.getCbActivar().setSelected(true);
        } else {
            vista.getCbActivar().setSelected(false);
        }

        vista.getListVuelos().clearSelection();

        vista.getListEstados().clearSelection();
    }
}
