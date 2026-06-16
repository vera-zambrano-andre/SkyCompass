package aeropuerto;

import java.io.Serializable;

/**
 * Clase que representa una dimension.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Dimension implements Serializable {
	private static final long serialVersionUID = 1L;
	private double ancho;
	private double largo;
	private double alto;

	/**
	 * Constructor de la clase Dimension.
	 * 
	 * @param ancho ancho de la dimension.
	 * @param largo largo de la dimension.
	 * @param alto  alto de la dimension.
	 */
	public Dimension(double ancho, double largo, double alto) {

		this.ancho = ancho;
		this.largo = largo;
		this.alto = alto;
	}

	/**
	 * Devuelve el ancho de la dimension.
	 * 
	 * @return ancho de la dimension.
	 */
	public double getAncho() {
		return ancho;
	}

	/**
	 * Devuelve el largo de la dimension.
	 * 
	 * @return largo de la dimension.
	 */
	public double getLargo() {
		return largo;
	}

	/**
	 * Devuelve el alto de la dimension.
	 * 
	 * @return alto de la dimension.
	 */
	public double getAlto() {
		return alto;
	}

}
