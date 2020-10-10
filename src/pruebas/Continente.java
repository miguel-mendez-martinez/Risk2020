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
    public Continente(String nombre){
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
  
        switch(nombre){
            
            case("África"):
                
                Pais anorte = new Pais("ANorte");
                this.paises.add(anorte);
                
                Pais aoriental = new Pais("AOriental");
                this.paises.add(aoriental);
                
                Pais congo = new Pais("Congo");
                this.paises.add(congo);
                
                Pais egipto = new Pais("Egipto");
                this.paises.add(egipto);
                
                Pais madagascar = new Pais("Madagasca");
                this.paises.add(madagascar);
                
                Pais sudafrica = new Pais("Sudáfrica");
                this.paises.add(sudafrica);
                
                break;
                
            case("América del Norte"):
                
                Pais alaska = new Pais("Alaska");
                this.paises.add(alaska);
                
                Pais alberta = new Pais("Alberta");
                this.paises.add(alberta);
                
                Pais amecentral = new Pais("AmeCentra");
                this.paises.add(amecentral);
                
                Pais groenlandia = new Pais("Groenlan");
                this.paises.add(groenlandia);
                
                Pais ontario = new Pais("Ontario");
                this.paises.add(ontario);
                
                Pais quebec = new Pais("Quebec");
                this.paises.add(quebec);
                
                Pais tnoroeste = new Pais("TNoroeste");
                this.paises.add(tnoroeste);
                
                Pais usaeste = new Pais("USAEste");
                this.paises.add(usaeste);
                
                Pais usaoeste = new Pais("USAOeste");
                this.paises.add(usaoeste);
                
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

    public String getColor() {
        return color;
    } 
}
