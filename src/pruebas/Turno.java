/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.ArrayList;

/**
 *
 * @author migue
 */
public class Turno {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Pais> paises;

    public Turno(ArrayList<Pais> paisesMundo) {
        this.jugadores = new ArrayList<>();
        //this.paises = new ArrayList<>();
        this.paises = paisesMundo;
    }

    public void addJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }

    public void addArray(ArrayList<Jugador> jugadoresP) {
        this.jugadores.addAll(jugadoresP);
    }

    public Jugador pasarTurno(Jugador jugador) {
        int i;
        for (i = 0; i < this.jugadores.size(); i++) {
            if (jugador.getNombre().equals(this.jugadores.get(i).getNombre())) {
                break;
            }
        }
        if (i == (this.jugadores.size() - 1)) {
            i = 0;
        } else {
            i++;
        }

        return this.jugadores.get(i);

    }
}
