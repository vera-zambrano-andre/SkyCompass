package elementocoste;

import aeropuerto.Dimension;

/**
 * Clase que representa un hangar de pasajeros.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class HangarPasajero extends Hangar {

    /**
     * Constructor de la clase HangarPasajero.
     * 
     * @param dimension Dimension del hangar.
     * @param maxPlazas Numero maximo de plazas.
     * @param costeHora Coste por hora del hangar.
     */
    public HangarPasajero(Dimension dimension, int maxPlazas, double costeHora) {
        super(dimension, maxPlazas, costeHora);
    }

}
