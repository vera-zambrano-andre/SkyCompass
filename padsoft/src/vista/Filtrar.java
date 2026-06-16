package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Clase que representa un panel de filtrado para buscar vuelos.
 * Proporciona campos para ingresar criterios de búsqueda y botones para aplicar o eliminar filtros.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Filtrar extends JPanel {

    private final Map<String, String> filtros = new HashMap<>();
    private final JTextField idField = new JTextField(10);
    private final JTextField destinoField = new JTextField(10);
    private final JTextField origenField = new JTextField(10);
    private final JTextField aerolineaField = new JTextField(10);
    private final JTextField terminalField = new JTextField(10);
    private final JTextField modeloField = new JTextField(10);
    private final JTextField fechaSalField = new JTextField(10);
    private final JTextField fechaLlegField = new JTextField(10);
    private final JComboBox<String> tipoCombo = new JComboBox<>(new String[] { "Todos", "Pasajeros", "Mercancía" });

    private final JButton aplicarBoton = new JButton("Aplicar filtros");
    private final JButton eliminarBoton = new JButton("Eliminar filtros");

    private final VentanaPrincipal frame;
    private final String next;

    /**
     * Constructor de la clase Filtrar.
     *
     * @param frame Ventana principal desde la que se invoca este panel.
     * @param next Nombre del panel al que se debe regresar al presionar "Volver".
     */
    public Filtrar(VentanaPrincipal frame, String next) {
        this.frame = frame;
        this.next = next;
        
        setLayout(new BorderLayout());

        add(crearBarraSuperior(), BorderLayout.NORTH);
        add(crearPanelFiltros(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);

        cargarFiltrosPrevios();
    }

    /**
     * Crea y retorna la barra superior con el botón "Volver".
     *
     * @return JPanel con la barra superior.
     */
    private JPanel crearBarraSuperior() {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton volverBoton = new JButton("← Volver");

        volverBoton.addActionListener(e -> {
            frame.changeVisiblePanel(next);
            this.limpiarCampos();
        });

        barra.add(volverBoton);
        return barra;
    }

    /**
     * Crea y retorna el panel central con los campos de filtrado.
     *
     * @return JPanel con los campos de filtro.
     */
    private JPanel crearPanelFiltros() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarCampo(panel, gbc, "ID", idField, 0, 0);
        agregarCampo(panel, gbc, "DESTINO", destinoField, 0, 1);
        agregarCampo(panel, gbc, "ORIGEN", origenField, 0, 2);
        agregarCampo(panel, gbc, "AEROLÍNEA", aerolineaField, 0, 3);
        agregarCampo(panel, gbc, "TERMINAL", terminalField, 0, 4);

        agregarCampo(panel, gbc, "MODELO", modeloField, 2, 0);
        agregarCampo(panel, gbc, "FECHA SAL.", fechaSalField, 2, 1);
        agregarCampo(panel, gbc, "FECHA LLEG.", fechaLlegField, 2, 2);
        agregarCampo(panel, gbc, "TIPO", tipoCombo, 2, 3);

        return panel;
    }

    /**
     * Agrega un campo de entrada con su etiqueta al panel.
     *
     * @param panel Panel al que se agrega el campo.
     * @param gbc Constraints del layout.
     * @param label Texto de la etiqueta.
     * @param field Componente del campo.
     * @param x Coordenada X en el grid.
     * @param y Coordenada Y en el grid.
     */
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int x, int y) {
        agregarCampo(panel, gbc, label, field, x, y, Color.BLACK);
    }

    /**
     * Agrega un campo con color de etiqueta personalizado.
     *
     * @param panel Panel al que se agrega el campo.
     * @param gbc Constraints del layout.
     * @param label Texto de la etiqueta.
     * @param field Componente del campo.
     * @param x Coordenada X en el grid.
     * @param y Coordenada Y en el grid.
     * @param color Color del texto de la etiqueta.
     */
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int x, int y, Color color) {
        gbc.gridx = x;
        gbc.gridy = y;
        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(color);
        panel.add(jLabel, gbc);

        gbc.gridx = x + 1;
        panel.add(field, gbc);
    }

    /**
     * Crea y retorna el panel inferior con los botones de aplicar y eliminar filtros.
     *
     * @return JPanel con botones.
     */
    private JPanel crearPanelBotones() {
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonesPanel.add(aplicarBoton);
        botonesPanel.add(eliminarBoton);
        return botonesPanel;
    }

    /**
     * Retorna el botón para aplicar filtros.
     *
     * @return JButton aplicarBoton
     */
    public JButton getAplicarButton() {
        return aplicarBoton;
    }

    /**
     * Retorna el botón para eliminar filtros.
     *
     * @return JButton eliminarBoton
     */
    public JButton getEliminarButton() {
        return eliminarBoton;
    }

    /**
     * Retorna el mapa de filtros actuales ingresados por el usuario.
     *
     * @return Mapa con filtros activos.
     */
    public Map<String, String> getFiltros() {
        filtros.clear();
        putIfNotEmpty("ID", idField.getText());
        putIfNotEmpty("DESTINO", destinoField.getText());
        putIfNotEmpty("ORIGEN", origenField.getText());
        putIfNotEmpty("AEROLÍNEA", aerolineaField.getText());
        putIfNotEmpty("TERMINAL", terminalField.getText());
        putIfNotEmpty("MODELO", modeloField.getText());
        putIfNotEmpty("FECHA_SAL", fechaSalField.getText());
        putIfNotEmpty("FECHA_LLEG", fechaLlegField.getText());
        String tipoSeleccionado = (String) tipoCombo.getSelectedItem();
        if (tipoSeleccionado != null && !tipoSeleccionado.equals("Todos")) {
            filtros.put("TIPO", tipoSeleccionado);
        }
        return new HashMap<>(filtros);
    }

    /**
     * Agrega una entrada al mapa de filtros si el valor no está vacío.
     *
     * @param clave Clave del filtro.
     * @param valor Valor del filtro.
     */
    private void putIfNotEmpty(String clave, String valor) {
        if (valor != null && !valor.trim().isEmpty()) {
            filtros.put(clave, valor.trim());
        }
    }

    /**
     * Elimina todos los filtros internos y limpia los campos.
     */
    public void eliminarFiltrosInternos() {
        limpiarCampos();
        filtros.clear();
    }

    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarCampos() {
        idField.setText("");
        destinoField.setText("");
        origenField.setText("");
        aerolineaField.setText("");
        terminalField.setText("");
        modeloField.setText("");
        fechaSalField.setText("");
        fechaLlegField.setText("");
        tipoCombo.setSelectedIndex(0);
    }

    /**
     * Carga los filtros previos si existen, o limpia los campos si no hay filtros cargados.
     */
    private void cargarFiltrosPrevios() {
        if (!filtros.isEmpty()) {
            idField.setText(filtros.getOrDefault("ID", ""));
            destinoField.setText(filtros.getOrDefault("DESTINO", ""));
            origenField.setText(filtros.getOrDefault("ORIGEN", ""));
            aerolineaField.setText(filtros.getOrDefault("AEROLÍNEA", ""));
            terminalField.setText(filtros.getOrDefault("TERMINAL", ""));
            modeloField.setText(filtros.getOrDefault("MODELO", ""));
            fechaSalField.setText(filtros.getOrDefault("FECHA_SAL", ""));
            fechaLlegField.setText(filtros.getOrDefault("FECHA_LLEG", ""));
            tipoCombo.setSelectedItem(filtros.getOrDefault("TIPO", "Todos"));
        } else {
            limpiarCampos();
        }
    }

    /**
     * Asigna un controlador a los botones de aplicar y eliminar filtros.
     *
     * @param e ActionListener que manejará los eventos.
     */
    public void setControlador(ActionListener e) {
        aplicarBoton.addActionListener(e);
        eliminarBoton.addActionListener(e);
    }
}
