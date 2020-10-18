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
public class Casilla {
    private Pais pais;
    private int x;
    private int y;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pais getPais(){
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    
    
    
    
}

