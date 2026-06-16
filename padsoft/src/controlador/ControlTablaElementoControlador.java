package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sistema.SkyCompass;
import vista.TablaElemento;
import vista.VentanaPrincipal;

/**
 * Clase que representa el controlador de la vista de la tabla de elementos del controlador.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlTablaElementoControlador implements ActionListener {
    private VentanaPrincipal frame;
	private SkyCompass app;
	private TablaElemento vista;

    /**
     * Constructor de la clase ControlTablaElementoControlador.
     * @param frame ventana principal
     * @param app aplicacion
     * @param vista vista de la tabla
     */
    public ControlTablaElementoControlador(VentanaPrincipal frame, SkyCompass app, TablaElemento vista) {
        this.frame = frame;
        this.vista = frame.getTablaElementoControlador(); 
        this.app = app;
    }

    /**
     * Método que se encarga de gestionar los eventos de la vista de la tabla de elementos.
     * @param e evento al pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(vista.getNext());
            vista.limpiar();
        }
        
        String seleccionado = (String) vista.getSelector().getSelectedItem();

        switch (seleccionado) {
            case "Aeropuerto":
                vista.mostrarTabla("Aeropuerto", vista.crearTablaAeropuertos(app.getAeropuertos()));
                break;
            case "Pista":
                vista.mostrarTabla("Pista", vista.crearTablaPistas(app.getPistasDespegues(), app.getPistasAterrizajes()));
                break;
            case "Terminal":
                vista.mostrarTabla("Terminal", vista.crearTablaTerminales(app.getTerminalesMercancias(), app.getTerminalesPasajeros()));
                break;
            case "Puerta de embarque":
                vista.mostrarTabla("Puerta de embarque", vista.crearTablaPuertas(app.getPuertasEmbarque()));
                break;
            case "Aparcamiento":
                vista.mostrarTabla("Aparcamiento", vista.crearTablaAparcamientos(app.getZonasAparcamiento()));
                break;
            case "Plaza":
                vista.mostrarTabla("Plaza", vista.crearTablaPlazas(app.getPlazas()));
                break;
            case "Finger":
                vista.mostrarTabla("Finger", vista.crearTablaFingers(app.getFingers()));
                break;
            case "Hangar":
                vista.mostrarTabla("Hangar", vista.crearTablaHangares(app.getListaHangarMercancia(), app.getListaHangarPasajero()));
                break;
            case "Aerolínea":
                vista.mostrarTabla("Aerolínea", vista.crearTablaAerolineas(app.getAerolineas()));
                break;
        }
        
    }
}

