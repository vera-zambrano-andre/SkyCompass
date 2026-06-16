package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import org.bouncycastle.jcajce.provider.drbg.DRBG.Default;

import java.util.List;

import vuelo.Vuelo;
import vuelo.VueloProgramado;

/**
 * Clase que representa la vista de solicitud de vuelos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class SolicitudVuelos extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private JButton botonAceptar = new JButton("Aceptar");
    private JButton rechazarBoton = new JButton("Rechazar");
    private JButton botonVolver = new JButton("← Volver");
    private JPanel panelBotones = new JPanel();
    private List<VueloProgramado> solicitudesActuales;

    /**
     * Constructor de la clase SolicitudVuelos.
     * @param frame la ventana principal
     */
    public SolicitudVuelos(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));        
        barraSuperior.add(botonVolver);
        this.add(barraSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Solicitudes de Vuelos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLUE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(titulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] columnNames = {"ID", "Modelo", "Aerolínea", "Origen", "Destino", "Salida", "Llegada", "Tipo"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setMaximumSize(new Dimension(900, 300));

        panelCentral.add(scrollPane);

        
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBotones.add(botonAceptar);
        panelBotones.add(rechazarBoton);
        panelBotones.setVisible(false);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelCentral.add(panelBotones);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                panelBotones.setVisible(true);
            } else {
                panelBotones.setVisible(false);
            }
        });


        this.add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * Asigna el controlador de eventos a los botones de cargar solicitudes, 
     * aceptar, rechazar y volver.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        botonAceptar.addActionListener(e);
        rechazarBoton.addActionListener(e);
        botonVolver.addActionListener(e);
    }

    /**
     * Actualiza la tabla con la lista de vuelos programados proporcionada.
     * La lista se guarda en una variable de instancia para poder
     * acceder a los datos de los vuelos seleccionados.
     * 
     * @param solicitudes lista de vuelos programados a mostrar
     */
    public void actualizarTabla(List<VueloProgramado> solicitudes) {
        this.solicitudesActuales = solicitudes; 
        model.setRowCount(0);
    
        for (VueloProgramado vp : solicitudes) {
            Object[] row = {
                vp.getCodigo(),
                vp.getAvion().getTipo().getModelo(),
                vp.getAerolinea().getNombre(),
                vp.getOrigen().getNombre(),
                vp.getDestino().getNombre(),
                vp.getFrecuencia().getFechaInicio().toString(),      
                vp.getFrecuencia().getFechaFin().toString(),
                vp.getTipo()
            };
            model.addRow(row);
        }
    }

    

    /**
     * Devuelve el modelo de la tabla de vuelos programados.
     * @return el modelo de la tabla de vuelos programados
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * Devuelve la tabla que contiene la lista de vuelos programados.
     * @return la tabla que contiene la lista de vuelos programados
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Devuelve el panel que contiene los botones para aceptar o rechazar
     * una solicitud de vuelo.
     * @return el panel que contiene los botones de aceptar y rechazar
     */
    public JPanel getPanelBotones() {
        return panelBotones;
    }

    /**
     * Devuelve el vuelo programado seleccionado en la tabla de solicitudes de vuelos.
     * Si no hay una fila seleccionada o la lista de solicitudes actuales es nula, devuelve null.
     * @return el vuelo programado seleccionado o null si no hay una fila seleccionada
     */
    public VueloProgramado getVueloSeleccionado() {
        int fila = table.getSelectedRow();
        if (fila != -1 && solicitudesActuales != null && fila < solicitudesActuales.size()) {
            return solicitudesActuales.get(fila);
        }
        return null;
    }
}
