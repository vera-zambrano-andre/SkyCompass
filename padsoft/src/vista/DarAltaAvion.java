package vista;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.List;

import aeropuerto.TipoAvion;
import aeropuerto.TipoAvionMercancia;
import aeropuerto.Dimension;

/**
 * Panel para dar de alta un nuevo avión en el sistema.
 * Permite introducir información detallada del avión como año de compra,
 * fecha de revisión, dimensiones, autonomía y tipo (pasajeros o mercancías).
 * Según el tipo seleccionado, muestra campos adicionales específicos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class DarAltaAvion extends JPanel {

    private JTextField anoCompra;
    private JTextField fechaRevision;
    private JComboBox<TipoAvion> tipoCombo;
    private JButton botonVolver;
    private JButton botonDarAlta;

    /**
     * 
     * Constructor de la clase DarAltaAvion.
     * 
     */
    public DarAltaAvion() {
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
        panel.add(new JLabel("Año de compra:"), gbc);
        gbc.gridx = 1;
        anoCompra = new JTextField(15);
        panel.add(anoCompra, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Fecha de última revisión:"), gbc);
        gbc.gridx = 1;
        fechaRevision = new JTextField(15);
        panel.add(fechaRevision, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel("Lista de tipos de aviones:"), gbc);
        gbc.gridx = 1;
        tipoCombo = new JComboBox<>();
        panel.add(tipoCombo, gbc);
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
     * Asigna el controlador de eventos a los botones de dar de alta un avion
     * y de volver.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        botonVolver.addActionListener(e);
        botonDarAlta.addActionListener(e);
    }

    /**
     * Retorna el texto actual en el campo de texto "Año de Compra".
     * 
     * @return el texto actual en el campo de texto "Año de Compra"
     */
    public String getAnoCompra() {
        return anoCompra.getText();
    }

    /**
     * Retorna el texto actual en el campo de texto "Fecha de última revisión".
     * 
     * @return el texto actual en el campo de texto "Fecha de última revisión"
     */
    public String getFechaRevision() {
        return fechaRevision.getText();
    }

    /**
     * Retorna el tipo de avión seleccionado en el JComboBox.
     * 
     * @return el tipo de avión seleccionado en el JComboBox
     */
    public TipoAvion getTipoAvionSeleccionado() {
        return (TipoAvion) tipoCombo.getSelectedItem();
    }

    /**
     * Asigna la lista de tipos de aviones que se muestran en el JComboBox.
     * 
     * @param tipos la lista de tipos de aviones a mostrar en el JComboBox
     */
    public void setTiposAvion(List<TipoAvion> tipos) {
        tipoCombo.removeAllItems();

        tipoCombo.addItem(new TipoAvionMercancia("vacío", "vacío", new Dimension(0, 0, 0), 0.0, false, false, 0.0) {
            @Override
            public String toString() {
                return "";
            }
        });

        for (TipoAvion tipo : tipos) {
            tipoCombo.addItem(tipo);
        }
    }
}
