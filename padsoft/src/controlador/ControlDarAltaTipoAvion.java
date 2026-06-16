package controlador;

import sistema.SkyCompass;
import vista.DarAltaTipoAvion;
import vista.VentanaPrincipal;

import aeropuerto.Dimension;
import aeropuerto.TipoAvion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Clase que representa el controlador de dar alta tipo avion.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlDarAltaTipoAvion implements ActionListener {
    private VentanaPrincipal frame;
    private SkyCompass app;
    private DarAltaTipoAvion vista;

    /**
     * Constructor del controlador.
     * 
     * @param frame Ventana principal de la aplicación
     * @param app   Instancia del sistema principal SkyCompass
     * @param vista Vista encargada de gestionar la interfaz de alta de tipo de avión
     */

    public ControlDarAltaTipoAvion(VentanaPrincipal frame, SkyCompass app, DarAltaTipoAvion vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Método que maneja los eventos generados desde la vista de alta de tipo de avión.
     * Realiza validaciones y comunica con la lógica de negocio para registrar un nuevo tipo de avión.
     * 
     * @param e Evento de acción generado por los componentes de la interfaz gráfica
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
        }

        if(!e.getActionCommand().equals("Dar de alta")){
            String tipo = (String) vista.getTipoCombo().getSelectedItem();
            boolean esPasajeros = "Pasajeros".equals(tipo);
            boolean esMercancias = "Mercancías".equals(tipo);

            vista.getAforoLabel().setVisible(esPasajeros);
            vista.getAforoField().setVisible(esPasajeros);

            vista.getTempLabel().setVisible(esMercancias);
            vista.getTempPanel().setVisible(esMercancias);

            vista.getCargaLabel().setVisible(esMercancias);
            vista.getCargaField().setVisible(esMercancias);

            vista.getPeligroLabel().setVisible(esMercancias);
            vista.getPeligroPanel().setVisible(esMercancias);
        }

        if(e.getActionCommand().equals("Dar de alta")){

            String marca = vista.getMarcaField().getText();
            String modelo = vista.getModeloField().getText();
            String ancho = vista.getAnchoField().getText();
            String largo = vista.getLargoField().getText();
            String alto = vista.getAltoField().getText();
            String autonomia = vista.getAutonomiaField().getText();

            if(marca.equals("") || modelo.equals("") || ancho.equals("") || largo.equals("") || alto.equals("") || autonomia.equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tipo = (String) vista.getTipoCombo().getSelectedItem();

            if(tipo.equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de avión", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(tipo.equals("Pasajeros")){
                String aforo = vista.getAforoField().getText();
                if(aforo.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try{
                    Double.parseDouble(ancho);
                    Double.parseDouble(largo);
                    Double.parseDouble(alto);
                    Double.parseDouble(autonomia);
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Tanto el ancho como el largo, el alto y la autonomía deben ser números decimales", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try{
                    Integer.parseInt(aforo);
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "El aforo debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                TipoAvion avion = app.darAltaTipoAvionPasajero(marca, modelo, new Dimension(Double.parseDouble(ancho), Double.parseDouble(largo), Double.parseDouble(alto)), Double.parseDouble(autonomia), Integer.parseInt(aforo));

                if(avion != null){
                    JOptionPane.showMessageDialog(null, "El tipo de avión ha sido dado de alta", "Información", JOptionPane.INFORMATION_MESSAGE);
                    frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
                }
                else{
                    JOptionPane.showMessageDialog(null, "El tipo de avión no ha podido ser dado de alta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(tipo.equals("Mercancías")){
                String carga = vista.getCargaField().getText();
                if(carga.isEmpty() || vista.isControlTemperaturaSi() == false && vista.isControlTemperaturaNo() == false || vista.isMercanciasPeligrosasSi() == false && vista.isMercanciasPeligrosasNo() == false){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try{
                    Double.parseDouble(ancho);
                    Double.parseDouble(largo);
                    Double.parseDouble(alto);
                    Double.parseDouble(autonomia);
                    Double.parseDouble(carga);
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Tanto el ancho como el largo, el alto, la autonomía y la carga deben ser números decimales", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                TipoAvion avion = app.darAltaTipoAvionMercancia(marca, modelo, new Dimension(Double.parseDouble(ancho), Double.parseDouble(largo), Double.parseDouble(alto)), Double.parseDouble(autonomia), vista.isControlTemperaturaSi(), vista.isMercanciasPeligrosasSi() ,Double.parseDouble(carga));

                if(avion != null){
                    JOptionPane.showMessageDialog(null, "El tipo de avión ha sido dado de alta", "Información", JOptionPane.INFORMATION_MESSAGE);
                    frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
                }
                else{
                    JOptionPane.showMessageDialog(null, "El tipo de avión no ha podido ser dado de alta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
