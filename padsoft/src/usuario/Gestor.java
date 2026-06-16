package usuario;

import vuelo.*;
import java.util.*;

/**
 * Clase que representa un gestor.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Gestor extends Usuario {
    private boolean notificacionesActivadas = true;
    private Set<Vuelo> vuelosNotificaciones = new HashSet<>();
    private Set<String> cambiosEstadoNotificaciones = new HashSet<>();

    /**
     * Constructor de la clase Gestor.
     * 
     * @param dni      El documento de identidad del gestor.
     * @param nombre   El nombre del gestor.
     * @param password La contrasena del gestor.
     */
    public Gestor(String dni, String nombre, String password) {
        super(dni, nombre, password);
    }

    /**
     * Devuelve el conjunto de cambios de estado de vuelos que se desean
     * recibir notificaciones.
     *
     * @return El conjunto de cambios de estado de vuelos.
     */
    public Set<String> getCambiosEstadoNotificaciones() {
        return this.cambiosEstadoNotificaciones;
    }

    /**
     * Agrega un cambio de estado de vuelo que se desean recibir
     * notificaciones.
     * 
     * @param cambio El cambio de estado de vuelo.
     */
    public void addCambiosEstadoNotificaciones(String cambio) {
        this.cambiosEstadoNotificaciones.add(cambio);
    }

    /**
     * Devuelve el conjunto de vuelos para los que se desean recibir
     * notificaciones.
     *
     * @return El conjunto de vuelos.
     */
    public Set<Vuelo> getVuelosNotificaciones() {
        return this.vuelosNotificaciones;
    }

    /**
     * Agrega un vuelo a la lista de vuelos para los que se desean recibir
     * notificaciones.
     * 
     * @param vuelo El vuelo a agregar.
     */
    public void addVueloNotificacion(Vuelo vuelo) {
        this.vuelosNotificaciones.add(vuelo);
    }

    /**
     * Permite asignar una nueva contrasena al gestor.
     * 
     * @param password La nueva contrasena.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Agrega una notificacion a la lista de notificaciones si las notificaciones
     * estan activadas.
     * 
     * @param notificacion La notificacion a agregar.
     */
    public void addNotificacion(Notificacion notificacion) {
        List<Notificacion> notificaciones = getNotificaciones();
        if (getNotificacionesActivadas()) {
            notificaciones.add(notificacion);
        }
    }

    /**
     * Devuelve el estado de activacion de las notificaciones.
     * 
     * @return true si las notificaciones estan activadas, false en caso contrario.
     */
    public boolean getNotificacionesActivadas() {
        return this.notificacionesActivadas;
    }

    /**
     * Activa las notificaciones.
     */
    public void activarNotificaciones() {
        this.notificacionesActivadas = true;
    }

    /**
     * Desactiva las notificaciones.
     */
    public void desactivarNotificaciones() {
        this.notificacionesActivadas = false;
    }

    /**
     * Configura los dias de antelacion minimos para la programacion de vuelo.
     * 
     * @param dias El numero de dias de antelacion.
     */
    public void configurarDiasAntelacionMinProgramacionVuelo(int dias) {
        ConfiguracionPorDefecto.getConfiguracion().setDiasAntelacionMinProgramacionVuelo(dias);
    }

    /**
     * Configura el rango de minutos de disponibilidad para ZAFinger.
     * 
     * @param rangoZAFinger El rango en minutos.
     */
    public void configurarRangoMinutosDisponibilidadZAFinger(int rangoZAFinger) {
        ConfiguracionPorDefecto.getConfiguracion().setRangoMinutosDisponibilidadZAFinger(rangoZAFinger);
    }

    /**
     * Configura los minutos de reserva de terminal.
     * 
     * @param minutos El numero de minutos.
     */
    public void configurarMinutosReservaTerminal(int minutos) {
        ConfiguracionPorDefecto.getConfiguracion().setMinutosReservaTerminal(minutos);
    }

    /**
     * Establece el rango de horas disponible para la terminal.
     * 
     * @param rangoTerminal El rango de horas.
     */
    public void setRangoHorasDisponibleTerminal(int rangoTerminal) {
        ConfiguracionPorDefecto.getConfiguracion().setRangoHorasDisponibleTerminal(rangoTerminal);
    }
}
