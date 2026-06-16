package aeropuerto;

import java.io.Serializable;

/**
 * Clase que representa un tipo de avion.
 * 
 * Esta clase abstracta define las propiedades basicas de un avion, tales como
 * la marca,
 * el modelo, la dimension y la autonomia. Ademas, se define el metodo abstracto
 * que indica
 * si el avion permite mercancias peligrosas.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public abstract class TipoAvion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String marca;
    private String modelo;
    private Dimension dimension;
    private double autonomia;

    /**
     * Constructor de la clase TipoAvion.
     * 
     * @param marca     Marca del avion.
     * @param modelo    Modelo del avion.
     * @param dimension Dimension del avion.
     * @param autonomia Autonomia del avion.
     */
    public TipoAvion(String marca, String modelo, Dimension dimension, double autonomia) {
        this.marca = marca;
        this.modelo = modelo;
        this.dimension = dimension;
        this.autonomia = autonomia;
    }

    /**
     * Metodo abstracto que indica si el avion permite mercancias peligrosas.
     * 
     * @return true si permite mercancias peligrosas; false en caso contrario.
     */
    public abstract boolean permiteMercanciasPeligrosas();

    /**
     * Devuelve la marca del avion.
     * 
     * @return Marca del avion.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo que indica si el avion es de pasajeros.
     * 
     * @return true si es un avion de pasajeros; false en caso contrario.
     */
    public boolean isPassenger() {
        return false;
    }

    /**
     * Devuelve la capacidad del avion.
     * 
     * @return Capacidad del avion.
     */
    public abstract double getCapacidad();

    /**
     * Devuelve el modelo del avion.
     * 
     * @return Modelo del avion.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Devuelve la dimension del avion.
     * 
     * @return Dimension del avion.
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Devuelve la autonomia del avion.
     * 
     * @return Autonomia del avion.
     */
    public double getAutonomia() {
        return autonomia;
    }

    /**
     * Devuelve una representación en cadena del tipo de avión que incluye
     * su marca y modelo.
     * 
     * @return Una cadena que representa la marca y el modelo del avión.
     */

    public String toString() {
        return this.marca + " " + this.modelo;
    }
}
