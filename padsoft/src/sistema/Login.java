package sistema;

/**
 * Enum que representa el estado en el que se encuentra el login.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public enum Login {
    /**
     * El login se ha realizado correctamente.
     */
    EXITO,
    /**
     * El login ha fallado por un error del operador.
     */
    FALLO_OPERADOR,
    /**
     * El login ha fallado por un error del controlador.
     */
    FALLO_CONTROLADOR,
    /**
     * El login ha fallado porque el usuario esta bloqueado.
     */
    USUARIO_BLOQUEADO,
    /**
     * El login ha fallado porque el usuario no existe.
     */
    NO_EXISTE
}
