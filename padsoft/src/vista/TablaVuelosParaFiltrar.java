package vista;

import vuelo.Vuelo;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la vista de la tabla de vuelos para filtrado.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TablaVuelosParaFiltrar extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final VentanaPrincipal frame;
    private final String anterior;

    /**
     * 
     * Constructor de la clase TablaVuelosParaFiltrar.
     * 
     * @param frame    Ventana principal para navegación
     * @param anterior Identificador del panel al que volver
     */
    public TablaVuelosParaFiltrar(VentanaPrincipal frame, String anterior) {
        this.frame = frame;
        this.anterior = anterior;
        setLayout(new BorderLayout());

        String[] columnNames = { "ID", "Origen", "Destino", "Aerolínea", "Terminal", "Modelo", "Fecha Salida",
                "Fecha Llegada", "Tipo" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cerrarBoton = new JButton("Cerrar");
        cerrarBoton.addActionListener(e -> frame.changeVisiblePanel(anterior));
        bottomPanel.add(cerrarBoton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Actualiza la tabla con la lista de vuelos proporcionada.
     * 
     * @param vuelos Lista de vuelos a mostrar
     */
    public void actualizarDatos(List<Vuelo> vuelos) {
        tableModel.setRowCount(0);
        for (Vuelo v : vuelos) {
            Object[] row = {
                    v.getVueloProgramado().getCodigo(),
                    v.getVueloProgramado().getOrigen().getNombre(),
                    v.getVueloProgramado().getDestino().getNombre(),
                    v.getVueloProgramado().getAerolinea().getNombre(),
                    v.getTerminal() != null ? v.getTerminal().getCodigo() : "",
                    v.getVueloProgramado().getAvion().getTipo().getModelo(),
                    v.getFecha().toString(),
                    v.getVueloProgramado().getFrecuencia().getFechaFin().toString(),
                    v.getVueloProgramado().getTipo()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Obtiene la tabla de vuelos filtrados.
     * 
     * @return la tabla de vuelos filtrados
     */
    public JTable getTable() {
        return table;
    }
}
