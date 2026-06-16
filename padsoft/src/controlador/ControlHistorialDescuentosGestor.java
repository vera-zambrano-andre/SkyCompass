package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sistema.SkyCompass;
import usuario.Operador;
import vista.HistorialDescuentos;
import vista.VentanaPrincipal;
import factura.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa el controlador del historial de descuentos del gestor.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlHistorialDescuentosGestor implements ActionListener, ListSelectionListener {
    private VentanaPrincipal frame;
    private SkyCompass app;
    private HistorialDescuentos vista;
    private List<Descuento> descuentos;

    /**
     * Constructor de la clase ControlHistorialDescuentosGestor.
     * Inicializa el controlador con las referencias necesarias para
     * interactuar con la vista y la lógica de negocio.
     *
     * @param frame Ventana principal de la aplicación.
     * @param app Instancia del sistema principal SkyCompass.
     * @param vista Vista gráfica del historial de descuentos.
     */
    public ControlHistorialDescuentosGestor(VentanaPrincipal frame, SkyCompass app, HistorialDescuentos vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Método que gestiona los eventos de acción (botones, selecciones, etc.).
     * Se encarga de gestionar la navegación, edición y eliminación de descuentos.
     *
     * @param e Evento de acción generado por la interfaz gráfica.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
            vista.limpiar();
        }

        String seleccion = (String) vista.getComboAccion().getSelectedItem();

        if (seleccion.equals("Editar periodo")) {
            vista.getCampoFechaInicio().setVisible(true);
            vista.getCampoFechaFin().setVisible(true);
            vista.getFechaInicioLabel().setVisible(true);
            vista.getFechaFinLabel().setVisible(true);
            vista.getBotonEditarPeriodo().setVisible(true);
            vista.getBotonEliminarPeriodo().setVisible(false);
        } else if (seleccion.equals("Eliminar periodo")) {
            vista.getCampoFechaInicio().setVisible(false);
            vista.getCampoFechaFin().setVisible(false);
            vista.getFechaInicioLabel().setVisible(false);
            vista.getFechaFinLabel().setVisible(false);
            vista.getBotonEditarPeriodo().setVisible(false);
            vista.getBotonEliminarPeriodo().setVisible(true);
        } else {
            vista.getCampoFechaInicio().setVisible(false);
            vista.getCampoFechaFin().setVisible(false);
            vista.getFechaInicioLabel().setVisible(false);
            vista.getFechaFinLabel().setVisible(false);
            vista.getBotonEditarPeriodo().setVisible(false);
            vista.getBotonEliminarPeriodo().setVisible(false);
        }

        if (e.getActionCommand().equals("Editar")) {
            int fila = vista.getTabla().getSelectedRow();

            Descuento descuento = null;
            if (fila >= 0) {
                descuento = descuentos.get(fila);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione primero un descuento de la tabla.");
                return;
            }

            String fechaInicio = vista.getCampoFechaInicio().getText();
            String fechaFin = vista.getCampoFechaFin().getText();

            if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                return;
            }

            LocalDate inicio = null;
            LocalDate fin = null;
            try {
                inicio = LocalDate.parse(fechaInicio);
                fin = LocalDate.parse(fechaFin);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto: el formato debe ser AAAA-MM-DD.");
                return;
            }

            try {
                app.definirNuevoPeriodoDescuento(descuento, inicio, fin);
                JOptionPane.showMessageDialog(null, "El periodo del descuento ha sido modificado correctamente.");
                vista.limpiar();
                actualizarDescuentos();
                return;
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el periodo del descuento: " + ex.getMessage());
                vista.limpiar();
                return;
            }
        }

        if (e.getActionCommand().equals("Eliminar")) {
            int fila = vista.getTabla().getSelectedRow();

            Descuento descuento = null;
            if (fila >= 0) {
                descuento = descuentos.get(fila);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione primero un descuento de la tabla.");
                return;
            }
            try {
                app.eliminarPeriodoDescuento(descuento);
                JOptionPane.showMessageDialog(null, "El periodo del descuento ha sido eliminado correctamente.");
                vista.limpiar();
                actualizarDescuentos();
                return;
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el periodo del descuento: " + ex.getMessage());
                vista.limpiar();
                return;
            }
        }
    }

    /**
     * Método que gestiona los eventos de selección en la tabla.
     * Muestra u oculta los campos y botones según si hay una fila seleccionada.
     *
     * @param e Evento de selección generado por la tabla.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int fila = vista.getTabla().getSelectedRow();
            if (fila != -1) {
                vista.mostrarSelectorAccion(true);
                vista.getLabelInfo().setVisible(false);
            } else {
                vista.mostrarSelectorAccion(false);
                vista.getBotonEditarPeriodo().setVisible(false);
                vista.getBotonEliminarPeriodo().setVisible(false);
                vista.getCampoFechaInicio().setVisible(false);
                vista.getCampoFechaFin().setVisible(false);
                vista.getFechaInicioLabel().setVisible(false);
                vista.getFechaFinLabel().setVisible(false);
                vista.getLabelInfo().setVisible(true);
            }
        }
    }

    /**
     * Actualiza la tabla de descuentos cargando los datos desde el modelo.
     * Si no hay descuentos, no se realiza ninguna acción.
     */
    public void actualizarDescuentos() {

        descuentos = app.getDescuentos();

        if (descuentos == null)
            return;

        this.vista.cargarDescuentos(descuentos);
    }

}
