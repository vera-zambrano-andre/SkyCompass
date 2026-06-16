package aeropuerto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa una temporada.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Temporada implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;

    /**
     * Constructor de la clase Temporada.
     * 
     * @param fechaInicio     Fecha de inicio de la temporada.
     * @param fechaFin        Fecha de fin de la temporada.
     * @param horarioApertura Hora de apertura.
     * @param horarioCierre   Hora de cierre.
     */
    public Temporada(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horarioApertura, LocalTime horarioCierre) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    /**
     * Obtiene la fecha de inicio de la temporada.
     * 
     * @return Fecha de inicio.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio de la temporada.
     * 
     * @param fechaInicio Nueva fecha de inicio.
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha de fin de la temporada.
     * 
     * @return Fecha de fin.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin de la temporada.
     * 
     * @param fechaFin Nueva fecha de fin.
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene la hora de apertura.
     * 
     * @return Hora de apertura.
     */
    public LocalTime getHorarioApertura() {
        return horarioApertura;
    }

    /**
     * Establece la hora de apertura.
     * 
     * @param horarioApertura Nueva hora de apertura.
     */
    public void setHorarioApertura(LocalTime horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    /**
     * Obtiene la hora de cierre.
     * 
     * @return Hora de cierre.
     */
    public LocalTime getHorarioCierre() {
        return horarioCierre;
    }

    /**
     * Establece la hora de cierre.
     * 
     * @param horarioCierre Nueva hora de cierre.
     */
    public void setHorarioCierre(LocalTime horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    /**
     * Representación en cadena de la temporada.
     * 
     * @return Cadena con la información de la temporada.
     */
    @Override
    public String toString() {
        return "Temporada: (" + horarioApertura + " - " + horarioCierre + ", " + fechaInicio + " - " + fechaFin + ")";
    }
}
