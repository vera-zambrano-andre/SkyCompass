package controlador;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import aeropuerto.*;
import sistema.SkyCompass;
import vista.DarAltaElemento;
import vista.VentanaPrincipal;
import vuelo.ZonaEspera;
import elementocoste.*;

import java.util.List;

/**
 * Clase que representa el controlador de la vista de dar alta elementos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlDarAltaElemento implements ActionListener {
    private VentanaPrincipal frame;
    private DarAltaElemento vista;
    private SkyCompass app;

    /**
     * Constructor de la clase ControlDarAltaElemento.
     * 
     * @param frame ventana principal
     * @param app   aplicacion
     */
    public ControlDarAltaElemento(VentanaPrincipal frame, SkyCompass app) {
        this.frame = frame;
        this.vista = frame.getPanelDarAltaElemento();
        this.app = app;
    }

    /**
     * Maneja los eventos de la vista de dar alta elementos.
     * 
     * @param e evento que se produce
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String selected = (String) vista.getSelectedElemento();
        vista.getCardLayout().show(vista.getContentPanel(), selected);
        vista.getButtonPanel().setVisible(true);

        if (selected.equals("Terminal")) {
            String tipo = (String) vista.getTipoTerminalComboBox().getSelectedItem();
            boolean esPasajeros = "Pasajeros".equals(tipo);
            boolean esMercancias = "Mercancías".equals(tipo);

            vista.getAforoLabel().setVisible(esPasajeros);
            vista.getAforoTerminal().setVisible(esPasajeros);
            vista.getPuertasLabel().setVisible(esPasajeros);
            vista.getScrollPuertas().setVisible(esPasajeros);

            vista.getZonasLabel().setVisible(esMercancias);
            vista.getScrollZonas().setVisible(esMercancias);

            if (!e.getActionCommand().equals("Aceptar")) {
                if (esPasajeros) {
                    vista.setPuertasCheckboxes(app.getPuertasEmbarque());
                }

                if (esMercancias) {
                    vista.setZonasCheckboxes(app.getZonasAparcamiento());
                }
            }
        }

        if(selected.equals("Puerta de embarque")){

            if(!e.getActionCommand().equals("Aceptar")){
                vista.setZonasCheckboxesPuertas(app.getZonasAparcamiento());
                vista.setFingerRadioButtons(app.getFingers());
            }
        }

        if(selected.equals("Aparcamiento")){

            if(!e.getActionCommand().equals("Aceptar")){
                vista.setPlazasCheckboxes(app.getPlazas());
            }
        }

        if (selected.equals("Hangar")) {
            String tipo = vista.getTipoHangar();
            if(tipo.equals("Mercancías")){
                vista.getLabelMercanciasPeligrosas().setVisible(true);
                vista.getPanelMercanciasPeligrosas().setVisible(true);
            }
            else{
                vista.getLabelMercanciasPeligrosas().setVisible(false);
                vista.getPanelMercanciasPeligrosas().setVisible(false);
            }
        }

        if (e.getActionCommand().equals("Agregar Temporada")) {
            try {
                LocalDate inicio = vista.getCampoFechaInicioTemporada();
                LocalDate fin = vista.getCampoFechaFinTemporada();
                LocalTime apertura = vista.getCampoHoraInicio();
                LocalTime cierre = vista.getCampoHoraFin();

                Temporada temporada = new Temporada(inicio, fin, apertura, cierre);
                vista.getTemporadasListModel().addElement(temporada);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa fechas AAAA-MM-DD y horas HH:MM.");
            }
        }

        if (e.getActionCommand().equals("Aceptar")) {
            switch (selected) {
                case "Aeropuerto":
                    String nombre = vista.getCampoNombreAeropuerto();
                    String codigo = vista.getCampoAeropuertoId();
                    String ciudad = vista.getCampoCiudadAeropuerto();
                    String pais = vista.getCampoPaisAeropuerto();
                    String distanciaString = vista.getCampoDistanciaCiudadKm();
                    Direccion direccion = vista.getCampoDireccionAeropuerto();
                    String diferenciaHorariaString = vista.getCampoDiferenciaHorariaAeropuerto();
                    List<Temporada> temporadas = vista.getTemporadas();

                    if (nombre.isEmpty() || codigo.isEmpty() || ciudad.isEmpty() || pais.isEmpty() || direccion == null
                            || temporadas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    try {
                        Integer.parseInt(distanciaString);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La distancia debe ser un número entero.");
                        return;
                    }

                    try {
                        Integer.parseInt(diferenciaHorariaString);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La diferencia horaria debe ser un número entero.");
                        return;
                    }

                    Aeropuerto nuevo = new Aeropuerto(nombre, codigo, ciudad, pais, Integer.parseInt(distanciaString),
                            direccion, Integer.parseInt(diferenciaHorariaString), temporadas);
                    app.addAeropuerto(nuevo);
                    JOptionPane.showMessageDialog(null, "Aeropuerto añadido correctamente");
                    break;

                case "Aerolínea":
                    String nombreAerolinea = vista.getCampoNombreAerolinea().trim();
                    String idAerolinea = vista.getCampoAerolineaId().trim();

                    if (nombreAerolinea.isEmpty() || idAerolinea.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                vista,
                                "Por favor, rellene todos los campos.",
                                "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        app.darAltaAerolinea(idAerolinea, nombreAerolinea);
                        JOptionPane.showMessageDialog(
                                vista,
                                "Aerolínea añadida correctamente",
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                        
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(
                                vista,
                                ex.getMessage(),
                                "Error al dar de alta aerolínea",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                
                case "Pista":

                    String tipoPista = (String) vista.getTipoPistaComboBox().getSelectedItem();
                    String longitud = vista.getCampoLongitudPista();

                    if (longitud.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    if(!tipoPista.equals("Despegue") && !tipoPista.equals("Aterrizaje")){ 
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de pista.");
                        return;
                    }

                    if (longitud.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    try {
                        Double.parseDouble(longitud);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La longitud debe ser un número decimal.");
                        return;
                    }

                    Pista pista = null;
                    if(tipoPista.equals("Despegue")) {
                        pista = app.darAltaPistaDespegue(Double.parseDouble(longitud), new ZonaEspera());
                    } else if(tipoPista.equals("Aterrizaje")) {
                        pista = app.darAltaPistaAterrizaje(Double.parseDouble(longitud));
                    }

                    if (pista != null) {
                        JOptionPane.showMessageDialog(null, "Pista creada correctamente.");
                        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error al crear la pista.");
                    }

                    break;

                case "Terminal":

                    String tipo = (String) vista.getTipoTerminalComboBox().getSelectedItem();
                    if(tipo.equals("")){ 
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de terminal.");
                        return;
                    }

                    String codigoTerminal = vista.getCodigoTerminal();
                    String maxPlazas = vista.getMaxPlazas();
                    String horaApertura = vista.getHoraApertura();
                    String horaCierre = vista.getHoraCierre();

                    if (codigoTerminal.isEmpty() || maxPlazas.isEmpty() || horaApertura.isEmpty()
                            || horaCierre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    try {
                        Integer.parseInt(maxPlazas);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El número de plazas máximas debe ser un número entero.");
                        return;
                    }

                    LocalTime horaAperturaTime = null;
                    LocalTime horaCierreTime = null;
                    try {
                        horaAperturaTime = LocalTime.parse(horaApertura);
                        horaCierreTime = LocalTime.parse(horaCierre);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de hora incorrecto. Usa el formato HH:MM.");
                        return;
                    }

                    Terminal terminal = null;

                    try {
                        switch (tipo) {

                            case "Pasajeros":
                                String aforoMax = vista.getAforo();
                                if (aforoMax.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                                    return;
                                }

                                try {
                                    Integer.parseInt(aforoMax);
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null,
                                            "El aforo máximo debe ser un número entero.");
                                    return;
                                }

                                terminal = app.darAltaTerminalPasajero(codigoTerminal, Integer.parseInt(maxPlazas),
                                        Integer.parseInt(aforoMax),
                                        vista.getPuertasSeleccionadas(),
                                        new RangoTiempo(horaAperturaTime, horaCierreTime));
                                break;

                            case "Mercancías":
                                terminal = app.darAltaTerminalMercancia(codigoTerminal, Integer.parseInt(maxPlazas),
                                        vista.getZonasSeleccionadas(),
                                        new RangoTiempo(horaAperturaTime, horaCierreTime));
                                break;
                        }

                        if (terminal != null) {
                            JOptionPane.showMessageDialog(null, "Terminal creada correctamente.");
                            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                        }
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(
                                vista,
                                ex.getMessage(),
                                "Error al dar de alta terminal",
                                JOptionPane.WARNING_MESSAGE);
                    }

                    break;

                case "Puerta de embarque":
                    
                    String codigoPuerta = vista.getCodigoPuerta();

                    if (codigoPuerta.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    PuertaEmbarque puerta = app.darAltaPuertaEmbarque(codigoPuerta, vista.getFingerSeleccionado(), vista.getZonasPuertasSeleccionadas());

                    if(puerta != null) {
                        JOptionPane.showMessageDialog(null, "Puerta de embarque creada correctamente.");
                        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error al crear la puerta de embarque.");
                    }

                    break;

                case "Aparcamiento":
                    String codigoAparcamiento = vista.getCodigoZonaAparcamiento();
                    String capacidadMaxima = vista.getCapacidadMaximaZona();
                    String costeHora = vista.getCosteHoraZona();

                    if (codigoAparcamiento.isEmpty() || capacidadMaxima.isEmpty() || costeHora.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    try {
                        Integer.parseInt(capacidadMaxima);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La capacidad máxima debe ser un número entero.");
                        return;
                    }

                    try {
                        Double.parseDouble(costeHora);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El coste por hora debe ser un número decimal.");
                        return;
                    }

                    ZonaAparcamiento aparcamiento = app.darAltaZonaAparcamiento(codigoAparcamiento, Integer.parseInt(capacidadMaxima), vista.getPlazasSeleccionadas(), Double.parseDouble(costeHora));
                    if (aparcamiento != null) {
                        JOptionPane.showMessageDialog(null, "Aparcamiento creado correctamente.");
                        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error al crear el aparcamiento.");
                    }
                    break;

                case "Plaza":
                    String anchoPlaza = vista.getAnchoPlaza();
                    String largoPlaza = vista.getLargoPlaza();

                    if(anchoPlaza.isEmpty() || largoPlaza.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    try {
                        Double.parseDouble(anchoPlaza);
                        Double.parseDouble(largoPlaza);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Tanto el ancho como el largo deben ser un número decimal.");
                        return;
                    }

                    Plaza plaza = app.darAltaPlaza(Double.parseDouble(anchoPlaza), Double.parseDouble(largoPlaza));

                    if (plaza != null) {
                        JOptionPane.showMessageDialog(null, "Plaza creada correctamente.");
                        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error al crear la plaza.");
                    }
                    break;
                
                case "Finger":
                    String codigoFinger = vista.getCodigoFinger();
                    String alturaMaxFinger = vista.getAlturaMaxFinger();
                    String costeFinger = vista.getCosteFinger();

                    if (codigoFinger.isEmpty() || alturaMaxFinger.isEmpty() || costeFinger.isEmpty() || !vista.isPuertaDobleFinger() && !vista.isPuertaSimpleFinger()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    try {
                        Double.parseDouble(alturaMaxFinger);
                        Double.parseDouble(costeFinger);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La altura máxima y el coste deben ser un número decimal.");
                        return;
                    }

                    Finger finger = app.darAltaFinger(codigoFinger, Double.parseDouble(alturaMaxFinger), vista.isPuertaDobleFinger(),  Double.parseDouble(costeFinger));

                    if (finger != null) {
                        JOptionPane.showMessageDialog(null, "Finger creado correctamente.");
                        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error al crear el finger.");
                    }
                    break;
                
                case "Hangar":
                    String capacidadMaximaHangar = vista.getMaxPlazasHangar();
                    String costeHoraHangar = vista.getCosteHoraHangar();
                    String largoHangar = vista.getLargoHangar();
                    String altoHangar = vista.getAltoHangar();
                    String anchoHangar = vista.getAnchoHangar();

                    if (capacidadMaximaHangar.isEmpty() || costeHoraHangar.isEmpty() || largoHangar.isEmpty() || altoHangar.isEmpty() || anchoHangar.isEmpty() || !vista.isMercanciasNoPeligrosas() && !vista.isMercanciasPeligrosas()) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
                        return;
                    }

                    try {
                        Integer.parseInt(capacidadMaximaHangar);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La capacidad máxima debe ser un número entero.");
                        return;
                    }

                    try {
                        Double.parseDouble(costeHoraHangar);
                        Double.parseDouble(largoHangar);
                        Double.parseDouble(altoHangar);
                        Double.parseDouble(anchoHangar);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El coste por hora, el largo, el alto y el ancho deben ser un número decimal.");
                        return;
                    }

                    Hangar hangar = null;

                    if(vista.getTipoHangar().equals("Pasajeros")) {
                        hangar = app.darAltaHangarPasajero(new Dimension(Double.parseDouble(anchoHangar), Double.parseDouble(largoHangar), Double.parseDouble(altoHangar)), Integer.parseInt(capacidadMaximaHangar), Double.parseDouble(costeHoraHangar));
                    }
                    else if(vista.getTipoHangar().equals("Mercancías")) {
                        hangar = app.darAltaHangarMercancia(new Dimension(Double.parseDouble(anchoHangar), Double.parseDouble(largoHangar), Double.parseDouble(altoHangar)), Integer.parseInt(capacidadMaximaHangar), vista.isMercanciasPeligrosas(), Double.parseDouble(costeHoraHangar));
                    }

                    if(hangar != null) {
                        JOptionPane.showMessageDialog(null, "Hangar creado correctamente.");
                        frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                        vista.limpiarFormularios();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error al crear el hangar.");
                    }

                    break;
            }

        } else if (e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
            vista.limpiarFormularios();
        }

        else if (e.getActionCommand().equals("Cancelar")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
            vista.limpiarFormularios();
        }

    }

}
