package main;

import aeropuerto.*;
import elementocoste.*;
import estadística.Estadistica;
import factura.CondicionLimiteImporte;
import factura.CondicionLimiteVuelos;
import factura.Descuento;
import factura.Factura;
import sistema.SkyCompass;
import usuario.*;
import vuelo.*;

import java.io.IOException;
import java.time.*;
import java.util.*;

import javax.swing.plaf.nimbus.AbstractRegionPainter;

/**
 * Clase que prueba la funcionalidad de la app.
 * @author Ruben Bucero Gonzalez
 * @author Xinliang Lin
 * @author Andre Vera Zambrano
 */

public class Main {

    public static void main(String[] args)  {
        SkyCompass app = SkyCompass.getInstance();

        System.out.println("Registro y sesión:");

        // Registrar gestor
        Gestor gestor = app.getGestor();
        System.out.println(gestor); 

        System.out.println("\tGestor registrado con éxito.");

        // Registrar operador

        Aerolinea aerolinea1 = app.crearAerolinea("AeroTest", "AT001");
        Operador operador1 = app.registrarOperador("000000000A", "Operador1", aerolinea1);
        System.out.println("\tOperador registrado con éxito.");


        // Loguear operador
        app.loginEmpleado("000000000A", "macarrones");
        if(app.getUsuarioLogeado() instanceof Operador){
            System.out.println("\tOperador logueado con contraseña correcta.");
        }
        else{
            System.out.println("\tOperador logueado con contraseña incorrecta.");
        }

        // Loguear gestor
        app.loginGestor("admin");
        if(app.getUsuarioLogeado() instanceof Gestor) {
            System.out.println("\tGestor logueado con contraseña correcta.");
        } else {
            System.out.println("\tGestor logueado con contraseña incorrecta.");
        }

        List<PuertaEmbarque> puertas = new ArrayList<>();

        app.darAltaZonaAparcamiento("ZA1", 50, app.crearPlazas(10, 20, 30), 20.0);
        PuertaEmbarque puerta1 = app.darAltaPuertaEmbarque("P1", app.darAltaFinger("F1", 12.0, true, 50.0), app.getZonasAparcamiento());

        app.darAltaZonaAparcamiento("ZA2", 50, app.crearPlazas(10, 20, 30), 20.0);
        PuertaEmbarque puerta2 = app.darAltaPuertaEmbarque("P2", app.darAltaFinger("F2", 10.0, false, 40.0), app.getZonasAparcamiento());
        
        app.darAltaZonaAparcamiento("ZA3", 50, app.crearPlazas(10, 20, 30), 20.0);
        PuertaEmbarque puerta3 = app.darAltaPuertaEmbarque("P3", app.darAltaFinger("F3", 15.0, true, 60.0), app.getZonasAparcamiento());

        app.darAltaZonaAparcamiento("ZA4", 50, app.crearPlazas(10, 20, 30), 20.0);
        PuertaEmbarque puerta4 = app.darAltaPuertaEmbarque("P4", app.darAltaFinger("F4", 15.0, true, 60.0), app.getZonasAparcamiento());
        puertas.add(puerta1);
        puertas.add(puerta2);
        puertas.add(puerta3);
        System.out.println("\tSe han creado " + puertas.size() + " puertas de embarque con sus respectivos fingers y zonas de aparcamiento.");

        
        TerminalPasajero terminal1 = app.darAltaTerminalPasajero("TP1", 1, 200, puertas, new RangoTiempo(LocalTime.of(0, 0), LocalTime.of(23, 59))); // RangoTiempo de prueba
        TerminalPasajero terminal2 = app.darAltaTerminalPasajero("TP2", 1, 200, puertas, new RangoTiempo(LocalTime.of(0, 0), LocalTime.of(23, 59)));
        System.out.println("\tTerminal de Pasajeros TP1 creada.");

        // Registrar controlador
        app.registrarControlador("000000000B", "Controlador1", "contragestor", terminal1);
        System.out.println("\tControlador registrado con éxito.");

        // Loguear controlador
        app.loginEmpleado("000000000B", "contragestor");
        if(app.getUsuarioLogeado() instanceof Controlador) {
            System.out.println("\tControlador logueado con contraseña correcta.");
        } else {
            System.out.println("\tControlador logueado con contraseña incorrecta.");
        }

        // Loguear gestor
        app.loginGestor("admin");
        if(app.getUsuarioLogeado() instanceof Gestor) {
            System.out.println("\tGestor logueado con contraseña correcta.");
        } else {
            System.out.println("\tGestor logueado con contraseña incorrecta.");
        }

        // Crear fingers adicionales de forma independiente
        Finger finger4 = app.darAltaFinger("F4", 300.0, false, 45.0);
        Finger finger5 = app.darAltaFinger("F5", 200.0, true, 55.0);
        System.out.println("\tFingers adicionales creados: " + finger4.getCodigo() + ", " + finger5.getCodigo());

        
        System.out.println("\tSe han creado " + puertas.size() + " puertas de embarque con sus respectivos fingers y zonas de aparcamiento.");
        
        System.out.println(app.getFingers());

        List<Plaza> plazas1 = app.crearPlazas(200, 50, 60);  
        List<Plaza> plazas2 = app.crearPlazas(200, 50, 60);   
        // Crear zonas de aparcamiento adicionales
        ZonaAparcamiento zonaAparcamiento1 = app.darAltaZonaAparcamiento("ZA4", 60, plazas1, 25.0);
        ZonaAparcamiento zonaAparcamiento2 = app.darAltaZonaAparcamiento("ZA5", 70, plazas2, 30.0);
        

        // Datos básicos del aeropuerto
        String nombre = "Adolfo Suárez Madrid-Barajas";
        String codigo1 = "MAD";
        String ciudadMasCercana = "Madrid";
        String pais = "España";
        int distanciaCiudadKm = 19;
        Direccion direccion = Direccion.ESTE; 
        int diferenciaHoraria = 1;



        // Lista de temporadas operativas del aeropuerto
        List<Temporada> temporadas = new ArrayList<>();

        // Formato de fecha y horario
        LocalDate fechaInicio1 = LocalDate.of(2024, 10, 31); // 31 de octubre de 2024
        LocalDate fechaFin1 = LocalDate.of(2025, 3, 30); // 30 de marzo de 2025
        LocalTime horarioApertura1 = LocalTime.of(9, 0); // 09:00 AM
        LocalTime horarioCierre1 = LocalTime.of(23, 0); // 11:00 PM

        Temporada temporada1 = new Temporada(fechaInicio1, fechaFin1, horarioApertura1, horarioCierre1);
        temporadas.add(temporada1);

        LocalDate fechaInicio2 = LocalDate.of(2025, 3, 31); // 31 de marzo de 2025
        LocalDate fechaFin2 = LocalDate.of(2025, 10, 30); // 30 de octubre de 2025
        LocalTime horarioApertura2 = LocalTime.of(8, 0); // 08:00 AM
        LocalTime horarioCierre2 = LocalTime.of(23, 0); // 11:00 PM

        Temporada temporada2 = new Temporada(fechaInicio2, fechaFin2, horarioApertura2, horarioCierre2);
        temporadas.add(temporada2);

        // Creación del objeto Aeropuerto
        Aeropuerto aeropuerto = app.crearAeropuerto(nombre, codigo1, ciudadMasCercana, pais, distanciaCiudadKm, direccion, diferenciaHoraria, temporadas);
        Aeropuerto aer= app.crearAeropuerto("El Papo de Montesblau", codigo1, ciudadMasCercana, pais, distanciaCiudadKm, direccion, diferenciaHoraria, temporadas);
        
        // Definiendo los datos para el vuelo
        LocalDate fechaInicio = LocalDate.of(2025, 6, 10); // 1 de junio de 2025
        LocalTime horaSalida = LocalTime.of(5, 0);
        LocalDate fechaFin = LocalDate.of(2025, 6, 30); // 3 de junio de 2025
        LocalTime horaFin = LocalTime.of(22, 0, 0);

        String codigo2 = "IB1234"; // Código de vuelo
        FrecuenciaVuelo frecuencia = new VueloDiario(fechaInicio, fechaFin); 

        Dimension dimension = new Dimension(10, 20, 30);

        TipoAvionPasajero tipoAvion = new TipoAvionPasajero("Mercedes", "JX2", dimension, 2000, 50);

        TipoAvionMercancia tipoAvionMercancia = new TipoAvionMercancia("Boeeing", "PG9", dimension, 1000, true, true, 10000);

        LocalDate fechaRevision = LocalDate.of(2025, 1, 1);
        Avion avion = app.darAltaAvion(2010, fechaRevision, tipoAvion);

        Avion avionMerc = app.darAltaAvion(2012, fechaRevision, tipoAvionMercancia);

        Aerolinea aerolinea = new Aerolinea("Iberia", "IB");

        Aeropuerto aeropuertoOrigen = app.getAeropuertos().get(0);

        Aeropuerto aeropuertoDestino = app.getAeropuertos().get(1);
        
        boolean usaFinger = true;

        puerta1.setEstadoPuertaEmbarque(EstadoElemento.DISPONIBLE);

        VueloProgramado vueloMerc = app.programarVuelo(codigo2, horaSalida, horaFin, frecuencia, avionMerc, aerolinea1, aeropuertoOrigen, aeropuertoDestino, new IVueloMercanciasProgramacion());
        // Creando el vuelo de pasajeros
        VueloProgramado vueloP = app.programarVuelo(codigo2, horaSalida, horaFin, frecuencia, avion, aerolinea1, aeropuertoOrigen, aeropuertoDestino, new IVueloPasajerosProgramacion(usaFinger));
        
        // Definiendo los datos para otro vuelo
        LocalDate fechaInicioOtroVuelo = LocalDate.of(2025, 6, 1); 
        LocalTime horaSalidaOtroVuelo = LocalTime.of(5, 25, 0);
        LocalDate fechaFinOtroVuelo = LocalDate.of(2025, 6, 2); 
        LocalTime horaFinOtroVuelo = LocalTime.of(23, 0, 0);

        String codigoOtroVuelo = "IB5678"; // Código de vuelo
        FrecuenciaVuelo frecuenciaOtroVuelo = new VueloDiario(fechaInicioOtroVuelo, fechaFinOtroVuelo);

        Dimension dimensionOtroVuelo = new Dimension(15, 25, 35);

        TipoAvionPasajero tipoAvionOtroVuelo = new TipoAvionPasajero("Boeing", "747", dimensionOtroVuelo, 3000, 70);

        LocalDate fechaRevisionOtroVuelo = LocalDate.of(2025, 2, 1);
        Avion avionOtroVuelo = new Avion(2021, fechaRevisionOtroVuelo, tipoAvionOtroVuelo);
        Aerolinea aerolineaOtroVuelo = new Aerolinea("British Airways", "BA");

        boolean usaFingerOtroVuelo = false;
        puerta2.setEstadoPuertaEmbarque(EstadoElemento.DISPONIBLE);

        // Creando el otro vuelo de pasajeros
        VueloProgramado vueloOtroP = app.programarVuelo(codigoOtroVuelo, horaSalidaOtroVuelo, horaFinOtroVuelo, frecuenciaOtroVuelo, avionOtroVuelo, aerolineaOtroVuelo, aeropuertoOrigen, aeropuertoDestino, new IVueloPasajerosProgramacion(usaFingerOtroVuelo));

        System.out.println("\tVUELOS SOLICITADOS:" + app.getVuelosSolicitados());
        
        boolean vuelo = app.aceptarVueloProgramado(vueloP); 

        boolean vuelo2 = app.aceptarVueloProgramado(vueloOtroP);

        boolean vuelo3 = app.aceptarVueloProgramado(vueloMerc);

        Hangar hangarPasajero = app.darAltaHangarPasajero(new Dimension(50, 50, 50), 50, 100);
        Hangar hangarMercancia = app.darAltaHangarMercancia(new Dimension(75, 75, 75), 50, true, 100);
        
        if(vuelo){
            System.out.println("\tVuelo programado con éxito.");
        }
        else {
            System.out.println("\tVuelo programado con fallo.");
        }

        if(vuelo2){
            System.out.println("\tVuelo programado con éxito.");
        }
        else {
            System.out.println("\tVuelo programado con fallo.");
        }

        System.out.println(app.getNotificaciones());

        ZonaEspera z1 = new ZonaEspera();  
        app.darAltaPistaDespegue(200, z1);
        ZonaEspera z2 = new ZonaEspera();  
        app.darAltaPistaDespegue(200, z2);
        ZonaEspera z3 = new ZonaEspera();  
        app.darAltaPistaDespegue(200, z3);

        app.darAltaPistaAterrizaje(200);
        app.darAltaPistaAterrizaje(369);
        app.darAltaPistaAterrizaje(369);
        app.darAltaPistaAterrizaje(369);

        System.out.println("\n VUELOS :" + app.getVuelos());


        System.out.println("\n VUELOS PROGRAMADOS:" + app.getVuelosProgramados());

        int cont =0;
         //aeropuerto de destino del app.

        //metodo simular vuelo de hoy

        FlightScheduler flightScheduler = new FlightScheduler();

        //flightScheduler.ejecutarDespegues(app.getVuelos(), app, aeropuertoOrigen);

        //flightScheduler.ejecutarAterrizajes(app.getVuelos(), app, aeropuertoDestino);
        
        for(Vuelo elemento : app.getVuelos()){

                System.out.println(cont++ + " Elemento");

                app.asignarVueloHangar(elemento);

                System.out.println(elemento.getHangar());

                Avion avionVuelo = elemento.getVueloProgramado().getAvion();

                System.out.println(avionVuelo.getLocalizacion());

                app.desasignarVueloHangar(elemento);

                app.asignarVueloPuertaEmbarque(elemento);

                System.out.println(elemento.getPuertaEmbarque());

                boolean embarque = app.iniciarEmbarque(elemento);

                boolean embarque2 = app.finalizarEmbarque(elemento);

                app.desasignarVueloPuertaEmbarque(elemento);

                boolean pista = app.asignarVueloPistaDespegue(elemento);
                
                //el elemento con mayor prioridad se despega
                boolean despegue = app.darSeñalDespegue(elemento);

                app.desasignarVueloPistaDespegue(elemento);

                //cambio

                pista = app.asignarVueloPistaAterrizaje(elemento);

                boolean aterrizaje = app.darSeñalAterrizaje(elemento);

                app.asignarVueloZonaAparcamiento(elemento);

                app.desasignarVueloZonaAparcamiento(elemento);

                if(!aterrizaje){
                    System.out.println("Aterrizaje fallido");
                }

                app.asignarVueloPuertaEmbarque(elemento);
                System.out.println(elemento.getPuertaEmbarque());
                app.desasignarVueloPistaAterrizaje(elemento);

                embarque = app.iniciarDesembarque(elemento);
                System.out.println(embarque + " " + elemento.getEstadoVuelo());

                embarque2 = app.finalizarDesembarque(elemento);
                System.out.println(embarque2 + " " + elemento.getEstadoVuelo());

                boolean fin = app.finalizarVuelo(elemento);
                System.out.println(fin + " " + elemento.getEstadoVuelo());

        }
        

        /*
        System.out.println("\tEstado del vuelo: " + vuelo);


        if(vuelo != null) {
            System.out.println("\tVuelo programado con éxito.");
        }

        app.asignarVueloPuertaEmbarque(vuelo);

        

        avion.setLocalizacion(hangarPasajero);
        
        vuelo.iniciarEmbarque();

        System.out.println(gestor.getNotificaciones());

        System.out.println("\tEstado del vuelo: " + vuelo.getEstadoVuelo());

        if(vuelo.getEstadoVuelo().equals(EstadoVuelo.EMBARQUE)){
            System.out.println("\tCarga iniciada con éxito.");
        }
        else{
            System.err.println("\tError al iniciar la carga.");
        }

        if(vuelo.getVueloProgramado().getTipo().equals("mercancias"))
            System.err.println("Tipo erroneo\n");

        if(vuelo.despegarVuelo().equals(false))
            System.err.println("Despegando vuelo sin exito");

        long retraso = vuelo.tiempoRetraso();
        if(retraso > 0)
            System.out.println("El tiempo de retraso es"+retraso);

        if(vuelo.aterrizarVuelo().equals(false))
            System.out.println("Vuelo aterrizando sin exito");

        //Estadistica.getEstadistica().estadisticaGestor();

        //Estadistica.getEstadistica().estadisticaOperador(YearMonth.of(2024, 6));
        
        */

        for (Vuelo v : app.getVuelos()) {
            System.out.println("Vuelo " + v.getVueloProgramado().getCodigo() + ": hangar " + v.getMinutosHangar() + " ,finger " + v.getMinutosFinger() + " ,aparcamiento " + v.getMinutosZonaAparcamiento() + " ,puerta embarque " + v.getMinutosPuertaEmbarque());
        }        

        Estadistica stats = Estadistica.getEstadistica();

        System.out.println("\tUso medio del hangar por dia: " + stats.usoMedioHangarPorDia());

        try {
            app.importarAeropuertosFichero("./resources/aeropuertos.txt");
        } catch (IOException ex) {
            System.err.println("Error al importar los aeropuertos");
        }

        //System.out.println("\tAeropuertos cargados: " + app.getAeropuertos());

        System.out.println("\tProbando a lanzar pago: ");
        app.lanzarPago(aerolinea1);

        Factura factura = app.getFacturas().get(0);

        Descuento descuento1 = app.darAltaDescuento("¡Junio a mitad de precio!: ", LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30), 50, new CondicionLimiteVuelos(3), true);
        Descuento descuento2 = app.darAltaDescuento("Aprovecha el 20% en los recursos del aeropuerto", LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30), 20, new CondicionLimiteVuelos(3), false);
        app.definirNuevoPeriodoDescuento(descuento1, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30));

        System.out.println("\tPrecio inicial: " + factura.getPrecioTotalSinDecuento());
        System.out.println("\tPrecio Recursos: " + factura.getPrecioRecursosSinDescuento());

        factura.aplicarDescuentos(app.getDescuentos());

        factura.generarFacturaPDF();
        System.out.println("\tPrecio final: " + factura.getPrice());

        app.darAltaTipoAvionPasajero("Boing", "737", new Dimension(10, 10, 10), 1000, 100);

        System.out.println("\tNotificaciones generadas a los operadores: " + app.getOperadores().get(0).getNotificaciones());

        app.guardarInformacion("./resources/datosSkyCompass.txt");
        System.out.println("\tDatos guardados con éxito.");
        SkyCompass cargado = SkyCompass.cargarInformacion("./resources/datosSkyCompass.txt");
        System.out.println("\tDatos cargados: " );
        if (cargado != null) {
            System.out.println("\tCorrectamente");
        }
        else {
            System.out.println("\tError en carga.");
        }

    }
}
