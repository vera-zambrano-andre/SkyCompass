package aeropuerto;

import java.util.*;

/**
 * Clase que representa una terminal de pasajeros.
 * 
 * Esta clase extiende de Terminal y agrega informacion especifica de los
 * terminales de pasajeros, como el aforo maximo, el aforo actual y la lista
 * de puertas de embarque.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TerminalPasajero extends Terminal {

    private int aforoMax;
    private int aforoActual;
    private List<PuertaEmbarque> puertaEmbarque = new ArrayList<>();

    /**
     * Constructor de la clase TerminalPasajero.
     * 
     * @param codigo         Codigo identificador de la terminal.
     * @param maxPlazas      Numero maximo de plazas.
     * @param aforoMax       Aforo maximo permitido.
     * @param puertaEmbarque Lista de puertas de embarque asociadas.
     * @param rangoTiempo    Rango de tiempo de la terminal.
     */
    public TerminalPasajero(String codigo, int maxPlazas, int aforoMax, List<PuertaEmbarque> puertaEmbarque,
            RangoTiempo rangoTiempo) {
        super(codigo, maxPlazas, rangoTiempo);
        this.aforoMax = aforoMax;
        this.aforoActual = 0;
        this.puertaEmbarque = puertaEmbarque;
    }

    /**
     * Devuelve el aforo maximo de la terminal.
     * 
     * @return Aforo maximo.
     */
    public int getAforoMax() {
        return aforoMax;
    }

    /**
     * Devuelve el aforo actual de la terminal.
     * 
     * @return Aforo actual.
     */
    public int getAforoActual() {
        return aforoActual;
    }

    /**
     * Devuelve la lista de puertas de embarque de la terminal.
     * 
     * @return Lista de puertas de embarque.
     */
    public List<PuertaEmbarque> getPuertaEmbarque() {
        return puertaEmbarque;
    }
}
