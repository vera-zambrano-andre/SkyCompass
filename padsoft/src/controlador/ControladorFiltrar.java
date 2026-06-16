package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JOptionPane;

import sistema.SkyCompass;
import vuelo.Vuelo;
import vista.Filtrar;
import vista.TablaVuelosParaFiltrar;
import vista.VentanaPrincipal;

/**
 * Clase que representa el controlador de la vista de filtrado.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class ControladorFiltrar implements ActionListener {
    private final VentanaPrincipal frame;
    private final Filtrar filtrarPanel;
    private final TablaVuelosParaFiltrar tablaVuelos;
    private SkyCompass app;
    private String tabla;

    /**
     * Constructor de la clase ControladorFiltrar.
     *
     * @param frame Ventana principal de la aplicación.
     * @param filtrarPanel Panel de filtrado de vuelos.
     * @param tablaVuelos Vista de la tabla de vuelos filtrados.
     * @param app Instancia del sistema principal SkyCompass.
     * @param tabla Identificador de la tabla actual.
     */
    public ControladorFiltrar(VentanaPrincipal frame, Filtrar filtrarPanel, TablaVuelosParaFiltrar tablaVuelos, SkyCompass app, String tabla) {
        this.frame = frame;
        this.filtrarPanel = filtrarPanel;
        this.tablaVuelos = tablaVuelos;
        this.app = app;
        this.tabla = tabla;
    }

    
    /**
     * Metodo que se encarga de gestionar los eventos de los botones del panel de filtrado.
     * 
     * @param e evento de pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Aplicar filtros":
                aplicarFiltros();
                break;
            case "Eliminar filtros":
                eliminarFiltros();
                break;
            case "← Volver":
                frame.changeVisiblePanel(tabla);
                break;
        }
    }

    /**
     * Devuelve una lista de vuelos que coinciden con los filtros del panel de filtrado.
     * Si un campo no tiene valor, se ignora el filtro para ese campo.
     * 
     * @return una lista de vuelos que coinciden con los filtros
     */
    private List<Vuelo> vuelosFiltrar() {
        List<Vuelo> vuelos = app.getVuelos();
        List<Vuelo> vuelosFiltrados = new ArrayList<>();
        Map<String, String> filtros = filtrarPanel.getFiltros();

        String idFiltro        = filtros.getOrDefault("ID", "");
        String destinoFiltro   = filtros.getOrDefault("DESTINO", "");
        String origenFiltro    = filtros.getOrDefault("ORIGEN", "");
        String aerolineaFiltro = filtros.getOrDefault("AEROLÍNEA", "");
        String terminalFiltro  = filtros.getOrDefault("TERMINAL", "");
        String modeloFiltro    = filtros.getOrDefault("MODELO", "");
        String fechaSalFiltro  = filtros.getOrDefault("FECHA_SAL", "");
        String fechaLlegFiltro = filtros.getOrDefault("FECHA_LLEG", "");
        String tipoFiltro      = filtros.getOrDefault("TIPO", "");

        for (Vuelo v : vuelos) {
            boolean coincide = true;
            if (!idFiltro.isEmpty() && !v.getVueloProgramado().getCodigo().equals(idFiltro)) {
                coincide = false;
            }
            if (!destinoFiltro.isEmpty() && !v.getVueloProgramado().getDestino().getNombre().equals(destinoFiltro)) {
                coincide = false;
            }
            if (!origenFiltro.isEmpty() && !v.getVueloProgramado().getOrigen().getNombre().equals(origenFiltro)) {
                coincide = false;
            }
            if (!aerolineaFiltro.isEmpty() && !v.getVueloProgramado().getAerolinea().getNombre().equals(aerolineaFiltro)) {
                coincide = false;
            }
            if (!terminalFiltro.isEmpty() && (v.getTerminal() == null || !v.getTerminal().getCodigo().equals(terminalFiltro))) {
                coincide = false;
            }
            if (!modeloFiltro.isEmpty() && !v.getVueloProgramado().getAvion().getTipo().getModelo().equals(modeloFiltro)) {
                coincide = false;
            }
            if (!fechaSalFiltro.isEmpty() && !v.getFecha().toString().equals(fechaSalFiltro)) {
                coincide = false;
            }
            if (!fechaLlegFiltro.isEmpty() && !v.getVueloProgramado().getFrecuencia().getFechaFin().toString().equals(fechaLlegFiltro)) {
                coincide = false;
            }
            if (!tipoFiltro.isEmpty() && !v.getVueloProgramado().getTipo().equals(tipoFiltro)) {
                coincide = false;
            }
            if (coincide) {
                vuelosFiltrados.add(v);
            }
        }
        return vuelosFiltrados;
    }

    /**
     * Aplica los filtros configurados y muestra la lista de vuelos que
     * coinciden en la pantalla principal.
     */
    private void aplicarFiltros() {
        List<Vuelo> vuelosFiltrados = vuelosFiltrar();
        tablaVuelos.actualizarDatos(vuelosFiltrados);
        tablaVuelos.getTable();
        JOptionPane.showMessageDialog(null, "Filtros aplicados ");
        filtrarPanel.limpiarCampos();
        frame.changeVisiblePanel(tabla);
    }

    /**
     * Elimina los filtros configurados y muestra la lista completa de
     * vuelos en la pantalla principal.
     */
    private void eliminarFiltros() {
        
        filtrarPanel.eliminarFiltrosInternos();
        
        List<Vuelo> todosVuelos = app.getVuelos();
        
        tablaVuelos.actualizarDatos(todosVuelos);
        JOptionPane.showMessageDialog(null, "Filtros desaplicados ");

    }
}
