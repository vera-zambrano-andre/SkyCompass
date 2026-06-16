package vuelo;

/**
 * Clase que representa el estado de un vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public enum EstadoVuelo {
	/**
	 * Estado de carga.
	 */
	CARGA,
	/**
	 * Estado de embarque.
	 */
	EMBARQUE,
	/**
	 * Estado de fin de carga.
	 */
	FIN_CARGA,
	/**
	 * Estado de fin de embarque.
	 */
	FIN_EMBARQUE,
	/**
	 * Estado de descarga.
	 */
	DESCARGA,
	/**
	 * Estado de desembarque.
	 */
	DESEMBARQUE,
	/**
	 * Estado de fin de descarga.
	 */
	FIN_DESCARGA,
	/**
	 * Estado de fin de desembarque.
	 */
	FIN_DESEMBARQUE,
	/**
	 * Estado de espera.
	 */
	EN_ZONAESPERA,
	/**
	 * Estado de despegue.
	 */
	DESPEGADO,
	/**
	 * Estado de aterrizaje.
	 */
	ATERRIZADO,
	/**
	 * Estado de finalizado.
	 */
	FINALIZADO,
	/**
	 * Estado de programado.
	 */
	PROGRAMADO
}