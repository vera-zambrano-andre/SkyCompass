package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import sistema.SkyCompass;
import vista.CargarAeropuertos;
import vista.VentanaPrincipal;

/**
 * Clase que representa el controlador para la carga de aeropuertos.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlCargarAeropuertos implements ActionListener {
    private VentanaPrincipal frame;
    private SkyCompass app;
    private CargarAeropuertos vista;

    /**
     * Constructor del controlador de carga de aeropuertos.
     * 
     * @param frame Ventana principal de la aplicación
     * @param app   Instancia principal de la lógica de negocio
     * @param vista Vista encargada de la carga de aeropuertos
     */
    public ControlCargarAeropuertos(VentanaPrincipal frame, SkyCompass app, CargarAeropuertos vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
    }

    /**
     * Maneja las acciones de los botones de la vista.
     * Si se pulsa "← Volver", se regresa al panel del gestor.
     * Si se pulsa "Cargar Aeropuertos", se muestra un diálogo de confirmación
     * y, si se acepta, se intenta cargar el archivo de aeropuertos.
     * 
     * @param e Evento de acción generado por los botones
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
        } else if (e.getActionCommand().equals("Cargar Aeropuertos")) {
            int respuesta = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Estás seguro de que quieres cargar los aeropuertos?\n",
                    "Confirmar carga de aeropuertos",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    this.app.importarAeropuertosFichero("./resources/aeropuertos.txt");

                    JOptionPane.showMessageDialog(
                            vista,
                            "Aeropuertos cargados correctamente",
                            "Aeropuertos cargados",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            vista,
                            "Error al importar los aeropuertos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
