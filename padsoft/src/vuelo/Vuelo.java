package vuelo;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import usuario.*;
import aeropuerto.*;
import elementocoste.*;
import factura.UsoCoste;
import sistema.SkyCompass;

/**
 * Clase que representa un vuelo.
 * 
 * Esta clase gestiona el estado de un vuelo, sus recursos asociados
 * (como pista, hangar, finger, etc.) y los procesos de carga y embarque.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    private VueloProgramado vueloProgramado;
    private EstadoVuelo estado;
    private ZonaAparcamiento aparcamiento;
    private Plaza plaza;
    private ZonaEspera zonaEspera;
    private Hangar hangar;
    private Finger finger;
    private PuertaEmbarque puertaEmbarque;
    private PistaDespegue pistaDespegue;
    private PistaAterrizaje pistaAterrizaje;
    private Terminal terminal;
    private List<UsoCoste> historialCostes = new ArrayList<>();
    private long minutosFinger = 0;
    private long minutosHangar = 0;
    private long minutosZonaAparcamiento = 0;
    private long minutosPuertaEmbarque = 0;
    private boolean retrasado;
    private Controlador controlador;
    private SkyCompass skyCompass = SkyCompass.getInstance();
    private LocalDate fecha;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;
    private LocalDateTime horaInicioEspera;
    private LocalDateTime horaFinEspera;

    /**
     * Constructor de Vuelo.
     * 
     * @param vueloProgramado El vuelo programado en base al cual se crea el vuelo.
     */
    public Vuelo(VueloProgramado vueloProgramado) {
        this.vueloProgramado = vueloProgramado;
        this.horaSalida = vueloProgramado.getHoraSalida();
        this.horaLlegada = vueloProgramado.getHoraLlegada();
        this.fecha = vueloProgramado.getFrecuencia().getFecha();
        this.estado = EstadoVuelo.PROGRAMADO;
    }

    /**
     * Devuelve la fecha del vuelo.
     * 
     * @return La fecha.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Devuelve la hora de salida del vuelo.
     * 
     * @return La hora de salida.
     */
    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    /**
     * Llamar cuando el vuelo entra en la zona de espera.
     */
    public void iniciarEspera() {
        horaInicioEspera = LocalDateTime.now();
    }

    /**
     * Llamar cuando el vuelo sale de la zona de espera.
     */
    public void terminarEspera() {
        horaFinEspera = LocalDateTime.now();
    }

    /**
     * Devuelve los minutos que el vuelo ha estado en espera.
     * 
     * @return Los minutos de espera.
     */
    public long getTiempoEspera() {
        if (horaInicioEspera != null && horaFinEspera != null) {
            return Duration.between(horaInicioEspera, horaFinEspera).toMinutes();
        }
        return 0;
    }

    /**
     * Devuelve la hora de llegada del vuelo.
     * 
     * @return La hora de llegada.
     */
    public LocalTime getHoraLlegada() {
        return this.horaLlegada;
    }

    /**
     * Devuelve la terminal asignada al vuelo.
     * 
     * @return La terminal.
     */
    public Terminal getTerminal() {
        return getVueloProgramado().getTerminal();
    }

    /**
     * Asigna la terminal al vuelo.
     * 
     * @param terminal La terminal a asignar.
     */
    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    /**
     * Devuelve el controlador asignado al vuelo.
     * 
     * @return El controlador.
     */
    public Controlador getControlador() {
        return controlador;
    }

    /**
     * Asigna el controlador al vuelo.
     * 
     * @param controlador El controlador a asignar.
     */
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Indica si el vuelo se encuentra retrasado.
     * 
     * @return return true si esta retrasado, return false en caso contrario.
     */
    public boolean isRetrasado() {
        return this.retrasado;
    }

    /**
     * Asigna el estado de retraso del vuelo.
     * 
     * @param retrasado El estado de retraso.
     */
    public void setRetrasado(boolean retrasado) {
        this.retrasado = retrasado;
    }

    /**
     * Devuelve los minutos de uso de la puerta de embarque.
     * 
     * @return Los minutos de uso.
     */
    public long getMinutosPuertaEmbarque() {
        return minutosPuertaEmbarque * 100;
    }

    /**
     * Asigna los minutos de uso de la puerta de embarque.
     * 
     * @param minutosPuertaEmbarque Los minutos a asignar.
     */
    public void setMinutosPuertaEmbarque(long minutosPuertaEmbarque) {
        this.minutosPuertaEmbarque = minutosPuertaEmbarque;
    }

    /**
     * Devuelve los minutos de uso del finger.
     * 
     * @return Los minutos de uso.
     */
    public long getMinutosFinger() {
        return minutosFinger * 100;
    }

    /**
     * Asigna los minutos de uso del finger.
     * 
     * @param minutosFinger Los minutos a asignar.
     */
    public void setMinutosFinger(long minutosFinger) {
        this.minutosFinger = minutosFinger;
    }

    /**
     * Devuelve los minutos de uso del hangar.
     * 
     * @return Los minutos de uso.
     */
    public long getMinutosHangar() {
        return minutosHangar * 100;
    }

    /**
     * Asigna los minutos de uso del hangar.
     * 
     * @param minutosHangar Los minutos a asignar.
     */
    public void setMinutosHangar(long minutosHangar) {
        this.minutosHangar = minutosHangar;
    }

    /**
     * Devuelve los minutos de uso de la zona de aparcamiento.
     * 
     * @return Los minutos de uso.
     */
    public long getMinutosZonaAparcamiento() {
        return minutosZonaAparcamiento * 100;
    }

    /**
     * Asigna los minutos de uso de la zona de aparcamiento.
     * 
     * @param minutosZonaEspera Los minutos a asignar.
     */
    public void setMinutosZonaAparcamiento(long minutosZonaEspera) {
        this.minutosZonaAparcamiento = minutosZonaEspera;
    }

    /**
     * Devuelve la zona de espera asignada.
     * 
     * @return La zona de espera.
     */
    public ZonaEspera getZonaEspera() {
        return this.zonaEspera;
    }

    /**
     * Devuelve la puerta de embarque asignada.
     * 
     * @return La puerta de embarque.
     */
    public PuertaEmbarque getPuertaEmbarque() {
        return this.puertaEmbarque;
    }

    /**
     * Asigna la zona de espera al vuelo.
     * 
     * @param zonaEspera La zona de espera a asignar.
     */
    public void setZonaEspera(ZonaEspera zonaEspera) {
        this.zonaEspera = zonaEspera;
    }

    /**
     * Devuelve la pista de aterrizaje asignada.
     * 
     * @return La pista de aterrizaje.
     */
    public PistaAterrizaje getPistaAterrizaje() {
        return this.pistaAterrizaje;
    }

    /**
     * Devuelve la pista de despegue asignada.
     * 
     * @return La pista de despegue.
     */
    public PistaDespegue getPistaDespegue() {
        return this.pistaDespegue;
    }

    /**
     * Devuelve la zona de aparcamiento asignada.
     * 
     * @return La zona de aparcamiento.
     */
    public ZonaAparcamiento getAparcamiento() {
        return this.aparcamiento;
    }

    /**
     * Asigna la zona de aparcamiento al vuelo.
     * 
     * @param aparcamiento La zona de aparcamiento a asignar.
     */
    public void setAparcamiento(ZonaAparcamiento aparcamiento) {
        this.aparcamiento = aparcamiento;
    }

    /**
     * Devuelve la plaza asignada.
     * 
     * @return La plaza.
     */
    public Plaza getPlaza() {
        return this.plaza;
    }

    /**
     * Asigna la plaza al vuelo.
     * 
     * @param plaza La plaza a asignar.
     */
    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    /**
     * Devuelve el finger asignado.
     * 
     * @return El finger.
     */
    public Finger getFinger() {
        return this.finger;
    }

    /**
     * Devuelve el hangar asignado.
     * 
     * @return El hangar.
     */
    public Hangar getHangar() {
        return this.hangar;
    }

    /**
     * Devuelve el vuelo programado.
     * 
     * @return El vuelo programado.
     */
    public VueloProgramado getVueloProgramado() {
        return this.vueloProgramado;
    }

    /**
     * Devuelve el estado del vuelo.
     * 
     * @return El estado del vuelo.
     */
    public EstadoVuelo getEstadoVuelo() {
        return this.estado;
    }

    /**
     * Asigna el estado del vuelo.
     * 
     * @param estado El estado a asignar.
     */
    private void setEstadoVuelo(EstadoVuelo estado) {
        this.estado = estado;
    }

    /**
     * Asigna el hangar al vuelo.
     * 
     * @param hangar El hangar a asignar.
     */
    public void setHangar(Hangar hangar) {
        VueloProgramado vp = this.getVueloProgramado();
        vp.getAvion().setLocalizacion(hangar);
        this.hangar = hangar;

    }

    /**
     * Asigna el finger al vuelo.
     * 
     * @param finger El finger a asignar.
     */
    public void setFinger(Finger finger) {
        this.finger = finger;
    }

    /**
     * Asigna la pista de despegue al vuelo.
     * 
     * @param pistaDespegue La pista de despegue a asignar.
     */
    public void setPistaDespegue(PistaDespegue pistaDespegue) {
        this.pistaDespegue = pistaDespegue;
    }

    /**
     * Asigna la pista de aterrizaje al vuelo.
     * 
     * @param pistaAterrizaje La pista de aterrizaje a asignar.
     */
    public void setPistaAterrizaje(PistaAterrizaje pistaAterrizaje) {
        this.pistaAterrizaje = pistaAterrizaje;
    }

    /**
     * Asigna la puerta de embarque al vuelo.
     * 
     * @param puerta La puerta de embarque a asignar.
     */
    public void setPuertaEmbarque(PuertaEmbarque puerta) {
        VueloProgramado vp = this.getVueloProgramado();
        vp.getAvion().setLocalizacion(puerta);
        this.puertaEmbarque = puerta;
    }

    /**
     * Determina si el vuelo se encuentra en el aeropuerto.
     * 
     * @return return true si el vuelo no esta DESPEGADO, return false en caso
     *         contrario.
     */
    public boolean isEnAeropuerto() {
        if (!(getEstadoVuelo() == EstadoVuelo.DESPEGADO))
            return true;
        return false;
    }

    /**
     * Actualiza el estado de retraso del vuelo.
     * 
     * Compara la hora actual con la hora de llegada o salida segun corresponda.
     */
    public void actualizarRetraso() {
        LocalDateTime ahora = LocalDateTime.now();
        if (isEnAeropuerto()) {
            retrasado = ahora.isAfter(LocalDateTime.of(getFecha(), getHoraSalida()));
        } else {
            if (!isEnAeropuerto() && ahora.plusMinutes(45).isAfter(LocalDateTime.of(getFecha(), getHoraSalida()))) {
                retrasado = true;
            } else {
                retrasado = false;
            }
        }
    }

    /**
     * Calcula el tiempo de retraso en minutos.
     * 
     * @return Los minutos de retraso si existe, de lo contrario 0.
     */
    public long tiempoRetraso() {
        if (isRetrasado()) {
            if (isEnAeropuerto()) {
                return Duration.between(LocalDateTime.of(getFecha(), getHoraSalida()), LocalDateTime.now()).toMinutes();
            } else {
                return Duration.between(LocalDateTime.of(getFecha(), getHoraLlegada()), LocalDateTime.now())
                        .toMinutes();
            }
        }

        return 0;
    }

    /**
     * Inicia la carga del vuelo.
     * 
     * Para vuelos de mercancias, si existe puerta de embarque y el avion esta en
     * hangar,
     * se cambia el estado a CARGA y se notifica al gestor y a los controladores.
     * 
     * @return return true si se inicia la carga, return false en caso contrario.
     */
    public boolean iniciarCarga() {
        VueloProgramado vp = this.vueloProgramado;
        System.out.println(this.puertaEmbarque);
        if (this.puertaEmbarque != null && vueloProgramado.getAvion().estaEnHangar() == true) {
            setEstadoVuelo(EstadoVuelo.CARGA);
            System.out.println("Estado del vuelo: " + getEstadoVuelo());

            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo CARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "CARGA");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion("Vuelo esta en modo CARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion("Vuelo esta en modo CARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this);

            return true;
        }

        if (this.puertaEmbarque == null) {
            throw new RuntimeException("No hay puerta de embarque asignada");
        }

        if (vueloProgramado.getAvion().estaEnHangar() == false) {
            throw new RuntimeException("El avion no esta en hangar");
        }

        return false;
    }

    /**
     * Finaliza la carga del vuelo.
     * 
     * Para vuelos de mercancias, si el estado es CARGA,
     * se cambia el estado a FIN_CARGA y se notifica al gestor y a los
     * controladores.
     * 
     * @return return true si se finaliza la carga, return false en caso contrario.
     */
    public boolean finalizarCarga() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.estado == EstadoVuelo.CARGA) {
            setEstadoVuelo(EstadoVuelo.FIN_CARGA);
            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo FIN_CARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "FIN_CARGA");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion("Vuelo esta en modo FIN_CARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FIN_CARGA" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            return true;
        }

        if (this.estado != EstadoVuelo.CARGA) {
            throw new RuntimeException("El estado del vuelo no es CARGA");
        }

        return false;
    }

    /**
     * Obtiene la fecha y hora de salida del vuelo.
     * 
     * @return fecha y hora de salida del vuelo.
     */
    public LocalDateTime getFechaHoraSalida() {
        return LocalDateTime.of(
                getVueloProgramado().getFrecuencia().getFechaInicio(),
                getVueloProgramado().getHoraSalida());
    }

    /**
     * Obtiene la fecha y hora de llegada del vuelo.
     * 
     * @return fecha y hora de llegada del vuelo.
     */
    public LocalDateTime getFechaHoraLlegada() {
        return LocalDateTime.of(
                getVueloProgramado().getFrecuencia().getFechaFin(),
                getHoraLlegada());
    }

    /**
     * Inicia la descarga del vuelo.
     * 
     * Para vuelos de mercancias, si existe puerta de embarque y el avion esta en
     * hangar,
     * se cambia el estado a DESCARGA y se notifica al gestor y a los controladores.
     * 
     * @return return true si se inicia la descarga, return false en caso contrario.
     */
    public boolean iniciarDescarga() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.puertaEmbarque != null && this.estado == EstadoVuelo.ATERRIZADO
                && vueloProgramado.getAvion().estaEnPuertaEmbarque() == true) {
            setEstadoVuelo(EstadoVuelo.DESCARGA);
            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo DESCARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "DESCARGA");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion("Vuelo esta en modo DESCARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo DESCARGA" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            return true;
        }

        if (this.puertaEmbarque == null) {
            throw new RuntimeException("No hay puerta de embarque asignada");
        }

        if (vueloProgramado.getAvion().estaEnPuertaEmbarque() == false) {
            throw new RuntimeException("El avion no esta en puerta de embarque");
        }

        if (this.estado != EstadoVuelo.ATERRIZADO) {
            throw new RuntimeException("El estado del vuelo no es ATERIZADO");
        }

        return false;
    }

    /**
     * Finaliza la descarga del vuelo.
     * 
     * Para vuelos de mercancias, si el estado es DESCARGA,
     * se cambia el estado a FIN_DESCARGA y se notifica al gestor y a los
     * controladores.
     * 
     * @return return true si se finaliza la descarga, return false en caso
     *         contrario.
     */
    public boolean finalizarDescarga() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.estado == EstadoVuelo.DESCARGA) {
            setEstadoVuelo(EstadoVuelo.FIN_DESCARGA);
            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo FIN_DESCARGA" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "FIN_DESCARGA");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FIN_DESCARGA" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FIN_DESCARGA" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            return true;
        }

        if (this.estado != EstadoVuelo.DESCARGA) {
            throw new RuntimeException("El estado del vuelo no es DESCARGA");
        }

        return false;
    }

    /**
     * Inicia el embarque del vuelo.
     * 
     * Para vuelos de pasajeros, si existe puerta de embarque y el avion esta en
     * hangar,
     * se cambia el estado a EMBARQUE y se notifica al gestor y a los controladores.
     * 
     * @return return true si se inicia el embarque, return false en caso contrario.
     */
    public boolean iniciarEmbarque() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.puertaEmbarque != null && vueloProgramado.getAvion().estaEnPuertaEmbarque() == true) {
            setEstadoVuelo(EstadoVuelo.EMBARQUE);
            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo EMBARQUE" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "EMBARQUE");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion("Vuelo esta en modo EMBARQUE" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo EMBARQUE" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            return true;
        }

        if (this.puertaEmbarque == null) {
            throw new RuntimeException("No hay puerta de embarque asignada");
        }

        if (vueloProgramado.getAvion().estaEnPuertaEmbarque() == false) {
            throw new RuntimeException("El avion no esta en puerta de embarque");
        }

        return false;
    }

    /**
     * Finaliza el embarque del vuelo.
     * 
     * Para vuelos de pasajeros, si el estado es EMBARQUE,
     * se cambia el estado a FIN_EMBARQUE y se notifica al gestor y a los
     * controladores.
     * 
     * @return return true si se finaliza el embarque, return false en caso
     *         contrario.
     */
    public boolean finalizarEmbarque() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.estado == EstadoVuelo.EMBARQUE) {
            setEstadoVuelo(EstadoVuelo.FIN_EMBARQUE);
            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo FIN_EMBARQUE" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "FIN_EMBARQUE");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FIN_EMBARQUE" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FIN_EMBARQUE" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            return true;
        }

        if (this.estado != EstadoVuelo.EMBARQUE) {
            throw new RuntimeException("El estado del vuelo no es EMBARQUE");
        }

        return false;
    }

    /**
     * Inicia el desembarque del vuelo.
     * 
     * Para vuelos de pasajeros, si existe pista de aterrizaje y el avion esta en la
     * pista,
     * se cambia el estado a DESEMBARQUE y se notifica al gestor y a los
     * controladores.
     * 
     * @return return true si se inicia el desembarque, return false en caso
     *         contrario.
     */
    public boolean iniciarDesembarque() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.estado == EstadoVuelo.ATERRIZADO && vueloProgramado.getAvion().estaEnPuertaEmbarque() == true) {
            setEstadoVuelo(EstadoVuelo.DESEMBARQUE);
            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo DESEMBARQUE" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "DESEMBARQUE");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo DESEMBARQUE" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo DESEMBARQUE" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            return true;
        }

        if (this.estado != EstadoVuelo.ATERRIZADO) {
            throw new RuntimeException("El estado del vuelo no es ATERRIZADO");
        }

        if (vueloProgramado.getAvion().estaEnPuertaEmbarque() == false) {
            throw new RuntimeException("El avion no esta en puerta de embarque");
        }

        return false;
    }

    /**
     * Finaliza el desembarque del vuelo.
     * 
     * Para vuelos de pasajeros, si el estado es DESEMBARQUE,
     * se cambia el estado a FIN_DESEMBARQUE y se notifica al gestor y a los
     * controladores.
     * 
     * @return return true si se finaliza el desembarque, return false en caso
     *         contrario.
     */
    public boolean finalizarDesembarque() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.estado == EstadoVuelo.DESEMBARQUE) {
            setEstadoVuelo(EstadoVuelo.FIN_DESEMBARQUE);
            skyCompass
                    .notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo FIN_DESEMBARQUE" + vp.getCodigo(),
                            LocalDate.now(), LocalTime.now()), this, "FIN_DESEMBARQUE");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FIN_DESEMBARQUE" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FIN_DESEMBARQUE" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            return true;
        }

        if (this.estado != EstadoVuelo.DESEMBARQUE) {
            throw new RuntimeException("El estado del vuelo no es DESEMBARQUE");
        }

        return false;
    }

    /**
     * Realiza el despegue del vuelo.
     * 
     * Si existe pista de despegue, se cambia el estado a DESPEGADO.
     * 
     * @return return true si despega, return false en caso contrario.
     */
    public boolean despegarVuelo() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.pistaDespegue != null && (this.estado.equals(EstadoVuelo.FIN_CARGA) || this.estado.equals(EstadoVuelo.FIN_EMBARQUE))) {
            setEstadoVuelo(EstadoVuelo.DESPEGADO);

            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo DESPEGADO" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "DESPEGADO");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion("Vuelo esta en modo DESPEGADO" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo DESPEGADO" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);

            return true;
        }

        if (this.pistaDespegue == null) {
            throw new RuntimeException("No existe pista de despegue");
        }

        return false;
    }

    /**
     * Realiza el aterrizaje del vuelo.
     * 
     * Si existe pista de aterrizaje, se cambia el estado a ATERRIZADO.
     * 
     * @return return true si aterriza, return false en caso contrario.
     */
    public boolean aterrizarVuelo() {
        VueloProgramado vp = this.vueloProgramado;
        if (this.pistaAterrizaje != null && (this.estado.equals(EstadoVuelo.DESPEGADO))) {
            setEstadoVuelo(EstadoVuelo.ATERRIZADO);

            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo ATERRIZADO" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "ATERRIZADO");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo ATERRIZADO" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo ATERRIZADO" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);

            return true;
        }

        if (this.pistaAterrizaje == null) {
            throw new RuntimeException("No existe pista de aterrizaje");
        }

        return false;
    }

    /**
     * Finaliza el vuelo.
     * 
     * Si el estado es ATERRIZADO y ademas FIN_DESEMBARQUE o FIN_DESCARGA,
     * se cambia el estado a FINALIZADO.
     * 
     * @return return true si se finaliza el vuelo, return false en caso contrario.
     */
    public boolean finalizarVuelo() {
        VueloProgramado vp = this.vueloProgramado;

        if (this.estado == EstadoVuelo.FIN_DESEMBARQUE || this.estado == EstadoVuelo.FIN_DESCARGA) {
            setEstadoVuelo(EstadoVuelo.FINALIZADO);

            skyCompass.notificarGestor(skyCompass.crearNotificacion("Vuelo esta en modo FINALIZADO" + vp.getCodigo(),
                    LocalDate.now(), LocalTime.now()), this, "FINALIZADO");
            skyCompass.notificarOperadores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FINALIZADO" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);
            skyCompass.notificarControladores(skyCompass.crearNotificacion(
                    "Vuelo esta en modo FINALIZADO" + vp.getCodigo(), LocalDate.now(), LocalTime.now()), this);

            return true;
        }

        if (this.estado != EstadoVuelo.FIN_DESEMBARQUE) {
            throw new RuntimeException("El estado del vuelo no ha finalizado el desembarque");
        }

        if (this.estado != EstadoVuelo.FIN_DESCARGA) {
            throw new RuntimeException("El estado del vuelo no ha finalizado la descarga");
        }

        return false;
    }

    /**
     * Inicia el uso de un elemento de coste.
     * 
     * @param elemento El elemento de coste.
     */
    public void iniciarUsoElemento(ElementoCoste elemento) {
        elemento.iniciarUso();
        System.out.println(elemento.getHoraInicio());
    }

    /**
     * Compara este objeto Vuelo con otro objeto para determinar si son iguales.
     * 
     * @param o El objeto a comparar con este Vuelo.
     * @return true si el objeto especificado es igual a este Vuelo, false de lo
     *         contrario.
     */

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Vuelo) || o == null)
            return false;
        Vuelo v = (Vuelo) o;
        VueloProgramado vp = this.vueloProgramado;

        return vp.getCodigo().equals(v.getVueloProgramado().getCodigo());
    }

    /**
     * Calcula el codigo de hash del vuelo.
     * 
     * El codigo de hash se calcula a partir del codigo del vuelo programado.
     * 
     * @return Codigo de hash del vuelo.
     */
    public int hashCode() {
        VueloProgramado vp = this.vueloProgramado;
        return vp.getCodigo().hashCode();
    }

    /**
     * Inicia el uso de la puerta de embarque.
     * 
     * @param p La puerta de embarque.
     */
    public void inicioPuertaEmbarque(PuertaEmbarque p) {
        p.iniciarUso();
    }

    /**
     * Finaliza el uso de la puerta de embarque.
     * 
     * Calcula y asigna los minutos de uso de la puerta.
     * 
     * @param p La puerta de embarque.
     */
    public void finalizarPuertaEmbarque(PuertaEmbarque p) {
        p.finalizarUso();
        long minutos = p.calcularMinutosUso();
        setMinutosPuertaEmbarque(minutos);
    }

    /**
     * Finaliza el uso de un elemento de coste.
     * 
     * Calcula el tiempo de uso, el coste total y registra el uso en el historial.
     * 
     * @param elemento    El elemento de coste.
     * @param descripcion La descripcion del uso.
     */
    public void finalizarUsoElemento(ElementoCoste elemento, String descripcion) {
        elemento.finalizarUso();
        long minutos = elemento.calcularMinutosUso();
        double costeTotal = elemento.getCosteTotal();
        String tiempo = minutos + " minutos";
        double costeHora = elemento.getCosteHora();

        switch (elemento.getLocalizacion()) {
            case "hangar":
                setMinutosHangar(minutos);
                break;
            case "finger":
                setMinutosFinger(minutos);
                break;
            case "aparcamiento":
                setMinutosZonaAparcamiento(minutos);
                break;
        }

        UsoCoste nuevoUso = new UsoCoste(descripcion, costeHora, tiempo, costeTotal);
        historialCostes.add(nuevoUso);
        System.out.println("Se ha añadido al historial de usos:" + nuevoUso);
        System.out.println("Historial de usos:" + historialCostes);
    }

    /**
     * Devuelve el historial de usos de coste.
     * 
     * @return La lista del historial de usos.
     */
    public List<UsoCoste> getHistorialUso() {
        return this.historialCostes;
    }

    /**
     * Representación en cadena del vuelo.
     * 
     * @return Cadena con la información del vuelo.
     */
    public String toString() {
        return "Vuelo con código " + this.vueloProgramado.getCodigo() + " del día " + this.fecha
                + ". La información del vuelo programado es: " + this.vueloProgramado;
    }
}
