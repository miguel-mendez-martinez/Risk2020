/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Miguel 
 * 
 */
public class Menu {
    // En esta clase se deberían de definir los atributos a los que será 
    // necesario acceder durante la ejecución del programa como, por ejemplo,
    // el mapa o los jugadores
    ArrayList<Jugador> jugadores;
    ArrayList<Pais> paises;
    ArrayList<Continente> continentes;
    ArrayList<Cartas> cartas;
    Mapa mapa;
    Jugador jugadorActual;
    Turno t;
    /**
     * 
     */
    public Menu() {
        // Inicialización de algunos atributos
        // Iniciar juego
         // Con fichero:
        this.mapa = null;
        Mision mision;
        this.jugadores = new ArrayList<>();
        this.paises = new ArrayList<>();
        this.continentes = new ArrayList<>();
        this.cartas = new ArrayList<>();
        int checker = 0, conquisto=1; //cuando se ataque y se obtenga victoria esto ira a 0, y se pondra siempre a 0 al empezar un turno
        String orden= null;
        BufferedReader bufferLector= null;
        this.jugadorActual = null;
        //Jugador jugadorActual = new Jugador();
        
        //debemos ver si ya existe un fichero salida y eliminarlo si lo hace, ya que si existe escribiremos en el y tendremos las salidas de ejecuciones distintas en el mismo archivo
        File file = new File("salida.txt"); 
        if(file.delete()) 
        { 
            System.out.println("Existía archivo, ha sido eliminado correctamente"); 
        } 
        else
        { 
            System.out.println("No existía archivo, por lo que no elimino nada."); 
        }
        
        //preguntar al weon si esto de arriba deberiamos imprimirlo tmb en salidas
        // weon xddd
        
        
        try {
            File fichero=  new File("comandos.csv"); // fichero será comandos.csv
            FileReader lector= new FileReader(fichero); // Creacion de objeto que lee "fichero"
            bufferLector= new BufferedReader(lector); // Usamos objeto de la clase BufferedReader ya que permite leer lineas completas
            while((orden= bufferLector.readLine())!=null) { // Mientras las líneas no estén en blanco:
                String comandoArchivo = "\n$> " + orden;
                System.out.println(comandoArchivo);
                Salida s = new Salida();
                s.imprimirArchivo(comandoArchivo);
                String[] partes=orden.split(" "); // Separa en diferentes strings los elementos de la linea separados por espacios
                String comando= partes[0]; // Definimos como comando la primera palabra de la linea
                // COMANDOS INICIALES PARA EMPEZAR A JUGAR
                //    crear mapa
                //    crear jugadores <nombre_fichero>
                //    crear <nombre_jugador> <nombre_color>
                //    asignar misiones
                //    asignar paises <nombre_fichero>
                //    asignar <nombre_pais> <nombre_jugador>

                // COMANDOS DISPONIBLES DURANTE EL JUEGO
                //    acabar
                //    atacar <nombre_pais> <nombre_pais>
                //    describir continente <nombre_continente>
                //    describir frontera <nombre_pais>
                //    describir frontera <nombre_continente>
                //    describir jugador <nombre_jugador>
                //    describir pais <nombre_pais>
                //    jugador
                //    repartir ejercitos
                //    ver mapa
                //    ver pais <nombre_pais>
                switch(comando) {
                    case "crear":
                        if(partes.length==2) {
                            if(partes[1].equals("mapa")){
                                if(this.mapa == null){
                                    crearMapa();
                                    if(this.mapa == null){
                                        System.out.println("Error en la creacion de mapa.");
                                    }else
                                    checker = 1;//tras crear el mapa, los comandos de maxima prioridad han terminado, por lo que se pueden ejecutar los siguientes
                                }else{
                                    Salida error = new Salida(107);
                                    System.out.println(error.toString());
                                }
                            }else{
                                Salida error = new Salida(101);
                                System.out.println(error.toString());
                            }
                        }else if(partes.length==3) {
                            if(checker >= 1){
                                if(checker >2){ //si la siguiente instruccion en prioridad ha sido ejecutada no podra ejecutar esta, pero si podra volver a ejecutarse despues de un crear jugador
                                    Salida error = new Salida(99);
                                    System.out.println(error.toString());
                                }else{
                                    if(partes[1].equals("jugadores")) { 
                                        crearJugador(new File(partes[2]));
                                        //en cuanto creamos un jugador o varios, el actual será siempre el primero se añadan los que se añadan
                                        
                                        /*int i=0;
                                        this.jugadorActual = this.jugadores.get(i);*/
                                        if(checker < 2){
                                            checker = 2;
                                        }
                                    } else {
                                        crearJugador(partes[1], partes[2]);
                                        //idem arriba
                                        /*int i=0;
                                        this.jugadorActual = this.jugadores.get(i);*/
                                        if(checker < 2){
                                            checker = 2;
                                        }
                                    }
                                }
                            }else{
                                Salida error = new Salida(106);
                                System.out.println(error.toString());
                            }
                            
                        }else {
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }
                        break;
                    case "ver":
                        if(partes[1].equals("mapa")){
                            if(checker >= 1){
                                this.mapa.printMapa();
                            }else{
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }
                        }
                        break;
                    case "jugador":
                        //esto es describir toda la mierda del jugador del turno actual
                        if(this.jugadorActual == null){
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }else{
                            System.out.println(this.jugadorActual);
                            Salida x = new Salida();
                            x.imprimirArchivo(this.jugadorActual.toString());
                        }
                        
                        break;
                    case "describir":
                        if(partes[1].equals("jugador")){
                            //descripcion del jugador
                            Jugador jugador = existeJugador(this.jugadores, partes[2]);
                            //jugador.continentesJugador(this.continentes);
                            System.out.println(jugador);
                            Salida salida = new Salida();
                            salida.imprimirArchivo(jugador.toString());
                        }else if(partes[1].equals("pais")){
                            //descripcion del pais
                            Pais pais = existePais(this.paises, partes[2]);
                            if(pais == null){
                                Salida error = new Salida(109);
                                System.out.println(error.toString());
                            }else{
                                System.out.println(pais.descPais());
                                Salida salida = new Salida();
                                salida.imprimirArchivo(pais.descPais());
                            }
                            
                        }else if(partes[1].equals("Continente")){
                            //descripcion del continente
                        }else{
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }
                        break;
                    case "acabar":
                        if(checker >= 5){
                            this.jugadorActual = this.t.pasarTurno(this.jugadorActual);
                            conquisto = 0;
                            System.out.println(this.jugadorActual.printNomEjerR());
                            Salida salida = new Salida();
                            salida.imprimirArchivo(this.jugadorActual.printNomEjerR());
                            
                        }else{
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }
                        break;
                    case "cambiar":
                        if(this.jugadorActual == null){
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }else{
                            //aqui se haria la mierda de cartas de cambias todas o cambiar alguna por tropas
                            //se hace aqui al jugador actual que es quien marca los turnos
                        }
                        break;
                    case "asignar":
                        if(partes.length!=3) {
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }else if(partes[1].equals("misiones")){
                            if(checker >= 2){
                                if(checker > 3){ //si la siguiente instruccion ya ha sido ejecutada, no podra volver a ejecutar asignar
                                    Salida error = new Salida(99);
                                    System.out.println(error.toString());
                                }else{
                                    if(partes[1].equals("misiones")){
                                        asignarMisiones(new File(partes[2]));
                                        
                                        if(checker < 3){
                                            checker = 3;
                                        }
                                    }
                                }
                            }else{
                                Salida error = new Salida(105);
                                System.out.println(error.toString());
                            }
                            //primero tenemos que asignar misiones antes de paises
                        }else if(partes[1].equals("paises")){
                            if(checker >= 3){
                                if(checker > 4){
                                    Salida error = new Salida(99);
                                    System.out.println(error.toString());
                                }
                                else{
                                    asignarPaises(new File(partes[2]));
                                    for(Jugador j: this.jugadores){
                                        j.continentesJugador(this.continentes);
                                    }
                                    if(checker < 4){
                                            checker = 4;
                                        }
                                }
                            }else{
                                Salida error = new Salida(118);
                                System.out.println(error.toString());
                            }
                        }else if(partes[1].equals("carta")){
                            //se le asigna a jugador actual una carta
                            if(this.jugadorActual == null){
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }else{
                                String[] partes2 = partes[2].split("&"); 
                                if(partes2[0].equals("Infantería") || partes2[0].equals("Artillería") || partes2[0].equals("Caballería")){
                                    String pais = partes2[1];
                                    String clase = partes2[0];
                                    Pais p = this.existePais(this.paises, pais);
                                    if(p == null){
                                        Salida error = new Salida(109);
                                        System.out.println(error.toString());
                                    }else{//continuamos
                                        if(conquisto==1){
                                            //comprobar si esta asignada
                                            Cartas c = this.estaAsignada(this.cartas, pais, clase);
                                            if(c == null){
                                                c = new Cartas(pais, this.jugadorActual, clase);
                                                this.jugadorActual.setCartas(c);
                                                this.cartas.add(c);
                                                
                                                System.out.println(c);
                                                Salida salida = new Salida();
                                                salida.imprimirArchivo(c.toString());
                                                
                                            }else{
                                                Salida error = new Salida(126);
                                                System.out.println(error.toString()); 
                                            }
                                        }
                                    }
                                    
                                }else{
                                    Salida error = new Salida(125);
                                    System.out.println(error.toString());
                                }
                                
                            }
                        }else if(partes[2].charAt(0) == 'M'){
                            //Estas operaciones por ahora no cambian el checker ya que no asignan cosas a todos los jugadores
                            if(checker >= 2){
                                if(checker > 3){
                                    Salida error = new Salida(99);
                                    System.out.println(error.toString());
                                }else{
                                    if(partes[2].charAt(1) == '1' ||
                                    partes[2].charAt(1) == '2' ||
                                    partes[2].charAt(1) == '3' ||
                                    partes[2].charAt(1) == '4'){
                                        asignarMisiones(partes[1], partes[2]);
                                    }else{
                                        if(checker >= 3){
                                            asignarPaises(partes[1], partes[2]);
                                            for(Jugador j: this.jugadores){
                                                j.continentesJugador(this.continentes);
                                            }
                                        }else{
                                            Salida error = new Salida(118);
                                            System.out.println(error.toString());
                                        }   
                                    }
                                }
                            }else{
                                Salida error = new Salida(105);
                                System.out.println(error.toString());
                            }
                        }else if(checker >= 3){
                            asignarPaises(partes[1], partes[2]);
                        }else{
                            Salida error = new Salida(118);
                            System.out.println(error.toString());
                        }
                        
                        break;
                    case "atacar":
                        //el jugador actual atacara a un pais que no sea de su dominio, y que tenga alguna frontera en comun
                        //dos comandos, el automatico, y el forzado
                        if(partes.length>5){
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }else if(partes.length == 3){
                            /*automatico
                            tendremos que mirar, que exita pais, que pertenezca 
                            al jugador, que no pertenezca el que atacamos, si 
                            son frontera y si hay ejercitos suficientes, partes 1 
                            y partes 2, y ver si tienen suficientes ejercitos
                            y si son frontera*/
                            Pais p1 = this.existePais(this.paises, partes[1]);
                            Pais p2 = this.existePais(this.paises, partes[2]);
                            if(p1 == null || p2 == null){
                                Salida error = new Salida(109);
                                System.out.println(error.toString());
                            }else{
                                Pais p3 = this.existePais(this.jugadorActual.getPaises(), p1.getAbreviatura()); //miramos si el primer pais pertenece al jugador
                                if(p3 == null){
                                    Salida error = new Salida(110);
                                    System.out.println(error.toString());
                                }else{
                                    Pais p4 = this.existePais(this.jugadorActual.getPaises(), p2.getAbreviatura()); //miramos si el segundo pais NO pertenece al jugador
                                    if(p4 != null){
                                        Salida error = new Salida(111);
                                        System.out.println(error.toString());
                                    }else{
                                        //a este punto 1 y 3, 2 y 4 son iguales. 5 sera igual a p2
                                        Pais p5 = this.existePais(p1.getFronteras(), p2.getAbreviatura());
                                        if(p5 == null){
                                            Salida error = new Salida(112);
                                            System.out.println(error.toString());
                                        }else{
                                            System.out.println("Hola voy a atacar.\n");
                                        }
                                    }
                                }
                            }
                            
                            
                            //preguntar ataque pdf siberia-rusia
                        }else if(partes.length == 4){
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }else{
                            //forzado
                        }
                        break;
                    case "obtener": // Comandos sobre el mapa
                        if(checker >= 1){
                            if(partes.length!=3) {
                                Salida error = new Salida(101);
                                System.out.println(error.toString());
                            
                            }else if(partes[1].equals("frontera")){
                                obtenerFronteras(partes[2]);  

                            }else if(partes[1].equals("continente")){
                                obtenerContinente(partes[2]);

                            }else if(partes[1].equals("color")){
                                obtenerColor(partes[2]);

                            }else if(partes[1].equals("paises")){
                                obtenerPaises(partes[2]);
                            }
                        }else{
                            Salida error = new Salida(106);
                            System.out.println(error.toString());
                        }
                        break;
                    case "repartir":
                        
                        //en cuanto repartamos ejercitos, debe crearse un nuevo jugador actual, ese sera el que tiene el turno en ese momentoy el que ejecutara los comandos 
                        if(checker >= 4){
                            if(checker > 5){
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }else{
                                if(partes[2] == null){
                                    //auto, esta es la importante parra empezar la partida, deben estar repartidos entre los paises pa empezar todo
                                    checker = 5;
                                    this.jugadorActual = this.jugadores.get(0);
                                }else{
                                    repartirEjercitos(partes[2], partes[3]);
                                    this.jugadorActual = this.jugadores.get(0);
                                    //a partir de aqui es cuando comienzan los turnos
                                    checker = 5;
                                }
                            }
                        }else{
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }
                        break;
                    default:
                        Salida error = new Salida(101);
                        System.out.println(error.toString());
                }
            }
        } catch(Exception excepcion) {
            excepcion.printStackTrace();
        }
    }

