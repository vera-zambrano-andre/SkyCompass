package aeropuerto;

import vuelo.*;

/**
 * Interfaz que comprueba si una dimension es apta para un vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public interface IDimensionAptitud {

    /**
     * Comprueba si la dimension es apta para el vuelo.
     * 
     * @param v Vuelo a comprobar.
     * @return true si la dimension es compatible con el vuelo; false en caso
     *         contrario.
     */
    public boolean aptitudDimension(Vuelo v);
}