package aeropuerto;

import vuelo.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un avion.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Avion implements Serializable {

    private static final long serialVersionUID = 1L;
    private int anoCompra;
    private LocalDate fechaUltimaRevision;
    private TipoAvion tipo;
    private Localizacion localizacion;

    /**
     * Constructor de la clase Avion.
     * 
     * @param anoCompra     A o de compra del avion.
     * @param fechaRevision Fecha de la ultima revision del avion.
     * @param tipo          Tipo de avion.
     */

    public Avion(int anoCompra, LocalDate fechaRevision, TipoAvion tipo) {
        this.anoCompra = anoCompra;
        this.fechaUltimaRevision = fechaRevision;
        this.tipo = tipo;
    }

    /**
     * Obtiene la dimensión del avión.
     * 
     * @return La dimensión del avión.
     */
    public Dimension getDimensionAvion() {
        return this.tipo.getDimension();
    }

    /**
     * Obtiene el año de compra del avión.
     * 
     * @return El año en el que el avión fue comprado.
     */

    public int getAnoCompra() {
        return this.anoCompra;
    }

    /**
     * Obtiene la fecha de la última revisión del avión.
     * 
     * @return La fecha de la última revisión.
     */

    public LocalDate getFechaRevision() {
        return this.fechaUltimaRevision;
    }

    /**
     * Obtiene el tipo de avion.
     * 
     * @return El tipo de avion.
     */
    public TipoAvion getTipo() {
        return this.tipo;
    }

    /**
     * Obtiene la localización actual del avión.
     * 
     * @return La localización actual del avión.
     */
    public Localizacion getLocalizacion() {
        System.out.println(this.localizacion.getLocalizacion());
        return this.localizacion;

    }

    /**
     * Establece la localización actual del avión.
     * 
     * @param localizacion La nueva localización a asignar al avión.
     */

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Comprueba si el avion se encuentra en un hangar (localizacion hangar).
     * 
     * @return true si el avion se encuentra en un hangar, false en caso contrario.
     */
    public boolean estaEnHangar() {

        if (this.localizacion == null) {
            throw new IllegalStateException("No se puede determinar puerta: localizacion es null");
        }

        if (localizacion.getLocalizacion().equals("hangar")) {
            return true;
        }
        return false;
    }

    /**
     * Comprueba si el avion se encuentra en un finger (pasarela de embarque).
     * 
     * @return true si el avion se encuentra en un finger, false en caso contrario.
     */

    public boolean estaEnFinger() {

        if (this.localizacion == null) {
            throw new IllegalStateException("No se puede determinar puerta: localizacion es null");
        }

        if (localizacion.getLocalizacion().equals("finger")) {
            return true;
        }
        return false;
    }

    /**
     * Comprueba si el avion se encuentra en una zona de aparcamiento.
     * 
     * @return true si el avion se encuentra en una zona de aparcamiento, false en
     *         caso contrario.
     */
    public boolean estaEnZonaAparcamiento() {

        if (this.localizacion == null) {
            throw new IllegalStateException("No se puede determinar puerta: localizacion es null");
        }

        if (localizacion.getLocalizacion().equals("aparcamiento")) {
            return true;
        }
        return false;
    }

    /**
     * Comprueba si el avion se encuentra en una puerta de embarque.
     * 
     * @return true si el avion se encuentra en una puerta de embarque, false en
     *         caso contrario.
     */
    public boolean estaEnPuertaEmbarque() {

        if (this.localizacion == null) {
            throw new IllegalStateException("No se puede determinar puerta: localizacion es null");
        }

        if (localizacion.getLocalizacion().equals("puerta")) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the object.
     * 
     * @return a string representation of the object
     */
    public String toString() {
        return "Avion " + this.tipo.toString();
    }
}
