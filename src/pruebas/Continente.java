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
    
    // Constructores
    
    
    // setters & getters
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
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
