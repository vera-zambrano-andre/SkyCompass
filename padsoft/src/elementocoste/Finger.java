package elementocoste;

import vuelo.*;
import aeropuerto.*;

/**
 * Clase que representa un finger.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Finger extends ElementoCoste implements IDimensionAptitud {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private double alturaMaxima;
	private boolean puertaDoble;
	private boolean ocupado;
	private boolean semiOcupado;

	/**
	 * Constructor de la clase Finger.
	 * 
	 * @param codigo       Codigo del Finger.
	 * @param alturaMaxima Altura maxima del Finger.
	 * @param puertaDoble  true si el Finger es de doble puerta, false en caso
	 *                     contrario.
	 * @param costeHora    Coste por hora del Finger.
	 */

	public Finger(String codigo, double alturaMaxima, boolean puertaDoble, double costeHora) {
		super(costeHora);
		this.codigo = codigo;
		this.alturaMaxima = alturaMaxima;
		this.puertaDoble = puertaDoble;
		this.ocupado = false;
		this.semiOcupado = false;
	}

	/**
	 * Comprueba si el avion asignado al vuelo programado, tiene una altura
	 * maxima menor o igual que la altura maxima del finger.
	 * 
	 * @param v Vuelo que se va a comprobar.
	 * @return true si el avion puede realizar operaciones en el finger, false
	 *         en caso contrario.
	 */
	public boolean aptitudDimension(Vuelo v) {
		VueloProgramado vp = v.getVueloProgramado();
		Dimension dim = vp.getDimensionVueloProgramado();
		if (getAlturaMaxima() > dim.getAlto()) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica si el Finger está en estado de semiocupado.
	 *
	 * @return true si el Finger está semiocupado, false en caso contrario.
	 */

	public boolean semiOcupado() {
		return semiOcupado;
	}

	/**
	 * Devuelve el código del Finger.
	 * 
	 * @return El código del Finger.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Obtiene la altura máxima permitida para el Finger.
	 * 
	 * @return La altura máxima.
	 */

	public double getAlturaMaxima() {
		return alturaMaxima;
	}

	/**
	 * Establece la altura máxima permitida para el Finger.
	 * 
	 * @param alturaMaxima La nueva altura máxima a establecer.
	 */

	public void setAlturaMaxima(double alturaMaxima) {
		this.alturaMaxima = alturaMaxima;
	}

	/**
	 * Verifica si el Finger es de doble puerta.
	 * 
	 * @return true si el Finger es de doble puerta, false en caso contrario.
	 */

	public boolean isPuertaDoble() {
		return puertaDoble;
	}

	/**
	 * Establece si el Finger es de doble puerta.
	 * 
	 * @param puertaDoble true si el Finger es de doble puerta, false en caso
	 *                    contrario.
	 */
	public void setPuertaDoble(boolean puertaDoble) {
		this.puertaDoble = puertaDoble;
	}

	/**
	 * Devuelve el estado de ocupacion del Finger.
	 * 
	 * @return true si el Finger esta ocupado, false en caso contrario.
	 */
	public boolean isOcupado() {
		return ocupado;
	}

	/**
	 * Establece el estado de ocupacion del Finger.
	 * Si el finger es de doble puerta (puertaDoble == true), se establece el estado
	 * de
	 * semiOcupado si el finger estaba vacío el estado de ocupado si el finger
	 * estaba
	 * semiOcupado. Si el finger no es de doble puerta, se establece directamente el
	 * estado
	 * de ocupado.
	 * 
	 * @param ocupado true si el finger debe estar ocupado, false en caso contrario.
	 */
	public void setOcupado(boolean ocupado) {
		if (isPuertaDoble()) {

			if (semiOcupado()) {
				this.ocupado = ocupado;
			} else {
				this.semiOcupado = ocupado;
			}

		} else {

			this.ocupado = ocupado;
		}

	}

	/**
	 * Devuelve la localizacion del finger.
	 * 
	 * @return La localizacion del finger.
	 */
	public String getLocalizacion() {
		return "finger";
	}
}