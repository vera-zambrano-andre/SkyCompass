package usuario;

import aeropuerto.*;

/**
 * Clase que representa un operador.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Operador extends Usuario {
    private boolean logeado;
    private Aerolinea aerolinea;
    private Integer fallos;

    /**
     * Constructor de la clase Operador.
     * 
     * @param dni El documento de identidad del operador.
     * @param nombre El nombre del operador.
     * @param aerolinea La aerolinea a la que pertenece el operador.
     */
    public Operador(String dni, String nombre, Aerolinea aerolinea) {
        super(dni, nombre, "");
        this.aerolinea = aerolinea;
        fallos = 0;
    }

    /**
     * Devuelve el estado de logeo del operador.
     * 
     * @return True si el operador se ha logeado alguna vez, false en caso contrario.
     */
    public boolean getLogeado() {
        return this.logeado;
    }

    /**
     * Establece el estado de logeo del operador.
     * 
     * @param logeado True si el operador se ha logeado alguna vez, false en caso contrario.
     */
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    /**
     * Devuelve la aerolinea asociada al operador.
     * 
     * @return La aerolinea del operador.
     */
    public Aerolinea getAerolinea() {
        return this.aerolinea;
    }

    /**
     * Devuelve el número de fallos del operador al iniciar sesión.
     * 
     * @return El número de fallos.
     */

    public Integer getFallos() {
        return fallos;
    }
    
    /**
     * Establece el número de fallos del operador al iniciar sesión.
     * 
     * @param fallos El nuevo número de fallos.
     */

    public void setFallo(Integer fallos) {
        this.fallos = fallos;
    }

    /**
     * Permite cambiar la contrasena del operador si la contrasena actual esta vacia.
     * 
     * @param password La nueva contrasena a asignar.
     */
    public void setPassword(String password) {
        if (this.getPassword().equals("")) {
            super.setPassword(password);
        } else {
            throw new IllegalStateException("La contraseña ya ha sido establecida y no puede cambiarse.");
        }
    }

    /**
     * Permite resetear la contraseña del operador a su valor inicial, que es una cadena vacia.
     */
    public void resetearPassword() {
        super.setPassword("");
    }
}
