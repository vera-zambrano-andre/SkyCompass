package aeropuerto;

import java.util.*;
import elementocoste.*;

/**
 * Clase que representa una terminal de mercancias.
 * 
 * Esta clase extiende de Terminal y agrega informacion especifica para
 * terminales de mercancias,
 * asignando una zona de aparcamiento.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TerminalMercancia extends Terminal {

    private List<ZonaAparcamiento> aparcamiento;

    /**
     * Constructor de la clase TerminalMercancia.
     * 
     * @param codigo       Codigo identificador de la terminal.
     * @param maxPlazas    Numero maximo de plazas de la terminal.
     * @param aparcamiento Zona de aparcamiento asociada a la terminal.
     * @param rangoTiempo  Horario de disponibilidad de la terminal.
     */
    public TerminalMercancia(String codigo, int maxPlazas, List<ZonaAparcamiento> aparcamiento,
            RangoTiempo rangoTiempo) {
        super(codigo, maxPlazas, rangoTiempo);
        this.aparcamiento = aparcamiento;
    }

    /**
     * Devuelve la zona de aparcamiento asignada a la terminal.
     * 
     * @return ZonaAparcamiento asignada.
     */
    public List<ZonaAparcamiento> getAparcamientos() {
        return this.aparcamiento;
    }

    /**
     * Agrega una zona de aparcamiento a la lista de zonas de aparcamiento de la
     * terminal. Si la zona ya existe en la lista no se hace nada.
     * 
     * @param z La nueva zona de aparcamiento a agregar.
     */
    public void addAparcamiento(ZonaAparcamiento z) {
        if (z != null && !aparcamiento.contains(z)) {
            aparcamiento.add(z);
        }
    }

    /**
     * Establece una nueva zona de aparcamiento para la terminal.
     * 
     * @param aparcamiento Nueva zona de aparcamiento.
     */
    public void setAparcamiento(List<ZonaAparcamiento> aparcamiento) {
        this.aparcamiento = aparcamiento;
    }
}
