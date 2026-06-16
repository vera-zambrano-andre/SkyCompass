package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

import sistema.SkyCompass;
import usuario.Operador;
import vista.HistorialDescuentos;
import vista.VentanaPrincipal;
import factura.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa el controlador historial de descuentos de operador.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlHistorialDescuentosOperador implements ActionListener {
    private VentanaPrincipal frame;
    private SkyCompass app;
    private HistorialDescuentos vista;

    /**
     * Constructor de la clase ControlHistorialDescuentosOperador.
     * Inicializa el controlador con referencias a la ventana principal,
     * al sistema central (SkyCompass) y a la vista correspondiente.
     *
     * @param frame Ventana principal de la aplicación.
     * @param app Instancia del sistema principal SkyCompass.
     * @param vista Vista gráfica del historial de descuentos.
     */
    public ControlHistorialDescuentosOperador(VentanaPrincipal frame, SkyCompass app, HistorialDescuentos vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;

        vista.getLabelInfo().setVisible(false);
    }

    /**
     * Método que gestiona los eventos de acción (por ahora, solo el botón "Volver").
     *
     * @param e Evento de acción generado por la interfaz gráfica.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
        }
    }

    /**
     * Actualiza la tabla de descuentos cargando los datos desde el modelo.
     * Solo se actualiza si la lista de descuentos no es nula.
     */
    public void actualizarDescuentos() {

        if(app.getDescuentos() == null) return;

        List<Descuento> descuentos = app.getDescuentos();

        this.vista.cargarDescuentos(descuentos);
    }
    
}
