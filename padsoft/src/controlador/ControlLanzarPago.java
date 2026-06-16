package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sistema.SkyCompass;
import vista.LanzarPago;
import vista.VentanaPrincipal;

/**
 * Clase que representa el controlador de la lanzar pago.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlLanzarPago implements ActionListener {
    private VentanaPrincipal frame;
    private SkyCompass app;
    private LanzarPago vista;

    /**
     * Constructor del controlador ControlLanzarPago.
     * Inicializa el controlador con la ventana principal, el sistema y la vista.
     *
     * @param frame Ventana principal de la aplicación.
     * @param app Instancia del sistema SkyCompass.
     * @param vista Vista correspondiente a la funcionalidad de lanzar pagos.
     */
    public ControlLanzarPago(VentanaPrincipal frame, SkyCompass app, LanzarPago vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;

    }

    /**
     * Método que responde a eventos de acción.
     * En este caso, se encarga de lanzar una orden de pago cuando el botón
     * correspondiente es presionado, validando previamente que una aerolínea
     * haya sido seleccionada.
     *
     * @param e Evento de acción generado por la vista.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Lanzar orden de pago (del mes actual) para la aerolínea seleccionada")) {

            if(vista.getAerolineaSeleccionada().toString().equals("")) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una aerolinea", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(app.lanzarPago(vista.getAerolineaSeleccionada())){
                JOptionPane.showMessageDialog(vista, "Orden de pago lanzada correctamente", "Orden de pago lanzada", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(vista, "Error al lanzar orden de pago", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.update();
        }
        
    }

    /**
     * Actualiza la vista con la lista actualizada de aerolíneas desde el modelo.
     * Este método se utiliza para refrescar la información mostrada al usuario.
     */
    public void update() {
        this.vista.setAerolineas(app.getAerolineas());
    }
}
