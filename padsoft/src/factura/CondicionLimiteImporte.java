package factura;

import java.io.Serializable;

/**
 * Clase que representa la condición de Limite Importe.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class CondicionLimiteImporte implements CondicionDescuento, Serializable {
    private static final long serialVersionUID = 1L;
    private double limiteImporte;

    /**
     * Constructor de la condición de Limite Importe.
     * 
     * @param limiteImporte El límite de importe para la condición.
     */
    public CondicionLimiteImporte(double limiteImporte) {
        this.limiteImporte = limiteImporte;
    }

    /**
     * Verifica si la factura cumple con la condición de límite de importe.
     * 
     * Compara el precio total sin descuento de la factura con el límite de importe
     * especificado.
     * 
     * @param factura La factura a evaluar.
     * @return true si el precio total sin descuento de la factura es mayor que el
     *         límite de importe, false en caso contrario.
     */

    public boolean cumpleCondicion(Factura factura) {
        return factura.getPrecioTotalSinDecuento() > limiteImporte;
    }

    /**
     * Representación en cadena de la condición de límite de importe.
     * 
     * @return Cadena con la información de la condición de límite de importe.
     */
    @Override
    public String toString() {
        return "Límite Importe: " + this.limiteImporte;
    }
}
