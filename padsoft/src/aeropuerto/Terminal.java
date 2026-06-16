package aeropuerto;

import java.io.Serializable;

/**
 * Clase que representa una terminal.
 * 
 * Esta clase abstracta define los atributos basicos de una terminal, tales como
 * el codigo, el numero maximo de plazas, las plazas actuales y el horario de
 * disponibilidad.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public abstract class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private int maxPlazas;
    private int plazas;
    private RangoTiempo horarioDisponibilidad;

    /**
     * Constructor de la clase Terminal.
     * 
     * @param codigo    Codigo identificador de la terminal.
     * @param Maxplazas Numero maximo de plazas.
     * @param horarioDisponibilidad Horario de disponibilidad.
     */
    public Terminal(String codigo, int Maxplazas, RangoTiempo horarioDisponibilidad) {
        this.maxPlazas = Maxplazas;
        this.codigo = codigo;
        this.plazas = 0;
        this.horarioDisponibilidad = horarioDisponibilidad;
    }

    /**
     * Devuelve el horario de disponibilidad de la terminal.
     * 
     * @return Horario de disponibilidad.
     */
    public RangoTiempo getHorarioDisponibilidad() {
        return this.horarioDisponibilidad;
    }

    /**
     * Devuelve el codigo de la terminal.
     * 
     * @return Codigo de la terminal.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Devuelve el numero actual de plazas ocupadas en la terminal.
     * 
     * @return Numero de plazas actuales.
     */
    public int getPlazasActuales() {
        return this.plazas;
    }

    /**
     * Devuelve el numero maximo de plazas de la terminal.
     * 
     * @return Numero maximo de plazas.
     */
    public int getMaxPlazas() {
        return this.maxPlazas;
    }

    /**
     * Establece el numero maximo de plazas de la terminal.
     * 
     * @param maxPlazas Nuevo valor para el numero maximo de plazas.
     */
    public void setMaxPlazas(int maxPlazas) {
        this.maxPlazas = maxPlazas;
    }

    /**
     * Establece el numero de plazas actuales ocupadas en la terminal.
     * 
     * @param plazas Nuevo valor para el numero de plazas actuales.
     */
    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    /**
     * Compara dos objetos y devuelve true si son iguales.
     * 
     * En este caso, dos objetos de tipo Terminal son iguales si tienen el mismo
     * codigo.
     * 
     * @param o El objeto a comparar con este objeto.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Terminal) || o == null)
            return false;
        Terminal t = (Terminal) o;
        return this.codigo.equals(t.getCodigo());
    }

    /**
     * Calcula el codigo de hash de la terminal.
     * 
     * El codigo de hash se calcula a partir del codigo de la terminal.
     * 
     * @return Codigo de hash de la terminal.
     */
    public int hashCode() {
        return this.codigo.hashCode();
    }

    /**
     * Convierte la informacion de la terminal en un String.
     * 
     * @return String con la informacion de la terminal.
     */
    public String toString() {
        return "Terminal " + this.codigo + " " + this.maxPlazas + " " + this.plazas;
    }
}
