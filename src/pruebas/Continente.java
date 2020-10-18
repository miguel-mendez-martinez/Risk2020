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


public class Continente {
    
    private String nombre;
    private int bonus;
    private ArrayList<Pais> paises;
    private String color;
    
    // Constructores, recordar siempre reservar memoria para el array
    
    public Continente(){
    }
    public Continente(String nombre, String color){
        this.paises = new ArrayList<>(); //inicio el array
        //en vez de esta mierda tenemos que hacer que lea un fichero donde esten las asignaciones de los continentes y paises y ver que esta bien
        if( nombre.equals("África") ||
            nombre.equals("América del Norte") ||
            nombre.equals("América del Sur") ||
            nombre.equals("Asia") ||
            nombre.equals("Europa") ||
            nombre.equals("Oceanía")) {
            
            this.nombre = nombre;
                   
        }
        this.color = color;
    }
    
    // setters & getters
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        if( nombre.equals("África") ||
            nombre.equals("América del Norte") ||
            nombre.equals("América del Sur") ||
            nombre.equals("Asia") ||
            nombre.equals("Europa") ||
            nombre.equals("Oceanía")) {
            
            this.nombre = nombre;            
        }
    }
    
    public int getBonus(){
        return bonus;
    }
    
    public void setBonus(int bonus){
        this.bonus = bonus;
    }
    
    public ArrayList<Pais> getPaises(){
        return paises;
    }
    
    public void setPais(Pais pais){
        this.paises.add(pais);
    }

    public String getColor() {
        return color;
    } 
    public String printColor(){

        if(this.color == "AMARILLO") return "\033[0;103m" + "\033[1;90m";
        else if(this.color == "AZUL") return "\033[0;104m" + "\033[1;90m";
        else if(this.color == "CYAN") return "\033[0;106m" + "\033[1;90m";
        else if(this.color == "ROJO") return "\033[0;101m" + "\033[1;90m";
        else if(this.color == "VERDE") return "\033[0;102m" + "\033[1;90m";
        else if(this.color == "VIOLETA") return "\033[10;95m" + "\033[1;90m";
        // mirar codigo de error o algo para ver que hacer si color es invalido como migueloh else System.out.println();
    }

    public String toString(){
        String paisCont = "";
        for (int i=0;i<paises.size();i++) {
            paisCont += paises.get(i);
        }
        String texto = "\n" + this.nombre + " con paises: " + paisCont;
        return texto;
    }
}
