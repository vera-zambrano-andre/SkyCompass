package factura;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase que representa un Descuento.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Descuento implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String descripcion;
    private PeriodoDescuento periodo;
    private final double porcentaje;
    private final CondicionDescuento condicion;
    private final boolean aplicableTotal;

    /**
     * Constructor de la clase Descuento.
     * 
     * @param descripcion    descripcion del descuento
     * @param fechaInicio    inicio del descuento
     * @param fechaFin       fin del descuento
     * @param porcentaje     porcentaje del descuento
     * @param condicion      condicion del descuento
     * @param aplicableTotal true si el descuento se aplica a la totalidad de la
     *                       factura, false en caso contrario
     */

    public Descuento(String descripcion, LocalDate fechaInicio, LocalDate fechaFin, double porcentaje,
            CondicionDescuento condicion, boolean aplicableTotal) {
        this.descripcion = descripcion;
        this.periodo = new PeriodoDescuento(fechaInicio, fechaFin);

        if (porcentaje < 1 || porcentaje > 50) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 1 y 50");
        }

        this.porcentaje = porcentaje;
        this.condicion = condicion;
        this.aplicableTotal = aplicableTotal;
    }

    /**
     * Calcula el descuento total que se aplica a la factura en base a la condicion
     * y el porcentaje del descuento.
     *
     * @param factura la factura a la que se aplica el descuento.
     * @return el descuento total.
     */
    public double calcularDescuentoTotal(Factura factura) {
        if (periodo == null) {
            return 0;
        }

        if (periodo.estaVigente() && condicion.cumpleCondicion(factura) && aplicableTotal) {
            System.out.println("Aplicable total: " + factura.getPrecioTotalSinDecuento() * (porcentaje / 100));
            return factura.getPrecioTotalSinDecuento() * (porcentaje / 100);
        }
        return 0;
    }

    /**
     * Calcula el descuento que se aplica a los recursos de la factura en base a la
     * condicion y el porcentaje del descuento.
     *
     * @param factura la factura a la que se aplica el descuento.
     * @return el descuento a los recursos.
     */
    public double calcularDescuentoRecursos(Factura factura) {
        if (periodo == null) {
            return 0;
        }

        if (periodo.estaVigente() && condicion.cumpleCondicion(factura) && !aplicableTotal) {

            System.out.println("Aplicable recursos: " + factura.getPrecioRecursosSinDescuento() * (porcentaje / 100));
            return factura.getPrecioRecursosSinDescuento() * (porcentaje / 100);

        }
        return 0;
    }

    /**
     * Establece un nuevo periodo de descuento para el objeto Descuento.
     * 
     * @param periodo el nuevo periodo de descuento a establecer.
     * @throws IllegalStateException si el periodo actual está vigente y no se puede
     *                               modificar.
     */

    public void setNuevoPeriodo(PeriodoDescuento periodo) {
        if (this.periodo != null && this.periodo.estaVigente()) {
            throw new IllegalStateException("No se puede modificar el periodo mientras está vigente.");
        }
        this.periodo = periodo;
    }

    /**
     * Elimina el periodo de descuento del objeto Descuento.
     *
     * @throws IllegalStateException si el periodo actual está vigente y no se puede
     *                               eliminar.
     */
    public void eliminarPeriodo() {
        if (this.periodo != null && this.periodo.estaVigente()) {
            throw new IllegalStateException("No se puede eliminar el periodo mientras está vigente.");
        }
        this.periodo = null;
    }

    /**
     * Devuelve la descripción del descuento.
     *
     * @return La descripción del descuento.
     */

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve la fecha de inicio del descuento. Si el descuento no tiene un
     * periodo asociado, devuelve null.
     *
     * @return la fecha de inicio del descuento o null si no tiene un periodo.
     */
    public LocalDate getFechaInicio() {
        if (periodo == null) {
            return null;
        }
        return periodo.getFechaInicio();
    }

    /**
     * Devuelve la fecha de fin del descuento. Si el descuento no tiene un
     * periodo asociado, devuelve null.
     *
     * @return la fecha de fin del descuento o null si no tiene un periodo.
     */
    public LocalDate getFechaFin() {
        if (periodo == null) {
            return null;
        }
        return periodo.getFechaFin();
    }

    /**
     * Devuelve el porcentaje del descuento.
     *
     * @return El porcentaje del descuento.
     */

    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * Devuelve la condición asociada al descuento.
     *
     * @return La condición del descuento.
     */

    public CondicionDescuento getCondicion() {
        return condicion;
    }

    /**
     * Devuelve true si el descuento se aplica a la totalidad de la factura,
     * false en caso contrario.
     *
     * @return true si el descuento se aplica a la totalidad de la factura,
     *         false en caso contrario.
     */
    public boolean isAplicableTotal() {
        return aplicableTotal;
    }
}
