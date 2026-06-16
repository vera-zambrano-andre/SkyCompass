package aeropuerto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import elementocoste.Finger;
import elementocoste.ZonaAparcamiento;
import vuelo.*;

/**
 * Clase que representa una puerta de embarque.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class PuertaEmbarque extends Localizacion {

    private Random RAND = new Random();
    private String codigo;
    private Finger finger;
    private EstadoElemento estado;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private List<ZonaAparcamiento> aparcamiento = new ArrayList<>();

    /**
     * Constructor de la clase PuertaEmbarque.
     * 
     * @param codigo       El código único de la puerta de embarque.
     * @param finger       El finger asignado a la puerta de embarque.
     * @param aparcamiento La zona de aparcamiento asignada a la puerta de embarque.
     */

    public PuertaEmbarque(String codigo, Finger finger, List<ZonaAparcamiento> aparcamiento) {
        this.codigo = codigo;
        this.finger = finger;
        this.aparcamiento = aparcamiento;
        this.estado = EstadoElemento.DISPONIBLE;
    }

    /**
     * Comprueba si la puerta de embarque esta disponible.
     * 
     * @return true si la puerta esta disponible, false en caso contrario.
     */
    public boolean isDisponible() {
        return estado.isDisponible();
    }

    /**
     * Comprueba si la puerta de embarque esta reservada.
     * 
     * @return true si la puerta esta reservada, false en caso contrario.
     */
    public boolean isReservada() {
        return estado.isReservada();
    }

    /**
     * Comprueba si la puerta de embarque está ocupada.
     * 
     * @return true si la puerta está ocupada, false en caso contrario.
     */

    public boolean isOcupada() {
        return estado.isOcupada();
    }

    /**
     * Obtiene la hora de inicio del uso de la puerta de embarque.
     * 
     * @return La hora de inicio registrada.
     */

    public LocalDateTime getHoraInicio() {
        return this.horaInicio;
    }

    /**
     * Obtiene la hora de finalizacion del uso de la puerta de embarque.
     * 
     * @return La hora de finalizacion registrada.
     */
    public LocalDateTime getHoraFin() {
        return this.horaFin;
    }

    /**
     * Inicia el uso de la puerta de embarque.
     * Establece la hora actual como hora de inicio y cambia el estado a OCUPADA.
     */
    public void iniciarUso() {
        this.horaInicio = LocalDateTime.now();
        this.estado = EstadoElemento.OCUPADA;
    }

    /**
     * Reserva el uso de la puerta de embarque.
     * Cambia el estado a RESERVADO.
     */

    public void reservarUso() {
        this.estado = EstadoElemento.RESERVADO;
    }

    /**
     * Finaliza el uso de la puerta de embarque.
     * Establece la hora actual como hora de finalizacion y cambia el estado a
     * DISPONIBLE.
     */
    public void finalizarUso() {
        this.horaFin = LocalDateTime.now();
        this.estado = EstadoElemento.DISPONIBLE;
    }

    /**
     * Calcula la duración en minutos del uso de la puerta de embarque.
     * 
     * @return Los minutos transcurridos entre la hora de inicio y la hora de fin
     *         del uso.
     *         Devuelve 0 si no se ha registrado alguna de las horas.
     */

    public long calcularMinutosUso() {
        if (this.horaInicio == null || this.horaFin == null)
            return 0;
        return Duration.between(this.horaInicio, this.horaFin).toMinutes() + RAND.nextInt(11);
    }

    /**
     * Devuelve la localización de la puerta de embarque.
     * 
     * @return "puerta"
     */
    public String getLocalizacion() {
        return "puerta";
    }

    /**
     * Obtiene el estado actual de la puerta de embarque.
     * 
     * @return El estado de la puerta de embarque.
     */

    public EstadoElemento getEstado() {
        return this.estado;
    }

    /**
     * Cambia el estado actual de la puerta de embarque.
     * 
     * @param estado El nuevo estado de la puerta de embarque.
     */
    public void setEstadoPuertaEmbarque(EstadoElemento estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el código de la puerta de embarque.
     * 
     * @return El código de la puerta de embarque.
     */

    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Devuelve el finger asignado a la puerta de embarque.
     * 
     * @return El finger asignado.
     */
    public Finger getFinger() {
        return this.finger;
    }

    /**
     * Devuelve la zona de aparcamiento asignada a la puerta de embarque.
     * 
     * @return La zona de aparcamiento asignada.
     */
    public List<ZonaAparcamiento> getAparcamientos() {
        return this.aparcamiento;
    }

    /**
     * Agrega una zona de aparcamiento a la lista de zonas de aparcamiento de la
     * puerta de embarque. Si la zona ya existe en la lista no se hace nada.
     * 
     * @param z La nueva zona de aparcamiento a agregar.
     */
    public void addAparcamiento(ZonaAparcamiento z) {
        if (z != null && !aparcamiento.contains(z)) {
            aparcamiento.add(z);
        }
    }

    /**
     * Establece la zona de aparcamiento asignada a la puerta de embarque.
     * 
     * @param aparcamiento La nueva zona de aparcamiento a asignar.
     */

    public void setAparcamiento(List<ZonaAparcamiento> aparcamiento) {
        this.aparcamiento = aparcamiento;
    }

    /**
     * Convierte el objeto en una cadena de texto que describe sus atributos.
     * 
     * @return La cadena de texto que describe el objeto.
     */
    public String toString() {
        return "PuertaEmbarque{" +
                "codigo='" + codigo + '\'' +
                ", finger=" + finger +
                ", aparcamiento=" + aparcamiento +
                ", estado=" + estado +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                '}';
    }

}
