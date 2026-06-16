package factura;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa el periodo de un descuento.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class PeriodoDescuento implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    /**
     * Constructor de la clase PeriodoDescuento
     * 
     * @param fechaInicio inicio del periodo
     * @param fechaFin    fin del periodo
     */

    public PeriodoDescuento(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /**
     * Comprueba si una fecha dada esta dentro del periodo del descuento.
     * 
     * @param fecha fecha a comprobar
     * @return true si la fecha esta dentro del periodo, false en caso contrario
     */
    public boolean estaDentro(LocalDate fecha) {
        return (fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio))
                && (fecha.isBefore(fechaFin) || fecha.isEqual(fechaFin));
    }

    /**
     * Verifica si el periodo de descuento está vigente en la fecha actual.
     *
     * @return true si la fecha actual está dentro del periodo de descuento, false
     *         en caso contrario.
     */

    public boolean estaVigente() {
        return estaDentro(LocalDate.now());
    }

    /**
     * Devuelve la fecha de inicio del descuento.
     * 
     * @return la fecha de inicio del descuento.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Devuelve la fecha de fin del descuento.
     * 
     * @return la fecha de fin del descuento.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }
}
