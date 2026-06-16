package controlador;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import aeropuerto.Aerolinea;
import aeropuerto.Aeropuerto;
import aeropuerto.Avion;
import aeropuerto.Temporada;
import sistema.SkyCompass;
import usuario.Operador;
import vista.SolicitarVuelo;
import vista.VentanaPrincipal;
import vuelo.FrecuenciaVuelo;
import vuelo.IVueloMercanciasProgramacion;
import vuelo.IVueloPasajerosProgramacion;
import vuelo.IVueloProgramacion;
import vuelo.VueloDiario;
import vuelo.VueloProgramado;
import vuelo.VueloSemanal;
import vuelo.VueloPuntual;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Clase que representa el controlador de la vista de solicitar vuelo.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlSolicitarVuelo implements ActionListener {
    private VentanaPrincipal frame;
    private SolicitarVuelo vista;
    private SkyCompass app;

    /**
     * Constructor de la clase ControlSolicitarVuelo.
     * @param frame ventana principal
     * @param app aplicacion
     */
    public ControlSolicitarVuelo(VentanaPrincipal frame, SkyCompass app) {
        this.frame = frame;
        this.app = app;
        this.vista = frame.getPanelSolicitarVuelo(); 
    }

    /**
     * Metodo que se encarga de gestionar los eventos de los botones de la vista de solicitar vuelo.
     * 
     * @param e evento de pulsar el botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
            vista.limpiar();
            return;
        }

        Object source = e.getSource();

        if (source == vista.getFrecuenciaCombo()) {
            String frecuencia = vista.getFrecuencia();

            boolean esPuntual = frecuencia.equals("Puntual");
            boolean esSemanal = frecuencia.equals("Semanal");

            vista.getFechaInicioLabel().setVisible(true);
            vista.getFechaInicioField().setVisible(true);

            vista.getFechaFinLabel().setVisible(!esPuntual);
            vista.getFechaFinField().setVisible(!esPuntual);

            vista.getPanelDiasSemana().setVisible(esSemanal);
            return;
        }

        if (source == vista.getTipoCombo()) {
            String tipo = vista.getTipoVuelo();
            vista.getPanelFinger().setVisible(tipo.equals("Pasajeros"));
            vista.revalidate();
            vista.repaint();
            return;
        }

        String id = vista.getIdVuelo();
        String frecuencia = vista.getFrecuencia();

        if(e.getActionCommand().equals("Solicitar vuelo")) {
            try {
                
                String textoHoraSalida = vista.getHoraSalida();
                String textoHoraLlegada = vista.getHoraLlegada();

                if (textoHoraSalida.isEmpty() || textoHoraLlegada.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Hay campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalTime horaSalida = LocalTime.parse(textoHoraSalida);
                LocalTime horaLlegada = LocalTime.parse(textoHoraLlegada);

                List<DayOfWeek> dias = vista.getDias();
                FrecuenciaVuelo frecuenciaVuelo = null;
                String textoFechaInicio = vista.getFechaInicioField().getText();
                String textoFechaFin = vista.getFechaFinField().getText();
                switch(frecuencia) {
                    case "Diario":
                        if (textoFechaInicio.isEmpty() || textoFechaFin.isEmpty()) {
                            JOptionPane.showMessageDialog(vista, "Hay campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        LocalDate inicio = LocalDate.parse(vista.getFechaInicioField().getText());
                        LocalDate fin = LocalDate.parse(vista.getFechaFinField().getText());
                        frecuenciaVuelo = new VueloDiario(inicio, fin);
                        break;
                    case "Semanal":
                        if (textoFechaInicio.isEmpty() || textoFechaFin.isEmpty()) {
                            JOptionPane.showMessageDialog(vista, "Hay campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        inicio = LocalDate.parse(vista.getFechaInicioField().getText());
                        fin = LocalDate.parse(vista.getFechaFinField().getText());
                        frecuenciaVuelo = new VueloSemanal(dias,inicio, fin);
                        break;
                    case "Puntual":
                        if (textoFechaInicio.isEmpty()) {
                            JOptionPane.showMessageDialog(vista, "Hay campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        inicio = LocalDate.parse(vista.getFechaInicioField().getText());
                        frecuenciaVuelo = new VueloPuntual(inicio);
                        break;
                }
    
                
                String tipo = vista.getTipoVuelo();
                boolean usaFinger = false;
    
                IVueloProgramacion vueloProgramacion = null;
    
                if(tipo.equals("Pasajeros")) {
                    vista.getPanelFinger().setVisible(true);
                    usaFinger = vista.getUsaFinger();
                    vueloProgramacion = new IVueloPasajerosProgramacion(usaFinger);
                } else if(tipo.equals("Mercancías")) {
                    vueloProgramacion = new IVueloMercanciasProgramacion();
                }
    
                String seleccionadoDestino = (String) vista.getAeropuertoDestinoCombo().getSelectedItem();
                String seleccionadoOrigen = (String) vista.getAeropuertoOrigenCombo().getSelectedItem();
                Aeropuerto aeropuertoOrigen = null;
                Aeropuerto aeropuertoDestino = null;
                for (Aeropuerto aeropuerto : app.getAeropuertos()) {
                    String textoDestino = aeropuerto.getNombre() + " (" + aeropuerto.getCodigo() + ")";
                    if (textoDestino.equals(seleccionadoDestino)) {
                        aeropuertoDestino = aeropuerto;
                    }
                    
                    String textoOrigen = aeropuerto.getNombre() + " (" + aeropuerto.getCodigo() + ")";
                    if (textoOrigen.equals(seleccionadoOrigen)) {
                        aeropuertoOrigen = aeropuerto;
                    }
                }
                Avion avion = vista.getAvion();

                if (aeropuertoDestino == null || aeropuertoOrigen == null) {
                    JOptionPane.showMessageDialog(vista, "Selecciona un aeropuerto de origen/destino válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                Operador operador = (Operador) app.getUsuarioLogeado();
                Aerolinea aerolinea = operador.getAerolinea();
    
    
                VueloProgramado exito = app.programarVuelo(id, horaSalida, horaLlegada, frecuenciaVuelo, avion, aerolinea, aeropuertoOrigen, aeropuertoDestino, vueloProgramacion);
    
                if (exito !=null) {
                    JOptionPane.showMessageDialog(vista, "Vuelo solicitado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
                    vista.limpiar();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al solicitar vuelo (posible ID duplicado)", "Error", JOptionPane.ERROR_MESSAGE);
                }
    
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa fechas AAAA-MM-DD y horas HH:MM.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + ex.getMessage());
            }
        }
    }

    /**
     * Actualiza los valores en los JComboBox de aviones y aeropuertos.
     * Limpia los valores actuales y vuelve a cargarlos con los aviones y aeropuertos
     * actuales en el sistema.
     */
    public void actualizarComboBoxes() {
        vista.getAvionCombo().removeAllItems();
        for (Avion avion : app.getAviones()) {
            vista.getAvionCombo().addItem(avion);
        }
        
        
        vista.getAeropuertoDestinoCombo().removeAllItems();
        vista.getAeropuertoDestinoCombo().addItem("");
        for (Aeropuerto aeropuerto : app.getAeropuertos()) {
            String nombreCodigo = aeropuerto.getNombre() + " (" + aeropuerto.getCodigo() + ")";
            vista.getAeropuertoDestinoCombo().addItem(nombreCodigo);
        }

        vista.getAeropuertoOrigenCombo().removeAllItems();
        vista.getAeropuertoOrigenCombo().addItem("");
        for (Aeropuerto aeropuerto : app.getAeropuertos()) {
            String nombreCodigo = aeropuerto.getNombre() + " (" + aeropuerto.getCodigo() + ")";
            vista.getAeropuertoOrigenCombo().addItem(nombreCodigo);
        }
    }
}
