package aeropuerto;

import java.io.Serializable;
import java.util.*;
import vuelo.*;

/**
 * Clase que representa una aerolinea.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Aerolinea implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private List<VueloProgramado> vuelosProgramados = new ArrayList<>();
    private List<VueloCompartido> vuelosCompartidos = new ArrayList<>();
    private List<VueloCompartido> vuelosSolicitudes = new ArrayList<>();

    /**
     * Constructor de Aerolinea.
     * 
     * @param nombre El nombre de la aerolinea.
     * @param id     El id de la aerolinea.
     */
    public Aerolinea(String nombre, String id) {
        this.id = id;
        this.nombre = nombre;
        this.vuelosProgramados = new ArrayList<>();
    }

    /**
     * Devuelve el id de la aerolinea.
     *
     * @return El id de la aerolinea.
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve la lista de vuelos compartidos de esta aerolinea.
     *
     * @return La lista de vuelos compartidos de esta aerolinea.
     */
    public List<VueloCompartido> getVuelosCompartidos() {
        return vuelosCompartidos;
    }

    /**
     * Agrega un vuelo compartido a la lista de esta aerolinea.
     * 
     * @param vuelo El vuelo compartido a agregar.
     */
    public void agregarVueloCompartido(VueloCompartido vuelo) {
        vuelosCompartidos.add(vuelo);
    }

    /**
     * Agrega un vuelo programado a la lista de vuelos programados de esta
     * aerolinea.
     * 
     * @param vuelo El vuelo programado a agregar.
     */

    public void agregarVueloProgramado(VueloProgramado vuelo) {
        vuelosProgramados.add(vuelo);
    }

    /**
     * Devuelve la lista de vuelos programados de esta aerolinea.
     * 
     * @return La lista de vuelos programados de esta aerolinea.
     */
    public List<VueloProgramado> getVuelosProgramados() {
        return vuelosProgramados;
    }

    /**
     * Devuelve el nombre de la aerolinea.
     * 
     * @return El nombre de la aerolinea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega un vuelo compartido a la lista de esta aerolinea.
     * 
     * @param vueloCompartido El vuelo compartido a agregar.
     */
    public void addVueloCompartido(VueloCompartido vueloCompartido) {
        this.vuelosCompartidos.add(vueloCompartido);
    }

    /**
     * Agrega un vuelo compartido a la lista de solicitudes de esta aerolinea.
     * 
     * @param vueloCompartido El vuelo compartido a agregar.
     */
    public void addSolicitud(VueloCompartido vueloCompartido) {
        this.vuelosSolicitudes.add(vueloCompartido);
    }

    /**
     * Quita un vuelo compartido de la lista de solicitudes de esta aerolinea.
     * 
     * @param vueloCompartido El vuelo compartido a eliminar.
     */
    public void removeSolicitud(VueloCompartido vueloCompartido) {
        this.vuelosSolicitudes.remove(vueloCompartido);
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     * 
     * Dos objetos Aerolinea son iguales si tienen el mismo id.
     * 
     * @param o El objeto a comparar.
     * @return true si el objeto es igual, false en caso contrario.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Aerolinea))
            return false;
        Aerolinea aerolinea = (Aerolinea) o;
        return this.id.equals(aerolinea.getId());
    }

    /**
     * Devuelve el hashCode de este objeto.
     * 
     * El hashCode se determina a partir del id de la aerolinea.
     * 
     * @return el hashCode de este objeto.
     */
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * Devuelve una representacion en cadena de esta aerolinea. La
     * representacion incluye el nombre y el id de la aerolinea.
     * 
     * @return una representacion en cadena de esta aerolinea.
     */
    @Override
    public String toString() {
        return nombre + "(" + id + ")";
    }
}
