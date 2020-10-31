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
    private ArrayList<Pais> paises;
    private Mision mision;
	
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
    }

    /*public Jugador(String nombre, String color, ArrayList<Pais> paises){
	this.nombre= nombre;
        this.color= color;
        this.paises = paises;
    }
    public Jugador(String nombre, String color, ArrayList<Pais> paises, int tropas){
	this.nombre= nombre;
        this.color= color;
        this.paises = paises;
        this.tropas = tropas;
    }*/
    
    // setters & getters
    
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
