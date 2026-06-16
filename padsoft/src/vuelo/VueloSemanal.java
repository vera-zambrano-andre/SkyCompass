package vuelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase que representa un vuelo semanal.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class VueloSemanal extends FrecuenciaVuelo {

    private List<DayOfWeek> diasSemana;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate proximaFecha;
    private LocalDate fechaActual;

    /**
     * Constructor que inicializa un vuelo con frecuencia semanal.
     * 
     * @param diasSemana  Lista de dias de la semana en los que opera el vuelo
     * @param fechaInicio Fecha de inicio de la operacion del vuelo
     * @param fechaFin    Fecha de finalizacion de la operacion del vuelo
     */
    public VueloSemanal(List<DayOfWeek> diasSemana, LocalDate fechaInicio, LocalDate fechaFin) {
        this.diasSemana = diasSemana;
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
        return this.fechaInicio;
    }

    /**
     * Devuelve la fecha de finalizacion de la operacion del vuelo.
     * 
     * @return La fecha de finalizacion de la operacion del vuelo.
     */

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }

    /**
     * Devuelve la siguiente fecha de operación del vuelo semanal.
     * Recorre las fechas a partir de la fecha actual y devuelve la
     * primera que coincide con uno de los días de operación
     * especificados. Si se ha alcanzado la fecha de fin sin encontrar
     * una fecha válida, devuelve null.
     * 
     * @return La próxima fecha de operación válida o null si se ha
     *         alcanzado la fecha de fin.
     */

    public LocalDate getSiguienteFecha() {

        while (proximaFecha != null && !proximaFecha.isAfter(fechaFin)) {
            if (diasSemana.contains(proximaFecha.getDayOfWeek())) {
                fechaActual = proximaFecha;
                proximaFecha = proximaFecha.plusDays(1);
                return fechaActual;
            }
            proximaFecha = proximaFecha.plusDays(1);
        }
        return null;
    }

    /**
     * Obtiene la lista de dias de la semana en los que opera el vuelo.
     * 
     * @return Lista de dias de la semana
     */
    public List<DayOfWeek> getDiasSemana() {
        return this.diasSemana;
    }

    /**
     * Establece los dias de la semana en los que opera el vuelo.
     * 
     * @param diasSemana Lista de dias de la semana
     */
    public void setDiasSemana(List<DayOfWeek> diasSemana) {
        this.diasSemana = diasSemana;
    }

    /**
     * Devuelve la fecha del vuelo.
     * 
     * @return La fecha actual del vuelo.
     */
    public LocalDate getFecha() {
        return fechaActual;
    }
}
