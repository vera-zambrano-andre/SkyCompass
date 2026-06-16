package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import usuario.Operador;

/**
 * Clase del panel para resetear datos de un usuario por su DNI.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Resetear extends JPanel {

    private JButton botonVolver;
    private JButton resetearBoton;
    private JPanel operadoresBloqueadosPanel;
    private JScrollPane scrollOperadoresBloqueados;
    private List<JCheckBox> checkboxes;

    /**
     * Constructor del panel Resetear.
     * Configura la interfaz con botones y la lista de operadores bloqueados.
     * 
     * @param frame Ventana principal a la que pertenece este panel
     */
    public Resetear(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");
        topPanel.add(botonVolver);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        operadoresBloqueadosPanel = new JPanel();
        operadoresBloqueadosPanel.setLayout(new BoxLayout(operadoresBloqueadosPanel, BoxLayout.Y_AXIS));
        scrollOperadoresBloqueados = new JScrollPane(operadoresBloqueadosPanel);
        scrollOperadoresBloqueados.setPreferredSize(new Dimension(250, 150));

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Selecciona operadores a resetear:"), gbc);

        gbc.gridy = 1;
        centerPanel.add(scrollOperadoresBloqueados, gbc);

        gbc.gridy = 2;
        resetearBoton = new JButton("Resetear");
        centerPanel.add(resetearBoton, gbc);

        this.add(centerPanel, BorderLayout.CENTER);

        checkboxes = new ArrayList<>();
    }

    /**
     * Permite asignar un ActionListener común a todos los botones del panel.
     * Esto facilita la gestión de eventos desde el controlador principal.
     *
     * @param e el ActionListener a asignar
     */
    public void setControlador(ActionListener e) {
        botonVolver.addActionListener(e);
        resetearBoton.addActionListener(e);
    }

    /**
     * Establece la lista de operadores bloqueados que se mostrarán en el panel.
     * Sólo se incluyen los operadores que tienen 3 o más fallos.
     * 
     * @param operadores La lista de operadores a mostrar
     */
    public void setOperadoresBloqueados(List<Operador> operadores) {
        operadoresBloqueadosPanel.removeAll();
        checkboxes.clear();

        for (Operador o : operadores) {
            if (o.getFallos() >= 3) { 
                JCheckBox check = new JCheckBox(o.getDni() + " - " + o.getNombre());
                checkboxes.add(check);
                operadoresBloqueadosPanel.add(check);
            }
        }

        operadoresBloqueadosPanel.revalidate();
        operadoresBloqueadosPanel.repaint();
    }

    /**
     * Devuelve la lista de DNIs de los operadores seleccionados por el usuario.
     * 
     * @return Lista de DNIs como Strings
     */
    public List<String> getDnisSeleccionados() {
        List<String> seleccionados = new ArrayList<>();
        for (JCheckBox cb : checkboxes) {
            if (cb.isSelected()) {
                String dni = cb.getText().split(" - ")[0];
                seleccionados.add(dni);
            }
        }
        return seleccionados;
    }

    /**
     * Deselecciona todos los checkboxes del panel.
     * Se puede usar tras realizar una acción o para limpiar la vista.
     */
    public void update() {
        for (JCheckBox cb : checkboxes) {
            cb.setSelected(false);
        }
    }

}
