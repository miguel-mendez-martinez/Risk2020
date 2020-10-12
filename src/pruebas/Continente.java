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
            
            case("América del Sur"):
                
                Pais argentina = new Pais("Argentina");
                this.paises.add(argentina);
                
                Pais brasil = new Pais("Brasil");
                this.paises.add(brasil);
                
                Pais peru = new Pais("Perú");
                this.paises.add(peru);
                
                Pais venezuela = new Pais("Venezuela");
                this.paises.add(venezuela);
                
                break;
                
            case("Asia"):
                
                Pais afgan = new Pais("Afgan");
                this.paises.add(afgan);
                
                Pais china = new Pais("China");
                this.paises.add(china);
                
                Pais india = new Pais("India");
                this.paises.add(india);
                
                Pais irktusk = new Pais("Irktusk");
                this.paises.add(irktusk);
                
                Pais japon = new Pais("Japón");
                this.paises.add(japon);
                
                Pais kamchatka = new Pais("Kamchatka");
                this.paises.add(kamchatka);
                
                Pais mongolia = new Pais("Mongolia");
                this.paises.add(mongolia);
                
                Pais omedio = new Pais("OMedio");
                this.paises.add(omedio);
                
                Pais sasiatico = new Pais("SAsiático");
                this.paises.add(sasiatico);
                
                Pais siberia = new Pais("Siberia");
                this.paises.add(siberia);
                
                Pais urales = new Pais("Urales");
                this.paises.add(urales);
                
                Pais yakutsk = new Pais("yakutsk");
                this.paises.add(yakutsk);
                
                break;
                
            case("Europa"):
                  
                Pais eurnorte = new Pais("EurNorte");
                this.paises.add(eurnorte);
                
                Pais eurocc = new Pais("EurOcc");
                this.paises.add(eurocc);
                
                Pais eursur = new Pais("EurSur");
                this.paises.add(eursur);
                
                Pais escandina = new Pais("Escandina");
                this.paises.add(escandina);
                
                Pais gbretaña = new Pais("GBretaña");
                this.paises.add(gbretaña);
                
                Pais islandia = new Pais("Islandia");
                this.paises.add(islandia);
                
                Pais rusia = new Pais("Rusia");
                this.paises.add(rusia);
                
                break;
                
            case("Oceania"):
                
                Pais ausoccid = new Pais("AusOccid");
                this.paises.add(ausoccid);
                
                Pais ausorient = new Pais("AusOrient");
                this.paises.add(ausorient);
                
                Pais indonesia = new Pais("Indonesia");
                this.paises.add(indonesia);
                
                Pais NGuinea = new Pais("nguinea");
                this.paises.add(NGuinea);
                
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
