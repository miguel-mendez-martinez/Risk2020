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
    private String color;
    
    // Constructores
    
    public Pais(){
    }
    
    public Pais(String nombre){
        this.nombre=nombre;
        this.ejercitos=0;
    }
    
    public Pais(String nombre, String color){
        this.nombre=nombre;
        this.color=color;
    }
    
    public Pais(String nombre, int ejercitos){
        this.nombre=nombre;
        this.ejercitos=ejercitos;
    }
    
    public Pais(String nombre, String color, int ejercitos){
        this.nombre=nombre;
        this.color=color;
        this.ejercitos=ejercitos;
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
    
    
    @Override
    public String toString(){
        String texto="\n\tNombre: "+ this.nombre + ", Tropas en pais: " + this.ejercitos;
        return texto;
    }
}
