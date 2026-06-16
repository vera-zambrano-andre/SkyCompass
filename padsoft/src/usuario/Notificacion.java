package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * Clase que representa una notificacion.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private String notificacion;
    private LocalDate fecha;
    private LocalTime hora;

    /**
     * Constructor de la clase Notificacion.
     * 
     * @param notificacion El contenido de la notificacion.
     * @param fecha        La fecha de la notificacion.
     * @param hora         La hora de la notificacion.
     */
    public Notificacion(String notificacion, LocalDate fecha, LocalTime hora) {
        this.notificacion = notificacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    /**
     * Devuelve el contenido de la notificacion.
     * 
     * @return El contenido de la notificacion.
     */
    public String getNotificacion() {
        return this.notificacion;
    }

    /**
     * Establece el contenido de la notificacion.
     * 
     * @param notificacion El nuevo contenido de la notificacion.
     */
    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    /**
     * Devuelve la fecha de la notificacion.
     * 
     * @return La fecha de la notificacion.
     */
    public LocalDate getFecha() {
        return this.fecha;
    }

    /**
     * Establece la fecha de la notificacion.
     * 
     * @param fecha La nueva fecha de la notificacion.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve la hora de la notificacion.
     * 
     * @return La hora de la notificacion.
     */
    public LocalTime getHora() {
        return this.hora;
    }

    /**
     * Establece la hora de la notificacion.
     * 
     * @param hora La nueva hora de la notificacion.
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Devuelve una representacion en String de la notificacion.
     * 
     * @return La representacion en String de la notificacion.
     */
    public String toString() {
        return "notificacion='" + notificacion + '\'' + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
}
