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
	
    public Jugador(){}

    public Jugador(String nombre, String color){
	this.nombre= nombre;
        this.color= color;
    }

    public Jugador(String nombre){
            this.nombre= nombre;
            this.color= "rojo";
    }
    public Jugador(String nombre, String color, ArrayList<Pais> paises){
	this.nombre= nombre;
        this.color= color;
        this.paises = paises;
    }
    public String getNombre() {
            return nombre;
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
    public void setTropas(int tropas){
        
    }
    public int getTropas(){
        return tropas;
    }
    public void setPaises(ArrayList<Pais> paises){
        this.paises = paises;
    }
    public void getPaises(){
        
    }
    @Override
    public String toString(){
        String texto="\nNombre: "+ this.nombre + "\nColor: " + this.color+ "\nPaises: " + this.paises;
        return texto;
    }
//alberto maricon jaja
}
