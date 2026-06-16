package vuelo;

import java.time.*;
import aeropuerto.*;
import elementocoste.*;
import factura.Factura;

import java.util.*;

/**
 * Clase que representa un vuelo de mercancias.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class VueloMercancias extends VueloProgramado {
    /**
     * Constructor de la clase VueloMercancias.
     *
     * @param codigo      Codigo unico del vuelo de mercancias.
     * @param horaSalida  Hora de salida del vuelo.
     * @param horaLlegada Hora de llegada del vuelo.
     * @param frecuencia  Frecuencia con la que opera el vuelo.
     * @param avion       Avion asignado para el vuelo.
     * @param aerolinea   Aerolinea que opera el vuelo.
     * @param origen      Aeropuerto de origen del vuelo.
     * @param destino     Aeropuerto de destino del vuelo.
     */
    public VueloMercancias(String codigo, LocalTime horaSalida, LocalTime horaLlegada, FrecuenciaVuelo frecuencia, Avion avion, Aerolinea aerolinea, Aeropuerto origen, Aeropuerto destino) {
        super(codigo, horaSalida, horaLlegada, frecuencia, avion, aerolinea, origen, destino);
    }
    
    /**
     * Busca un Finger disponible que cumpla con la aptitud de dimension
     * para el vuelo.
     *
     * @param fingers lista de Fingers del aeropuerto.
     * @param vuelo   Vuelo que se busca asignar un Finger.
     *
     * @return null al ser de mercancias.
     */
    public Finger fingerVuelo(List<Finger> fingers, Vuelo vuelo) {
		return null;
	}

    /**
     * Busca un Hangar disponible que cumpla con la aptitud de dimension
     * para el vuelo.
     *
     * @param hangarPasajero  lista de hangares de pasajeros.
     * @param hangarMercancia lista de hangares de mercancias.
     * @param vuelo   Vuelo que se busca asignar un Hangar.
     *
     * @return El Hangar disponible que cumpla con la aptitud de dimension,
     *         o null si no se encuentra.
     */
    public Hangar hangarVuelo(List<HangarPasajero> hangarPasajero, List<HangarMercancia> hangarMercancia, Vuelo vuelo){
        VueloProgramado vp = vuelo.getVueloProgramado();
        Avion avion=vp.getAvion();
        TipoAvion tAvion = avion.getTipo();

        for(HangarMercancia h: hangarMercancia) {

            if (h.getMaxPlazas() - h.getPlazas() != 0) {
                if (h.aptitudDimension(vuelo)) {
                    if ((h.permiteMercanciasPeligrosas() == tAvion.permiteMercanciasPeligrosas())) {
                        return h;
                    } 
                }
            }
        }

        return null;
    }


    /**
     * Devuelve una lista de terminales de mercancias libres en una franja horaria.
     *
     * @param terminalesPasajero    lista de terminales de pasajeros.
     * @param terminalesMercancia   lista de terminales de mercancias.
     * @param vuelosProgramados      Coleccion de vuelos programados.
     * @param horaDia               Hora del dia en la que se busca la terminal.
     * @param intervalo             Intervalo de minutos en el que se busca la terminal.
     *
     * @return lista de terminales de mercancias libres en la franja horaria.
     */
    public List<Terminal> terminalVuelo(List<TerminalPasajero> terminalesPasajero, List<TerminalMercancia> terminalesMercancia, Collection<VueloProgramado> vuelosProgramados, LocalDateTime horaDia, Integer intervalo) {
    	LocalDateTime ventanaInicio = horaDia.minusMinutes(intervalo);
    	LocalDateTime ventanaFin    = horaDia.plusMinutes(intervalo);

		List<Terminal> libres = new ArrayList<>();

    	for (TerminalMercancia t : terminalesMercancia) {
        	int ocupadasEnVentana = 0;
        	for (VueloProgramado vp : vuelosProgramados) {
            	if (t.equals(vp.getTerminal())) {
                	LocalDateTime fh = LocalDateTime.of(vp.getFrecuencia().getFechaInicio(), vp.getHoraSalida());
                	if (!fh.isBefore(ventanaInicio) && !fh.isAfter(ventanaFin)) {
                    	ocupadasEnVentana++;
                	}
            	}
        	}

        	if (ocupadasEnVentana >= t.getMaxPlazas()) {
            	continue;
        	}

        	libres.add(t);
    	}

    	return libres;
	}


    /**
     * Inicia el embarque del vuelo.
     * 
     * @param vuelo El vuelo a iniciar el embarque.
     * @return true si el embarque se inicia correctamente, false en caso contrario.
     */
    public boolean iniciarEmbarqueVuelo(Vuelo vuelo) {
		return vuelo.iniciarCarga();
	}

    /**
     * Finaliza el embarque del vuelo.
     * 
     * @param vuelo El vuelo a finalizar el embarque.
     * @return true si el embarque se finaliza correctamente, false en caso contrario.
     */

    public boolean finalizarEmbarqueVuelo(Vuelo vuelo) {
        return vuelo.finalizarCarga();
    }

    /**
     * Inicia el desembarque del vuelo.
     * 
     * @param vuelo El vuelo a iniciar el desembarque.
     * @return true si el desembarque se inicia correctamente, false en caso contrario.
     */

    public boolean iniciarDesembarqueVuelo(Vuelo vuelo) {
        return vuelo.iniciarDescarga();
    }

    /**
     * Finaliza el desembarque del vuelo.
     * 
     * @param vuelo El vuelo a finalizar.
     * @return true si se ha finalizado correctamente, false en caso de error.
     */
    public boolean finalizarDesembarqueVuelo(Vuelo vuelo) {
        return vuelo.finalizarDescarga();
    }

    /**
     * Devuelve el tipo de vuelo.
     *
     * @return "Mercancías", indicando que es un vuelo de mercancías.
     */
    public String getTipo(){
        return "Mercancías";
    }

    /**
     * Devuelve el recargo por mercancías actual.
     * 
     * @return El recargo por mercancías actual.
     */
    public double getRecargo(){
        return Factura.getRecargoMercancias();
    }
}
