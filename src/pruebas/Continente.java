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
}
