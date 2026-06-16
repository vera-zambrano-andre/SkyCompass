package usuario;

import aeropuerto.Terminal;
import java.util.*;

/**
 * Clase que representa un controlador.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Controlador extends Usuario {
    private Terminal terminal;
    private List<Notificacion> notificacionEspecial;

    /**
     * Constructor de la clase Controlador.
     * 
     * @param dni            El documento de identidad del controlador.
     * @param nombre         El nombre del controlador.
     * @param passwordGestor La contrasena del gestor.
     * @param terminal       La terminal asociada al controlador.
     */
    public Controlador(String dni, String nombre, String passwordGestor, Terminal terminal) {
        super(dni, nombre, passwordGestor);
        this.terminal = terminal;
    }

    /**
     * Devuelve la lista de notificaciones especiales.
     * 
     * @return Una lista de notificaciones especiales.
     */
    public List<Notificacion> getNotificacionEspecial() {
        return notificacionEspecial;
    }

    /**
     * Agrega una notificacion especial a la lista.
     * 
     * @param notificacion La notificacion especial a agregar.
     */
    public void addNotificacionEspecial(Notificacion notificacion) {
        this.notificacionEspecial.add(notificacion);
    }

    /**
     * Devuelve la terminal asociada al controlador.
     * 
     * @return La terminal del controlador.
     */
    public Terminal getTerminal() {
        return terminal;
    }

    /**
     * Asigna una nueva terminal al controlador.
     * 
     * @param terminal La nueva terminal a asignar.
     */
    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
