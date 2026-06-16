package factura;

import java.io.Serializable;

/**
 * Clase que representa la condición de Limite Vuelos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class CondicionLimiteVuelos implements CondicionDescuento, Serializable {
    private static final long serialVersionUID = 1L;
    private int limiteVuelos;

    /**
     * Constructor de la condición de Limite Vuelos.
     * 
     * @param limiteVuelos Límite de vuelos.
     */
    public CondicionLimiteVuelos(int limiteVuelos) {
        this.limiteVuelos = limiteVuelos;
    }

    /**
     * Comprueba si el número de vuelos de la factura es mayor que el límite de
     * vuelos.
     * 
     * @param factura Factura a comprobar.
     * @return true si el número de vuelos es mayor que el límite de vuelos; false
     *         en caso contrario.
     */
    public boolean cumpleCondicion(Factura factura) {
        return factura.getNumeroVuelos() > limiteVuelos;
    }

    /**
     * Representación en cadena de la condición de Límite Vuelos.
     * 
     * @return Cadena con la información de la condición de Límite Vuelos.
     */
    @Override
    public String toString() {
        return "Límite Vuelos: " + limiteVuelos;
    }
}
