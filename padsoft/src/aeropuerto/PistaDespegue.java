package aeropuerto;

import vuelo.ZonaEspera;

/**
 * Clase que representa una pista de despegue.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class PistaDespegue extends Pista {

    private ZonaEspera zonaEspera;

    /**
     * Constructor de PistaDespegue.
     * 
     * @param longitud   La longitud de la pista.
     * @param zonaEspera La zona de espera asociada a la pista.
     */

    public PistaDespegue(double longitud, ZonaEspera zonaEspera) {
        super(longitud);
        this.zonaEspera = zonaEspera;
    }

    /**
     * Obtiene la zona de espera asociada a la pista.
     * 
     * @return La zona de espera asociada.
     */
    public ZonaEspera getZonaEspera() {
        return zonaEspera;
    }

}
