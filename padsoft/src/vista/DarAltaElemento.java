package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import aeropuerto.Direccion;
import aeropuerto.PuertaEmbarque;
import aeropuerto.Temporada;
import elementocoste.Finger;
import elementocoste.Plaza;
import elementocoste.ZonaAparcamiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

/**
 * Panel para dar de alta diferentes elementos del aeropuerto.
 *
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class DarAltaElemento extends JPanel {

    private String[] elementos = {
            "", "Aeropuerto", "Pista", "Terminal", "Puerta de embarque",
            "Aparcamiento", "Plaza", "Finger", "Hangar", "Aerolínea"
    };

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JComboBox<String> tipoComboBox;
    private JButton botonVolver;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JPanel buttonPanel;

    private JTextField campoIdAeropuerto;
    private JTextField campoNombreAeropuerto;
    private JTextField campoCiudadAeropuerto;
    private JTextField campoPaisAeropuerto;
    private JTextField campoDistanciaCiudadKm;
    private JComboBox<Direccion> comboDireccionAeropuerto;
    private JTextField campoDiferenciaHorariaAeropuerto;
    private JTextField campoFechaInicio;
    private JTextField campoFechaFin;
    private JTextField campoHoraApertura;
    private JTextField campoHoraCierre;
    private DefaultListModel<Temporada> temporadas;
    private JButton botonAgregarTemporada;

    private JTextField campoNombreAerolinea;
    private JTextField campoIdAerolinea;

    private JTextField campoLongitudPista;
    private JComboBox<String> tipoPistaComboBox;

    private JTextField codigoTerminal;
    private JComboBox<String> tipoTerminalComboBox;
    private JTextField maxPlazasTerminal;
    private JTextField horaAperturaTerminal;
    private JTextField horaCierreTerminal;
    private JLabel zonasLabel;
    private JScrollPane scrollZonas;
    private JPanel zonasCheckboxPanel;
    private List<JCheckBox> zonasCheckboxes;
    private JLabel aforoLabel;
    private JPanel puertasCheckboxPanel;
    private List<JCheckBox> puertasCheckboxes;
    private JLabel puertasLabel;
    private JTextField aforoTerminal;
    private JScrollPane scrollPuertas;
    private Map<JCheckBox, PuertaEmbarque> mapaPuertas;
    private Map<JCheckBox, ZonaAparcamiento> mapaZonas;

    private JTextField campoCodigoPuerta;
    private JPanel panelPuertaEmbarque;
    private JPanel fingersRadioButtonsPanel;
    private JScrollPane scrollFingers;
    private List<JRadioButton> fingersRadioButtons;
    private Map<JRadioButton, Finger> mapaFingers;
    private ButtonGroup grupoFingers;
    private JPanel aparcamientosCheckboxPanel;
    private JScrollPane scrollAparcamientos;
    private List<JCheckBox> aparcamientosCheckboxes;

    private JTextField codigoZonaAparcamiento;
    private JTextField capacidadMaximaZona;
    private JTextField costeHoraZona;
    private JLabel plazasLabel;
    private JPanel plazasCheckboxPanel;
    private JScrollPane scrollPlazas;
    private List<JCheckBox> plazasCheckboxes;
    private Map<JCheckBox, Plaza> mapaPlazas;

    private JTextField campoAnchoPista;
    private JTextField campoLargoPista;

    private JTextField campoCodigoFinger;
    private JTextField campoAlturaMaxFinger;
    private JTextField campoCosteFinger;
    private JRadioButton radioSiFinger;
    private JRadioButton radioNoFinger;
    private ButtonGroup grupoPuertaDoble;

    private JTextField maxPlazasHangar;
    private JTextField costeHoraHangar;
    private JTextField anchoHangar;
    private JTextField largoHangar;
    private JTextField altoHangar;
    private JComboBox<String> tipoHangarComboBox;
    private JLabel labelMercanciasPeligrosas;
    private JPanel panelMercanciasPeligrosas;
    private JRadioButton radioSiPeligrosas;
    private JRadioButton radioNoPeligrosas;
    private ButtonGroup grupoPeligrosas;

    /**
     * Construye el panel de alta de elementos y configura la navegación.
     *
     * @param frame la ventana principal donde se visualiza este panel
     */
    public DarAltaElemento(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");
        botonVolver.addActionListener(e -> frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR));
        topPanel.add(botonVolver);

        JPanel selectorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tipoComboBox = new JComboBox<>(elementos);
        selectorPanel.add(new JLabel("Seleccione elemento:"));
        selectorPanel.add(tipoComboBox);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        buttonPanel = new JPanel(new FlowLayout());
        aceptarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");
        buttonPanel.add(aceptarButton);
        buttonPanel.add(cancelarButton);

        contentPanel.add(new JPanel(), "");
        contentPanel.add(panelAeropuerto(), "Aeropuerto");
        contentPanel.add(panelPista(), "Pista");
        contentPanel.add(panelTerminal(), "Terminal");
        contentPanel.add(panelPuertaEmbarque(), "Puerta de embarque");
        contentPanel.add(panelAparcamiento(), "Aparcamiento");
        contentPanel.add(panelPlaza(), "Plaza");
        contentPanel.add(panelFinger(), "Finger");
        contentPanel.add(panelHangar(), "Hangar");
        contentPanel.add(panelAerolinea(), "Aerolínea");

        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(selectorPanel);
        centerPanel.add(contentPanel);
        centerPanel.add(buttonPanel);
        buttonPanel.setVisible(false);
        this.add(centerPanel, BorderLayout.CENTER);

    }

    /**
     * Devuelve el administrador de tarjetas para cambiar formularios.
     *
     * @return el CardLayout usado en este panel
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Devuelve el panel que contiene cada formulario de elemento.
     *
     * @return el panel con CardLayout
     */
    public JPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Registra el controlador para todos los componentes interactivos.
     *
     * @param e el ActionListener que maneja eventos de UI
     */
    public void setControlador(ActionListener e) {
        tipoComboBox.addActionListener(e);
        botonVolver.addActionListener(e);
        botonAgregarTemporada.addActionListener(e);
        aceptarButton.addActionListener(e);
        cancelarButton.addActionListener(e);
        tipoTerminalComboBox.addActionListener(e);
        tipoHangarComboBox.addActionListener(e);
    }

    /**
     * Obtiene el tipo de elemento seleccionado.
     *
     * @return la cadena con el nombre del elemento
     */
    public String getSelectedElemento() {
        return (String) tipoComboBox.getSelectedItem();
    }

    /**
     * Genera un panel de formulario usando etiquetas y campos.
     *
     * @param labels las etiquetas de cada campo
     * @param fields los componentes de entrada correspondientes
     * @return un JPanel con GridBagLayout
     */
    private JPanel createGridForm(String[] labels, JComponent[] fields) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }
        return panel;
    }

    /**
     * Devuelve el panel inferior con los botones de Aceptar y Cancelar.
     *
     * @return el panel con los botones de Aceptar y Cancelar
     */
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * Construye y devuelve el formulario para Aeropuerto.
     *
     * @return el panel con campos de Aeropuerto
     */
    private JPanel panelAeropuerto() {
        campoIdAeropuerto = new JTextField(10);
        campoNombreAeropuerto = new JTextField(20);
        campoCiudadAeropuerto = new JTextField(20);
        campoPaisAeropuerto = new JTextField(20);
        campoDistanciaCiudadKm = new JTextField(5);
        comboDireccionAeropuerto = new JComboBox<>(Direccion.values());
        campoDiferenciaHorariaAeropuerto = new JTextField(5);

        campoFechaInicio = new JTextField(10);
        campoFechaFin = new JTextField(10);
        campoHoraApertura = new JTextField(5);
        campoHoraCierre = new JTextField(5);

        temporadas = new DefaultListModel<>();
        JList<Temporada> listaTemporadas = new JList<>(temporadas);
        JScrollPane scrollTemporadas = new JScrollPane(listaTemporadas);

        botonAgregarTemporada = new JButton("Agregar Temporada");

        String[] labels = {
                "ID:", "Nombre:", "Ciudad más cercana:", "País:",
                "Distancia:", "Dirección:", "Diferencia horaria:",
                "Fecha inicio temporada:", "Fecha fin temporada:",
                "Hora apertura temporada:", "Hora cierre temporada:"
        };

        JComponent[] fields = {
                campoIdAeropuerto, campoNombreAeropuerto, campoCiudadAeropuerto,
                campoPaisAeropuerto, campoDistanciaCiudadKm, comboDireccionAeropuerto, campoDiferenciaHorariaAeropuerto,
                campoFechaInicio, campoFechaFin, campoHoraApertura, campoHoraCierre
        };

        JPanel form = createGridForm(labels, fields);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.add(form);
        centro.add(botonAgregarTemporada);
        centro.add(new JLabel("Temporadas añadidas:"));
        centro.add(scrollTemporadas);

        panel.add(centro, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Construye y devuelve el formulario para Aerolínea.
     *
     * @return el panel con campos de Aerolínea
     */
    private JPanel panelAerolinea() {
        campoNombreAerolinea = new JTextField(20);
        campoIdAerolinea = new JTextField(10);

        String[] labels = { "Nombre:", "ID:" };
        JComponent[] fields = { campoNombreAerolinea, campoIdAerolinea };

        JPanel panel = createGridForm(labels, fields);

        return panel;
    }

    /**
     * Construye y devuelve el formulario para Pista.
     *
     * @return el panel con campos de Pista
     */
    private JPanel panelPista() {
        campoLongitudPista = new JTextField(10);
        tipoPistaComboBox = new JComboBox<>(new String[] { "", "Despegue", "Aterrizaje" });

        String[] labels = { "Longitud (m):", "Tipo de pista:" };
        JComponent[] fields = { campoLongitudPista, tipoPistaComboBox };

        return createGridForm(labels, fields);
    }

    /**
     * Construye y devuelve un panel de formulario para configurar los detalles de
     * la terminal.
     * El panel incluye campos para el código de la terminal, tipo, número máximo
     * de plazas, horas de apertura y cierre, y opcionalmente muestra campos para
     * zonas de aparcamiento, aforo máximo y puertas de embarque según el tipo de
     * terminal seleccionado. La disposición se gestiona usando GridBagLayout para
     * una alineación y espaciado flexible de los componentes.
     *
     * @return JPanel con los campos de configuración de la terminal
     */

    private JPanel panelTerminal() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        codigoTerminal = new JTextField(15);
        panel.add(codigoTerminal, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        tipoTerminalComboBox = new JComboBox<>(new String[] { "", "Pasajeros", "Mercancías" });
        panel.add(tipoTerminalComboBox, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Plazas máximas:"), gbc);
        gbc.gridx = 1;
        maxPlazasTerminal = new JTextField(15);
        panel.add(maxPlazasTerminal, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Hora Apertura:"), gbc);
        gbc.gridx = 1;
        horaAperturaTerminal = new JTextField(15);
        panel.add(horaAperturaTerminal, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Hora Cierre:"), gbc);
        gbc.gridx = 1;
        horaCierreTerminal = new JTextField(15);
        panel.add(horaCierreTerminal, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        zonasLabel = new JLabel("Zonas de aparcamiento:");
        panel.add(zonasLabel, gbc);
        gbc.gridx = 1;
        zonasLabel.setVisible(false);
        y++;

        zonasCheckboxPanel = new JPanel();
        zonasCheckboxPanel.setLayout(new BoxLayout(zonasCheckboxPanel, BoxLayout.Y_AXIS));
        zonasCheckboxes = new ArrayList<>();

        scrollZonas = new JScrollPane(zonasCheckboxPanel);
        scrollZonas.setPreferredSize(new Dimension(200, 100));
        scrollZonas.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(scrollZonas, gbc);

        gbc.gridwidth = 1;
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        aforoLabel = new JLabel("Aforo máximo:");
        panel.add(aforoLabel, gbc);
        gbc.gridx = 1;
        aforoTerminal = new JTextField(15);
        panel.add(aforoTerminal, gbc);
        aforoLabel.setVisible(false);
        aforoTerminal.setVisible(false);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        puertasLabel = new JLabel("Puertas de embarque:");
        panel.add(puertasLabel, gbc);
        gbc.gridx = 1;
        puertasLabel.setVisible(false);
        y++;

        puertasCheckboxPanel = new JPanel();
        puertasCheckboxPanel.setLayout(new BoxLayout(puertasCheckboxPanel, BoxLayout.Y_AXIS));
        puertasCheckboxes = new ArrayList<>();

        scrollPuertas = new JScrollPane(puertasCheckboxPanel);
        scrollPuertas.setPreferredSize(new Dimension(200, 100));
        scrollPuertas.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(scrollPuertas, gbc);

        gbc.gridwidth = 1;
        y++;

        return panel;
    }

    /**
     * Crea un panel que contiene los campos para dar de alta una puerta de
     * embarque.
     * 
     * @return El panel con los campos para dar de alta una puerta de embarque.
     */
    private JPanel panelPuertaEmbarque() {
        panelPuertaEmbarque = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        panelPuertaEmbarque.add(new JLabel("Código de puerta:"), gbc);
        gbc.gridx = 1;
        campoCodigoPuerta = new JTextField(15);
        panelPuertaEmbarque.add(campoCodigoPuerta, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panelPuertaEmbarque.add(new JLabel("Fingers:"), gbc);
        y++;

        fingersRadioButtonsPanel = new JPanel();
        fingersRadioButtonsPanel.setLayout(new BoxLayout(fingersRadioButtonsPanel, BoxLayout.Y_AXIS));
        scrollFingers = new JScrollPane(fingersRadioButtonsPanel);
        scrollFingers.setPreferredSize(new Dimension(200, 80));
        panelPuertaEmbarque.add(scrollFingers, gbc);
        fingersRadioButtons = new ArrayList<>();
        mapaFingers = new HashMap<>();
        grupoFingers = new ButtonGroup();
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panelPuertaEmbarque.add(scrollFingers, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panelPuertaEmbarque.add(new JLabel("Zonas de aparcamiento:"), gbc);
        y++;

        aparcamientosCheckboxPanel = new JPanel();
        aparcamientosCheckboxPanel.setLayout(new BoxLayout(aparcamientosCheckboxPanel, BoxLayout.Y_AXIS));
        aparcamientosCheckboxes = new ArrayList<>();

        scrollAparcamientos = new JScrollPane(aparcamientosCheckboxPanel);
        scrollAparcamientos.setPreferredSize(new Dimension(200, 100));

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panelPuertaEmbarque.add(scrollAparcamientos, gbc);

        return panelPuertaEmbarque;
    }

    /**
     * Creates a panel for entering details of a new {@link ZonaAparcamiento}.
     * 
     * @return The panel.
     */
    private JPanel panelAparcamiento() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        codigoZonaAparcamiento = new JTextField(15);
        panel.add(codigoZonaAparcamiento, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Capacidad máxima:"), gbc);
        gbc.gridx = 1;
        capacidadMaximaZona = new JTextField(15);
        panel.add(capacidadMaximaZona, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Coste por hora (€):"), gbc);
        gbc.gridx = 1;
        costeHoraZona = new JTextField(15);
        panel.add(costeHoraZona, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        plazasLabel = new JLabel("Plazas disponibles:");
        panel.add(plazasLabel, gbc);
        gbc.gridx = 1;
        plazasLabel.setVisible(false);
        y++;

        plazasCheckboxPanel = new JPanel();
        plazasCheckboxPanel.setLayout(new BoxLayout(plazasCheckboxPanel, BoxLayout.Y_AXIS));
        plazasCheckboxes = new ArrayList<>();

        scrollPlazas = new JScrollPane(plazasCheckboxPanel);
        scrollPlazas.setPreferredSize(new Dimension(200, 100));

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(scrollPlazas, gbc);
        gbc.gridwidth = 1;
        y++;

        return panel;
    }

    /**
     * Construye y devuelve el formulario para Plaza.
     *
     * @return el panel con campos de Plaza
     */

    private JPanel panelPlaza() {
        campoAnchoPista = new JTextField(10);
        campoLargoPista = new JTextField(10);

        String[] labels = { "Ancho (m):", "Largo (m):" };
        JComponent[] fields = { campoAnchoPista, campoLargoPista };

        return createGridForm(labels, fields);
    }

    /**
     * Construye y devuelve el formulario para Finger.
     *
     * @return el panel con campos de Finger
     */
    private JPanel panelFinger() {
        campoCodigoFinger = new JTextField(10);
        campoAlturaMaxFinger = new JTextField(10);
        campoCosteFinger = new JTextField(10);

        grupoPuertaDoble = new ButtonGroup();
        radioSiFinger = new JRadioButton("Sí");
        radioNoFinger = new JRadioButton("No");
        grupoPuertaDoble.add(radioSiFinger);
        grupoPuertaDoble.add(radioNoFinger);

        JPanel panelPuertaDobleFinger = new JPanel();
        panelPuertaDobleFinger.add(radioSiFinger);
        panelPuertaDobleFinger.add(radioNoFinger);

        String[] labels = { "Codigo:", "Altura maxima:", "Coste por hora (€):", "Puerta doble:" };
        JComponent[] fields = { campoCodigoFinger, campoAlturaMaxFinger, campoCosteFinger, panelPuertaDobleFinger };

        JPanel panel = createGridForm(labels, fields);

        return panel;
    }

    /**
     * Construye y devuelve el formulario para Hangar.
     *
     * @return el panel con campos de Hangar
     */
    private JPanel panelHangar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        tipoHangarComboBox = new JComboBox<>(new String[] { "", "Pasajeros", "Mercancías" });
        panel.add(tipoHangarComboBox, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Ancho (m):"), gbc);
        gbc.gridx = 1;
        anchoHangar = new JTextField(15);
        panel.add(anchoHangar, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Largo (m):"), gbc);
        gbc.gridx = 1;
        largoHangar = new JTextField(15);
        panel.add(largoHangar, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Alto (m):"), gbc);
        gbc.gridx = 1;
        altoHangar = new JTextField(15);
        panel.add(altoHangar, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Máximo de plazas:"), gbc);
        gbc.gridx = 1;
        maxPlazasHangar = new JTextField(15);
        panel.add(maxPlazasHangar, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Coste por hora (€):"), gbc);
        gbc.gridx = 1;
        costeHoraHangar = new JTextField(15);
        panel.add(costeHoraHangar, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        labelMercanciasPeligrosas = new JLabel("¿Permite mercancías peligrosas?");
        labelMercanciasPeligrosas.setVisible(false);
        panel.add(labelMercanciasPeligrosas, gbc);

        gbc.gridx = 1;
        panelMercanciasPeligrosas = new JPanel();
        radioSiPeligrosas = new JRadioButton("Sí");
        radioNoPeligrosas = new JRadioButton("No");
        grupoPeligrosas = new ButtonGroup();
        grupoPeligrosas.add(radioSiPeligrosas);
        grupoPeligrosas.add(radioNoPeligrosas);
        panelMercanciasPeligrosas.add(radioSiPeligrosas);
        panelMercanciasPeligrosas.add(radioNoPeligrosas);
        panelMercanciasPeligrosas.setVisible(false);
        panel.add(panelMercanciasPeligrosas, gbc);
        y++;

        return panel;
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce el nombre del
     * aeropuerto.
     * 
     * @return nombre del aeropuerto.
     */
    public String getCampoNombreAeropuerto() {
        return campoNombreAeropuerto.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce el id del aeropuerto.
     * 
     * @return id del aeropuerto.
     */
    public String getCampoAeropuertoId() {
        return campoIdAeropuerto.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la ciudad del
     * aeropuerto.
     * 
     * @return la ciudad del aeropuerto.
     */
    public String getCampoCiudadAeropuerto() {
        return campoCiudadAeropuerto.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce el país del
     * aeropuerto.
     * 
     * @return el país del aeropuerto.
     */
    public String getCampoPaisAeropuerto() {
        return campoPaisAeropuerto.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la distancia en
     * kilómetros a la ciudad más cercana.
     * 
     * @return la distancia en kilómetros a la ciudad más cercana.
     */

    public String getCampoDistanciaCiudadKm() {
        return campoDistanciaCiudadKm.getText();
    }

    /**
     * Obtiene el valor del combo donde se selecciona la dirección del aeropuerto.
     * 
     * @return la dirección del aeropuerto.
     */
    public Direccion getCampoDireccionAeropuerto() {
        return (Direccion) comboDireccionAeropuerto.getSelectedItem();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la diferencia horaria
     * del aeropuerto con respecto al Tiempo Universal Coordinado (UTC).
     * 
     * @return la diferencia horaria en horas.
     */
    public String getCampoDiferenciaHorariaAeropuerto() {
        return campoDiferenciaHorariaAeropuerto.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la hora de apertura
     * del aeropuerto.
     * 
     * @return la hora de apertura del aeropuerto.
     */
    public LocalTime getCampoHoraInicio() {
        return LocalTime.parse(campoHoraApertura.getText());
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la hora de cierre del
     * aeropuerto.
     * 
     * @return la hora de cierre del aeropuerto.
     */
    public LocalTime getCampoHoraFin() {
        return LocalTime.parse(campoHoraCierre.getText());
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la fecha de inicio de
     * la temporada.
     * 
     * @return la fecha de inicio de la temporada.
     */

    public LocalDate getCampoFechaInicioTemporada() {
        return LocalDate.parse(campoFechaInicio.getText());
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la fecha de fin de la
     * temporada.
     * 
     * @return la fecha de fin de la temporada.
     */
    public LocalDate getCampoFechaFinTemporada() {
        return LocalDate.parse(campoFechaFin.getText());
    }

    /**
     * Devuelve el modelo de lista que contiene las temporadas de la lista de
     * temporadas.
     * 
     * @return el modelo de lista de temporadas.
     */
    public DefaultListModel<Temporada> getTemporadasListModel() {
        return temporadas;
    }

    /**
     * Convierte el modelo de lista de temporadas en una lista de temporadas.
     * 
     * @return la lista de temporadas.
     */
    public List<Temporada> getTemporadas() {
        List<Temporada> lista = new ArrayList<>();

        for (int i = 0; i < temporadas.size(); i++) {
            lista.add(temporadas.getElementAt(i));
        }

        return lista;
    }

    /**
     * Obtiene el nombre de la aerol&#237;nea que se ha introducido en el campo de
     * texto.
     * 
     * @return el nombre de la aerol&#237;nea.
     */
    public String getCampoNombreAerolinea() {
        return campoNombreAerolinea.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce el id de la
     * aerol&#237;nea.
     * 
     * @return el id de la aerol&#237;nea.
     */
    public String getCampoAerolineaId() {
        return campoIdAerolinea.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la longitud de la
     * pista.
     * 
     * @return el valor del campo de texto con la longitud de la pista.
     */
    public String getCampoLongitudPista() {
        return campoLongitudPista.getText();
    }

    /**
     * Obtiene el JComboBox con las opciones de tipo de pista.
     * 
     * @return el JComboBox con las opciones de tipo de pista.
     */
    public JComboBox<String> getTipoPistaComboBox() {
        return tipoPistaComboBox;
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce el código de la
     * terminal.
     * 
     * @return el código de la terminal.
     */
    public String getCodigoTerminal() {
        return codigoTerminal.getText();
    }

    /**
     * Obtiene el JComboBox con las opciones de tipo de terminal.
     * 
     * @return el JComboBox con las opciones de tipo de terminal.
     */
    public JComboBox<String> getTipoTerminalComboBox() {
        return tipoTerminalComboBox;
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce el número máximo de
     * plazas de la terminal.
     * 
     * @return el número máximo de plazas de la terminal.
     */
    public String getMaxPlazas() {
        return maxPlazasTerminal.getText();
    }

    /**
     * Obtiene el aforo de la terminal.
     * 
     * @return el aforo de la terminal.
     */
    public String getAforo() {
        return aforoTerminal.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la hora de apertura
     * de la terminal.
     * 
     * @return la hora de apertura de la terminal.
     */
    public String getHoraApertura() {
        return horaAperturaTerminal.getText();
    }

    /**
     * Obtiene el valor del campo de texto donde se introduce la hora de cierre
     * de la terminal.
     * 
     * @return la hora de cierre de la terminal.
     */

    public String getHoraCierre() {
        return horaCierreTerminal.getText();
    }

    /**
     * Obtiene el JTextField donde se introduce el aforo de la terminal.
     * 
     * @return el JTextField con el aforo de la terminal.
     */
    public JTextField getAforoTerminal() {
        return aforoTerminal;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Aforo:".
     * 
     * @return la etiqueta de aforo
     */
    public JLabel getAforoLabel() {
        return aforoLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Puertas:".
     * 
     * @return la etiqueta de puertas
     */
    public JLabel getPuertasLabel() {
        return puertasLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Zonas:".
     * 
     * @return la etiqueta de zonas
     */

    public JLabel getZonasLabel() {
        return zonasLabel;
    }

    /**
     * Devuelve el panel que contiene el JList de zonas en un JScrollPane.
     * 
     * @return el JScrollPane con el JList de zonas.
     */
    public JScrollPane getScrollZonas() {
        return scrollZonas;
    }

    /**
     * Devuelve el panel que contiene el JList de puertas en un JScrollPane.
     * 
     * @return el JScrollPane con el JList de puertas.
     */
    public JScrollPane getScrollPuertas() {
        return scrollPuertas;
    }

    /**
     * Establece las puertas de embarque que se muestran en el panel de
     * selecci n de puertas.
     * 
     * @param puertas la lista de puertas de embarque a mostrar.
     */
    public void setPuertasCheckboxes(List<PuertaEmbarque> puertas) {
        puertasCheckboxPanel.removeAll();
        puertasCheckboxes = new ArrayList<>();
        mapaPuertas = new HashMap<>();

        for (PuertaEmbarque puerta : puertas) {
            JCheckBox check = new JCheckBox(puerta.getCodigo());
            puertasCheckboxPanel.add(check);
            puertasCheckboxes.add(check);
            mapaPuertas.put(check, puerta);
        }

        puertasCheckboxPanel.revalidate();
        puertasCheckboxPanel.repaint();
    }

    /**
     * Establece las zonas de aparcamiento que se muestran en el panel de
     * selecci n de zonas.
     * 
     * @param zonas la lista de zonas de aparcamiento a mostrar.
     */
    public void setZonasCheckboxes(List<ZonaAparcamiento> zonas) {
        zonasCheckboxPanel.removeAll();
        zonasCheckboxes = new ArrayList<>();
        mapaZonas = new HashMap<>();

        for (ZonaAparcamiento zona : zonas) {
            JCheckBox check = new JCheckBox(zona.getCodigo());
            zonasCheckboxPanel.add(check);
            zonasCheckboxes.add(check);
            mapaZonas.put(check, zona);
        }

        zonasCheckboxPanel.revalidate();
        zonasCheckboxPanel.repaint();
    }

    /**
     * Devuelve la lista de puertas de embarque que han sido seleccionadas.
     * 
     * @return la lista de puertas de embarque seleccionadas.
     */
    public List<PuertaEmbarque> getPuertasSeleccionadas() {
        List<PuertaEmbarque> seleccionadas = new ArrayList<>();
        for (JCheckBox check : puertasCheckboxes) {
            if (check.isSelected()) {
                seleccionadas.add(mapaPuertas.get(check));
            }
        }
        return seleccionadas;
    }

    /**
     * Devuelve la lista de zonas de aparcamiento que han sido seleccionadas.
     * 
     * @return la lista de zonas de aparcamiento seleccionadas.
     */
    public List<ZonaAparcamiento> getZonasSeleccionadas() {
        List<ZonaAparcamiento> seleccionadas = new ArrayList<>();
        for (JCheckBox check : zonasCheckboxes) {
            if (check.isSelected()) {
                seleccionadas.add(mapaZonas.get(check));
            }
        }
        return seleccionadas;
    }

    /**
     * Establece los radio buttons de los fingers que se muestran en el panel
     * de selección de fingers.
     * 
     * @param fingersDisponibles la lista de fingers disponibles.
     */
    public void setFingerRadioButtons(List<Finger> fingersDisponibles) {
        fingersRadioButtons = new ArrayList<>();
        mapaFingers = new HashMap<>();
        grupoFingers = new ButtonGroup();
        fingersRadioButtonsPanel.removeAll();

        for (Finger finger : fingersDisponibles) {
            JRadioButton radio = new JRadioButton(finger.getCodigo());
            fingersRadioButtons.add(radio);
            mapaFingers.put(radio, finger);
            grupoFingers.add(radio);
            fingersRadioButtonsPanel.add(radio);
        }

        fingersRadioButtonsPanel.revalidate();
        fingersRadioButtonsPanel.repaint();
    }

    /**
     * Establece los JCheckBox de las zonas de aparcamiento que se muestran en el
     * panel de selección de zonas de aparcamiento para una puerta de embarque.
     * 
     * @param zonas la lista de zonas de aparcamiento disponibles para la
     *              puerta de embarque.
     */
    public void setZonasCheckboxesPuertas(List<ZonaAparcamiento> zonas) {
        aparcamientosCheckboxPanel.removeAll();
        aparcamientosCheckboxes = new ArrayList<>();
        mapaZonas = new HashMap<>();

        for (ZonaAparcamiento zona : zonas) {
            JCheckBox check = new JCheckBox(zona.getCodigo());
            aparcamientosCheckboxPanel.add(check);
            aparcamientosCheckboxes.add(check);
            mapaZonas.put(check, zona);
        }

        aparcamientosCheckboxPanel.revalidate();
        aparcamientosCheckboxPanel.repaint();
    }

    /**
     * Devuelve el código de la puerta de embarque introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el código de la puerta de embarque.
     */
    public String getCodigoPuerta() {
        return campoCodigoPuerta.getText();
    }

    /**
     * Devuelve el Finger seleccionado por el usuario a través de los JRadioButton.
     * 
     * @return El Finger seleccionado o null si no hay ninguno seleccionado.
     */

    public Finger getFingerSeleccionado() {
        for (JRadioButton rb : fingersRadioButtons) {
            if (rb.isSelected()) {
                return mapaFingers.get(rb);
            }
        }
        return null;
    }

    
    /**
     * Devuelve la lista de zonas de aparcamiento seleccionadas por el usuario.
     * 
     * @return la lista de zonas de aparcamiento seleccionadas.
     */
    public List<ZonaAparcamiento> getZonasPuertasSeleccionadas() {
        List<ZonaAparcamiento> seleccionadas = new ArrayList<>();
        for (JCheckBox check : aparcamientosCheckboxes) {
            if (check.isSelected()) {
                seleccionadas.add(mapaZonas.get(check));
            }
        }
        return seleccionadas;
    }

    
    /**
     * Establece el panel de selección de plazas para dar de alta un hangar.
     * 
     * @param plazas la lista de plazas disponibles para el hangar.
     */
    public void setPlazasCheckboxes(List<Plaza> plazas) {
        plazasCheckboxPanel.removeAll();
        plazasCheckboxes = new ArrayList<>();
        mapaPlazas = new HashMap<>();

        for (Plaza plaza : plazas) {
            JCheckBox check = new JCheckBox("Plaza con ancho: " + plaza.getAncho() + " y largo: " + plaza.getLargo());
            plazasCheckboxPanel.add(check);
            plazasCheckboxes.add(check);
            mapaPlazas.put(check, plaza);
        }

        plazasCheckboxPanel.revalidate();
        plazasCheckboxPanel.repaint();
    }

    
    /**
     * Devuelve la lista de plazas seleccionadas por el usuario.
     * 
     * @return la lista de plazas seleccionadas.
     */
    public List<Plaza> getPlazasSeleccionadas() {
        List<Plaza> seleccionadas = new ArrayList<>();
        for (JCheckBox check : plazasCheckboxes) {
            if (check.isSelected()) {
                seleccionadas.add(mapaPlazas.get(check));
            }
        }
        return seleccionadas;
    }

    
    /**
     * Devuelve el código de la zona de aparcamiento introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el código de la zona de aparcamiento.
     */
    public String getCodigoZonaAparcamiento() {
        return codigoZonaAparcamiento.getText();
    }

    
    /**
     * Devuelve la capacidad máxima de la zona de aparcamiento introducida en el campo de
     * texto de la interfaz.
     * 
     * @return la capacidad máxima de la zona de aparcamiento.
     */
    public String getCapacidadMaximaZona() {
        return capacidadMaximaZona.getText();
    }

    /**
     * Devuelve el coste por hora de la zona de aparcamiento introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el coste por hora de la zona de aparcamiento.
     */
    public String getCosteHoraZona() {
        return costeHoraZona.getText();
    }

    /**
     * Devuelve el ancho de la plaza introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el ancho de la plaza.
     */

    public String getAnchoPlaza() {
        return campoAnchoPista.getText();
    }

    /**
     * Devuelve el largo de la plaza introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el largo de la plaza.
     */

    public String getLargoPlaza() {
        return campoLargoPista.getText();
    }

    /**
     * Devuelve el código del Finger introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el código del Finger.
     */
    public String getCodigoFinger() {
        return campoCodigoFinger.getText();
    }

    /**
     * Devuelve la altura máxima del Finger introducida en el campo de
     * texto de la interfaz.
     * 
     * @return la altura máxima del Finger.
     */
    public String getAlturaMaxFinger() {
        return campoAlturaMaxFinger.getText();
    }

    /**
     * Devuelve el coste por hora del Finger introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el coste por hora del Finger.
     */
    public String getCosteFinger() {
        return campoCosteFinger.getText();
    }

    
    
    /**
     * Indica si el Finger tiene puerta doble o no.
     * 
     * @return true si tiene puerta doble, false en caso contrario.
     */
    public boolean isPuertaDobleFinger() {
        return radioSiFinger.isSelected();
    }

    
    /**
     * Indica si el Finger tiene puerta simple o no.
     * 
     * @return true si tiene puerta simple, false en caso contrario.
     */
    public boolean isPuertaSimpleFinger() {
        return radioNoFinger.isSelected();
    }

    /**
     * Devuelve el número máximo de plazas del Hangar introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el número máximo de plazas del Hangar.
     */
    public String getMaxPlazasHangar() {
        return maxPlazasHangar.getText();
    }

    /**
     * Devuelve el coste por hora del Hangar introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el coste por hora del Hangar.
     */

    public String getCosteHoraHangar() {
        return costeHoraHangar.getText();
    }

    /**
     * Devuelve el ancho del Hangar introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el ancho del Hangar.
     */

    public String getAnchoHangar() {
        return anchoHangar.getText();
    }

    /**
     * Devuelve el largo del Hangar introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el largo del Hangar.
     */

    public String getLargoHangar() {
        return largoHangar.getText();
    }

    /**
     * Devuelve el alto del Hangar introducido en el campo de
     * texto de la interfaz.
     * 
     * @return el alto del Hangar.
     */
    public String getAltoHangar() {
        return altoHangar.getText();
    }

    /**
     * Devuelve el tipo de hangar seleccionado en el ComboBox.
     * 
     * @return El tipo de hangar actualmente seleccionado como un String.
     */

    public String getTipoHangar() {
        return (String) tipoHangarComboBox.getSelectedItem();
    }

    /**
     * Devuelve la etiqueta que indica si el hangar puede almacenar
     * mercancías peligrosas.
     *
     * @return JLabel que representa la etiqueta de mercancías peligrosas.
     */

    public JLabel getLabelMercanciasPeligrosas() {
        return labelMercanciasPeligrosas;
    }

    /**
     * Devuelve el panel que contiene la pregunta sobre si el hangar puede
     * almacenar mercancías peligrosas o no.
     * 
     * @return JPanel que contiene la pregunta de mercancías peligrosas
     */
    public JPanel getPanelMercanciasPeligrosas() {
        return panelMercanciasPeligrosas;
    }

    /**
     * Comprueba si el hangar puede almacenar mercancías peligrosas.
     * 
     * @return true si el hangar puede almacenar mercancías peligrosas;
     *         false en caso contrario.
     */
    public boolean isMercanciasPeligrosas() {
        return radioSiPeligrosas.isSelected();
    }

    /**
     * Comprueba si el hangar no puede almacenar mercancías peligrosas.
     * 
     * @return true si el hangar no puede almacenar mercancías peligrosas;
     *         false en caso contrario.
     */
    public boolean isMercanciasNoPeligrosas() {
        return radioNoPeligrosas.isSelected();
    }

    /**
     * Limpia todos los campos de texto y combobox de todos los formularios
     * del panel de dar de alta un elemento del aeropuerto. Además, elimina
     * todos los componentes de los paneles de puertas de embarque y zonas de
     * aparcamiento y los vuelve invisibles.
     */
    public void limpiarFormularios() {

        tipoComboBox.setSelectedIndex(0);

        // Aeropuerto
        campoIdAeropuerto.setText("");
        campoNombreAeropuerto.setText("");
        campoCiudadAeropuerto.setText("");
        campoPaisAeropuerto.setText("");
        campoDistanciaCiudadKm.setText("");
        comboDireccionAeropuerto.setSelectedIndex(0);
        campoDiferenciaHorariaAeropuerto.setText("");
        campoFechaInicio.setText("");
        campoFechaFin.setText("");
        campoHoraApertura.setText("");
        campoHoraCierre.setText("");
        temporadas.clear();

        // Aerolínea
        campoNombreAerolinea.setText("");
        campoIdAerolinea.setText("");

        // Pista
        campoLongitudPista.setText("");
        tipoPistaComboBox.setSelectedIndex(0);

        // Terminal
        codigoTerminal.setText("");
        tipoTerminalComboBox.setSelectedIndex(0);
        maxPlazasTerminal.setText("");
        horaAperturaTerminal.setText("");
        horaCierreTerminal.setText("");
        aforoTerminal.setText("");
        aforoLabel.setVisible(false);
        aforoTerminal.setVisible(false);
        zonasLabel.setVisible(false);
        puertasLabel.setVisible(false);
        scrollZonas.setVisible(false);
        scrollPuertas.setVisible(false);
        zonasCheckboxes.forEach(cb -> cb.setSelected(false));
        puertasCheckboxes.forEach(cb -> cb.setSelected(false));

        // Puerta de embarque
        campoCodigoPuerta.setText("");
        fingersRadioButtons.forEach(rb -> grupoFingers.remove(rb));
        fingersRadioButtons.clear();
        fingersRadioButtonsPanel.removeAll();
        mapaFingers.clear();
        aparcamientosCheckboxes.forEach(cb -> cb.setSelected(false));

        // Aparcamiento
        codigoZonaAparcamiento.setText("");
        capacidadMaximaZona.setText("");
        costeHoraZona.setText("");
        plazasCheckboxes.forEach(cb -> cb.setSelected(false));

        // Plaza
        campoAnchoPista.setText("");
        campoLargoPista.setText("");

        // Finger
        campoCodigoFinger.setText("");
        campoAlturaMaxFinger.setText("");
        campoCosteFinger.setText("");
        grupoPuertaDoble.clearSelection();

        // Hangar
        tipoHangarComboBox.setSelectedIndex(0);
    }

}