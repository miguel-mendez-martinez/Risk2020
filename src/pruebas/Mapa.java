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
    private ArrayList<Casilla> casillas;

    // Métodos
    // Constructor
    public Mapa() {
        continentes = new ArrayList<>();
        paises = new ArrayList<>();
        casillas = new ArrayList<>();

        /*for (int i = 0; i < 11; i++) {
            this.casillas.add(new ArrayList<>());
            for (int j = 0; j < 8; j++) {
                this.casillas.get(i).add(new Casilla(i, j));
            }
        }*/
        crearAfrica();
        crearEuropa();
        crearANorte();
        crearASur();
        crearOceania();
        crearAsia();
        setFronterasP();
        printMapa();
        //getFronterasP(); // prueba
        //aacabamos de borrar todo, aqui po
    }

    public ArrayList<Continente> getContinentes() {
        return continentes;
    }
    
    public ArrayList<Pais> getPaises(){
        return paises;
    }
    private void crearAfrica() {
        String color = "VERDE";
        Casilla casilla;
        Continente africa = new Continente("África", color);

        casilla = new Casilla(5, 4);
        Pais anorte = new Pais("África del Norte", "ANorte", casilla, africa);
        africa.setPais(anorte);
        casilla.setPais(anorte);
        casillas.add(casilla);
        paises.add(anorte);

        casilla = new Casilla(6, 5);
        Pais aoriental = new Pais("Africa Oriental", "AOriental", casilla, africa);
        africa.setPais(aoriental);
        casilla.setPais(aoriental);
        casillas.add(casilla);
        paises.add(aoriental);

        casilla = new Casilla(5, 5);
        Pais congo = new Pais("Congo", "Congo", casilla, africa);
        africa.setPais(congo);
        casilla.setPais(congo);
        casillas.add(casilla);
        paises.add(congo);

        casilla = new Casilla(6, 4);
        Pais egipto = new Pais("Egipto", "Egipto", casilla, africa);
        africa.setPais(egipto);
        casilla.setPais(egipto);
        casillas.add(casilla);
        paises.add(egipto);

        casilla = new Casilla(7, 6);
        Pais madagascar = new Pais("Madagascar", "Madagasca", casilla, africa);
        africa.setPais(madagascar);
        casilla.setPais(madagascar);
        casillas.add(casilla);
        paises.add(madagascar);

        casilla = new Casilla(6, 6);
        Pais sudafrica = new Pais("Sudafrica", "Sudáfrica", casilla, africa);
        africa.setPais(sudafrica);
        casilla.setPais(sudafrica);
        casillas.add(casilla);
        paises.add(sudafrica);

        continentes.add(africa);
    }

    private void crearEuropa() {
        String color = "AMARILLO";
        Casilla casilla;
        Continente europa = new Continente("Europa", color);

        casilla = new Casilla(4, 0);
        Pais islandia = new Pais("Islandia", "Islandia", casilla, europa);
        europa.setPais(islandia);
        casilla.setPais(islandia);
        casillas.add(casilla);
        paises.add(islandia);

        casilla = new Casilla(5, 0);
        Pais escandina = new Pais("Escandinavia", "Escandina", casilla, europa);
        europa.setPais(escandina);
        casilla.setPais(escandina);
        casillas.add(casilla);
        paises.add(escandina);

        casilla = new Casilla(5, 1);
        Pais GBretaña = new Pais("Gran Bretaña", "GBretaña", casilla, europa);
        europa.setPais(GBretaña);
        casilla.setPais(GBretaña);
        casillas.add(casilla);
        paises.add(GBretaña);

        casilla = new Casilla(5, 2);
        Pais EurOcc = new Pais("Europa Occidental", "EurOcc", casilla, europa);
        europa.setPais(EurOcc);
        casilla.setPais(EurOcc);
        casillas.add(casilla);
        paises.add(EurOcc);

        casilla = new Casilla(6, 1);
        Pais EurNorte = new Pais("Europa Norte", "EurNorte", casilla, europa);
        europa.setPais(EurNorte);
        casilla.setPais(EurNorte);
        casillas.add(casilla);
        paises.add(EurNorte);

        casilla = new Casilla(6, 2);
        Pais EurSur = new Pais("Europa Sur", "EurSur", casilla, europa);
        europa.setPais(EurSur);
        casilla.setPais(EurSur);
        casillas.add(casilla);
        paises.add(EurSur);

        casilla = new Casilla(7, 1);
        Pais Rusia = new Pais("Rusia", "Rusia", casilla, europa);
        europa.setPais(Rusia);
        casilla.setPais(Rusia);
        casillas.add(casilla);
        paises.add(Rusia);

        continentes.add(europa);
    }

    private void crearANorte() {
        String color = "MORADO";
        Casilla casilla;
        Continente Anorte = new Continente("América del Norte", color);

        casilla = new Casilla(0, 0);
        Pais Alaska = new Pais("Alaska", "Alaska", casilla, Anorte);
        Anorte.setPais(Alaska);
        casilla.setPais(Alaska);
        casillas.add(casilla);
        paises.add(Alaska);

        casilla = new Casilla(0, 1);
        Pais Alberta = new Pais("Alberta", "Alberta", casilla, Anorte);
        Anorte.setPais(Alberta);
        casilla.setPais(Alberta);
        casillas.add(casilla);
        paises.add(Alberta);

        casilla = new Casilla(0, 2);
        Pais USAOeste = new Pais("Estados Unidos del Oeste", "USAOeste", casilla, Anorte);
        Anorte.setPais(USAOeste);
        casilla.setPais(USAOeste);
        casillas.add(casilla);
        paises.add(USAOeste);

        casilla = new Casilla(1, 0);
        Pais TNoroeste = new Pais("Territorios del Noroeste", "TNoroeste", casilla, Anorte);
        Anorte.setPais(TNoroeste);
        casilla.setPais(TNoroeste);
        casillas.add(casilla);
        paises.add(TNoroeste);

        casilla = new Casilla(1, 1);
        Pais Ontario = new Pais("Ontario", "Ontario", casilla, Anorte);
        Anorte.setPais(Ontario);
        casilla.setPais(Ontario);
        casillas.add(casilla);
        paises.add(Ontario);

        casilla = new Casilla(1, 2);
        Pais USAEste = new Pais("Estados Unidos del Este", "USAEste", casilla, Anorte);
        Anorte.setPais(USAEste);
        casilla.setPais(USAEste);
        casillas.add(casilla);
        paises.add(USAEste);

        casilla = new Casilla(1, 3);
        Pais AmeCentral = new Pais("America Central", "AmeCentra", casilla, Anorte);
        Anorte.setPais(AmeCentral);
        casilla.setPais(AmeCentral);
        casillas.add(casilla);
        paises.add(AmeCentral);

        casilla = new Casilla(2, 0);
        Pais Groenlan = new Pais("Groenlandia", "Groenlan", casilla, Anorte);
        Anorte.setPais(Groenlan);
        casilla.setPais(Groenlan);
        casillas.add(casilla);
        paises.add(Groenlan);

        casilla = new Casilla(2, 1);
        Pais Quebec = new Pais("Quebec", "Quebec", casilla, Anorte);
        Anorte.setPais(Quebec);
        casilla.setPais(Quebec);
        casillas.add(casilla);
        paises.add(Quebec);

        continentes.add(Anorte);
    }

    private void crearASur() {
        String color = "ROJO";
        Casilla casilla;
        Continente Asur = new Continente("América del Sur", color);

        casilla = new Casilla(1, 4);
        Pais venezuela = new Pais("Venezuela", "Venezuela", casilla, Asur);
        Asur.setPais(venezuela);
        casilla.setPais(venezuela);
        casillas.add(casilla);
        paises.add(venezuela);

        casilla = new Casilla(1, 5);
        Pais Perú = new Pais("Perú", "Perú", casilla, Asur);
        Asur.setPais(Perú);
        casilla.setPais(Perú);
        casillas.add(casilla);
        paises.add(Perú);

        casilla = new Casilla(1, 6);
        Pais Argentina = new Pais("Argentina", "Argentina", casilla, Asur);
        Asur.setPais(Argentina);
        casilla.setPais(Argentina);
        casillas.add(casilla);
        paises.add(Argentina);

        casilla = new Casilla(2, 5);
        Pais Brasil = new Pais("Brasil", "Brasil", casilla, Asur);
        Asur.setPais(Brasil);
        casilla.setPais(Brasil);
        casillas.add(casilla);
        paises.add(Brasil);

        continentes.add(Asur);
    }

    private void crearOceania() {
        String color = "AZUL";
        Casilla casilla;
        Continente oceania = new Continente("Oceanía", color);

        casilla = new Casilla(9, 6);
        Pais Indonesia = new Pais("Indonesia", "Indonesia", casilla, oceania);
        oceania.setPais(Indonesia);
        casilla.setPais(Indonesia);
        casillas.add(casilla);
        paises.add(Indonesia);

        casilla = new Casilla(9, 7);
        Pais AusOccid = new Pais("Australia Occidental", "AusOccid", casilla, oceania);
        oceania.setPais(AusOccid);
        casilla.setPais(AusOccid);
        casillas.add(casilla);
        paises.add(AusOccid);

        casilla = new Casilla(10, 6);
        Pais NGuinea = new Pais("Nueva Guinea", "NGuinea", casilla, oceania);
        oceania.setPais(NGuinea);
        casilla.setPais(NGuinea);
        casillas.add(casilla);
        paises.add(NGuinea);

        casilla = new Casilla(10, 7);
        Pais AusOrient = new Pais("Australia Oriental", "AusOrient", casilla, oceania);
        oceania.setPais(AusOrient);
        casilla.setPais(AusOrient);
        casillas.add(casilla);
        paises.add(AusOrient);

        continentes.add(oceania);
    }

    private void crearAsia() {
        String color = "CYAN";
        Casilla casilla;
        Continente asia = new Continente("Asia", color);

        casilla = new Casilla(6, 0);
        Pais Siberia = new Pais("Siberia", "Siberia", casilla, asia);
        asia.setPais(Siberia);
        casilla.setPais(Siberia);
        casillas.add(casilla);
        paises.add(Siberia);

        casilla = new Casilla(7, 0);
        Pais Yakustsk = new Pais("Yakustsk", "Yakustsk", casilla, asia);
        asia.setPais(Yakustsk);
        casilla.setPais(Yakustsk);
        casillas.add(casilla);
        paises.add(Yakustsk);

        casilla = new Casilla(7, 2);
        Pais Urales = new Pais("Urales", "Urales", casilla, asia);
        asia.setPais(Urales);
        casilla.setPais(Urales);
        casillas.add(casilla);
        paises.add(Urales);

        casilla = new Casilla(7, 3);
        Pais Afgan = new Pais("Afganistan", "Afgan", casilla, asia);
        asia.setPais(Afgan);
        casilla.setPais(Afgan);
        casillas.add(casilla);
        paises.add(Afgan);

        casilla = new Casilla(7, 4);
        Pais OMedio = new Pais("Oriente Medio", "OMedio", casilla, asia);
        asia.setPais(OMedio);
        casilla.setPais(OMedio);
        casillas.add(casilla);
        paises.add(OMedio);

        casilla = new Casilla(8, 0);
        Pais Kamchatka = new Pais("Kamchatka", "Kamchatka", casilla, asia);
        asia.setPais(Kamchatka);
        casilla.setPais(Kamchatka);
        casillas.add(casilla);
        paises.add(Kamchatka);

        casilla = new Casilla(8, 1);
        Pais Irkutsk = new Pais("Irkutsk", "Irkutsk", casilla, asia);
        asia.setPais(Irkutsk);
        casilla.setPais(Irkutsk);
        casillas.add(casilla);
        paises.add(Irkutsk);

        casilla = new Casilla(8, 2);
        Pais Mongolia = new Pais("Mongolia", "Mongolia", casilla, asia);
        asia.setPais(Mongolia);
        casilla.setPais(Mongolia);
        casillas.add(casilla);
        paises.add(Mongolia);

        casilla = new Casilla(8, 3);
        Pais China = new Pais("China", "China", casilla, asia);
        asia.setPais(China);
        casilla.setPais(China);
        casillas.add(casilla);
        paises.add(China);

        casilla = new Casilla(8, 4);
        Pais India = new Pais("India", "India", casilla, asia);
        asia.setPais(India);
        casilla.setPais(India);
        casillas.add(casilla);
        paises.add(India);

        casilla = new Casilla(9, 2);
        Pais Japón = new Pais("Japón", "Japón", casilla, asia);
        asia.setPais(Japón);
        casilla.setPais(Japón);
        casillas.add(casilla);
        paises.add(Japón);

        casilla = new Casilla(9, 4);
        Pais SAsiático = new Pais("Sureste Asiático", "SAsiático", casilla, asia);
        asia.setPais(SAsiático);
        casilla.setPais(SAsiático);
        casillas.add(casilla);
        paises.add(SAsiático);

        continentes.add(asia);
    }

    private void setFronterasP() {
        for (Pais p : paises) {
            for (Pais f : paises) {
                if ((p.getCasilla().getX() == f.getCasilla().getX())
                        || p.getCasilla().getY() == f.getCasilla().getY()) {
                    if ((p.getCasilla().getY() - f.getCasilla().getY() == -1)
                            || (p.getCasilla().getY() - f.getCasilla().getY() == 1)
                            || (p.getCasilla().getX() - f.getCasilla().getX() == -1)
                            || p.getCasilla().getX() - f.getCasilla().getX() == 1) {
                        p.addFront(f);
                    }
                    //Fronteras indirectas: //revisar nombres //cambiar segundas partes de la igualdad a pej "anorte"
                 if((p.getAbreviatura().equals("anorte")) && (f.getAbreviatura().equals("Brasil"))){            
                    p.addFront(f);  
                    f.addFront(p);
                 } 
                 if((p.getAbreviatura().equals("Groenlan")) && (f.getAbreviatura().equals("Islandia"))){            
                    p.addFront(f);  
                    f.addFront(p);
                 }
                 if((p.getAbreviatura().equals("anorte")) && (f.getAbreviatura().equals("EurOcc"))){            
                    p.addFront(f);  
                    f.addFront(p);
                 }
                 if((p.getAbreviatura().equals("Egipto")) && (f.getAbreviatura().equals("EurSur"))){            
                    p.addFront(f);  
                    f.addFront(p);
                 }
                  if((p.getAbreviatura().equals("Kamchatka")) && (f.getAbreviatura().equals("Alaska"))){            
                    p.addFront(f);  
                    f.addFront(p);
                 }
                   if((p.getAbreviatura().equals("SAsiático")) && (f.getAbreviatura().equals("Indonesia"))){            
                    p.addFront(f);  
                    f.addFront(p);
                 }
                }
            }
        }

    }

        //Fronteras indirectas
    //Pais anorte.addFront(Brasil);
    //anorte.addFront(EurSur);
    //egipto.addFront(EurOcc);
    //islandia.addFront(Groenalndia);
    //Pais Alaska.addFront(Kamchatka);
    //indonesia.addFront(sasiatico);


