package vista;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factura.Factura;

/**
 * Clase que representa la vista para realizar pagos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class RealizarPago extends JPanel {

    private JPanel checkboxPanel;
    private JTextField numTarjeta;
    private Map<JCheckBox, Factura> checkboxFacturaMap = new HashMap<>();
    private JButton botonPagar;
    private JButton botonVolver;

    /**
     * Constructor de la clase RealizarPago.
     * 
     * @param frame ventana principal
     */
    public RealizarPago(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");
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
        panel.add(new JLabel("Facturas pendientes:"), gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Introduce el número de la tarjeta con la que quieres pagar:"), gbc);
        numTarjeta = new JTextField(15);
        gbc.gridx = 1;
        panel.add(numTarjeta, gbc);
        y++;

        checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBorder(BorderFactory.createTitledBorder("Selecciona las facturas correspondientes"));

        JScrollPane scrollPane = new JScrollPane(checkboxPanel);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        botonPagar = new JButton("Pagar seleccionadas");
        panel.add(botonPagar, gbc);
        gbc.gridx = 1;

        this.add(panel, BorderLayout.CENTER);
    }

    /**
     * Asigna un ActionListener común a los botones para que el controlador pueda
     * manejar eventos.
     * 
     * @param e ActionListener a asignar a los botones
     */
    public void setControlador(ActionListener e) {
        botonPagar.addActionListener(e);
        botonVolver.addActionListener(e);
    }

    /**
     * Carga las facturas pendientes en el panel de checkboxes.
     * Cada factura se representa con un checkbox que muestra su identificador y
     * precio.
     * 
     * @param facturas Lista de facturas pendientes
     */
    public void setFacturas(List<Factura> facturas) {
        checkboxPanel.removeAll();
        checkboxFacturaMap.clear();

        for (Factura factura : facturas) {
            JCheckBox cb = new JCheckBox(factura.getInvoiceIdentifier() + " - " + factura.getPrice() + "€");
            checkboxFacturaMap.put(cb, factura);
            checkboxPanel.add(cb);
        }

        checkboxPanel.revalidate();
        checkboxPanel.repaint();
    }

    /**
     * Obtiene la lista de facturas seleccionadas por el usuario para pagar.
     * 
     * @return Lista de facturas seleccionadas
     */
    public List<Factura> getFacturasSeleccionadas() {
        List<Factura> seleccionadas = new ArrayList<>();
        for (Map.Entry<JCheckBox, Factura> entry : checkboxFacturaMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                seleccionadas.add(entry.getValue());
            }
        }
        return seleccionadas;
    }

    /**
     * Devuelve el botón para pagar, útil para el controlador.
     * 
     * @return JButton para pagar facturas
     */
    public JButton getBotonPagar() {
        return botonPagar;
    }

    /**
     * Devuelve el texto ingresado en el campo del número de tarjeta.
     * 
     * @return Número de tarjeta como cadena de texto
     */
    public String getNumTarjeta() {
        return numTarjeta.getText();
    }
}
