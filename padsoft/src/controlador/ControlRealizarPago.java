package controlador;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import sistema.SkyCompass;
import usuario.Operador;
import vista.RealizarPago;
import vista.VentanaPrincipal;
import java.util.List;
import factura.Factura;

/**
 * Clase que representa el controlador de realizar pago.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ControlRealizarPago implements ActionListener {
    private VentanaPrincipal frame;
    private SkyCompass app;
    private RealizarPago vista;

    /**
     * Constructor del controlador ControlRealizarPago.
     * Inicializa el controlador con la ventana principal, el sistema y la vista.
     *
     * @param frame Ventana principal de la aplicación.
     * @param app Instancia del sistema SkyCompass.
     * @param vista Vista correspondiente a la funcionalidad de realizar pagos.
     */
    public ControlRealizarPago(VentanaPrincipal frame, SkyCompass app, RealizarPago vista) {
        this.frame = frame;
        this.vista = vista;
        this.app = app;

    }

    /**
     * Método que maneja los eventos de acción de la vista.
     * Permite volver a la pantalla anterior o ejecutar el proceso de pago para las facturas seleccionadas,
     * validando tanto la tarjeta como la selección de facturas.
     *
     * @param e Evento de acción generado por la vista.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("← Volver")) {
            frame.changeVisiblePanel(VentanaPrincipal.PRINCIPALOPERADOR);
            return;
        }

        List<Factura> seleccionadas = vista.getFacturasSeleccionadas();
        String numTarjeta = vista.getNumTarjeta();

        if (vista.getNumTarjeta().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No se ha introducido ninguna tarjeta.");
            return;
        }

        if (seleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay facturas seleccionadas.");
            return;
        }

        if (!TeleChargeAndPaySystem.isValidCardNumber(numTarjeta)) {
            JOptionPane.showMessageDialog(vista, "Número de tarjeta inválido (Debe tener 16 dígitos válidos).");
            return;
        }

        for (Factura factura : seleccionadas) {
            try {
                app.pagarFactura(numTarjeta, factura);
            } catch (InvalidCardNumberException ex) {
                JOptionPane.showMessageDialog(vista, "El número de la tarjeta no es válido.");
                return;
            } catch (FailedInternetConnectionException ex) {
                JOptionPane.showMessageDialog(vista, "Error de conexión al procesar el pago.");
                return;
            } catch (OrderRejectedException ex) {
                JOptionPane.showMessageDialog(vista, "El banco ha rechazado el cobro.");
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error desconocido al pagar la factura: " + ex.getMessage());
                return;
            }
        }

        JOptionPane.showMessageDialog(vista, "Facturas pagadas con éxito.");

        this.update();
    }

    /**
     * Actualiza la lista de facturas en la vista según el operador logeado.
     * Solo se muestran facturas pendientes asociadas a la aerolínea del operador actual.
     */
    public void update() {
        List<Factura> facturas = app.getFacturasPendientes();

        if (!facturas.isEmpty()) {

            List<Factura> facturasFiltradas = facturas.stream()
                    .filter(p -> {
                        Operador operador = (Operador) app.getUsuarioLogeado();
                        return operador.getAerolinea().equals(p.getAerolinea());
                    })
                    .toList();

            this.vista.setFacturas(facturasFiltradas);
        } else {
            this.vista.setFacturas(List.of());
        }
    }

    /**
     * Intenta abrir un archivo PDF en el visor predeterminado del sistema operativo.
     *
     * @param archivoPDF Archivo PDF a abrir.
     */
    public void abrirFacturaPDF(File archivoPDF) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(archivoPDF);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo PDF.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Desktop no soportado en este sistema.");
        }
    }
}
