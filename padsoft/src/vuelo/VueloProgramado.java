package vuelo;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import aeropuerto.*;
import usuario.Controlador;
import usuario.EstadoSolicitud;

/**
 * Clase que representa un vuelo programado.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public abstract class VueloProgramado implements Serializable, IElementoVuelo, IProcesosVuelo {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private FrecuenciaVuelo frecuencia;
	private EstadoSolicitud estadoSolicitud;
	private Avion avion;
	private Aerolinea aerolinea;
	private Terminal terminal;
	private Controlador controlador;
	private Aeropuerto origen;
	private Aeropuerto destino;
	private LocalTime horaSalida;
	private LocalTime horaLlegada;
	private List<Vuelo> vuelos = new ArrayList<>();

	/**
	 * Constructor de la clase VueloProgramado
	 * 
	 * @param codigo      codigo del vuelo
	 * @param horaSalida  hora de salida
	 * @param horaLlegada hora de llegada
	 * @param frecuencia  frecuencia del vuelo
	 * @param avion       avion asignado
	 * @param aerolinea   aerolinea asignada
	 * @param origen      aeropuerto de origen
	 * @param destino     aeropuerto de destino
	 */
	public VueloProgramado(String codigo, LocalTime horaSalida, LocalTime horaLlegada, FrecuenciaVuelo frecuencia,
			Avion avion, Aerolinea aerolinea, Aeropuerto origen, Aeropuerto destino) {
		this.codigo = codigo;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.frecuencia = frecuencia;
		this.avion = avion;
		this.aerolinea = aerolinea;
		this.origen = origen;
		this.destino = destino;
	}

	/**
	 * Establece el aeropuerto de origen del vuelo.
	 * 
	 * @param origen aeropuerto de origen
	 */
	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}

	/**
	 * Obtiene el aeropuerto de origen del vuelo.
	 * 
	 * @return El aeropuerto de origen.
	 */

	public Aeropuerto getOrigen() {
		return this.origen;
	}

	/**
	 * Agrega un vuelo a la lista de vuelos programados.
	 * 
	 * @param vuelo el vuelo a agregar
	 */
	public void addVuelo(Vuelo vuelo) {
		this.vuelos.add(vuelo);
	}

	/**
	 * Elimina un vuelo de la lista de vuelos.
	 * 
	 * @param vuelo el vuelo a eliminar
	 */
	public void removeVuelo(Vuelo vuelo) {
		this.vuelos.remove(vuelo);
	}

	/**
	 * Devuelve la lista de vuelos programados.
	 * 
	 * @return La lista de vuelos programados.
	 */
	public List<Vuelo> getVuelos() {
		return this.vuelos;
	}

	/**
	 * Obtiene el tipo de vuelo programado.
	 * 
	 * @return El tipo de vuelo programado.
	 */
	public abstract String getTipo();

	/**
	 * Obtiene la hora de salida del vuelo programado.
	 * 
	 * @return Hora de salida del vuelo.
	 */
	public LocalTime getHoraSalida() {
		return this.horaSalida;
	}

	/**
	 * Obtiene la hora de llegada del vuelo programado.
	 * 
	 * @return Hora de llegada del vuelo.
	 */
	public LocalTime getHoraLlegada() {
		return this.horaLlegada;
	}

	/**
	 * Obtiene el estado de solicitud del vuelo programado.
	 * 
	 * @return Estado de solicitud del vuelo.
	 */
	public EstadoSolicitud getEstadoSolicitud() {
		return this.estadoSolicitud;
	}

	/**
	 * Obtiene las dimensiones del avion asignado al vuelo.
	 * 
	 * @return Dimensiones del avion.
	 */
	public Dimension getDimensionVueloProgramado() {
		return this.avion.getDimensionAvion();
	}

	/**
	 * Obtiene la terminal desde la cual opera el vuelo.
	 * 
	 * @return Terminal de salida.
	 */
	public Terminal getTerminal() {
		return this.terminal;
	}

	/**
	 * Establece la terminal de salida del vuelo.
	 * 
	 * @param terminal Nueva terminal de salida.
	 */
	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	/**
	 * Obtiene la frecuencia con la que opera el vuelo.
	 * 
	 * @return Frecuencia de operacion.
	 */
	public FrecuenciaVuelo getFrecuencia() {
		return this.frecuencia;
	}

	/**
	 * Establece la frecuencia del vuelo.
	 * 
	 * @param frecuencia Nueva frecuencia de vuelo.
	 */
	public void setFrecuencia(FrecuenciaVuelo frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Obtiene el controlador asignado al vuelo.
	 * 
	 * @return Controlador del vuelo.
	 */
	public Controlador getControlador() {
		return this.controlador;
	}

	/**
	 * Establece el controlador responsable del vuelo.
	 * 
	 * @param controlador Nuevo controlador del vuelo.
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * Obtiene el codigo unico del vuelo.
	 * 
	 * @return Codigo del vuelo.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Establece el codigo del vuelo.
	 * 
	 * @param codigo Nuevo codigo del vuelo.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el avion asignado al vuelo.
	 * 
	 * @return Avion del vuelo.
	 */
	public Avion getAvion() {
		return this.avion;
	}

	/**
	 * Establece el avion que realizara el vuelo.
	 * 
	 * @param avion Nuevo avion asignado.
	 */
	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	/**
	 * Obtiene la aerolinea que opera el vuelo.
	 * 
	 * @return Aerolinea del vuelo.
	 */
	public Aerolinea getAerolinea() {
		return aerolinea;
	}

	/**
	 * Establece la aerolinea operadora del vuelo.
	 * 
	 * @param aerolinea Nueva aerolinea del vuelo.
	 */
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}

	/**
	 * Obtiene el aeropuerto de destino del vuelo.
	 * 
	 * @return Aeropuerto de destino.
	 */
	public Aeropuerto getDestino() {
		return destino;
	}

	/**
	 * Compara dos objetos y devuelve true si son iguales.
	 * 
	 * En este caso, dos objetos de tipo VueloProgramado son iguales si tienen el
	 * mismo
	 * codigo.
	 * 
	 * @param o El objeto a comparar con este objeto.
	 * @return true si los objetos son iguales, false en caso contrario.
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Terminal) || o == null)
			return false;
		Terminal t = (Terminal) o;
		return this.codigo.equals(t.getCodigo());
	}

	/**
	 * Calcula el codigo de hash del vuelo programado.
	 * 
	 * El codigo de hash se calcula a partir del codigo del vuelo programado.
	 * 
	 * @return Codigo de hash del vuelo programado.
	 */
	public int hashCode() {
		return this.codigo.hashCode();
	}

	/**
	 * Convierte este objeto a una cadena de texto.
	 * 
	 * @return Cadena de texto que representa al objeto.
	 */
	public String toString() {
		return "VueloProgramado{" +
				"codigo='" + codigo + '\'' +
				", terminal=" + terminal +
				", frecuencia=" + frecuencia +
				", controlador=" + controlador +
				", avion=" + avion +
				", aerolinea=" + aerolinea +
				", destino=" + destino +
				'}';
	}

	/**
	 * Obtiene el recargo asociado al vuelo.
	 * 
	 * @return Recargo del vuelo.
	 */
	public abstract double getRecargo();
}
