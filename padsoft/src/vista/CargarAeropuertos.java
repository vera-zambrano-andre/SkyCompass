package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Clase que representa la vista de la carga de aeropuertos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class CargarAeropuertos extends JPanel {

    private JButton botonVolver;
    private JButton cargarButton;

    /**
     * Constructor de la clase CargarAeropuertos.
     */
    public CargarAeropuertos() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");
        topPanel.add(botonVolver);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        cargarButton = new JButton("Cargar Aeropuertos");

        cargarButton.setPreferredSize(new Dimension(300, 100));
        cargarButton.setFont(new Font("Arial", Font.BOLD, 24));

        centerPanel.add(cargarButton, new GridBagConstraints());
        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Asigna un controlador a los botones de la vista de carga de aeropuertos.
     * 
     * @param e ActionListener que manejará los eventos.
     */
    public void setControlador(ActionListener e) {
        botonVolver.addActionListener(e);
        cargarButton.addActionListener(e);
    }
}
