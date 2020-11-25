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
    private String abreviatura;
    private int bonus;
    private ArrayList<Pais> paises;
    private String color;
    private int numPaises; // numero de paises de cada continente
    public int ejercitosRearme; // Setear esto al crear el mapa
    // Constructores, recordar siempre reservar memoria para el array

    public Continente() {
    }

    public Continente(String nombre, String color) {
        this.paises = new ArrayList<>(); //inicio el array
        //en vez de esta mierda tenemos que hacer que lea un fichero donde esten las asignaciones de los continentes y paises y ver que esta bien
        if (nombre.equals("África")
                || nombre.equals("América del Norte")
                || nombre.equals("América del Sur")
                || nombre.equals("Asia")
                || nombre.equals("Europa")
                || nombre.equals("Oceanía")) {

            this.nombre = nombre;

        }
        this.color = color;
        
        switch(nombre){
            case "África":
                this.bonus = 3;
                break;
            case "América del Norte":
                this.bonus = 5;
                break;
            case "América del Sur":
                this.bonus = 2;
                break;
            case "Asia":
                this.bonus = 7;
                break;
            case "Europa":
                this.bonus = 5;
                break;
            case "Oceanía":
                this.bonus = 2;
                break;
        }
    }

    // setters & getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.equals("África")
                || nombre.equals("América del Norte")
                || nombre.equals("América del Sur")
                || nombre.equals("Asia")
                || nombre.equals("Europa")
                || nombre.equals("Oceanía")) {

            this.nombre = nombre;
        }
    }

    public String getAbreviatura() {
        return this.abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPais(Pais pais) {
        this.paises.add(pais);
    }

    public String getColor() {
        return color;
    }

    public int getNumPaises() {
        return numPaises;
    }

    public void setNumPaises(int numPaises) {
        this.numPaises = numPaises;
    }

    public String printColor() {

        if ("AMARILLO".equals(this.color)) {
            return "\033[43m" + "\033[1;90m";
        } else if ("AZUL".equals(this.color)) {
            return "\033[44m" + "\033[1;90m";
        } else if ("CYAN".equals(this.color)) {
            return "\033[46m" + "\033[1;90m";
        } else if ("ROJO".equals(this.color)) {
            return "\033[41m" + "\033[1;90m";
        } else if ("VERDE".equals(this.color)) {
            return "\033[42m" + "\033[1;90m";
        } else if ("VIOLETA".equals(this.color)) {
            return "\033[45m" + "\033[1;90m";
        } else {
            return " ";
        }
        // mirar codigo de error o algo para ver que hacer si color es invalido como migueloh else System.out.println();
    }

    public String printPaises() {
        String paisCont = "";
        for (int i = 0; i < paises.size(); i++) {
            if (i == 0) {
                paisCont += "{\npaises: [ " + paises.get(i).printNombre() + ",\n";
            } else if (i == (paises.size()) - 1) {
                paisCont += "\t " + paises.get(i).printNombre() + "\n\t ]";
            } else {
                paisCont += "\t " + paises.get(i).printNombre() + ",\n";
            }
        }
        return paisCont;
    }

    public String printPaisesEjer(Pais pais) {
        String paisCont = "";
        int i = 0;
        for (Pais p : this.paises) {
            if (i == 0) {
                if(pais.getJugador().equals(p.getJugador())){
                    paisCont += "[ { " + p.printNombre() + "," + p.getEjercitos() + " }";
                    i+=1;
                }
            } else{
                if(pais.getJugador().equals(p.getJugador())){
                    paisCont += ", { " + p.printNombre() + "," + p.getEjercitos() + " } ]";
                }
            } 
        }
        return paisCont;
    }

    public int fronterasContinente() {
        int resultado = 0;

        switch (this.abreviatura) {

            case "África":
                resultado = 4;
                break;
            case "Asia":
                resultado = 7;
                break;
            case "AméricaNorte":
                resultado = 3;
                break;
            case "AméricaSur":
                resultado = 2;
                break;
            case "Europa":
                resultado = 7;
                break;
            case "Oceanía":
                resultado = 1;
                break;

        }

        return resultado;
    }
    public String getJugadores(){
        ArrayList<Jugador> jugadores;
        String result = " ";
        jugadores = new ArrayList<>();
        for(Pais p : this.paises){
            if(jugadores.isEmpty()){
                jugadores.add(p.getJugador());
                result += "{ " + p.getJugador().getNombre() + "," + p.getJugador().ejercitosColocadosEnContinente(this) + "}";
            }else{
                if(jugadores.contains(p.getJugador())){
                    
                }else{
                    jugadores.add(p.getJugador());
                    result += ", { " + p.getJugador().getNombre() + "," + p.getJugador().ejercitosColocadosEnContinente(this) + "}";
                }
            }
        }
        return result;
    }
    public float porcentajePaisesOcupados() {
        int contador = 0;
        for (Pais p : this.paises) {
            if (p.estaAsignado()) {
                contador++;
            }
        }
        return (this.paises.size() / contador) * 100;
    }

    public String printNombre() {
        String texto = "\"" + this.nombre + "\"";
        return texto;
    }
    
    public int ejercitosColocadosEnContinenteTotal(){
        int resultado = 0;
        for(Pais p: this.paises){
        
            resultado += p.getEjercitos();
        
        }
    
    return resultado;
    }

}
