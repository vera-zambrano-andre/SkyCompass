package vuelo;

/**
 * Clase que representa un vuelo compartido.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class VueloCompartido {

    private VueloPasajeros vueloPasajero;
    private double porcentaje;
    private String codigo;
    private boolean aceptado;

    /**
     * Constructor para crear un objeto de VueloCompartido.
     * 
     * @param vueloPasajero el vuelo de pasajeros principal asociado.
     * @param porcentaje    el porcentaje de ocupación asignado.
     * @param codigo        el código identificador del vuelo compartido.
     */
    public VueloCompartido(VueloPasajeros vueloPasajero, double porcentaje, String codigo) {
        this.vueloPasajero = vueloPasajero;
        this.porcentaje = porcentaje;
        this.codigo = codigo;
    }

    /**
     * Obtiene el vuelo de pasajeros asociado.
     * 
     * @return el vuelo de pasajeros asociado.
     */
    public VueloPasajeros getVueloPasajero() {
        return vueloPasajero;
    }

    /**
     * Establece el vuelo de pasajeros asociado.
     * 
     * @param vueloPasajero el nuevo vuelo de pasajeros asociado.
     */
    public void setVueloPasajero(VueloPasajeros vueloPasajero) {
        this.vueloPasajero = vueloPasajero;
    }

    /**
     * Obtiene el porcentaje de ocupacion asignado.
     * 
     * @return el porcentaje de ocupacion.
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * Establece el porcentaje de ocupacion asignado.
     * 
     * @param porcentaje el nuevo porcentaje de ocupación.
     */
    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * Obtiene el codigo identificador del vuelo compartido.
     * 
     * @return el codigo del vuelo compartido.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el codigo identificador del vuelo compartido.
     * 
     * @param codigo el nuevo codigo del vuelo compartido.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Verifica si el vuelo compartido ha sido aceptado.
     * 
     * @return true si el vuelo está aceptado, de lo contrario false.
     */
    public boolean isAceptado() {
        return aceptado;
    }

    /**
     * Establece si el vuelo compartido ha sido aceptado.
     * 
     * @param aceptado true si el vuelo debe marcarse como aceptado, de lo contrario
     *                 false.
     */
    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
}
