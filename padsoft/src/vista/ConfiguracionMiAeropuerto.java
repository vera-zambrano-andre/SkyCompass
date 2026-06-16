package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import aeropuerto.Aeropuerto;
import aeropuerto.Direccion;
import aeropuerto.Temporada;
import sistema.SkyCompass;

/**
 * Clase que representa la vista de la configuración de mi aeropuerto.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class ConfiguracionMiAeropuerto extends JPanel {

    private JTextField campoNombre;
    private JTextField campoCodigo;
    private JTextField campoCiudad;
    private JTextField campoPais;
    private JTextField campoDistancia;
    private JTextField campoDiferenciaHoraria;
    private JComboBox<Direccion> comboDireccion;
    private JButton botonVolver;
    private JButton guardarButton;
    private JButton cancelarButton;
    private Aeropuerto aeropuertoPrincipal;

    /**
     * Constructor de la clase ConfiguracionMiAeropuerto.
     * @param app Instancia de SkyCompass
     */
    public ConfiguracionMiAeropuerto(SkyCompass app) {
        this.setLayout(new BorderLayout());

        this.aeropuertoPrincipal = app.getAeropuertoPrincipal();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");
        topPanel.add(botonVolver);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        campoNombre = new JTextField(20);
        contentPanel.add(campoNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Código:"), gbc);

        gbc.gridx = 1;
        campoCodigo = new JTextField(20);
        contentPanel.add(campoCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Ciudad más cercana:"), gbc);

        gbc.gridx = 1;
        campoCiudad = new JTextField(20);
        contentPanel.add(campoCiudad, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Distancia a la ciudad (km):"), gbc);

        gbc.gridx = 1;
        campoDistancia = new JTextField(20);
        contentPanel.add(campoDistancia, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("País:"), gbc);

        gbc.gridx = 1;
        campoPais = new JTextField(20);
        contentPanel.add(campoPais, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        comboDireccion = new JComboBox<>(Direccion.values());
        contentPanel.add(comboDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Diferencia horaria:"), gbc);

        gbc.gridx = 1;
        campoDiferenciaHoraria = new JTextField(20);
        contentPanel.add(campoDiferenciaHoraria, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");

        buttonPanel.add(guardarButton);
        buttonPanel.add(cancelarButton);
        contentPanel.add(buttonPanel, gbc);

        this.update();

        this.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(false);
    }

    /**
     * Asigna el controlador de eventos a los botones de volver, guardar y cancelar.
     * @param e el ActionListener a asociar
     */
    public void setControlador (ActionListener e) {
        botonVolver.addActionListener(e);
        guardarButton.addActionListener(e);
        cancelarButton.addActionListener(e);
    }

    /**
     * Actualiza los campos de la interfaz de usuario con los valores del aeropuerto
     * principal actual.
     */
    public void update() {
        if (aeropuertoPrincipal != null) {
            campoNombre.setText(aeropuertoPrincipal.getNombre());
            campoCodigo.setText(aeropuertoPrincipal.getCodigo());
            campoCiudad.setText(aeropuertoPrincipal.getCiudadMasCercana());
            campoPais.setText(aeropuertoPrincipal.getPais());
            campoDistancia.setText(String.valueOf(aeropuertoPrincipal.getDistanciaCiudadKm()));
            campoDiferenciaHoraria.setText(String.valueOf(aeropuertoPrincipal.getDiferenciaHoraria()));
            comboDireccion.setSelectedItem(aeropuertoPrincipal.getDireccion());
        }
    }

    /**
     * Devuelve el nombre del aeropuerto principal introducido por el usuario en
     * la interfaz de usuario.
     * 
     * @return El nombre del aeropuerto principal.
     */
    public String getNombreAeropuerto() {
        return campoNombre.getText();
    }

    /**
     * Devuelve el codigo del aeropuerto principal introducido por el usuario en
     * la interfaz de usuario.
     * 
     * @return El codigo del aeropuerto principal.
     */
    public String getCodigoAeropuerto() {
        return campoCodigo.getText();
    }

    /**
     * Devuelve la ciudad más cercana al aeropuerto principal introducida por el usuario
     * en la interfaz de usuario.
     * 
     * @return La ciudad más cercana al aeropuerto principal.
     */
    public String getCiudadAeropuerto() {
        return campoCiudad.getText();
    }

    /**
     * Devuelve el pais en el que se encuentra el aeropuerto principal introducido
     * por el usuario en la interfaz de usuario.
     * 
     * @return El pais en el que se encuentra el aeropuerto principal.
     */
    public String getPaisAeropuerto() {
        return campoPais.getText();
    }

    /**
     * Devuelve la distancia en kilómetros a la ciudad más cercana al aeropuerto
     * principal introducida por el usuario en la interfaz de usuario.
     * 
     * @return La distancia en kilómetros a la ciudad más cercana.
     */

    public int getDistanciaCiudad() {
        return Integer.parseInt(campoDistancia.getText());
    }

    /**
     * Devuelve la dirección del aeropuerto principal introducida por el usuario
     * en la interfaz de usuario.
     * 
     * @return La dirección del aeropuerto principal.
     */
    public Direccion getDireccionAeropuerto() {

        return (Direccion) comboDireccion.getSelectedItem();
    }

    /**
     * Devuelve la diferencia horaria del aeropuerto principal respecto al
     * tiempo UTC introducida por el usuario en la interfaz de usuario.
     * 
     * @return La diferencia horaria en horas.
     */
    public int getDiferenciaHoraria() {
        return Integer.parseInt(campoDiferenciaHoraria.getText());
    }

}
