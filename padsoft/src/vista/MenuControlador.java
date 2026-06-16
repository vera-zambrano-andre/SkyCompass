package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel de menú principal para el controlador del sistema.
 * Incluye botones con submenús desplegables que permiten navegar entre distintas vistas
 * de la aplicación como gestión de vuelos, operaciones, perfil, etc.
 *
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class MenuControlador extends JPanel {

    /**
     * Constructor que crea y configura el menú del controlador.
     *
     * @param frame  Ventana principal a la que se le cambian los paneles visibles.
     * @param perfil Objeto de perfil para actualizar datos del usuario al acceder a la vista de perfil.
     */
    public MenuControlador(VentanaPrincipal frame, Perfil perfil) {

        Map<String, String[]> opcionesConSubmenu = new LinkedHashMap<>();
        opcionesConSubmenu.put("Inicio", new String[]{"Perfil", "Cerrar sesión"});
        opcionesConSubmenu.put("Vuelos", new String[]{"Filtrar", "Vuelos programados"});
        opcionesConSubmenu.put("Aeropuerto", new String[]{"Elementos"});
        opcionesConSubmenu.put("Operaciones", new String[]{"Estados Vuelos"});
        opcionesConSubmenu.put("Notificaciones", new String[]{"Notificaciones"});

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
                            frame.changeVisiblePanel(VentanaPrincipal.PERFILCONTROLADOR);
                            break;
                        case "Cerrar sesión":
                            frame.changeVisiblePanel(VentanaPrincipal.LOGIN);
                            break;
                        case "Filtrar":
                            frame.changeVisiblePanel(VentanaPrincipal.FILTRARCONTROLADOR);
                            break;
                        case "Vuelos programados":
                            frame.changeVisiblePanel(VentanaPrincipal.TABLAPROGRAMADOCONTROLADOR);
                            break;
                        case "Estados Vuelos":
                            frame.changeVisiblePanel(VentanaPrincipal.GESTIONESTADOVUELOCONTROLADOR);
                            break;
                        case "Elementos":
                            frame.changeVisiblePanel(VentanaPrincipal.ELEMENTOSAEROPUERTOCONTROLADOR);
                            break;
                        case "Notificaciones":
                            frame.changeVisiblePanel(VentanaPrincipal.NOTIFICACIONESCONTROLADOR);
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
