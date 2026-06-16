package factura;

import es.uam.eps.padsof.invoices.IInvoiceInfo;
import es.uam.eps.padsof.invoices.IResourceUsageInfo;
import es.uam.eps.padsof.invoices.InvoiceSystem;

import es.uam.eps.padsof.invoices.NonExistentFileException;
import es.uam.eps.padsof.invoices.UnsupportedImageTypeException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import aeropuerto.Aerolinea;

/**
 * Clase que representa una factura.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Factura implements IInvoiceInfo, Serializable {

    private static final long serialVersionUID = 1L;
    private Aerolinea aerolinea;
    private LocalDate fecha;
    private static double precioBase = 100;
    private static double recargoMercancias = 50;
    private static double recargoPasajeros = 20;
    private double precioBasePropio;
    private double recargo;
    private List<IResourceUsageInfo> listaCostesRecurso;
    private boolean pagada = false;
    private double precioFinal; // Precio final inicializado con el precio total sin descuento, no será
                                // modificado hasta que se apliquen descuentos
    private double precioRecursosDescontado;
    private int numVuelos;

    /**
     * Constructor de la clase Factura.
     * 
     * @param aerolinea          aerolinea a la que le pertenece la factura
     * @param fecha              fecha en la que se emite la factura
     * @param recargo            recargo de la factura
     * @param listaCostesRecurso lista de costes de recursos
     * @param numVuelos          número de vuelos
     */
    public Factura(Aerolinea aerolinea, LocalDate fecha, double recargo, List<IResourceUsageInfo> listaCostesRecurso,
            int numVuelos) {
        this.aerolinea = aerolinea;
        this.fecha = fecha;
        this.recargo = recargo;
        this.listaCostesRecurso = listaCostesRecurso;
        this.numVuelos = numVuelos;

        this.precioBasePropio = precioBase;

        precioFinal = getPrecioTotalSinDecuento();
    }

    /**
     * Genera un archivo PDF de la factura.
     * 
     * 
     * @return true si el archivo PDF se crea correctamente, false en caso
     *         contrario.
     */

    public boolean generarFacturaPDF() {
        try {
            InvoiceSystem.createInvoice(this, "./tmp/");
        } catch (NonExistentFileException e) {
            System.err.println("Error creating invoice: " + e.getMessage());
            return false;
        } catch (UnsupportedImageTypeException e) {
            System.err.println("Error creating invoice: unsupported image type");
            return false;
        }

        return true;
    }

    /**
     * Devuelve el nombre de la compañía.
     * 
     * @return El nombre de la compañía.
     */

    public String getCompanyName() {
        return "SkyCompass";
    }

    /**
     * Devuelve la ruta del logotipo de la compañía.
     * 
     * @return La ruta del logotipo de la compañía.
     */
    public String getCompanyLogo() {
        return "./resources/SkyCompass.jpg";
    }

    /**
     * Devuelve la aerolínea asociada a la factura.
     * 
     * @return El nombre de la aerolínea.
     */

    public String getAirline() {
        return this.aerolinea.getNombre();
    }

    /**
     * Devuelve la aerolínea asociada a la factura.
     * 
     * @return La aerolínea asociada a la factura.
     */
    public Aerolinea getAerolinea() {
        return this.aerolinea;
    }

    /**
     * Devuelve el identificador de la factura.
     * 
     * @return El identificador de la factura.
     */
    @Override
    public String getInvoiceIdentifier() {
        return "INV" + fecha.toString().replace("-", "") + aerolinea.getNombre();
    }

    /**
     * Devuelve la fecha en que se emitió la factura.
     * 
     * @return La fecha de emisión de la factura.
     */
    public String getInvoiceDate() {
        return this.fecha.toString();
    }

    /**
     * Devuelve el precio base de la factura.
     * 
     * @return El precio base de la factura.
     */

    public double getBasePrice() {
        return precioBasePropio;
    }

    /**
     * Devuelve el recargo aplicado a la factura.
     * 
     * @return El recargo aplicado.
     */
    public double getSurcharge() {
        return this.recargo;
    }

    /**
     * Devuelve la lista de costes de recursos asociados a la factura.
     * 
     * @return La lista de costes de recursos.
     */
    public List<IResourceUsageInfo> getResourcePrices() {
        return this.listaCostesRecurso;
    }

    /**
     * Calcula el precio total de la factura.
     * 
     * Suma el precio base, el recargo y todos los costes de recursos.
     * 
     * @return El precio total de la factura.
     */
    public double getPrice() {
        return precioFinal;
    }

    /**
     * Calcula el precio total de la factura sin descuentos.
     * 
     * Suma el precio base, el recargo y todos los costes de recursos.
     * 
     * @return El precio total de la factura sin descuentos.
     */
    public double getPrecioTotalSinDecuento() {
        double sumaRecursos = 0;
        for (IResourceUsageInfo uso : listaCostesRecurso) {
            sumaRecursos += uso.getPrice();
        }
        return this.precioBasePropio + this.recargo + sumaRecursos;
    }

    /**
     * Calcula el precio total de los recursos sin aplicar descuentos.
     *
     * @return La suma de los precios de todos los recursos asociados a la factura.
     */

    public double getPrecioRecursosSinDescuento() {
        double sumaRecursos = 0;
        for (IResourceUsageInfo uso : listaCostesRecurso) {
            sumaRecursos += uso.getPrice();
        }
        return sumaRecursos;
    }

    /**
     * Indica si la factura ya ha sido pagada.
     * 
     * @return true si la factura ha sido pagada, false en caso contrario.
     */
    public boolean isPagada() {
        return this.pagada;
    }

    /**
     * Establece si la factura ha sido pagada o no.
     * 
     * @param pagada true si la factura ha sido pagada, false en caso contrario.
     */
    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    /**
     * Aplica una lista de descuentos a la factura, ajustando los precios
     * de los recursos y el precio final en base a los descuentos totales y
     * específicos de recursos.
     *
     * @param descuentos lista de descuentos a aplicar a la factura.
     */
    public void aplicarDescuentos(List<Descuento> descuentos) {
        double descuentoTotal = 0;
        double descuentoRecursos = 0;

        for (Descuento descuento : descuentos) {
            descuentoTotal += descuento.calcularDescuentoTotal(this);
            descuentoRecursos += descuento.calcularDescuentoRecursos(this);
        }

        this.precioRecursosDescontado = getPrecioRecursosSinDescuento() - descuentoRecursos;

        if (this.precioRecursosDescontado < 0) {
            this.precioRecursosDescontado = 0;
        }

        this.precioFinal = this.precioBasePropio + this.recargo + this.precioRecursosDescontado - descuentoTotal;

        if (this.precioFinal < 0) {
            this.precioFinal = 0;
        }
    }

    /**
     * Obtiene el número de vuelos relacionados con esta factura.
     *
     * @return El número de vuelos relacionados con esta factura.
     */
    public int getNumeroVuelos() {
        return this.numVuelos;
    }

    // Métodos para poder configurar el precio base

    /**
     * Devuelve el precio base de la factura.
     * 
     * @return El precio base de la factura.
     */
    public static double getPrecioBase() {
        return precioBase;
    }

    /**
     * Establece un nuevo precio base para la factura.
     * 
     * @param nuevoPrecio El nuevo precio base a asignar.
     */

    public static void setPrecioBase(double nuevoPrecio) {
        precioBase = nuevoPrecio;
    }

    /**
     * Devuelve el recargo por mercancías actual.
     * 
     * @return El recargo por mercancías actual.
     */
    public static double getRecargoMercancias() {
        return recargoMercancias;
    }

    /**
     * Establece un nuevo recargo por mercancías para la factura.
     * 
     * @param nuevoRecargo El nuevo recargo por mercancías a asignar.
     */
    public static void setRecargoMercancias(double nuevoRecargo) {
        recargoMercancias = nuevoRecargo;
    }

    /**
     * Devuelve el recargo por pasajeros actual.
     * 
     * @return El recargo por pasajeros actual.
     */
    public static double getRecargoPasajeros() {
        return recargoPasajeros;
    }

    /**
     * Establece un nuevo recargo por pasajeros para la factura.
     * 
     * @param nuevoRecargo El nuevo recargo por pasajeros a asignar.
     */

    public static void setRecargoPasajeros(double nuevoRecargo) {
        recargoPasajeros = nuevoRecargo;
    }

}
