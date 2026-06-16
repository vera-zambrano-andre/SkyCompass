package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

import sistema.SkyCompass;
import usuario.Operador;
import vista.HistorialPagos;
import vista.VentanaPrincipal;
import factura.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa el controlador del historial de los pagos del gestor.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlHistorialPagosGestor{
    private VentanaPrincipal frame;
    private SkyCompass app;
    private HistorialPagos vista;

    /**
     * Constructor de la clase ControlHistorialPagosGestor.
     * Inicializa el controlador con las referencias necesarias a la interfaz gráfica
     * y al sistema central.
     *
     * @param frame Ventana principal de la aplicación.
     * @param app Instancia del sistema principal SkyCompass.
     * @param vista Vista gráfica del historial de pagos.
     */
    public ControlHistorialPagosGestor(VentanaPrincipal frame, SkyCompass app, HistorialPagos vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Carga y actualiza el historial de pagos del gestor obteniendo las facturas
     * desde el sistema (modelo) y pasándolas a la vista.
     * No realiza ninguna acción si la lista de facturas es nula.
     */
    public void actualizarPagosGestor() {

        if(app.getFacturas() == null) return;

        List<Factura> facturas = app.getFacturas();

        this.vista.cargarFacturas(facturas);
    }
    
}
