package aeropuerto;

import vuelo.*;

/**
 * Clase que representa una pista.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public abstract class Pista extends Localizacion implements IDimensionAptitud {
    private Avion avionEnPista;
    private double longitud;

    /**
     * Constructor de la clase Pista.
     * 
     * @param longitud Longitud de la pista.
     *                 Inicializa la longitud de la pista y establece que no hay
     *                 avion en la pista.
     */

    public Pista(double longitud) {
        this.longitud = longitud;
        avionEnPista = null;
    }

    /**
     * Verifica si la pista cumple con la aptitud de dimension para el vuelo dado.
     * 
     * @param v Vuelo para el cual se verifica la aptitud.
     * @return true si la dimension del vuelo es compatible con la pista; false en
     *         caso contrario.
     */
    public boolean aptitudDimension(Vuelo v) {
        VueloProgramado vp = v.getVueloProgramado();
        Dimension dim = vp.getDimensionVueloProgramado();
        if (dim.getLargo() > getLongitud()) {
            return false;
        }

        return true;
    }

    /**
     * Devuelve la localizacion actual de la pista.
     * 
     * @return "pista"
     */
    public String getLocalizacion() {
        return "pista";
    }

    /**
     * Devuelve la longitud de la pista.
     * 
     * @return longitud de la pista
     */
    public double getLongitud() {
        return this.longitud;
    }

    /**
     * Establece una nueva longitud para la pista.
     * 
     * @param longitud Nueva longitud de la pista.
     */

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Devuelve el avion que se encuentra en la pista.
     * 
     * @return El avion en la pista o null si no hay ninguno.
     */
    public Avion getAvionEnPista() {
        return this.avionEnPista;
    }

    /**
     * Establece el avion que se encuentra en la pista.
     * 
     * @param a El avion que se colocará en la pista.
     */

    public void setAvionEnPista(Avion a) {
        this.avionEnPista = a;
    }
}
