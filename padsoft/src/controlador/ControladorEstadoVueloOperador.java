package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;
import sistema.SkyCompass;
import vista.GestionEstadoVueloOperador;
import vista.VentanaPrincipal;
import vuelo.Vuelo;

/**
 * Clase que representa el controlador de la vista de estados de un vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControladorEstadoVueloOperador implements ActionListener {
    private final VentanaPrincipal frame;
    private final GestionEstadoVueloOperador vista;
    private final SkyCompass app;
    private Vuelo vueloActual;
    private final String anterior;

    /**
     * Constructor de la clase ControladorEstadoVuelo.
     * 
     * @param frame    ventana principal
     * @param vista    vista
     * @param app      aplicacion principal
     * @param anterior nombre de la pestaña a la que se debe regresar al pulsar el
     *                 botón "volver"
     */
    public ControladorEstadoVueloOperador(VentanaPrincipal frame, GestionEstadoVueloOperador vista, SkyCompass app, String anterior) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
        this.anterior = anterior;
    }

    /**
     * Método que se encarga de gestionar los eventos de la vista de estados de un
     * vuelo.
     * 
     * @param e evento de pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "buscar":
                buscarVuelo();
                break;
            case "volver":
                frame.changeVisiblePanel(anterior);
                break;
            default:
                ejecutarAccion(cmd);
                break;
        }
    }

    /**
     * Busca un vuelo a partir del código introducido en la vista.
     * Actualiza la vista con el estado del vuelo y habilita/deshabilita los botones
     * según si se encontró.
     */
    private void buscarVuelo() {
        String codigo = vista.getCodigoField().getText().trim();
        String fecha = vista.getFechaField().getText().trim();

        if (codigo.isEmpty() || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.");
            return;
        }

        try {
            LocalDate.parse(fecha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Formato de fecha incorrecto.");
            return;
        }

        vueloActual = app.buscarVueloCodigoFecha(codigo, LocalDate.parse(fecha));
        
        if (vueloActual == null) {
            JOptionPane.showMessageDialog(vista, "Vuelo no encontrado.");
            vista.getEstadoLabel().setText("Estado: ");
            setBotonesEnabled(false);
        } else {
            JOptionPane.showMessageDialog(vista, "Vuelo encontrado.");
            vista.getEstadoLabel().setText("Estado: " + vueloActual.getEstadoVuelo());
            setBotonesEnabled(true);
        }
    }

    /**
     * Ejecuta una acción sobre el vuelo actual según el nombre del método recibido.
     * Llama a los métodos correspondientes en el modelo SkyCompass.
     * Muestra un mensaje con el resultado y actualiza el estado mostrado en la
     * vista.
     * 
     * @param metodo Nombre del método/acción a ejecutar
     */

    private void ejecutarAccion(String metodo) {
        boolean ok = false;
        try {
            switch (metodo) {
                case "iniciarEmbarque":
                    ok = app.iniciarEmbarque(vueloActual);
                    break;
                case "finalizarEmbarque":
                    ok = app.finalizarEmbarque(vueloActual);
                    break;
                case "iniciarDesembarque":
                    ok = app.iniciarDesembarque(vueloActual);
                    break;
                case "finalizarDesembarque":
                    ok = app.finalizarDesembarque(vueloActual);
                    break;
            }

            if (ok) {
                JOptionPane.showMessageDialog(vista,
                        "Acción realizada correctamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Acción no se pudo completar.",
                        "Fallo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(vista,
                    ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (vueloActual != null) {
            vista.getEstadoLabel().setText(
                    "Estado: " + vueloActual.getEstadoVuelo());
        }
    }

    /**
     * Habilita o deshabilita todos los botones del panel según el valor del
     * parámetro.
     * 
     * @param enabled true para habilitar, false para deshabilitar
     */
    private void setBotonesEnabled(boolean enabled) {
        for (Component c : vista.getBotonesPanel().getComponents()) {
            c.setEnabled(enabled);
        }
    }
}
