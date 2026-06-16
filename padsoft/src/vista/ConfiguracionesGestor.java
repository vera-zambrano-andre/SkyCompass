package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import usuario.*;

/**
 * Clase que representa la configuración del gestor.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ConfiguracionesGestor extends JPanel {

    ConfiguracionPorDefecto conf = ConfiguracionPorDefecto.getConfiguracion();

    private JButton botonVolver;
    private JButton aceptarButton;
    private JButton cancelarButton;

    private JTextField diasAntelacionField;
    private JTextField toleranciaParkingField;
    private JTextField rangoTerminalesField;
    private JTextField reservaTerminalField;

    /**
     * Constructor de la clase ConfiguracionesGestor.
     */
    public ConfiguracionesGestor() {
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
        contentPanel.add(new JLabel("Días de antelación para programar un vuelo:"), gbc);

        gbc.gridx = 1;
        diasAntelacionField = new JTextField(5);
        contentPanel.add(diasAntelacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Rango de minutos de aparcamientos/finger:"), gbc);

        gbc.gridx = 1;
        toleranciaParkingField = new JTextField(5);
        contentPanel.add(toleranciaParkingField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Rango de horas para terminales:"), gbc);

        gbc.gridx = 1;
        rangoTerminalesField = new JTextField(5);
        contentPanel.add(rangoTerminalesField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Minutos de reserva de la terminal:"), gbc);

        gbc.gridx = 1;
        reservaTerminalField = new JTextField(5);
        contentPanel.add(reservaTerminalField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        aceptarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");

        buttonPanel.add(aceptarButton);
        buttonPanel.add(cancelarButton);
        contentPanel.add(buttonPanel, gbc);

        this.update();

        this.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(false);
    }

    /**
     * Asigna el controlador de eventos a los botones de aceptar, cancelar y volver.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        botonVolver.addActionListener(e);
        aceptarButton.addActionListener(e);
        cancelarButton.addActionListener(e);
    }

    /**
     * Obtiene el número de días de antelación para programar un vuelo
     * desde el campo de texto correspondiente.
     *
     * @return el número de días de antelación como un Integer
     */

    public Integer getDiasAntelacion() {
        return Integer.valueOf(diasAntelacionField.getText());
    }

    /**
     * Obtiene el rango de minutos de aparcamiento/finger desde el campo de texto
     * correspondiente.
     *
     * @return el rango de minutos de aparcamiento/finger como un Integer
     */

    public Integer getTolerancia() {
        return Integer.valueOf(toleranciaParkingField.getText());
    }

    /**
     * Obtiene el rango de horas para terminales desde el campo de texto
     * correspondiente.
     *
     * @return el rango de horas para terminales como un Integer
     */
    public Integer getRangoTerminal() {
        return Integer.valueOf(rangoTerminalesField.getText());
    }

    /**
     * Obtiene los minutos de reserva de terminal desde el campo de texto
     * correspondiente.
     *
     * @return los minutos de reserva de terminal como un Integer
     */

    public Integer getReservaTerminal() {
        return Integer.valueOf(reservaTerminalField.getText());
    }

    /**
     * Actualiza los campos de texto con los valores actuales de configuracion.
     */
    public void update() {
        diasAntelacionField.setText(conf.getDiasAntelacionMinProgramacionVuelo().toString());
        toleranciaParkingField.setText(conf.getRangoMinutosDisponibilidadZAFinger().toString());
        rangoTerminalesField.setText(conf.getRangoHorasDisponibleTerminal().toString());
        reservaTerminalField.setText(conf.getMinutosReservaTerminal().toString());
    }
}
