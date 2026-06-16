package aeropuerto;

/**
 * Clase que representa un tipo de avion de mercancias.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TipoAvionMercancia extends TipoAvion {

    private boolean controlTemperatura;
    private boolean mercanciaPeligrosa;
    private double maxCarga;

    /**
     * Constructor de la clase TipoAvionMercancia.
     * 
     * @param marca              Marca del avion.
     * @param modelo             Modelo del avion.
     * @param dimension          Dimension del avion.
     * @param autonomia          Autonomia del avion.
     * @param controlTemperatura Indica si se controla la temperatura.
     * @param mercanciaPeligrosa Indica si permite mercancia peligrosa.
     * @param maxCarga           Carga maxima que puede transportar el avion.
     */
    public TipoAvionMercancia(String marca, String modelo, Dimension dimension, double autonomia,
            boolean controlTemperatura, boolean mercanciaPeligrosa, double maxCarga) {
        super(marca, modelo, dimension, autonomia);
        this.controlTemperatura = controlTemperatura;
        this.mercanciaPeligrosa = mercanciaPeligrosa;
        this.maxCarga = maxCarga;
    }

    /**
     * Devuelve la carga maxima que puede transportar el avion.
     * 
     * @return Carga maxima.
     */
    public double getMaxCarga() {
        return maxCarga;
    }

    /**
     * Devuelve la capacidad del avion.
     * 
     * @return Capacidad, que es equivalente a la carga maxima.
     */

    public double getCapacidad() {
        return getMaxCarga();
    }

    /**
     * Indica si se controla la temperatura en el avion.
     * 
     * @return true si se controla la temperatura; false en caso contrario.
     */
    public boolean controlTemperatura() {
        return controlTemperatura;
    }

    /**
     * Indica si este tipo de avion permite mercancia peligrosa.
     * 
     * @return true si permite mercancia peligrosa; false en caso contrario.
     */
    public boolean permiteMercanciasPeligrosas() {
        return mercanciaPeligrosa;
    }

    /**
     * Representacion en cadena de un tipo de avion mercancia.
     * 
     * @return Representacion en cadena del tipo de avion.
     */
    @Override
    public String toString() {
        return "Mercancías - " + getMarca() + " " + getModelo() +
                " (" + maxCarga + " kg" +
                (controlTemperatura ? ", Control de temperatura" : "") +
                (mercanciaPeligrosa ? ", Mercancías peligrosas" : "") +
                ")";
    }

}
