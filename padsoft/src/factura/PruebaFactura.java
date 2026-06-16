package factura;

import es.uam.eps.padsof.invoices.InvoiceSystem;
import es.uam.eps.padsof.invoices.NonExistentFileException;
import es.uam.eps.padsof.invoices.UnsupportedImageTypeException;
import es.uam.eps.padsof.invoices.IResourceUsageInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import aeropuerto.Aerolinea;

/**
 * Clase que prueba la creacion de una factura.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class PruebaFactura {
    public static void main(String[] args)
            throws NonExistentFileException, UnsupportedImageTypeException {

        List<IResourceUsageInfo> recursos = new ArrayList<>();
        recursos.add(new UsoCoste("Finger F22", 10.35, "50 minutos", 10.35));
        recursos.add(new UsoCoste("Hangar H02", 150.30, "2 horas 30 minutos", 450.90));
        recursos.add(new UsoCoste("Finger F24", 12.35, "50 minutos", 12.35));

        Factura factura = new Factura(new Aerolinea("PruebaFactura", "PF1"), LocalDate.of(2022, 9, 1), 0, recursos, 0);

        InvoiceSystem.createInvoice(factura, "./tmp/");

        System.out.println("Factura generada con éxito en la carpeta ./tmp/");
    }
}
