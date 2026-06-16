package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import sistema.*;
import javax.swing.border.*;
import vuelo.*;
import java.util.List;

/**
 * Clase que representa la tabla principal
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TablaPrincipal extends JPanel {
    private DefaultTableModel model;

    /**
     * Constructor de la clase TablaPrincipal
     */
    public TablaPrincipal() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 0, 0, 0));

        String[] columnNames = {"Id", "Origen", "Destino", "Aerolínea", "Terminal", "Modelo Avión", "Fecha Inicio", "Fecha Fin" ,"Tipo"};
        
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
     * Actualiza la tabla con la lista de vuelos programados proporcionada.
     * @param vuelosProgramados Lista de vuelos programados a mostrar
     */
    public void actualizarTabla(List<VueloProgramado> vuelosProgramados) {
        model.setRowCount(0);
        for (VueloProgramado vp : vuelosProgramados) {
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

