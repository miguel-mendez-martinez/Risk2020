/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;
    import java.util.Random;
/**
 *
 * @author migue
 */
public class Dados {
    private int x;
    private int y;
    private int z;
    
    
    public Dados(){
        x = 0;
        y = 0;
        z = 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
    
    public void ordenarDados(){
        int aux;
        if(this.y > this.x){
            aux = this.x;
            this.x = this.y;
            this.y = aux;
        }
        if(this.z > this.x){
            aux = this.x;
            this.x = this.z;
            this.z = aux;
        }
        if(this.z > this.y){
            aux = this.y;
            this.y = this.z;
            this.z = aux;
        }
    }
    public void genDados(int numDados){
        Random rand = new Random();
        int aux;
        if(numDados == 1){
            this.x = rand.nextInt(6) + 1; 
        }else if(numDados == 2){
            this.x = rand.nextInt(6) + 1; 
            this.y = rand.nextInt(6) + 1; 
            if(this.y > this.x){
                aux = this.x;
                this.x = this.y;
                this.y = aux;
            }
            
        }else{
            this.x = rand.nextInt(6) + 1; 
            this.y = rand.nextInt(6) + 1;
            if(this.y > this.x){
                aux = this.x;
                this.x = this.y;
                this.y = aux;
            }
            this.z = rand.nextInt(6) + 1;
            if(this.z > this.x){
                aux = this.x;
                this.x = this.z;
                this.z = aux;
            }
            if(this.z > this.y){
                aux = this.y;
                this.y = this.z;
                this.z = aux;
            }
        }
        
    }
    
    public int compDado(Dados dado2){
        int result=0;
        
        if(this.x <= dado2.getX()){
            result = 0;
            //ya una victoria para defensores, se devolvera 1 o 0
            if(dado2.getY() == 0){ //si la y es 0 la z tmb por lo tanto el otro dado solo lanzo 1 variable
                result = 0;
            }else{
                if(this.y <= dado2.getY() && this.z <= dado2.getY()){
                    //significa que tanto la x como la y del segundo dado son mayores a todo lo del primero
                    result = 0;
                }else{
                    //el dado2 tiene la x mayor pero a la y menor que la y o z de los atacantes
                    result = 1;
                }
            }
        }else{
            if(dado2.getY() == 0){ //si la y es 0 la z tmb por lo tanto el otro dado solo lanzo 1 variable
                result = 2;
            }else{
                if(this.y <= dado2.getY() && this.z <= dado2.getY()){
                    //una de las dos coordenadas del dado 2 es mayor que del dado 1, 1 victoria cada 1
                    result = 1;
                }else{
                    result = 2;
                }
            }
        }
        return result;
    }
    public int maxDado(){
        int max;
        if(this.x < this.y){
            max = this.y;
            if(max < this.z){
                max = this.z;
            }
        }else{
            max = this.x;
            if(max < this.z){
                max = this.z;
            }
        }
        return max;
    }
    public char buscaDado(int valor){
        char posicion;
        if(valor == this.x){
            return 'x';
        }else if(valor == this.y){
            return 'y';
        }else if(valor == this.z){
            return 'z';
        }else{
            return 'e'; //de error
        }
    }
    public int countDados(){
        if(this.z != 0){
            return 3;
        }else if(this.y != 0){
            return 2;
        }else{
            return 1;
        }
    }
    @Override
    public String toString(){
        String texto = "";
        if(x > 0){
            texto += this.x;
        }else if(x>0 && y>0){
            texto += this.x + ", " + this.y;
        }else if(x>0 && y>0 && z>0){
            texto += this.x + ", " + this.y + ", " + this.z;
        }else{
            texto += "";
        }
        return texto;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
