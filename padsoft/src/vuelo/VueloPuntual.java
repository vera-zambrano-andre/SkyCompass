package vuelo;

import java.time.LocalDate;

/**
 * Clase que representa un vuelo puntual.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class VueloPuntual extends FrecuenciaVuelo {
    
    private LocalDate fecha;
    private LocalDate siguienteFecha;
    private LocalDate fechaActual;

    /**
     * Constructor de la clase VueloPuntual.
     * 
     * @param fecha Fecha en la que se realizara el vuelo.
     */
    public VueloPuntual(LocalDate fecha) {
        this.fecha = fecha;
        this.siguienteFecha = fecha;
        this.fechaActual = fecha;
    }

    /**
     * Obtiene la fecha del vuelo puntual.
     * 
     * @return La fecha del vuelo.
     */
    public LocalDate getFecha() {
        return this.fecha;
    }

    /**
     * Devuelve la proxima fecha de operacion del vuelo.
     * 
     * @return La proxima fecha de operacion o null si
     *         se ha alcanzado la fechaFin.
     */
    public LocalDate getSiguienteFecha() {
        if (siguienteFecha != null) {
            fechaActual = siguienteFecha;
            siguienteFecha = null;
            return fechaActual;
        }

        return null;
    }

    /**
     * Establece una nueva fecha para el vuelo puntual.
     * 
     * @param fecha Nueva fecha del vuelo.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la fecha de inicio de la operacion del vuelo.
     * 
     * @return La fecha de inicio de la operacion del vuelo.
     */
    public LocalDate getFechaInicio() {
        return this.fecha;
    }

    /**
     * Obtiene la fecha de finalizacion de la operacion del vuelo.
     * 
     * @return La fecha de finalizacion de la operacion del vuelo.
     */
    public LocalDate getFechaFin() {
        return this.fecha;
    }
}
