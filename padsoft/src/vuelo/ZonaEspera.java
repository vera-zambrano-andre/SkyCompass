package vuelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Clase que representa la zona de espera de vuelos.
 * 
 * Esta clase implementa la cola de espera de vuelos con prioridad.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class ZonaEspera implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Clase interna que representa el comparador de vuelos.
     */
    public static class VueloComparator implements Comparator<Vuelo>, Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Compara dos vuelos según la siguiente prioridad:
         * 1. Mayor retraso
         * 2. Mayor tiempo en espera
         * 3. Vuelos de pasajeros antes que mercancías
         * 4. Mayor capacidad
         * 
         * @param a Vuelo a comparar
         * @param b Vuelo a comparar
         * @return -1 si a tiene prioridad sobre b, 1 si b tiene prioridad sobre a, 0 si son iguales
         */
        @Override
        public int compare(Vuelo a, Vuelo b) {
            long da = a.tiempoRetraso(), db = b.tiempoRetraso();
            if (db != da)
                return Long.compare(db, da);
            long wa = a.getTiempoEspera(), wb = b.getTiempoEspera();
            if (wb != wa)
                return Long.compare(wb, wa);
            boolean pa = a.getVueloProgramado().getAvion().getTipo().isPassenger();
            boolean pb = b.getVueloProgramado().getAvion().getTipo().isPassenger();
            if (pa != pb)
                return pa ? -1 : 1;
            double ca = a.getVueloProgramado().getAvion().getTipo().getCapacidad();
            double cb = b.getVueloProgramado().getAvion().getTipo().getCapacidad();
            return Double.compare(cb, ca);
        }
    }

    private transient PriorityQueue<Vuelo> cola;

    /*
     * Constructor de la zona de espera
     */
    public ZonaEspera() {
        initQueue();
    }

    /**
     * Inicializa la cola de espera de vuelos con un comparador de vuelos.
     */
    private void initQueue() {
        cola = new PriorityQueue<>(new VueloComparator());
    }

    /**
     * Agrega un vuelo a la cola de espera y lo inicializa.
     * 
     * @param vuelo el vuelo a agregar
     */
    public void addVuelo(Vuelo vuelo) {
        vuelo.iniciarEspera();
        cola.add(vuelo);
    }

    /**
     * Devuelve el siguiente vuelo en la cola de espera.
     * 
     * @return el siguiente vuelo en la cola de espera, o null
     */
    public Vuelo siguienteVuelo() {
        Vuelo v = cola.poll();
        if (v != null)
            v.terminarEspera();
        return v;
    }

    /**
     * Comprueba si la cola de espera de vuelos está vacía.
     * 
     * @return true si la cola está vacía, false en otro caso
     */
    public boolean isEmpty() {
        return cola.isEmpty();
    }

    /**
     * Devuelve un mapa con los vuelos en la zona de espera
     * 
     * @return un mapa con los vuelos en la zona de espera
     */
    public Map<Vuelo, LocalTime> getVuelosEnZonaEspera() {
        List<Vuelo> list = new ArrayList<>(cola);
        Collections.sort(list, new VueloComparator());
        Map<Vuelo, LocalTime> schedule = new LinkedHashMap<>();
        LocalTime time = LocalTime.now().plusMinutes(5);
        for (Vuelo v : list) {
            schedule.put(v, time);
            time = time.plusMinutes(5);
        }
        return schedule;
    }

}
