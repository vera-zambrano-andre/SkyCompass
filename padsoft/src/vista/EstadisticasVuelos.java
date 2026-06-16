package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import vuelo.Vuelo;
import estadística.Estadistica;
import sistema.SkyCompass;
import java.util.Map;

/**
 * Panel de la vista gestor que permite generar estadísticas de un vuelo
 * en función de su código. Muestra un gráfico de barras con datos estadísticos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class EstadisticasVuelos extends JPanel {
    private final JTextField codigoField = new JTextField(10);
    private final JButton generarBoton = new JButton("Generar Estadísticas");
    private final JLabel mensajeLabel = new JLabel(" ");
    private final JPanel graficoContainer = new JPanel(new BorderLayout());
    private JButton botonVolver;
    private JTextField fechaField = new JTextField(10);

    /**
     * Constructor que inicializa la interfaz del panel de estadísticas.
     * 
     * @param frame referencia a la ventana principal para poder cambiar de panel.
     */
    public EstadisticasVuelos(VentanaPrincipal frame) {
        setLayout(new BorderLayout(10, 10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Código de vuelo:"));
        top.add(codigoField);

        top.add(new JLabel("Fecha del vuelo (AAAA-MM-DD):"));
        top.add(fechaField);

        generarBoton.setActionCommand("generar");
        top.add(generarBoton);
        add(top, BorderLayout.NORTH);

        mensajeLabel.setForeground(Color.RED);
        add(mensajeLabel, BorderLayout.CENTER);

        add(graficoContainer, BorderLayout.CENTER);

        botonVolver = new JButton("← Volver");

        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barra.add(botonVolver);
        add(barra, BorderLayout.PAGE_END);
    }

    /**
     * Asigna el controlador de eventos al botón de generar estadísticas.
     * 
     * @param e el ActionListener a asociar
     */
    public void setControlador(ActionListener e) {
        generarBoton.addActionListener(e);
        botonVolver.addActionListener(e);
    }

    /**
     * Devuelve el texto del campo de texto de código de vuelo,
     * sin espacios al principio ni al final.
     * 
     * @return Código de vuelo sin espacios.
     */
    public String getCodigoVuelo() {
        return codigoField.getText().trim();
    }

    /**
     * Devuelve el texto del campo de texto de fecha de vuelo,
     * sin espacios al principio ni al final.
     * 
     * @return Fecha del vuelo en formato de cadena sin espacios.
     */

    public String getFechaVuelo() {
        return fechaField.getText().trim();
    }

    /**
     * Muestra un mensaje de error en el panel. El mensaje se
     * mostrará en el lugar donde se encuentra el gráfico.
     * El gráfico se eliminará de la pantalla.
     * 
     * @param msg el mensaje de error a mostrar
     */
    public void mostrarError(String msg) {
        mensajeLabel.setText(msg);
        graficoContainer.removeAll();
        revalidate();
        repaint();
    }

    /**
     * Muestra un gráfico de barras en el panel con los datos estadísticos
     * del vuelo correspondiente al código proporcionado.
     * 
     * @param datos mapa de nombre de recurso a valor numérico (tiempo en horas)
     */
    public void mostrarGrafico(Map<String, Double> datos) {
        mensajeLabel.setText(" ");
        graficoContainer.removeAll();
        graficoContainer.add(new PanelGraficoVuelo(datos), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Clase interna que representa un panel gráfico de barras para visualizar
     * estadísticas de vuelo.
     */

    static class PanelGraficoVuelo extends JPanel {
        private final String[] recursos;
        private final double[] valores;

        /**
         * Constructor que inicializa el gráfico con los datos proporcionados.
         * 
         * @param datos mapa de nombre de recurso a valor numérico (tiempo en horas).
         */
        PanelGraficoVuelo(Map<String, Double> datos) {
            this.recursos = datos.keySet().toArray(new String[0]);
            this.valores = datos.values().stream().mapToDouble(Double::doubleValue).toArray();
            setPreferredSize(new Dimension(800, 400));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int ancho = getWidth(), alto = getHeight();
            int margen = 50;
            int n = recursos.length;
            if (n == 0)
                return;
            double maxVal = 0;
            for (double v : valores)
                maxVal = Math.max(maxVal, v);
            int barraAncho = (ancho - 2 * margen) / n / 2;
            int espacio = barraAncho;
            for (int i = 0; i < n; i++) {
                int x = margen + i * (barraAncho + espacio);
                int altura = (int) ((valores[i] / maxVal) * (alto - 2 * margen));
                int y = alto - margen - altura;
                g2.fillRect(x, y, barraAncho, altura);
                g2.drawString(recursos[i], x, alto - margen + 15);
                g2.drawString(String.format("%.2fh", valores[i]), x, y - 5);
            }
            g2.drawLine(margen, alto - margen, ancho - margen, alto - margen);
            g2.drawLine(margen, margen, margen, alto - margen);
        }
    }
}
