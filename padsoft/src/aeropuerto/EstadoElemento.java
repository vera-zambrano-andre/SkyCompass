package aeropuerto;

/**
 * Clase que representa el estado de un elemento.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public enum EstadoElemento {
    /**
     * Elemento disponible
     */
    DISPONIBLE,
    /**
     * Elemento ocupado
     */
    OCUPADA,
    /**
     * Elemento reservado
     */
    RESERVADO;

    /**
     * Comprueba si el elemento esta disponible.
     *
     * @return true si el elemento esta disponible, false en caso contrario.
     */
    public boolean isDisponible() {
        return this == EstadoElemento.DISPONIBLE;
    }

    /**
     * Comprueba si el elemento esta ocupado.
     *
     * @return true si el elemento esta ocupado, false en caso contrario.
     */
    public boolean isOcupada() {
        return this == EstadoElemento.OCUPADA;
    }

    /**
     * Comprueba si el elemento está reservado.
     *
     * @return true si el elemento está reservado, false en caso contrario.
     */

    public boolean isReservada() {
        return this == EstadoElemento.RESERVADO;
    }
}