    public Jugador existeJugador(ArrayList<Jugador> jugadores, String jugador){ // en nj guardamos el jugador si este existeque existe
        if(jugadores.isEmpty()==true){
            
            return null;
        }else{
            for(Jugador j : jugadores){
                if(j.getNombre().equals(jugador)==true){
                    return j;
                    }    
            }
            return null;
        }
    }
    public Continente existeContinente(ArrayList<Continente> continentes, String abCont){ // en nj guardamos el jugador si este existeque existe
        if(continentes.isEmpty()==true){
            
            return null;
        }else{
            for(Continente c : continentes){
                if(c.getNombre().equals(abCont)==true){
                    return c;
                    }    
            }
            return null;
        }
    }
    public Pais existePais(ArrayList<Pais> paises, String pais){ // mismo que nj
        if(paises.isEmpty()){
            
            return null;
        }else{
            for(Pais p : paises){
                if(p.getAbreviatura().equals(pais)==true){
                 
                    return p;
                    }    
            }
            return null;
        }
        
    }
    public Cartas estaAsignada(ArrayList<Cartas> carta, String pais, String clase){
        //esta funcion retorna null si la carta no esta asignada, la carta si lo esta.
        if(cartas.isEmpty()){
            return null;
        }else{
            for(Cartas c : carta){
                if((c.getClase().equals(clase)==true) && (c.getPais().equals(pais))){
                    if(c.getJugador() == null){
                        return null;
                    }else{
                        return c;
                    }
                    }    
            }
            return null;
        }
        
    }
    public void asignarMisiones(File file){
        String codigoMision;
        String nombreJugador;

        String jugadorLeido = null;
        BufferedReader bufferLector = null;

        try {

            File fichero = new File("misiones.csv");
            FileReader lector = new FileReader(fichero);
            bufferLector = new BufferedReader(lector);

            while((jugadorLeido= bufferLector.readLine())!=null){

                String[] partes = jugadorLeido.split(";");
                nombreJugador = partes[0];
                codigoMision = partes[1];
                asignarMisiones(nombreJugador, codigoMision);

            }
        }
        catch (Exception excepcion){
            excepcion.printStackTrace();
        }

    }
    
