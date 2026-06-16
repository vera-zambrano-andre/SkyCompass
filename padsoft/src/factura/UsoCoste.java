package factura;

import java.io.Serializable;

import es.uam.eps.padsof.invoices.IResourceUsageInfo;

/**
 * Clase que representa un uso de coste.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class UsoCoste implements IResourceUsageInfo, Serializable {

    private static final long serialVersionUID = 1L;
    private String descripcionRecurso;
    private double precioHora;
    private String tiempoUso;
    private double precioFinal;

    /**
     * Constructor para inicializar los valores del coste de uso.
     *
     * @param descripcionRecurso Descripción del recurso (ej. "Finger F22")
     * @param precioHora         Precio por hora de uso
     * @param tiempoUso          Cadena que describe el tiempo de uso (ej. "50
     *                           minutos")
     * @param precioFinal        Precio total calculado
     */
    public UsoCoste(String descripcionRecurso, double precioHora, String tiempoUso, double precioFinal) {
        this.descripcionRecurso = descripcionRecurso;
        this.precioHora = precioHora;
        this.tiempoUso = tiempoUso;
        this.precioFinal = precioFinal;
    }

    // Métodos de la interfaz IResourceUsageInfo (nombres en inglés por la librería)

    /**
     * Devuelve la descripción del recurso asociado al uso de coste.
     *
     * @return La descripción del recurso.
     */
    public String getResourceDescription() {
        return descripcionRecurso;
    }

    /**
     * Devuelve el precio por hora de uso del recurso asociado al uso de coste.
     *
     * @return El precio por hora de uso.
     */
    public double getHourlyPrice() {
        return precioHora;
    }

    /**
     * Devuelve la cadena que describe el tiempo de uso del recurso asociado al uso
     * de coste.
     * 
     * @return La cadena que describe el tiempo de uso.
     */
    public String getUsageTime() {
        return tiempoUso;
    }

    /**
     * Devuelve el precio total del uso de coste.
     *
     * @return El precio total del uso de coste.
     */
    public double getPrice() {
        return precioFinal;
    }
}
