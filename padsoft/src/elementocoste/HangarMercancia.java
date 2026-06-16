package elementocoste;

import aeropuerto.Dimension;

/**
 * Clase que representa un hangar de mercancías.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class HangarMercancia extends Hangar {

    private static final long serialVersionUID = 1L;
    private boolean mercanciasPeligrosas;

    /**
     * Constructor para crear un hangar de mercancías.
     * 
     * @param dimension            Dimensiones del hangar.
     * @param maxPlazas            Capacidad máxima de plazas del hangar.
     * @param mercanciasPeligrosas Indica si el hangar permite almacenar mercancías
     *                             peligrosas.
     * @param costeHora            Coste por hora de uso del hangar.
     */

    public HangarMercancia(Dimension dimension, int maxPlazas, boolean mercanciasPeligrosas,
            double costeHora) {
        super(dimension, maxPlazas, costeHora);
        this.mercanciasPeligrosas = mercanciasPeligrosas;
    }

    /**
     * Verifica si el hangar permite almacenar mercancías peligrosas.
     * 
     * @return true si el hangar admite mercancías peligrosas, false en caso
     *         contrario.
     */

    public boolean permiteMercanciasPeligrosas() {
        return mercanciasPeligrosas;
    }

}
