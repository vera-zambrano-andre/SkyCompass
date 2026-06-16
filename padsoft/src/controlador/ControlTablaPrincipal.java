package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aeropuerto.Aerolinea;
import sistema.SkyCompass;
import usuario.Controlador;
import usuario.Operador;
import usuario.Usuario;
import vista.TablaPrincipal;
import vuelo.Vuelo;
import vuelo.VueloProgramado;

/**
 * Clase que representa el controlador de la vista de la tabla principal.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlTablaPrincipal {
    private SkyCompass app;
    private TablaPrincipal vista;

    /**
     * Constructor de la clase ControlTablaPrincipal.
     * @param app aplicacion
     * @param vista vista
     */
    public ControlTablaPrincipal(SkyCompass app, TablaPrincipal vista) {
        this.app = app;
        this.vista = vista;
    }
    /**
     * Metodo que se encarga de actualizar la tabla de vuelos programados.
     */
    public void actualizarVuelosProgramados() {
        List<VueloProgramado> vuelosAMostrar = null;

        Usuario usuario = app.getUsuarioLogeado();
        if (usuario instanceof Operador) {
            Aerolinea aerolinea = ((Operador) usuario).getAerolinea();
            vuelosAMostrar = new ArrayList<VueloProgramado>(app.getVuelosProgramados().values().stream().filter(v -> v.getAerolinea().equals(aerolinea)).collect(Collectors.toList()));
        } else if (usuario instanceof Controlador) {
            Controlador controlador = (Controlador) usuario;
            vuelosAMostrar = new ArrayList<VueloProgramado>(app.getVuelosProgramados().values().stream().filter(v -> v.getControlador() != null && v.getControlador().equals(controlador)).collect(Collectors.toList()));
        } else {
            vuelosAMostrar = new ArrayList<VueloProgramado>(app.getVuelosProgramados().values());
        }

        vista.actualizarTabla(vuelosAMostrar);
    }
}

