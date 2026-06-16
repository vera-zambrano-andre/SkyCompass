package aeropuerto;

/**
 * Clase que representa un tipo de avion de pasajeros.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TipoAvionPasajero extends TipoAvion {

    private int maxPasajeros;

    /**
     * Constructor de la clase TipoAvionPasajero.
     * 
     * @param marca        Marca del avion.
     * @param modelo       Modelo del avion.
     * @param dimension    Dimension del avion.
     * @param autonomia    Autonomia del avion.
     * @param maxPasajeros Numero maximo de pasajeros.
     */
    public TipoAvionPasajero(String marca, String modelo, Dimension dimension, double autonomia, int maxPasajeros) {
        super(marca, modelo, dimension, autonomia);
        this.maxPasajeros = maxPasajeros;
    }

    /**
     * Indica si este tipo de avion permite mercancias peligrosas.
     * 
     * @return false, ya que no permite mercancias peligrosas.
     */
    public boolean permiteMercanciasPeligrosas() {
        return false;
    }

    /**
     * Metodo que indica si el avion es de pasajeros.
     * 
     * @return true, ya que este tipo de avion es de pasajeros.
     */

    public boolean isPassenger() {
        return true;
    }

    /**
     * Devuelve la capacidad del avion en numero de pasajeros.
     * 
     * @return Capacidad del avion en numero de pasajeros.
     */
    public double getCapacidad() {
        return getMaxPasajeros();
    }

    /**
     * Devuelve el numero maximo de pasajeros que puede transportar el avion.
     * 
     * @return Numero maximo de pasajeros.
     */
    public int getMaxPasajeros() {
        return maxPasajeros;
    }

    /**
     * Representacion en forma de string del tipo de avion de pasajeros.
     * 
     * @return Cadena que representa el tipo de avion de pasajeros.
     */
    @Override
    public String toString() {
        return "Pasajeros - " + getMarca() + " " + getModelo() + " (" + maxPasajeros + " pax)";
    }
}
