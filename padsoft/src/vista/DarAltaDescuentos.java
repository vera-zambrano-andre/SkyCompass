package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.Desktop.Action;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import aeropuerto.Dimension;

/**
 * Clase que representa la vista del alta de descuentos.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class DarAltaDescuentos extends JPanel {

    private JButton botonVolver;
    private JTextField descripcionField;
    private JTextField fechaInicioField;
    private JTextField fechaFinField;
    private JTextField porcentajeField;
    private JTextField limiteField;

    private JRadioButton tipoTotalRadio;
    private JRadioButton tipoRecursosRadio;
    private ButtonGroup tipoGroup;

    private JRadioButton condicionVuelosRadio;
    private JRadioButton condicionImporteRadio;
    private ButtonGroup condicionGroup;

    private JButton botonDarAltaDescuento;

    /**
     * Constructor de la clase DarAltaDescuentos.
     */

    public DarAltaDescuentos() {
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

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        infoPanel.add(new JLabel(
                "Vuelos mínimos: si eliges 3 como límite, solo se aplicará el descuento si hay más de 3 vuelos en ese mes"));
        infoPanel.add(new JLabel(
                "Mínimo importe: si eliges 1000 como límite, solo se aplicará el descuento si el importe total supera 1000€"));

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(infoPanel, gbc);

        gbc.gridwidth = 1;
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Descripción del descuento:"), gbc);
        gbc.gridx = 1;
        descripcionField = new JTextField(10);
        panel.add(descripcionField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Fecha Inicio:"), gbc);
        gbc.gridx = 1;
        fechaInicioField = new JTextField(10);
        panel.add(fechaInicioField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Fecha Fin:"), gbc);
        gbc.gridx = 1;
        fechaFinField = new JTextField(10);
        panel.add(fechaFinField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Porcentaje (debe estar comprendido entre 1 y 50):"), gbc);
        gbc.gridx = 1;
        porcentajeField = new JTextField(10);
        panel.add(porcentajeField, gbc);
        y++;

        tipoTotalRadio = new JRadioButton("Importe total");
        tipoRecursosRadio = new JRadioButton("Recursos");
        tipoGroup = new ButtonGroup();
        tipoGroup.add(tipoTotalRadio);
        tipoGroup.add(tipoRecursosRadio);

        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipoPanel.add(new JLabel("Aplicar descuento a:"));
        tipoPanel.add(tipoTotalRadio);
        tipoPanel.add(tipoRecursosRadio);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(tipoPanel, gbc);
        gbc.gridwidth = 1;
        y++;

        condicionVuelosRadio = new JRadioButton("Vuelos mínimos");
        condicionImporteRadio = new JRadioButton("Mínimo importe");
        condicionGroup = new ButtonGroup();
        condicionGroup.add(condicionVuelosRadio);
        condicionGroup.add(condicionImporteRadio);

        JPanel condicionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        condicionPanel.add(new JLabel("Condición de aplicación:"));
        condicionPanel.add(condicionVuelosRadio);
        condicionPanel.add(condicionImporteRadio);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(condicionPanel, gbc);
        gbc.gridwidth = 1;
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Límite:"), gbc);
        gbc.gridx = 1;
        limiteField = new JTextField(10);
        panel.add(limiteField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        botonDarAltaDescuento = new JButton("Dar Alta Descuento");
        panel.add(botonDarAltaDescuento, gbc);

        this.add(panel, BorderLayout.CENTER);
    }

    /**
     * Limpia todos los campos del formulario.
     * 
     * Borra los contenidos de los campos de texto y deselecciona los botones de
     * radio.
     */
    public void limpiar() {
        descripcionField.setText("");
        fechaInicioField.setText("");
        fechaFinField.setText("");
        porcentajeField.setText("");
        limiteField.setText("");
        
        condicionGroup.clearSelection();
        tipoGroup.clearSelection();
    }

    /**
     * Asigna el controlador de eventos a los botones de dar de alta
     * un descuento y de volver.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        botonDarAltaDescuento.addActionListener(e);
        botonVolver.addActionListener(e);
    }

/**
 * Obtiene la descripción del descuento ingresada en el campo de texto.
 * 
 * @return La descripción actual del descuento.
 */

    public String getDescripcion() {
        return descripcionField.getText();
    }

    /**
     * Obtiene el porcentaje del descuento ingresado en el campo de texto.
     * 
     * @return El porcentaje actual del descuento.
     */
    public String getPorcentaje() {
        return porcentajeField.getText();
    }

    /**
     * Obtiene la fecha de inicio del descuento ingresada en el campo de texto.
     * 
     * @return La fecha de inicio actual del descuento.
     */
    public String getFechaInicio() {
        return fechaInicioField.getText();
    }

    /**
     * Obtiene la fecha de fin del descuento ingresada en el campo de texto.
     * 
     * @return La fecha de fin actual del descuento.
     */

    public String getFechaFin() {
        return fechaFinField.getText();
    }

/**
 * Obtiene el límite ingresado en el campo de texto.
 * 
 * @return El límite actual como una cadena.
 */

    public String getLimite() {
        return limiteField.getText();
    }

    /**
     * Indica si el descuento se aplica por condición de vuelos mínimos.
     * 
     * @return true si se aplica por condición de vuelos, false en caso contrario.
     */
    public boolean isCondicionVuelos() {
        return condicionVuelosRadio.isSelected();
    }

    /**
     * Indica si el descuento se aplica por condición de importe.
     * 
     * @return true si se aplica por condición de importe, false en caso
     *         contrario.
     */
    public boolean isCondicionImporte() {
        return condicionImporteRadio.isSelected();
    }

    /**
     * Indica si el descuento es de tipo total.
     * 
     * @return true si es de tipo total, false en caso contrario.
     */

    public boolean isTipoTotal() {
        return tipoTotalRadio.isSelected();
    }

    /**
     * Indica si el descuento es de tipo por recursos.
     * 
     * @return true si es de tipo por recursos, false en caso contrario.
     */
    public boolean isTipoRecursos() {
        return tipoRecursosRadio.isSelected();
    }
}
