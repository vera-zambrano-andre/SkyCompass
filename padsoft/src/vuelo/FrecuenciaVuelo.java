package vuelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa una frecuencia de vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public abstract class FrecuenciaVuelo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Devuelve la proxima fecha de operacion del vuelo
     * 
     * @return La proxima fecha de operacion o null si se ha alcanzado la fechaFin
     */
    public abstract LocalDate getSiguienteFecha();

    /**
     * Devuelve la fecha de operacion del vuelo
     * 
     * @return La fecha de operacion del vuelo
     */
    public abstract LocalDate getFecha();

    /**
     * Devuelve la fecha de inicio de la operacion del vuelo
     * 
     * @return La fecha de inicio de la operacion del vuelo
     */
    public abstract LocalDate getFechaInicio();

    /**
     * Devuelve la fecha de finalizacion de la operacion del vuelo
     * 
     * @return La fecha de finalizacion de la operacion del vuelo
     */
    public abstract LocalDate getFechaFin();
}
