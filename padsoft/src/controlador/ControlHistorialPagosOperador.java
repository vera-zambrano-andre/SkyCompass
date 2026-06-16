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
 * Clase que representa el controlador de la historial de pagos del operador.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlHistorialPagosOperador{
    private VentanaPrincipal frame;
    private SkyCompass app;
    private HistorialPagos vista;

    /**
     * Constructor de la clase ControlHistorialPagosOperador.
     * Inicializa el controlador con las referencias necesarias a la ventana principal,
     * el sistema central y la vista del historial de pagos.
     *
     * @param frame Ventana principal de la aplicación.
     * @param app Instancia del sistema principal SkyCompass.
     * @param vista Vista gráfica del historial de pagos del operador.
     */
    public ControlHistorialPagosOperador(VentanaPrincipal frame, SkyCompass app, HistorialPagos vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Actualiza el historial de pagos del operador cargando solo las facturas
     * asociadas a la aerolínea del operador actualmente autenticado.
     * No realiza ninguna acción si la lista de facturas es nula.
     */
    public void actualizarPagosOperador() {

        if(app.getFacturas() == null) return;

        List<Factura> facturas = app.getFacturas().stream().filter(f -> f.getAerolinea().equals(((Operador) app.getUsuarioLogeado()).getAerolinea())).collect(Collectors.toList());

        this.vista.cargarFacturas(facturas);
    }
    
}
