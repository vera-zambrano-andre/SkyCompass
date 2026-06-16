package elementocoste;

import java.io.Serializable;
import aeropuerto.*;
import vuelo.*;

/**
 * Clase que representa una plaza.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Plaza implements IDimensionAptitud, Serializable {

    private static final long serialVersionUID = 1L;
    private double ancho;
    private double largo;
    private boolean ocupada;

    /**
     * Constructor de la clase Plaza.
     *
     * @param ancho Ancho de la plaza.
     * @param largo Largo de la plaza.
     */
    public Plaza(double ancho, double largo) {
        this.ancho = ancho;
        this.largo = largo;
        this.ocupada = false;
    }

    /**
     * Verifica si la plaza cumple con la aptitud de dimension para el vuelo dado.
     *
     * @param v Vuelo para el cual se verifica la aptitud.
     * @return true si la dimension del vuelo es compatible con la plaza; false en
     *         caso contrario.
     */
    public boolean aptitudDimension(Vuelo v) {
        VueloProgramado vp = v.getVueloProgramado();
        Dimension dim = vp.getDimensionVueloProgramado();
        if (dim.getAncho() > getAncho() || dim.getLargo() > getLargo()) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve el ancho de la plaza.
     *
     * @return Ancho de la plaza.
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * Devuelve el largo de la plaza.
     *
     * @return Largo de la plaza.
     */
    public double getLargo() {
        return largo;
    }

    /**
     * Indica si la plaza esta ocupada.
     *
     * @return true si la plaza esta ocupada; false en caso contrario.
     */
    public boolean isOcupada() {
        return this.ocupada;
    }

    /**
     * Establece el estado de ocupacion de la plaza.
     *
     * @param ocupada Nuevo estado de ocupacion.
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * Representacion en cadena de la plaza.
     *
     * @return Cadena con la informaci n de la plaza.
     */
    public String toString() {
        return "Plaza{" + "ancho=" + ancho + ", largo=" + largo + ", ocupada=" + ocupada + '}';
    }
}
