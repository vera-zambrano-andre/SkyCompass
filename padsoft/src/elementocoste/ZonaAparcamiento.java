package elementocoste;

import java.util.*;

/**
 * Clase que representa una zona de aparcamiento.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ZonaAparcamiento extends ElementoCoste {

    private String codigo;
    private int maxCapacidad;
    private int capacidadActual;
    private List<Plaza> plazas = new ArrayList<>();

    /**
     * Constructor de la clase ZonaAparcamiento.
     * 
     * @param codigo       Codigo identificador de la zona.
     * @param maxCapacidad Capacidad maxima de la zona.
     * @param plazas       Lista de plazas de la zona.
     * @param costeHora    Coste por hora del uso de la zona.
     */
    public ZonaAparcamiento(String codigo, int maxCapacidad, List<Plaza> plazas, double costeHora) {
        super(costeHora);
        this.codigo = codigo;
        this.maxCapacidad = maxCapacidad;
        this.plazas = plazas;
        this.capacidadActual = 0;
    }

    /**
     * Devuelve el codigo de la zona de aparcamiento.
     * 
     * @return Codigo de la zona.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Devuelve la capacidad maxima de la zona.
     * 
     * @return Capacidad maxima.
     */
    public int getMaxCapacidad() {
        return this.maxCapacidad;
    }

    /**
     * Establece el codigo de la zona.
     * 
     * @param codigo Nuevo codigo de la zona.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Agrega una plaza a la lista de plazas.
     * 
     * @param plaza La plaza a agregar.
     */
    public void addPlaza(Plaza plaza) {
        this.plazas.add(plaza);
    }

    /**
     * Devuelve la lista de plazas de la zona.
     * 
     * @return Lista de plazas.
     */
    public List<Plaza> getPlazas() {
        return this.plazas;
    }

    /**
     * Establece la lista de plazas de la zona.
     * 
     * @param plazas Nueva lista de plazas.
     */
    public void setPlazas(List<Plaza> plazas) {
        this.plazas = plazas;
    }

    /**
     * Devuelve la localizacion del elemento.
     * 
     * @return "aparcamiento"
     */
    public String getLocalizacion() {
        return "aparcamiento";
    }

    /**
     * Devuelve la capacidad actual de la zona.
     * 
     * @return Capacidad actual.
     */
    public int getCapacidadActual() {
        return this.capacidadActual;
    }

    /**
     * Establece la capacidad actual de la zona.
     * 
     * @param capacidadActual Nueva capacidad actual.
     */
    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }
}
