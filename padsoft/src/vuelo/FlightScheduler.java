package vuelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.LocalDate;
import sistema.SkyCompass;
import aeropuerto.Aeropuerto;

import java.io.Serializable;

/**
 * Clase que representa la gestión de la ejecución de despegues y aterrizajes.
 * 
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */
public class FlightScheduler implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Ejecuta los despegues de los vuelos dados.
     * 
     * @param vuelos           Lista de vuelos a ejecutar.
     * @param app              La aplicación.
     * @param aeropuertoOrigen El aeropuerto de origen.
     */
    public static void ejecutarDespegues(List<Vuelo> vuelos, SkyCompass app, Aeropuerto aeropuertoOrigen) {
        List<Vuelo> copia = new ArrayList<>(vuelos);
        Collections.sort(copia, new Comparator<Vuelo>() {
            @Override
            public int compare(Vuelo a, Vuelo b) {
                return a.getFechaHoraSalida().compareTo(b.getFechaHoraSalida());
            }
        });
        int cont = 0;
        System.out.println("Comienzan los despegues: ");
        for (Vuelo elemento : copia) {
            LocalDateTime salida = elemento.getFechaHoraSalida();
            if (!salida.toLocalDate().equals(LocalDate.now())
                    || !elemento.getVueloProgramado().getOrigen().equals(aeropuertoOrigen)) {
                continue;
            }

            System.out.println((cont++) + " Despegue - vuelo: " + elemento);

            while (LocalDateTime.now().isBefore(salida)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

            app.asignarVueloHangar(elemento);
            System.out.println("  Hangar: " + elemento.getHangar());
            app.desasignarVueloHangar(elemento);
            app.asignarVueloPuertaEmbarque(elemento);
            System.out.println("  Puerta: " + elemento.getPuertaEmbarque());
            app.iniciarEmbarque(elemento);
            app.finalizarEmbarque(elemento);

            boolean ok;
            do {
                ok = app.asignarVueloPistaDespegue(elemento);
                if (!ok) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            } while (!ok);
            System.out.println("  Pista de despegue asignada");
            app.darSeñalDespegue(elemento);
            System.out.println("  Estado: " + elemento.getEstadoVuelo());
            app.desasignarVueloPistaDespegue(elemento);
        }
    }

    /**
     * Ejecuta los aterrizajes de los vuelos dados.
     *
     * 
     * @param vuelos            Lista de vuelos a ejecutar.
     * @param app               La aplicación.
     * @param aeropuertoDestino El aeropuerto de destino.
     */
    public static void ejecutarAterrizajes(List<Vuelo> vuelos, SkyCompass app, Aeropuerto aeropuertoDestino) {
        List<Vuelo> copia = new ArrayList<>(vuelos);
        Collections.sort(copia, new Comparator<Vuelo>() {
            @Override
            public int compare(Vuelo a, Vuelo b) {
                return a.getFechaHoraLlegada().compareTo(b.getFechaHoraLlegada());
            }
        });
        int cont = 0;
        System.out.println("Comienzan los aterrizajes: ");
        for (Vuelo elemento : copia) {
            LocalDateTime llegada = elemento.getFechaHoraLlegada();
            if (!llegada.toLocalDate().equals(LocalDate.now())
                    || !elemento.getVueloProgramado().getDestino().equals(aeropuertoDestino)) {
                continue;
            }
            System.out.println((cont++) + " Aterrizaje - vuelo: " + elemento);

            while (LocalDateTime.now().isBefore(llegada)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

            boolean ok;
            do {
                ok = app.asignarVueloPistaAterrizaje(elemento);
                if (!ok) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            } while (!ok);

            System.out.println("  Pista de aterrizaje asignada");
            boolean aterrizaje = app.darSeñalAterrizaje(elemento);
            System.out.println("  Señal aterrizaje: " + aterrizaje);
            app.desasignarVueloPistaAterrizaje(elemento);

            app.asignarVueloPuertaEmbarque(elemento);
            System.out.println("  Puerta: " + elemento.getPuertaEmbarque());
            app.iniciarDesembarque(elemento);
            app.finalizarDesembarque(elemento);
            boolean fin = app.finalizarVuelo(elemento);
            System.out.println("  Vuelo finalizado: " + fin + " estado=" + elemento.getEstadoVuelo());
        }
    }
}