package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import aeropuerto.Avion;
import aeropuerto.TipoAvion;
import sistema.SkyCompass;
import vista.DarAltaAvion;
import vista.VentanaPrincipal;

/**
 * Clase que representa el controlador de dar alta avion.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlDarAltaAvion implements ActionListener{
    private VentanaPrincipal frame;
    private SkyCompass app;
    private DarAltaAvion vista;

    /**
     * Constructor del controlador de alta de avión.
     * 
     * @param frame Ventana principal de la aplicación
     * @param app   Instancia de la lógica de negocio
     * @param vista Vista de alta de avión
     */
    public ControlDarAltaAvion(VentanaPrincipal frame, SkyCompass app, DarAltaAvion vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Maneja las acciones de la vista, como volver al menú principal o dar de alta un avión.
     * 
     * @param e Evento generado por la interacción del usuario
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
        }

        if(e.getActionCommand().equals("Dar de alta")) {
            String anoCompra = this.vista.getAnoCompra();
            String fechaRevision = this.vista.getFechaRevision();
            TipoAvion tipo = this.vista.getTipoAvionSeleccionado();
            
            if(anoCompra.isEmpty() || fechaRevision.isEmpty() || tipo.toString().equals("")) {
                JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDate.parse(fechaRevision);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(vista, "Formato de fecha incorrecto, debe ser AAAA-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(anoCompra);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El año de compra debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Avion avion = app.darAltaAvion(Integer.parseInt(anoCompra), LocalDate.parse(fechaRevision), tipo);

            if(avion != null) {
                JOptionPane.showMessageDialog(vista, "Avión dado de alta con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "No se ha podido dar de alta el avión", "Error", JOptionPane.ERROR_MESSAGE);
            }
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
        }
    }

    /**
     * Actualiza la lista de tipos de avión disponibles en la vista.
     * Se debe llamar al inicializar la pantalla o si cambia la lista en la aplicación.
     */
    public void update() {
        if(app.getTiposAvion() == null) {
            return;
        }

        this.vista.setTiposAvion(app.getTiposAvion());
    }
}