    public void asignarMisiones(String Jugador, String Codigo){
        int checkJug=0, checkPais=0;
        Mision m = new Mision();
        if(existeJugador(this.jugadores, Jugador) == null){ 
                Salida error = new Salida(103);
                System.out.println(error.toString());
        }else if(m.existeMision(Codigo) == 1){
            for(Jugador j : this.jugadores){
                if(j.getNombre().equals(Jugador)==true){
                        if(j.getMision() == null){
                            if(m.getJugador() == null){
                                m = new Mision(Codigo, j);
                                j.setMision(m);
                                System.out.println(m.toString());
                                Salida salida = new Salida();
                                salida.imprimirArchivo(m.toString());
                            }else{
                                Salida error = new Salida(115);
                                System.out.println(error.toString());
                            }
                        }else{
                            Salida error = new Salida(117);
                            System.out.println(error.toString());
                        }

                }    
            }
        }else{
            Salida error = new Salida(116);
            System.out.println(error.toString());
        }
    }
    public void asignarPaises(File file) {
        // Código necesario para asignar países

        String nombrePais;
        String nombreJugador;

        String jugadorLeido = null;
        BufferedReader bufferLector = null;

        try {

            File fichero = new File("asignaciones.csv");
            FileReader lector = new FileReader(fichero);
            bufferLector = new BufferedReader(lector);

            while((jugadorLeido= bufferLector.readLine())!=null){

                String[] partes = jugadorLeido.split(";");
                nombreJugador = partes[0];
                nombrePais = partes[1];
                asignarPaises(nombreJugador, nombrePais);
            }
        }
        catch (Exception excepcion){
            excepcion.printStackTrace();
        }

    }

