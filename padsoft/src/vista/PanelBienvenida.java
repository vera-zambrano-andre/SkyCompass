package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Clase que representa el panel de bienvenida de la aplicación SkyCompass.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class PanelBienvenida extends JPanel {

    /** Botón que permite al usuario comenzar e ir al login */
    private JButton comienzo;

    /**
     * Constructor del panel de bienvenida.
     *
     * @param frame la ventana principal que contiene y gestiona los paneles
     */
    public PanelBienvenida(VentanaPrincipal frame) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBorder(new EmptyBorder(50, 50, 50, 50)); 

        JLabel bienvenida = new JLabel("¡Bienvenido/a a SkyCompass! Aquí podrás gestionar tus vuelos.");
        comienzo = new JButton("Comenzar");
        bienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        comienzo.setFont(new Font("Arial", Font.BOLD, 24));

        bienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        comienzo.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension maxSize = new Dimension(400, 80);
        comienzo.setMaximumSize(maxSize);
        comienzo.setPreferredSize(maxSize);
        comienzo.setMinimumSize(maxSize);

        this.add(Box.createVerticalStrut(150));
        this.add(bienvenida);
        this.add(Box.createRigidArea(new Dimension(0, 75)));
        this.add(comienzo);
        this.add(Box.createVerticalGlue()); 

        this.setVisible(true);

        comienzo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.changeVisiblePanel(VentanaPrincipal.LOGIN);
            }
        });
    }

    /**
     * Permite asignar un controlador externo al botón de comenzar.
     * Esto puede usarse para pruebas o controladores personalizados.
     *
     * @param e ActionListener a asignar al botón
     */
    public void setControlador(ActionListener e) {
        comienzo.addActionListener(e);
    }
}

