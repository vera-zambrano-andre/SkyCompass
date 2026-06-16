package factura;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

/**
 * Clase que prueba el pago con tarjeta.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class CobroTarjeta {
    public static void main(String[] args) {
        try {
            String tarjetaValida = "1234567890123456";
            String tarjetaInvalida = "123456789012345";

            System.out.println("¿Es válida la tarjeta 1234567890123456? " 
                               + TeleChargeAndPaySystem.isValidCardNumber(tarjetaValida));
            System.out.println("¿Es válida la tarjeta 123456789012345? " 
                               + TeleChargeAndPaySystem.isValidCardNumber(tarjetaInvalida));

            TeleChargeAndPaySystem.charge(tarjetaValida, "Pago Factura INV000213", 350.0, true);
            System.out.println("Cobro realizado con éxito.");

        } catch (InvalidCardNumberException e) {
            System.err.println("Error: número de tarjeta inválido. " + e.getMessage());
        } catch (FailedInternetConnectionException e) {
            System.err.println("Error: fallo en la conexión a internet. " + e.getMessage());
        } catch (OrderRejectedException e) {
            System.err.println("Error: la orden fue rechazada. " + e.getMessage());
        }
    }
}
