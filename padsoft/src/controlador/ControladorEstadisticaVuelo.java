package controlador;

import estadística.Estadistica;
import sistema.SkyCompass;
import vuelo.Vuelo;
import vista.EstadisticasVuelos;
import vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * Clase que representa el controlador de la vista de estadísticas.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControladorEstadisticaVuelo implements ActionListener {

    private VentanaPrincipal frame;
    private final EstadisticasVuelos vista;
    private final SkyCompass app;
    private final Estadistica estadistica;
    private final String volver;
    /**
     * Constructor de la clase ControladorEstadisticaVuelo.
     * @param frame ventana principal
     * @param vista vista de estadísticas
     * @param app SkyCompass
     * @param volver panel anterior
     */
    public ControladorEstadisticaVuelo(VentanaPrincipal frame, EstadisticasVuelos vista, SkyCompass app, String volver) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;
        this.estadistica = Estadistica.getEstadistica();
        this.volver = volver;
        this.vista.setControlador(this);
    }

    /**
     * Genera un gráfico estadístico de los minutos que el vuelo ha pasado en cada una de las zonas del aeropuerto.
     * 
     * @param e evento de acción
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(volver);
            return;
        }

        String comando = e.getActionCommand();
        if ("generar".equals(comando)) {
            String codigo = vista.getCodigoVuelo();
            String fecha  = vista.getFechaVuelo();

            if(codigo.isEmpty() || fecha.isEmpty()){
                JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.");
                return;
            }

            try {
                LocalDate.parse(fecha);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Formato de fecha incorrecto.");
                return;
            }

            Vuelo vuelo = app.buscarVueloCodigoFecha(codigo, LocalDate.parse(fecha));

            if (vuelo == null) {
                System.out.println("Vuelo no encontrado.");
                JOptionPane.showMessageDialog(vista, "Vuelo no encontrado.");
                return;
            }
            
            JOptionPane.showMessageDialog(vista, "Vuelo encontrado.");

            Map<String, Double> datos = new HashMap<>();
            datos.put("Hangar", (double) vuelo.getMinutosHangar());
            datos.put("Aparcamiento", (double) vuelo.getMinutosZonaAparcamiento());
            datos.put("Embarque", (double) vuelo.getMinutosPuertaEmbarque());
            datos.put("Finger", (double) vuelo.getMinutosFinger());

            vista.mostrarGrafico(datos);
        }
    }
}