    /**
     * 
     * @param nombrePais
     * @param nombreJugador 
     */
    public void asignarPaises(String nombreJugador, String nombrePais) {
        // Código necesario para asignar un país a un jugador
        /*dos for, uno va entre todos los jugadores y comprueba que exista y otro 
        recorre los paises para ver si existe, si el nombre pasado coicide con
        alguno de los paises del array del mapa, se añade a jugador y se setea su
        jugador al nombre que ha pasado por la lista*/
        int checkJug=0, checkPais=0, checkPaisAsig=0;
        Pais pais = existePais(this.paises, nombrePais);
        Jugador jugador = existeJugador(this.jugadores, nombreJugador);

        if(jugador == null){
                Salida error = new Salida(103);
                System.out.println(error.toString());
            }else{
            //Si el jugador existe lo siguiente es comprobar que exista el pais
                if(pais == null){
                    Salida error = new Salida(109);
                    System.out.println(error.toString());
                }else{ // Existe el pais y existe el jugador, comprobar que ese pais no este asignado ya
                    //pasamos a ver si los paises ya estan en el jugador etc etc etc
                    
                            if(!pais.estaAsignado()){ // El pais no está asignado a ningun jugador, por lo que asignamos e imprimimos
                                pais.setJugador(jugador);
                                jugador.setPaises(pais);
                                String fronteras = pais.fronterasToString();
                                String exito = "{\n\tnombre: " + nombreJugador +"\n\tpaís: " + nombrePais + "\n\tcontinente: " + pais.getContinente().getNombre() + "\n\tfronteras:" + fronteras + "\n}";
                                System.out.println(exito);
                                Salida salida = new Salida();
                                salida.imprimirArchivo(exito);

                            }else{ // COdigo de error pais asignado
                                Salida error = new Salida(113);
                                System.out.println(error.toString());
                            }
                        //}
                        
                    //}
// Queda pendiente error de misiones

                }
            }
        }


