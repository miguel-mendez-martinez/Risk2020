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


public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Jugador jugador1= new Jugador(); 
	Jugador jugador2= new Jugador("Miguel", "azul");
	Jugador jugador3= new Jugador("loko4");
        jugador1.setNombre("Nicolas");
        Pais pais1=new Pais();
        Pais pais2=new Pais("España");
        Pais pais3=new Pais("Alemnia", 200);
        ArrayList<Pais> paises= new ArrayList<Pais>();
        paises.add(pais1);
        paises.add(pais3);
        Jugador jugador4= new Jugador("Diana", "rojo", paises);
        
	System.out.println(jugador1.toString());
	System.out.println(jugador2.toString());
	System.out.println(jugador3.toString());
        System.out.println(jugador4.toString());
        System.out.println(pais1.toString());
	System.out.println(pais2.toString());
	System.out.println(pais3.toString());
		
    }
    
}
