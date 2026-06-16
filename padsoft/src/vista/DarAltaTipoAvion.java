package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Clase que representa la vista de dar de alta un tipo de avión.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class DarAltaTipoAvion extends JPanel {

    private JLabel marcaLabel;
    private JLabel modeloLabel;
    private JLabel largoLabel;
    private JLabel anchoLabel;
    private JLabel altoLabel;
    private JLabel autonomiaLabel;

    private JTextField marcaField;
    private JTextField modeloField;
    private JTextField largoField;
    private JTextField anchoField;
    private JTextField altoField;
    private JTextField autonomiaField;

    private JLabel aforoLabel;
    private JLabel tempLabel;
    private JLabel cargaLabel;
    private JLabel peligroLabel;

    private JTextField aforoField;
    private JTextField cargaField;
    private JComboBox<String> tipoCombo;
    private JRadioButton tempSiRadio;
    private JRadioButton tempNoRadio;
    private ButtonGroup tempGroup;

    private JRadioButton peligroSiRadio;
    private JRadioButton peligroNoRadio;
    private ButtonGroup peligroGroup;

    private JPanel tempPanel;
    private JPanel peligroPanel;

    private JButton botonVolver;
    private JButton botonDarAlta;

    /**
     * Constructor de la clase DarAltaTipoAvion.
     * @param frame Ventana principal
     */

    public DarAltaTipoAvion(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");
        botonVolver.addActionListener(e -> frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR));
        topPanel.add(botonVolver);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        marcaLabel = new JLabel("Marca:");
        panel.add(marcaLabel, gbc);
        gbc.gridx = 1;
        marcaField = new JTextField(15);
        panel.add(marcaField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        modeloLabel = new JLabel("Modelo:");
        panel.add(modeloLabel, gbc);
        gbc.gridx = 1;
        modeloField = new JTextField(15);
        panel.add(modeloField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        largoLabel = new JLabel("Largo (m):");
        panel.add(largoLabel, gbc);
        gbc.gridx = 1;
        largoField = new JTextField(15);
        panel.add(largoField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        anchoLabel = new JLabel("Ancho (m):");
        panel.add(anchoLabel, gbc);
        gbc.gridx = 1;
        anchoField = new JTextField(15);
        panel.add(anchoField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        altoLabel = new JLabel("Alto (m):");
        panel.add(altoLabel, gbc);
        gbc.gridx = 1;
        altoField = new JTextField(15);
        panel.add(altoField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        autonomiaLabel = new JLabel("Autonomía (km):");
        panel.add(autonomiaLabel, gbc);
        gbc.gridx = 1;
        autonomiaField = new JTextField(15);
        panel.add(autonomiaField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        JLabel tipoLabel = new JLabel("Tipo de avión:");
        panel.add(tipoLabel, gbc);
        gbc.gridx = 1;
        tipoCombo = new JComboBox<>(new String[] { "", "Pasajeros", "Mercancías" });
        panel.add(tipoCombo, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        aforoLabel = new JLabel("Aforo máximo (pasajeros):");
        panel.add(aforoLabel, gbc);
        gbc.gridx = 1;
        aforoField = new JTextField(15);
        panel.add(aforoField, gbc);
        aforoLabel.setVisible(false);
        aforoField.setVisible(false);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        tempLabel = new JLabel("Control de temperatura:");
        panel.add(tempLabel, gbc);
        gbc.gridx = 1;
        tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tempSiRadio = new JRadioButton("Sí");
        tempNoRadio = new JRadioButton("No");
        tempGroup = new ButtonGroup();
        tempGroup.add(tempSiRadio);
        tempGroup.add(tempNoRadio);
        tempPanel.add(tempSiRadio);
        tempPanel.add(tempNoRadio);
        panel.add(tempPanel, gbc);
        tempLabel.setVisible(false);
        tempPanel.setVisible(false);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        cargaLabel = new JLabel("Carga máxima (kg):");
        panel.add(cargaLabel, gbc);
        gbc.gridx = 1;
        cargaField = new JTextField(15);
        panel.add(cargaField, gbc);
        cargaLabel.setVisible(false);
        cargaField.setVisible(false);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        peligroLabel = new JLabel("Mercancías peligrosas:");
        panel.add(peligroLabel, gbc);
        gbc.gridx = 1;
        peligroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        peligroSiRadio = new JRadioButton("Sí");
        peligroNoRadio = new JRadioButton("No");
        peligroGroup = new ButtonGroup();
        peligroGroup.add(peligroSiRadio);
        peligroGroup.add(peligroNoRadio);
        peligroPanel.add(peligroSiRadio);
        peligroPanel.add(peligroNoRadio);
        panel.add(peligroPanel, gbc);
        peligroLabel.setVisible(false);
        peligroPanel.setVisible(false);
        y++;

        gbc.gridx = 1;
        gbc.gridy = y;
        JPanel bottomPanel = new JPanel();
        botonDarAlta = new JButton("Dar de alta");
        bottomPanel.add(botonDarAlta);
        panel.add(bottomPanel, gbc);

        this.add(panel, BorderLayout.CENTER);
    }

    /**
     * Asigna un ActionListener a los botones y combos para manejar
     * los eventos de usuario.
     * 
     * @param e el ActionListener que maneja los eventos
     */
    public void setControlador(ActionListener e) {
        botonVolver.addActionListener(e);
        tipoCombo.addActionListener(e);
        botonDarAlta.addActionListener(e);
    }

    /**
     * Devuelve el campo de texto que contiene la marca del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con la marca
     */

    public JTextField getMarcaField() {
        return marcaField;
    }

    /**
     * Devuelve el campo de texto que contiene el modelo del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con el modelo
     */

    public JTextField getModeloField() {
        return modeloField;
    }

    /**
     * Devuelve el campo de texto que contiene el largo del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con el largo
     */
    public JTextField getLargoField() {
        return largoField;
    }

    /**
     * Devuelve el campo de texto que contiene el ancho del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con el ancho
     */

    public JTextField getAnchoField() {
        return anchoField;
    }

    /**
     * Devuelve el campo de texto que contiene el alto del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con el alto
     */

    public JTextField getAltoField() {
        return altoField;
    }

    /**
     * Devuelve el campo de texto que contiene la autonomía del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con la autonomía
     */
    public JTextField getAutonomiaField() {
        return autonomiaField;
    }

    /**
     * Devuelve el JComboBox con las opciones de tipo de avión.
     * 
     * @return JComboBox con las opciones de tipo de avión
     */
    public JComboBox<String> getTipoCombo() {
        return tipoCombo;
    }

    /**
     * Devuelve el campo de texto que contiene el aforo del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con el aforo
     */

    public JTextField getAforoField() {
        return aforoField;
    }

    /**
     * Devuelve el campo de texto que contiene la carga del tipo de avión
     * a dar de alta.
     * 
     * @return el campo de texto con la carga
     */
    public JTextField getCargaField() {
        return cargaField;
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
     * Devuelve la etiqueta que contiene el texto "Temperatura critica:".
     * 
     * @return la etiqueta de temperatura critica
     */
    public JLabel getTempLabel() {
        return tempLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Carga máxima (kg):".
     * 
     * @return la etiqueta de carga máxima
     */

    public JLabel getCargaLabel() {
        return cargaLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Mercancías peligrosas:".
     * 
     * @return la etiqueta de mercancías peligrosas
     */
    public JLabel getPeligroLabel() {
        return peligroLabel;
    }

    /**
     * Devuelve el botón "Volver" para asignar controladores o acceder desde otras
     * clases.
     * 
     * @return JButton del botón volver
     */
    public JButton getBotonVolver() {
        return botonVolver;
    }

    /**
     * Devuelve el panel que contiene la pregunta sobre si el tipo de avión puede
     * transportar mercancías peligrosas o no.
     * 
     * @return JPanel que contiene la pregunta de mercancías peligrosas
     */
    public JPanel getTempPanel() {
        return tempPanel;
    }

    /**
     * Devuelve el panel que contiene la pregunta sobre si el tipo de avión puede
     * transportar mercancías peligrosas o no.
     * 
     * @return JPanel que contiene la pregunta de mercancías peligrosas
     */
    public JPanel getPeligroPanel() {
        return peligroPanel;
    }

    /**
     * Indica si el tipo de avión seleccionado controla la temperatura o no.
     * 
     * @return true si el tipo de avión seleccionado controla la temperatura;
     *         false en caso contrario.
     */
    public boolean isControlTemperaturaSi() {
        return tempSiRadio.isSelected();
    }

    /**
     * Indica si el tipo de avión seleccionado NO controla la temperatura o no.
     * 
     * @return true si el tipo de avión seleccionado NO controla la temperatura;
     *         false en caso contrario.
     */
    public boolean isControlTemperaturaNo() {
        return tempNoRadio.isSelected();
    }

    /**
     * Indica si el tipo de avión seleccionado puede transportar mercancías
     * peligrosas o no.
     * 
     * @return true si el tipo de avión seleccionado puede transportar
     *         mercancías peligrosas; false en caso contrario.
     */
    public boolean isMercanciasPeligrosasSi() {
        return peligroSiRadio.isSelected();
    }

    /**
     * Indica si el tipo de avión seleccionado NO puede transportar mercancías
     * peligrosas.
     * 
     * @return true si el tipo de avión seleccionado NO puede transportar
     *         mercancías peligrosas; false en caso contrario.
     */

    public boolean isMercanciasPeligrosasNo() {
        return peligroNoRadio.isSelected();
    }

}
