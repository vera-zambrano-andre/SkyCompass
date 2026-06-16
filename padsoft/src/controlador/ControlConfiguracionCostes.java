package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import aeropuerto.Direccion;
import factura.Factura;
import sistema.SkyCompass;
import vista.ConfiguracionCostes;
import vista.VentanaPrincipal;

/**
 * Clase que representa el controlador de la configuraciones de costes.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlConfiguracionCostes implements ActionListener{
    private VentanaPrincipal frame;
    private SkyCompass app;
    private ConfiguracionCostes vista;

    /**
     * Constructor del controlador.
     * 
     * @param frame Ventana principal de la aplicación
     * @param app   Instancia principal del sistema
     * @param vista Vista de configuración de costes
     */
    public ControlConfiguracionCostes(VentanaPrincipal frame, SkyCompass app, ConfiguracionCostes vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Maneja los eventos de los botones de la vista: "← Volver", "Guardar" y "Cancelar".
     * 
     * @param e Evento de acción recibido
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
     * Guarda la configuración ingresada por el usuario, si es válida y diferente
     * a la configuración actual.
     * 
     * @return true si se realizaron cambios, false si no hubo modificaciones
     */
    private boolean guardar() {

        Double costeBase = vista.getCosteBase();
        Double recargoMercancias = vista.getRecargoMercancias();
        Double recargoPasajeros = vista.getRecargoPasajeros();

        boolean sinCambios = costeBase.equals(Factura.getPrecioBase()) &&
                recargoMercancias.equals(Factura.getRecargoMercancias()) &&
                recargoPasajeros.equals(Factura.getRecargoPasajeros());

        if (sinCambios) {
            JOptionPane.showMessageDialog(vista,
                    "No se ha realizado ningún cambio en la configuración.",
                    "Sin cambios",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        

       Factura.setPrecioBase(costeBase);
       Factura.setRecargoMercancias(recargoMercancias);
       Factura.setRecargoPasajeros(recargoPasajeros);     


        vista.update();

        return true;
    }

    /**
     * Cancela los cambios y recarga la configuración actual en la vista.
     */
    public void cancelar (){
        vista.update();
    }
}
