package vuelo;

import java.util.*;
import aeropuerto.*;
import elementocoste.*;
import factura.Factura;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Clase que representa un vuelo de pasajeros.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class VueloPasajeros extends VueloProgramado {

	private boolean usaFinger;
	private List<Aerolinea> aerolineasCompartidas = new ArrayList<>();

	/**
	 * Constructor de la clase VueloPasajeros.
	 * 
	 * @param codigo      Codigo unico del vuelo.
	 * @param horaSalida  Hora de salida del vuelo.
	 * @param horaLlegada Hora de llegada del vuelo.
	 * @param frecuencia  Frecuencia con la que opera el vuelo.
	 * @param avion       Avion asignado para el vuelo.
	 * @param aerolinea   Aerolinea principal que opera el vuelo.
	 * @param origen      Aeropuerto de origen del vuelo.
	 * @param destino     Aeropuerto de destino del vuelo.
	 * @param usaFinger   Indica si el vuelo usa Finger (pasarela de embarque).
	 */
	public VueloPasajeros(String codigo, LocalTime horaSalida, LocalTime horaLlegada, FrecuenciaVuelo frecuencia,
			Avion avion, Aerolinea aerolinea, Aeropuerto origen, Aeropuerto destino, boolean usaFinger) {
		super(codigo, horaSalida, horaLlegada, frecuencia, avion, aerolinea, origen, destino);
		this.usaFinger = usaFinger;
	}

	/**
	 * Verifica si el vuelo usa Finger (pasarela de embarque).
	 * 
	 * @return true si usa Finger, false en caso contrario.
	 */
	public boolean getUsaFinger() {
		return this.usaFinger;
	}

	/**
	 * Establece si el vuelo usara Finger.
	 * 
	 * @param usaFinger true si usara Finger, false en caso contrario.
	 */
	public void setUsaFinger(boolean usaFinger) {
		this.usaFinger = usaFinger;
	}

	/**
	 * Agrega una aerolinea compartida al vuelo.
	 * 
	 * @param aerolinea Aerolinea que comparte el vuelo.
	 */
	public void addAerolineaCompartida(Aerolinea aerolinea) {
		this.aerolineasCompartidas.add(aerolinea);
	}

	/**
	 * Busca un Finger disponible que cumpla con la aptitud de dimension
	 * para el vuelo.
	 * 
	 * @param fingers Lista de Fingers del aeropuerto.
	 * @param vuelo   Vuelo que se busca asignar un Finger.
	 * @return El Finger disponible que cumpla con la aptitud de dimension,
	 *         o null si no se encuentra.
	 */
	public Finger fingerVuelo(List<Finger> fingers, Vuelo vuelo) {
		for (Finger f : fingers) {
			if (!f.isOcupado()) {
				if (f.aptitudDimension(vuelo)) {
					return f;
				}
			}
		}
		return null;
	}

	/**
	 * Busca un hangar adecuado para el vuelo proporcionado.
	 * 
	 * 
	 * @param hangarPasajero  Lista de hangares de pasajeros.
	 * @param hangarMercancia Lista de hangares de mercancías (no se utiliza).
	 * @param vuelo           El vuelo para el cual se busca un hangar.
	 * @return El hangar adecuado si se encuentra, null en caso contrario.
	 */

	public Hangar hangarVuelo(List<HangarPasajero> hangarPasajero, List<HangarMercancia> hangarMercancia, Vuelo vuelo) {
		for (HangarPasajero h : hangarPasajero) {
			if (h.getMaxPlazas() - h.getPlazas() != 0) {
				if (h.aptitudDimension(vuelo)) {
					return h;
				}
			}
		}
		return null;
	}

	/**
	 * Devuelve una lista de terminales de pasajeros libres en una franja horaria.
	 *
	 * @param terminalesPasajero  lista de terminales de pasajeros.
	 * @param terminalesMercancia lista de terminales de mercancias (no se utiliza).
	 * @param vuelosProgramados   Coleccion de vuelos programados.
	 * @param horaDia             Hora del dia en la que se busca la terminal.
	 * @param intervalo           Intervalo de minutos en el que se busca la
	 *                            terminal.
	 *
	 * @return lista de terminales de pasajeros libres en la franja horaria.
	 */
	public List<Terminal> terminalVuelo(List<TerminalPasajero> terminalesPasajero,
			List<TerminalMercancia> terminalesMercancia, Collection<VueloProgramado> vuelosProgramados,
			LocalDateTime horaDia, Integer intervalo) {
		LocalDateTime ventanaInicio = horaDia.minusMinutes(intervalo);
		LocalDateTime ventanaFin = horaDia.plusMinutes(intervalo);

		List<Terminal> libres = new ArrayList<>();

		for (TerminalPasajero t : terminalesPasajero) {
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
	 * @return true si el embarque se inicia correctamente, false en caso de error.
	 */
	public boolean iniciarEmbarqueVuelo(Vuelo vuelo) {
		return vuelo.iniciarEmbarque();
	}

	/**
	 * Finaliza el embarque del vuelo.
	 * 
	 * @param vuelo El vuelo a finalizar el embarque.
	 * @return true si se ha finalizado correctamente, false en caso de error.
	 */
	public boolean finalizarEmbarqueVuelo(Vuelo vuelo) {
		return vuelo.finalizarEmbarque();
	}

	/**
	 * Inicia el desembarque del vuelo.
	 * 
	 * @param vuelo El vuelo a iniciar el desembarque.
	 * @return true si se inicia el desembarque correctamente, false en caso
	 *         contrario.
	 */

	public boolean iniciarDesembarqueVuelo(Vuelo vuelo) {
		return vuelo.iniciarDesembarque();
	}

	/**
	 * Finaliza el desembarque del vuelo.
	 * 
	 * @param vuelo El vuelo a finalizar.
	 * @return true si se ha finalizado correctamente, false en caso de error.
	 */
	public boolean finalizarDesembarqueVuelo(Vuelo vuelo) {
		return vuelo.finalizarDesembarque();
	}

	/**
	 * Obtiene el tipo de vuelo.
	 * 
	 * @return "Pasajeros", indicando que es un vuelo de pasajeros.
	 */
	public String getTipo() {
		return "Pasajeros";
	}

	/**
	 * Obtiene el recargo actual aplicado a los pasajeros.
	 * 
	 * @return El valor del recargo por pasajeros.
	 */

	public double getRecargo() {
		return Factura.getRecargoPasajeros();
	}
}
