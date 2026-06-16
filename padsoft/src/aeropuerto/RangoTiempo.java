package aeropuerto;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Clase que representa un rango de tiempo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class RangoTiempo implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    /**
     * Constructor de la clase RangoTiempo
     * 
     * @param horaInicio inicio del rango
     * @param horaFin    fin del rango
     */
    public RangoTiempo(LocalTime horaInicio, LocalTime horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    /**
     * Obtiene la hora de inicio del rango.
     * 
     * @return Hora de inicio.
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Obtiene la hora de fin del rango.
     * 
     * @return Hora de fin.
     */

    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Comprueba si la hora dada se encuentra dentro del rango definido.
     * 
     * @param hora Hora a comprobar.
     * @return True si la hora se encuentra en el rango, false en caso contrario.
     */
    public boolean estaEnRango(LocalTime hora) {
        return (hora.isAfter(horaInicio) && hora.isBefore(horaFin));
    }

    /**
     * Representacion en cadena de un rango de tiempo.
     * 
     * @return Cadena en formato "HH:mm - HH:mm" que representa el rango de tiempo.
     */
    @Override
    public String toString() {
        return horaInicio + " - " + horaFin;
    }
}
