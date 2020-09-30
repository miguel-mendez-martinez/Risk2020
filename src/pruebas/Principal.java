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
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Jugador jugador1= new Jugador(); 
	Jugador jugador2= new Jugador("Miguel", "azul");
	Jugador jugador3= new Jugador("Miguel");
        jugador1.setNombre("Nicolas");
        Pais pais1=new Pais();
        Pais pais2=new Pais("España");
        Pais pais3=new Pais("Alemnia", 200);
        
	System.out.println(jugador1);
	System.out.println(jugador2);
	System.out.println(jugador3);
        System.out.println(pais1);
	System.out.println(pais2);
	System.out.println(pais3);
		
    }
    
}
