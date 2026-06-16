package vista;

import controlador.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle.Control;

import javax.swing.*;
import sistema.SkyCompass;

/**
 * Clase que representa la ventana principal del programa.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class VentanaPrincipal extends JFrame {
	public final static String BIENVENIDA = "Bienvenida";
	public final static String LOGIN = "Login";

	public final static String LOGINGESTOR = "Iniciar Sesión Gestor";
	public final static String PRINCIPALGESTOR = "PrincipalGestor";
	public final static String PERFILGESTOR = "PerfilGestor";
	public final static String DARALTAUSUARIO = "DarAltaUsuario";
	public final static String RESETEAR = "Resetear";
	public final static String FILTRARGESTOR = "FiltrarGestor";
	public final static String TABLAPROGRAMADOGESTOR = "TablaProgramadosGestor";
	public final static String SOLICITUDVUELO = "SolicitudVuelo";
	public final static String DARALTAELEMENTO = "DarAltaElemento";
	public final static String ELEMENTOSAEROPUERTOGESTOR = "ElementosAeropuertoGestor";
	public final static String LANZARPAGO = "LanzarPago";
	public final static String HISTORIALPAGOSGESTOR = "HistorialPagosGestor";
	public final static String ESTADISTICAGESTOR = "EstadisticaGestor";
	public final static String CONFIGURACIONGESTOR = "ConfiguracionGestor";
	public final static String NOTIFICACIONESGESTOR = "NotificacionesGestor";
	public final static String CONFIGURACIONNOTIFICACIONES = "ConfiguracionNotificaciones";
	public final static String CONFIGURACIONAEROPUERTOGESTOR = "ConfiguracionAeropuertoGestor";
	public final static String CARGAFICHEROAEROPUERTOS = "CargarFicheroAeropuertos";
	public final static String DARALTADESCUENTOS = "DarAltaDescuentos";
	public final static String HISTORIALDESCUENTOSGESTOR = "HistorialDescuentosGestor";
	public final static String CONFIGURACIONCOSTES = "ConfiguraciónCostes";
	public final static String TABLAFILTRARVUELOSGESTOR = "TablaFiltrarVuelosGestor";

	public final static String LOGINOPERADOR = "Iniciar Sesión Operador";
	public final static String PRINCIPALOPERADOR = "PrincipalOperador";
	public final static String PERFILOPERADOR = "PerfilOperador";
	public final static String FILTRAROPERADOR = "FiltrarOperador";
	public final static String TABLAPROGRAMADOOPERADOR = "TablaProgramadoOperador";
	public final static String SOLICITARVUELO = "SolicitarVuelo";
	public final static String DARALTAAVION = "DarAltaAvion";
	public final static String DARALTATIPOAVION = "DarAltaTipoAvion";
	public final static String ELEMENTOSAEROPUERTOOPERADOR = "ElementosAeropuertoOperador";
	public final static String REALIZARPAGO = "RealizarPago";
	public final static String HISTORIALPAGOSOPERADOR = "HistorialPagosOperador";
	public final static String ESTADISTICAOPERADOR = "EstadisticaOperador";
	public final static String NOTIFICACIONESOPERADOR = "NotificacionesOperador";
	public final static String HISTORIALDESCUENTOSOPERADOR = "HistorialDescuentosOperador";
	public final static String TABLAFILTRARVUELOSOPERADOR = "TablaFiltrarVuelosOperador";
	public final static String GESTIONESTADOVUELOOPERADOR = "GestionEstadoVueloOperador";

	public final static String LOGINCONTROLADOR = "Iniciar Sesión Controlador";
	public final static String PRINCIPALCONTROLADOR = "PrincipalControlador";
	public final static String PERFILCONTROLADOR = "PerfilControlador";
	public final static String FILTRARCONTROLADOR = "FiltrarControlador";
	public final static String TABLAPROGRAMADOCONTROLADOR = "TablaProgramadoControlador";
	public final static String GESTIONESTADOVUELOCONTROLADOR = "GestionEstadoVueloControlador";
	public final static String ELEMENTOSAEROPUERTOCONTROLADOR = "ElementosAeropuertoControlador";
	public final static String ASIGNAR = "Asignar";
	public final static String NOTIFICACIONESCONTROLADOR = "NotificacionesControlador";
	public final static String CONFIGURACIONDEFECTOCONTROLADOR = "ConfiguracionDefectoControlador";
	public final static String TABLAFILTRARVUELOSCONTROLADOR = "TablaFiltrarVuelosControlador";

	public static final String ESTADISTICA_VUELO = "estadistica_vuelo";

	private PanelBienvenida pantallaBienvenida;
	private PanelLogin pantallaLogin;

	private PanelLoginUsuario pantallaLoginGestor;
	private DarAltaUsuario pantallaDarAlta;
	private TablaElemento pantallaTablaElementoGestor;
	private DarAltaElemento pantallaDarAltaElemento;
	private Resetear pantallaResetear;
	private Filtrar pantallaFiltrarGestor;
	private SolicitudVuelos pantallaSolicitudesVuelos;
	private PrincipalGestor pantallaPrincipalGestor;

	private PanelLoginUsuario pantallaLoginOperador;
	private TablaElemento pantallaTablaElementoOperador;
	private SolicitarVuelo pantallaSolicitarVuelo;
	private Filtrar pantallaFiltrarOperador;
	private PrincipalOperador pantallaPrincipalOperador;

	private PanelLoginUsuario pantallaLoginControlador;
	private TablaElemento pantallaTablaElementoControlador;
	private Filtrar pantallaFiltrarControlador;
	private PrincipalControlador pantallaPrincipalControlador;

	private TablaVuelosParaFiltrar pantallaTablaFiltrarVuelosGestor;
	private TablaVuelosParaFiltrar pantallaTablaFiltrarVuelosOperador;
	private TablaVuelosParaFiltrar pantallaTablaFiltrarVuelosControlador;

	private TablaProgramado pantallaTablaProgramadoGestor;
	private TablaProgramado pantallaTablaProgramadoOperador;
	private TablaProgramado pantallaTablaProgramadoControlador;

	private ControlTablaPrincipal controlTablaPrincipalGestor;
	private ControlTablaPrincipal controlTablaPrincipalOperador;
	private ControlTablaPrincipal controlTablaPrincipalControlador;

	private ControlTablaVuelosHoy controlTablaVuelosHoyGestor;
	private ControlTablaVuelosHoy controlTablaVuelosHoyOperador;
	private ControlTablaVuelosHoy controlTablaVuelosHoyControlador;

	private ControladorNotificacionesOperador controlNotificacionesOperador;
	private ControladorNotificacionesGestor controlNotificacionesGestor;
	private ControladorNotificacionesControlador controlNotificacionesControlador;

	private ControlResetear controlResetear;
	private ControlRealizarPago controlRealizarPago;
	private ControlLanzarPago controlLanzarPago;
	
	private ControlHistorialPagosGestor controlHistorialPagosGestor;
	private ControlHistorialPagosOperador controlHistorialPagosOperador;

	private ControladorEstadoVueloControlador controladorEstadoVueloControlador;
	private ControladorEstadoVueloOperador controladorEstadoVueloOperador;

	private ControlHistorialDescuentosGestor controlHistorialDescuentosGestor;
	private ControlHistorialDescuentosOperador controlHistorialDescuentosOperador;

	private ControlSolicitudVuelos controlSolicitudVuelos;
	private ControlSolicitarVuelo controlSolicitarVuelo;

	private ControlDarAltaAvion controlDarAltaAvion;

	private CardLayout layout;
	private Container contenedor;

	/**
	 * Constructor de la clase VentanaPrincipal
	 * 
	 * @param name nombre de la ventana
	 */
	public VentanaPrincipal(String name) {
		// crear ventana con el nombre
		super(name);

		// obtener contenedor, asignar layout
		contenedor = this.getContentPane();
		layout = new CardLayout();
		contenedor.setLayout(layout);

		// modelo
		SkyCompass modelo = SkyCompass.cargarInformacion("./resources/datosSkyCompass.txt");

		/*
		 * SkyCompass modelo = SkyCompass.getInstance();
		 * modelo.crearGestor();
		 */

		// crear pantallas
		pantallaBienvenida = new PanelBienvenida(this);
		pantallaLogin = new PanelLogin(this);

		pantallaLoginGestor = new PanelLoginUsuario(this, LOGINGESTOR);
		Perfil pantallaPerfilGestor = new Perfil(this, modelo, PRINCIPALGESTOR);
		pantallaPrincipalGestor = new PrincipalGestor(this, pantallaPerfilGestor);
		pantallaDarAlta = new DarAltaUsuario(this);

		pantallaFiltrarGestor = new Filtrar(this, PRINCIPALGESTOR);
		pantallaTablaProgramadoGestor = new TablaProgramado(this, PRINCIPALGESTOR, modelo);
		pantallaSolicitudesVuelos = new SolicitudVuelos(this);
		pantallaDarAltaElemento = new DarAltaElemento(this);
		pantallaResetear = new Resetear(this);
		pantallaTablaElementoGestor = new TablaElemento(this, PRINCIPALGESTOR);
		LanzarPago pantallaLanzarPago = new LanzarPago(this);
		HistorialPagos pantallaHistorialPagosGestor = new HistorialPagos(this, PRINCIPALGESTOR);
		EstadisticasVuelos pantallaEstadisticaGestor = new EstadisticasVuelos(this);
		ConfiguracionesGestor pantallaConfiguracionGestor = new ConfiguracionesGestor();
		ControlConfiguracionGestor controlConfiguracionDefecto = new ControlConfiguracionGestor(this, modelo, pantallaConfiguracionGestor);
		ConfiguracionNotificaciones pantallaConfiguracionNotificaciones = new ConfiguracionNotificaciones(this, modelo);
		ConfiguracionMiAeropuerto pantallaConfiguracionMiAeropuerto = new ConfiguracionMiAeropuerto(modelo);
		CargarAeropuertos pantallaCargarAeropuertos = new CargarAeropuertos();
		DarAltaDescuentos pantallaDarAltaDescuentos = new DarAltaDescuentos();
		HistorialDescuentos pantallaHistorialDescuentosGestor = new HistorialDescuentos(this);
		ConfiguracionCostes pantallaConfiguracionCostes = new ConfiguracionCostes();
		pantallaTablaFiltrarVuelosGestor = new TablaVuelosParaFiltrar(this, PRINCIPALGESTOR);

		pantallaLoginOperador = new PanelLoginUsuario(this, LOGINOPERADOR);
		Perfil pantallaPerfilOperador = new Perfil(this, modelo, PRINCIPALOPERADOR);
		pantallaPrincipalOperador = new PrincipalOperador(this, pantallaPerfilOperador);
		pantallaFiltrarOperador = new Filtrar(this, PRINCIPALOPERADOR);
		pantallaTablaProgramadoOperador = new TablaProgramado(this, PRINCIPALOPERADOR, modelo);
		pantallaSolicitarVuelo = new SolicitarVuelo(this);
		DarAltaAvion pantallaDarAltaAvion = new DarAltaAvion();
		DarAltaTipoAvion pantallaDarAltaTipoAvion = new DarAltaTipoAvion(this);
		pantallaTablaElementoOperador = new TablaElemento(this, PRINCIPALOPERADOR);
		RealizarPago pantallaRealizarPago = new RealizarPago(this);
		HistorialPagos pantallaHistorialPagosOperador = new HistorialPagos(this, PRINCIPALOPERADOR);
		EstadisticasVuelos pantallaEstadisticaOperador = new EstadisticasVuelos(this);
		HistorialDescuentos pantallaHistorialDescuentosOperador = new HistorialDescuentos(this);
		pantallaTablaFiltrarVuelosOperador = new TablaVuelosParaFiltrar(this, PRINCIPALOPERADOR);
		GestionEstadoVueloOperador pantallaVueloEstadoOperador = new GestionEstadoVueloOperador();

		pantallaLoginControlador = new PanelLoginUsuario(this, LOGINCONTROLADOR);
		Perfil pantallaPerfilControlador = new Perfil(this, modelo, PRINCIPALCONTROLADOR);
		pantallaPrincipalControlador = new PrincipalControlador(this, pantallaPerfilControlador);
		pantallaTablaProgramadoControlador = new TablaProgramado(this, PRINCIPALCONTROLADOR, modelo);
		pantallaTablaElementoControlador = new TablaElemento(this, PRINCIPALCONTROLADOR);
		GestionEstadoVueloControlador pantallaVueloEstadoControlador = new GestionEstadoVueloControlador();
		pantallaFiltrarControlador = new Filtrar(this, PRINCIPALCONTROLADOR);
		pantallaTablaFiltrarVuelosControlador = new TablaVuelosParaFiltrar(this, PRINCIPALCONTROLADOR);

		// crear controladores
		ControlBienvenida controlBienvenida = new ControlBienvenida(this);
		ControlLogin controlLogin = new ControlLogin(this);

		ControlLoginUsuario controlLoginGestor = new ControlLoginUsuario(this, modelo, pantallaLoginGestor);
		ControlDarAltaUsuario controlDarAltaUsuario = new ControlDarAltaUsuario(this, modelo);
		controlResetear = new ControlResetear(this, modelo, pantallaResetear);
		ControlTablaElementoGestor controlTablaElementoGestor = new ControlTablaElementoGestor(this, modelo,
				pantallaTablaElementoGestor);
		ControlDarAltaElemento controlDarAltaElemento = new ControlDarAltaElemento(this, modelo);
		ControladorFiltrar controlFiltrarGestor = new ControladorFiltrar(this, pantallaFiltrarGestor,
				pantallaTablaFiltrarVuelosGestor, modelo, TABLAFILTRARVUELOSGESTOR);
		controlSolicitudVuelos = new ControlSolicitudVuelos(this, modelo);
		NotificacionesGestor notificacionesGestor = new NotificacionesGestor(this);
		controlNotificacionesGestor = new ControladorNotificacionesGestor(
				notificacionesGestor, modelo);
		controlTablaPrincipalGestor = new ControlTablaPrincipal(modelo, pantallaPrincipalGestor.getTablaPrincipal());
		controlTablaVuelosHoyGestor = new ControlTablaVuelosHoy(modelo, pantallaPrincipalGestor.getTablaVuelosHoy());
		ControlConfiguracionMiAeropuerto controlConfiguracionMiAeropuerto = new ControlConfiguracionMiAeropuerto(this,
				modelo, pantallaConfiguracionMiAeropuerto);
		controlLanzarPago = new ControlLanzarPago(this, modelo, pantallaLanzarPago);
		controlHistorialPagosGestor = new ControlHistorialPagosGestor(this, modelo,
				pantallaHistorialPagosGestor);
		ControlCargarAeropuertos controlCargarAeropuertos = new ControlCargarAeropuertos(this, modelo, pantallaCargarAeropuertos);
		ControlDarAltaDescuento controlDarAltaDescuento = new ControlDarAltaDescuento(this, modelo, pantallaDarAltaDescuentos);
		controlHistorialDescuentosGestor = new ControlHistorialDescuentosGestor(this, modelo, pantallaHistorialDescuentosGestor);
		ControlConfiguracionCostes controlConfiguracionCostes = new ControlConfiguracionCostes(this, modelo, pantallaConfiguracionCostes);
		ControladorEstadisticaVuelo controlEstadisticasGestor = new ControladorEstadisticaVuelo(this, pantallaEstadisticaGestor, modelo, PRINCIPALGESTOR);
		ControlConfiguracionNotificaciones controlConfiguracionNotificaciones = new ControlConfiguracionNotificaciones(this, pantallaConfiguracionNotificaciones, modelo);

		ControlLoginUsuario controlLoginOperador = new ControlLoginUsuario(this, modelo, pantallaLoginOperador);
		ControlTablaElementoOperador controlTablaElementoOperador = new ControlTablaElementoOperador(this, modelo,
				pantallaTablaElementoOperador);
		controlSolicitarVuelo = new ControlSolicitarVuelo(this, modelo);
		ControladorFiltrar controlFiltrarOperador = new ControladorFiltrar(this, pantallaFiltrarOperador,
				pantallaTablaFiltrarVuelosOperador, modelo, TABLAFILTRARVUELOSOPERADOR);
		NotificacionesOperador notificacionesOperador = new NotificacionesOperador(this);
		controlNotificacionesOperador = new ControladorNotificacionesOperador(
				notificacionesOperador, modelo);
		controlTablaPrincipalOperador = new ControlTablaPrincipal(modelo,
				pantallaPrincipalOperador.getTablaPrincipal());
		controlTablaVuelosHoyOperador = new ControlTablaVuelosHoy(modelo,
				pantallaPrincipalOperador.getTablaVuelosHoy());
		controlRealizarPago = new ControlRealizarPago(this, modelo, pantallaRealizarPago);
		controlHistorialPagosOperador = new ControlHistorialPagosOperador(this, modelo,
				pantallaHistorialPagosOperador);
		controlHistorialDescuentosOperador = new ControlHistorialDescuentosOperador(this, modelo, pantallaHistorialDescuentosOperador);
		ControlDarAltaTipoAvion controlDarAltaTipoAvion = new ControlDarAltaTipoAvion(this, modelo, pantallaDarAltaTipoAvion);
		controlDarAltaAvion = new ControlDarAltaAvion(this, modelo, pantallaDarAltaAvion);
		ControladorEstadisticaVuelo controlEstadisticasOperador = new ControladorEstadisticaVuelo(this, pantallaEstadisticaOperador, modelo, PRINCIPALOPERADOR);
		ControladorEstadoVueloOperador controlVueloEstadoOperador = new ControladorEstadoVueloOperador(this,
				pantallaVueloEstadoOperador, modelo, PRINCIPALOPERADOR);

		ControlLoginUsuario controlLoginControlador = new ControlLoginUsuario(this, modelo, pantallaLoginControlador);
		ControladorEstadoVueloControlador controlVueloEstadoControlador = new ControladorEstadoVueloControlador(this,
				pantallaVueloEstadoControlador, modelo, PRINCIPALCONTROLADOR);
		ControlTablaElementoControlador controlTablaElementoControlador = new ControlTablaElementoControlador(this,
				modelo, pantallaTablaElementoControlador);
		ControladorFiltrar controlFiltrarControlador = new ControladorFiltrar(this, pantallaFiltrarControlador,
				pantallaTablaFiltrarVuelosControlador, modelo, TABLAFILTRARVUELOSCONTROLADOR);
		NotificacionesControlador notificacionesControlador = new NotificacionesControlador(this);
		controlNotificacionesControlador = new ControladorNotificacionesControlador(
				notificacionesControlador, modelo);
		controlTablaPrincipalControlador = new ControlTablaPrincipal(modelo,
				pantallaPrincipalControlador.getTablaPrincipal());
		controlTablaVuelosHoyControlador = new ControlTablaVuelosHoy(modelo,
				pantallaPrincipalControlador.getTablaVuelosHoy());

		// configurar las vistas con los controladores
		pantallaBienvenida.setControlador(controlBienvenida);
		pantallaLogin.setControlador(controlLogin);

		pantallaLoginGestor.setControlador(controlLoginGestor);
		pantallaDarAlta.setControlador(controlDarAltaUsuario);
		pantallaResetear.setControlador(controlResetear);
		pantallaTablaElementoGestor.setControlador(controlTablaElementoGestor);
		pantallaDarAltaElemento.setControlador(controlDarAltaElemento);
		pantallaFiltrarGestor.setControlador(controlFiltrarGestor);
		pantallaSolicitudesVuelos.setControlador(controlSolicitudVuelos);
		pantallaConfiguracionGestor.setControlador(controlConfiguracionDefecto);
		pantallaConfiguracionMiAeropuerto.setControlador(controlConfiguracionMiAeropuerto);
		pantallaLanzarPago.setControlador(controlLanzarPago);
		pantallaCargarAeropuertos.setControlador(controlCargarAeropuertos);
		pantallaDarAltaDescuentos.setControlador(controlDarAltaDescuento);
		pantallaHistorialDescuentosGestor.setControlador(controlHistorialDescuentosGestor);
		pantallaHistorialDescuentosGestor.addListSelectionListener(controlHistorialDescuentosGestor);
		pantallaConfiguracionCostes.setControlador(controlConfiguracionCostes);
		pantallaConfiguracionNotificaciones.setControlador(controlConfiguracionNotificaciones);
		pantallaVueloEstadoOperador.setControlador(controlVueloEstadoOperador);

		pantallaLoginOperador.setControlador(controlLoginOperador);
		pantallaTablaElementoOperador.setControlador(controlTablaElementoOperador);
		pantallaSolicitarVuelo.setControlador(controlSolicitarVuelo);
		pantallaFiltrarOperador.setControlador(controlFiltrarOperador);
		pantallaRealizarPago.setControlador(controlRealizarPago);
		pantallaHistorialDescuentosOperador.setControlador(controlHistorialDescuentosOperador);
		pantallaDarAltaTipoAvion.setControlador(controlDarAltaTipoAvion);
		pantallaDarAltaAvion.setControlador(controlDarAltaAvion);


		pantallaLoginControlador.setControlador(controlLoginControlador);
		pantallaTablaElementoControlador.setControlador(controlTablaElementoControlador);
		pantallaVueloEstadoControlador.setControlador(controlVueloEstadoControlador);
		pantallaFiltrarControlador.setControlador(controlFiltrarControlador);

		// añadir componentes al contenedor
		contenedor.add(pantallaBienvenida, BIENVENIDA);
		contenedor.add(pantallaLogin, LOGIN);

		// componenentes del gestor
		contenedor.add(pantallaLoginGestor, LOGINGESTOR);
		contenedor.add(pantallaPrincipalGestor, PRINCIPALGESTOR);
		contenedor.add(pantallaPerfilGestor, PERFILGESTOR);
		contenedor.add(pantallaDarAlta, DARALTAUSUARIO);
		contenedor.add(pantallaResetear, RESETEAR);
		contenedor.add(pantallaFiltrarGestor, FILTRARGESTOR);
		contenedor.add(pantallaTablaProgramadoGestor, TABLAPROGRAMADOGESTOR);
		contenedor.add(pantallaSolicitudesVuelos, SOLICITUDVUELO);
		contenedor.add(pantallaDarAltaElemento, DARALTAELEMENTO);
		contenedor.add(pantallaTablaElementoGestor, ELEMENTOSAEROPUERTOGESTOR);
		contenedor.add(pantallaLanzarPago, LANZARPAGO);
		contenedor.add(pantallaHistorialPagosGestor, HISTORIALPAGOSGESTOR);
		contenedor.add(pantallaEstadisticaGestor, ESTADISTICAGESTOR);
		contenedor.add(notificacionesGestor, NOTIFICACIONESGESTOR);
		contenedor.add(pantallaConfiguracionNotificaciones, CONFIGURACIONNOTIFICACIONES);
		contenedor.add(pantallaConfiguracionGestor, CONFIGURACIONGESTOR);
		contenedor.add(pantallaConfiguracionMiAeropuerto, CONFIGURACIONAEROPUERTOGESTOR);
		contenedor.add(pantallaCargarAeropuertos, CARGAFICHEROAEROPUERTOS);
		contenedor.add(pantallaDarAltaDescuentos, DARALTADESCUENTOS);
		contenedor.add(pantallaHistorialDescuentosGestor, HISTORIALDESCUENTOSGESTOR);
		contenedor.add(pantallaConfiguracionCostes, CONFIGURACIONCOSTES);
		contenedor.add(pantallaTablaFiltrarVuelosGestor, TABLAFILTRARVUELOSGESTOR);
		contenedor.add(pantallaTablaFiltrarVuelosGestor, TABLAFILTRARVUELOSGESTOR);

		// componentes del operador
		contenedor.add(pantallaLoginOperador, LOGINOPERADOR);
		contenedor.add(pantallaPrincipalOperador, PRINCIPALOPERADOR);
		contenedor.add(pantallaPerfilOperador, PERFILOPERADOR);
		contenedor.add(pantallaFiltrarOperador, FILTRAROPERADOR);
		contenedor.add(pantallaTablaProgramadoOperador, TABLAPROGRAMADOOPERADOR);
		contenedor.add(pantallaSolicitarVuelo, SOLICITARVUELO);
		contenedor.add(pantallaDarAltaAvion, DARALTAAVION);
		contenedor.add(pantallaDarAltaTipoAvion, DARALTATIPOAVION);
		contenedor.add(pantallaTablaElementoOperador, ELEMENTOSAEROPUERTOOPERADOR);
		contenedor.add(pantallaRealizarPago, REALIZARPAGO);
		contenedor.add(pantallaHistorialPagosOperador, HISTORIALPAGOSOPERADOR);
		contenedor.add(pantallaEstadisticaOperador, ESTADISTICAOPERADOR);
		contenedor.add(notificacionesOperador, NOTIFICACIONESOPERADOR);
		contenedor.add(pantallaHistorialDescuentosOperador, HISTORIALDESCUENTOSOPERADOR);
		contenedor.add(pantallaTablaFiltrarVuelosOperador, TABLAFILTRARVUELOSOPERADOR);
		contenedor.add(pantallaVueloEstadoOperador, GESTIONESTADOVUELOOPERADOR);

		// componentes del controlador
		contenedor.add(pantallaLoginControlador, LOGINCONTROLADOR);
		contenedor.add(pantallaPrincipalControlador, PRINCIPALCONTROLADOR);
		contenedor.add(pantallaPerfilControlador, PERFILCONTROLADOR);
		contenedor.add(pantallaFiltrarControlador, FILTRARCONTROLADOR);
		contenedor.add(pantallaTablaProgramadoControlador, TABLAPROGRAMADOCONTROLADOR);
		contenedor.add(pantallaVueloEstadoControlador, GESTIONESTADOVUELOCONTROLADOR);
		contenedor.add(pantallaTablaElementoControlador, ELEMENTOSAEROPUERTOCONTROLADOR);
		contenedor.add(notificacionesControlador, NOTIFICACIONESCONTROLADOR);
		contenedor.add(pantallaFiltrarControlador, FILTRARCONTROLADOR);
		contenedor.add(pantallaTablaFiltrarVuelosControlador, TABLAFILTRARVUELOSCONTROLADOR);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				modelo.guardarInformacion("./resources/datosSkyCompass.txt");
			}
		});

		layout.show(contenedor, BIENVENIDA);

		// mostrar ventana
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

	/**
	 * Muestra el panel dado por pármetro y actualiza
	 * los datos de los vuelos programados y hoy según corresponda.
	 * 
	 * @param toVisible nombre del panel a mostrar
	 */
	public void changeVisiblePanel(String toVisible) {
		
		if (toVisible.equals(VentanaPrincipal.NOTIFICACIONESOPERADOR)) {
			controlNotificacionesOperador.cargarNotificaciones();
		}

		if (toVisible.equals(VentanaPrincipal.NOTIFICACIONESGESTOR)) {
			controlNotificacionesGestor.cargarNotificaciones();
		}

		if (toVisible.equals(VentanaPrincipal.NOTIFICACIONESCONTROLADOR)) {
			controlNotificacionesControlador.cargarNotificaciones();
		}

		if (toVisible.equals(VentanaPrincipal.PRINCIPALGESTOR)) {
			controlTablaPrincipalGestor.actualizarVuelosProgramados();
			controlTablaVuelosHoyGestor.actualizarVuelosHoy();
		}
		if (toVisible.equals(VentanaPrincipal.PRINCIPALOPERADOR)) {
			controlTablaPrincipalOperador.actualizarVuelosProgramados();
			controlTablaVuelosHoyOperador.actualizarVuelosHoy();
		}
		if (toVisible.equals(VentanaPrincipal.PRINCIPALCONTROLADOR)) {
			controlTablaPrincipalControlador.actualizarVuelosProgramados();
			controlTablaVuelosHoyControlador.actualizarVuelosHoy();
		}

		if(toVisible.equals(VentanaPrincipal.RESETEAR)){
			controlResetear.actualizarOperadores();
		}

		if(toVisible.equals(VentanaPrincipal.REALIZARPAGO)){
			controlRealizarPago.update();
		}

		if(toVisible.equals(VentanaPrincipal.HISTORIALPAGOSGESTOR)){
			controlHistorialPagosGestor.actualizarPagosGestor();
		}

		if(toVisible.equals(VentanaPrincipal.HISTORIALPAGOSOPERADOR)){
			controlHistorialPagosOperador.actualizarPagosOperador();
		}

		if(toVisible.equals(VentanaPrincipal.LANZARPAGO)){
			controlLanzarPago.update();
		}

		if(toVisible.equals(VentanaPrincipal.SOLICITUDVUELO)){
			controlSolicitudVuelos.recargarTablaSolicitudes();
		}

		if(toVisible.equals(VentanaPrincipal.HISTORIALDESCUENTOSGESTOR)){
			controlHistorialDescuentosGestor.actualizarDescuentos();
		}

		if(toVisible.equals(VentanaPrincipal.HISTORIALDESCUENTOSOPERADOR)){
			controlHistorialDescuentosOperador.actualizarDescuentos();
		}

		if(toVisible.equals(VentanaPrincipal.SOLICITARVUELO)){
			controlSolicitarVuelo.actualizarComboBoxes();
		}

		if(toVisible.equals(VentanaPrincipal.DARALTAAVION)){
			controlDarAltaAvion.update();
		}

		if(toVisible.equals(VentanaPrincipal.TABLAPROGRAMADOGESTOR)){
			pantallaTablaProgramadoGestor.refrescarTabla();
		}

		if(toVisible.equals(VentanaPrincipal.TABLAPROGRAMADOOPERADOR)){
			pantallaTablaProgramadoOperador.refrescarTabla();
		}

		if(toVisible.equals(VentanaPrincipal.TABLAPROGRAMADOCONTROLADOR)){
			pantallaTablaProgramadoControlador.refrescarTabla();
		}

		layout.show(contenedor, toVisible);
	}

	/**
	 * Obtiene el panel de filtrado de vuelos para el controlador.
	 * 
	 * @return pantalla de filtrado de vuelos para el controlador
	 */
	public Filtrar getPantallaFiltrarControlador() {
		return pantallaFiltrarControlador;
	}


	/**
	 * Obtiene el panel de bienvenida.
	 * 
	 * @return el panel de bienvenida
	 */
	public PanelBienvenida getPanelBienvenida() {
		return pantallaBienvenida;
	}

	/**
	 * Obtiene el panel de login.
	 * 
	 * @return el panel de login
	 */
	public PanelLogin getPanelLogin() {
		return pantallaLogin;
	}

	/**
	 * Obtiene el panel de login del gestor.
	 * 
	 * @return el panel de login del gestor
	 */
	public PanelLoginUsuario getPanelLoginGestor() {
		return pantallaLoginGestor;
	}

	/**
	 * Obtiene el panel de login del operador.
	 * 
	 * @return el panel de login del operador
	 */
	public PanelLoginUsuario getPanelLoginOperador() {
		return pantallaLoginOperador;
	}

	/**
	 * Obtiene el panel de login del controlador.
	 * 
	 * @return el panel de login del controlador
	 */
	public PanelLoginUsuario getPanelLoginControlador() {
		return pantallaLoginControlador;
	}

	/**
	 * Obtiene el panel de dar de alta.
	 * 
	 * @return el panel de dar de alta
	 */
	public DarAltaUsuario getPanelDarAlta() {
		return pantallaDarAlta;
	}

	/**
	 * Obtiene la pantalla de la tabla de elementos del gestor.
	 * 
	 * @return la pantalla de la tabla de elementos del gestor
	 */
	public TablaElemento getTablaElementoGestor() {
		return pantallaTablaElementoGestor;
	}

	/**
	 * Obtiene la pantalla de la tabla de elementos del operador.
	 * 
	 * @return la pantalla de la tabla de elementos del operador
	 */
	public TablaElemento getTablaElementoOperador() {
		return pantallaTablaElementoOperador;
	}

	/**
	 * Obtiene la pantalla de la tabla de elementos del controlador.
	 * 
	 * @return la pantalla de la tabla de elementos del controlador
	 */
	public TablaElemento getTablaElementoControlador() {
		return pantallaTablaElementoControlador;
	}

	/**
	 * Obtiene el panel de dar de alta de elementos.
	 * 
	 * @return el panel de dar de alta de elementos
	 */
	public DarAltaElemento getPanelDarAltaElemento() {
		return pantallaDarAltaElemento;
	}

	/**
	 * Obtiene el panel de solicitar vuelo.
	 * 
	 * @return el panel de solicitar vuelo
	 */
	public SolicitarVuelo getPanelSolicitarVuelo() {
		return pantallaSolicitarVuelo;
	}

	/**
	 * Obtiene el panel de solicitudes de vuelos.
	 * 
	 * @return el panel de solicitudes de vuelos
	 */
	public SolicitudVuelos getPanelSolicitudVuelos() {
		return pantallaSolicitudesVuelos;
	}
}
