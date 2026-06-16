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
import factura.Factura;
import sistema.SkyCompass;

/**
 * Clase que representa la vista de la configuración de costes.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class ConfiguracionCostes extends JPanel {

    private JTextField campoCosteBase;
    private JTextField campoRecargoMercancias;
    private JTextField campoRecargoPasajeros;
    private JButton botonVolver;
    private JButton guardarButton;
    private JButton cancelarButton;

    /**
     * Constructor de la clase ConfiguracionCostes.
     *
     */
    public ConfiguracionCostes() {
        this.setLayout(new BorderLayout());

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
        contentPanel.add(new JLabel("Coste base de las facturas:"), gbc);

        gbc.gridx = 1;
        campoCosteBase = new JTextField(20);
        contentPanel.add(campoCosteBase, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Recargo vuelo de mercancías:"), gbc);

        gbc.gridx = 1;
        campoRecargoMercancias = new JTextField(20);
        contentPanel.add(campoRecargoMercancias, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Recargo vuelo de pasajeros:"), gbc);

        gbc.gridx = 1;
        campoRecargoPasajeros = new JTextField(20);
        contentPanel.add(campoRecargoPasajeros, gbc);

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

        this.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(false);

        this.update();
    }

    /**
     * Asigna el controlador de eventos a los botones de guardar,
     * cancelar y volver.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        botonVolver.addActionListener(e);
        guardarButton.addActionListener(e);
        cancelarButton.addActionListener(e);
    }

    /**
     * Retorna el JTextField que contiene el coste base de las facturas.
     * 
     * @return JTextField con el coste base
     */
    public JTextField getCampoCosteBase() {
        return campoCosteBase;
    }

    /**
     * Retorna el JTextField que contiene el recargo por mercancías.
     * 
     * @return JTextField con el recargo por mercancías
     */
    public JTextField getCampoRecargoMercancias() {
        return campoRecargoMercancias;
    }

    /**
     * Retorna el JTextField que contiene el recargo por pasajeros.
     * 
     * @return JTextField con el recargo por pasajeros
     */
    public JTextField getCampoRecargoPasajeros() {
        return campoRecargoPasajeros;
    }

    /**
     * Retorna el coste base de las facturas.
     * 
     * @return el coste base
     */
    public double getCosteBase() {
        return Double.parseDouble(campoCosteBase.getText());
    }

    /**
     * Retorna el recargo por mercancías.
     * 
     * @return El recargo por mercancías.
     */
    public double getRecargoMercancias() {
        return Double.parseDouble(campoRecargoMercancias.getText());
    }

    /**
     * Retorna el recargo por pasajeros.
     * 
     * @return El recargo por pasajeros.
     */
    public double getRecargoPasajeros() {
        return Double.parseDouble(campoRecargoPasajeros.getText());
    }

    /**
     * Actualiza los campos de texto del formulario con los valores actuales
     * de coste base, recargo por mercancías y recargo por pasajeros obtenidos
     * de la clase Factura.
     */

    public void update() {
        campoCosteBase.setText(String.valueOf(Factura.getPrecioBase()));
        campoRecargoMercancias.setText(String.valueOf(Factura.getRecargoMercancias()));
        campoRecargoPasajeros.setText(String.valueOf(Factura.getRecargoPasajeros()));
    }

}
