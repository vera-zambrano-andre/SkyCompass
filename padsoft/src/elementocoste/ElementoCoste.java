package elementocoste;

import java.time.Duration;
import java.time.LocalDateTime;
import vuelo.Localizacion;
import java.util.*;

/**
 * Clase que representa un elemento con coste.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public abstract class ElementoCoste extends Localizacion {

    private Random RAND = new Random();
    private double costeHora;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    /**
     * Constructor de la clase ElementoCoste.
     * 
     * @param costeHora El coste por hora de este elemento de coste.
     */

    public ElementoCoste(double costeHora) {
        this.costeHora = costeHora;
    }

    /**
     * Obtiene la hora de inicio del uso de este elemento de coste.
     * 
     * @return La hora de inicio del uso.
     */
    public LocalDateTime getHoraInicio() {
        return this.horaInicio;
    }

    /**
     * Obtiene la hora de fin del uso de este elemento de coste.
     * 
     * @return La hora de fin del uso.
     */
    public LocalDateTime getHoraFin() {
        return this.horaFin;
    }

    /**
     * Inicia el uso de este elemento de coste.
     * 
     * Guarda la hora actual como hora de inicio.
     */
    public void iniciarUso() {
        this.horaInicio = LocalDateTime.now();
    }

    /**
     * Finaliza el uso de este elemento de coste.
     * 
     * Guarda la hora actual como hora de fin.
     */
    public void finalizarUso() {
        this.horaFin = LocalDateTime.now().plusMinutes(RAND.nextInt(11));
    }

    /**
     * Calcula el número de minutos transcurridos desde el inicio hasta el fin del
     * uso de este elemento de coste.
     * 
     * @return El número de minutos transcurridos. Si el uso no ha sido iniciado o
     *         finalizado, devuelve 0.
     */
    public long calcularMinutosUso() {
        if (this.horaInicio == null || this.horaFin == null)
            return 0;
        return Duration.between(this.horaInicio.toLocalTime(), this.horaFin.toLocalTime()).toMinutes() + 1;
    }

    /**
     * Calcula el coste total del uso de este elemento de coste.
     * 
     * @return El coste total del uso.
     */
    public double getCosteTotal() {
        return (calcularMinutosUso() / 60.0) * this.costeHora;
    }

    /**
     * Devuelve el coste por hora de este elemento de coste.
     * 
     * @return El coste por hora.
     */
    public double getCosteHora() {
        return this.costeHora;
    }

}
