package factura;

/**
 * Clase que representa la interfaz funcional CondicionDescuento.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public interface CondicionDescuento {

    /**
     * Metodo que comprueba si una factura cumple con la condicion de descuento.
     * @param factura Factura a comprobar
     * @return true si la factura cumple con la condicion de descuento; false en caso contrario
     */
    public boolean cumpleCondicion(Factura factura);
}
