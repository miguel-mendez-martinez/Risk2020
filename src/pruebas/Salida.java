/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author migue
 */
public class Salida {
    private int code;
    private String descripcion;

    public Salida(int code) {
        this.code = code;
    }

    public Salida() {
        //esto lo utilizaremos para imprimir al archivo salidas que no sean error, y que por lo tanto no tengan codigo
    }
    
    public void imprimirArchivo(String salida){
        try{
            File archivo = new File("salida.txt");
            if(archivo.createNewFile()){
                System.out.println("Creando el archivo de salidas...");
            }
            //iniciamos el escritor en el archivo, buscado en internet 
            FileOutputStream fos = new FileOutputStream(archivo, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            Writer writer = new BufferedWriter(osw);
            
            writer.append(salida).append("\n");
            
            writer.close();
            osw.close();
            fos.close();
        }catch(Exception excepcion) {
            excepcion.printStackTrace();
        }
    }
    
    
   @Override
    public String toString(){
        codeToDesc(this.code);
        String error = "{\n código de error: " + this.code + ",\n descripción: \"" + this.descripcion + "\"\n{";
        this.imprimirArchivo(error);
        return error;
    }
    
    
    public void codeToDesc(int code){
        switch(code){
            case 99:
                this.descripcion = "Comando no permitido en este momento";
                break;
            case 100:
                this.descripcion = "Color no permitido";
                break;
            case 101:
                this.descripcion = "Comando incorrecto";
                break;
            case 102:
                this.descripcion = "El continente no existe";
                break;
            case 103:
                this.descripcion = "El jugador no existe";
                break;
            case 104:
                this.descripcion = "El jugador ya existe";
                break;
            case 105:
                this.descripcion = "Los jugadores no están creados";
                break;
            case 106:
                this.descripcion = "El mapa no está creado";
                break;
            case 107:
                this.descripcion = "El mapa ya ha sido creado";
                break;
            case 109:
                this.descripcion = "El país no existe";
                break;
            case 110:
                this.descripcion = "El país no pertence al jugador";
                break;
            case 111:
                this.descripcion = "El país pertenece al jugador";
                break;
            case 112:
                this.descripcion = "Los países no son frontera";
                break;
            case 113:
                this.descripcion = "El país ya está asignado";
                break;
            case 114:
                this.descripcion = "El color ya está asignado";
                break;
            case 115:
                this.descripcion = "La misión ya está asignada";
                break;
            case 116:
                this.descripcion = "La misión no existe";
                break;
            case 117:
                this.descripcion = "El jugador ya tiene asignada una misión";
                break;
            case 118:
                this.descripcion = "Las misiones no están asignadas";
                break;
            case 119:
                this.descripcion = "Ejercitos no disponible";
                break;
            case 120:
                this.descripcion = "No hay cartas suficientes";
                break;
            case 121:
                this.descripcion = "No hay configuración de cambio";
                break;
            case 122:
                this.descripcion = "Algunas cartas no pertenecen al jugador";
                break;
            case 123:
                this.descripcion = "Algunas cartas no existen";
                break;
            case 124:
                this.descripcion = "No hay ejercitos suficientes";
                break;
            case 125:
                this.descripcion = "El identificador no sigue el formato correcto";
                break;
            case 126:
                this.descripcion = "Carta de equipamiento ya asignada";
                break;
            default:
                System.out.println("Código de error no existente.");
            
        }
    }
}
