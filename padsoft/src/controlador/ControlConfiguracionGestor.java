package controlador;

import java.awt.event.*;

import javax.swing.JOptionPane;

import sistema.SkyCompass;
import usuario.ConfiguracionPorDefecto;
import vista.*;

/**
 * Clase que representa el controlador de la vista de configuracion por defecto
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlConfiguracionGestor implements ActionListener {

    private VentanaPrincipal frame;
    private SkyCompass app;
    private ConfiguracionesGestor vista;

    /**
     * Constructor de la clase ControlLoginUsuario.
     * 
     * @param frame ventana principal
     * @param app   aplicacion
     * @param vista vista
     */
    public ControlConfiguracionGestor(VentanaPrincipal frame, SkyCompass app, ConfiguracionesGestor vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Método que se encarga de gestionar el evento de pulsar el botón
     * 
     * 
     * @param e evento al pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
        } else if (e.getActionCommand().equals("Aceptar")) {
            try {
                if (!this.aceptar()) {
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista,
                        "Error en el formato de los campos, por favor escribe valores válidos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(vista, "Configuración cambiada correctamente", "Configuración cambiada",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("Cancelar")) {
            this.cancelar();
        }
    }

    /**
     * Realiza el cambio de configuracion
     */
    private boolean aceptar() {

        Integer diasAntelacionField = vista.getDiasAntelacion();
        Integer toleranciaParkingField = vista.getTolerancia();
        Integer rangoTerminalesField = vista.getRangoTerminal();
        Integer reservaTerminalField = vista.getReservaTerminal();

        ConfiguracionPorDefecto conf = ConfiguracionPorDefecto.getConfiguracion();

        boolean sinCambios = diasAntelacionField.equals(conf.getDiasAntelacionMinProgramacionVuelo()) &&
                toleranciaParkingField.equals(conf.getRangoMinutosDisponibilidadZAFinger()) &&
                rangoTerminalesField.equals(conf.getRangoHorasDisponibleTerminal()) &&
                reservaTerminalField.equals(conf.getMinutosReservaTerminal());

        if (sinCambios) {
            JOptionPane.showMessageDialog(vista,
                    "No se ha realizado ningún cambio en la configuración.",
                    "Sin cambios",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        app.aceptarConfiguracion(diasAntelacionField, toleranciaParkingField, rangoTerminalesField,
                reservaTerminalField);
        vista.update();

        return true;
    }

    /**
     * Deshace el cambio
     */
    private void cancelar() {
        vista.update();
    }
}
