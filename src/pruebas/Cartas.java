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

    public Cartas(String pais, Jugador jugador, String tipo) {
        this.clase = tipo;
        this.pais = pais;
        this.jugador = jugador;
        this.identificador = this.clase + "&" + this.pais;
    }

    //manera random de generacion de cartas
    /*public Cartas(ArrayList<Pais> paises){ //habra que pasarle el arraylist de donde pillara los paises
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
     }*/
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getPais() {
        return pais;
    }

    public String getClase() {
        return clase;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public String printCarta() {
        String texto = "\"" + this.identificador + "\"";
        return texto;
    }

    public String printTipo() {
        String texto = "\"" + this.clase + "\"";
        return texto;
    }

    public String printPais() {
        String texto = "\"" + this.pais + "\"";
        return texto;
    }

    public int ejercitosCarta() {
        int ejer;
        Pais p = paisCoincidente(this.jugador.getPaises(), this.pais);
        if (p == null) {
            ejer = 0;
        } else {
            ejer = 1;
        }
        return ejer;
    }

    @Override
    public String toString() {
        String texto = "{\n tipoCarta: " + this.printTipo() + ",\n paisAsociado: "
                + this.printPais() + ",\n perteneceAJugador: " + this.jugador.printNombre()
                + ",\n ejercitosDeRearme: " + this.ejercitosCarta() + "\n}";
        return texto;
    }

    public Pais paisCoincidente(ArrayList<Pais> paises, String pais) { // mismo que nj
        if (paises.isEmpty()) {

            return null;
        } else {
            for (Pais p : paises) {
                if (p.getAbreviatura().equals(pais) == true) {

                    return p;
                }
            }
            return null;
        }

    }
}
