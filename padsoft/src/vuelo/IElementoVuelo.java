package vuelo;

import java.time.LocalDateTime;
import java.util.*;
import elementocoste.*;
import aeropuerto.*;

/**
 * Interfaz que implementa métodos relacionados con la obtención de elementos
 * para un vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public interface IElementoVuelo {

    /**
     * Devuelve un finger que sea apto
     * @param finger lista de fingers
     * @param vuelo vuelo al que se le quiere asignar el finger
     * @return El finger apto
     */
    public Finger fingerVuelo(List<Finger> finger, Vuelo vuelo);

    /**
     * Devuelve un hangar que sea apto
     * @param hangarPasajero lista de hangares de pasajeros
     * @param hangarMercancia lista de hangares de mercancias
     * @param vuelo vuelo al que se le quiere asignar el hangar
     * @return El hangar apto
     */
    public Hangar hangarVuelo(List<HangarPasajero> hangarPasajero, List<HangarMercancia> hangarMercancia, Vuelo vuelo);

    /**
     * Devuelve una lista de terminales que sea apta
     * @param terminalesPasajero lista de terminales de pasajeros
     * @param terminalesMercancia lista de terminales de mercancias
     * @param vuelosProgramados lista de vuelos programados
     * @param horaDia hora del dia
     * @param intervalo intervalo de tiempo
     * @return Lista de terminales aptas
     */
    public List<Terminal> terminalVuelo(List<TerminalPasajero> terminalesPasajero,
            List<TerminalMercancia> terminalesMercancia, Collection<VueloProgramado> vuelosProgramados,
            LocalDateTime horaDia, Integer intervalo);
}
