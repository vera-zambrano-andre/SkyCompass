package vuelo;

import java.time.LocalDate;

/**
 * Clase que representa un vuelo diario.
 * Extiende de FrecuenciaVuelo, lo que indica que este vuelo opera con una frecuencia diaria. 
 *
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class VueloDiario extends FrecuenciaVuelo {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate proximaFecha;
    private LocalDate fechaActual;

    /**
     * Constructor de la clase VueloDiario.
     * 
     * @param fechaInicio La fecha de inicio de la operacion del vuelo.
     * @param fechaFin La fecha de finalizacion de la operacion del vuelo.
     */
    public VueloDiario(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.proximaFecha = fechaInicio;
        this.fechaActual = fechaInicio;
    }
    
    /**
     * Devuelve la fecha de inicio de la operacion del vuelo.
     * 
     * @return La fecha de inicio de la operacion del vuelo.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Devuelve la fecha de finalizacion de la operacion del vuelo.
     * 
     * @return La fecha de finalizacion de la operacion del vuelo.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Devuelve la proxima fecha de operacion del vuelo. Si
     * se ha alcanzado la fechaFin, devuelve null.
     * 
     * @return La proxima fecha de operacion o null si
     *         se ha alcanzado la fechaFin.
     */
    public LocalDate getSiguienteFecha() {
        if (proximaFecha == null || proximaFecha.isAfter(fechaFin)) {
            return null; 
        }

        fechaActual = proximaFecha;
        proximaFecha = proximaFecha.plusDays(1);
        return fechaActual;
    }

    /**
     * Devuelve la fecha.
     * 
     * @return La fecha del vuelo.
     */
    public LocalDate getFecha() {
        return fechaActual;
    }

}
