/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package pruebas;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author migue
 */
public class Cartas {
    private String pais;
    private String clase;
    private Jugador jugador;
    private String identificador;
    
    
    public Cartas(String identif, Jugador jugador){
        this.identificador = identif;
        String[] partes=identif.split("&");
        partes[0] = this.clase;
        partes[1] = this.pais;
        this.jugador = jugador;
    }
    
    
    //manera random de generacion de cartas
    public Cartas(ArrayList<Pais> paises){ //habra que pasarle el arraylist de donde pillara los paises
        Random rand = new Random();
        int random;
        
        random = rand.nextInt(3); //seleccion de tipo
        switch(random){
            case 0:
                this.clase = "Infantería";
                break;
            case 1:
                this.clase = "Caballería";
                break;
            case 2:
                this.clase = "Artillería";
                break;
            default:
                System.out.println("Error en la generacion aleatoria.\n");
        }
        random = rand.nextInt(42);
        this.pais = paises.get(random).getAbreviatura();
        
        //con esto quedaria creada la carta, habra que crear ek identificador
        
        this.identificador = this.clase + "&" + this.pais;
        
        
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getPais() {
        return pais;
    }
    
    public String printCarta(){
        String texto = "\"" + this.identificador + "\"";
        return texto;
    }
    
}
