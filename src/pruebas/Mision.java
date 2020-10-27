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
public class Mision {
    private String codigo;
    private String descripcion;
    private Jugador jugador;

    public Mision(String codigo, Jugador jugador) {
        this.codigo = codigo;
        this.jugador = jugador;
        this.descripcion = codigoToDesc(codigo);
    }

    public Mision(String codigo) {
        this.codigo = codigo;
    }
    
    

    public Mision() {
        this.jugador = null;
    }

    public Jugador getJugador() {
        return jugador;
    }
    
    
    
    public String codigoToDesc(String codigo){
        if( codigo.equals("M1")){
            return "Conquistar 24 países de la preferencia del jugador";
        }else if( codigo.equals("M2")){
            return "Conquistar 18 países de la preferencia del jugador con un mínimo de dos ejércitos";
        }else if( codigo.equals("M31")){
            return "Conquistar Asia y América del Sur";
        }else if( codigo.equals("M32")){
            return "Conquistar Asia y África";
        }else if( codigo.equals("M33")){
            return "Conquistar América del Norte y África";
        }else if( codigo.equals("M34")){
            return "Conquistar América del Norte y Oceanía";
        }else if( codigo.equals("M41")){
            return "Destruir el ejército AMARILLO";
        }else if( codigo.equals("M42")){
            return "Destruir el ejército AZUL";
        }else if( codigo.equals("M43")){
            return "Destruir el ejército CYAN";
        }else if( codigo.equals("M44")){
            return "Destruir el ejército ROJO";
        }else if( codigo.equals("M45")){
            return "Destruir el ejército VERDE";
        }else if( codigo.equals("M46")){
            return "Destruir el ejército VIOLETA";
        }
        return "fallo";
    }
    
    public int existeMision(String codigo){
        if( codigo.equals("M1") ||
            codigo.equals("M2") ||
            codigo.equals("M31") ||
            codigo.equals("M32") ||
            codigo.equals("M33") ||
            codigo.equals("M34") ||
            codigo.equals("M41") ||
            codigo.equals("M42") ||
            codigo.equals("M43") ||
            codigo.equals("M44") ||
            codigo.equals("M45") ||
            codigo.equals("M46") ) {
            
            return 1;
                   
        }else{
            return 0;
        }
    }
    
    @Override
    public String toString(){
        String texto;
        
        texto = "{\n\tNombre: " + this.jugador.getNombre() + "\n\tMision: " + this.descripcion;
        
        return texto;
    }
}
