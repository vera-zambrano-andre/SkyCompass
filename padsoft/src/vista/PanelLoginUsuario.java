package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Clase que representa el panel de inicio de sesión específico para un tipo de usuario
 * (Gestor, Operador o Controlador).
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class PanelLoginUsuario extends JPanel {

    private JTextField textFieldDni;

    private JButton loginButton;

    private JButton cancelarButton;

    private JPasswordField password;

    private String tipo;

    /**
     * Constructor que inicializa la interfaz del panel de login según el tipo de usuario.
     *
     * @param frame la ventana principal (no se usa directamente en esta clase)
     * @param tipo el tipo de usuario que está iniciando sesión
     */
    public PanelLoginUsuario(VentanaPrincipal frame, String tipo) {
        this.tipo = tipo;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(40, 20, 40, 20));

        JLabel inicioSesion = new JLabel(tipo);
        inicioSesion.setFont(new Font("Arial", Font.BOLD, 22));
        inicioSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelDNI = new JPanel();
        textFieldDni = new JTextField(12);
        textFieldDni.setPreferredSize(new Dimension(150, 25));
        textFieldDni.setMaximumSize(new Dimension(150, 25));

        if (!tipo.equals(VentanaPrincipal.LOGINGESTOR)) {
            panelDNI.setLayout(new BoxLayout(panelDNI, BoxLayout.X_AXIS));
            panelDNI.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel dni = new JLabel("DNI:");
            dni.setPreferredSize(new Dimension(60, 25));
            panelDNI.add(dni);
            panelDNI.add(Box.createRigidArea(new Dimension(10, 0)));
            panelDNI.add(textFieldDni);
        }

        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new BoxLayout(panelPassword, BoxLayout.X_AXIS));
        panelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Contraseña:");
        label.setPreferredSize(new Dimension(80, 25));

        password = new JPasswordField(12);
        password.setPreferredSize(new Dimension(150, 25));
        password.setMaximumSize(new Dimension(150, 25));

        panelPassword.add(label);
        panelPassword.add(Box.createRigidArea(new Dimension(10, 0)));
        panelPassword.add(password);

        loginButton = new JButton("Iniciar sesión");
        loginButton.setPreferredSize(new Dimension(120, 30));

        cancelarButton = new JButton("Cancelar");
        cancelarButton.setPreferredSize(new Dimension(120, 30));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBotones.add(loginButton);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(cancelarButton);

        this.add(inicioSesion);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(separator);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        if (!tipo.equals(VentanaPrincipal.LOGINGESTOR)) {
            this.add(panelDNI);
            this.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        this.add(panelPassword);
        this.add(Box.createRigidArea(new Dimension(0, 25)));
        this.add(panelBotones);

        this.setVisible(false);
    }

    /**
     * Devuelve el valor introducido en el campo de DNI.
     * 
     * @return el texto del DNI
     */
    public String getDni() {
        return textFieldDni.getText();
    }

    /**
     * Devuelve el valor introducido en el campo de contraseña.
     * 
     * @return la contraseña como String
     */
    public String getPassword() {
        return new String(password.getPassword());
    }

    /**
     * Asigna un ActionListener a los botones de login y cancelar.
     * 
     * @param c el ActionListener a registrar
     */
    public void setControlador(ActionListener c) {
        loginButton.addActionListener(c);
        cancelarButton.addActionListener(c);
    }

    /**
     * Devuelve el tipo de usuario para este panel (Gestor, Operador, Controlador).
     * 
     * @return el tipo de usuario
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Reinicia los campos de texto y pone el foco en el DNI o contraseña.
     */
    public void update() {
        textFieldDni.setText("");
        textFieldDni.grabFocus();
        password.setText("");
        password.grabFocus();
    }
}
