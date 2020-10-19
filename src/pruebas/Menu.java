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
    String eleccion;
    /**
     * 
     */
    public Menu() {
        // Inicialización de algunos atributos
        // Iniciar juego
         // Con fichero:
        jugadores = new ArrayList<>();
        String orden= null;
        BufferedReader bufferLector= null;
        try {
            File fichero=  new File("comandos.csv"); // fichero será comandos.csv
            FileReader lector= new FileReader(fichero); // Creacion de objeto que lee "fichero"
            bufferLector= new BufferedReader(lector); // Usamos objeto de la clase BufferedReader ya que permite leer lineas completas
            while((orden= bufferLector.readLine())!=null) { // Mientras las líneas no estén en blanco:
                System.out.println("$> " + orden);
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
                                // crearMapa es un método de la clase Menú desde el que se puede invocar
                                // a otros métodos de las clases que contienen los atributos y los métodos
                                // necesarios para realizar esa invocación 
                                crearMapa();
                            }else{
                                System.out.println("\nComando incorrecto.");
                            }
                        }else if(partes.length==3) {
                            if(partes[1].equals("jugadores")) { 
                                crearJugador(new File(partes[2]));
                            } else {
                                crearJugador(partes[1], partes[2]);
                            }
                            for (int i=0;i<jugadores.size();i++) {
                                System.out.println(jugadores.get(i).toString());
                            }
                        }else {
                            System.out.println("\nComando incorrecto.");
                        }
                        break;
                    case "asignar":
                        if(partes.length!=3) {
                            System.out.println("\nComando incorrecto.");
                        } else if(partes[1].equals("paises")) {
                            // asignarPaises es un método de la clase Menu que recibe como entrada el fichero
                            // en el que se encuentra la asignación de países a jugadores. Dentro de este
                            // método se invocará a otros métodos de las clases que contienen los atributos
                            // y los métodos necesarios para realizar esa invocación
                            asignarPaises(new File(partes[2]));
                        } else {
                            asignarPaises(partes[1], partes[2]);
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
    public void asignarPaises(String nombrePais, String nombreJugador) {
        // Código necesario para asignar un país a un jugador



     // mirar en casa tengo el cerebro frito

    }

    /**
     * 
     */
    public void crearMapa() {
        // Código necesario para crear el mapa
        Mapa mapa = new Mapa();
        //System.out.println(mapa);
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
            String error = "{\n\tCodigo de error 100. \n\tDescripcion: Color no permitido\n}\n";
            System.out.println(error);
        }
        else if(jugadores.isEmpty()==true){ //si esta vacia metemos directo
            Jugador jugador= new Jugador(nombre, color);
            jugadores.add(jugador); 
        }else{
            for (int i=0;i<jugadores.size();i++) {
                if(jugadores.get(i).getNombre().equals(nombre)==true){
                    String error = "{\n\tCodigo de error 104. \n\tDescripcion: Jugador ya creado.\n}\n";
                    System.out.println(error);
                    flag++;
                }
                else if(jugadores.get(i).getColor().equals(color)){
                    String error = "{\n\tCodigo de error 114. \n\tDescripcion: Color ya asignado.\n}\n";
                    System.out.println(error);
                    flag++;
                }
            }
            if(flag==0){
                    Jugador jugador= new Jugador(nombre, color);
                    jugadores.add(jugador);
                }
        }
        /*System.out.println("Array de jugadores despues de la insercion/error:");
        for (int i=0;i<jugadores.size();i++) {
            System.out.println(jugadores.get(i).toString());
        }*/
    }
}
