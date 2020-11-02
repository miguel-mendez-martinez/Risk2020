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
    Mapa mapa;
    /**
     * 
     */
    public Menu() {
        // Inicialización de algunos atributos
        // Iniciar juego
         // Con fichero:
        this.mapa = null;
        Mision mision;
        jugadores = new ArrayList<>();
        paises = new ArrayList<>();
        int checker = 0;
        String orden= null;
        BufferedReader bufferLector= null;
        
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
                                if(partes[1].equals("jugadores")) { 
                                    crearJugador(new File(partes[2]));
                                    if(checker < 2){
                                        checker = 2;
                                    }
                                } else {
                                    crearJugador(partes[1], partes[2]);
                                    if(checker < 2){
                                        checker = 2;
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
                                Salida error = new Salida(106);
                                System.out.println(error.toString());
                            }
                        }
                        break;
                    case "asignar":
                        if(partes.length!=3) {
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }else if(partes[1].equals("misiones")){
                            if(checker >= 2){
                                if(partes[1].equals("misiones")){
                                    asignarMisiones(new File(partes[2]));
                                    if(checker < 3){
                                        checker = 3;
                                    }
                                }
                            }else{
                                Salida error = new Salida(105);
                                System.out.println(error.toString());
                            }
                            //primero tenemos que asignar misiones antes de paises
                        }else if(partes[1].equals("paises")){
                            if(checker >= 3){
                                asignarPaises(new File(partes[2]));
                                if(checker < 4){
                                        checker = 4;
                                    }
                            }else{
                                Salida error = new Salida(118);
                                System.out.println(error.toString());
                            }
                        }else if(partes[2].charAt(0) == 'M'){
                            //Estas operaciones por ahora no cambian el checker ya que no asignan cosas a todos los jugadores
                            if(checker >= 2){
                                if(partes[2].charAt(1) == '1' ||
                                    partes[2].charAt(1) == '2' ||
                                    partes[2].charAt(1) == '3' ||
                                    partes[2].charAt(1) == '4'){
                                    asignarMisiones(partes[1], partes[2]);
                                }else{
                                    if(checker >= 3){
                                        asignarPaises(partes[1], partes[2]);
                                    }else{
                                        Salida error = new Salida(118);
                                        System.out.println(error.toString());
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
    
    public Pais existePais(ArrayList<Pais> paises, String pais){ // mismo que nj
        if(paises.isEmpty()==true){
            
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

            System.out.println("Array final de jugadores tras la lectura completa del archivo de Paises:");
            for (int i=0;i<jugadores.size();i++) {
                System.out.println(jugadores.get(i).toString());
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
                                String exito = "{\n\tnombre: " + nombreJugador +"\n\tpaís: " + nombrePais + "\n\tcontinente: " + pais.getContinente().getNombre() + "\n\t" + fronteras + "\n}";
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

     // mirar en casa tengo el cerebro frito

    public void asignarEjercitos(String nombrePais, int ejercitos){
        
    }

    /**
     * 
     */
    public void crearMapa() {
        // Código necesario para crear el mapa
        this.mapa = new Mapa();
        this.paises = this.mapa.getPaises();
        this.continentes = this.mapa.getContinentes();
        //System.out.println(mapa);
    }
    
    public void obtenerFronteras(String npais){
    
        int flagPais = 0; // flag para controlar si el pais que se inserto existe o no
        for(Pais pais:paises){
            if(pais.getAbreviatura().equals(npais)){
                flagPais = 1;
                String fronteras = "";
                for (int i=0;i<pais.getFronteras().size();i++) {
                    if(i == 0){
                        fronteras += "frontera: [ " + pais.getFronteras().get(i).printNombre() + ", ";
                    }else if(i==(pais.getFronteras().size())-1){
                        fronteras += pais.getFronteras().get(i).printNombre() + " ]";
                    }else{
                        fronteras += pais.getFronteras().get(i).printNombre() + ", ";
                    }
                    
                }
                System.out.println(fronteras);
                Salida salida = new Salida();
                salida.imprimirArchivo(fronteras);
            }          
        }
        if(flagPais == 0){ // Error      
            Salida error = new Salida(109);
            System.out.println(error.toString());      
        } 
    }
    
    public void obtenerContinente(String npais){
    
        int flagPais = 0; // flag para controlar si el pais que se inserto existe o no
        for(Pais pais:paises){
        
            if(pais.getAbreviatura().equals(npais)){
                flagPais = 1;
                String continente = "{ continente:  \"" + pais.getContinente().getNombre() + "\" }";
                System.out.println(continente);
                Salida salida = new Salida();
                salida.imprimirArchivo(continente);
            }          
        }
        if(flagPais == 0){ // Error      
            Salida error = new Salida(109);
            System.out.println(error.toString());      
        }      
    }
    
    public void obtenerColor(String npais){
    
        int flagPais = 0;// flag para controlar si el pais que se inserto existe o no
        
        
        for(Pais pais:paises){
        
            if(pais.getAbreviatura().equals(npais)){
                flagPais = 1;
                String color = pais.getContinente().getColor();
                if((color.equals("AMARILLO") || color.equals("ROJO") || color.equals("AZUL")
                || color.equals("CYAN") || color.equals("VERDE") || color.equals("VIOLETA"))){
                    String exito = "{ color: \"" + color + "\" }";
                    System.out.println(exito);
                    Salida salida = new Salida();
                salida.imprimirArchivo(exito);
                }else{
                    Salida error = new Salida(100);
                    System.out.println(error.toString());
                }
            }          
        }
        if(flagPais == 0){ // Error      
            Salida error = new Salida(109);
            System.out.println(error.toString());       
        }      
    }

    public void obtenerPaises(String abCont){

        int flagContinente = 0;
        for(Continente c:continentes){
            if(c.getNombre().equals(abCont)){
                String paises = c.printPaises();
                System.out.println(paises);
                Salida salida = new Salida();
                salida.imprimirArchivo(paises);
                flagContinente = 1;//para que no imrpima el error
            }
        }
        if(flagContinente == 0){
            Salida error = new Salida(102);
            System.out.println(error.toString()); 
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
            System.out.println(jugador.printColorNom());
            Salida salida = new Salida();
            salida.imprimirArchivo(jugador.printColorNom());
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
                    System.out.println(jugador.printColorNom());
                    Salida salida = new Salida();
                    salida.imprimirArchivo(jugador.printColorNom());
                }
        }
    }
}
