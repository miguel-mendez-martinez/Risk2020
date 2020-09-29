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
public class Jugador {
    private String nombre;
    private String color;
	
    public Jugador(){}

    public Jugador(String nombre, String color){
	this.nombre= nombre;
        this.color= color;
    }

    public Jugador(String nombre){
            this.nombre= nombre;
            this.color= "rojo";
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
    @Override
    public String toString(){
        String texto="\nNombre: "+ this.nombre + "\nColor: " + this.color;
        return texto;
    }

}
