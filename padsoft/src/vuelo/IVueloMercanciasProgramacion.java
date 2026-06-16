package vuelo;

import java.time.LocalTime;

import aeropuerto.Aerolinea;
import aeropuerto.Aeropuerto;
import aeropuerto.Avion;

/**
 * Clase que implementa la creación de un vuelo de mercancías
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class IVueloMercanciasProgramacion implements IVueloProgramacion {

    /**
     * Crea un vuelo de mercancías programado.
     *
     * @param codigo      Codigo unico del vuelo.
     * @param horaSalida  Hora de salida del vuelo.
     * @param horaLlegada Hora de llegada del vuelo.
     * @param frecuencia  Frecuencia con la que opera el vuelo.
     * @param avion       Avion asignado para el vuelo.
     * @param aerolinea   Aerolinea que opera el vuelo.
     * @param origen      Aeropuerto de origen del vuelo.
     * @param destino     Aeropuerto de destino del vuelo.
     * @return Un objeto de tipo VueloProgramado que representa el vuelo programado.
     */
    @Override
    public VueloProgramado crearVuelo(String codigo, LocalTime horaSalida, LocalTime horaLlegada,
            FrecuenciaVuelo frecuencia, Avion avion, Aerolinea aerolinea, Aeropuerto origen, Aeropuerto destino) {
        return new VueloMercancias(codigo, horaSalida, horaLlegada, frecuencia, avion, aerolinea, origen, destino);
    }
}
