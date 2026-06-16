package vista;

import java.awt.*;
import javax.swing.*;

import aeropuerto.Aeropuerto;
import aeropuerto.Avion;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Clase que representa la vista para solicitar un vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class SolicitarVuelo extends JPanel {

    private JTextField idField = new JTextField(15);
    private JTextField horaSalidaField = new JTextField(15);
    private JTextField horaLlegadaField = new JTextField(15);
    private JTextField fechaInicioField = new JTextField(15);
    private JTextField fechaFinField = new JTextField(15);
    private JLabel fechaInicioLabel;
    private JLabel fechaFinLabel;
    private JComboBox<String> frecuenciaCombo = new JComboBox<>(new String[] { "", "Diario", "Semanal", "Puntual" });
    private JComboBox<Avion> avionCombo = new JComboBox<>();
    private JComboBox<String> destinoCombo = new JComboBox<>();
    private JComboBox<String> origenCombo = new JComboBox<>();
    private JComboBox<String> tipoCombo = new JComboBox<>(new String[] { "", "Pasajeros", "Mercancías" });
    private JButton botonSolicitar = new JButton("Solicitar vuelo");
    private JCheckBox[] diasSemanaCheckboxes = new JCheckBox[] {
            new JCheckBox("Lunes"),
            new JCheckBox("Martes"),
            new JCheckBox("Miércoles"),
            new JCheckBox("Jueves"),
            new JCheckBox("Viernes"),
            new JCheckBox("Sábado"),
            new JCheckBox("Domingo")
    };

    private JPanel panelFinger = new JPanel();
    private JRadioButton fingerSi = new JRadioButton("Sí");
    private JRadioButton fingerNo = new JRadioButton("No");
    private ButtonGroup fingerGroup = new ButtonGroup();

    private JPanel panelDiasSemana = new JPanel();

    private JButton botonVolver;

    /**
     * Constructor de la clase SolicitarVuelo.
     * 
     * @param frame la ventana principal del programa
     */
    public SolicitarVuelo(VentanaPrincipal frame) {
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
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Hora Salida:"), gbc);
        gbc.gridx = 1;
        panel.add(horaSalidaField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Hora Llegada:"), gbc);
        gbc.gridx = 1;
        panel.add(horaLlegadaField, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Frecuencia:"), gbc);
        gbc.gridx = 1;
        panel.add(frecuenciaCombo, gbc);
        y++;

        panelDiasSemana.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (JCheckBox cb : diasSemanaCheckboxes) {
            panelDiasSemana.add(cb);
        }

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(panelDiasSemana, gbc);
        panelDiasSemana.setVisible(false);
        gbc.gridwidth = 1;
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        fechaInicioLabel = new JLabel("Fecha Inicio:");
        panel.add(fechaInicioLabel, gbc);
        gbc.gridx = 1;
        panel.add(fechaInicioField, gbc);
        y++;
        fechaInicioLabel.setVisible(false);
        fechaInicioField.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = y;
        fechaFinLabel = new JLabel("Fecha Fin:");
        panel.add(fechaFinLabel, gbc);
        gbc.gridx = 1;
        panel.add(fechaFinField, gbc);
        y++;
        fechaFinLabel.setVisible(false);
        fechaFinField.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Avión:"), gbc);
        gbc.gridx = 1;
        panel.add(avionCombo, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Aeropuerto origen:"), gbc);
        gbc.gridx = 1;
        panel.add(origenCombo, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Aeropuerto destino:"), gbc);
        gbc.gridx = 1;
        panel.add(destinoCombo, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Tipo de vuelo:"), gbc);
        gbc.gridx = 1;
        panel.add(tipoCombo, gbc);

        panelFinger.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelFinger.add(new JLabel("¿Usa finger?:"));
        fingerGroup.add(fingerSi);
        fingerGroup.add(fingerNo);
        panelFinger.add(fingerSi);
        panelFinger.add(fingerNo);
        fingerNo.setSelected(true);

        gbc.gridx = 0;
        gbc.gridy = ++y;
        gbc.gridwidth = 2;
        panel.add(panelFinger, gbc);
        panelFinger.setVisible(false);
        gbc.gridwidth = 1;

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(botonSolicitar);
        this.add(panel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.AFTER_LAST_LINE);
    }

    /**
     * Asigna el controlador de eventos a los componentes interactivos.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        botonSolicitar.addActionListener(e);
        frecuenciaCombo.addActionListener(e);
        avionCombo.addActionListener(e);
        origenCombo.addActionListener(e);
        destinoCombo.addActionListener(e);
        tipoCombo.addActionListener(e);
        fingerSi.addActionListener(e);
        fingerNo.addActionListener(e);
        botonVolver.addActionListener(e);
    }

    /**
     * Devuelve el JComboBox con las opciones de frecuencia de vuelo.
     * 
     * @return JComboBox con las opciones de frecuencia de vuelo
     */
    public JComboBox<String> getFrecuenciaCombo() {
        return frecuenciaCombo;
    }

    /**
     * Devuelve el JComboBox con las opciones de tipo de vuelo.
     * 
     * @return JComboBox con las opciones de tipo de vuelo
     */
    public JComboBox<String> getTipoCombo() {
        return tipoCombo;
    }

    /**
     * Retorna el botón para solicitar el vuelo.
     * 
     * @return JButton con el botón para solicitar el vuelo
     */
    public JButton getBotonSolicitar() {
        return botonSolicitar;
    }

    /**
     * Retorna el id del vuelo a solicitar.
     * 
     * @return cadena con el id del vuelo a solicitar
     */
    public String getIdVuelo() {
        return idField.getText();
    }

    /**
     * Retorna la hora de salida del vuelo como cadena en formato
     * HH:MM.
     * 
     * @return cadena con la hora de salida del vuelo
     */
    public String getHoraSalida() {
        return horaSalidaField.getText();
    }

    /**
     * Retorna la hora de llegada del vuelo como cadena en formato
     * HH:MM.
     * 
     * @return cadena con la hora de llegada del vuelo
     */
    public String getHoraLlegada() {
        return horaLlegadaField.getText();
    }

    /**
     * Retorna el campo de texto para la fecha de inicio del vuelo.
     * 
     * @return JTextField con la fecha de inicio del vuelo
     */
    public JTextField getFechaInicioField() {
        return fechaInicioField;
    }

    /**
     * Retorna la etiqueta de la fecha de inicio del vuelo.
     * 
     * @return JLabel con la etiqueta de la fecha de inicio del vuelo
     */

    public JLabel getFechaInicioLabel() {
        return fechaInicioLabel;
    }

    /**
     * Retorna el campo de texto para la fecha de fin del vuelo.
     * 
     * @return JTextField con la fecha de fin del vuelo
     */
    public JTextField getFechaFinField() {
        return fechaFinField;
    }

    /**
     * Retorna la etiqueta de la fecha de fin del vuelo.
     * 
     * @return JLabel con la etiqueta de la fecha de fin del vuelo
     */
    public JLabel getFechaFinLabel() {
        return fechaFinLabel;
    }

    /**
     * Retorna la frecuencia seleccionada en el JComboBox.
     * 
     * @return String con la frecuencia seleccionada
     */
    public String getFrecuencia() {
        return frecuenciaCombo.getSelectedItem().toString();
    }

    /**
     * Retorna el avion seleccionado en el JComboBox.
     * 
     * @return Avion seleccionado
     */
    public Avion getAvion() {
        return (Avion) avionCombo.getSelectedItem();
    }

    /**
     * Retorna el aeropuerto de origen seleccionado en el JComboBox.
     * 
     * @return String con el aeropuerto de origen seleccionado
     */
    public String getOrigen() {
        return (String) origenCombo.getSelectedItem();
    }

    /**
     * Retorna el aeropuerto de destino seleccionado en el JComboBox.
     * 
     * @return String con el aeropuerto de destino seleccionado
     */
    public String getDestino() {
        return (String) destinoCombo.getSelectedItem();
    }

    /**
     * Retorna el tipo de vuelo seleccionado en el JComboBox.
     * 
     * @return String con el tipo de vuelo seleccionado
     */
    public String getTipoVuelo() {
        return (String) tipoCombo.getSelectedItem();
    }

    /**
     * Retorna el panel que contiene los JCheckBox para los dias de la semana.
     * 
     * @return JPanel con los JCheckBox para los dias de la semana
     */
    public JPanel getPanelDiasSemana() {
        return panelDiasSemana;
    }

    /**
     * Retorna una lista con los dias de la semana seleccionados.
     * 
     * @return lista de dias de la semana
     */
    public List<DayOfWeek> getDias() {
        List<DayOfWeek> dias = new ArrayList<>();
        DayOfWeek[] valores = DayOfWeek.values();
        for (int i = 0; i < diasSemanaCheckboxes.length; i++) {
            if (diasSemanaCheckboxes[i].isSelected()) {
                dias.add(valores[i]);
            }
        }
        return dias;
    }

    /**
     * Retorna el JComboBox que contiene los aviones disponibles.
     * 
     * @return JComboBox con los aviones disponibles
     */
    public JComboBox<Avion> getAvionCombo() {
        return avionCombo;
    }

    /**
     * Retorna el JComboBox que contiene los aeropuertos de destino disponibles.
     * 
     * @return JComboBox con los aeropuertos de destino disponibles
     */
    public JComboBox<String> getAeropuertoDestinoCombo() {
        return destinoCombo;
    }

    /**
     * Retorna el JComboBox que contiene los aeropuertos de origen disponibles.
     * 
     * @return JComboBox con los aeropuertos de origen disponibles
     */
    public JComboBox<String> getAeropuertoOrigenCombo() {
        return origenCombo;
    }

    /**
     * Retorna el panel que contiene los JRadioButton para seleccionar si el
     * vuelo utiliza o no Finger (pasarela de embarque).
     * 
     * @return JPanel con los JRadioButton para seleccionar si el vuelo usa Finger
     */
    public JPanel getPanelFinger() {
        return panelFinger;
    }

    /**
     * Devuelve true si el vuelo seleccionado utiliza Finger (pasarela de embarque)
     * o false en caso contrario.
     * 
     * @return true si el vuelo utiliza Finger, false en caso contrario
     */
    public boolean getUsaFinger() {
        return fingerSi.isSelected();
    }

    /**
     * Limpia todos los campos del formulario.
     * Borra los contenidos de los campos de texto y deselecciona los botones de
     * radio.
     */
    public void limpiar() {
        idField.setText("");
        horaSalidaField.setText("");
        horaLlegadaField.setText("");
        fechaInicioField.setText("");
        fechaFinField.setText("");

        frecuenciaCombo.setSelectedIndex(0);
        tipoCombo.setSelectedIndex(0);
        avionCombo.setSelectedIndex(-1);
        origenCombo.setSelectedIndex(-1);
        destinoCombo.setSelectedIndex(-1);

        for (JCheckBox cb : diasSemanaCheckboxes) {
            cb.setSelected(false);
        }

        panelDiasSemana.setVisible(false);
        fechaInicioLabel.setVisible(false);
        fechaInicioField.setVisible(false);
        fechaFinLabel.setVisible(false);
        fechaFinField.setVisible(false);

        fingerGroup.clearSelection();
        fingerNo.setSelected(true);
        panelFinger.setVisible(false);
    }

}
