package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aeropuerto.Aerolinea;
import sistema.SkyCompass;
import usuario.Controlador;
import usuario.Operador;
import usuario.Usuario;
import vista.TablaPrincipal;
import vista.TablaVuelosHoy;
import vuelo.Vuelo;
import vuelo.VueloProgramado;


/**
 * Clase que representa el controlador de la vista de la tabla de vuelos de hoy.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlTablaVuelosHoy {
    private SkyCompass app;
    private TablaVuelosHoy vista;

    /**
     * Constructor de la clase ControlTablaVuelosHoy.
     * @param app aplicacion
     * @param vista vista
     */
    public ControlTablaVuelosHoy(SkyCompass app, TablaVuelosHoy vista) {
        this.app = app;
        this.vista = vista;
    }

    /**
     * Metodo que se encarga de actualizar la tabla de vuelos de hoy.
     */
    public void actualizarVuelosHoy() {
        LocalDate hoy = LocalDate.now();

        List<Vuelo> vuelosAMostrar = new ArrayList<>();

        for(VueloProgramado vp : app.getVuelosProgramados().values()) {
            for(Vuelo vuelo : vp.getVuelos()) {
                if(vuelo.getFecha().equals(hoy)) {
                    vuelosAMostrar.add(vuelo);
                }
            }
        }

        vista.actualizarTabla(vuelosAMostrar);
    }
}