    /**
     * 
     */
    public void crearMapa() {
        // Código necesario para crear el mapa
        this.mapa = new Mapa();
        this.paises = this.mapa.getPaises();
        this.continentes = this.mapa.getContinentes();
        this.t = new Turno(this.paises);
        //System.out.println(mapa);
    }
    
    public void obtenerFronteras(String npais){
        Pais pais = existePais(this.paises,npais);
        if(pais == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String fronteras = "{\n fronteras: ";
            fronteras += pais.fronterasToString();
            fronteras += "\n}";
            System.out.println(fronteras);
            Salida salida = new Salida();
            salida.imprimirArchivo(fronteras);
        }
    }
    
    public void obtenerContinente(String npais){
        Pais pais = existePais(this.paises,npais);
        if(pais == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String continente = "{ continente: ";
            continente += pais.printCont() + " }";
            System.out.println(continente);
            Salida salida = new Salida();
            salida.imprimirArchivo(continente);
        }
}
    
    public void obtenerColor(String npais){
        Pais pais = existePais(this.paises, npais);
        if(pais == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String color = "{ color: ";
            color += pais.printColor() + " }";
            System.out.println(color);
            Salida salida = new Salida();
            salida.imprimirArchivo(color);
        }
    }

    public void obtenerPaises(String abCont){
        Continente c = existeContinente(this.continentes, abCont);
        if(c == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String paises = "{\n paises: [ ";
            for(int i=0; i<c.getPaises().size(); i++){
                if(i==0){
                    paises += c.getPaises().get(i).printNombre();
                }else{
                    paises += ", " + c.getPaises().get(i).printNombre();
                }
                
            }
            paises += " ]\n}";
            System.out.println(paises);
            Salida salida = new Salida();
            salida.imprimirArchivo(paises);
        }
    }

    /**
     * 
     * @param file 
     */
    private void crearJugador(File file) {
        // Código necesario para crear a los jugadores del RISK
        //lo que vamos a hacer es leer el fichero, y en caso de tener jugadores en el fichero llamar a la funcion de crear jugador con nombre y color.
        String nombre;
        String color;
        
        String jugadorleido=null;
        BufferedReader bufferLector= null;
        try {
            File fichero=  new File("jugadores.csv");
            FileReader lector= new FileReader(fichero);
            bufferLector= new BufferedReader(lector);
            while((jugadorleido= bufferLector.readLine())!=null) {
                String[] partes=jugadorleido.split(";");
                nombre= partes[0];
                color= partes[1];
                crearJugador(nombre, color);
            }
            
        }catch(Exception excepcion) {
            excepcion.printStackTrace();
        }
    }
    
    /**
     * 
     * @param file
     */
    private void crearJugador(String nombre, String color) {
        // Código necesario para crear a un jugador a partir de su nombre y color
        //aqui es donde debemos hacer las comprobaciones de todo e imprimir los errores
        //lo primero es comprobar color
        int flag=0;
        if(!(color.equals("AMARILLO") || color.equals("ROJO") || color.equals("AZUL")
                || color.equals("CYAN") || color.equals("VERDE") || color.equals("VIOLETA"))){
            //aqui estaria guai `poner nombre y color del jugador que se ha intentado crear
            Salida error = new Salida(100);
            System.out.println(error.toString()); 
        }
        else if(jugadores.isEmpty()==true){ //si esta vacia metemos directo
            Jugador jugador= new Jugador(nombre, color);
            jugadores.add(jugador);
            this.t.addJugador(jugador);
            System.out.println(jugador.printColorNom());
            Salida salida = new Salida();
            salida.imprimirArchivo(jugador.printColorNom());
            jugador.setAllContinentes(continentes);
            //utilizamos el metodo que solo imprime nombre y color
        }else{
            for (int i=0;i<jugadores.size();i++) {
                if(jugadores.get(i).getNombre().equals(nombre)==true){
                    Salida error = new Salida(104);
                    System.out.println(error.toString()); 
                    flag++;
                }
                else if(jugadores.get(i).getColor().equals(color)){
                    Salida error = new Salida(114);
                    System.out.println(error.toString()); 
                    flag++;
                }
            }
            if(flag==0){
                    Jugador jugador= new Jugador(nombre, color);
                    jugadores.add(jugador);
                    this.t.addJugador(jugador);
                    System.out.println(jugador.printColorNom());
                    Salida salida = new Salida();
                    salida.imprimirArchivo(jugador.printColorNom());
                jugador.setAllContinentes(continentes);
                }
        }
    }


