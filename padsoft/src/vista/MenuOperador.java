package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Clase que representa el menú lateral del operador en la interfaz gráfica.
 * Este panel contiene botones principales que despliegan submenús con diferentes opciones
 * relacionadas con las funcionalidades del sistema, como perfil, vuelos, pagos, etc.
 * 
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class MenuOperador extends JPanel {

    /**
     * Constructor del menú del operador.
     * Inicializa y configura el menú con botones principales y sus respectivos submenús.
     * Cada subopción del menú invoca un cambio de panel en la ventana principal.
     *
     * @param frame  la ventana principal del sistema donde se mostrarán los diferentes paneles
     * @param perfil el perfil actual del operador, usado para actualizar datos personales
     */
    public MenuOperador(VentanaPrincipal frame, Perfil perfil) {
        
        Map<String, String[]> opcionesConSubmenu = new LinkedHashMap<>();
        opcionesConSubmenu.put("Inicio", new String[]{"Perfil", "Cerrar sesión"});
        opcionesConSubmenu.put("Vuelos", new String[]{"Filtrar", "Vuelos programados", "Solicitar vuelo"});
        opcionesConSubmenu.put("Aeropuerto", new String[]{"Elementos", "Dar de alta avión", "Dar alta tipo de avión"});
        opcionesConSubmenu.put("Operaciones", new String[]{"Estado vuelos"});
        opcionesConSubmenu.put("Pago", new String[]{"Realizar pago", "Historial de pagos", "Descuentos SKYCOMPASS"});
        opcionesConSubmenu.put("Notificaciones", new String[]{"Notificaciones"});
        opcionesConSubmenu.put("Estadística", new String[]{"Estadisticas"});

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        this.setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));

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
                            frame.changeVisiblePanel(VentanaPrincipal.PERFILOPERADOR);
                            break;
                        case "Cerrar sesión":
                            frame.changeVisiblePanel(VentanaPrincipal.LOGIN);
                            break; 
                        case "Filtrar":
                            frame.changeVisiblePanel(VentanaPrincipal.FILTRAROPERADOR);
                            break;
                        case "Vuelos programados":
                            frame.changeVisiblePanel(VentanaPrincipal.TABLAPROGRAMADOOPERADOR);
                            break;
                        case "Solicitar vuelo":
                            frame.changeVisiblePanel(VentanaPrincipal.SOLICITARVUELO);
                            break;
                        case "Elementos":
                            frame.changeVisiblePanel(VentanaPrincipal.ELEMENTOSAEROPUERTOOPERADOR);
                            break;
                        case "Dar de alta avión":
                            frame.changeVisiblePanel(VentanaPrincipal.DARALTAAVION);
                            break;
                        case "Dar alta tipo de avión":
                            frame.changeVisiblePanel(VentanaPrincipal.DARALTATIPOAVION);
                            break;                    
                        case "Realizar pago":
                            frame.changeVisiblePanel(VentanaPrincipal.REALIZARPAGO);
                            break;
                        case "Historial de pagos":
                            frame.changeVisiblePanel(VentanaPrincipal.HISTORIALPAGOSOPERADOR);
                            break;
                        case "Notificaciones":
                            frame.changeVisiblePanel(VentanaPrincipal.NOTIFICACIONESOPERADOR);
                            break;
                        case "Estadisticas":
                            frame.changeVisiblePanel(VentanaPrincipal.ESTADISTICAOPERADOR);
                            break;
                        case "Descuentos SKYCOMPASS":
                            frame.changeVisiblePanel(VentanaPrincipal.HISTORIALDESCUENTOSOPERADOR);
                            break;
                        case "Estado vuelos":
                            frame.changeVisiblePanel(VentanaPrincipal.GESTIONESTADOVUELOOPERADOR);
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
