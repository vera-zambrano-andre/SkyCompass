package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Clase que representa el menú principal del Gestor en la interfaz gráfica.
 * Contiene botones con submenús para acceder a las distintas funcionalidades
 * de la aplicación, como gestión de vuelos, elementos del aeropuerto, pagos,
 * notificaciones, configuración, entre otros.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class MenuGestor extends JPanel {

    /**
     * Constructor del menú de Gestor. Crea el panel con las opciones de navegación.
     *
     * @param frame  La ventana principal donde se cargan los paneles correspondientes.
     * @param perfil Objeto de perfil utilizado para actualizar datos del gestor.
     */
    public MenuGestor(VentanaPrincipal frame, Perfil perfil) {

        Map<String, String[]> opcionesConSubmenu = new LinkedHashMap<>();
        opcionesConSubmenu.put("Inicio", new String[]{"Perfil", "Dar de alta usuario", "Resetear", "Cerrar sesión"});
        opcionesConSubmenu.put("Vuelos", new String[]{"Filtrar", "Vuelos programados", "Solicitudes de vuelo"});
        opcionesConSubmenu.put("Aeropuerto", new String[]{"Elementos", "Dar de alta elemento", "Cargar aeropuertos"});
        opcionesConSubmenu.put("Pago", new String[]{"Órdenes de pago", "Historial de pagos", "Dar de alta descuentos", "Descuentos SKYCOMPASS"});
        opcionesConSubmenu.put("Notificaciones", new String[]{"Notificaciones"});
        opcionesConSubmenu.put("Estadística", new String[]{"Estadisticas"});
        opcionesConSubmenu.put("Configuración", new String[]{"Configurar notificaciones", "Configuración por defecto", "Configurar mi aeropuerto", "Configuración costes"});

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));

        for (Map.Entry<String, String[]> entry : opcionesConSubmenu.entrySet()) {
            String titulo = entry.getKey();
            String[] submenuItems = entry.getValue();

            JButton botonPrincipal = new JButton(titulo);
            botonPrincipal.setFocusPainted(false);
            botonPrincipal.setBackground(Color.WHITE);
            botonPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            botonPrincipal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JPopupMenu submenu = new JPopupMenu();
            submenu.setBorder(new LineBorder(Color.BLUE));

            for (String item : submenuItems) {
                JMenuItem menuItem = new JMenuItem(item);
                menuItem.addActionListener(e -> {
                    switch (item) {
                        case "Perfil":
                            perfil.actualizarPerfil();
                            frame.changeVisiblePanel(VentanaPrincipal.PERFILGESTOR);
                            break;
                        case "Cerrar sesión":
                            frame.changeVisiblePanel(VentanaPrincipal.LOGIN);
                            break;
                        case "Dar de alta usuario":
                            frame.changeVisiblePanel(VentanaPrincipal.DARALTAUSUARIO);
                            break;
                        case "Resetear":
                            frame.changeVisiblePanel(VentanaPrincipal.RESETEAR);
                            break;
                        case "Filtrar":
                            frame.changeVisiblePanel(VentanaPrincipal.FILTRARGESTOR);
                            break;
                        case "Vuelos programados":
                            frame.changeVisiblePanel(VentanaPrincipal.TABLAPROGRAMADOGESTOR);
                            break;
                        case "Solicitudes de vuelo":
                            frame.changeVisiblePanel(VentanaPrincipal.SOLICITUDVUELO);
                            break;
                        case "Elementos":
                            frame.changeVisiblePanel(VentanaPrincipal.ELEMENTOSAEROPUERTOGESTOR);
                            break;
                        case "Dar de alta elemento":
                            frame.changeVisiblePanel(VentanaPrincipal.DARALTAELEMENTO);
                            break;
                        case "Órdenes de pago":
                            frame.changeVisiblePanel(VentanaPrincipal.LANZARPAGO);
                            break;
                        case "Historial de pagos":
                            frame.changeVisiblePanel(VentanaPrincipal.HISTORIALPAGOSGESTOR);
                            break;
                        case "Notificaciones":
                            frame.changeVisiblePanel(VentanaPrincipal.NOTIFICACIONESGESTOR);
                            break;
                        case "Configurar notificaciones":
                            frame.changeVisiblePanel(VentanaPrincipal.CONFIGURACIONNOTIFICACIONES);
                            break;
                        case "Estadisticas":
                            frame.changeVisiblePanel(VentanaPrincipal.ESTADISTICAGESTOR);
                            break;
                        case "Configuración por defecto":
                            frame.changeVisiblePanel(VentanaPrincipal.CONFIGURACIONGESTOR);
                            break;
                        case "Configurar mi aeropuerto":
                            frame.changeVisiblePanel(VentanaPrincipal.CONFIGURACIONAEROPUERTOGESTOR);
                            break;
                        case "Cargar aeropuertos":
                            frame.changeVisiblePanel(VentanaPrincipal.CARGAFICHEROAEROPUERTOS);
                            break;
                        case "Dar de alta descuentos":
                            frame.changeVisiblePanel(VentanaPrincipal.DARALTADESCUENTOS);
                            break;
                        case "Descuentos SKYCOMPASS":
                            frame.changeVisiblePanel(VentanaPrincipal.HISTORIALDESCUENTOSGESTOR);
                            break;
                        case "Configuración costes":
                            frame.changeVisiblePanel(VentanaPrincipal.CONFIGURACIONCOSTES);
                            break;
                    }
                });
                submenu.add(menuItem);
            }

            botonPrincipal.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    submenu.show(botonPrincipal, 0, botonPrincipal.getHeight());
                }
            });

            this.add(botonPrincipal);
        }
    }
}
