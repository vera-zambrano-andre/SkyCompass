package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import sistema.*;

/**
 * Clase del panel principal para el usuario con rol de Gestor.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class PrincipalGestor extends JPanel {

    /** Tabla que muestra todos los vuelos programados */
    private TablaPrincipal tablaPrincipal = new TablaPrincipal();

    /** Tabla que muestra los vuelos que salen hoy */
    private TablaVuelosHoy tablaVuelosHoy = new TablaVuelosHoy();

    /**
     * Constructor del panel principal del Gestor.
     *
     * @param frame la ventana principal que contiene este panel
     * @param perfil el panel de perfil del usuario logueado
     */

    public PrincipalGestor(VentanaPrincipal frame, Perfil perfil) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 0, 50, 0));

        JPanel menuSuperior = new MenuGestor(frame, perfil);

        JPanel contenedorBarra = new JPanel(new BorderLayout());
        contenedorBarra.setBackground(Color.WHITE);
        contenedorBarra.add(menuSuperior, BorderLayout.CENTER);
        contenedorBarra.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tablaVuelosProgramados = new JLabel("Vuelos programados");
        tablaVuelosProgramados.setFont(new Font("Arial", Font.BOLD, 24));
        tablaVuelosProgramados.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablaVuelosProgramados.setForeground(Color.BLUE);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(tablaVuelosProgramados);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(tablaPrincipal);

        JLabel tablaVuelos = new JLabel("Vuelos que salen hoy");
        tablaVuelos.setFont(new Font("Arial", Font.BOLD, 24));
        tablaVuelos.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablaVuelos.setForeground(Color.BLUE);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(tablaVuelos);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(tablaVuelosHoy);

        this.add(contenedorBarra, BorderLayout.NORTH);
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setVisible(false);
    }

    /**
     * Devuelve la tabla que muestra los vuelos programados.
     *
     * @return el componente TablaPrincipal
     */
    public TablaPrincipal getTablaPrincipal() {
        return this.tablaPrincipal;
    }

    /**
     * Devuelve la tabla que muestra los vuelos que salen hoy.
     *
     * @return el componente TablaVuelosHoy
     */
    public TablaVuelosHoy getTablaVuelosHoy() {
        return this.tablaVuelosHoy;
    }
}