    public void repartirEjercitos(String num, String nombrePais) {

        int numJugadores;
        numJugadores = contarJugadores(this.jugadores);
        Pais pais = existePais(this.paises, nombrePais);
        
        
        int numEjer = Integer.parseInt(num);

        if (pais == null) {

            Salida error = new Salida(109);
            System.out.println(error.toString());
            //error de que pais no existe 109

        }else if(pais.getJugador() == null){
            Salida error = new Salida(110);
            System.out.println(error.toString());
            // jugador no asignado 110

        }else{
            switch (numJugadores){
                case 3:
                    for (Jugador j: jugadores){
                        j.setTropas(35);
                        j.setEjercitos_disponibles(35);
                    }
                    break;

                case 4:
                    for (Jugador j: jugadores){
                        j.setTropas(30);
                        j.setEjercitos_disponibles(30);
                    }
                    break;

                case 5:
                    for (Jugador j: jugadores){
                        j.setTropas(25);
                        j.setEjercitos_disponibles(25);
                    }
                    break;

                case 6:
                    for (Jugador j: jugadores){
                        j.setTropas(20);
                        j.setEjercitos_disponibles(20);
                    }
                    break;
            }
        }
        int ejDisp = pais.getJugador().getEjercitos_disponibles();
        if(ejDisp == 0){
            Salida error = new Salida(119);
            System.out.println(error.toString());
            //error 119 y eso
        }else if(numEjer > ejDisp){
            pais.addEjercitos(ejDisp);
            pais.getJugador().setEjercitos_disponibles(0); // si se quieren asignar mas de los disponibles se asignan solo estos y pasa a haber 0 disponibles
            String exito = "{\n pais: \"" + pais.getNombre() + "\",\n jugador: \"" + 
                    pais.getJugador().getNombre() + "\",\n numeroEjercitosAsignados: " 
                    + ejDisp + ",\n numeroEjercitosTotales: " + pais.getEjercitos() + ",\n paisesOcupadosContinente: " + pais.getContinente().printPaisesEjer();
            System.out.println(exito);
            Salida salida = new Salida();
            salida.imprimirArchivo(exito);
        }else { // caso todo correcto
            pais.addEjercitos(numEjer);
            pais.getJugador().setEjercitos_disponibles(ejDisp - numEjer);
            String exito = "{\n pais: \"" + pais.getNombre() + "\",\n jugador: \"" + 
                    pais.getJugador().getNombre() + "\",\n numeroEjercitosAsignados: " 
                    + numEjer + ",\n numeroEjercitosTotales: " + pais.getEjercitos() + ",\n paisesOcupadosContinente: " + pais.getContinente().printPaisesEjer() + "\n}";
            System.out.println(exito);
            Salida salida = new Salida();
            salida.imprimirArchivo(exito);
        }
    }



