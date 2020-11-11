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
    
    public Dados(){
        Random rand = new Random();
        int random;
        
        random = rand.nextInt(6); 
        
        switch(random){
            case 0:
                this.y = 1;
                break;
            case 1:
                this.y = 2;
                break;
            case 2:
                this.y = 3;
                break;
            case 3:
                this.y = 4;
                break;
            case 4:
                this.y = 5;
                break;
            case 5:
                this.y = 6;
                break;
            default:
                System.out.println("Fallo en la generacion aleatoria.");
                break;       
        }
        
        random = rand.nextInt(6);
        
        switch(random){
            case 0:
                this.x = 1;
                break;
            case 1:
                this.x = 2;
                break;
            case 2:
                this.x = 3;
                break;
            case 3:
                this.x = 4;
                break;
            case 4:
                this.x = 5;
                break;
            case 5:
                this.x = 6;
                break;
            default:
                System.out.println("Fallo en la generacion aleatoria.");
                break;       
        }
    }
    
    @Override
    public String toString(){
        String texto = "Valores dados:" + this.x + " " + this. y;
        return texto;
    }
}
