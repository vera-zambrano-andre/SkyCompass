package vuelo;

/**
 * Interfaz que implementa métodos relacionados con los procesos de un vuelo.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public interface IProcesosVuelo {

    /**
     * Inicia el proceso de embarque de un vuelo.
     * 
     * @param vuelo El vuelo que se va a iniciar.
     * @return true si el proceso de embarque se ha iniciado con exito, false en
     *         caso contrario.
     */
    public boolean iniciarEmbarqueVuelo(Vuelo vuelo);

    /**
     * Finaliza el proceso de embarque de un vuelo.
     * 
     * @param vuelo El vuelo que se va a finalizar.
     * @return true si el proceso de embarque se ha finalizado con exito, false en
     *         caso contrario.
     */
    public boolean finalizarEmbarqueVuelo(Vuelo vuelo);

    /**
     * Inicia el proceso de desembarque de un vuelo.
     * 
     * @param vuelo El vuelo que se va a iniciar.
     * @return true si el proceso de desembarque se ha iniciado con exito, false
     *         en caso contrario.
     */
    public boolean iniciarDesembarqueVuelo(Vuelo vuelo);

    /**
     * Finaliza el proceso de desembarque de un vuelo.
     * 
     * @param vuelo El vuelo que se va a finalizar.
     * @return true si el proceso de desembarque se ha finalizado con exito, false
     *         en caso contrario.
     */
    public boolean finalizarDesembarqueVuelo(Vuelo vuelo);
}
