package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import sistema.*;
import javax.swing.border.*;
import vuelo.Vuelo;
import java.util.List;

/**
 * Clase que representa la tabla de vuelos de hoy.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TablaVuelosHoy extends JPanel {
    private DefaultTableModel model;

    /**
     * Constructor de la clase TablaVuelosHoy
     */
    public TablaVuelosHoy() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        

        String[] columnNames = {"Id", "Origen", "Destino", "Aerolínea", "Terminal", "Fecha", "Hora salida", "Hora llegada", "Tipo"};
        
        model = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        

        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Actualiza la tabla con la lista de vuelos proporcionada.
     * @param vuelos Lista de vuelos a mostrar
     */
    public void actualizarTabla(List<Vuelo> vuelos) {
        model.setRowCount(0);
        for (Vuelo v : vuelos) {
            Object[] row = {
                v.getVueloProgramado().getCodigo(),
                v.getVueloProgramado().getOrigen().getNombre(),
                v.getVueloProgramado().getDestino().getNombre(),
                v.getVueloProgramado().getAerolinea().getNombre(),
                v.getTerminal().getCodigo(),
                v.getFecha().toString(),
                v.getHoraSalida().toString(),
                v.getHoraLlegada().toString(),
                v.getVueloProgramado().getTipo()
            };
            model.addRow(row);
        }
    }
    
}


