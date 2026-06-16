package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import factura.CondicionDescuento;
import factura.CondicionLimiteImporte;
import factura.CondicionLimiteVuelos;
import sistema.SkyCompass;
import vista.DarAltaDescuentos;
import vista.VentanaPrincipal;
import factura.Descuento;

/**
 * Clase que representa el controlador de la dar alta de descuento.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlDarAltaDescuento implements ActionListener {
    private VentanaPrincipal frame;
    private SkyCompass app;
    private DarAltaDescuentos vista;

    /**
     * Constructor del controlador de alta de descuentos.
     * 
     * @param frame Ventana principal de la aplicación
     * @param app   Instancia de la lógica del sistema
     * @param vista Vista para dar de alta descuentos
     */
    public ControlDarAltaDescuento(VentanaPrincipal frame, SkyCompass app, DarAltaDescuentos vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Maneja los eventos de la vista relacionados con el alta de descuentos o volver al menú.
     * 
     * @param e Evento de acción generado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
            vista.limpiar();
            return;
        }

        if(e.getActionCommand().equals("Dar Alta Descuento")) {
            String descripcion = vista.getDescripcion();
            String porcentaje = vista.getPorcentaje();
            String fechaInicio = vista.getFechaInicio();
            String fechaFin = vista.getFechaFin();
            String limite = vista.getLimite();
            boolean condicionVuelos = vista.isCondicionVuelos();
            boolean condicionImporte = vista.isCondicionImporte();
            boolean tipoTotal = vista.isTipoTotal();
            boolean tipoRecursos = vista.isTipoRecursos();

            if(descripcion.isEmpty() || porcentaje.isEmpty() || fechaInicio.isEmpty() || fechaFin.isEmpty() || limite.isEmpty() || !condicionVuelos && !condicionImporte || !tipoTotal && !tipoRecursos) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                return;
            }

            try {
                LocalDate.parse(fechaInicio);
                LocalDate.parse(fechaFin);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto, debe ser AAAA-MM-DD");
                return;
            }

            try {
                Double.parseDouble(porcentaje);
                Integer.parseInt(limite);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El porcentaje debe ser un número decimal y el limite un número entero");
                return;
            }

            if(Double.parseDouble(porcentaje) < 1 || Double.parseDouble(porcentaje) > 50) {
                JOptionPane.showMessageDialog(null, "El porcentaje debe estar entre 1 y 50");
                return;
            }

            CondicionDescuento condicion = null;
            if(condicionVuelos){
                condicion = new CondicionLimiteVuelos(Integer.parseInt(limite));
            }
            else{
                condicion = new CondicionLimiteImporte(30);
            }

            Descuento descuento = app.darAltaDescuento(descripcion, LocalDate.parse(fechaInicio), LocalDate.parse(fechaFin), Double.parseDouble(porcentaje), condicion, tipoTotal);

            if(descuento == null) {
                JOptionPane.showMessageDialog(null, "No se ha podido dar de alta el descuento");
                return;
            }
            else {
                JOptionPane.showMessageDialog(null, "Descuento dado de alta correctamente");
                frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                vista.limpiar();
                return;
            }

        }
    }

}
