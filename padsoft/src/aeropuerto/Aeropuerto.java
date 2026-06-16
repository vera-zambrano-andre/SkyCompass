package aeropuerto;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que representa un aeropuerto.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Aeropuerto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String codigo;
    private String ciudadMasCercana;
    private String pais;
    private int distanciaCiudadKm;
    private Direccion direccion;
    private int diferenciaHoraria;
    private List<Temporada> temporadas;

    /**
     * Constructor de la clase Aeropuerto.
     * 
     * @param nombre            El nombre del aeropuerto.
     * @param codigo            El codigo del aeropuerto.
     * @param ciudadMasCercana  La ciudad mas cercana al aeropuerto.
     * @param pais              El pais en el que se encuentra el aeropuerto.
     * @param distanciaCiudadKm La distancia en km entre el aeropuerto y la ciudad
     *                          mas cercana.
     * @param direccion         La direccion del aeropuerto.
     * @param diferenciaHoraria La diferencia horaria en horas del aeropuerto con
     *                          respecto al UTC.
     * @param temporadas        La lista de temporadas en las que se encuentra el
     *                          aeropuerto.
     */

    public Aeropuerto(String nombre, String codigo, String ciudadMasCercana, String pais, int distanciaCiudadKm,
            Direccion direccion, int diferenciaHoraria, List<Temporada> temporadas) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.ciudadMasCercana = ciudadMasCercana;
        this.pais = pais;
        this.distanciaCiudadKm = distanciaCiudadKm;
        this.direccion = direccion;
        this.diferenciaHoraria = diferenciaHoraria;
        this.temporadas = temporadas;
    }

    /**
     * Devuelve el nombre del aeropuerto.
     * 
     * @return El nombre del aeropuerto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del aeropuerto.
     * 
     * @param nombre El nuevo nombre del aeropuerto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el codigo del aeropuerto.
     * 
     * @return El codigo del aeropuerto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el codigo del aeropuerto.
     * 
     * @param codigo El nuevo codigo del aeropuerto.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Devuelve la ciudad más cercana al aeropuerto.
     * 
     * @return La ciudad más cercana al aeropuerto.
     */
    public String getCiudadMasCercana() {
        return ciudadMasCercana;
    }

    /**
     * Establece la ciudad más cercana al aeropuerto.
     * 
     * @param ciudadMasCercana La nueva ciudad más cercana al aeropuerto.
     */
    public void setCiudadMasCercana(String ciudadMasCercana) {
        this.ciudadMasCercana = ciudadMasCercana;
    }

    /**
     * Devuelve el pais en el que se encuentra el aeropuerto.
     * 
     * @return El pais en el que se encuentra el aeropuerto.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el pais en el que se encuentra el aeropuerto.
     * 
     * @param pais El nuevo pais en el que se encuentra el aeropuerto.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Devuelve la distancia en kilómetros a la ciudad más cercana.
     * 
     * @return La distancia en kilómetros a la ciudad más cercana.
     */

    public int getDistanciaCiudadKm() {
        return distanciaCiudadKm;
    }

    /**
     * Establece la distancia en kilómetros a la ciudad más cercana.
     * 
     * @param distanciaCiudadKm La nueva distancia en kilómetros a la ciudad más
     *                          cercana.
     */

    public void setDistanciaCiudadKm(int distanciaCiudadKm) {
        this.distanciaCiudadKm = distanciaCiudadKm;
    }

    /**
     * Devuelve la dirección del aeropuerto.
     * 
     * @return La dirección del aeropuerto.
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del aeropuerto.
     * 
     * @param direccion La nueva dirección del aeropuerto.
     */

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve la diferencia horaria del aeropuerto respecto al tiempo UTC.
     * 
     * @return La diferencia horaria en horas.
     */

    public int getDiferenciaHoraria() {
        return diferenciaHoraria;
    }

    /**
     * Establece la diferencia horaria del aeropuerto respecto al tiempo UTC.
     * 
     * @param diferenciaHoraria La nueva diferencia horaria en horas.
     */
    public void setDiferenciaHoraria(int diferenciaHoraria) {
        this.diferenciaHoraria = diferenciaHoraria;
    }

    /**
     * Devuelve la lista de temporadas en las que se encuentra el aeropuerto.
     * 
     * @return La lista de temporadas.
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * Establece la lista de temporadas en las que se encuentra el aeropuerto.
     * 
     * @param temporadas La lista de temporadas.
     */
    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    /**
     * Compara dos objetos Aeropuerto y devuelve true si son iguales,
     * es decir, si tienen el mismo codigo, nombre y pais.
     * 
     * @param o El objeto a comparar.
     * 
     * @return true si los objetos son iguales, false en caso contrario.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Aeropuerto) || o == null)
            return false;
        Aeropuerto a = (Aeropuerto) o;
        return this.codigo.equals(a.getCodigo()) && this.nombre.equals(a.getNombre()) && this.pais.equals(a.getPais());
    }

    /**
     * Devuelve un valor hash para el objeto Aeropuerto basado en su código, nombre
     * y país.
     * 
     * @return Un valor hash para el objeto Aeropuerto.
     */
    public int hashCode() {
        return this.codigo.hashCode() + this.pais.hashCode() + this.nombre.hashCode();
    }

    /**
     * Devuelve una representacion en forma de string del aeropuerto que contiene:
     * - nombre
     * - codigo
     * - ciudadMasCercana
     * - pais
     * - distanciaCiudadKm
     * - direccion
     * - diferenciaHoraria
     * - temporadas
     *
     * @return La representacion en forma de string del aeropuerto.
     */
    @Override
    public String toString() {
        return "Aeropuerto{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", ciudadMasCercana='" + ciudadMasCercana + '\'' +
                ", pais='" + pais + '\'' +
                ", distanciaCiudadKm=" + distanciaCiudadKm +
                ", direccion=" + direccion +
                ", diferenciaHoraria=" + diferenciaHoraria +
                ", temporadas=" + temporadas +
                '}';
    }
}
