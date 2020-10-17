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
        String color = "VERDE";
        Continente africa = new Continente("África", color);
        
        Pais anorte = new Pais("Africa del Norte", color);
        africa.setPais(anorte);
        paises.add(anorte);

        Pais aoriental = new Pais("Africa Oriental", color);
        africa.setPais(aoriental);
        paises.add(aoriental);

        Pais congo = new Pais("Congo", color);
        africa.setPais(congo);
        paises.add(congo);


        Pais egipto = new Pais("Egipto", color);
        africa.setPais(egipto);
        paises.add(egipto);

        Pais madagascar = new Pais("Madagascar", color);
        africa.setPais(madagascar);
        paises.add(madagascar);

        Pais sudafrica = new Pais("Sudafrica", color);
        africa.setPais(sudafrica);
        paises.add(sudafrica);
        
        continentes.add(africa);
    }
    private void crearEuropa(){
        String color ="AMARILLO";
        Continente europa = new Continente("Europa", color);
        Pais islandia = new Pais("Islandia", color);
        europa.setPais(islandia);
        paises.add(islandia);

        Pais escandina = new Pais("Escandinavia", color);
        europa.setPais(escandina);
        paises.add(escandina);

        Pais GBretaña = new Pais("Gran Bretaña", color);
        europa.setPais(GBretaña);
        paises.add(GBretaña);

        Pais EurOcc = new Pais("Europa Occidental", color);
        europa.setPais(EurOcc);
        paises.add(EurOcc);

        Pais EurNorte = new Pais("Europa Norte", color);
        europa.setPais(EurNorte);
        paises.add(EurNorte);

        Pais EurSur = new Pais("Europa Sur", color);
        europa.setPais(EurSur);
        paises.add(EurSur);
        
        Pais Rusia = new Pais("Rusia", color);
        europa.setPais(Rusia);
        paises.add(Rusia);
        
        continentes.add(europa);
    }
    private void crearANorte(){
        String color = "MORADO";
        Continente Anorte = new Continente("América del Norte", color);
        
        Pais Alberta = new Pais("Alberta", color);
        Anorte.setPais(Alberta);
        paises.add(Alberta);

        Pais USAOeste = new Pais("Estados Unidos del Oeste", color);
        Anorte.setPais(USAOeste);
        paises.add(USAOeste);

        Pais TNoroeste = new Pais("Territorios del Noroeste", color);
        Anorte.setPais(TNoroeste);
        paises.add(TNoroeste);

        Pais Ontario = new Pais("Ontario", color);
        Anorte.setPais(Ontario);
        paises.add(Ontario);

        Pais USAEste = new Pais("Estados Unidos del Este", color);
        Anorte.setPais(USAEste);
        paises.add(USAEste);

        Pais AmeCentral = new Pais("America Central", color);
        Anorte.setPais(AmeCentral);
        paises.add(AmeCentral);
        
        Pais Groenlan = new Pais("Groenlandia", color);
        Anorte.setPais(Groenlan);
        paises.add(Groenlan);
        
        Pais Quebec = new Pais("Quebec", color);
        Anorte.setPais(Quebec);
        paises.add(Groenlan);
        
        continentes.add(Anorte);
    }
    private void crearASur(){
        String color="ROJO";
        Continente Asur = new Continente("América del Sur", color); 

        Pais venezuela = new Pais("venezuela", color);
        Asur.setPais(venezuela);
        paises.add(venezuela);

        Pais Perú = new Pais("Perú", color);
        Asur.setPais(Perú);
        paises.add(Perú);

        Pais Argentina = new Pais("Argentina", color);
        Asur.setPais(Argentina);
        paises.add(Argentina);

        Pais Brasil = new Pais("Brasil", color);
        Asur.setPais(Brasil);
        paises.add(Brasil);
        
        continentes.add(Asur);
    }
    private void crearOceania(){
        String color = "AZUL";
        Continente oceania = new Continente("Oceanía", color);
        
        Pais Indonesia = new Pais("Indonesia", color);
        oceania.setPais(Indonesia);
        paises.add(Indonesia);

        Pais AusOccid = new Pais("Australia Occidental", color);
        oceania.setPais(AusOccid);
        paises.add(AusOccid);

        Pais NGuinea = new Pais("Nueva Guinea", color);
        oceania.setPais(NGuinea);
        paises.add(NGuinea);

        Pais AusOrient = new Pais("Australia Oriental", color);
        oceania.setPais(AusOrient);
        paises.add(AusOrient);
        
        continentes.add(oceania);
    }
    private void crearAsia(){
        String color = "CYAN";
        Continente asia = new Continente("Asia", color);
       
        Pais Siberia = new Pais("Siberia", color);
        asia.setPais(Siberia);
        paises.add(Siberia);

        Pais Yakustsk = new Pais("Yakustsk", color);
        asia.setPais(Yakustsk);
        paises.add(Yakustsk);

        Pais Urales = new Pais("Urales", color);
        asia.setPais(Urales);
        paises.add(Urales);
        
        Pais Afgan = new Pais("Afganistan", color);
        asia.setPais(Afgan);
        paises.add(Afgan);

        Pais OMedio = new Pais("Oriente Medio", color);
        asia.setPais(OMedio);
        paises.add(OMedio);

        Pais Kamchatka = new Pais("Kamchatka", color);
        asia.setPais(Kamchatka);
        paises.add(Kamchatka);
        
        Pais Mongolia = new Pais("Mongolia", color);
        asia.setPais(Mongolia);
        paises.add(Mongolia);
        
        Pais China = new Pais("China", color);
        asia.setPais(China);
        paises.add(China);
        
        Pais India = new Pais("India", color);
        asia.setPais(India);
        paises.add(India);
        
        Pais Japón = new Pais("Japón", color);
        asia.setPais(Japón);
        paises.add(Japón);
        
        Pais SAsiático = new Pais("Sureste Asiático", color);
        asia.setPais(SAsiático);
        paises.add(SAsiático);
        
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