// voy a ver las fronteras
    /*public void printFronterasP(){

            for(Pais p:paises){
                System.out.println("Pais:" + p.getNombre());
                for(int i=0; i<p.getFronteras().size();i++){    
                   System.out.println("Frontera" + i+1 + ": " + p.getFronteras().get(i).getNombre());  
                }
            }
    }*/





    //@Override

    public void printMapa(){ //si haces to string hay que hacer return de un string, ya veremos como hacerlo
        int oceano;
        for(int j=0; j<8; j++){
            if(j == 5){ //impresion linea vertical que une brasil y anorte
                System.out.print("\n|-----------|-----------|-----------|-----------\033[0;31m|\033[0m-----------|-----------|-----------|-----------|-----------|-----------|-----------|\n");
            }else
                System.out.print("\n|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|\n");
            for(int i=0; i<11; i++){
                /*cada vez que vayamos a comprobar una casilla ponemos oceano a 0. Si esta cambia a 1 significa que 
                en esa posicion hay pais, si se mantiene a 0 es que hay un oceano porque nada ha sido impreso*/
                oceano = 0; 
                System.out.print("|"); //columnas
                //ponemos esto, por si el color no ha sido parado en allguno de los ifs, pare ahora antes de imprimir nada mas
                System.out.print("\033[0m");
                for(Casilla cBucle : casillas){
                    
                    if ((cBucle.getX() == i) && (cBucle.getY() == j)){
                        System.out.print(cBucle.getPais().getContinente().printColor());
                        //System.out.print("\033[0m" );  
                        System.out.printf(String.format("%1$-11s", cBucle.getPais().getAbreviatura()));
                        System.out.print("\033[0m");
                        oceano = 1;
                    }
                }
                if((i==3 || i==9 || i==10) && j == 0){
                    System.out.print("\033[0;31m-----------\033[0m");
                }else if((i==3 && j==5)){
                    System.out.print("\033[0;31m-----------");
                }/*los dos siguientes ifs, componen las lineas rojas que unen brasil y anorte, ya que hay que hacer que la 
                linea vertical de la anterior de anorte sea roja y la siguiente a brasil igual*/
                else if(i==3 && j==4){
                    System.out.print("           \033[0;31m");
                }else if(i==4 && j==4){
                    System.out.print("\033[0;31m-----------\033[0m");
                }else if(((i==5 || i==6)&& j==3) || (i==9 && j==5)){
                    System.out.print("     \033[0;31m|\033[0m     ");
                }
                else if(oceano == 0){
                    System.out.print("           ");
                }
            
            }
        System.out.print("|");
        }
        System.out.println("\n|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|\n");
        
    }
}