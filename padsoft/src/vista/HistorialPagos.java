package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import factura.Factura;
import java.util.List;

/**
 * Panel que muestra el historial de pagos en una tabla.
 * Incluye un botón para volver a la vista anterior.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class HistorialPagos extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    /**
     * Constructor del panel de historial de pagos.
     *
     * @param frame Referencia al marco principal para permitir el cambio de vista.
     * @param next  Nombre del panel al que se debe volver al presionar "Volver".
     */
    public HistorialPagos(VentanaPrincipal frame, String next) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20)); 

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonVolver = new JButton("← Volver");
        botonVolver.addActionListener((ActionEvent e) -> {
            frame.changeVisiblePanel(next);
        });
        barraSuperior.add(botonVolver);
        this.add(barraSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Historial de Pagos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLUE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(titulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] columnNames = {"ID Factura", "Aerolínea", "Fecha", "Precio base", "Recargo", "Precio recursos", "Precio total (sin aplicar descuentos)", "Precio final (ya aplicado descuentos)", "Pagada"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);


         JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 300));
        panelCentral.add(scrollPane);

        panelCentral.add(scrollPane);

        this.add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Carga una lista de facturas en la tabla del historial.
     * Convierte los datos numéricos a formato de texto con símbolo de euro.
     * 
     * @param facturas Lista de objetos Factura a mostrar
     */
    public void cargarFacturas(List<Factura> facturas) {
        model.setRowCount(0);
        for (Factura factura : facturas) {
            model.addRow(new Object[] {
                factura.getInvoiceIdentifier(),
                factura.getAirline(),
                factura.getInvoiceDate(),
                String.format("%.2f", factura.getBasePrice()) + " €",
                String.format("%.2f", factura.getSurcharge()) + " €",
                String.format("%.2f", factura.getPrecioRecursosSinDescuento()) + " €",
                String.format("%.2f", factura.getPrecioTotalSinDecuento()) + " €",
                String.format("%.2f", factura.getPrice()) + " €",
                factura.isPagada() ? "Sí" : "No"
            });
        }
    }
    
}
