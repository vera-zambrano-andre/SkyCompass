package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import sistema.SkyCompass;
import vuelo.VueloProgramado;

import java.util.Collection;
import java.util.List;

/**
 * Clase que representa la tabla de vuelos programados.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TablaProgramado extends JPanel {
    private final SkyCompass app;
    private DefaultTableModel model;

    /**
     * Constructor de la clase TablaProgramado.
     * 
     * @param frame Ventana principal
     * @param next  Panel al que volver
     * @param app   Instancia de SkyCompass para obtener datos
     */
    public TablaProgramado(VentanaPrincipal frame, String next, SkyCompass app) {
        this.app = app;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonVolver = new JButton("← Volver");
        botonVolver.addActionListener(e -> frame.changeVisiblePanel(next));
        barraSuperior.add(botonVolver);
        add(barraSuperior, BorderLayout.NORTH);

        add(barraSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Vuelos Programados");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLUE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(titulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] columnNames = { "Id", "Origen", "Destino", "Aerolínea", "Terminal", "Modelo Avión", "Fecha Inicio",
                "Fecha Fin", "Tipo" };

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);
        panelCentral.add(scrollPane);
        add(panelCentral, BorderLayout.CENTER);

        refrescarTabla();
    }

    /**
     * Actualiza la tabla con la lista de vuelos.
     */
    public void refrescarTabla() {
        model.setRowCount(0);
        for (VueloProgramado vp : app.getVuelosProgramados().values()) {
            Object[] row = {
                    vp.getCodigo(),
                    vp.getOrigen().getNombre(),
                    vp.getDestino().getNombre(),
                    vp.getAerolinea().getNombre(),
                    vp.getTerminal().getCodigo(),
                    vp.getAvion().getTipo().getModelo(),
                    vp.getFrecuencia().getFechaInicio().toString(),
                    vp.getFrecuencia().getFechaFin().toString(),
                    vp.getTipo()
            };
            model.addRow(row);
        }
    }
}
