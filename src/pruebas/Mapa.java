/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class Mapa {
    // Atributos
    private ArrayList<Continente> continentes;
    private ArrayList<Pais> paises;
    
    // Métodos
    
    // Constructor
    public Mapa(){
        continentes = new ArrayList<>();
        paises = new ArrayList<>();
        crearAfrica();
        crearEuropa();
        crearANorte();
        crearASur();
        crearOceania();
        crearAsia();
        //aacabamos de borrar todo, aqui po
    }
    
    
    
    public ArrayList<Continente> getContinentes(){
        return continentes;
    }
    
    @Override
    public String toString(){
        String paisMapa = "";
        String contMapa = "";
        for (int i=0;i<paises.size();i++) {
                paisMapa += paises.get(i);
        }
        for (int i=0;i<continentes.size();i++) {
                contMapa += continentes.get(i);
        }
        String texto="\nContinentes en Mapa: " + contMapa + "\nPaises en Mapa: " + paisMapa;
        return texto;
    }
    private void crearAfrica(){
        Continente africa = new Continente("África", "VERDE");
        Pais anorte = new Pais("Africa del Norte");
        africa.setPais(anorte);
        paises.add(anorte);

        Pais aoriental = new Pais("Africa Oriental");
        africa.setPais(aoriental);
        paises.add(aoriental);

        Pais congo = new Pais("Congo");
        africa.setPais(congo);
        paises.add(congo);


        Pais egipto = new Pais("Egipto");
        africa.setPais(egipto);
        paises.add(egipto);

        Pais madagascar = new Pais("Madagascar");
        africa.setPais(madagascar);
        paises.add(madagascar);

        Pais sudafrica = new Pais("Sudafrica");
        africa.setPais(sudafrica);
        paises.add(sudafrica);
        
        continentes.add(africa);
    }
    private void crearEuropa(){
        Continente europa = new Continente("Europa", "AMARILLo");
        /*Pais anorte = new Pais("Africa del Norte");
        africa.setPais(anorte);
        paises.add(anorte);

        Pais aoriental = new Pais("Africa Oriental");
        africa.setPais(aoriental);
        paises.add(aoriental);

        Pais congo = new Pais("Congo");
        africa.setPais(congo);
        paises.add(congo);


        Pais egipto = new Pais("Egipto");
        africa.setPais(egipto);
        paises.add(egipto);

        Pais madagascar = new Pais("Madagascar");
        africa.setPais(madagascar);
        paises.add(madagascar);

        Pais sudafrica = new Pais("Sudafrica");
        africa.setPais(sudafrica);
        paises.add(sudafrica);*/
        
        continentes.add(europa);
    }
    private void crearANorte(){
        Continente Anorte = new Continente("América del Norte", "MORADO");
        /*Pais anorte = new Pais("Africa del Norte");
        africa.setPais(anorte);
        paises.add(anorte);

        Pais aoriental = new Pais("Africa Oriental");
        africa.setPais(aoriental);
        paises.add(aoriental);

        Pais congo = new Pais("Congo");
        africa.setPais(congo);
        paises.add(congo);


        Pais egipto = new Pais("Egipto");
        africa.setPais(egipto);
        paises.add(egipto);

        Pais madagascar = new Pais("Madagascar");
        africa.setPais(madagascar);
        paises.add(madagascar);

        Pais sudafrica = new Pais("Sudafrica");
        africa.setPais(sudafrica);
        paises.add(sudafrica);*/
        
        continentes.add(Anorte);
    }
    private void crearASur(){
        Continente Asur = new Continente("América del Sur", "ROJO");
        /*Pais anorte = new Pais("Africa del Norte");
        africa.setPais(anorte);
        paises.add(anorte);

        Pais aoriental = new Pais("Africa Oriental");
        africa.setPais(aoriental);
        paises.add(aoriental);

        Pais congo = new Pais("Congo");
        africa.setPais(congo);
        paises.add(congo);


        Pais egipto = new Pais("Egipto");
        africa.setPais(egipto);
        paises.add(egipto);

        Pais madagascar = new Pais("Madagascar");
        africa.setPais(madagascar);
        paises.add(madagascar);

        Pais sudafrica = new Pais("Sudafrica");
        africa.setPais(sudafrica);
        paises.add(sudafrica);*/
        
        continentes.add(Asur);
    }
    private void crearOceania(){
        Continente oceania = new Continente("Oceanía", "AZUL");
        /*Pais anorte = new Pais("Africa del Norte");
        africa.setPais(anorte);
        paises.add(anorte);

        Pais aoriental = new Pais("Africa Oriental");
        africa.setPais(aoriental);
        paises.add(aoriental);

        Pais congo = new Pais("Congo");
        africa.setPais(congo);
        paises.add(congo);


        Pais egipto = new Pais("Egipto");
        africa.setPais(egipto);
        paises.add(egipto);

        Pais madagascar = new Pais("Madagascar");
        africa.setPais(madagascar);
        paises.add(madagascar);

        Pais sudafrica = new Pais("Sudafrica");
        africa.setPais(sudafrica);
        paises.add(sudafrica);*/
        
        continentes.add(oceania);
    }
    private void crearAsia(){
        Continente asia = new Continente("Asia", "CYAN");
        /*Pais anorte = new Pais("Africa del Norte");
        africa.setPais(anorte);
        paises.add(anorte);

        Pais aoriental = new Pais("Africa Oriental");
        africa.setPais(aoriental);
        paises.add(aoriental);

        Pais congo = new Pais("Congo");
        africa.setPais(congo);
        paises.add(congo);


        Pais egipto = new Pais("Egipto");
        africa.setPais(egipto);
        paises.add(egipto);

        Pais madagascar = new Pais("Madagascar");
        africa.setPais(madagascar);
        paises.add(madagascar);

        Pais sudafrica = new Pais("Sudafrica");
        africa.setPais(sudafrica);
        paises.add(sudafrica);*/
        
        continentes.add(asia);
    }
    
    
    
    
    
}
/*switch(nombre){
            
            case("África"):
                
                
                
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
        }*/
