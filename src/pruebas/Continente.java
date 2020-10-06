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
    
    // Constructores, recordar siempre reservar memoria para el array
    
    public Continente(){
    }
    public Continente(String nombre){
        
        if( nombre.equals("África") ||
            nombre.equals("América del Norte") ||
            nombre.equals("América del Sur") ||
            nombre.equals("Asia") ||
            nombre.equals("Europa") ||
            nombre.equals("Oceanía")) {
            
            this.nombre = nombre;
                   
        }
  
        switch(nombre){
            
            case("África"):
                
                this.paises.add("Congo")
                
                break;
            
            
        
        }
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
    
    public void setPaises(ArrayList<Pais> paises){
        this.paises = paises;
    }
    
    
    
    
}
