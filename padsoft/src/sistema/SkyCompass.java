package sistema;

import aeropuerto.*;
import elementocoste.*;
import es.uam.eps.padsof.invoices.IResourceUsageInfo;
import es.uam.eps.padsof.invoices.InvoiceSystem;
import es.uam.eps.padsof.invoices.NonExistentFileException;
import es.uam.eps.padsof.invoices.UnsupportedImageTypeException;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import vuelo.*;
import usuario.*;
import factura.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase que representa la aplicacion.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class SkyCompass implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Notificacion> notificaciones;
    private List<Aerolinea> aerolineas;
    private List<Vuelo> vuelos;
    private List<VueloPasajeros> vuelosPasajeros;
    private List<VueloMercancias> vuelosMercancias;
    private List<VueloCompartido> vuelosCompartidos;
    private Map<String, VueloProgramado> vuelosProgramados;
    private List<Controlador> controladores;
    private List<Operador> operadores;
    private List<Avion> aviones;
    private List<TipoAvionPasajero> tiposAvionPasajeros;
    private List<TipoAvionMercancia> tiposAvionMercancias;
    private List<Finger> fingers;
    private List<PistaDespegue> pistasDespegues;
    private List<PistaAterrizaje> pistasAterrizajes;
    private List<ZonaEspera> zonasEspera;
    private List<ZonaAparcamiento> zonasAparcamiento;
    private List<Aeropuerto> aeropuertos;
    private List<Plaza> plazas;
    private List<HangarMercancia> hangaresMercancias;
    private List<HangarPasajero> hangaresPasajeros;
    private List<VueloProgramado> vuelosSolicitados;
    private List<PuertaEmbarque> puertasEmbarque;
    private List<TerminalMercancia> terminalesMercancias;
    private List<TerminalPasajero> terminalesPasajeros;
    private List<Terminal> terminalesReservadas;
    private List<Factura> facturas;
    private List<Descuento> descuentos;

    private Usuario usuarioLogeado;
    private Gestor gestorSkyCompass;
    private Aeropuerto aeropuertoPrincipal;

    private static final String DNI = "123456789A";
    private static final String NOMBRE = "admin";
    private static final String PASSWORD = "admin";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static SkyCompass INSTANCE;

    /**
     * Constructor privado de SkyCompass que inicializa las listas.
     */
    private SkyCompass() {
        this.vuelos = new ArrayList<>();
        this.vuelosPasajeros = new ArrayList<>();
        this.vuelosMercancias = new ArrayList<>();
        this.vuelosCompartidos = new ArrayList<>();
        this.controladores = new ArrayList<>();
        this.operadores = new ArrayList<>();
        this.aviones = new ArrayList<>();
        this.fingers = new ArrayList<>();
        this.pistasAterrizajes = new ArrayList<>();
        this.plazas = new ArrayList<>();
        this.pistasDespegues = new ArrayList<>();
        this.zonasEspera = new ArrayList<>();
        this.zonasAparcamiento = new ArrayList<>();
        this.aeropuertos = new ArrayList<>();
        this.hangaresMercancias = new ArrayList<>();
        this.hangaresPasajeros = new ArrayList<>();
        this.vuelosSolicitados = new ArrayList<>();
        this.vuelosProgramados = new HashMap<>();
        this.tiposAvionMercancias = new ArrayList<>();
        this.tiposAvionPasajeros = new ArrayList<>();
        this.puertasEmbarque = new ArrayList<>();
        this.terminalesMercancias = new ArrayList<>();
        this.terminalesPasajeros = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
        this.aerolineas = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.descuentos = new ArrayList<>();
    }

    /**
     * Devuelve la instancia unica de SkyCompass.
     * 
     * @return La instancia de SkyCompass.
     */
    public static SkyCompass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SkyCompass();
            INSTANCE.crearGestor();
            INSTANCE.crearAeropuertoPrincipal();
        }
        return INSTANCE;
    }

    /**
     * Crea el gestor de SkyCompass utilizando los datos predefinidos.
     */
    public void crearGestor() {
        Gestor gestor = new Gestor(DNI, NOMBRE, PASSWORD);
        gestorSkyCompass = gestor;
    }

    /**
     * Crea el aeropuerto principal de SkyCompass con los valores predeterminados.
     */
    public void crearAeropuertoPrincipal() {
        List<Temporada> temporadas = new ArrayList<>();

        temporadas.add(new Temporada(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 3, 31), LocalTime.of(7, 0),
                LocalTime.of(23, 0)));
        temporadas.add(new Temporada(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 6, 30), LocalTime.of(6, 0),
                LocalTime.of(22, 0)));
        temporadas.add(new Temporada(LocalDate.of(2025, 7, 1), LocalDate.of(2025, 9, 30), LocalTime.of(5, 0),
                LocalTime.of(21, 0)));
        temporadas.add(new Temporada(LocalDate.of(2025, 10, 1), LocalDate.of(2025, 12, 31), LocalTime.of(6, 0),
                LocalTime.of(22, 0)));

        this.aeropuertoPrincipal = crearAeropuerto("Aeropuerto Oficial SKYCOMPASS", "SC", "Alcobendas", "España", 15,
                Direccion.NORTE, 0, temporadas);
    }

    /**
     * Realiza el login del gestor.
     * 
     * @param password La contrasena ingresada.
     * @return return true si la contrasena es correcta, return false en caso
     *         contrario.
     */
    public boolean loginGestor(String password) {
        if (gestorSkyCompass.getPassword().equals(password)) {
            this.usuarioLogeado = gestorSkyCompass;
            return true;
        }
        return false;
    }

    /**
     * Realiza el login de un empleado (operador o controlador).
     * 
     * @param dni      El dni del empleado.
     * @param password La contrasena del empleado.
     * @return return Login.EXITO si el login es exitoso u otro elemento del enum
     *         Login en caso contrario
     */
    public Login loginEmpleado(String dni, String password) {
        List<Operador> operadores = getOperadores();
        List<Controlador> controladores = getControladores();

        for (Operador o : operadores) {

            if (o.getDni().equals(dni) && !o.getLogeado()) {
                this.usuarioLogeado = o;
                o.setLogeado(true);
                o.setPassword(password);
                return Login.EXITO;
            }

            if (o.getDni().equals(dni) && o.getLogeado()) {
                if (o.getFallos() >= 3) {
                    return Login.USUARIO_BLOQUEADO;
                }
                if (o.getPassword().equals(password)) {
                    this.usuarioLogeado = o;
                    o.setFallo(0);
                    return Login.EXITO;
                } else {
                    o.setFallo(o.getFallos() + 1);
                    return Login.FALLO_OPERADOR;
                }
            }
        }
        for (Controlador c : controladores) {
            if (c.getDni().equals(dni)) {
                if (c.getPassword().equals(password)) {
                    this.usuarioLogeado = c;
                    return Login.EXITO;
                } else {
                    return Login.FALLO_CONTROLADOR;
                }
            }
        }
        return Login.NO_EXISTE;
    }

    /**
     * Obtiene un operador por su DNI
     * 
     * @param dni El DNI del operador a buscar
     * @return El operador con el DNI buscado, o null si no existe
     */
    public Operador getOperadorPorDNI(String dni) {
        List<Operador> operadores = getOperadores();

        for (Operador o : operadores) {
            if (o.getDni().equals(dni)) {
                return o;
            }
        }
        return null;
    }

    /**
     * Resetea el numero de fallos del operador
     * 
     * @param dni El dni del operador.
     */
    public void resetear(String dni) {
        Operador operador = getOperadorPorDNI(dni);
        operador.setFallo(0);
        operador.setLogeado(false);
        operador.resetearPassword();
    }

    /**
     * Acepta la configuracion por defecto del sistema.
     * 
     * @param diasAntelacion   Los dias de antelacion minimos para la programacion
     *                         de un vuelo.
     * @param rangoZAFinger    El rango de minutos de disponibilidad para el ZA
     *                         Finger.
     * @param rangoTerminal    El rango de horas de disponibilidad para el Terminal.
     * @param reservaTerminal   Los minutos de reserva para el Terminal.
     */
    public void aceptarConfiguracion(int diasAntelacion, int rangoZAFinger, int rangoTerminal, int reservaTerminal) {
        ConfiguracionPorDefecto conf = ConfiguracionPorDefecto.getConfiguracion();
        conf.setDiasAntelacionMinProgramacionVuelo(diasAntelacion);
        conf.setRangoMinutosDisponibilidadZAFinger(rangoZAFinger);
        conf.setRangoHorasDisponibleTerminal(rangoTerminal);
        conf.setMinutosReservaTerminal(reservaTerminal);
    }

    /**
     * Registra un operador en el sistema.
     * 
     * @param dni       El dni del operador.
     * @param nombre    El nombre del operador.
     * @param aerolinea La aerolinea asociada.
     * @return El operador registrado.
     */
    public Operador registrarOperador(String dni, String nombre, Aerolinea aerolinea) {
        if (!dniUnico(dni)) {
            throw new IllegalArgumentException("Ya existe un empleado con DNI " + dni);
        }

        Operador operador = new Operador(dni, nombre, aerolinea);
        this.addOperador(operador);
        return operador;
    }

    /**
     * Registra un controlador en el sistema.
     * 
     * @param dni            El dni del controlador.
     * @param nombre         El nombre del controlador.
     * @param passwordGestor La contrasena del gestor.
     * @param terminal       La terminal asignada.
     * @return El controlador registrado.
     */
    public Controlador registrarControlador(String dni, String nombre, String passwordGestor, Terminal terminal) {
        if (!dniUnico(dni)) {
            throw new IllegalArgumentException("Ya existe un empleado con DNI " + dni);
        }

        Controlador controlador = new Controlador(dni, nombre, passwordGestor, terminal);
        this.addControlador(controlador);
        return controlador;
    }

    /**
     * Comprueba si el DNI proporcionado es único entre los operadores y
     * controladores.
     * 
     * @param dni El DNI a verificar.
     * @return true si el DNI no está registrado en ningún operador ni controlador,
     *         false en caso contrario.
     */

    private boolean dniUnico(String dni) {
        for (Operador o : operadores) {
            if (o.getDni().equalsIgnoreCase(dni)) {
                return false;
            }
        }
        for (Controlador c : controladores) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Comprueba la disponibilidad de recursos (finger, terminal, aparcamiento) para
     * un vuelo programado.
     * 
     * @param vueloProgramado El vuelo programado a comprobar.
     * @return return true si hay disponibilidad de todos los recursos requeridos,
     *         return false en caso contrario.
     */
    public boolean comprobarDisponibilidadSolicitud(VueloProgramado vueloProgramado) {
        boolean disFinger = false, disTerminal = false, disAparcamiento = false;
        List<Finger> lFinger = getFingers();
        List<Terminal> lTerminal = buscarTerminalesDisponibles(vueloProgramado, 30);
        List<ZonaAparcamiento> lAparcamiento = getZonasAparcamiento();

        for (Finger f : lFinger) {
            if (!f.isOcupado()) {
                disFinger = true;
            }
        }
        for (Terminal t : lTerminal) {
            if (t.getHorarioDisponibilidad().estaEnRango(LocalTime.now())) {
                disTerminal = true;
            }
        }
        for (ZonaAparcamiento z : lAparcamiento) {
            if (z.getMaxCapacidad() - z.getCapacidadActual() > 0) {
                disAparcamiento = true;
            }
        }

        if (disFinger && disTerminal && disAparcamiento) {
            return true;
        }
        return false;
    }

    /**
     * Solicita un vuelo compartido.
     * 
     * @param vueloPasajeros El vuelo de pasajeros sobre el que se solicita el vuelo
     *                       compartido.
     * @param porcentaje     El porcentaje de participacion, que debe ser mayor que
     *                       0 y menor o igual a 50.
     * @param codigo         El codigo del vuelo compartido.
     * @return return false si el porcentaje es menor o igual a 0 o mayor que 50, o
     *         si ya existe un vuelo programado con el mismo codigo;
     *         return true si el vuelo compartido se solicita correctamente.
     */
    public boolean solicitarVueloCompartido(VueloPasajeros vueloPasajeros, double porcentaje, String codigo) {
        if (porcentaje <= 0 || porcentaje > 50) {
            return false;
        }

        Aerolinea aerolineaPrincipal = vueloPasajeros.getAerolinea();

        for (VueloProgramado v : aerolineaPrincipal.getVuelosProgramados()) {
            if (v.getCodigo().equals(codigo)) {
                return false;
            }
        }

        VueloCompartido vueloCompartido = new VueloCompartido(vueloPasajeros, porcentaje, codigo);
        aerolineaPrincipal.addSolicitud(vueloCompartido);

        System.out.println("Vuelo compartido solicitado correctamente");
        return true;
    }

    /**
     * Acepta un vuelo compartido.
     * 
     * @param vueloCompartido El vuelo compartido a aceptar.
     * @return return true si el usuario logeado es un Operador y se acepta el vuelo
     *         compartido;
     *         return false en caso contrario.
     */
    public boolean aceptarVueloCompartido(VueloCompartido vueloCompartido) {
        vueloCompartido.setAceptado(true);

        Aerolinea aerolinea = usuarioLogeado.getAerolinea();
        if (aerolinea == null) {
            return false;
        }
        VueloPasajeros vueloPasajero = vueloCompartido.getVueloPasajero();

        aerolinea.addVueloCompartido(vueloCompartido);
        vueloPasajero.addAerolineaCompartida(aerolinea);
        aerolinea.removeSolicitud(vueloCompartido);

        return true;

    }

    /**
     * Rechaza un vuelo compartido.
     * 
     * @param vueloCompartido El vuelo compartido a rechazar.
     * @return return true si el usuario logeado es un Operador y se rechaza el
     *         vuelo compartido;
     *         return false en caso contrario.
     */
    public boolean rechazarVueloCompartido(VueloCompartido vueloCompartido) {

        Aerolinea aerolinea = usuarioLogeado.getAerolinea();
        if (aerolinea == null) {
            return false;
        }
        aerolinea.removeSolicitud(vueloCompartido);
        return true;

    }

    /**
     * Devuelve el usuario actualmente logeado.
     * 
     * @return El usuario logeado.
     */
    public Usuario getUsuarioLogeado() {
        return this.usuarioLogeado;
    }

    /**
     * Agrega una notificacion al sistema.
     * 
     * @param notificacion La notificacion a agregar.
     */
    public void addNotificacion(Notificacion notificacion) {
        this.notificaciones.add(notificacion);
    }

    /**
     * Devuelve la lista de notificaciones.
     * 
     * @return Una lista inmodificable de notificaciones.
     */
    public List<Notificacion> getNotificaciones() {
        return Collections.unmodifiableList(this.notificaciones);
    }

    /**
     * Agrega un operador al sistema.
     * 
     * @param operador El operador a agregar.
     */
    public void addOperador(Operador operador) {
        this.operadores.add(operador);
    }

    /**
     * Agrega un controlador al sistema.
     * 
     * @param controlador El controlador a agregar.
     */
    public void addControlador(Controlador controlador) {
        this.controladores.add(controlador);
    }

    /**
     * Agrega una aerolinea al sistema.
     * 
     * @param aerolinea La aerolinea a agregar.
     */
    public void addAerolinea(Aerolinea aerolinea) {
        this.aerolineas.add(aerolinea);
    }

    /**
     * Agrega una terminal de pasajero al sistema.
     * 
     * @param terminalPasajero La terminal de pasajero a agregar.
     */
    public void addTerminalPasajero(TerminalPasajero terminalPasajero) {
        this.terminalesPasajeros.add(terminalPasajero);
    }

    /**
     * Agrega una terminal de mercancia al sistema.
     * 
     * @param terminalMercancia La terminal de mercancia a agregar.
     */
    public void addTerminalMercancia(TerminalMercancia terminalMercancia) {
        this.terminalesMercancias.add(terminalMercancia);
    }

    /**
     * Agrega una puerta de embarque al sistema.
     * 
     * @param puertaEmbarque La puerta de embarque a agregar.
     */
    public void addPuertaEmbarque(PuertaEmbarque puertaEmbarque) {
        this.puertasEmbarque.add(puertaEmbarque);
    }

    /**
     * Agrega un vuelo al sistema.
     * 
     * @param vuelo El vuelo a agregar.
     */
    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }

    /**
     * Devuelve una lista inmodificable de terminales reservadas.
     *
     * @return Una lista inmodificable de terminales reservadas.
     */

    public List<Terminal> getTerminalesReservadas() {
        return Collections.unmodifiableList(this.terminalesReservadas);
    }

    /**
     * Agrega un vuelo compartido al sistema.
     * 
     * @param vueloCompartido El vuelo compartido a agregar.
     */
    public void addVueloCompartido(VueloCompartido vueloCompartido) {
        this.vuelosCompartidos.add(vueloCompartido);
    }

    /**
     * Agrega un vuelo de mercancias al sistema.
     * 
     * @param vueloMercancias El vuelo de mercancias a agregar.
     */
    public void addVueloMercancias(VueloMercancias vueloMercancias) {
        this.vuelosMercancias.add(vueloMercancias);
    }

    /**
     * Agrega un vuelo de pasajeros al sistema.
     * 
     * @param vueloPasajeros El vuelo de pasajeros a agregar.
     */
    public void addVueloPasajeros(VueloPasajeros vueloPasajeros) {
        this.vuelosPasajeros.add(vueloPasajeros);
    }

    /**
     * Agrega un tipo de avion de mercancia al sistema.
     * 
     * @param avionMercancia El tipo de avion de mercancia a agregar.
     */
    public void addAvionMercancia(TipoAvionMercancia avionMercancia) {
        this.tiposAvionMercancias.add(avionMercancia);
    }

    /**
     * Agrega un tipo de avion de pasajeros al sistema.
     * 
     * @param avionPasajero El tipo de avion de pasajeros a agregar.
     */
    public void addAvionPasajero(TipoAvionPasajero avionPasajero) {
        this.tiposAvionPasajeros.add(avionPasajero);
    }

    /**
     * Agrega un aeropuerto al sistema.
     * 
     * @param aeropuerto El aeropuerto a agregar.
     */
    public void addAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuertos.add(aeropuerto);
    }

    /**
     * Agrega un avion al sistema.
     * 
     * @param avion El avion a agregar.
     */
    public void addAvion(Avion avion) {
        this.aviones.add(avion);
    }

    /**
     * Agrega una plaza al sistema.
     * 
     * @param plaza La plaza a agregar.
     */
    public void addPlaza(Plaza plaza) {
        this.plazas.add(plaza);
    }

    /**
     * Agrega una pista de despegue al sistema.
     * 
     * @param pista La pista de despegue a agregar.
     */
    public void addPistaDespegue(PistaDespegue pista) {
        this.pistasDespegues.add(pista);
    }

    /**
     * Agrega una pista de aterrizaje al sistema.
     * 
     * @param pista La pista de aterrizaje a agregar.
     */
    public void addPistaAterrizaje(PistaAterrizaje pista) {
        this.pistasAterrizajes.add(pista);
    }

    /**
     * Agrega un hangar de mercancia al sistema.
     * 
     * @param hangarMerc El hangar de mercancia a agregar.
     */
    public void addHangarMercancia(HangarMercancia hangarMerc) {
        this.hangaresMercancias.add(hangarMerc);
    }

    /**
     * Agrega un hangar de pasajeros al sistema.
     * 
     * @param hangarPasaj El hangar de pasajeros a agregar.
     */
    public void addHangarPasajero(HangarPasajero hangarPasaj) {
        this.hangaresPasajeros.add(hangarPasaj);
    }

    /**
     * Agrega un finger al sistema.
     * 
     * @param finger El finger a agregar.
     */
    public void addFinger(Finger finger) {
        this.fingers.add(finger);
    }

    /**
     * Agrega una zona de espera al sistema.
     * 
     * @param zonaEspera La zona de espera a agregar.
     */
    public void addZonaEspera(ZonaEspera zonaEspera) {
        this.zonasEspera.add(zonaEspera);
    }

    /**
     * Agrega una zona de aparcamiento al sistema.
     * 
     * @param zonaAparcamiento La zona de aparcamiento a agregar.
     */
    public void addZonaAparcamiento(ZonaAparcamiento zonaAparcamiento) {
        this.zonasAparcamiento.add(zonaAparcamiento);
    }

    /**
     * Devuelve el gestor de SkyCompass.
     * 
     * @return El gestor del sistema.
     */
    public Gestor getGestor() {
        return this.gestorSkyCompass;
    }

    /**
     * Devuelve la lista de controladores.
     * 
     * @return Una lista inmodificable de controladores.
     */
    public List<Controlador> getControladores() {
        return Collections.unmodifiableList(this.controladores);
    }

    /**
     * Devuelve la lista de operadores.
     * 
     * @return Una lista inmodificable de operadores.
     */
    public List<Operador> getOperadores() {
        return Collections.unmodifiableList(this.operadores);
    }

    /**
     * Devuelve la lista de aerolineas.
     * 
     * @return Una lista inmodificable de aerolineas.
     */
    public List<Aerolinea> getAerolineas() {
        return Collections.unmodifiableList(this.aerolineas);
    }

    /**
     * Devuelve la lista de terminales de mercancia.
     * 
     * @return Una lista inmodificable de terminales de mercancia.
     */
    public List<TerminalMercancia> getTerminalesMercancias() {
        return Collections.unmodifiableList(this.terminalesMercancias);
    }

    /**
     * Devuelve la lista de terminales de pasajeros.
     * 
     * @return Una lista inmodificable de terminales de pasajeros.
     */
    public List<TerminalPasajero> getTerminalesPasajeros() {
        return Collections.unmodifiableList(this.terminalesPasajeros);
    }

    /**
     * Devuelve la lista de zonas de aparcamiento.
     * 
     * @return Una lista inmodificable de zonas de aparcamiento.
     */
    public List<ZonaAparcamiento> getZonasAparcamiento() {
        return Collections.unmodifiableList(this.zonasAparcamiento);
    }

    /**
     * Devuelve la lista de vuelos.
     * 
     * @return Una lista inmodificable de vuelos.
     */
    public List<Vuelo> getVuelos() {
        return this.vuelos;
    }

    /**
     * Devuelve la lista de vuelos compartidos.
     * 
     * @return Una lista inmodificable de vuelos compartidos.
     */
    public List<VueloCompartido> getVuelosCompartidos() {
        return Collections.unmodifiableList(this.vuelosCompartidos);
    }

    /**
     * Devuelve la lista de vuelos de pasajeros.
     * 
     * @return Una lista inmodificable de vuelos de pasajeros.
     */
    public List<VueloPasajeros> getVuelosPasajeros() {
        return Collections.unmodifiableList(this.vuelosPasajeros);
    }

    /**
     * Devuelve la lista de vuelos de mercancias.
     * 
     * @return Una lista inmodificable de vuelos de mercancias.
     */
    public List<VueloMercancias> getVuelosMercancias() {
        return Collections.unmodifiableList(this.vuelosMercancias);
    }

    /**
     * Devuelve la lista de puertas de embarque.
     * 
     * @return Una lista inmodificable de puertas de embarque.
     */
    public List<PuertaEmbarque> getPuertasEmbarque() {
        return Collections.unmodifiableList(this.puertasEmbarque);
    }

    /**
     * Devuelve la lista de aviones.
     * 
     * @return Una lista inmodificable de aviones.
     */
    public List<Avion> getAviones() {
        return Collections.unmodifiableList(this.aviones);
    }

    /**
     * Devuelve la lista de tipos de aviones de mercancias.
     * 
     * @return Una lista inmodificable de tipos de aviones de mercancias.
     */
    public List<TipoAvionMercancia> getTiposAvionMercancias() {
        return Collections.unmodifiableList(this.tiposAvionMercancias);
    }

    /**
     * Devuelve la lista de tipos de aviones de pasajeros.
     * 
     * @return Una lista inmodificable de tipos de aviones de pasajeros.
     */
    public List<TipoAvionPasajero> getTiposAvionPasajeros() {
        return Collections.unmodifiableList(this.tiposAvionPasajeros);
    }

    /**
     * Devuelve la lista de tipos de aviones.
     * 
     * @return Una lista inmodificable de tipos de aviones.
     */
    public List<TipoAvion> getTiposAvion() {
        List<TipoAvion> todos = new ArrayList<>();
        todos.addAll(this.tiposAvionMercancias);
        todos.addAll(this.tiposAvionPasajeros);
        return Collections.unmodifiableList(todos);
    }

    /**
     * Devuelve la lista de fingers.
     * 
     * @return Una lista inmodificable de fingers.
     */
    public List<Finger> getFingers() {
        return Collections.unmodifiableList(this.fingers);
    }

    /**
     * Devuelve la lista de pistas de despegue.
     * 
     * @return Una lista inmodificable de pistas de despegue.
     */
    public List<PistaDespegue> getPistasDespegues() {
        return Collections.unmodifiableList(this.pistasDespegues);
    }

    /**
     * Devuelve la lista de pistas de aterrizaje.
     * 
     * @return Una lista inmodificable de pistas de aterrizaje.
     */
    public List<PistaAterrizaje> getPistasAterrizajes() {
        return Collections.unmodifiableList(this.pistasAterrizajes);
    }

    /**
     * Devuelve la lista de zonas de espera.
     * 
     * @return Una lista inmodificable de zonas de espera.
     */
    public List<ZonaEspera> getZonasEspera() {
        return Collections.unmodifiableList(this.zonasEspera);
    }

    /**
     * Devuelve la lista de facturas.
     * 
     * @return Una lista inmodificable de facturas.
     */
    public List<Factura> getFacturas() {
        return Collections.unmodifiableList(this.facturas);
    }

    /**
     * Devuelve la lista de facturas pendientes de pago.
     * 
     * @return Una lista inmodificable de facturas pendientes de pago.
     */
    public List<Factura> getFacturasPendientes() {
        return this.facturas.stream().filter(factura -> !factura.isPagada()).collect(Collectors.toList());
    }

    /**
     * Devuelve la lista de hangares de mercancia.
     * 
     * @return Una lista inmodificable de hangares de mercancia.
     */
    public List<HangarMercancia> getListaHangarMercancia() {
        return Collections.unmodifiableList(this.hangaresMercancias);
    }

    /**
     * Devuelve la lista de hangares de pasajeros.
     * 
     * @return Una lista inmodificable de hangares de pasajeros.
     */
    public List<HangarPasajero> getListaHangarPasajero() {
        return Collections.unmodifiableList(this.hangaresPasajeros);
    }

    /**
     * Devuelve un mapa que asocia a cada vuelo programado con su codigo.
     * 
     * @return Un mapa inmodificable de vuelos programados.
     */
    public Map<String, VueloProgramado> getVuelosProgramados() {
        return this.vuelosProgramados;
    }

    /**
     * Devuelve la lista de aeropuertos.
     * 
     * @return Una lista inmodificable de aeropuertos.
     */
    public List<Aeropuerto> getAeropuertos() {
        return Collections.unmodifiableList(this.aeropuertos);
    }

    /**
     * Devuelve la lista de vuelos programados solicitados.
     * 
     * @return Una lista inmodificable de vuelos programados solicitados.
     */
    public List<VueloProgramado> getVuelosSolicitados() {
        return this.vuelosSolicitados;
    }

    /**
     * Devuelve la lista de hangares de mercancia con plazas disponibles.
     * 
     * @return Una lista inmodificable de hangares de mercancia con disponibilidad.
     */
    public List<HangarMercancia> getListaHangarMercanciaLibre() {
        List<HangarMercancia> lista = getListaHangarMercancia();
        List<HangarMercancia> result = new ArrayList<>();

        for (HangarMercancia h : lista) {
            if (h.getMaxPlazas() - h.getPlazas() != 0)
                result.add(h);
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * Devuelve la lista de hangares de pasajeros con plazas disponibles.
     * 
     * @return Una lista inmodificable de hangares de pasajeros con disponibilidad.
     */
    public List<HangarPasajero> getListaHangarPasajeroLibre() {
        List<HangarPasajero> lista = getListaHangarPasajero();
        List<HangarPasajero> result = new ArrayList<>();

        for (HangarPasajero h : lista) {
            if (h.getMaxPlazas() - h.getPlazas() != 0)
                result.add(h);
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * Devuelve la lista de plazas del aeropuerto.
     * 
     * @return Una lista inmodificable de plazas.
     */
    public List<Plaza> getPlazas() {
        return Collections.unmodifiableList(this.plazas);
    }

    /**
     * Devuelve la lista de zonas de aparcamiento con disponibilidad.
     * 
     * @return Una lista inmodificable de zonas de aparcamiento con disponibilidad.
     */
    public List<ZonaAparcamiento> getListaZonaAparcamientoLibre() {
        List<ZonaAparcamiento> lista = getZonasAparcamiento();
        List<ZonaAparcamiento> result = new ArrayList<>();

        for (ZonaAparcamiento z : lista) {
            if (z.getMaxCapacidad() - z.getCapacidadActual() != 0)
                result.add(z);
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * Programa un vuelo asignando una terminal y un controlador si hay
     * disponibilidad.
     * 
     * @param codigo       El codigo del vuelo.
     * @param horaSalida   La hora de salida del vuelo.
     * @param horaLlegada  La hora de llegada del vuelo.
     * @param frecuencia   La frecuencia del vuelo.
     * @param avion        El avion del vuelo.
     * @param aerolinea    La aerolinea del vuelo.
     * @param origen       El origen del vuelo.
     * @param destino      El destino del vuelo.
     * @param programacion La programacion del vuelo.
     * 
     * @return El vuelo programado creado.
     */
    public VueloProgramado programarVuelo(String codigo, LocalTime horaSalida, LocalTime horaLlegada,
            FrecuenciaVuelo frecuencia, Avion avion, Aerolinea aerolinea, Aeropuerto origen, Aeropuerto destino,
            IVueloProgramacion programacion) {

        if (vuelosProgramados.containsKey(codigo)) {
            System.out.println("El vuelo con el código " + codigo + " ya ha sido programado\n");
            return null;
        }

        VueloProgramado vuelo = programacion.crearVuelo(codigo, horaSalida, horaLlegada, frecuencia, avion, aerolinea,
                origen,
                destino);

        vuelosSolicitados.add(vuelo);

        System.out.println("Solicitud de vuelo creada correctamente\n");

        return vuelo;
    }

    /**
     * Acepta un vuelo programado con asignacion de terminal y controlador.
     * 
     * @param vp          El vuelo programado a aceptar.
     * @param terminal    La terminal asignada al vuelo.
     * @param controlador El controlador asignado al vuelo.
     * 
     * @return true si se acepta correctamente el vuelo, false en caso
     *         contrario.
     */
    public boolean aceptarVueloProgramadoConAsignacion(VueloProgramado vp, Terminal terminal, Controlador controlador) {

        LocalDate fecha = vp.getFrecuencia().getFecha();
        int diasMinAntelacion = ConfiguracionPorDefecto
                .getConfiguracion()
                .getDiasAntelacionMinProgramacionVuelo();

        long diasEntre = ChronoUnit.DAYS.between(LocalDate.now(), fecha);
        if (diasEntre < diasMinAntelacion) {
            return false;
        }

        vp.setTerminal(terminal);
        vp.setControlador(controlador);

        Notificacion noti = new Notificacion(
                "Vuelo " + vp.getCodigo() + " ha sido aprobado.",
                LocalDate.now(),
                LocalTime.now());
        this.getUsuarioLogeado().addNotificacion(noti);

        this.vuelosSolicitados.remove(vp);

        while (vp.getFrecuencia().getSiguienteFecha() != null) {
            Vuelo v = new Vuelo(vp);
            vp.addVuelo(v);
            this.vuelos.add(v);
        }

        this.vuelosProgramados.put(vp.getCodigo(), vp);

        return true;
    }

    /**
     * Acepta un vuelo programado, asignandole los recursos necesarios y
     * registrandolo en el sistema.
     * 
     * @param vueloProgramado El vuelo programado a aceptar.
     * @return El vuelo creado a partir del vuelo programado o null si no se pudo
     *         aceptar.
     */

    public boolean aceptarVueloProgramado(VueloProgramado vueloProgramado) {
        LocalDate fecha = vueloProgramado.getFrecuencia().getFecha();
        LocalTime hora = vueloProgramado.getHoraSalida();
        LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

        if (fecha.isBefore(LocalDate.now().plusDays(
                ConfiguracionPorDefecto.getConfiguracion().getDiasAntelacionMinProgramacionVuelo()))) {
            System.out.println("El vuelo no puede ser aceptado por falta de antelación");
            return false;
        }

        LocalDateTime ventana30Inicio = fechaHora.minusMinutes(30);
        LocalDateTime ventana30Fin = fechaHora.plusMinutes(30);

        int rangoHoras = ConfiguracionPorDefecto
                .getConfiguracion()
                .getRangoHorasDisponibleTerminal();
        LocalDateTime ventana48Inicio = fechaHora.minusHours(rangoHoras);
        LocalDateTime ventana48Fin = fechaHora.plusHours(rangoHoras);

        List<Terminal> terminalesDisponibles = buscarTerminalesDisponibles(vueloProgramado, 30);
        if (terminalesDisponibles.isEmpty()) {
            System.out.println("No hay terminales disponibles en ±30 min. Probando ±48 h…");
            terminalesDisponibles = buscarTerminalesDisponibles(vueloProgramado, rangoHoras * 60);
            if (terminalesDisponibles.isEmpty()) {
                System.out.println("No hay terminales disponibles en ±48 h.");
                return false;
            }
        }

        Terminal terminal = getTerminalOperador(terminalesDisponibles);
        Controlador controlador = getControladorDisponible(terminal);

        vueloProgramado.setTerminal(terminal);
        vueloProgramado.setControlador(controlador);

        crearNotificacion(
                "Vuelo " + vueloProgramado.getCodigo() + " ha sido aprobado.",
                LocalDate.now(),
                LocalTime.now());
        this.vuelosSolicitados.remove(vueloProgramado);
        
        while (vueloProgramado.getFrecuencia().getSiguienteFecha() != null) {
            Vuelo vuelo = new Vuelo(vueloProgramado);
            vueloProgramado.addVuelo(vuelo);
            vuelos.add(vuelo);
            System.out.println(vuelo);
        }
        this.vuelosProgramados.put(vueloProgramado.getCodigo(), vueloProgramado);

        return true;
    }

    /**
     * Solicita al usuario que seleccione un terminal de una lista de opciones
     * mostradas y devuelve el terminal seleccionado.
     * 
     * @param lista Lista de terminales disponibles.
     * @return Terminal seleccionado por el usuario.
     */

    private Terminal getTerminalOperador(List<Terminal> lista) {

        Map<Integer, Terminal> terms = new LinkedHashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            terms.put(i, lista.get(i));
        }

        System.out.println("Elige el gestor:");
        for (Map.Entry<Integer, Terminal> e : terms.entrySet()) {
            System.out.println("ID " + e.getKey() + ": " + e.getValue());
        }

        Integer opcion = null;
        while (opcion == null) {
            if (SCANNER.hasNextInt()) {
                int cand = SCANNER.nextInt();
                if (terms.containsKey(cand)) {
                    opcion = cand;
                } else {
                    System.out.println("ID inválido, introduce un número entre 0 y "
                            + (lista.size() - 1));
                }
            } else {
                SCANNER.next();
                System.out.println("Por favor introduce un número entero.");
            }
        }

        return terms.get(opcion);
    }

    /**
     * Obtiene un controlador disponible para la terminal asignada.
     * 
     * @param terminal La terminal a la que se asignará un controlador.
     * @return Un controlador asignado a la terminal, o null si no hay disponible.
     */
    private Controlador getControladorDisponible(Terminal terminal) {
        List<Controlador> disponibles = new ArrayList<>();

        for (Controlador c : getControladores()) {
            if (c.getTerminal().equals(terminal)) {
                disponibles.add(c);
            }
        }

        Random random = new Random();

        if (!disponibles.isEmpty()) {
            return disponibles.get(random.nextInt(disponibles.size()));
        }

        return null;
    }

    /**
     * Busca terminales disponibles en un rango de tiempo
     * solicitada.
     * 
     * @param vuelo El vuelo a programar.
     * @param intervalo El intervalo de tiempo en minutos.
     * @return Lista de terminales disponibles.
     */

    public List<Terminal> buscarTerminalesDisponibles(VueloProgramado vuelo, Integer intervalo) {
        List<Terminal> terminalesDisponibles = new ArrayList<>();

        terminalesDisponibles = vuelo.terminalVuelo(getTerminalesPasajeros(), getTerminalesMercancias(),
                vuelosProgramados.values(),
                LocalDateTime.of(vuelo.getFrecuencia().getFechaInicio(), vuelo.getHoraSalida()), intervalo);
        return terminalesDisponibles;
    }

    /**
     * Asigna un hangar a un vuelo. Si el vuelo es de pasajeros, busca un hangar
     * pasajero
     * disponible y con capacidad para el avión. Si el vuelo es de mercancias, busca
     * un
     * hangar de mercancias disponible y con capacidad para el avión, y que permita
     * el
     * tipo de mercancia que se va a transportar. Si se encuentra un hangar
     * disponible,
     * se asigna el hangar al vuelo y se notifica al gestor y a los operadores. Si
     * no se
     * encuentra un hangar disponible, se devuelve false.
     *
     * @param vuelo el vuelo al que se le va a asignar el hangar
     * @return true si se asignó un hangar, false en caso contrario
     */
    public boolean asignarVueloHangar(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();

        Hangar h = vp.hangarVuelo(getListaHangarPasajeroLibre(), getListaHangarMercanciaLibre(), vuelo);
        if (h != null) {
            vuelo.setHangar(h);
            vuelo.iniciarUsoElemento(h);
            h.setPlazas(h.getPlazas() + 1);
            return true;
        }

        return false;
    }

    /**
     * Desasigna el hangar de un vuelo, actualizando su disponibilidad y finalizando
     * su uso.
     *
     * @param vuelo El vuelo del que se desea desasignar el hangar.
     * @return true si se desasigno correctamente.
     */
    public boolean desasignarVueloHangar(Vuelo vuelo) {
        if (vuelo.getHangar() == null) {
            throw new RuntimeException("Hangar no asignado");
        }
        Hangar hangar = vuelo.getHangar();
        hangar.setPlazas(hangar.getPlazas() - 1);
        vuelo.finalizarUsoElemento(hangar, "Hangar con vuelo " + vuelo.getVueloProgramado().getCodigo()
                + vuelo.getVueloProgramado().getAerolinea());
        vuelo.setHangar(null);
        return true;
    }

    /**
     * Inicia el embarque de un vuelo.
     * 
     * Asigna una puerta de embarque al vuelo y llama a la función
     * iniciarEmbarqueVuelo del vuelo programado correspondiente.
     * 
     * @param vuelo el vuelo al que se le va a iniciar el embarque
     * @return true si se inicio el embarque, false en caso contrario
     */
    public boolean iniciarEmbarque(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        asignarVueloFinger(vuelo);
        return vp.iniciarEmbarqueVuelo(vuelo);
    }

    /**
     * Finaliza el proceso de embarque de un vuelo.
     *
     * Se desasigna el finger y se finaliza el uso de la puerta de embarque asociada
     * al vuelo. Además, se llama al método correspondiente del vuelo programado
     * para
     * completar el proceso de finalización del embarque.
     *
     * @param vuelo El vuelo cuyo embarque se va a finalizar.
     * @return true si el embarque se finalizó correctamente, false en caso
     *         contrario.
     */

    public boolean finalizarEmbarque(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        desasignarVueloFinger(vuelo);
        vuelo.finalizarPuertaEmbarque(vuelo.getPuertaEmbarque());
        return vp.finalizarEmbarqueVuelo(vuelo);
    }

    /**
     * Inicia el proceso de desembarque de un vuelo.
     * 
     * Asigna un finger al vuelo y llama a la función
     * iniciarDesembarqueVuelo del vuelo programado correspondiente.
     * 
     * @param vuelo el vuelo al que se le va a iniciar el desembarque
     * @return true si se inició el desembarque correctamente, false en caso
     *         contrario
     */

    public boolean iniciarDesembarque(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        asignarVueloFinger(vuelo);
        return vp.iniciarDesembarqueVuelo(vuelo);
    }

    /**
     * Finaliza el proceso de desembarque de un vuelo.
     * 
     * Se desasigna el finger y se finaliza el uso de la puerta de embarque asociada
     * al vuelo. Además, se llama al método correspondiente del vuelo programado
     * para
     * completar el proceso de finalización del desembarque.
     * 
     * @param vuelo El vuelo cuyo desembarque se va a finalizar.
     * @return true si el desembarque se finalizó correctamente, false en caso
     *         contrario.
     */
    public boolean finalizarDesembarque(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        desasignarVueloFinger(vuelo);
        vuelo.finalizarPuertaEmbarque(vuelo.getPuertaEmbarque());
        return vp.finalizarDesembarqueVuelo(vuelo);
    }

    /**
     * Finaliza el vuelo.
     * 
     * Si el estado es ATERRIZADO y ademas FIN_DESEMBARQUE o FIN_DESCARGA,
     * se cambia el estado a FINALIZADO.
     * 
     * @param vuelo El vuelo a finalizar.
     * @return true si se finaliza el vuelo, return false en caso contrario.
     */
    public boolean finalizarVuelo(Vuelo vuelo) {
        return vuelo.finalizarVuelo();
    }

    /**
     * Añade un vuelo programado a la lista de vuelos programados.
     * 
     * @param vuelo El vuelo programado a agregar.
     */
    public void addVueloProgramado(VueloProgramado vuelo) {
        if (vuelo != null) {
            vuelosProgramados.put(vuelo.getCodigo(), vuelo);
        }
    }

    /**
     * Busca un vuelo por su código.
     * 
     * @param codigo Código del vuelo buscado.
     * @param fecha  Fecha del vuelo buscado.
     * 
     * @return el vuelo encontrado, o null si no existe.
     */
    public Vuelo buscarVueloCodigoFecha(String codigo, LocalDate fecha) {

        for (Vuelo v : getVuelos()) {
            if (v.getVueloProgramado().getCodigo().equals(codigo) && v.getFecha().equals(fecha)) {
                return v;
            }
        }

        return null;
    }

    /**
     * Busca vuelos cuyo aeropuerto de origen coincide con el dado.
     *
     * @param aeropuertoOrigen aeropuerto de origen buscado.
     * @return lista de vuelos encontrados.
     */
    public List<VueloProgramado> buscarVuelosPorAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        List<VueloProgramado> resultados = new ArrayList<>();
        Map<String, VueloProgramado> vuelosProgramados = getVuelosProgramados();
        for (VueloProgramado v : vuelosProgramados.values()) {
            if (v.getOrigen().equals(aeropuertoOrigen)) {
                resultados.add(v);
            }
        }
        return resultados;
    }

    /**
     * Busca vuelos cuyo aeropuerto de destino coincide con el dado.
     *
     * @param aeropuertoDestino aeropuerto de destino buscado.
     * @return lista de vuelos encontrados.
     */
    public List<VueloProgramado> buscarVuelosPorAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        List<VueloProgramado> resultados = new ArrayList<>();
        Map<String, VueloProgramado> vuelosProgramados = getVuelosProgramados();
        for (VueloProgramado v : vuelosProgramados.values()) {
            if (v.getDestino().equals(aeropuertoDestino)) {
                resultados.add(v);
            }
        }
        return resultados;
    }

    /**
     * Busca vuelos cuya fecha/hora de llegada coincide exactamente.
     *
     * @param fecha llegada buscada.
     * @return lista de vuelos encontrados.
     * 
     */

    public List<VueloProgramado> buscarVuelosPorFecha(LocalDate fecha) {
        List<VueloProgramado> resultados = new ArrayList<>();
        Map<String, VueloProgramado> vuelosProgramados = getVuelosProgramados();
        for (VueloProgramado v : vuelosProgramados.values()) {
            for (Vuelo vuelo : v.getVuelos()) {
                if (vuelo.getFecha().equals(fecha) && vuelo.getVueloProgramado().equals(v)) {
                    resultados.add(v);
                }
            }
        }
        return resultados;
    }

    /**
     * Busca vuelos que operan en una terminal concreta.
     *
     * @param terminal terminal buscada.
     * @return lista de vuelos encontrados.
     */
    public List<VueloProgramado> buscarVuelosPorTerminal(Terminal terminal) {
        List<VueloProgramado> resultados = new ArrayList<>();
        Map<String, VueloProgramado> vuelosProgramados = getVuelosProgramados();
        for (VueloProgramado v : vuelosProgramados.values()) {
            if (v.getTerminal().equals(terminal)) {
                resultados.add(v);
            }
        }
        return resultados;
    }

    /**
     * Asigna un finger a un vuelo, verificando que el finger no este ocupado y que
     * cumpla con la aptitud de dimension.
     *
     * @param vuelo El vuelo al que se desea asignar el finger.
     * @return true si se asigno correctamente; false en caso contrario.
     */
    public boolean asignarVueloFinger(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        List<Finger> lista = getFingers();
        Finger f = vp.fingerVuelo(lista, vuelo);

        if (f != null) {
            vuelo.setFinger(f);
            vuelo.iniciarUsoElemento(f);
            f.setOcupado(true);
            return true;
        }

        return false;
    }

    /**
     * Desasigna el finger de un vuelo, actualizando su estado y finalizando su uso.
     *
     * @param vuelo El vuelo del que se desea desasignar el finger.
     * @return true si se desasigno correctamente.
     */
    public boolean desasignarVueloFinger(Vuelo vuelo) {
        Finger finger = vuelo.getFinger();
        finger.setOcupado(false);
        vuelo.setFinger(null);
        vuelo.finalizarUsoElemento(finger, "Finger");
        return true;
    }

    /**
     * Selecciona y asigna una zona de espera a un vuelo a partir de una lista de
     * pistas de despegue.
     * Muestra al usuario las opciones de zonas de espera disponibles y le permite
     * elegir una opción válida.
     * Asigna la pista de despegue seleccionada al vuelo si cumple con la aptitud de
     * dimensión.
     *
     * @param lista Lista de pistas de despegue disponibles para el vuelo.
     * @param vuelo El vuelo al que se le asignará la zona de espera.
     * @return Un mapa con la pista de despegue y la zona de espera seleccionadas.
     */

    private Map<PistaDespegue, ZonaEspera> elegirZonaEspera(List<PistaDespegue> lista, Vuelo vuelo) {

        Map<Integer, Map<PistaDespegue, ZonaEspera>> zones = new LinkedHashMap<>();
        int i = 0;

        for (PistaDespegue pd : lista) {
            Map<PistaDespegue, ZonaEspera> aux = new LinkedHashMap<>();
            aux.put(pd, pd.getZonaEspera());
            zones.put(i, aux);
            i++;
        }

        System.out.println("Elige el operador:");
        for (Map.Entry<Integer, Map<PistaDespegue, ZonaEspera>> e : zones.entrySet()) {
            System.out.println("ID " + e.getKey() + ": " + e.getValue().entrySet().iterator().next().getValue());
        }

        Integer opcion = null;
        while (opcion == null) {
            if (SCANNER.hasNextInt()) {
                int cand = SCANNER.nextInt();
                PistaDespegue p = zones.get(cand).keySet().iterator().next();
                if (zones.containsKey(cand) && p.aptitudDimension(vuelo)) {
                    opcion = cand;
                    vuelo.setPistaDespegue(p);
                    asignarVueloZonaEspera(vuelo);
                } else {
                    System.out.println("ID inválido, introduce un número entre 0 y "
                            + (lista.size() - 1));
                }
            } else {
                SCANNER.next();
                System.out.println("Por favor introduce un número entero.");
            }
        }

        return zones.get(opcion);
    }

    /**
     * Asigna una pista de despegue a un vuelo, verificando la disponibilidad y la
     * aptitud de dimension.
     *
     * @param vuelo El vuelo al que se le asignara la pista de despegue.
     * @return true si se asigno la pista; false en caso contrario.
     */

    public boolean asignarVueloPistaDespegue(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        Avion avion = vp.getAvion();

        List<PistaDespegue> lista = getPistasDespegues();
        for (PistaDespegue p : lista) {
            if (p.getAvionEnPista() == null) {
                if (p.aptitudDimension(vuelo)) {
                    vuelo.setPistaDespegue(p);
                    p.setAvionEnPista(avion);
                    return true;
                }
            }
        }

        System.out.println("No hay pistas de despegue disponibles. Asignando zona de espera.");

        Map<PistaDespegue, ZonaEspera> zona = elegirZonaEspera(lista, vuelo);
        if (zona != null) {
            return true;
        }

        System.out.println("La pista de despegue no pudo ser asignada. Problemas elegirZonaEspera");

        return false;
    }

    /**
     * Desasigna la pista de despegue de un vuelo, liberando la pista.
     *
     * @param vuelo El vuelo del que se desasigna la pista de despegue.
     * @return true si se desasigno correctamente.
     */
    public boolean desasignarVueloPistaDespegue(Vuelo vuelo) {

        if (vuelo.getPistaDespegue() == null) {
            throw new RuntimeException("Pista de despegue no asignada");
        }
        PistaDespegue p = vuelo.getPistaDespegue();
        p.setAvionEnPista(null);
        vuelo.setPistaDespegue(null);
        return true;
    }

    /**
     * Envía la señal para que un vuelo despegue.
     * 
     * @param vuelo El vuelo al que se le dará la señal de despegue.
     * @return true si el vuelo despegó correctamente; false en caso contrario.
     */

    public boolean darSeñalDespegue(Vuelo vuelo) {
        return vuelo.despegarVuelo();
    }

    /**
     * Envía la señal para que un vuelo aterrice.
     * 
     * @param vuelo El vuelo al que se le dará la señal de aterrizaje.
     * @return true si el vuelo aterrizó correctamente; false en caso contrario.
     */

    public boolean darSeñalAterrizaje(Vuelo vuelo) {
        return vuelo.aterrizarVuelo();
    }

    /**
     * Crea un numero determinado de plazas con dimensiones dadas y las agrega al
     * sistema.
     *
     * @param numPlazas Numero de plazas a crear.
     * @param ancho     Ancho de las plazas a crear.
     * @param largo     Largo de las plazas a crear.
     * @return La lista de plazas creadas.
     */
    public List<Plaza> crearPlazas(int numPlazas, double ancho, double largo) {
        List<Plaza> plazas = new ArrayList<>();

        for (int i = 0; i < numPlazas; i++) {
            Plaza plaza = new Plaza(ancho, largo);
            plazas.add(plaza);
            addPlaza(plaza);
        }
        return plazas;
    }

    /**
     * Asigna una pista de aterrizaje a un vuelo, verificando la disponibilidad y la
     * aptitud de dimension.
     *
     * @param vuelo El vuelo al que se le asignara la pista de aterrizaje.
     * @return true si se asigno la pista; false en caso contrario.
     */
    public boolean asignarVueloPistaAterrizaje(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        Avion avion = vp.getAvion();

        List<PistaAterrizaje> lista = getPistasAterrizajes();
        for (PistaAterrizaje p : lista) {
            if (p.getAvionEnPista() == null) {
                if (p.aptitudDimension(vuelo)) {
                    vuelo.setPistaAterrizaje(p);
                    p.setAvionEnPista(avion);
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Desasigna la pista de aterrizaje de un vuelo, liberando la pista.
     *
     * @param vuelo El vuelo del que se desasigna la pista de aterrizaje.
     * @return true si se desasigno correctamente.
     */
    public boolean desasignarVueloPistaAterrizaje(Vuelo vuelo) {
        PistaAterrizaje p = vuelo.getPistaAterrizaje();
        p.setAvionEnPista(null);
        vuelo.setPistaAterrizaje(null);
        return true;
    }

    /**
     * Asigna una puerta de embarque a un vuelo, actualizando su estado.
     *
     * @param vuelo El vuelo al que se le asignara la puerta de embarque.
     * @return true si se asigno la puerta; false en caso contrario.
     */
    public boolean asignarVueloPuertaEmbarque(Vuelo vuelo) {
        List<PuertaEmbarque> lista = getPuertasEmbarque();

        for (PuertaEmbarque p : lista) {
            if (p.isDisponible()) {
                vuelo.setPuertaEmbarque(p);
                p.setEstadoPuertaEmbarque(EstadoElemento.OCUPADA);
                p.iniciarUso();
                return true;
            }
        }
        return false;
    }

    /**
     * Desasigna la puerta de embarque de un vuelo, actualizando su estado a
     * DISPONIBLE.
     *
     * @param vuelo El vuelo del que se desasigna la puerta de embarque.
     * @return true si se desasigno correctamente.
     */
    public boolean desasignarVueloPuertaEmbarque(Vuelo vuelo) {
        if (vuelo.getPuertaEmbarque() == null) {
            throw new RuntimeException("Puerta de embarque no asignada");
        }
        PuertaEmbarque p = vuelo.getPuertaEmbarque();
        p.setEstadoPuertaEmbarque(EstadoElemento.DISPONIBLE);
        p.finalizarUso();
        vuelo.setMinutosPuertaEmbarque(p.calcularMinutosUso());
        vuelo.setPuertaEmbarque(null);
        return true;
    }

    /**
     * Asigna una zona de aparcamiento a un vuelo, buscando una plaza disponible que
     * cumpla la aptitud de dimension.
     *
     * @param vuelo El vuelo al que se le asignara la zona de aparcamiento.
     * @return true si se asigno la zona; false en caso contrario.
     */
    public boolean asignarVueloZonaAparcamiento(Vuelo vuelo) {
        List<ZonaAparcamiento> lista = getListaZonaAparcamientoLibre();

        for (ZonaAparcamiento z : lista) {
            List<Plaza> plazas = z.getPlazas();
            for (Plaza p : plazas) {
                if (!p.isOcupada()) {
                    if (p.aptitudDimension(vuelo)) {
                        vuelo.setPlaza(p);
                        vuelo.setAparcamiento(z);
                        vuelo.iniciarUsoElemento(z);
                        p.setOcupada(true);
                        z.setCapacidadActual(z.getCapacidadActual() + 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Desasigna la zona de aparcamiento de un vuelo, liberando la plaza y
     * actualizando la capacidad.
     *
     * @param vuelo El vuelo del que se desasigna la zona de aparcamiento.
     * @return true si se desasigno correctamente.
     */
    public boolean desasignarVueloZonaAparcamiento(Vuelo vuelo) {
        ZonaAparcamiento z = vuelo.getAparcamiento();
        z.setCapacidadActual(z.getCapacidadActual() - 1);
        Plaza p = vuelo.getPlaza();
        p.setOcupada(false);
        vuelo.setPlaza(null);
        vuelo.setAparcamiento(null);
        vuelo.finalizarUsoElemento(z, "ZonaAparcamiento Plaza");
        return true;
    }

    /**
     * Asigna una zona de espera a un vuelo basandose en la pista de despegue.
     *
     * @param vuelo El vuelo al que se le asignara la zona de espera.
     * @return true si se asigno la zona; false si no hay pista asignada.
     */
    public boolean asignarVueloZonaEspera(Vuelo vuelo) {
        VueloProgramado vp = vuelo.getVueloProgramado();
        PistaDespegue pista = vuelo.getPistaDespegue();
        if (pista == null) {
            return false;
        }

        pista.getZonaEspera().addVuelo(vuelo);
        vuelo.setZonaEspera(pista.getZonaEspera());
        notificarGestor(
                crearNotificacion("ZonaEspera asignado a Vuelo" + vp.getCodigo(), LocalDate.now(), LocalTime.now()),
                vuelo, "ZonaEspera");
        notificarOperadores(
                crearNotificacion("ZonaEspera asignado a Vuelo" + vp.getCodigo(), LocalDate.now(), LocalTime.now()),
                vuelo);
        return true;
    }

    /**
     * Desasigna la zona de espera de un vuelo.
     *
     * @param vuelo El vuelo del que se desasigna la zona de espera.
     * @return true si se desasigno correctamente; false si no habia zona asignada.
     */
    public boolean desasignarVueloZonaEspera(Vuelo vuelo) {
        ZonaEspera zonaEspera = vuelo.getZonaEspera();
        if (zonaEspera == null) {
            return false;
        }
        vuelo.setZonaEspera(null);
        return true;
    }

    /**
     * Da de alta un vuelo programado de pasajeros y lo agrega al sistema.
     *
     * @param codigo      Codigo unico del vuelo.
     * @param horaSalida  Hora de salida del vuelo.
     * @param horaLlegada Hora de llegada del vuelo.
     * @param frecuencia  Frecuencia con la que se opera el vuelo.
     * @param avion       Avion asignado al vuelo.
     * @param aerolinea   Aerolinea principal que opera el vuelo.
     * @param origen      Aeropuerto de origen del vuelo.
     * @param destino     Aeropuerto de destino del vuelo.
     * @param usaFinger   Indica si el vuelo usa Finger (pasarela de embarque).
     * @return El vuelo programado de pasajeros recien creado.
     */
    public VueloPasajeros darAltaVueloProgramadoPasajeros(String codigo, LocalTime horaSalida, LocalTime horaLlegada,
            FrecuenciaVuelo frecuencia, Avion avion, Aerolinea aerolinea, Aeropuerto origen, Aeropuerto destino,
            boolean usaFinger) {
        VueloPasajeros vueloPasajeros = new VueloPasajeros(codigo, horaSalida, horaLlegada, frecuencia, avion,
                aerolinea, origen, destino, usaFinger);
        addVueloPasajeros(vueloPasajeros);
        return vueloPasajeros;
    }

    /**
     * Da de alta un vuelo programado de mercancias y lo agrega al sistema.
     *
     * @param codigo      Codigo unico del vuelo.
     * @param horaSalida  Hora de salida del vuelo.
     * @param horaLlegada Hora de llegada del vuelo.
     * @param frecuencia  Frecuencia con la que se opera el vuelo.
     * @param avion       Avion asignado al vuelo.
     * @param aerolinea   Aerolinea principal que opera el vuelo.
     * @param terminal    Terminal desde la que opera el vuelo.
     * @param controlador Controlador asignado al vuelo.
     * @param origen      Aeropuerto de origen del vuelo.
     * @param destino     Aeropuerto de destino del vuelo.
     *
     * @return El objeto VueloMercancias recien creado.
     */
    public VueloMercancias darAltaVueloProgramadoMercancias(String codigo, LocalTime horaSalida, LocalTime horaLlegada,
            FrecuenciaVuelo frecuencia, Avion avion, Aerolinea aerolinea, Terminal terminal, Controlador controlador,
            Aeropuerto origen,
            Aeropuerto destino) {
        VueloMercancias vueloMercancias = new VueloMercancias(codigo, horaSalida, horaLlegada, frecuencia, avion,
                aerolinea, origen, destino);
        addVueloMercancias(vueloMercancias);
        return vueloMercancias;
    }

    /**
     * Da de alta un vuelo a partir de un vuelo programado y lo agrega al sistema.
     *
     * @param vueloProgramado El vuelo programado en base al que se crea el vuelo.
     * @return El objeto Vuelo creado.
     */
    public Vuelo darAltaVuelo(VueloProgramado vueloProgramado) {
        Vuelo vuelo = new Vuelo(vueloProgramado);
        addVuelo(vuelo);
        return vuelo;
    }

    /**
     * Da de alta una zona de espera y la agrega al sistema.
     *
     * @return El objeto ZonaEspera creado.
     */
    public ZonaEspera darAltaZonaEspera() {
        ZonaEspera zonaEspera = new ZonaEspera();
        addZonaEspera(zonaEspera);
        return zonaEspera;
    }

    /**
     * Da de alta una zona de aparcamiento y la agrega al sistema.
     *
     * @param codigo       Codigo de la zona de aparcamiento.
     * @param maxCapacidad Capacidad maxima de la zona.
     * @param plazas       Lista de plazas de la zona.
     * @param costeHora    Coste por hora de la zona.
     * @return El objeto ZonaAparcamiento creado.
     */
    public ZonaAparcamiento darAltaZonaAparcamiento(String codigo, int maxCapacidad, List<Plaza> plazas,
            double costeHora) {
        ZonaAparcamiento zonaAparcamiento = new ZonaAparcamiento(codigo, maxCapacidad, plazas, costeHora);
        addZonaAparcamiento(zonaAparcamiento);
        return zonaAparcamiento;
    }

    /**
     * Da de alta una plaza y la agrega al sistema.
     *
     * @param ancho Ancho de la plaza.
     * @param largo Largo de la plaza.
     * @return El objeto Plaza creado.
     */
    public Plaza darAltaPlaza(double ancho, double largo) {
        Plaza plaza = new Plaza(ancho, largo);
        addPlaza(plaza);
        return plaza;
    }

    /**
     * Da de alta un hangar de mercancias y lo agrega al sistema.
     *
     * @param dimension            Dimension del hangar.
     * @param maxPlazas            Numero maximo de plazas.
     * @param mercanciasPeligrosas Indica si admite mercancias peligrosas.
     * @param costeHora            Coste por hora del hangar.
     * @return El objeto HangarMercancia creado.
     */
    public HangarMercancia darAltaHangarMercancia(Dimension dimension, int maxPlazas,
            boolean mercanciasPeligrosas, double costeHora) {
        HangarMercancia hangarMercancia = new HangarMercancia(dimension, maxPlazas, mercanciasPeligrosas,
                costeHora);
        addHangarMercancia(hangarMercancia);
        return hangarMercancia;
    }

    /**
     * Da de alta un hangar de pasajeros y lo agrega al sistema.
     *
     * @param dimension Dimension del hangar.
     * @param maxPlazas Numero maximo de plazas.
     * @param costeHora Coste por hora del hangar.
     * @return El objeto HangarPasajero creado.
     */
    public HangarPasajero darAltaHangarPasajero(Dimension dimension, int maxPlazas, double costeHora) {
        HangarPasajero hangarPasajero = new HangarPasajero(dimension, maxPlazas, costeHora);
        addHangarPasajero(hangarPasajero);
        return hangarPasajero;
    }

    /**
     * Da de alta un finger y lo agrega al sistema.
     *
     * @param codigo       Codigo del finger.
     * @param alturaMaxima Altura maxima permitida.
     * @param puertaDoble  Indica si tiene puerta doble.
     * @param costeHora    Coste por hora del finger.
     * @return El objeto Finger creado.
     */
    public Finger darAltaFinger(String codigo, double alturaMaxima, boolean puertaDoble, double costeHora) {
        Finger finger = new Finger(codigo, alturaMaxima, puertaDoble, costeHora);
        addFinger(finger);
        return finger;
    }

    /**
     * Da de alta un tipo de avion para pasajeros y lo agrega al sistema.
     *
     * @param marca        Marca del avion.
     * @param modelo       Modelo del avion.
     * @param dimension    Dimension del avion.
     * @param autonomia    Autonomia del avion.
     * @param maxPasajeros Numero maximo de pasajeros.
     * @return El objeto TipoAvionPasajero creado.
     */
    public TipoAvionPasajero darAltaTipoAvionPasajero(String marca, String modelo, Dimension dimension,
            double autonomia, int maxPasajeros) {
        TipoAvionPasajero tipoAvionPasajero = new TipoAvionPasajero(marca, modelo, dimension, autonomia, maxPasajeros);
        addAvionPasajero(tipoAvionPasajero);
        return tipoAvionPasajero;
    }

    /**
     * Da de alta un tipo de avion para mercancias y lo agrega al sistema.
     *
     * @param marca              Marca del avion.
     * @param modelo             Modelo del avion.
     * @param dimension          Dimension del avion.
     * @param autonomia          Autonomia del avion.
     * @param controlTemperatura Indica si controla la temperatura.
     * @param mercanciaPeligrosa Indica si admite mercancia peligrosa.
     * @param maxCarga           Carga maxima permitida.
     * @return El objeto TipoAvionMercancia creado.
     */
    public TipoAvionMercancia darAltaTipoAvionMercancia(String marca, String modelo, Dimension dimension,
            double autonomia, boolean controlTemperatura, boolean mercanciaPeligrosa, double maxCarga) {
        TipoAvionMercancia tipoAvionMercancia = new TipoAvionMercancia(marca, modelo, dimension, autonomia,
                controlTemperatura, mercanciaPeligrosa, maxCarga);
        addAvionMercancia(tipoAvionMercancia);
        return tipoAvionMercancia;
    }

    /**
     * Da de alta una terminal de pasajeros y la agrega al sistema.
     *
     * @param codigo         Codigo de la terminal.
     * @param maxPlazas      Numero maximo de plazas.
     * @param aforoMax       Aforo maximo permitido.
     * @param puertaEmbarque Lista de puertas de embarque asociadas.
     * @param rangoTiempo    Rango de tiempo asociado.
     * @return El objeto TerminalPasajero creado.
     */
    public TerminalPasajero darAltaTerminalPasajero(String codigo, int maxPlazas, int aforoMax,
            List<PuertaEmbarque> puertaEmbarque, RangoTiempo rangoTiempo) {
        TerminalPasajero terminalPasajero = new TerminalPasajero(codigo, maxPlazas, aforoMax, puertaEmbarque,
                rangoTiempo);
        addTerminalPasajero(terminalPasajero);
        return terminalPasajero;
    }

    /**
     * Da de alta una terminal de mercancia y la agrega al sistema.
     *
     * @param codigo       Codigo de la terminal.
     * @param maxPlazas    Numero maximo de plazas.
     * @param aparcamiento Zona de aparcamiento asociada.
     * @param rangoTiempo  Rango de tiempo asociado.
     * @return El objeto TerminalMercancia creado.
     */
    public TerminalMercancia darAltaTerminalMercancia(String codigo, int maxPlazas, List<ZonaAparcamiento> aparcamiento,
            RangoTiempo rangoTiempo) {
        TerminalMercancia terminalMercancia = new TerminalMercancia(codigo, maxPlazas, aparcamiento, rangoTiempo);
        addTerminalMercancia(terminalMercancia);
        return terminalMercancia;
    }

    /**
     * Da de alta una puerta de embarque y la agrega al sistema.
     *
     * @param codigo       Codigo de la puerta.
     * @param finger       Finger asociado.
     * @param aparcamiento Zona de aparcamiento asociada.
     * @return El objeto PuertaEmbarque creado.
     */
    public PuertaEmbarque darAltaPuertaEmbarque(String codigo, Finger finger, List<ZonaAparcamiento> aparcamiento) {
        PuertaEmbarque puertaEmbarque = new PuertaEmbarque(codigo, finger, aparcamiento);
        addPuertaEmbarque(puertaEmbarque);
        return puertaEmbarque;
    }

    /**
     * Da de alta una pista de despegue y la agrega al sistema.
     *
     * @param longitud   Longitud de la pista.
     * @param zonaEspera Zona de espera asociada.
     * @return El objeto PistaDespegue creado.
     */
    public PistaDespegue darAltaPistaDespegue(double longitud, ZonaEspera zonaEspera) {
        PistaDespegue pistaDespegue = new PistaDespegue(longitud, zonaEspera);
        addPistaDespegue(pistaDespegue);
        return pistaDespegue;
    }

    /**
     * Da de alta una pista de aterrizaje y la agrega al sistema.
     *
     * @param longitud Longitud de la pista.
     * @return El objeto PistaAterrizaje creado.
     */
    public PistaAterrizaje darAltaPistaAterrizaje(double longitud) {
        PistaAterrizaje pistaAterrizaje = new PistaAterrizaje(longitud);
        addPistaAterrizaje(pistaAterrizaje);
        return pistaAterrizaje;
    }

    /**
     * Da de alta un avion y lo agrega al sistema.
     *
     * @param anoCompra     Ano de compra del avion.
     * @param fechaRevision Fecha de revision.
     * @param tipo          Tipo de avion.
     * @return El objeto Avion creado.
     */
    public Avion darAltaAvion(int anoCompra, LocalDate fechaRevision, TipoAvion tipo) {
        Avion avion = new Avion(anoCompra, fechaRevision, tipo);
        addAvion(avion);
        return avion;
    }

    /**
     * Crea una aerolinea y la agrega al sistema.
     *
     * @param codigo Codigo de la aerolinea.
     * @param nombre Nombre de la aerolinea.
     * @return La aerolinea creada.
     * @throws RuntimeException si ya existe una aerolinea con el mismo codigo.
     */
    public Aerolinea darAltaAerolinea(String codigo, String nombre) {
        Aerolinea aerolinea = new Aerolinea(codigo, nombre);
        if (aerolineas.contains(aerolinea)) {
            throw new RuntimeException("Ya existe una aerolinea con ese codigo: " + codigo);
        }
        addAerolinea(aerolinea);
        return aerolinea;
    }

    /**
     * Crea un aeropuerto y lo agrega al sistema.
     *
     * @param nombre            Nombre del aeropuerto.
     * @param codigo            Codigo del aeropuerto.
     * @param ciudadMasCercana  Ciudad mas cercana.
     * @param pais              Pais donde se ubica.
     * @param distanciaCiudadKm Distancia a la ciudad en km.
     * @param direccion         Direccion (valor del enum).
     * @param diferenciaHoraria Diferencia horaria.
     * @param temporadas        Lista de temporadas.
     * @return El objeto Aeropuerto creado.
     */
    public Aeropuerto crearAeropuerto(String nombre, String codigo, String ciudadMasCercana, String pais,
            int distanciaCiudadKm, Direccion direccion, int diferenciaHoraria, List<Temporada> temporadas) {
        Aeropuerto aeropuerto = new Aeropuerto(nombre, codigo, ciudadMasCercana, pais, distanciaCiudadKm, direccion,
                diferenciaHoraria, temporadas);
        addAeropuerto(aeropuerto);
        return aeropuerto;
    }

    /**
     * Crea una aerolinea y la agrega al sistema.
     *
     * @param nombre Nombre de la aerolinea.
     * @param id     Identificador de la aerolinea.
     * @return El objeto Aerolinea creado.
     */
    public Aerolinea crearAerolinea(String nombre, String id) {
        Aerolinea aerolinea = new Aerolinea(nombre, id);
        addAerolinea(aerolinea);
        return aerolinea;
    }

    /**
     * Crea una notificacion y la agrega al sistema.
     *
     * @param notificacion Texto de la notificacion.
     * @param fecha        Fecha de la notificacion.
     * @param hora         Hora de la notificacion.
     * @return El objeto Notificacion creado.
     */
    public Notificacion crearNotificacion(String notificacion, LocalDate fecha, LocalTime hora) {
        Notificacion notificacion1 = new Notificacion(notificacion, fecha, hora);
        addNotificacion(notificacion1);
        return notificacion1;
    }

    /**
     * Da de alta un nuevo descuento.
     *
     * @param descripcion    Descripcion del descuento.
     * @param fechaInicio    Fecha de inicio del descuento.
     * @param fechaFin       Fecha de fin del descuento.
     * @param porcentaje     Porcentaje de descuento.
     * @param condicion      Condicion para aplicar el descuento.
     * @param aplicableTotal Indica si el descuento se aplica al total de la
     *                       factura.
     * @return El objeto Descuento creado.
     */
    public Descuento darAltaDescuento(String descripcion, LocalDate fechaInicio, LocalDate fechaFin, double porcentaje,
            CondicionDescuento condicion, boolean aplicableTotal) {

        Descuento descuento = new Descuento(descripcion, fechaInicio, fechaFin, porcentaje, condicion, aplicableTotal);
        addDescuento(descuento);

        for (Operador operador : getOperadores()) {
            operador.addNotificacion(crearNotificacion("Se ha dado de alta el descuento " + descuento.getDescripcion()
                    + ".\nEste descuento estará disponible del día " + descuento.getFechaInicio() + " al "
                    + descuento.getFechaFin() + ".", LocalDate.now(), LocalTime.now()));
        }
        return descuento;
    }

    /**
     * Agrega un descuento a la lista de descuentos del sistema.
     * 
     * @param descuento el descuento a agregar.
     */
    public void addDescuento(Descuento descuento) {
        descuentos.add(descuento);
    }

    /**
     * Devuelve una lista no modificable que contiene todos los descuentos dados
     * de alta en el sistema.
     *
     * @return lista de descuentos del sistema.
     */
    public List<Descuento> getDescuentos() {
        return Collections.unmodifiableList(descuentos);
    }

    /**
     * Define un nuevo periodo para el descuento especificado.
     *
     * @param descuento   El descuento al que se le va a definir un nuevo periodo.
     * @param fechaInicio La nueva fecha de inicio del periodo de descuento.
     * @param fechaFin    La nueva fecha de fin del periodo de descuento.
     * @throws IllegalStateException si el periodo actual está vigente y no se puede
     *                               modificar.
     */

    public void definirNuevoPeriodoDescuento(Descuento descuento, LocalDate fechaInicio, LocalDate fechaFin)
            throws IllegalStateException {
        descuento.setNuevoPeriodo(new PeriodoDescuento(fechaInicio, fechaFin));

        for (Operador operador : getOperadores()) {
            operador.addNotificacion(crearNotificacion(
                    "Se ha modificado el periodo del descuento: " + descuento.getDescripcion()
                            + ".\nEste descuento estará disponible a partir de ahora desde el día "
                            + descuento.getFechaInicio() + " al " + descuento.getFechaFin() + ".",
                    LocalDate.now(), LocalTime.now()));
        }
    }

    /**
     * Elimina el periodo de descuento del objeto Descuento.
     * 
     * @param descuento el descuento al que se le va a eliminar el periodo.
     * @throws IllegalStateException si el periodo actual está vigente y no se puede
     *                               eliminar.
     */
    public void eliminarPeriodoDescuento(Descuento descuento) throws IllegalStateException {
        descuento.eliminarPeriodo();

        for (Operador operador : getOperadores()) {
            operador.addNotificacion(crearNotificacion("Se ha eliminado el descuento: " + descuento.getDescripcion()
                    + ".\nEste descuento ya no estará disponible.", LocalDate.now(), LocalTime.now()));
        }
    }

    /**
     * Procesa el pago de una factura utilizando el sistema de cobro por tarjeta.
     *
     * @param numeroTarjeta El número de la tarjeta de crédito a utilizar para el
     *                      pago.
     * @param factura       La factura que se va a pagar.
     * @return true si el pago se realizó con éxito, false si el número de tarjeta
     *         no es válido.
     * @throws InvalidCardNumberException        Si el número de tarjeta es
     *                                           inválido.
     * @throws FailedInternetConnectionException Si hay un fallo en la conexión a
     *                                           internet.
     * @throws OrderRejectedException            Si el cobro es rechazado por el
     *                                           sistema.
     */

    public boolean pagarFactura(String numeroTarjeta, Factura factura)
            throws InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException {

        if (!TeleChargeAndPaySystem.isValidCardNumber(numeroTarjeta)) {
            System.out.println("El numero de la tarjeta no es valido");
            return false;
        }

        TeleChargeAndPaySystem.charge(numeroTarjeta, "Pago Factura " + factura.getInvoiceIdentifier(),
                factura.getPrice(), true);
        System.out.println("Cobro realizado con éxito.");

        factura.setPagada(true);

        return true;
    }

    /**
     * Lanza el pago de una aerolínea para el mes actual. Genera una factura con
     * todos los vuelos de ese mes y le aplica los descuentos correspondientes.
     * 
     * @param aerolinea La aerolínea a la que se le va a lanzar el pago.
     * @return true si se ha podido lanzar el pago con éxito, false en caso
     *         contrario.
     */
    public boolean lanzarPago(Aerolinea aerolinea) {
        Month mesActual = LocalDate.now().getMonth();
        List<IResourceUsageInfo> historialUso = new ArrayList<>();

        List<VueloProgramado> vuelosProgramados = this.getVuelosProgramados().values().stream()
                .filter(v -> v.getAerolinea().equals(aerolinea)).collect(Collectors.toList());

        double recargo = 0.0;
        for (VueloProgramado vueloProgramado : vuelosProgramados) {
            List<Vuelo> vuelos = vueloProgramado.getVuelos().stream()
                    .filter(v -> v.getFecha().getMonth().equals(mesActual)).collect(Collectors.toList());
            recargo += vueloProgramado.getRecargo();

            for (Vuelo vuelo : vuelos) {
                historialUso.addAll(vuelo.getHistorialUso());
            }
        }

        Factura factura = new Factura(aerolinea, LocalDate.now(), recargo, historialUso, vuelos.size());

        factura.aplicarDescuentos(this.descuentos);

        factura.generarFacturaPDF();

        for (Operador operador : getOperadores()) {
            if (operador.getAerolinea().equals(aerolinea)) {
                operador.addNotificacion(crearNotificacion("Ha recibido solicitud del pago del mes (" + mesActual + ")",
                        LocalDate.now(), LocalTime.now()));
            }
        }

        this.facturas.add(factura);
        return true;
    }

    /**
     * Notifica al gestor agregando la notificacion a su lista.
     *
     * @param notificacion La notificacion a enviar.
     * @param vuelo        El vuelo al que se le va a enviar la notificacion.
     * @param cambioEstado El cambio de estado del vuelo.
     * @return true si se notifico correctamente.
     */
    public boolean notificarGestor(Notificacion notificacion, Vuelo vuelo, String cambioEstado) {
        Gestor gestor = getGestor();
        if (gestor.getVuelosNotificaciones().contains(vuelo)
                && gestor.getCambiosEstadoNotificaciones().contains(cambioEstado)) {
            gestor.addNotificacion(notificacion);
        }

        if (gestor.getCambiosEstadoNotificaciones().isEmpty() && gestor.getVuelosNotificaciones().isEmpty()) {
            gestor.addNotificacion(notificacion);
        }

        return true;
    }

    /**
     * Notifica a todos los operadores agregando la notificacion a cada uno.
     *
     * @param notificacion La notificacion a enviar.
     * @param vuelo        El vuelo al que se le va a enviar la notificacion.
     * @return true si se notifico correctamente a todos.
     */
    public boolean notificarOperadores(Notificacion notificacion, Vuelo vuelo) {
        List<Operador> operadores = getOperadores();
        for (Operador operador : operadores) {
            if (operador.getAerolinea().equals(vuelo.getVueloProgramado().getAerolinea())) {
                operador.addNotificacion(notificacion);
            }

        }
        return true;
    }

    /**
     * Notifica a todos los controladores agregando la notificacion a cada uno.
     *
     * @param notificacion La notificacion a enviar.
     * @param vuelo        El vuelo al que se le va a enviar la notificacion.
     * @return true si se notifico correctamente a todos.
     */
    public boolean notificarControladores(Notificacion notificacion, Vuelo vuelo) {
        List<Controlador> controladores = getControladores();
        for (Controlador controlador : controladores) {
            if (controlador.getTerminal().equals(vuelo.getTerminal())) {
                controlador.addNotificacion(notificacion);
            }

        }
        return true;
    }

    /**
     * Indica si se da senyal de aterrizaje, comprobando que la pista de aterrizaje
     * este libre.
     *
     * @param v El vuelo a evaluar.
     * @return true si la pista de aterrizaje esta libre; false en caso contrario.
     */
    public boolean darSenyalAterrizaje(Vuelo v) {
        PistaAterrizaje p = v.getPistaAterrizaje();
        if (p.getAvionEnPista() == null) {
            return true;
        }
        return false;
    }

    /**
     * Guarda la informacion del sistema en el archivo especificado.
     *
     * @param rutaArchivo Ruta del archivo donde se guardara la informacion.
     */
    public void guardarInformacion(String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la informacion del sistema desde el archivo especificado.
     *
     * @param rutaArchivo Ruta del archivo desde donde se cargara la informacion.
     * @return La instancia de SkyCompass cargada, o null si ocurre un error.
     */
    public static SkyCompass cargarInformacion(String rutaArchivo) {
        SkyCompass skyCompass = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            skyCompass = (SkyCompass) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return skyCompass;
    }

    /**
     * Importa aeropuertos desde un fichero con formato especifico, creando
     * instancias de Aeropuerto.
     *
     * @param fichero Ruta del fichero que contiene la informacion de aeropuertos.
     * @throws IOException Si ocurre un error al leer el fichero.
     */
    public void importarAeropuertosFichero(String fichero) throws IOException {
        List<Aeropuerto> nuevosAeropuertos = new ArrayList<>();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea = br.readLine(); // Salto de linea para omitir la cabecera

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                String nombre = campos[0];
                String codigo = campos[1];
                String ciudadCercana = campos[2];
                String pais = campos[3];
                int distanciaCiudadKm = Integer.parseInt(campos[4]);

                Direccion direccion = Direccion.valueOf(campos[5].toUpperCase());
                int diferenciaHoraria = Integer.parseInt(campos[6]);
                int numeroTemporadas = Integer.parseInt(campos[7]);

                List<Temporada> temporadas = new ArrayList<>();
                int indiceTemporada = 8;

                for (int i = 0; i < numeroTemporadas; i++) {

                    LocalDate fechaInicio = LocalDate.parse(campos[indiceTemporada] + "/2000", formatoFecha);
                    LocalDate fechaFin = LocalDate.parse(campos[indiceTemporada + 2] + "/2000", formatoFecha);

                    String[] horarioSplit = campos[indiceTemporada + 1].split("-");
                    LocalTime horaApertura = LocalTime.parse(horarioSplit[0], formatoHora);
                    LocalTime horaCierre = LocalTime.parse(horarioSplit[1], formatoHora);

                    Temporada temporada = new Temporada(fechaInicio, fechaFin, horaApertura, horaCierre);
                    temporadas.add(temporada);
                    indiceTemporada += 3;
                }
                Aeropuerto aeropuerto = new Aeropuerto(nombre, codigo, ciudadCercana, pais, distanciaCiudadKm,
                        direccion, diferenciaHoraria, temporadas);

                if (!aeropuerto.getNombre().equals(this.aeropuertoPrincipal.getNombre())
                        || !aeropuerto.getCodigo().equals(this.aeropuertoPrincipal.getCodigo())) {
                    nuevosAeropuertos.add(aeropuerto);
                }

            }
            this.aeropuertos.clear();
            this.aeropuertos.add(this.aeropuertoPrincipal);
            this.aeropuertos.addAll(nuevosAeropuertos);
        }
    }

    /**
     * Devuelve el aeropuerto principal del sistema.
     *
     * @return El objeto Aeropuerto que representa el aeropuerto principal.
     */

    public Aeropuerto getAeropuertoPrincipal() {
        return aeropuertoPrincipal;
    }

}