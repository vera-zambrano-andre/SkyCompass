package estadística;

import vuelo.*;
import java.util.*;

import aeropuerto.PuertaEmbarque;
import elementocoste.Finger;
import elementocoste.Hangar;
import elementocoste.ZonaAparcamiento;

import java.time.*;
import java.time.format.DateTimeFormatter;
import sistema.*;

/**
 * Clase que representa una estadistica del sistema de vuelos.
 * Proporciona metodos para calcular estadisticas sobre el uso de recursos y
 * retrasos.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class Estadistica {
    private SkyCompass app;
    private static Estadistica ESTADISTICA;

    /**
     * Constructor privado para implementar el patron Singleton.
     */
    private Estadistica() {
        this.app = SkyCompass.getInstance();
    }

    /**
     * Devuelve la instancia unica de Estadistica.
     * 
     * @return instancia de Estadistica.
     */
    public static Estadistica getEstadistica() {
        if (ESTADISTICA == null)
            ESTADISTICA = new Estadistica();
        return ESTADISTICA;
    }

    /**
     * Calcula el uso medio diario de cada hangar en horas.
     * 
     * @return Un mapa que asocia cada hangar con su uso medio diario
     *         en horas.
     */
    public Map<Hangar, Double> usoMedioHangarPorDia() {
        Map<Hangar, List<Vuelo>> vuelosPorHangar = new HashMap<>();
        for (Vuelo v : app.getVuelos()) {
            Hangar h = v.getHangar();
            if (vuelosPorHangar.containsKey(h)) {
                vuelosPorHangar.get(h).add(v);
            } else {
                List<Vuelo> lista = new ArrayList<>();
                lista.add(v);
                vuelosPorHangar.put(h, lista);
            }
        }

        Map<Hangar, Double> resultado = new HashMap<>();
        for (Hangar h : vuelosPorHangar.keySet()) {
            List<Vuelo> lista = vuelosPorHangar.get(h);
            long minutosTotal = 0;
            LocalDate inicio = null, fin = null;
            for (Vuelo v : lista) {
                LocalDate f = v.getVueloProgramado().getFrecuencia().getFechaInicio();
                if (inicio == null || f.isBefore(inicio))
                    inicio = f;
                if (fin == null || f.isAfter(fin))
                    fin = f;
                minutosTotal += v.getMinutosHangar();
            }
            int dias = (inicio != null && fin != null)
                    ? Period.between(inicio, fin).getDays() + 1
                    : 1;
            double horasMedias = (minutosTotal / 60.0) / dias;
            resultado.put(h, horasMedias);
        }
        return resultado;
    }

    /**
     * Calcula el uso medio diario de cada zona de aparcamiento en horas.
     * 
     * @return Un mapa que asocia cada zona de aparcamiento con su uso medio
     *         diario en horas.
     */

    public Map<ZonaAparcamiento, Double> usoMedioZonaPorDia() {
        Map<ZonaAparcamiento, List<Vuelo>> vuelosPorZona = new HashMap<>();
        for (Vuelo v : app.getVuelos()) {
            ZonaAparcamiento z = v.getAparcamiento();
            if (vuelosPorZona.containsKey(z)) {
                vuelosPorZona.get(z).add(v);
            } else {
                List<Vuelo> lista = new ArrayList<>();
                lista.add(v);
                vuelosPorZona.put(z, lista);
            }
        }

        Map<ZonaAparcamiento, Double> resultado = new HashMap<>();
        for (ZonaAparcamiento z : vuelosPorZona.keySet()) {
            List<Vuelo> lista = vuelosPorZona.get(z);
            long minutosTotal = 0;
            LocalDate inicio = null, fin = null;
            for (Vuelo v : lista) {
                LocalDate f = v.getVueloProgramado().getFrecuencia().getFechaInicio();
                if (inicio == null || f.isBefore(inicio))
                    inicio = f;
                if (fin == null || f.isAfter(fin))
                    fin = f;
                minutosTotal += v.getMinutosZonaAparcamiento();
            }
            int dias = (inicio != null && fin != null)
                    ? Period.between(inicio, fin).getDays() + 1
                    : 1;
            double horasMedias = (minutosTotal / 60.0) / dias;
            resultado.put(z, horasMedias);
        }
        return resultado;
    }

    /**
     * Calcula el uso medio diario de cada puerta de embarque en horas.
     * 
     * @return Un mapa que asocia cada puerta de embarque con su uso medio
     *         diario en horas.
     */
    public Map<PuertaEmbarque, Double> usoMedioPuertaPorDia() {
        Map<PuertaEmbarque, List<Vuelo>> vuelosPorPuerta = new HashMap<>();
        for (Vuelo v : app.getVuelos()) {
            PuertaEmbarque p = v.getPuertaEmbarque();
            if (vuelosPorPuerta.containsKey(p)) {
                vuelosPorPuerta.get(p).add(v);
            } else {
                List<Vuelo> lista = new ArrayList<>();
                lista.add(v);
                vuelosPorPuerta.put(p, lista);
            }
        }

        Map<PuertaEmbarque, Double> resultado = new HashMap<>();
        for (PuertaEmbarque p : vuelosPorPuerta.keySet()) {
            List<Vuelo> lista = vuelosPorPuerta.get(p);
            long minutosTotal = 0;
            LocalDate inicio = null, fin = null;
            for (Vuelo v : lista) {
                LocalDate f = v.getVueloProgramado().getFrecuencia().getFechaInicio();
                if (inicio == null || f.isBefore(inicio))
                    inicio = f;
                if (fin == null || f.isAfter(fin))
                    fin = f;
                minutosTotal += v.getMinutosPuertaEmbarque();
            }
            int dias = (inicio != null && fin != null)
                    ? Period.between(inicio, fin).getDays() + 1
                    : 1;
            double horasMedias = (minutosTotal / 60.0) / dias;
            resultado.put(p, horasMedias);
        }
        return resultado;
    }

    /**
     * Calcula el uso medio diario de cada finger en horas.
     * 
     * @return Un mapa que asocia cada finger con su uso medio diario en horas.
     */
    public Map<Finger, Double> usoMedioFingerPorDia() {
        Map<Finger, List<Vuelo>> vuelosPorFinger = new HashMap<>();
        for (Vuelo v : app.getVuelos()) {
            Finger f = v.getFinger();
            if (vuelosPorFinger.containsKey(f)) {
                vuelosPorFinger.get(f).add(v);
            } else {
                List<Vuelo> lista = new ArrayList<>();
                lista.add(v);
                vuelosPorFinger.put(f, lista);
            }
        }

        Map<Finger, Double> resultado = new HashMap<>();
        for (Finger f : vuelosPorFinger.keySet()) {
            List<Vuelo> lista = vuelosPorFinger.get(f);
            long minutosTotal = 0;
            LocalDate inicio = null, fin = null;
            for (Vuelo v : lista) {
                LocalDate d = v.getVueloProgramado().getFrecuencia().getFechaInicio();
                if (inicio == null || d.isBefore(inicio))
                    inicio = d;
                if (fin == null || d.isAfter(fin))
                    fin = d;
                minutosTotal += v.getMinutosFinger();
            }
            int dias = (inicio != null && fin != null)
                    ? Period.between(inicio, fin).getDays() + 1
                    : 1;
            double horasMedias = (minutosTotal / 60.0) / dias;
            resultado.put(f, horasMedias);
        }
        return resultado;
    }

    /**
     * Calcula el numero de vuelos en retraso en un mes especifico.
     * 
     * @param mes El mes para el que se calcularan los vuelos en retraso.
     * @return Numero de vuelos en retraso.
     */
    public int numeroVuelosRetraso(YearMonth mes) {
        int total = 0;
        for (Vuelo v : app.getVuelos()) {
            VueloProgramado vp = v.getVueloProgramado();
            YearMonth mesVuelo = YearMonth.from(vp.getFrecuencia().getFechaInicio());
            v.actualizarRetraso();
            if (v.isRetrasado() && mes.equals(mesVuelo))
                total += 1;
        }
        return total;
    }

    /**
     * Calcula el tiempo medio de retraso de los vuelos en un mes especifico.
     * 
     * @param mes El mes para el que se calculara el tiempo medio de retraso.
     * @return Tiempo medio de retraso en minutos.
     */
    public double tiempoVuelosRetraso(YearMonth mes) {
        long total = 0;
        for (Vuelo v : app.getVuelos()) {
            VueloProgramado vp = v.getVueloProgramado();
            YearMonth mesVuelo = YearMonth.from(vp.getFrecuencia().getFechaInicio());
            v.actualizarRetraso();
            if (v.isRetrasado() && mes.equals(mesVuelo))
                total += v.tiempoRetraso();
        }
        return total / (double) mes.lengthOfMonth();
    }

    /**
     * Calcula el numero de vuelos que no estan retrasados agrupados por mes.
     * 
     * @return Mapa cuya clave es el mes y cuyo valor es el numero de vuelos.
     */
    public Map<YearMonth, Integer> numeroVuelosEnHoraPorMes() {
        Map<YearMonth, Integer> cuenta = new HashMap<>();
        for (Vuelo v : app.getVuelos()) {
            v.actualizarRetraso();
            if (!v.isRetrasado()) {
                YearMonth ym = YearMonth.from(
                        v.getVueloProgramado().getFrecuencia().getFechaInicio());
                cuenta.put(ym, cuenta.getOrDefault(ym, 0) + 1);
            }
        }
        return cuenta;
    }

    /**
     * Calcula el número de vuelos que no están retrasados agrupados por hora de
     * salida.
     * 
     * @return Mapa cuya clave es la hora del día (en formato de 24 horas) y cuyo
     *         valor es el número de vuelos.
     */

    public Map<Integer, Integer> numeroVuelosEnHoraPorFranjaHoraria() {

        Map<Integer, Integer> cuenta = new TreeMap<>();
        for (Vuelo v : app.getVuelos()) {
            v.actualizarRetraso();
            if (!v.isRetrasado()) {
                LocalTime time = v.getHoraSalida();
                int hora = time.getHour();
                cuenta.put(hora, cuenta.getOrDefault(hora, 0) + 1);
            }
        }
        return cuenta;
    }

    /**
     * Calcula el número de vuelos que no están retrasados agrupados por vuelo
     * programado.
     * 
     * @return Mapa cuya clave es el vuelo programado y cuyo valor es el número de
     *         vuelos.
     */
    public Map<VueloProgramado, Integer> numeroVuelosEnHoraPorVueloProgramado() {
        Map<VueloProgramado, Integer> cuenta = new HashMap<>();
        for (Vuelo v : app.getVuelos()) {
            v.actualizarRetraso();
            if (!v.isRetrasado()) {
                VueloProgramado vp = v.getVueloProgramado();
                cuenta.put(vp, cuenta.getOrDefault(vp, 0) + 1);
            }
        }
        return cuenta;
    }

    /**
     * Genera un informe de estadisticas para un operador basado en un mes
     * especifico.
     * 
     * @param mes El mes para el cual se generara el informe.
     * @return Cadena con la estadistica del operador.
     */
    public String estadisticaOperador(YearMonth mes) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("MMMM", Locale.of("es", "ES"));
        String nombreMes = mes.atDay(1).format(formato);
        return "Estadistica " + nombreMes +
                "\nNumero de vuelos en retraso: " + numeroVuelosRetraso(mes) +
                "\nTiempo medio diario vuelos en retraso: " + tiempoVuelosRetraso(mes) + "\n";
    }
}
