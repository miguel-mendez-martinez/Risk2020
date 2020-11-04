/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author migue
 */

import java.util.ArrayList;


public class Jugador {
    
    private String nombre;
    private String color;
    private int tropas;
    private int ejercitos_disponibles;
    private ArrayList<Pais> paises;
    private Mision mision;
    private int ejerRearme;
	
    // Constructores
    public Jugador(){
        
    }
    
    public Jugador(String nombre){
        this.nombre= nombre;
        this.color= "rojo";
    }

    public Jugador(String nombre, String color){
        this.nombre= nombre;
        this.color= color;
        this.paises = new ArrayList<>();
        this.mision = null; //este valor lo usaremos para comprobar a la hora de asignarle una mision
        this.ejerRearme = 0;
    }
    
    // setters & getters


    public void setEjercitos_disponibles(int ejercitos_disponibles) {
        this.ejercitos_disponibles = ejercitos_disponibles;
    }

    public int getEjercitos_disponibles() {
        return ejercitos_disponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    public Mision getMision() {
        return mision;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String Color){
        this.color = Color ;
    }
    
    public int getTropas(){
        return tropas;
    }
    
    public void setTropas(int tropas){
        this.tropas = tropas;
    }
    
    public ArrayList<Pais> getPaises(){
        return paises;
    }
    
    public void setPaises(Pais pais){
        this.paises.add(pais);
    }

    public String printColorNom(){
        String texto = "{\n nombre: \"" + this.nombre + "\",\n color: \"" + this.color + "\"\n}";
        return texto;
    }   
    
    public String printColor(){

        if("AMARILLO".equals(this.color)) return "\033[43m" + "\033[1;90m";
        else if("AZUL".equals(this.color)) return "\033[44m" + "\033[1;90m";
        else if("CYAN".equals(this.color)) return "\033[46m" + "\033[1;90m";
        else if("ROJO".equals(this.color)) return "\033[41m" + "\033[1;90m";
        else if("VERDE".equals(this.color)) return "\033[42m" + "\033[1;90m";
        else if("VIOLETA".equals(this.color)) return "\033[45m" + "\033[1;90m";
        else return " ";
        // mirar codigo de error o algo para ver que hacer si color es invalido como migueloh else System.out.println();
    }

    public String printNomEjerR(){
        String texto = "{\n nombre: \"" + this.nombre + "\",\n numeroEjercitosRearmar: " + this.ejerRearme + "\n}";
        return texto;
    }

    
    @Override
    public String toString(){
        //String result = "";
        //for (Pais pais ; this.paises){
          //  result += pais.toString();
        //}

        
        String texto="\nNombre: "+ this.nombre + "\nColor: " + this.color+ "\nPaises: " + /*result*/ this.paises + "\nTropas Jugador: " +this.tropas;
        return texto;
    }

}
