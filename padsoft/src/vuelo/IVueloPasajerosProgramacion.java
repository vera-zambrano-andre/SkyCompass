package vuelo;

import java.time.LocalTime;

import aeropuerto.Aerolinea;
import aeropuerto.Aeropuerto;
import aeropuerto.Avion;

/**
 * Clase que implementa la creación de un vuelo de pasajeros
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class IVueloPasajerosProgramacion implements IVueloProgramacion {

    private final boolean usaFinger;

    /**
     * Constructor de la clase IVueloPasajerosProgramacion
     * 
     * @param usaFinger Indica si el vuelo usa finger
     */
    public IVueloPasajerosProgramacion(boolean usaFinger) {
        this.usaFinger = usaFinger;
    }

    /**
     * Crea un vuelo de pasajeros.
     * 
     * @param codigo      Codigo unico del vuelo.
     * @param horaSalida  Hora de salida del vuelo.
     * @param horaLlegada Hora de llegada del vuelo.
     * @param frecuencia  Frecuencia con la que se opera el vuelo.
     * @param avion       Avion asignado al vuelo.
     * @param aerolinea   Aerolinea principal que opera el vuelo.
     * @param origen      Aeropuerto de origen del vuelo.
     * @param destino     Aeropuerto de destino del vuelo.
     * @return El vuelo programado de pasajeros recien creado.
     */
    public VueloProgramado crearVuelo(String codigo, LocalTime horaSalida, LocalTime horaLlegada,
            FrecuenciaVuelo frecuencia, Avion avion, Aerolinea aerolinea, Aeropuerto origen, Aeropuerto destino) {
        return new VueloPasajeros(codigo, horaSalida, horaLlegada, frecuencia, avion, aerolinea, origen, destino,
                usaFinger);
    }
}