    public int contarJugadores(ArrayList<Jugador> jugadores){
        int contador=0;

        for(Jugador j:jugadores){
            contador++;
        }
        return contador;
    }
    public void repartirEjercitos(){

        ArrayList<Continente> continentesOrdenadosMenosFronteras = new ArrayList<>();
        int numJugadores;
        // Asignar numero de tropas que tendrá cada jugador en funcion del numero de jugadores
        switch (numJugadores = contarJugadores(this.jugadores)){ // esto creo que solo va en la funcion de migueloh

            case 3:
                for (Jugador j: jugadores){
                    j.setTropas(35);
                    j.setEjercitos_disponibles(35);
                }
                break;

            case 4:
                for (Jugador j: jugadores){
                    j.setTropas(30);
                    j.setEjercitos_disponibles(30);
                }
                break;

            case 5:
                for (Jugador j: jugadores){
                    j.setTropas(25);
                    j.setEjercitos_disponibles(25);
                }
                break;

            case 6:
                for (Jugador j: jugadores){
                    j.setTropas(20);
                    j.setEjercitos_disponibles(20);
                }
                break;

        }

        // Asignar un ejercito a cada pais:
        for(Pais p:paises){
            p.setEjercitos(1);
            p.getJugador().setEjercitos_disponibles(p.getJugador().getTropas() - 1); // se le resta 1 a los ejercitos disponibles del jugador al que pertenece ese pais
        }


        for(int k=0; k<6; k++){
            Continente cAux = new Continente();
            for (int i = 0; i< continentes.size(); i++){
                if(continentesOrdenadosMenosFronteras.contains(continentes.get(i))) i++;
                if(continentes.get(i).fronterasContinente() < cAux.fronterasContinente()) cAux = continentes.get(i);

            }
            continentesOrdenadosMenosFronteras.add(cAux);
        }



        for(Jugador j: jugadores){ // J será el jugador al que se le iran aplicando los algoritmos

            int ejercitosDisponibles = 0;
            boolean hayEjercitos = false;
            Boolean R1 = false;
            Boolean R2 = false;
            Boolean R3 = false;
            Boolean R4 = false;
            Boolean R5 = false;
            Boolean R6 = false;
            Boolean R7 = false;
            Boolean R8 = false;
            Jugador jugadorMenosFronteras = new Jugador(); // jugador que tiene mas paises en el continente con menos fronteras
            Continente masPaisesOcupados = new Continente();
            Continente menosFronteras = new Continente();
            ArrayList<Continente> contCumpR1 = new ArrayList<>(); // continentes que cumplen la condicion R1
            ArrayList<Continente> contCumpR4 = new ArrayList<>(); // continentes que cumplen la condicion R4
            ArrayList<Continente> mismoPorcPaisOc = new ArrayList<>();


            numJugadores = contarJugadores(this.jugadores);
            int numPaisesJugEnC = 0; // numero de paises que tiene un jugador en cada continete

// R1
            for(Continente c:continentes) {
                numPaisesJugEnC = j.calcularNumPaisesEnC(c);
                if (numPaisesJugEnC > (c.getNumPaises() / 2)) { // Mismo jugador tiene mas de la mitad de los paises de un continente
                    contCumpR1.add(c); // Se añade continente
                }
            }

            if(contCumpR1.size() == 1){ // Solo un continente cumple la condicion
                for (Pais p:contCumpR1.get(0).getPaises()) { // en cada pais del continente se asigna el dato dado en el enunciado
                    if (p.getJugador().equals(j)) {
                        if (contCumpR1.get(0).getNombre().equals("Oceanía") || contCumpR1.get(0).getNombre().equals("Asur")) { // Cambia 1.5 o 1 dependiendo del continente
                            p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                            R1 = true;
                        } else {
                            p.addEjercitos((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC)));
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC))));
                            R1 = true;
                        }
                    }

                }
                // R2
            }else if(contCumpR1.size() > 1){ // mas de 1 continente cumple r1

                for(int i=0; i<contCumpR1.size(); i++){
                    if(i==0)  masPaisesOcupados = contCumpR1.get(i); // Primera iteracion
                    if(contCumpR1.get(i).porcentajePaisesOcupados() > masPaisesOcupados.porcentajePaisesOcupados()){
                        masPaisesOcupados = contCumpR1.get(i); // Si el siguiente continente de la lista tiene mas porcentaje de paises asignados este es el nuevo que mas tiene
                    }
                }
                mismoPorcPaisOc.add(masPaisesOcupados);
                for(int i=0; i < contCumpR1.size(); i++){ // Hay que comprobar si hay varios con el mismo porcentaje de ocupados
                    if(contCumpR1.get(i).porcentajePaisesOcupados() == masPaisesOcupados.porcentajePaisesOcupados()){
                        mismoPorcPaisOc.add(contCumpR1.get(i));
                    }
                }
                if(mismoPorcPaisOc.size()==1){ // Asignar ejercitos al continente que tiene el mayor porcentaje de paises ocupados
                    for (Pais p:mismoPorcPaisOc.get(0).getPaises()) { // recorremos paises de ese continente
                        if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                            if (mismoPorcPaisOc.get(0).getNombre().equals("Oceanía") || mismoPorcPaisOc.get(0).getNombre().equals("Asur")) { // Cambia 1.5 o 1 dependiendo del continente
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                                R2 = true;
                            } else {
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC))));
                                R2 = true;
                            }
                        }
                    }


                }else if (mismoPorcPaisOc.size()>1){ // Si hay varios con el mismo porcentaje de asignados hay que comprobar fronteras

                    for(int i=0; i<mismoPorcPaisOc.size(); i++){
                        if(i==0){
                            menosFronteras = mismoPorcPaisOc.get(i); // Primera iteracion
                        }
                        if(mismoPorcPaisOc.get(i).fronterasContinente() < menosFronteras.fronterasContinente()){
                            menosFronteras = mismoPorcPaisOc.get(i); // Si el siguiente continente de la lista tiene menos fronteras este es el nuevo que menos tiene
                        }
                    }

                    // Asignar ejercitos al pais que menos fronteras tiene
                    for (Pais p:menosFronteras.getPaises()) { // recorremos paises de ese continente
                        if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                            if (menosFronteras.getNombre().equals("Oceanía") || menosFronteras.getNombre().equals("Asur")) { // Cambia 1.5 o 1 dependiendo del continente
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                                R2 = true;
                            } else {
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC))));
                                R2 = true;
                            }
                        }
                    }
                }
            }
            // R3
            if(j.getEjercitos_disponibles() > 0){
                hayEjercitos = true;
            }

            // AHora si R3
            if((R1 || R2) && hayEjercitos){
                hayEjercitos = false; // Para recalcular para r6
                R3 = true;

                int paisesUnEjercito = 0;
                for (Pais p:j.getPaises()){
                    if(p.getEjercitos() == 1) paisesUnEjercito++;
                }

                if(paisesUnEjercito <= j.getEjercitos_disponibles()){ // Si hay mas o los mismos ejercitos disponibles que paises con un ejercito se asigna uno y ya
                    for (Pais p:j.getPaises()){
                        if(p.getEjercitos() == 1){
                            p.addEjercitos(1);
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                        }
                    }

                }else{ // mas paises con un ejercito que ejercitos disponibles
                    // Ir a continentes con menos fronteras
                    while (j.getEjercitos_disponibles() > 0){
                        for(Continente c: continentesOrdenadosMenosFronteras){
                            for(Pais p: c.getPaises()){
                                if(p.getJugador().equals(j)){
                                    if(p.getEjercitos() == 1){
                                        p.addEjercitos(1);
                                        j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                                    }
                                }
                            }

                        }
                    }
                }
            } // FIN R3

            // R4

            if(!R1 && !R2 && !R3){

                for(Continente c:continentes) {
                    numPaisesJugEnC = j.calcularNumPaisesEnC(c);
                    if ((numPaisesJugEnC > (c.getNumPaises() / 4)) && (numPaisesJugEnC < (c.getNumPaises() / 2))) { // Mismo jugador tiene mas de la mitad de los paises de un continente
                        contCumpR4.add(c); // Se añade continente
                    }
                }
                if(contCumpR4.size() == 1){ // Solo un continente cumple la condicion
                    for (Pais p:contCumpR4.get(0).getPaises()) { // en cada pais del continente se asigna el dato dado en el enunciado
                        if (p.getJugador().equals(j)) {
                            p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                            R4 = true;
                        }

                    }


                } // FIN R4
                // R5
                else if(contCumpR4.size() > 1){ // mas de 1 continente cumple r1

                    for(int i=0; i<contCumpR4.size(); i++){
                        if(i==0)  masPaisesOcupados = contCumpR4.get(i); // Primera iteracion
                        if(contCumpR4.get(i).porcentajePaisesOcupados() > masPaisesOcupados.porcentajePaisesOcupados()){
                            masPaisesOcupados = contCumpR4.get(i); // Si el siguiente continente de la lista tiene mas porcentaje de paises asignados este es el nuevo que mas tiene
                        }
                    }
                    mismoPorcPaisOc.add(masPaisesOcupados);
                    for(int i=0; i < contCumpR4.size(); i++){ // Hay que comprobar si hay varios con el mismo porcentaje de ocupados
                        if(contCumpR4.get(i).porcentajePaisesOcupados() == masPaisesOcupados.porcentajePaisesOcupados()){
                            mismoPorcPaisOc.add(contCumpR4.get(i));
                        }
                    }
                    if(mismoPorcPaisOc.size()==1){ // Asignar ejercitos al continente que tiene el mayor porcentaje de paises ocupados
                        for (Pais p:mismoPorcPaisOc.get(0).getPaises()) { // recorremos paises de ese continente
                            if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC))));
                                R5 = true;
                            }
                        }


                    }else if (mismoPorcPaisOc.size()>1){ // Si hay varios con el mismo porcentaje de asignados hay que comprobar fronteras

                        for(int i=0; i<mismoPorcPaisOc.size(); i++){
                            if(i==0){
                                menosFronteras = mismoPorcPaisOc.get(i); // Primera iteracion
                            }
                            if(mismoPorcPaisOc.get(i).fronterasContinente() < menosFronteras.fronterasContinente()){
                                menosFronteras = mismoPorcPaisOc.get(i); // Si el siguiente continente de la lista tiene menos fronteras este es el nuevo que menos tiene
                            }
                        }

                        // Asignar ejercitos al pais que menos fronteras tiene
                        for (Pais p:menosFronteras.getPaises()) { // recorremos paises de ese continente
                            if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC))));
                                R5 = true;

                            }
                        }
                    }
                } // fin R5
            }
            // R6
            if(j.getEjercitos_disponibles() > 0){
                hayEjercitos = true;
            }
            if((R5 || R4) && hayEjercitos){
                R6 = true;

                int paisesUnEjercito = 0;
                for (Pais p:j.getPaises()){
                    if(p.getEjercitos() == 1) paisesUnEjercito++;
                }

                if(paisesUnEjercito <= j.getEjercitos_disponibles()){ // Si hay mas o los mismos ejercitos disponibles que paises con un ejercito se asigna uno y ya
                    for (Pais p:j.getPaises()){
                        if(p.getEjercitos() == 1){
                            p.addEjercitos(1);
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                        }
                    }

                }else{ // mas paises con un ejercito que ejercitos disponibles
                    // Ir a continentes con menos fronteras
                    while (j.getEjercitos_disponibles() > 0){
                        for(Continente c: continentesOrdenadosMenosFronteras){
                            for(Pais p: c.getPaises()){
                                if(p.getJugador().equals(j)){
                                    if(p.getEjercitos() == 1){
                                        p.addEjercitos(1);
                                        j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                                    }
                                }
                            }

                        }
                    }
                }
            }
            // Fin R6





        }
    }
    }


