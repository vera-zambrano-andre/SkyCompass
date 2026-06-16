package controlador;

import java.awt.event.*;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import aeropuerto.Aerolinea;
import aeropuerto.Terminal;
import sistema.SkyCompass;
import vista.DarAltaUsuario;
import vista.VentanaPrincipal;    


/**
 * Clase que representa el controlador de la vista de dar alta usuarios.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlDarAltaUsuario implements ActionListener {
    private VentanaPrincipal frame;
    private DarAltaUsuario vista;
    private SkyCompass app;

    /**
     * Constructor de la clase.
     * @param frame ventana principal
     * @param app aplicación
     */
    public ControlDarAltaUsuario(VentanaPrincipal frame, SkyCompass app) {
        this.frame = frame;
        this.vista = frame.getPanelDarAlta();
        this.app = app;
    }

    /**
     * Maneja los eventos de la vista de dar de alta usuarios.
     *
     * @param e el evento de acción que se produce al interactuar con los componentes
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox<String> rolComboBox = vista.getRolComboBox();
        String selected = rolComboBox.getSelectedItem().toString();

        boolean mostrarCampos = !"".equals(selected);

        vista.getDniLabel().setVisible(mostrarCampos);
        vista.getDniField().setVisible(mostrarCampos);
        vista.getNombreLabel().setVisible(mostrarCampos);
        vista.getNombreField().setVisible(mostrarCampos);
        vista.getPasswordLabel().setVisible("Controlador".equals(selected));
        vista.getPasswordField().setVisible("Controlador".equals(selected));

        vista.getAerolineaLabel().setVisible("Operador".equals(selected));
        if(!e.getActionCommand().equals("Aceptar")){
            vista.setAerolineas(app.getAerolineas());
        }
        
        vista.getAerolineaComboBox().setVisible("Operador".equals(selected));
        vista.getTerminalLabel().setVisible("Controlador".equals(selected));

        if(!e.getActionCommand().equals("Aceptar")){
            vista.setTerminales(app.getTerminalesPasajeros(), app.getTerminalesMercancias());
        }

        vista.getTerminalComboBox().setVisible("Controlador".equals(selected));

        
        if(e.getActionCommand().equals("Aceptar")){

            if(selected.equals("Operador")){
                this.darAltaOperador();
            }

            else if(selected.equals("Controlador")){
                this.darAltaControlador();
            }

        }
        else if(e.getActionCommand().equals("Cancelar")){            
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);

            vista.limpiar();
            
            vista.getDniLabel().setVisible(false);
            vista.getDniField().setVisible(false);
            vista.getNombreLabel().setVisible(false);
            vista.getNombreField().setVisible(false);
            vista.getPasswordLabel().setVisible(false);
            vista.getPasswordField().setVisible(false);

            vista.getAerolineaLabel().setVisible(false);
            vista.getAerolineaComboBox().setVisible(false);
            vista.getTerminalLabel().setVisible(false);
            vista.getTerminalComboBox().setVisible(false);
        }
    }


    /**
     * Registra un operador en el sistema.
     */
    private void darAltaOperador() {
        String DNI = vista.getDniField().getText();
        String nombre = vista.getNombreField().getText();
        Aerolinea aerolinea = (Aerolinea) vista.getAerolineaComboBox().getSelectedItem();
        
        System.out.println(aerolinea);

        if(DNI.equals("") || nombre.equals("") || aerolinea.toString().equals("")){
            System.out.println(aerolinea);
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (app.registrarOperador(DNI, nombre, aerolinea) == null) {
                JOptionPane.showMessageDialog(vista, "Error al registar operador", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Operador ya registrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        JOptionPane.showMessageDialog(vista, "Operador registrado correctamente", "Operador registrado", JOptionPane.INFORMATION_MESSAGE);
        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
        vista.limpiar();
    }
    /**
     * Registra un controlador en el sistema.
     */
    private void darAltaControlador() {
        String DNI = vista.getDniField().getText();
        String password = vista.getPasswordField().getText();
        String nombre = vista.getNombreField().getText();
        Terminal terminal = (Terminal) vista.getTerminalComboBox().getSelectedItem();

        if(DNI.equals("") || password.equals("") || nombre.equals("") || terminal.toString().equals("")){
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (app.registrarControlador(DNI, nombre, password, terminal) == null) {
                JOptionPane.showMessageDialog(vista, "Error al registar controlador", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Controlador ya registrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(vista, "Controlador registrado correctamente", "Controlador registrado", JOptionPane.INFORMATION_MESSAGE);
        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
        vista.limpiar();
        
    }
}
