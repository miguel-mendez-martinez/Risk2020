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
    private int numPaises; // numero de paises de cada continente
    // Constructores, recordar siempre reservar memoria para el array
    
    public Continente(){
    }
    public Continente(String nombre, String color){
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
        this.color = color;
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
    
    public void setPais(Pais pais){
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

    public String printColor(){

        if("AMARILLO".equals(this.color)) return "\033[43m" + "\033[1;90m";
        else if("AZUL".equals(this.color)) return "\033[44m" + "\033[1;90m";
        else if("CYAN".equals(this.color)) return "\033[46m" + "\033[1;90m";
        else if("ROJO".equals(this.color)) return "\033[41m" + "\033[1;90m";
        else if("VERDE".equals(this.color)) return "\033[42m" + "\033[1;90m";
        else if("VIOLETA".equals(this.color)) return "\033[45m" + "\033[1;90m";
        else return " ";
        // mirar codigo de error o algo para ver que hacer si color es invalido como migueloh else System.out.println();
    }

    public String printPaises(){
        String paisCont = "";
        for (int i=0;i<paises.size();i++) {
            if(i == 0){
                paisCont += "{\n paises: [ " + paises.get(i).printNombre() + ",\n";
            }else if(i==(paises.size())-1){
                paisCont += "\t " + paises.get(i).printNombre() + "\n\t ]";
            }else{
                paisCont += "\t " + paises.get(i).printNombre() + ",\n";
            }
        }  
        return paisCont;
    }
    
    public String printPaisesEjer(){
        String paisCont = "";
        for (int i=0;i<paises.size();i++) {
            if(i == 0){
                paisCont += "[ { " + paises.get(i).printNombre() + "," + paises.get(i).getEjercitos() + " },\n";
            }else if(i==(paises.size())-1){
                paisCont += "\t\t\t     { " + paises.get(i).printNombre() + "," + paises.get(i).getEjercitos() + " }\n\t\t\t    ]";
            }else{
                paisCont += "\t\t\t     { " + paises.get(i).printNombre() + "," + paises.get(i).getEjercitos() + " },\n";
            }
        }
        return paisCont;
    }

    public int fronterasContinente(){
        int resultado=0;

        for(Pais p: this.paises){

            for (int i=0; i< p.getFronteras().size(); i++){
                resultado++;
            }

        }

        return resultado;
    }

    public float porcentajePaisesOcupados(){
        int contador = 0;
        for (Pais p:this.paises){
            if(p.estaAsignado()) contador++;
        }
       return (this.paises.size()/contador) * 100;
    }


    public String printNombre(){
        String texto = "\"" + this.nombre + "\"";
        return texto;
    }

}