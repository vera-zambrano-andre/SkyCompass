package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import aeropuerto.Aerolinea;
import aeropuerto.Terminal;
import aeropuerto.TerminalMercancia;
import aeropuerto.TerminalPasajero;
import java.util.List;

/**
 * Clase que representa el panel de alta de usuario en la interfaz gráfica.
 * Permite seleccionar un rol y registrar usuarios con distintos atributos como
 * DNI, nombre, contraseña, aerolínea y terminal.
 *
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class DarAltaUsuario extends JPanel {

    private JButton cancelarButton;
    private JButton aceptarButton;
    private JTextField dniField;
    private JTextField nombreField;
    private JTextField passwordField;

    private JLabel dniLabel;
    private JLabel nombreLabel;
    private JLabel passwordLabel;
    private JLabel aerolineaLabel;
    private JLabel terminalLabel;

    private JComboBox<String> rolComboBox;
    private JComboBox<Terminal> terminalComboBox;
    private JComboBox<Aerolinea> aerolineaComboBox;

    private String[] roles = { "", "Operador", "Controlador" };

    /**
     * Constructor del panel DarAltaUsuario.
     * 
     * @param frame la ventana principal que contiene este panel
     */
    public DarAltaUsuario(VentanaPrincipal frame) {
        this.setLayout(new BorderLayout());

        Dimension fieldSize = new Dimension(200, 25);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Seleccione rol:"), gbc);

        gbc.gridx = 1;
        rolComboBox = new JComboBox<>(roles);
        contentPanel.add(rolComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        dniLabel = new JLabel("DNI:");
        dniField = new JTextField();
        dniField.setPreferredSize(fieldSize);
        contentPanel.add(dniLabel, gbc);

        gbc.gridx = 1;
        contentPanel.add(dniField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        nombreField.setPreferredSize(fieldSize);
        contentPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        contentPanel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        passwordLabel = new JLabel("Contraseña:");
        passwordField = new JTextField();
        passwordField.setPreferredSize(fieldSize);
        contentPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        contentPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        aerolineaLabel = new JLabel("Aerolínea:");
        aerolineaComboBox = new JComboBox<Aerolinea>();
        aerolineaComboBox.setPreferredSize(fieldSize);
        contentPanel.add(aerolineaLabel, gbc);

        gbc.gridx = 1;
        contentPanel.add(aerolineaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        terminalLabel = new JLabel("Terminal:");

        terminalComboBox = new JComboBox<>();
        terminalComboBox.setPreferredSize(fieldSize);
        rolComboBox.setPreferredSize(fieldSize);

        contentPanel.add(terminalLabel, gbc);
        gbc.gridx = 1;
        contentPanel.add(terminalComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        aceptarButton = new JButton("Aceptar");
        cancelarButton = new JButton("Cancelar");

        buttonPanel.add(aceptarButton);
        buttonPanel.add(cancelarButton);
        contentPanel.add(buttonPanel, gbc);

        dniLabel.setVisible(false);
        dniField.setVisible(false);
        nombreLabel.setVisible(false);
        nombreField.setVisible(false);
        passwordLabel.setVisible(false);
        passwordField.setVisible(false);
        aerolineaLabel.setVisible(false);
        aerolineaComboBox.setVisible(false);
        terminalLabel.setVisible(false);

        terminalComboBox.setVisible(false);

        this.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(false);
    }

    /**
     * Asigna el controlador de eventos a los botones y combo boxes.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        aceptarButton.addActionListener(e);
        cancelarButton.addActionListener(e);
        rolComboBox.addActionListener(e);
    }

    /**
     * Devuelve la variable nombreField.
     * 
     * @return el campo de texto con el nombre
     */
    public JTextField getNombreField() {
        return nombreField;
    }

    /**
     * Devuelve el campo de texto que contiene el DNI del usuario
     * a dar de alta.
     * 
     * @return el campo de texto con el DNI
     */
    public JTextField getDniField() {
        return dniField;
    }

    /**
     * Devuelve el campo de texto que contiene la contraseña del usuario
     * a dar de alta.
     * 
     * @return el campo de texto con la contraseña
     */
    public JTextField getPasswordField() {
        return passwordField;
    }

    /**
     * Devuelve el combo box que contiene las aerolíneas disponibles para
     * seleccionar al dar de alta un usuario.
     * 
     * @return El combo box de aerolíneas.
     */
    public JComboBox<Aerolinea> getAerolineaComboBox() {
        return aerolineaComboBox;
    }

    /**
     * Devuelve el combo box que contiene los roles disponibles para
     * seleccionar al dar de alta un usuario.
     * 
     * @return El combo box de roles.
     */
    public JComboBox<String> getRolComboBox() {
        return rolComboBox;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "DNI:".
     * 
     * @return la etiqueta de DNI
     */
    public JLabel getDniLabel() {
        return dniLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Nombre:".
     * 
     * @return la etiqueta de nombre
     */
    public JLabel getNombreLabel() {
        return nombreLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Contraseña:".
     * 
     * @return la etiqueta de contraseña
     */
    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Aerolinea:".
     * 
     * @return la etiqueta de aerolinea
     */
    public JLabel getAerolineaLabel() {
        return aerolineaLabel;
    }

    /**
     * Devuelve la etiqueta que contiene el texto "Terminal:".
     * 
     * @return la etiqueta de terminal
     */
    public JLabel getTerminalLabel() {
        return terminalLabel;
    }

    /**
     * Devuelve el combobox que contiene las terminales.
     * 
     * @return el combobox de terminal
     */
    public JComboBox<Terminal> getTerminalComboBox() {
        return terminalComboBox;
    }

    /**
     * Asigna las terminales que se mostraran en el combobox.
     * 
     * @param terminalesPasajeros  la lista de terminales de pasajeros
     * @param terminalesMercancias la lista de terminales de mercancia
     */
    public void setTerminales(List<TerminalPasajero> terminalesPasajeros,
            List<TerminalMercancia> terminalesMercancias) {
        terminalComboBox.removeAllItems();

        terminalComboBox.addItem(new TerminalPasajero("vacío", 0, 0, null, null) {
            @Override
            public String toString() {
                return "";
            }
        });

        for (TerminalPasajero t : terminalesPasajeros) {
            terminalComboBox.addItem(t);
        }
        for (TerminalMercancia t : terminalesMercancias) {
            terminalComboBox.addItem(t);
        }
    }

    /**
     * Asigna las aerolineas que se mostraran en el combobox.
     * 
     * @param aerolineas la lista de aerolineas
     */
    public void setAerolineas(List<Aerolinea> aerolineas) {
        aerolineaComboBox.removeAllItems();

        aerolineaComboBox.addItem(new Aerolinea("vacío", "vacío") {
            @Override
            public String toString() {
                return "";
            }
        });

        for (Aerolinea a : aerolineas) {
            aerolineaComboBox.addItem(a);
        }
    }

    /**
     * Limpia los campos de texto y restablece los valores predeterminados
     * de los combo boxes en el formulario de alta de usuario.
     */

    public void limpiar() {
        nombreField.setText("");
        dniField.setText("");
        passwordField.setText("");
        rolComboBox.setSelectedIndex(0);
        aerolineaComboBox.setSelectedIndex(0);
        terminalComboBox.setSelectedIndex(0);
    }

}
