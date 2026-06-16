package usuario;

/**
 * Clase que representa el estado de una solicitud.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public enum EstadoSolicitud {
    /**
     * Aceptada. La solicitud ha sido aprobada.
     */
    ACEPTADA,
    /**
     * Denegada. La solicitud ha sido rechazada.
     */
    DENEGADA,
    /**
     * Pendiente. La solicitud está pendiente de aprobación.
     */
    PENDIENTE
}