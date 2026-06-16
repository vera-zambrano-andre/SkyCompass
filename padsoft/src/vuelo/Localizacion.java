package vuelo;

import java.io.Serializable;

/**
 * Clase que representa una localización.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public abstract class Localizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Devuelve la localizacion del vuelo
	 * 
	 * @return localizacion
	 */
	public abstract String getLocalizacion();

}
