package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Panel spara gestionar los estados de un vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class GestionEstadoVueloOperador extends JPanel {
    private final JTextField codigoField = new JTextField(10);
    private final JLabel estadoLabel = new JLabel("Estado: ");
    private final JPanel botonesPanel = new JPanel(new GridLayout(0, 1, 5, 5));
    private final JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JButton volverBoton = new JButton("← Volver");
    private final JButton buscarBoton = new JButton("Buscar");
    private final JLabel fechaLabel = new JLabel("Fecha del vuelo (AAAA-MM-DD):");
    private final JTextField fechaField = new JTextField(10);

    /**
     * Constructor. Inicializa la interfaz gráfica para la gestión del estado de vuelos.
     */
    public GestionEstadoVueloOperador() {
        setLayout(new BorderLayout(10, 10));

        volverBoton.setActionCommand("volver");
        topBar.add(volverBoton);
        add(topBar, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        center.add(new JLabel("Código de vuelo:"));
        buscarBoton.setActionCommand("buscar");
        center.add(codigoField);
        center.add(fechaLabel);
        center.add(fechaField);
        center.add(buscarBoton);
        center.add(estadoLabel);
        
        add(center, BorderLayout.CENTER);

        botonesPanel.setBorder(BorderFactory.createTitledBorder("Acciones"));
        String[] acciones = {
            "iniciarEmbarque", "finalizarEmbarque","iniciarDesembarque", "finalizarDesembarque",
        };
        for (String acc : acciones) {
            JButton boton = new JButton(acc);
            boton.setActionCommand(acc);
            boton.setEnabled(false);
            botonesPanel.add(boton);
        }
        add(botonesPanel, BorderLayout.SOUTH);
    }

    
    /**
     * Asigna el controlador de eventos a los componentes interactivos.
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        buscarBoton.addActionListener(e);
        volverBoton.addActionListener(e);
        for (Component c : botonesPanel.getComponents()) {
            ((JButton)c).addActionListener(e);
        }
    }

    
    /**
     * Devuelve la variable codigoField.
     * 
     * @return el campo de texto para el código.
     */
    public JTextField getCodigoField() { 
        return codigoField; 
    }

    /**
     * Devuelve la variable codigoField.
     * 
     * @return el campo de texto para el código.
     */
    public JTextField getFechaField() { 
        return fechaField; 
    }

    /**
     * Devuelve la variable estadoLabel.
     * 
     * @return la etiqueta de estado.
     */
    public JLabel getEstadoLabel() { 
        return estadoLabel; 
    }

    /**
     * Devuelve la variable botonesPanel.
     * 
     * @return el panel de botones.
     */
    public JPanel getBotonesPanel() { 
        return botonesPanel; 
    }

    /**
     * Devuelve la variable volverBoton.
     * 
     * @return el botón de volver.
     */
    public JButton getVolverBoton() { 
        return volverBoton; 
    }

    /**
     * Devuelve la variable buscarBoton.
     * 
     * @return el botón de buscar.
     */
    public JButton getBuscarBoton() { 
        return buscarBoton; 
    }
}
