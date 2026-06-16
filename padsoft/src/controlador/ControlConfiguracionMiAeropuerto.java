package controlador;

import java.awt.event.*;

import javax.swing.JOptionPane;

import aeropuerto.Direccion;
import sistema.SkyCompass;
import usuario.ConfiguracionPorDefecto;
import vista.*;

/**
 * Clase que representa el controlador de la vista de configuracion de mi aeropuerto
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlConfiguracionMiAeropuerto implements ActionListener {

    private VentanaPrincipal frame;
    private SkyCompass app;
    private ConfiguracionMiAeropuerto vista;

    /**
     * Constructor de la clase ControlConfiguracionMiAeropuerto.
     * 
     * @param frame ventana principal
     * @param app   aplicacion
     * @param vista vista
     */
    public ControlConfiguracionMiAeropuerto(VentanaPrincipal frame, SkyCompass app, ConfiguracionMiAeropuerto vista) {
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
        } else if (e.getActionCommand().equals("Guardar")) {
            try {
                if (!this.guardar()) {
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
    private boolean guardar() {

        String nombre = vista.getNombreAeropuerto();
        String codigo = vista.getCodigoAeropuerto();
        String ciudad = vista.getCiudadAeropuerto();
        String pais = vista.getPaisAeropuerto();
        int distanciaCiudad = vista.getDistanciaCiudad();
        Direccion direccion = vista.getDireccionAeropuerto();
        int diferenciaHoraria = vista.getDiferenciaHoraria();

        boolean sinCambios = nombre.equals(app.getAeropuertoPrincipal().getNombre()) &&
                codigo.equals(app.getAeropuertoPrincipal().getCodigo()) &&
                ciudad.equals(app.getAeropuertoPrincipal().getCiudadMasCercana()) &&
                pais.equals(app.getAeropuertoPrincipal().getPais()) &&
                distanciaCiudad == app.getAeropuertoPrincipal().getDistanciaCiudadKm() &&
                direccion.equals(app.getAeropuertoPrincipal().getDireccion()) &&
                diferenciaHoraria == app.getAeropuertoPrincipal().getDiferenciaHoraria();

        if (sinCambios) {
            JOptionPane.showMessageDialog(vista,
                    "No se ha realizado ningún cambio en la configuración.",
                    "Sin cambios",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        

        app.getAeropuertoPrincipal().setNombre(nombre);
        app.getAeropuertoPrincipal().setCodigo(codigo);
        app.getAeropuertoPrincipal().setCiudadMasCercana(ciudad);
        app.getAeropuertoPrincipal().setPais(pais);
        app.getAeropuertoPrincipal().setDistanciaCiudadKm(distanciaCiudad);
        app.getAeropuertoPrincipal().setDireccion(direccion);
        app.getAeropuertoPrincipal().setDiferenciaHoraria(diferenciaHoraria);        


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
