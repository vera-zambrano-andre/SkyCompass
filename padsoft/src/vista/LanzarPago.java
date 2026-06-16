package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.List;

import aeropuerto.Aerolinea;

/**
 * Clase que representa la vista de lanzar pagos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class LanzarPago extends JPanel {

    private JComboBox<Aerolinea> aerolineaComboBox;
    private JLabel aerolineaLabel;
    private JButton lanzarPago;

    /**
     * Constructor de la clase LanzarPago.
     * 
     * @param frame la ventana principal del programa
     */
    public LanzarPago(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());

        Dimension fieldSize = new Dimension(200, 25);

        JPanel contentPanel = new JPanel(new GridBagLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonVolver = new JButton("← Volver");
        botonVolver.addActionListener(e -> frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR));
        topPanel.add(botonVolver);

        this.add(topPanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        aerolineaLabel = new JLabel("Aerolínea:");
        contentPanel.add(aerolineaLabel, gbc);

        gbc.gridx = 1;
        aerolineaComboBox = new JComboBox<>();
        aerolineaComboBox.setPreferredSize(fieldSize);
        contentPanel.add(aerolineaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        lanzarPago = new JButton("Lanzar orden de pago (del mes actual) para la aerolínea seleccionada");
        contentPanel.add(lanzarPago, gbc);

        this.add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Asigna el controlador para el boton de lanzar orden de pago.
     * 
     * @param e el controlador a asignar
     */
    public void setControlador(ActionListener e) {
        lanzarPago.addActionListener(e);
    }

    /**
     * Asigna las aerolineas que se mostraran en el combobox.
     * 
     * @param aerolineas la lista de aerolineas
     */
    public void setAerolineas(List<Aerolinea> aerolineas) {
        aerolineaComboBox.removeAllItems();

        aerolineaComboBox.addItem(new Aerolinea("vacío", "vacío") {
            @Override
            public String toString() {
                return "";
            }
        });

        for (Aerolinea a : aerolineas) {
            aerolineaComboBox.addItem(a);
        }
    }

    /**
     * Obtiene la aerolínea seleccionada en el JComboBox.
     *
     * @return La aerolínea actualmente seleccionada.
     */

    public Aerolinea getAerolineaSeleccionada() {
        return (Aerolinea) aerolineaComboBox.getSelectedItem();
    }

}