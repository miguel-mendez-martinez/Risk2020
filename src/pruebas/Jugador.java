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

public class Jugador {

    private String nombre;
    private String color;
    private int tropas;
    private int ejercitos_disponibles;
    private ArrayList<Pais> paises;
    private ArrayList<Continente> continentes;
    private ArrayList<Cartas> cartas;
    private ArrayList<Continente> allContinentes;
    private Mision mision;
    private int ejerRearme;

    // Constructores
    public Jugador() {

    }

    public ArrayList<Cartas> getCartas() {
        return cartas;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.color = "rojo";
    }

    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.paises = new ArrayList<>();
        this.continentes = new ArrayList<>();
        this.cartas = new ArrayList<>();
        this.mision = null; //este valor lo usaremos para comprobar a la hora de asignarle una mision
        this.ejerRearme = 0;
    }

    // setters & getters
    public void setEjercitos_disponibles(int ejercitos_disponibles) {
        this.ejercitos_disponibles = ejercitos_disponibles;
    }

    public int getEjercitos_disponibles() {
        return ejercitos_disponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    public Mision getMision() {
        return mision;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String Color) {
        this.color = Color;
    }

    public int getTropas() {
        return tropas;
    }

    public void setTropas(int tropas) {
        this.tropas = tropas;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(Pais pais) {
        this.paises.add(pais);
    }

    public void setCartas(Cartas carta) {
        this.cartas.add(carta);
    }

    public void setAllContinentes(ArrayList<Continente> allContinentes) {
        this.allContinentes = allContinentes;
    }

    public String printColorNom() {
        String texto = "{\n nombre: \"" + this.nombre + "\",\n color: \"" + this.color + "\"\n}";
        return texto;
    }

    public String printColor() {

        if ("AMARILLO".equals(this.color)) {
            return "\033[1;33m" + "\033[1;90m";
        } else if ("AZUL".equals(this.color)) {
            return "\033[1;34m" + "\033[1;90m";
        } else if ("CYAN".equals(this.color)) {
            return "\033[1;36m" + "\033[1;90m";
        } else if ("ROJO".equals(this.color)) {
            return "\033[4;31m" + "\033[1;90m";
        } else if ("VERDE".equals(this.color)) {
            return "\033[4;32m" + "\033[1;90m";
        } else if ("VIOLETA".equals(this.color)) {
            return "033[4;35m" + "\033[1;90m";
        } else {
            return " ";
        }
        // mirar codigo de error o algo para ver que hacer si color es invalido como migueloh else System.out.println();
    }

    public String printNomEjerR() {
        String texto = "{\n nombre: \"" + this.nombre + "\",\n numeroEjercitosRearmar: " + this.ejercitos_disponibles + "\n}";
        return texto;
    }

    public String printNombre() {
        String texto = "\"" + this.nombre + "\"";
        return texto;
    }

    public String prinColor() {
        String texto = "\"" + this.color + "\"";
        return texto;
    }

    public String printPaises() {
        String paisJug = "";
        if (this.paises.isEmpty()) {
            paisJug = "[ ]";
        } else {
            for (int i = 0; i < this.paises.size(); i++) {
                if (i == 0) {
                    paisJug += "[ " + this.paises.get(i).printNombre();
                } else if (i == (this.paises.size()) - 1) {
                    paisJug += ", " + this.paises.get(i).printNombre() + " ]";
                } else {
                    paisJug += ", " + this.paises.get(i).printNombre();
                }
            }
        }
        return paisJug;
    }

    public String printCartas() {
        String cartaJug = "";
        if (this.cartas.isEmpty()) {
            cartaJug = "[ ]";
        } else {
            for (int i = 0; i < this.cartas.size(); i++) {
                if (i == 0) {
                    cartaJug += "[ " + this.cartas.get(i).printCarta();
                } else {
                    cartaJug += ", " + this.cartas.get(i).printCarta();
                }
            }
            cartaJug += " ]";
        }
        return cartaJug;
    }

    public int countTropas() {
        int tropas = 0;

        for (Pais p : this.paises) {
            tropas += p.getEjercitos();
        }
        this.setTropas(tropas);
        return tropas;
    }

    public String descJugador(int check) {
        String texto;
        if (check == 1) {
            texto = "{\n nombre: " + this.printNombre() + ",\n color: "
                    + this.prinColor() + ",\n mision: " + this.mision.printDesc()
                    + ",\n numeroEjercitos: " + this.countTropas() + ",\n paises: " + this.printPaises()
                    + ",\n continentes: " + printContinentes() + ",\n cartas: "
                    + this.printCartas() + ",\n numeroEjercitosRearmar: " + this.ejercitos_disponibles + "\n}";
        } else {
            texto = "{\n nombre: " + this.printNombre() + ",\n color: "
                    + this.prinColor() + ",\n numeroEjercitos: " + this.countTropas()
                    + ",\n paises: " + this.printPaises() + ",\n continentes: "
                    + printContinentes() + ",\n cartas: " + this.printCartas()
                    + ",\n numeroEjercitosRearmar: " + this.ejercitos_disponibles + "\n}";
        }
        return texto;
    }

    @Override
    public String toString() {
        String texto = "{\n nombre: " + this.printNombre() + ",\n color: "
                + this.prinColor() + ",\n mision: " + this.mision.printDesc()
                + ",\n numeroEjercitos: " + this.countTropas() + ",\n paises: " + this.printPaises()
                + ",\n continentes: " + printContinentes() + ",\n cartas: "
                + this.printCartas() + ",\n numeroEjercitosRearmar: " + this.ejerRearme + "\n}";
        return texto;
    }

    public int calcularNumPaisesEnC(Continente c) {
        int contador = 0;
        for (Pais p : this.paises) {
            if (p.getContinente().equals(c)) {
                contador++;
            }

        }
        return contador;
    }

    public boolean esDueño(Continente continente) {

        boolean flag = true; // Por defecto es dueño de todo el continente
        for (Pais p : continente.getPaises()) {
            if (p.getJugador() == null) {
                flag = false;
            } else if (!p.getJugador().equals(this)) {
                flag = false; // Si el jugador de un pais es distinto a this entonces this no tiene todo el continente
            }
        }

        return flag;
    }

    public void continentesJugador(ArrayList<Continente> allContinentes) {

        for (Continente c : allContinentes) {
            if (this.esDueño(c) == true && this.continentes.contains(c) == false) {
                this.continentes.add(c);
            }
        }
    }

    public String printContinentes() {

        String cont = "";
        if (this.continentes.isEmpty()) {
            cont = "[ ]";
        } else {
            for (int i = 0; i < this.continentes.size(); i++) {
                if (i == 0) {
                    cont += "[ " + continentes.get(i).printNombre();
                } else {
                    cont += ", " + continentes.get(i).printNombre();
                }
            }
            cont += " ]";
        }
        return cont;
    }
    
    public int ejercitosColocadosEnContinente(Continente continente){
    
        int resultado = 0;
        
        for(Pais p:this.paises){
            if(p.getContinente().equals(continente)) resultado += p.getEjercitos();
        
        }
       return resultado;
    }
    
}
