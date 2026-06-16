package controlador;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.*;

import aeropuerto.Terminal;
import sistema.SkyCompass;
import usuario.ConfiguracionPorDefecto;
import usuario.Controlador;
import vista.SolicitudVuelos;
import vista.VentanaPrincipal;
import vuelo.VueloProgramado;

/**
 * Clase que representa el controlador de la solicitud de vuelos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlSolicitudVuelos implements ActionListener {
    private final VentanaPrincipal frame;
    private final SolicitudVuelos vista;
    private final SkyCompass app;

    /**
     * Constructor del controlador.
     *
     * @param frame Ventana principal de la aplicación.
     * @param app   Sistema de gestión SkyCompass.
     */
    public ControlSolicitudVuelos(VentanaPrincipal frame, SkyCompass app) {
        this.frame = frame;
        this.app = app;
        this.vista = frame.getPanelSolicitudVuelos();
    }

    /**
     * Maneja los eventos de botones de la vista: volver, aceptar o rechazar
     * solicitud de vuelo.
     *
     * @param e Evento de acción recibido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "← Volver":
                frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALGESTOR);
                break;

            case "Aceptar":
                manejarAceptar();
                break;

            case "Rechazar":
                manejarRechazar();
                break;
        }
    }

    /**
     * Recarga la tabla de solicitudes de vuelo en la vista.
     */
    public void recargarTablaSolicitudes() {
        List<VueloProgramado> solicitudes = app.getVuelosSolicitados();
        vista.actualizarTabla(solicitudes);
    }

    /**
     * Maneja la lógica para rechazar una solicitud seleccionada por el gestor.
     * Elimina el vuelo del modelo y lo remueve de la tabla.
     */
    private void manejarRechazar() {
        int fila = vista.getTable().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Seleccione primero un vuelo de la tabla.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VueloProgramado vp = vista.getVueloSeleccionado();
        app.getVuelosSolicitados().remove(vp);
        vista.getModel().removeRow(fila);
        vista.getPanelBotones().setVisible(false);
    }

    /**
     * Maneja la lógica para aceptar una solicitud de vuelo:
     * Verifica la antelación mínima, busca terminales disponibles, solicita
     * terminal/controlador,
     * y realiza la aceptación si todo es válido.
     */
    private void manejarAceptar() {
        int fila = vista.getTable().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Seleccione primero un vuelo de la tabla.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VueloProgramado vp = vista.getVueloSeleccionado();

        LocalDate fechaVuelo = vp.getFrecuencia().getFechaInicio();
        int diasMinAntelacion = ConfiguracionPorDefecto
                .getConfiguracion()
                .getDiasAntelacionMinProgramacionVuelo();
        long diasEntre = ChronoUnit.DAYS.between(LocalDate.now(), fechaVuelo);
        if (diasEntre < diasMinAntelacion) {
            JOptionPane.showMessageDialog(
                    vista,
                    "No cumple los " + diasMinAntelacion +
                            " días mínimos de antelación.\nFaltan " + diasEntre + " días para la fecha de salida.",
                    "Error de antelación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Terminal> terminales = app.buscarTerminalesDisponibles(vp, 30);
        if (terminales.isEmpty()) {
            int rangoMinutos = ConfiguracionPorDefecto
                    .getConfiguracion()
                    .getRangoHorasDisponibleTerminal() * 60;
            terminales = app.buscarTerminalesDisponibles(vp, rangoMinutos);
            if (terminales.isEmpty()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "No hay terminales disponibles ni en ±30 min ni en el rango ampliado.",
                        "Sin terminales libres", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        SeleccionarTerminalControladorDialog dialog = new SeleccionarTerminalControladorDialog(
                frame,
                terminales,
                app.getControladores());
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);

        if (!dialog.isAceptado()) {
            return;
        }
        Terminal terminalSeleccionada = dialog.getTerminalSeleccionada();
        Controlador controladorSeleccionado = dialog.getControladorSeleccionado();

        boolean exito = app.aceptarVueloProgramadoConAsignacion(
                vp,
                terminalSeleccionada,
                controladorSeleccionado);
        if (exito) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Vuelo aceptado exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.getModel().removeRow(fila);
            vista.getPanelBotones().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(
                    vista,
                    "El vuelo no pudo ser aceptado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Clase interna para mostrar el diálogo con JComboBox
     */
    private static class SeleccionarTerminalControladorDialog extends JDialog {
        private final JComboBox<Terminal> cbTerminal;
        private final JComboBox<Controlador> cbControlador;
        private final JButton botonAceptar, botonCancelar;
        private boolean aceptado = false;

        /**
         * Constructor del diálogo.
         *
         * @param owner              Componente padre (ventana principal).
         * @param terminales         Lista de terminales disponibles.
         * @param todosControladores Lista de todos los controladores del sistema.
         */
        public SeleccionarTerminalControladorDialog(
                Window owner,
                List<Terminal> terminales,
                List<Controlador> todosControladores) {
            super(owner, "Seleccione Terminal y Controlador", ModalityType.APPLICATION_MODAL);
            this.setLayout(new BorderLayout(10, 10));

            JPanel center = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(4, 8, 4, 8);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            int y = 0;

            gbc.gridx = 0;
            gbc.gridy = y;
            gbc.gridwidth = 1;
            center.add(new JLabel("Terminal disponible:"), gbc);
            cbTerminal = new JComboBox<>(new DefaultComboBoxModel<>(terminales.toArray(new Terminal[0])));
            gbc.gridx = 1;
            center.add(cbTerminal, gbc);
            y++;

            gbc.gridx = 0;
            gbc.gridy = y;
            center.add(new JLabel("Controlador asociado:"), gbc);
            cbControlador = new JComboBox<>();
            gbc.gridx = 1;
            center.add(cbControlador, gbc);
            y++;

            this.add(center, BorderLayout.CENTER);

            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
            botonAceptar = new JButton("Aceptar");
            botonCancelar = new JButton("Cancelar");
            bottom.add(botonAceptar);
            bottom.add(botonCancelar);
            this.add(bottom, BorderLayout.SOUTH);

            cbTerminal.addActionListener(ev -> {
                Terminal t = (Terminal) cbTerminal.getSelectedItem();
                cbControlador.removeAllItems();
                for (Controlador c : todosControladores) {
                    if (c.getTerminal().equals(t)) {
                        cbControlador.addItem(c);
                    }
                }
                if (cbControlador.getItemCount() == 0) {
                    cbControlador.addItem(null);
                    cbControlador.setEnabled(false);
                    botonAceptar.setEnabled(false);
                } else {
                    cbControlador.setEnabled(true);
                    botonAceptar.setEnabled(true);
                }
            });

            if (!terminales.isEmpty()) {
                cbTerminal.setSelectedIndex(0);
                cbTerminal.getActionListeners()[0].actionPerformed(null);
            }

            botonAceptar.addActionListener(ev -> {
                if (cbControlador.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(
                            this,
                            "No hay controladores libres para esta terminal.",
                            "Atención", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                aceptado = true;
                this.dispose();
            });
            botonCancelar.addActionListener(ev -> {
                aceptado = false;
                this.dispose();
            });

            this.pack();
        }

        /**
         * Indica si se aceptó la selección.
         *
         * @return true si se aceptó, false si se canceló.
         */
        public boolean isAceptado() {
            return aceptado;
        }

        /**
         * Obtiene la terminal seleccionada.
         *
         * @return Terminal seleccionada por el usuario.
         */
        public Terminal getTerminalSeleccionada() {
            return (Terminal) cbTerminal.getSelectedItem();
        }

        /**
         * Obtiene el controlador seleccionado.
         *
         * @return Controlador seleccionado por el usuario.
         */
        public Controlador getControladorSeleccionado() {
            return (Controlador) cbControlador.getSelectedItem();
        }
    }
}
