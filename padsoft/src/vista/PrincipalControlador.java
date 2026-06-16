package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import sistema.*;

/**
 * Clase de la pantalla principal de la interfaz gráfica del usuario con rol de Controlador.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class PrincipalControlador extends JPanel {

    /** Tabla que muestra los vuelos controlables por el controlador */
    private TablaPrincipal tablaPrincipal = new TablaPrincipal();

    /** Tabla que muestra los vuelos de hoy*/
    private TablaVuelosHoy tablaVuelosHoy = new TablaVuelosHoy();

    /**
     * Constructor que inicializa el panel principal del controlador.
     *
     * @param frame la ventana principal que contiene todos los paneles
     * @param perfil el panel de perfil del usuario logueado
     */
    
    public PrincipalControlador(VentanaPrincipal frame, Perfil perfil) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 0, 50, 0));

        JPanel menuSuperior = new MenuControlador(frame, perfil);

        JPanel contenedorBarra = new JPanel(new BorderLayout());
        contenedorBarra.setBackground(Color.WHITE);
        contenedorBarra.add(menuSuperior, BorderLayout.CENTER);
        contenedorBarra.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tablaVuelosControlados = new JLabel("Tabla Vuelos (solo los que puede se puede controlar)");
        tablaVuelosControlados.setFont(new Font("Arial", Font.BOLD, 24));
        tablaVuelosControlados.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablaVuelosControlados.setForeground(Color.BLUE);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(tablaVuelosControlados);
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
     * Devuelve la tabla principal que contiene los vuelos que el controlador puede gestionar.
     *
     * @return el componente {@link TablaPrincipal}
     */
    public TablaPrincipal getTablaPrincipal() {
        return tablaPrincipal;
    }

    /**
     * Devuelve la tabla que muestra los vuelos de hoy.
     *
     * @return el componente TablaVuelosHoy
     */
    public TablaVuelosHoy getTablaVuelosHoy() {
        return tablaVuelosHoy;
    }
}
