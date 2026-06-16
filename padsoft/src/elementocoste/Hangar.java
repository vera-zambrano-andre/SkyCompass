package elementocoste;

import aeropuerto.Dimension;
import aeropuerto.IDimensionAptitud;
import vuelo.Vuelo;
import vuelo.VueloProgramado;

/**
 * Clase que representa un hangar.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public abstract class Hangar extends ElementoCoste implements IDimensionAptitud {

	private static final long serialVersionUID = 1L;
	private int plazas;
	private Dimension dimension;
	private int maxPlazas;

	/**
	 * Constructor para la clase Hangar.
	 * 
	 * @param dimension Dimensión del hangar.
	 * @param maxPlazas Número máximo de plazas permitido en el hangar.
	 * @param costeHora Coste por hora del hangar.
	 */

	public Hangar(Dimension dimension, int maxPlazas, double costeHora) {
		super(costeHora);
		this.plazas = 0;
		this.dimension = dimension;
		this.maxPlazas = maxPlazas;
	}

	/**
	 * Comprueba si el hangar tiene las dimensiones aptas para un vuelo.
	 * 
	 * @param v Vuelo a comprobar.
	 * @return true si el hangar es apto para el vuelo, false en caso contrario.
	 */
	public boolean aptitudDimension(Vuelo v) {
		VueloProgramado vp = v.getVueloProgramado();
		Dimension dim = vp.getDimensionVueloProgramado();
		if (dim.getAncho() > dimension.getAncho() || dim.getLargo() > dimension.getLargo()
				|| dim.getAlto() > dimension.getAlto()) {
			return false;
		}
		return true;
	}

	/**
	 * Obtiene la dimensión del hangar.
	 * 
	 * @return Dimension del hangar.
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * Establece la dimensión del hangar.
	 * 
	 * @param dimension Nueva dimensión del hangar.
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * Devuelve el numero de plazas del hangar.
	 * 
	 * @return Numero de plazas del hangar.
	 */
	public int getPlazas() {
		return plazas;
	}

	/**
	 * Establece el numero de plazas del hangar.
	 * 
	 * @param plazas Numero de plazas del hangar.
	 */
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	/**
	 * Obtiene el número máximo de plazas del hangar.
	 * 
	 * @return Número máximo de plazas.
	 */
	public int getMaxPlazas() {
		return maxPlazas;
	}

	/**
	 * Establece el número máximo de plazas del hangar.
	 * 
	 * @param maxPlazas El nuevo número máximo de plazas.
	 */

	public void setMaxPlazas(int maxPlazas) {
		this.maxPlazas = maxPlazas;
	}

	/**
	 * Obtiene la localización del hangar.
	 * 
	 * @return "hangar"
	 */
	public String getLocalizacion() {
		return "hangar";
	}

	/**
	 * Convierte a String el objeto Hangar.
	 * 
	 * @return Un string que representa al objeto Hangar.
	 */
	public String toString() {
		return "Hangar " + this.maxPlazas;
	}

}
