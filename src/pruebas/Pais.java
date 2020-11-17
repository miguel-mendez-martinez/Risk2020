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


public class Pais {
    
    private String nombre;
    private int ejercitos;
    private Casilla casilla;
    private String abreviatura;
    private Jugador jugador;
    private Continente continente;
    private ArrayList<Pais> fronteras;
    private int vecesOcupado;
    
    // Constructores
    
    public Pais(){
    }
    
    public Pais(String nombre){
        this.nombre=nombre;
        this.ejercitos=0;
    }
   
    public Pais(String nombre, String abreviatura, Casilla casilla, Continente continente) {
        this.nombre = nombre;
        this.vecesOcupado = 1;
        this.casilla = casilla;
        this.abreviatura = abreviatura;
        this.ejercitos = 0;
        fronteras = new ArrayList<>();
        this.continente = continente;
        this.jugador = null; //esto hara de comprobante para saber si esta asignado a algun jugador
    }
    
    public void addOcupacion(){
        this.vecesOcupado  += 1;
        this.setVecesOcupado(this.vecesOcupado);
    }

    public void setVecesOcupado(int vecesOcupado) {
        this.vecesOcupado = vecesOcupado;
    }
    
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Casilla getCasilla() {
        return casilla;
    }
    
    public void addFront(Pais f){
        this.fronteras.add(f);
    }

    public ArrayList<Pais> getFronteras(){
        return fronteras;
    }
    
    public String getAbreviatura(){
        return abreviatura;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEjercitos() {
        return ejercitos;
    }
    
    public void setEjercitos(int ejercitos){
        this.ejercitos = ejercitos ;
    }

    public void addEjercitos(int ejercitos){
        this.ejercitos += ejercitos;
    }


    public Continente getContinente() {
        return continente;
    }
    public String getColor(){
        return this.getContinente().getColor();
    }

    public boolean estaAsignado(){
        if(this.jugador == null){
            return false;
        }else{
            return true;
        }
    }
    
    public String fronterasToString(){
    
        String fronteras = "";
        for (int i=0;i<this.getFronteras().size();i++) {
            if(i == 0){
                fronteras += " [ " + this.getFronteras().get(i).printNombre();
            }else{
                fronteras += ", " + this.getFronteras().get(i).printNombre();
            }
        }
        fronteras += " ]";
        return fronteras;
    }

    public String printNombre(){
        String texto = "\"" + this.nombre + "\"";
        return texto;
    }
    public String printAbre(){
        String texto = "\"" + this.abreviatura + "\"";
        return texto;
    }
    
    public String printCont(){
        String texto = "\"" + this.continente.getNombre() + "\"";
        return texto;
    }
    public String printColor(){
        String texto = "\"" + this.continente.getColor() + "\"";
        return texto;
    }
    public String printJug(){
        String texto = "\"" + this.jugador.getNombre() + "\"";
        return texto;
    }
    public String descPais(){
        String texto = "{\n nombre: " + this.printNombre() + 
                ",\n abreviatura: " + this.printAbre() + ",\n continente: " 
                + this.printCont() + ",\n frontera: " + this.fronterasToString()
                + ",\n jugador: " + this.printJug() + ",\n numeroEjercitos: " 
                + this.ejercitos + ",\n numeroVecesOcupado: " + this.vecesOcupado + "\n}";
        return texto;
    }
    @Override
    public String toString(){
        String texto="\n\t "+ this.nombre + ", Tropas en pais: " + this.ejercitos;
        return texto;
    }

    @Override
    public boolean equals(Object obj){
        // Conversion explicita del objeto a tipo pais
        return this.abreviatura.equals(((Pais) obj).abreviatura);

    }
}

