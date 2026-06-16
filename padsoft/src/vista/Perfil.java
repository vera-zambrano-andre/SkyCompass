package vista;

import java.awt.*;
import javax.swing.*;

import sistema.SkyCompass;
import usuario.Controlador;
import usuario.Operador;

/**
 * Clase que muestra la información de perfil del usuario actualmente logueado
 * en la aplicación SkyCompass. 
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano 
 */
public class Perfil extends JPanel {
    
    private SkyCompass app;
    private JTextField nombreField;
    private JTextField dniField;
    private JTextField rolField;
    private JPasswordField passwordField;

    /**
     * Constructor que inicializa el panel de perfil con los datos del usuario actual.
     *
     * @param frame la ventana principal de la aplicación
     * @param app la lógica de negocio que contiene al usuario autenticado
     * @param ant el panel anterior
     */
    public Perfil(VentanaPrincipal frame, SkyCompass app, String ant) {
        this.setLayout(new BorderLayout());
        this.app = app;

        String nombreGuardado = app.getUsuarioLogeado().getNombre();
        String dniGuardado = app.getUsuarioLogeado().getDni();
        String passwordGuardado = app.getUsuarioLogeado().getPassword();
        String rolGuardado = obtenerRol(app.getUsuarioLogeado());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonVolver = new JButton("← Volver");
        botonVolver.addActionListener(e -> {
            frame.changeVisiblePanel(ant);
        });
        topPanel.add(botonVolver);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Nombre"), gbc);
        gbc.gridy++;
        contentPanel.add(new JLabel("DNI"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        nombreField = crearCampoTexto(nombreGuardado);
        contentPanel.add(nombreField, gbc);

        gbc.gridy++;
        dniField = crearCampoTexto(dniGuardado);
        contentPanel.add(dniField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Rol"), gbc);
        gbc.gridy++;
        contentPanel.add(new JLabel("Contraseña"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        rolField = crearCampoTexto(rolGuardado);
        contentPanel.add(rolField, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField(passwordGuardado, 12);
        passwordField.setEditable(false);
        passwordField.setFocusable(false);
        passwordField.setEchoChar('\u2022');
        contentPanel.add(passwordField, gbc);

        gbc.gridy++;
        JCheckBox verPassword = new JCheckBox("Ver Contraseña");
        verPassword.setEnabled(true);
        verPassword.addActionListener(e -> {
            passwordField.setEchoChar(verPassword.isSelected() ? (char) 0 : '\u2022');
        });
        contentPanel.add(verPassword, gbc);

        this.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(false);
    }

    /**
     * Actualiza los campos del panel con la información más reciente
     * del usuario logueado en el sistema.
     */
    public void actualizarPerfil() {
        var usuario = app.getUsuarioLogeado();
        nombreField.setText(usuario.getNombre());
        dniField.setText(usuario.getDni());
        passwordField.setText(usuario.getPassword());
        rolField.setText(obtenerRol(usuario));
    }

    /**
     * Devuelve el rol del usuario como una cadena legible.
     *
     * @param usuario el usuario actual
     * @return "Operador", "Controlador" o "Gestor"
     */
    private String obtenerRol(Object usuario) {
        if (usuario instanceof Operador) {
            return "Operador";
        } else if (usuario instanceof Controlador) {
            return "Controlador";
        } else {
            return "Gestor";
        }
    }

    /**
     * Crea un campo de texto no editable y no enfocable con el contenido dado.
     *
     * @param contenido el texto a mostrar
     * @return un JTextField preconfigurado
     */
    private JTextField crearCampoTexto(String contenido) {
        JTextField field = new JTextField(contenido, 12);
        field.setEditable(false);
        field.setFocusable(false);
        return field;
    }
}
