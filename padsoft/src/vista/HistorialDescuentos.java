package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import factura.Descuento;
import factura.Factura;
import java.util.List;

/**
 * Panel que muestra el historial de decuentos en una tabla.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class HistorialDescuentos extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private JButton botonVolver;
    private JButton botonEditarPeriodo;
    private JButton botonEliminarPeriodo;
    private JLabel labelInfo;
    private JLabel labelAccion;
    private JComboBox<String> comboAccion;
    private JTextField campoFechaInicio;
    private JTextField campoFechaFin;
    private JLabel fechaInicioLabel;
    private JLabel fechaFinLabel;

    /**
     * Constructor del panel de historial de descuentos.
     *
     * @param frame Referencia al marco principal para permitir el cambio de vista.
     */
    public HistorialDescuentos(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");

        barraSuperior.add(botonVolver);
        this.add(barraSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Descuentos de la aplicación");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLUE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(titulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] columnNames = { "Descripción", "Fecha inicio", "Fecha fin", "Porcentaje", "Condición", "Tipo" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 300));
        panelCentral.add(scrollPane);

        panelCentral.add(scrollPane);

        labelInfo = new JLabel("Pulsa una fila para ver sus opciones");
        labelAccion = new JLabel("Elige una acción sobre el descuento:");
        labelAccion.setVisible(false);

        comboAccion = new JComboBox<>(new String[] { "", "Editar periodo", "Eliminar periodo" });
        comboAccion.setVisible(false);

        campoFechaInicio = new JTextField(10);
        campoFechaFin = new JTextField(10);
        campoFechaInicio.setVisible(false);
        campoFechaFin.setVisible(false);

        JPanel panelAcciones = new JPanel();
        panelAcciones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelAcciones.add(labelInfo);
        panelAcciones.add(labelAccion);
        panelAcciones.add(comboAccion);

        fechaInicioLabel = new JLabel("Fecha Inicio:");
        fechaFinLabel = new JLabel("Fecha Fin:");
        fechaInicioLabel.setVisible(false);
        fechaFinLabel.setVisible(false);

        JPanel panelFechas = new JPanel();
        panelFechas.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelFechas.add(fechaInicioLabel);
        panelFechas.add(campoFechaInicio);
        panelFechas.add(fechaFinLabel);
        panelFechas.add(campoFechaFin);

        panelCentral.add(panelAcciones);
        panelCentral.add(panelFechas);

        botonEditarPeriodo = new JButton("Editar");
        botonEditarPeriodo.setVisible(false);

        botonEliminarPeriodo = new JButton("Eliminar");
        botonEliminarPeriodo.setVisible(false);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(botonEditarPeriodo);
        panelBotones.add(botonEliminarPeriodo);

        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(panelBotones);

        this.add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Carga la tabla con los datos de una lista de descuentos.
     * 
     * @param descuentos Lista de objetos Descuento
     */
    public void cargarDescuentos(List<Descuento> descuentos) {
        model.setRowCount(0);
        for (Descuento descuento : descuentos) {
            model.addRow(new Object[] {
                    descuento.getDescripcion(),
                    descuento.getFechaInicio() == null ? "No disponible" : descuento.getFechaInicio(),
                    descuento.getFechaFin() == null ? "No disponible" : descuento.getFechaFin(),
                    descuento.getPorcentaje() + " %",
                    descuento.getCondicion(),
                    descuento.isAplicableTotal() ? "Aplicable total" : "Aplicable solo a recursos"
            });
        }
    }

    /**
     * Asigna un único ActionListener a todos los botones y componentes de acción.
     * 
     * @param e Listener que maneja las acciones
     */
    public void setControlador(ActionListener e) {
        botonVolver.addActionListener(e);
        botonEditarPeriodo.addActionListener(e);
        botonEliminarPeriodo.addActionListener(e);
        comboAccion.addActionListener(e);
    }

    /**
     * Añade un listener para la selección de filas en la tabla.
     * 
     * @param listener Listener que manejará los cambios de selección
     */
    public void addListSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * Devuelve la tabla que muestra los descuentos.
     *
     * @return JTable con los descuentos
     */
    public JTable getTabla() {
        return table;
    }

    /**
     * Devuelve el botón para editar el periodo de un descuento.
     *
     * @return JButton para editar el periodo
     */

    public JButton getBotonEditarPeriodo() {
        return botonEditarPeriodo;
    }

    /**
     * Devuelve el botón para eliminar el periodo de un descuento.
     *
     * @return JButton para eliminar el periodo
     */
    public JButton getBotonEliminarPeriodo() {
        return botonEliminarPeriodo;
    }

    /**
     * Muestra u oculta los componentes de selección de acción.
     * 
     * @param visible true para mostrar, false para ocultar
     */
    public void mostrarSelectorAccion(boolean visible) {
        labelAccion.setVisible(visible);
        comboAccion.setVisible(visible);
    }

    /**
     * Devuelve la variable comboAccion.
     * 
     * @return La variable comboAccion
     */
    public JComboBox<String> getComboAccion() {
        return comboAccion;
    }

    /**
     * Devuelve la variable fechaInicio.
     * 
     * @return La variable fechaInicio
     */
    public JTextField getCampoFechaInicio() {
        return campoFechaInicio;
    }

    /**
     * Devuelve la variable fechaFin.
     * 
     * @return La variable fechaFin
     */
    public JTextField getCampoFechaFin() {
        return campoFechaFin;
    }

    /**
     * Devuelve la variable fechaInicioLabel.
     * 
     * @return La variable fechaInicioLabel
     */
    public JLabel getFechaInicioLabel() {
        return fechaInicioLabel;
    }

    /**
     * Devuelve la variable fechaFinLabel.
     * 
     * @return La variable fechaFinLabel
     */
    public JLabel getFechaFinLabel() {
        return fechaFinLabel;
    }

    /**
     * Limpia los campos de fecha y resetea la selección de acción.
     */
    public void limpiar() {
        campoFechaInicio.setText("");
        campoFechaFin.setText("");
        comboAccion.setSelectedIndex(0);
    }

    /**
     * Devuelve la variable labelInfo.
     * 
     * @return La variable labelInfo
     */
    public JLabel getLabelInfo() {
        return labelInfo;
    }

}
