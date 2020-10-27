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
    String eleccion;
    Mapa mapa;
    /**
     * 
     */
    public Menu() {
        // Inicialización de algunos atributos
        // Iniciar juego
         // Con fichero:
        this.mapa = null;
        jugadores = new ArrayList<>();
        paises = new ArrayList<>();
        int checker = 0;
        String orden= null;
        BufferedReader bufferLector= null;
        try {
            File fichero=  new File("comandos.csv"); // fichero será comandos.csv
            FileReader lector= new FileReader(fichero); // Creacion de objeto que lee "fichero"
            bufferLector= new BufferedReader(lector); // Usamos objeto de la clase BufferedReader ya que permite leer lineas completas
            while((orden= bufferLector.readLine())!=null) { // Mientras las líneas no estén en blanco:
                System.out.println("\n$> " + orden);
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
                                    checker +=1;//tras crear el mapa, los comandos de maxima prioridad han terminado, por lo que se pueden ejecutar los siguientes
                                }else{
                                    String error = "{\n\tCodigo de error 107. \n\tDescripcion: El mapa ya ha sido creado.\n}";
                                    System.out.println(error);
                                }
                            }else{
                                System.out.println("\nComando incorrecto.");
                            }
                        }else if(partes.length==3) {
                            if(checker >= 1){
                                if(partes[1].equals("jugadores")) { 
                                crearJugador(new File(partes[2]));
                                } else {
                                    crearJugador(partes[1], partes[2]);
                                }
                                System.out.print("Lista de jugadores tras el comando: $>" + orden);
                                for (int i=0;i<jugadores.size();i++) {
                                    System.out.println(jugadores.get(i).toString());
                                }
                                checker += 1; //tras crear los jugadores, el resto de comandos de prioridad 2 ya podran ser ejecutados
                            }else{
                                String error = "{\n\tCodigo de error 106. \n\tDescripcion: El mapa no esta creado.\n}";
                                System.out.println(error);
                            }
                            
                        }else {
                            System.out.println("\nComando incorrecto.");
                        }
                        break;
                    case "ver":
                        if(partes[1].equals("mapa")){
                            if(checker == 1){
                                this.mapa.printMapa();
                            }else{
                                String error = "{\n\tCodigo de error 106. \n\tDescripcion: El mapa no esta creado.\n}";
                                System.out.println(error);
                            }
                        }
                        break;
                    case "asignar":
                        if(partes.length!=3) {
                            System.out.println("\nComando incorrecto.");
                        }else if(partes[1].equals("misiones")){
                            if(checker >= 2){
                                if(partes[1].equals("misiones")){
                                    asignarMisiones(new File(partes[2]));
                                }else{
                                    asignarMisiones(partes[1], partes[2]);
                                }
                                checker += 1;
                            }else{
                                String error = "{\n\tCodigo de error 105. \n\tDescripcion: Los jugadores no estan creados.\n}";
                                System.out.println(error);
                            }
                            //primero tenemos que asignar misiones antes de paises
                        }else if(partes[1].equals("paises")) {
                            
                            asignarPaises(new File(partes[2]));
                        } else {
                            asignarPaises(partes[1], partes[2]);
                        }
                        break;
                        
                    case "obtener": // Comandos sobre el mapa
                        if(checker == 1){
                            if(partes.length!=3) {
                                System.out.println("\nComando incorrecto.");
                            
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
                            String error = "{\n\tCodigo de error 106. \n\tDescripcion: El mapa no esta creado.\n}";
                            System.out.println(error);
                        }
                        break;
                    default:
                        System.out.println("\nComando incorrecto.");
                }
            }
        } catch(Exception excepcion) {
            excepcion.printStackTrace();
        }
    }

    /**
     * 
     * @param file 
     */
    
    public void asignarMisiones(File file){
        
    }
    public void asignarMisiones(String Jugador, String Codigo){
        
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
                asignarPaises(nombrePais, nombreJugador);

            }

            System.out.println("Array final de jugadores tras la lectura completa del archivo:");
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
        Pais pais = new Pais();
        Jugador jugador = new Jugador();

        for(Jugador j : this.jugadores){
            //primero debemos comprobar que el jugador exista dentro del array de jugadores
            if(j.getNombre().equals(nombreJugador)==true){
                jugador = j;
                checkJug++;//comprobante de que el jugador exista
            }
        }
        if(checkJug == 0){
                String error = "{\n\tCodigo de error 103. \n\tDescripcion: Jugador no existente.\n}";
                System.out.println(error);
            }else{
            //Si el jugador existe lo siguiente es comprobar que exista el pais
                for(Pais p : this.paises){
                    if(p.getAbreviatura().equals(nombrePais)==true){
                        pais = p;
                        checkPais++;//comprobante de que el pais exista
                    }
                }
                if(checkPais == 0){
                    String error = "{\n\tCodigo de error 109. \n\tDescripcion: Pais no existente.\n}";
                    System.out.println(error);
                }else{ // Existe el pais y existe el jugador, comprobar que ese pais no este asignado ya
                    //pasamos a ver si los paises ya estan en el jugador etc etc etc

                    for(Pais p:paises){
                        for(Jugador j:jugadores){
                            if(p.getJugador().equals(j) == true) checkPaisAsig=1;
                        }
                    }
                    if(checkPaisAsig == 0){ // El pais no está asignado a ningun jugador, por lo que asignamos e imprimimos

                        pais.setJugador(jugador);
                        jugador.setPaises(pais);
                        // Falta imprirmir fronteras bien
                        String exito = "{\n\tnombre: " + nombreJugador +"\n\tpaís: " + nombrePais + "\n\tcontinente: " + pais.getContinente().getNombre() + "frontera:\n}";
                        System.out.println(exito);

                    }else{ // COdigo de error pais asignado
                        String error = "{\n\tCodigo de error 113. \n\tDescripcion: El país ya está asignado.\n}";
                        System.out.println(error);
                    }
// Queda pendiente error de misiones

                }
            }

     // mirar en casa tengo el cerebro frito

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
                System.out.println("Las fronteras del pais " + pais.getNombre() + " son:");
              
                for(Pais frontera : pais.getFronteras()){
                    System.out.println(" - Frontera: " + frontera.getNombre());        
                }  
            }          
        }
        if(flagPais == 0){ // Error      
            String error = "{\n\tCodigo de error 109. \n\tDescripcion: El pais no existe.\n}";
            System.out.println(error);      
        }      
    }
    
    public void obtenerContinente(String npais){
    
        int flagPais = 0; // flag para controlar si el pais que se inserto existe o no
        for(Pais pais:paises){
        
            if(pais.getAbreviatura().equals(npais)){
                flagPais = 1;
                System.out.println("El continente al que pertenece el país " + pais.getNombre() + " es: " + pais.getContinente().getNombre());
                
            }          
        }
        if(flagPais == 0){ // Error      
            String error = "{\n\tCodigo de error 109. \n\tDescripcion: El pais no existe.\n}";
            System.out.println(error);      
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
                    System.out.println("El color del país " + pais.getNombre() + " es: " + color);
                }else System.out.println("{\n\tCodigo de error 100. \n\tDescripcion: Color no permitido\n}\n");
            }          
        }
        if(flagPais == 0){ // Error      
            String error = "{\n\tCodigo de error 109. \n\tDescripcion: El pais no existe.\n}";
            System.out.println(error);       
        }      
    }

    public void obtenerPaises(String abCont){

        int flagContinente = 0;
        for(Continente c:continentes){
            if(c.getNombre().equals(abCont)){
                c.toString();
                flagContinente = 1;
            }
        }
        if(flagContinente == 0) System.out.println("{\n\tCodigo de error 102. \n\tDescripcion: El continente no existe.\n}");
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
            System.out.println("Se intento crear: " + nombre + " " + color);
            String error = "{\n\tCodigo de error 100. \n\tDescripcion: Color no permitido\n}";
            System.out.println(error);
        }
        else if(jugadores.isEmpty()==true){ //si esta vacia metemos directo
            Jugador jugador= new Jugador(nombre, color);
            jugadores.add(jugador); 
        }else{
            for (int i=0;i<jugadores.size();i++) {
                if(jugadores.get(i).getNombre().equals(nombre)==true){
                    String error = "{\n\tCodigo de error 104. \n\tDescripcion: Jugador ya creado.\n}";
                    System.out.println(error);
                    flag++;
                }
                else if(jugadores.get(i).getColor().equals(color)){
                    String error = "{\n\tCodigo de error 114. \n\tDescripcion: Color ya asignado.\n}";
                    System.out.println(error);
                    flag++;
                }
            }
            if(flag==0){
                    Jugador jugador= new Jugador(nombre, color);
                    jugadores.add(jugador);
                }
        }
    }
}
