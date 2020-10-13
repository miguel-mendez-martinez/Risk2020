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


public class Pais {
    
    private String nombre;
    private int ejercitos;
    private String color; //el color que tiene ya viene de continente this.getContinente.getColor para sacar el color del pais
    private String abreviatura;
    private Jugador jugador;
    private Continente continente;
    private ArrayList<Pais> fronteras;
    
    // Constructores
    
    public Pais(){
    }
    
    public Pais(String nombre){
        this.nombre=nombre;
        this.ejercitos=0;
    }
    
    /*public Pais(String nombre, String color){
        this.nombre=nombre;
        this.color=color;
    }*/
    
    
    
    public Pais(String nombre, String color){
        this.nombre=nombre;
        this.color=color;
        this.ejercitos=0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public int getEjercitos() {
        return ejercitos;
    }
    
    public void setEjercitos(int ejercitos){
        this.ejercitos = ejercitos ;
    }

    public Continente getContinente() {
        return continente;
    }
    public String getColor(){
        return this.getContinente().getColor();
    }
    @Override
    public String toString(){
        String texto="\n\t "+ this.nombre + ", Tropas en pais: " + this.ejercitos;
        return texto;
    }
}
