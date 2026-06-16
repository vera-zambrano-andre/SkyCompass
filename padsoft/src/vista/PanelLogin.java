package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa el panel de login de SkyCompass.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class PanelLogin extends JPanel {

    private ImageIcon imageIcon;

    private JButton volver;

    private JButton loginGestor;
    private JButton loginOperador;
    private JButton loginControlador;

    /**
     * Constructor del panel de login.
     *
     * @param frame la ventana principal que gestiona los paneles
     */
    public PanelLogin(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());

        imageIcon = new ImageIcon(getClass().getClassLoader().getResource("SkyCompass.jpg"));

        loginGestor = new JButton("Gestor");
        loginGestor.setPreferredSize(new Dimension(140, 40));
        loginOperador = new JButton("Operador");
        loginOperador.setPreferredSize(new Dimension(140, 40));
        loginControlador = new JButton("Controlador");
        loginControlador.setPreferredSize(new Dimension(140, 40));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setOpaque(false); 
        panelBotones.add(loginGestor);
        panelBotones.add(loginOperador);
        panelBotones.add(loginControlador);

        JLabel inicioSesion = new JLabel("¿Qué tipo de usuario eres?");
        inicioSesion.setFont(new Font("Arial", Font.BOLD, 20));
        inicioSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setPreferredSize(new Dimension(0, 120));
        panelInferior.setBackground(Color.WHITE);
        panelInferior.add(Box.createVerticalStrut(10));
        panelInferior.add(inicioSesion);
        panelInferior.add(Box.createVerticalStrut(10));
        panelInferior.add(panelBotones);
        panelInferior.add(Box.createVerticalStrut(10));

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imageIcon != null) {
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    int imgOriginalWidth = imageIcon.getIconWidth();
                    int imgOriginalHeight = imageIcon.getIconHeight();

                    double scale = 1;
                    int imgWidth = (int)(imgOriginalWidth * scale);
                    int imgHeight = (int)(imgOriginalHeight * scale);

                    int x = (panelWidth - imgWidth) / 2;
                    int y = (panelHeight - imgHeight) / 2;

                    g.drawImage(imageIcon.getImage(), x, y, imgWidth, imgHeight, this);
                }
            }
        };
        imagePanel.setLayout(new BorderLayout());

        this.add(imagePanel, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);

        volver = new JButton("← Volver");
        volver.setPreferredSize(new Dimension(100, 30));

        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelVolver.add(volver);
        this.add(panelVolver, BorderLayout.NORTH);

        this.setVisible(false);
    }

    /**
     * Permite asignar un ActionListener común a todos los botones del panel.
     * Esto facilita la gestión de eventos desde el controlador principal.
     *
     * @param e el ActionListener a asignar
     */
    public void setControlador(ActionListener e) {
        volver.addActionListener(e);
        loginGestor.addActionListener(e);
        loginOperador.addActionListener(e);
        loginControlador.addActionListener(e);
    }
}
