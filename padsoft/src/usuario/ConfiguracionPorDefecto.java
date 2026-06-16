package usuario;

/**
 * Clase que representa la configuracion por defecto.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ConfiguracionPorDefecto {
    private Integer diasAntelacionMinProgramacionVuelo;
    private Integer rangoMinutosDisponibilidadZAFinger;
    private Integer rangoHorasDisponibleTerminal;
    private Integer minutosReservaTerminal;
    private static ConfiguracionPorDefecto Configuracion;

    /**
     * Constructor privado que inicializa la configuracion por defecto.
     */
    private ConfiguracionPorDefecto() {
        diasAntelacionMinProgramacionVuelo = 30;
        rangoMinutosDisponibilidadZAFinger = 30;
        rangoHorasDisponibleTerminal = 48;
        minutosReservaTerminal = 60;
    }

    /**
     * Metodo que devuelve la instancia unica de la configuracion.
     * 
     * @return La instancia de ConfiguracionPorDefecto.
     */
    public static ConfiguracionPorDefecto getConfiguracion() {
        if (Configuracion == null)
            Configuracion = new ConfiguracionPorDefecto();
        return Configuracion;
    }

    /**
     * Devuelve el numero de dias de antelacion minima para la programacion de
     * vuelo.
     * 
     * @return Los dias de antelacion minima.
     */
    public Integer getDiasAntelacionMinProgramacionVuelo() {
        return diasAntelacionMinProgramacionVuelo;
    }

    /**
     * Devuelve el rango en minutos de disponibilidad para ZAFinger.
     * 
     * @return El rango en minutos.
     */
    public Integer getRangoMinutosDisponibilidadZAFinger() {
        return rangoMinutosDisponibilidadZAFinger;
    }

    /**
     * Devuelve el rango en horas disponible para la terminal.
     * 
     * @return El rango en horas.
     */
    public Integer getRangoHorasDisponibleTerminal() {
        return rangoHorasDisponibleTerminal;
    }

    /**
     * Devuelve los minutos de reserva de terminal.
     * 
     * @return Los minutos de reserva.
     */
    public Integer getMinutosReservaTerminal() {
        return minutosReservaTerminal;
    }

    /**
     * Establece el numero de dias de antelacion minima para la programacion de
     * vuelo.
     * 
     * @param dias El numero de dias.
     */
    public void setDiasAntelacionMinProgramacionVuelo(Integer dias) {
        diasAntelacionMinProgramacionVuelo = dias;
    }

    /**
     * Establece el rango en minutos de disponibilidad para ZAFinger.
     * 
     * @param rangoZAFinger El rango en minutos.
     */
    public void setRangoMinutosDisponibilidadZAFinger(Integer rangoZAFinger) {
        rangoMinutosDisponibilidadZAFinger = rangoZAFinger;
    }

    /**
     * Establece el rango en horas disponible para la terminal.
     * 
     * @param rangoTerminal El rango en horas.
     */
    public void setRangoHorasDisponibleTerminal(Integer rangoTerminal) {
        rangoHorasDisponibleTerminal = rangoTerminal;
    }

    /**
     * Establece los minutos de reserva de terminal.
     * 
     * @param minutos Los minutos de reserva.
     */
    public void setMinutosReservaTerminal(Integer minutos) {
        minutosReservaTerminal = minutos;
    }
}
