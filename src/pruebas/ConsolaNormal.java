/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Scanner;

/**
 *
 * @author migue
 */
public class ConsolaNormal implements Consola{
    @Override
    public void imprimir(String mensaje){
        System.out.println(mensaje);
    }
    @Override
    public String leer(){
        String orden="";
        Scanner scanIn = new Scanner(System.in);
        orden = scanIn.nextLine();
        scanIn.close();            
        return orden;
    }
}
