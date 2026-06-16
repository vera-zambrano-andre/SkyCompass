package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import vuelo.Vuelo;
import vuelo.EstadoVuelo;
import sistema.SkyCompass;
import usuario.Gestor;
import java.util.List;

/**
 * Clase que representa la vista de las notificaciones.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class ConfiguracionNotificaciones extends JPanel {

    private final VentanaPrincipal frame;
    private final SkyCompass app;

    private final JCheckBox cbActivar;
    private final JList<Vuelo> listVuelos;
    private final DefaultListModel<Vuelo> vuelosModel;

    private final JList<EstadoVuelo> listEstados;
    private final DefaultListModel<EstadoVuelo> estadosModel;

    private final JButton botonAceptar;
    private final JButton botonCancelar;

    /**
     * Constructor de la clase ConfiguracionNotificaciones.
     * 
     * @param frame ventana principal de la aplicación
     * @param app   sistema SkyCompass
     */
    public ConfiguracionNotificaciones(VentanaPrincipal frame, SkyCompass app) {
        this.frame = frame;
        this.app = app;
        this.setLayout(new BorderLayout(10, 10));

        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int y = 0;

        cbActivar = new JCheckBox("Activar notificaciones");
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        center.add(cbActivar, gbc);
        cbActivar.setSelected(app.getGestor().getNotificacionesActivadas());
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        center.add(new JLabel("Seleccione vuelos para NOTIFICAR (Ctrl+clic para varios):"), gbc);
        y++;

        vuelosModel = new DefaultListModel<>();
        listVuelos = new JList<>(vuelosModel);
        listVuelos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollListaVuelos = new JScrollPane(listVuelos);
        scrollListaVuelos.setPreferredSize(new Dimension(300, 120));
        scrollListaVuelos.setBorder(new TitledBorder("Vuelos disponibles"));
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        center.add(scrollListaVuelos, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        center.add(new JLabel("Seleccione estados de vuelo a notificar (Ctrl+clic para varios):"), gbc);
        y++;

        estadosModel = new DefaultListModel<>();
        for (EstadoVuelo ev : EstadoVuelo.values()) {
            estadosModel.addElement(ev);
        }
        listEstados = new JList<>(estadosModel);
        listEstados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollEstados = new JScrollPane(listEstados);
        scrollEstados.setPreferredSize(new Dimension(300, 150));
        scrollEstados.setBorder(new TitledBorder("Estados de Vuelo"));
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        center.add(scrollEstados, gbc);
        y++;

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");
        botones.add(botonAceptar);
        botones.add(botonCancelar);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        center.add(botones, gbc);

        this.add(center, BorderLayout.CENTER);

        cargarVuelosDisponibles();
    }

    /**
     * Asigna el controlador de eventos a los botones de aceptar y cancelar.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        botonAceptar.addActionListener(e);
        botonCancelar.addActionListener(e);
    }

    /**
     * Carga en la interfaz gráfica la lista de vuelos disponibles en el sistema.
     * Se utiliza para inicializar la vista de configuración de notificaciones.
     */
    private void cargarVuelosDisponibles() {
        for (Vuelo v : app.getVuelos()) {
            vuelosModel.addElement(v);
        }
    }

    /**
     * Devuelve el estado de la casilla de verificación para activar/desactivar
     * las notificaciones.
     * 
     * @return true si se han activado las notificaciones, false en caso
     *         contrario.
     */
    public boolean isNotificacionesActivadas() {
        return cbActivar.isSelected();
    }

    /**
     * Devuelve la lista de vuelos seleccionados en la interfaz de usuario.
     * La lista puede estar vacía si no se ha seleccionado ningún vuelo.
     * 
     * @return Una lista de objetos Vuelo que han sido seleccionados.
     */
    public List<Vuelo> getVuelosSeleccionados() {
        return listVuelos.getSelectedValuesList();
    }

    /**
     * Devuelve la lista de estados de vuelo seleccionados en la interfaz de
     * usuario.
     * La lista puede estar vacía si no se ha seleccionado ninguno.
     * 
     * @return Una lista de objetos EstadoVuelo que han sido seleccionados.
     */
    public List<EstadoVuelo> getEstadosSeleccionados() {
        return listEstados.getSelectedValuesList();
    }

    /**
     * Devuelve el botón "Aceptar" para asignar controladores o acceder desde otras
     * clases.
     * 
     * @return JButton del botón aceptar
     */

    public JButton getBotonAceptar() {
        return botonAceptar;
    }

    /**
     * Devuelve el botón "Cancelar" para asignar controladores o acceder desde otras
     * clases.
     * 
     * @return JButton del botón cancelar
     */
    public JButton getBotonCancelar() {
        return botonCancelar;
    }

    /**
     * Devuelve la casilla de verificación para activar/desactivar las
     * notificaciones.
     * 
     * @return JCheckBox de la casilla de verificación
     */
    public JCheckBox getCbActivar() {
        return cbActivar;
    }

    /**
     * Devuelve la lista de selección de vuelos disponibles en el sistema.
     * 
     * @return JList que contiene los vuelos disponibles para notificaciones.
     */

    public JList<Vuelo> getListVuelos() {
        return listVuelos;
    }

    /**
     * Devuelve la lista de selección de estados de vuelo disponibles en el
     * sistema.
     * 
     * @return JList que contiene los estados de vuelo disponibles para
     *         notificaciones.
     */
    public JList<EstadoVuelo> getListEstados() {
        return listEstados;
    }
}
