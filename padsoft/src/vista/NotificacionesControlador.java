package vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import usuario.Notificacion;

/**
 * Clase que representa la vista de las notificaciones del gestor.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class NotificacionesControlador extends NotificacionesBase {

    private final VentanaPrincipal frame;
    private final JButton botonVolver;
    private final JTable tablaMensajes;
    private final DefaultTableModel model;
    private final JTextArea contenidoMensaje;

    /**
     * Constructor de la vista de notificaciones para el controlador.
     * Inicializa la interfaz con tabla, botón volver y área de contenido.
     * 
     * @param frame Referencia a la ventana principal
     */
    public NotificacionesControlador(VentanaPrincipal frame) {
        this.frame = frame;
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        botonVolver = new JButton("← Volver");
        botonVolver.addActionListener(e -> frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALCONTROLADOR));
        topPanel.add(botonVolver, BorderLayout.WEST);

        JLabel titulo = new JLabel("Notificaciones");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titulo, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        String[] columnas = { "Notificación" };
        model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tablaMensajes = new JTable(model);
        tablaMensajes.setModel(model);
        tablaMensajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tablaMensajes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object val,
                    boolean isSel, boolean hasFoc,
                    int row, int col) {
                Component c = super.getTableCellRendererComponent(tbl, val, isSel, hasFoc, row, col);
                String asunto = val.toString();
                if (asunto.toLowerCase().contains("solicitud")) {
                    c.setBackground(Color.YELLOW);
                }
                else if(asunto.toLowerCase().contains("modo")){
                    c.setBackground(Color.CYAN);
                }
                else {
                    c.setBackground(isSel ? tbl.getSelectionBackground() : Color.WHITE);
                }
                return c;
            }
        });

        add(new JScrollPane(tablaMensajes), BorderLayout.CENTER);

        contenidoMensaje = new JTextArea(5, 30);
        contenidoMensaje.setEditable(false);
        contenidoMensaje.setLineWrap(true);
        contenidoMensaje.setWrapStyleWord(true);
        JScrollPane scrollContenido = new JScrollPane(contenidoMensaje);
        scrollContenido.setBorder(BorderFactory.createTitledBorder("Contenido del mensaje"));
        add(scrollContenido, BorderLayout.SOUTH);

        tablaMensajes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tablaMensajes.getSelectedRow();
                if (fila >= 0) {
                    Object obj = tablaMensajes.getValueAt(fila, 0);
                    contenidoMensaje.setText(obj.toString());
                }
            }
        });

    }

    /**
     * Actualiza la tabla con la lista de notificaciones.
     */
    public void actualizarTabla(List<Notificacion> notificaciones) {
        model.setRowCount(0);
        for (Notificacion notif : notificaciones) {
            String fila = "[" + notif.getFecha() + " " + notif.getHora() + "] " + notif.getNotificacion();
            model.addRow(new Object[] { fila });
        }
        if (!notificaciones.isEmpty()) {
            tablaMensajes.setRowSelectionInterval(0, 0);
        }
    }

    /**
     * Devuelve el botón "Volver" para asignar controladores o acceder desde otras clases.
     * 
     * @return JButton del botón volver
     */
    public JButton getbotonVolver() {
        return botonVolver;
    }

    /**
     * Devuelve la tabla que muestra las notificaciones.
     * 
     * @return JTable con las notificaciones
     */
    public JTable getTablaMensajes() {
        return tablaMensajes;
    }

    /**
     * Devuelve la referencia a la ventana principal.
     * 
     * @return VentanaPrincipal donde está integrada esta vista
     */
    public VentanaPrincipal getFrame() {
        return frame;
    }
}
