package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import aeropuerto.Dimension;
import aeropuerto.Aerolinea;
import aeropuerto.Aeropuerto;
import aeropuerto.Avion;
import aeropuerto.PistaAterrizaje;
import aeropuerto.PistaDespegue;
import aeropuerto.PuertaEmbarque;
import aeropuerto.TerminalMercancia;
import aeropuerto.TerminalPasajero;
import aeropuerto.TipoAvion;
import aeropuerto.TipoAvionMercancia;
import aeropuerto.TipoAvionPasajero;
import elementocoste.Finger;
import elementocoste.HangarMercancia;
import elementocoste.HangarPasajero;
import elementocoste.Plaza;
import elementocoste.ZonaAparcamiento;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la tabla de elementos
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class TablaElemento extends JPanel {

    private CardLayout cardLayout;
    private JPanel tablePanel;
    private JComboBox<String> selector;
    private JButton botonVolver;
    private String next;

    /**
     * Constructor de la clase TablaElemento
     * 
     * @param frame ventana principal
     * @param next  siguiente panel
     */
    public TablaElemento(VentanaPrincipal frame, String next) {
        this.next = next;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] elementos = {
                "", "Aeropuerto", "Pista", "Terminal", "Puerta de embarque",
                "Aparcamiento", "Plaza", "Finger", "Hangar", "Aerolínea", "Tipo de avión", "Avión"
        };

        selector = new JComboBox<>(elementos);

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonVolver = new JButton("← Volver");
        barraSuperior.add(botonVolver);

        JPanel selectorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectorPanel.add(new JLabel("Seleccione elemento: "));
        selectorPanel.add(selector);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(barraSuperior, BorderLayout.WEST);
        panelSuperior.add(selectorPanel, BorderLayout.CENTER);
        this.add(panelSuperior, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        tablePanel = new JPanel(cardLayout);

        tablePanel.add(new JPanel(), "");

        this.add(tablePanel, BorderLayout.CENTER);
    }

    /**
     * Crea una tabla con los datos proporcionados
     * 
     * @param columnNames nombres de las columnas
     * @param data        datos
     * @return JScrollPane con la tabla
     */
    private JScrollPane crearTabla(String[] columnNames, Object[][] data) {
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);
        return new JScrollPane(table);
    }

    /**
     * Crea una tabla con los datos de las aerolíneas proporcionadas.
     * 
     * @param lista lista de aerolíneas a mostrar
     * @return JScrollPane con la tabla
     */
    public JScrollPane crearTablaAerolineas(List<Aerolinea> lista) {
        String[] columnas = { "Nombre", "ID" };
        Object[][] datos = new Object[lista.size()][2];
        for (int i = 0; i < lista.size(); i++) {
            Aerolinea a = lista.get(i);
            datos[i][0] = a.getNombre();
            datos[i][1] = a.getId();
        }
        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de los aeropuertos proporcionados.
     * 
     * @param lista lista de aeropuertos a mostrar
     * @return JScrollPane con la tabla
     */
    public JScrollPane crearTablaAeropuertos(List<Aeropuerto> lista) {
        String[] columnas = { "Código", "Nombre", "Ciudad", "País", "Dirección", "Temporadas", "Diferencia Horaria" };
        Object[][] datos = new Object[lista.size()][7];

        for (int i = 0; i < lista.size(); i++) {
            Aeropuerto a = lista.get(i);
            datos[i][0] = a.getCodigo();
            datos[i][1] = a.getNombre();
            datos[i][2] = a.getCiudadMasCercana();
            datos[i][3] = a.getPais();
            datos[i][4] = a.getDireccion().toString();
            String temporadas = "-";
            if (a.getTemporadas().size() != 0) {
                temporadas = a.getTemporadas().toString();
            }
            datos[i][5] = temporadas;
            datos[i][6] = a.getDiferenciaHoraria();
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de las pistas de despegue y aterrizaje
     * proporcionadas.
     * 
     * @param despegues   lista de pistas de despegue a mostrar
     * @param aterrizajes lista de pistas de aterrizaje a mostrar
     * @return JScrollPane con la tabla de pistas
     */
    public JScrollPane crearTablaPistas(List<PistaDespegue> despegues, List<PistaAterrizaje> aterrizajes) {
        String[] columnas = { "Longitud", "Tipo" };
        int totalSize = despegues.size() + aterrizajes.size();
        Object[][] datos = new Object[totalSize][2];

        int index = 0;
        for (PistaDespegue despegue : despegues) {
            datos[index][0] = despegue.getLongitud();
            datos[index][1] = "Despegue";
            index++;
        }

        for (PistaAterrizaje aterrizaje : aterrizajes) {
            datos[index][0] = aterrizaje.getLongitud();
            datos[index][1] = "Aterrizaje";
            index++;
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de los terminales de pasajeros y mercancías
     * proporcionados.
     * 
     * @param mercancias lista de terminales de mercancías a mostrar
     * @param pasajeros  lista de terminales de pasajeros a mostrar
     * @return JScrollPane con la tabla de terminales
     */
    public JScrollPane crearTablaTerminales(List<TerminalMercancia> mercancias, List<TerminalPasajero> pasajeros) {

        int total = mercancias.size() + pasajeros.size();
        String[] columnas = { "Código", "Tipo", "Max. Plazas", "Horario de disponibilidad", "Zona Aparacamiento",
                "Puertas de embarque" };
        Object[][] datos = new Object[total][6];

        int i = 0;
        for (TerminalPasajero t : pasajeros) {
            datos[i][0] = t.getCodigo();
            datos[i][1] = "Pasajeros";
            datos[i][2] = t.getMaxPlazas();
            datos[i][3] = t.getHorarioDisponibilidad().toString();
            datos[i][4] = "-";

            if (t.getPuertaEmbarque().size() != 0) {
                List<String> puertas = new ArrayList<>();
                for (PuertaEmbarque p : t.getPuertaEmbarque()) {
                    puertas.add(p.getCodigo());
                }
                datos[i][5] = puertas;
            } else {
                datos[i][5] = "-";
            }
            i++;
        }

        for (TerminalMercancia t : mercancias) {
            datos[i][0] = t.getCodigo();
            datos[i][1] = "Mercancías";
            datos[i][2] = t.getMaxPlazas();
            datos[i][3] = t.getHorarioDisponibilidad().toString();

            String aparcamiento = "-";
            if (t.getAparcamientos() != null) {
                for (ZonaAparcamiento z : t.getAparcamientos()) {
                    aparcamiento = z.getCodigo();
                }
            }

            datos[i][4] = aparcamiento;

            datos[i][5] = "-";

            i++;
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de las puertas de embarque proporcionadas.
     * 
     * @param lista Lista de puertas de embarque a mostrar.
     * @return JScrollPane con la tabla de puertas de embarque.
     */
    public JScrollPane crearTablaPuertas(List<PuertaEmbarque> lista) {
        String[] columnas = { "Código", "Finger", "Aparcamiento" };
        Object[][] datos = new Object[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            PuertaEmbarque p = lista.get(i);
            datos[i][0] = p.getCodigo();

            String finger = "-";
            if (p.getFinger() != null) {
                finger = p.getFinger().getCodigo();
            }

            datos[i][1] = finger;

            String aparcamiento = "-";
            if (p.getAparcamientos() != null) {
                for (ZonaAparcamiento z : p.getAparcamientos()) {
                    aparcamiento = z.getCodigo();
                }
            }
            datos[i][2] = aparcamiento;
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de las zonas de aparcamiento proporcionadas.
     * 
     * @param lista Lista de zonas de aparcamiento a mostrar.
     * @return JScrollPane con la tabla de zonas de aparcamiento.
     */
    public JScrollPane crearTablaAparcamientos(List<ZonaAparcamiento> lista) {
        String[] columnas = { "Código", "Plazas", "Coste/hora", "Capacidad" };
        Object[][] datos = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            ZonaAparcamiento z = lista.get(i);
            datos[i][0] = z.getCodigo();

            String plazas = "-";
            if (z.getPlazas().size() != 0) {
                plazas = z.getPlazas().toString();
            }
            datos[i][1] = plazas;
            datos[i][2] = z.getCosteHora();
            datos[i][3] = z.getMaxCapacidad();
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de las plazas proporcionadas.
     * 
     * @param lista lista de plazas a mostrar.
     * @return JScrollPane con la tabla de plazas.
     */
    public JScrollPane crearTablaPlazas(List<Plaza> lista) {
        String[] columnas = { "Ancho", "Largo" };
        Object[][] datos = new Object[lista.size()][2];

        for (int i = 0; i < lista.size(); i++) {
            Plaza p = lista.get(i);
            datos[i][0] = p.getAncho();
            datos[i][1] = p.getLargo();
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de los fingers proporcionados.
     * 
     * @param lista lista de fingers a mostrar.
     * @return JScrollPane con la tabla de fingers.
     */
    public JScrollPane crearTablaFingers(List<Finger> lista) {
        String[] columnas = { "Código", "Puerta doble", "Altura máx.", "Coste/hora" };
        Object[][] datos = new Object[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            Finger f = lista.get(i);
            datos[i][0] = f.getCodigo();
            if (f.isPuertaDoble()) {
                datos[i][1] = "Sí";
            } else {
                datos[i][1] = "No";
            }
            datos[i][2] = f.getAlturaMaxima();
            datos[i][3] = f.getCosteHora();
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de los hangares de mercancia y pasajeros
     * proporcionados.
     * 
     * @param mercancias lista de hangares de mercancia a mostrar.
     * @param pasajeros  lista de hangares de pasajeros a mostrar.
     * @return JScrollPane con la tabla de hangares.
     */
    public JScrollPane crearTablaHangares(List<HangarMercancia> mercancias, List<HangarPasajero> pasajeros) {
        int total = mercancias.size() + pasajeros.size();
        String[] columnas = { "Plazas", "Coste/hora", "Tipo", "Mercancías peligrosas", "Ancho", "Largo", "Alto" };
        Object[][] datos = new Object[total][7];

        int i = 0;
        for (HangarMercancia h : mercancias) {
            datos[i][0] = h.getPlazas();
            datos[i][1] = h.getCosteHora();
            datos[i][2] = "Mercancías";
            if (h.permiteMercanciasPeligrosas()) {
                datos[i][3] = "Sí";
            } else {
                datos[i][3] = "No";
            }

            Dimension d = h.getDimension();
            datos[i][4] = d.getAncho();
            datos[i][5] = d.getLargo();
            datos[i][6] = d.getAlto();
            i++;
        }

        for (HangarPasajero h : pasajeros) {
            datos[i][0] = h.getPlazas();
            datos[i][1] = h.getCosteHora();
            datos[i][2] = "Pasajeros";
            datos[i][3] = "-";
            Dimension d = h.getDimension();
            datos[i][4] = d.getAncho();
            datos[i][5] = d.getAlto();
            datos[i][6] = d.getLargo();
            i++;
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de los tipos de aviones de mercancia y pasajeros
     * proporcionados.
     * 
     * @param tiposMercancias lista de tipos de aviones de mercancia a mostrar.
     * @param tiposPasajeros  lista de tipos de aviones de pasajeros a mostrar.
     * @return JScrollPane con la tabla de tipos de aviones.
     */
    public JScrollPane crearTablaTiposAvion(List<TipoAvionMercancia> tiposMercancias,
            List<TipoAvionPasajero> tiposPasajeros) {
        String[] columnas = { "Marca", "Modelo", "Ancho", "Largo", "Alto", "Autonomía", "Tipo", "Max. Pasajeros",
                "Hay control de temperatura", "Permite mercancias peligrosas", "Max. Carga" };
        int total = tiposMercancias.size() + tiposPasajeros.size();
        Object[][] datos = new Object[total][11];

        int i = 0;
        for (TipoAvionMercancia t : tiposMercancias) {
            datos[i][0] = t.getMarca();
            datos[i][1] = t.getModelo();
            datos[i][2] = t.getDimension().getAncho();
            datos[i][3] = t.getDimension().getLargo();
            datos[i][4] = t.getDimension().getAlto();
            datos[i][5] = t.getAutonomia();
            datos[i][6] = "Mercancias";
            datos[i][7] = "-";
            if (t.controlTemperatura()) {
                datos[i][8] = "Sí";
            } else {
                datos[i][8] = "No";
            }
            if (t.permiteMercanciasPeligrosas()) {
                datos[i][9] = "Sí";
            } else {
                datos[i][9] = "No";
            }
            datos[i][10] = t.getMaxCarga();
            i++;
        }
        for (TipoAvionPasajero t : tiposPasajeros) {
            datos[i][0] = t.getMarca();
            datos[i][1] = t.getModelo();
            datos[i][2] = t.getDimension().getAncho();
            datos[i][3] = t.getDimension().getLargo();
            datos[i][4] = t.getDimension().getAlto();
            datos[i][5] = t.getAutonomia();
            datos[i][6] = "Pasajeros";
            datos[i][7] = t.getMaxPasajeros();
            datos[i][8] = "-";
            datos[i][9] = "-";
            datos[i][10] = "-";
            i++;
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Crea una tabla con los datos de los aviones proporcionados.
     * 
     * @param aviones lista de aviones a mostrar
     * @return JScrollPane con la tabla de aviones
     */

    public JScrollPane crearTablaAviones(List<Avion> aviones) {
        String[] columnas = { "Año de compra", "Fecha de la última revisión", "Información sobre el tipo de avión" };
        Object[][] datos = new Object[aviones.size()][7];

        int i = 0;
        for (Avion a : aviones) {
            datos[i][0] = a.getAnoCompra();
            datos[i][1] = a.getFechaRevision();
            datos[i][2] = a.getTipo();
            i++;
        }

        return crearTabla(columnas, datos);
    }

    /**
     * Muestra la tabla correspondiente al tipo especificado en el panel de la
     * interfaz.
     * 
     * @param tipo  el tipo de tabla a mostrar
     * @param tabla el JScrollPane que contiene la tabla a mostrar
     */
    public void mostrarTabla(String tipo, JScrollPane tabla) {
        tablePanel.add(tabla, tipo);
        cardLayout.show(tablePanel, tipo);
    }

    /**
     * Registra el controlador para todos los componentes interactivos.
     *
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        selector.addActionListener(e);
        botonVolver.addActionListener(e);
    }

    /**
     * Obtiene el selector de JComboBox utilizado en la interfaz.
     * 
     * @return el JComboBox que actúa como selector en la interfaz.
     */
    public JComboBox<String> getSelector() {
        return selector;
    }

    /**
     * Obtiene el siguiente valor en la secuencia.
     *
     * @return el siguiente valor como una cadena.
     */
    public String getNext() {
        return next;
    }

    /**
     * Limpia los campos de texto y restablece los valores predeterminados
     * de los combo boxes en el formulario.
     */
    public void limpiar() {
        selector.setSelectedIndex(0);
        tablePanel.removeAll();
        cardLayout.first(tablePanel);
    }
}
