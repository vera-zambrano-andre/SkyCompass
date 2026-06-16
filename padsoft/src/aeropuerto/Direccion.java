package aeropuerto;

/**
 * Enum que representa una dirección.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public enum Direccion {
	/**
	 * La dirección Norte.
	 */
	NORTE,
	/**
	 * La dirección Este.
	 */
	ESTE,
	/**
	 * La dirección Noreste.
	 */
	NORESTE,
	/**
	 * La dirección Noroeste.
	 */
	NOROESTE,
	/**
	 * La dirección Oeste.
	 */
	OESTE,
	/**
	 * La dirección Sur.
	 */
	SUR,
	/**
	 * La dirección Sureste.
	 */
	SURESTE,
	/**
	 * La dirección Suroeste.
	 */
	SUROESTE;

	private double latitud;
	private double longuitud;

	/**
	 * Obtiene la latitud de la dirección.
	 * 
	 * @return La latitud.
	 */
	public double getLatitud() {
		return latitud;
	}

	/**
	 * Establece la latitud de la dirección.
	 * 
	 * @param latitud La latitud de la dirección.
	 */
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	/**
	 * Obtiene la longuitud de la dirección.
	 * 
	 * @return La longuitud.
	 */
	public double getLonguitud() {
		return longuitud;
	}

	/**
	 * Establece la longitud de la dirección.
	 * 
	 * @param longuitud La longitud de la dirección.
	 */

	public void setLonguitud(double longuitud) {
		this.longuitud = longuitud;
	}

}
