package usuario;

import java.util.ArrayList;
import java.util.List;

import aeropuerto.Aerolinea;

import java.io.Serializable;

/**
 * Clase que representa un usuario.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
abstract public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String dni;
    private String nombre;
    protected String password;
    private List<Notificacion> notificaciones = new ArrayList<>();

    /**
     * Constructor de la clase Usuario.
     * 
     * @param dni      El documento de identidad del usuario.
     * @param nombre   El nombre del usuario.
     * @param password La contrasena del usuario.
     */
    public Usuario(String dni, String nombre, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.password = password;
    }

    /**
     * Permite asignar una nueva contraseña al usuario.
     * 
     * @param password La nueva contraseña a establecer.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve la lista de notificaciones del usuario.
     * 
     * @return Una lista de objetos Notificacion.
     */
    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    /**
     * Agrega una notificacion a la lista de notificaciones del usuario.
     * 
     * @param notificacion La notificacion a agregar.
     */
    public void addNotificacion(Notificacion notificacion) {
        this.notificaciones.add(notificacion);
    }

    /**
     * Devuelve el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve el DNI del usuario.
     * 
     * @return El DNI del usuario.
     */
    public String getDni() {
        return this.dni;
    }

    /**
     * Compara dos objetos y devuelve true si son iguales.
     * 
     * Dos objetos de tipo Usuario son iguales si tienen el mismo DNI.
     * 
     * @param o El objeto a comparar con este objeto.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Usuario))
            return false;

        Usuario u = (Usuario) o;

        return this.getDni().equals(u.getDni());
    }

    /**
     * Calcula el código hash para el usuario basado en su DNI.
     *
     * @return El código hash del usuario.
     */

    public int hashCode() {
        return this.getDni().hashCode();
    }

    /**
     * Devuelve la contrasena del usuario.
     * 
     * @return La contrasena del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve la aerolinea asociada al usuario.
     * 
     * @return La aerolinea del usuario.
     */

    public Aerolinea getAerolinea() {
        return null;
    }

    /**
     * Devuelve una representación en cadena del usuario.
     * 
     * @return Una cadena que incluye el nombre y el DNI del usuario.
     */

    @Override
    public String toString() {
        return "Nombre: " + this.getNombre() + " DNI: " + this.getDni();
    }
}
