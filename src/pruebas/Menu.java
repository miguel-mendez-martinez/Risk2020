/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Miguel 
 * 
 */
public class Menu {
    // En esta clase se deberían de definir los atributos a los que será 
    // necesario acceder durante la ejecución del programa como, por ejemplo,
    // el mapa o los jugadores
    ArrayList<Jugador> jugadores;
    ArrayList<Pais> paises;
    ArrayList<Continente> continentes;
    ArrayList<Cartas> cartas;
    Mapa mapa;
    Jugador jugadorActual;
    Turno t;
    int conquisto;
    static ConsolaNormal consola;
    /**
     * 
     */
    public Menu() {
        //Aqui inicializamos algunos atrubutos con valores que ponemos predetermiados para saber alguna condicion de la prioridad de los comandos
        boolean iniciar=false; //este boolean nos dira si la partida ha sido iniciada correctamente
        this.mapa = null; //el mapa lo iniciamos a null para saber si ya ha sido creado
        int rearmo = 0; //esto es para saber cuando un jugador ha conquistado un pais en su turno
        int checker = 0; //esto lo utilizamos al principio de la creacion del proyecto. Aunque lo hemos quitado de muchos sitios con mejores comprobantes aun hay funciones que no hemos sabido como hacer para deshacernos de el 
        Mision mision; //se utiliza en varios puntos del menu por lo que hemos preferido declararlo aqui
        this.jugadorActual = null;
        this.conquisto=0;
        //creacion de los arrays caracteristicos del menu, que seran utilizados en practiamente todos sus metodos
        this.jugadores = new ArrayList<>();
        this.paises = new ArrayList<>();
        this.continentes = new ArrayList<>();
        this.cartas = new ArrayList<>();
        consola = new ConsolaNormal();
        
        
        
        //debemos ver si ya existe un fichero salida y eliminarlo si lo hace, ya que si existe escribiremos en el y tendremos las salidas de ejecuciones distintas en el mismo archivo
        File file = new File("resultados.txt"); 
        file.delete();
        String orden= null;
        //BufferedReader bufferLector= null;
        while(true){
            orden = consola.leer();// Mientras las líneas no estén en blanco:
            String comandoArchivo = "\n$> " + orden;
            consola.imprimir(comandoArchivo);
            Salida s = new Salida();
            s.imprimirArchivo(comandoArchivo);
            String[] partes=orden.split(" "); // Separa en diferentes strings los elementos de la linea separados por espacios
            String comando= partes[0]; // Definimos como comando la primera palabra de la linea
            switch(comando) {
                case "crear":
                    if(partes.length==2) {
                        if(partes[1].equals("mapa")){
                            if(this.mapa == null){ //comprobamos si el mapaya habia sido creado
                                crearMapa();
                                if(this.mapa == null){
                                    System.out.println("Error en la creacion de mapa.");
                                }else
                                checker = 1;//tras crear el mapa, los comandos de maxima prioridad han terminado, por lo que se pueden ejecutar los siguientes
                            }else{
                                //el mapa ya fue creado por lo que sacamos su error correspondiente
                                Salida error = new Salida(107);
                                System.out.println(error.toString());
                            }
                        }else{
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }
                    }else if(partes.length==3) {
                        if(this.mapa != null){
                            if(this.algunaMision(this.jugadores) == true){ //si la siguiente instruccion en prioridad ha sido ejecutada no podra ejecutar esta, pero si podra volver a ejecutarse despues de un crear jugador
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }else{
                                if(partes[1].equals("jugadores")) { 
                                    crearJugador(new File(partes[2]));
                                    this.setEjercitosInicio();
                                    this.jugadorActual = this.jugadores.get(0);
                                    if(checker < 2){
                                        checker = 2;
                                    }
                                } else {
                                    crearJugador(partes[1], partes[2]);
                                    this.setEjercitosInicio();
                                    this.jugadorActual = this.jugadores.get(0);
                                    if(checker < 2){
                                        checker = 2;
                                    }
                                }
                            }
                        }else{
                            Salida error = new Salida(106);
                            System.out.println(error.toString());
                        }

                    }else {
                        Salida error = new Salida(101);
                        System.out.println(error.toString());
                    }
                    break;
                case "ver":
                    if(partes[1].equals("mapa")){
                        if(this.mapa!=null){
                            this.mapa.printMapa();
                        }else{
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }
                    }
                    break;
                case "jugador":
                    //esto es describir toda la mierda del jugador del turno actual
                    if(iniciar==false){
                        Salida error = new Salida(99);
                        System.out.println(error.toString());
                    }else{
                        this.jugadorActual.continentesJugador(this.continentes);
                        System.out.println(this.jugadorActual);
                        Salida x = new Salida();
                        x.imprimirArchivo(this.jugadorActual.toString());
                    }

                    break;
                case "rearmar":
                    if(iniciar==false){
                        Salida error = new Salida(99);
                        System.out.println(error.toString());
                    }else{
                        if(partes.length!=4){
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }else{
                            Pais p1 = this.existePais(this.paises, partes[1]);
                            Pais p2 = this.existePais(this.paises, partes[3]);
                            if(p1 == null || p2 == null){
                                Salida error = new Salida(109);
                                System.out.println(error.toString());
                            }else{
                                Pais p3 = this.existePais(this.jugadorActual.getPaises(), p1.getAbreviatura()); //miramos si el primer pais pertenece al jugador
                                if(p3 == null){
                                    Salida error = new Salida(110);
                                    System.out.println(error.toString());
                                }else{
                                    Pais p4 = this.existePais(this.jugadorActual.getPaises(), p2.getAbreviatura()); //miramos si el segundo pais pertenece al jugador
                                    if(p4 == null){
                                        Salida error = new Salida(110);
                                        System.out.println(error.toString());
                                    }else{
                                        //a este punto 1 y 3, 2 y 4 son iguales. 5 sera igual a p2
                                        Pais p5 = this.existePais(p1.getFronteras(), p2.getAbreviatura());
                                        if(p5 == null){
                                            Salida error = new Salida(112);
                                            System.out.println(error.toString());
                                        }else{
                                            //en este punto, existen, pertenecen al jugador y son frontera
                                            if(p1.getEjercitos() <= 1){ //no se podran transferir tropas si el primer pais tiene el minimo
                                                Salida error = new Salida(124);
                                                System.out.println(error.toString());
                                            }else{
                                                rearme(p1,p2,partes[2]);
                                                rearmo = 1; 
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "describir":
                    if(iniciar==false){
                        Salida error = new Salida(99);
                        System.out.println(error.toString());
                    }else{
                        if(partes[1].equals("jugador")){
                            //descripcion del jugador
                            String exito;
                            Jugador jugador = existeJugador(this.jugadores, partes[2]);
                            if(jugador == null){
                                Salida error = new Salida(103);
                                System.out.println(error.toString());
                            }else{
                                jugador.continentesJugador(this.continentes);
                                if(jugador.getNombre().equals(this.jugadorActual.getNombre())){
                                    exito = jugador.descJugador(1);
                                }else{
                                    exito = jugador.descJugador(0);
                                }
                                consola.imprimir(exito);
                                Salida salida = new Salida();
                                salida.imprimirArchivo(exito);
                            }
                        }else if(partes[1].equals("pais")){
                            //descripcion del pais
                            Pais pais = existePais(this.paises, partes[2]);
                            if(pais == null){
                                Salida error = new Salida(109);
                                System.out.println(error.toString());
                            }else{
                                consola.imprimir(pais.descPais());
                                Salida salida = new Salida();
                                salida.imprimirArchivo(pais.descPais());
                            }

                        }else if(partes[1].equals("continente")){
                            describirContinente(partes[2]);
                        }else{
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }
                    }
                    break;
                case "acabar":
                    this.jugadorActual = this.t.pasarTurno(this.jugadorActual);
                    this.conquisto = 0;
                    rearmo = 0;
                    int minimo;
                    this.jugadorActual.continentesJugador(this.continentes);
                    //aqui asignamos cuantas tropas recibe
                    if(iniciar == true){
                        minimo = this.ejercitosTurno(this.jugadorActual);
                        this.jugadorActual.addEjercitos_disponibles(minimo);
                    }else{
                       minimo = this.ejercitosTurno(this.jugadorActual); 
                    }
                    //this.jugadorActual.setEjercitos_disponibles(10);
                    this.jugadorActual.setEjerRearme(minimo);
                    consola.imprimir(this.jugadorActual.printNomEjerR());
                    Salida sal = new Salida();
                    sal.imprimirArchivo(this.jugadorActual.printNomEjerR());
                    break;
                case "cambiar":
                    if(iniciar==false || rearmo == 1){
                        Salida error = new Salida(99);
                        System.out.println(error.toString());
                    }else if(partes.length == 3){
                        //cambiar cartas todas
                        //ALBERTO MARICA
                    }else{
                        //aqui se haria la mierda de cartas de cambias todas o cambiar alguna por tropas
                        //se hace aqui al jugador actual que es quien marca los turnos
                        if(jugadorActual.getCartas().size() < 3){
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }else{
                            if(partes.length != 5){
                                Salida error = new Salida(101);
                                System.out.println(error.toString());
                            }else{
                                //creacion de las cartas
                                String[] cartaCruda1=partes[2].split("&");
                                String[] cartaCruda2=partes[3].split("&");
                                String[] cartaCruda3=partes[4].split("&");

                                if((!"Caballería".equals(cartaCruda1[0]) && !"Infantería".equals(cartaCruda1[0]) && !"Artillería".equals(cartaCruda1[0])) 
                                    || (!"Caballería".equals(cartaCruda2[0]) && !"Infantería".equals(cartaCruda2[0]) && !"Artillería".equals(cartaCruda2[0]))
                                    || (!"Caballería".equals(cartaCruda3[0]) && !"Infantería".equals(cartaCruda3[0]) && !"Artillería".equals(cartaCruda3[0]))){
                                    Salida error = new Salida(123);
                                    System.out.println(error.toString());
                                }else{
                                    Pais p1 = this.existePais(this.paises, cartaCruda1[1]);
                                    Pais p2 = this.existePais(this.paises, cartaCruda2[1]);
                                    Pais p3 = this.existePais(this.paises, cartaCruda3[1]);
                                    if(p1==null || p2==null || p3==null){
                                        Salida error = new Salida(123);
                                        System.out.println(error.toString());
                                    }else{
                                        Cartas carta1 = this.estaAsignada(this.jugadorActual.getCartas(), cartaCruda1[1], cartaCruda1[0]);
                                        Cartas carta2 = this.estaAsignada(this.jugadorActual.getCartas(), cartaCruda2[1], cartaCruda2[0]);
                                        Cartas carta3 = this.estaAsignada(this.jugadorActual.getCartas(), cartaCruda3[1], cartaCruda3[0]);
                                        //revisar aqui
                                        if(carta1 == null || carta2 == null || carta3 == null){
                                            Salida error = new Salida(122);
                                            System.out.println(error.toString());
                                        }else{
                                            this.checkCombi(carta1, carta2, carta3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "asignar":
                    if(partes.length > 4) {
                        Salida error = new Salida(101);
                        System.out.println(error.toString());
                    }else if(partes[1].equals("misiones")){
                        if(checker >= 2){
                            if(this.algunaPais(this.paises) == true){ //si la siguiente instruccion ya ha sido ejecutada, no podra volver a ejecutar asignar
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }else{
                                if(partes[1].equals("misiones")){
                                    asignarMisiones(new File(partes[2]));
                                    if(checker < 3){
                                        if(this.misionesAsignadas(this.jugadores) == true){
                                            checker = 3;
                                            //se cumple que todos los jugadores tienen mision
                                        }
                                    }
                                }
                            }
                        }else{
                            Salida error = new Salida(105);
                            System.out.println(error.toString());
                        }
                        //primero tenemos que asignar misiones antes de paises
                    }else if(partes[1].equals("paises")){
                        if(checker >= 3){
                            if(checker > 4){
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }
                            else{
                                asignarPaises(new File(partes[2]));

                                for(Jugador j: this.jugadores){
                                    j.continentesJugador(this.continentes);
                                }
                                if(checker < 4){
                                    if(this.paisesAsignados(this.paises) == true){
                                        checker = 4;
                                    }
                                }
                            }
                        }else{
                            Salida error = new Salida(118);
                            System.out.println(error.toString());
                        }
                    }else if(partes[1].equals("carta")){
                        //se le asigna a jugador actual una carta
                        if(iniciar==false){
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }else{
                            String[] partes2 = partes[2].split("&"); 
                            if(partes2[0].equals("Infantería") || partes2[0].equals("Artillería") || partes2[0].equals("Caballería")){
                                String pais = partes2[1];
                                String clase = partes2[0];
                                Pais p = this.existePais(this.paises, pais);
                                if(p == null){
                                    Salida error = new Salida(109);
                                    System.out.println(error.toString());
                                }else{//continuamos
                                    if(this.conquisto==1){
                                        //comprobar si esta asignada
                                        Cartas c = this.estaAsignada(this.cartas, pais, clase);
                                        if(c == null){
                                            c = new Cartas(pais, this.jugadorActual, clase);
                                            this.jugadorActual.setCartas(c);
                                            this.cartas.add(c);

                                            System.out.println(c);
                                            Salida salida = new Salida();
                                            salida.imprimirArchivo(c.toString());

                                        }else{
                                            Salida error = new Salida(126);
                                            System.out.println(error.toString()); 
                                        }
                                    }else{
                                        Salida error = new Salida(127);
                                        System.out.println(error.toString());
                                    }
                                }

                            }else{
                                Salida error = new Salida(125);
                                System.out.println(error.toString());
                            }

                        }
                    }else if(partes[1].equals("mision")){
                        //Estas operaciones por ahora no cambian el checker ya que no asignan cosas a todos los jugadores
                        if(checker >= 2){
                            if(checker > 3){
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }else{
                                asignarMisiones(partes[2], partes[3]);
                                if(checker < 3){
                                    if(this.misionesAsignadas(this.jugadores) == true){
                                        checker = 3;
                                        //se cumple que todos los jugadores tienen mision
                                    }
                                }
                            }
                        }else{
                            Salida error = new Salida(105);
                            System.out.println(error.toString());
                        }
                    }else if(partes[1].equals("pais")){

                        if(checker >= 3){
                            asignarPaises(partes[2], partes[3]);
                            if(checker < 4){
                                if(this.paisesAsignados(this.paises) == true){
                                    checker = 4;
                                }
                            }
                        }else{
                            Salida error = new Salida(118);
                            System.out.println(error.toString());
                        } 

                    }
                    break;
                case "atacar":
                    //el jugador actual atacara a un pais que no sea de su dominio, y que tenga alguna frontera en comun
                    //dos comandos, el automatico, y el forzado
                    if(iniciar==false || rearmo == 1 || this.jugadorActual.getEjercitos_disponibles() != 0){ //solo se puede atacar entre repartir ejercitos y rearmar
                        Salida error = new Salida(99);
                        System.out.println(error.toString());
                    }
                    else{
                        if(partes.length>5){
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }else if(partes.length == 3){
                            Pais p1 = this.existePais(this.paises, partes[1]);
                            Pais p2 = this.existePais(this.paises, partes[2]);
                            if(p1 == null || p2 == null){
                                Salida error = new Salida(109);
                                System.out.println(error.toString());
                            }else{
                                Pais p3 = this.existePais(this.jugadorActual.getPaises(), p1.getAbreviatura()); //miramos si el primer pais pertenece al jugador
                                if(p3 == null){
                                    Salida error = new Salida(110);
                                    System.out.println(error.toString());
                                }else{
                                    Pais p4 = this.existePais(this.jugadorActual.getPaises(), p2.getAbreviatura()); //miramos si el segundo pais NO pertenece al jugador
                                    if(p4 != null){
                                        Salida error = new Salida(111);
                                        System.out.println(error.toString());
                                    }else{
                                        //a este punto 1 y 3, 2 y 4 son iguales. 5 sera igual a p2
                                        Pais p5 = this.existePais(p1.getFronteras(), p2.getAbreviatura());
                                        if(p5 == null){
                                            Salida error = new Salida(112);
                                            System.out.println(error.toString());
                                        }else{
                                            if(p1.getEjercitos() <= 1 || p2.getEjercitos() < 1){
                                                Salida error = new Salida(124);
                                                System.out.println(error.toString());
                                            }else{
                                                this.selectDados(p1, p2);
                                            }
                                        }
                                    }
                                }
                            }
                        }else if(partes.length == 4){
                            Salida error = new Salida(101);
                            System.out.println(error.toString());
                        }else{
                            //la forma de leer el comando es pais dados pais dado
                            Pais p1 = this.existePais(this.paises, partes[1]);
                            Pais p2 = this.existePais(this.paises, partes[3]);
                            if(p1 == null || p2 == null){
                                Salida error = new Salida(109);
                                System.out.println(error.toString());
                            }else{
                                Pais p3 = this.existePais(this.jugadorActual.getPaises(), p1.getAbreviatura()); //miramos si el primer pais pertenece al jugador
                                if(p3 == null){
                                    Salida error = new Salida(110);
                                    System.out.println(error.toString());
                                }else{
                                    Pais p4 = this.existePais(this.jugadorActual.getPaises(), p2.getAbreviatura()); //miramos si el segundo pais NO pertenece al jugador
                                    if(p4 != null){
                                        Salida error = new Salida(111);
                                        System.out.println(error.toString());
                                    }else{
                                        //a este punto 1 y 3, 2 y 4 son iguales. 5 sera igual a p2
                                        Pais p5 = this.existePais(p1.getFronteras(), p2.getAbreviatura());
                                        if(p5 == null){
                                            Salida error = new Salida(112);
                                            System.out.println(error.toString());
                                        }else{
                                            if(p1.getEjercitos() <= 1 || p2.getEjercitos() < 1){
                                                Salida error = new Salida(124);
                                                System.out.println(error.toString());
                                            }else{
                                                String dado1 = partes[2];
                                                String dado2 = partes[4];
                                                String[] partesDado1=dado1.split("x");
                                                String[] partesDado2=dado2.split("x");

                                                Dados dadoAt = new Dados();
                                                Dados dadoDef = new Dados();

                                                //ahora seteas todo:

                                                //ataque
                                                if(partesDado1.length > 3){
                                                    Salida error = new Salida(101); //el comando se ha escrito con demasiados dados
                                                    System.out.println(error.toString());
                                                    dadoAt = null;
                                                }else{
                                                    if(p1.getEjercitos() > 3){
                                                        if(partesDado1.length == 3){
                                                            dadoAt.setX(Integer.parseInt(partesDado1[0]));
                                                            dadoAt.setY(Integer.parseInt(partesDado1[1]));
                                                            dadoAt.setZ(Integer.parseInt(partesDado1[2]));
                                                        }else{ //tiramos 3 dados, si el comando tiene un numero erroneo de dados dara error
                                                            Salida error = new Salida(101); //el comando esta mal escrito
                                                            System.out.println(error.toString());
                                                            dadoAt = null;
                                                        }
                                                    }else{
                                                        if(partesDado1.length == 3){ //si no se tienen mas de 3 y se tiran 3 dados, error
                                                            Salida error = new Salida(124);
                                                            System.out.println(error.toString());
                                                            dadoAt = null;
                                                        }else if((p1.getEjercitos() == 3)){ //con 3 tropas se tiran 2 dados
                                                            if(partesDado1.length == 2){
                                                                dadoAt.setX(Integer.parseInt(partesDado1[0]));
                                                                dadoAt.setY(Integer.parseInt(partesDado1[1]));
                                                            }else{ //tiramos 2 dados, si el comando tiene un numero erroneo de dados dara error
                                                                Salida error = new Salida(101); //el comando esta mal escrito
                                                                System.out.println(error.toString());
                                                                dadoAt = null;
                                                            }
                                                        }else if((p1.getEjercitos() == 2)){
                                                            if(partesDado1.length == 1){
                                                                dadoAt.setX(Integer.parseInt(partesDado1[0]));
                                                            }else{ //tiramos 1 dados, si el comando tiene un numero erroneo de dados dara error
                                                                Salida error = new Salida(101); //el comando esta mal escrito
                                                                System.out.println(error.toString());
                                                                dadoAt = null;
                                                            }
                                                        }else{
                                                            Salida error = new Salida(124); //no hay las suficientes tropas para atacar
                                                            System.out.println(error.toString());
                                                            dadoAt = null;
                                                        }
                                                    }
                                                }

                                                //defensa
                                                if(partesDado2.length >= 3){
                                                        Salida error = new Salida(101); //comando mal escrito
                                                        System.out.println(error.toString());
                                                }else if(p2.getEjercitos() >= 2 && dadoAt != null){ //con 2 o mas trpas se lanzan 2 dadps
                                                    if(partesDado2.length == 2){
                                                        dadoDef.setX(Integer.parseInt(partesDado2[0]));
                                                        dadoDef.setY(Integer.parseInt(partesDado2[1]));
                                                        dadoAt.ordenarDados();
                                                        dadoDef.ordenarDados();
                                                        this.atacar(p1, dadoAt, p2, dadoDef);
                                                    }else if(partesDado2.length > 2){ //si se ponen mas de 2 valores no hay ejerctios disponibles
                                                        Salida error = new Salida(124); //no ejercitos
                                                        System.out.println(error.toString());
                                                    }else{
                                                        Salida error = new Salida(101); //comando incorrecto
                                                        System.out.println(error.toString());
                                                    }
                                                }else if(p2.getEjercitos() == 1 && dadoAt != null){
                                                    if(partesDado2.length > 1){
                                                        Salida error = new Salida(124);
                                                        System.out.println(error.toString());
                                                    }else{
                                                        dadoDef.setX(Integer.parseInt(partes[4]));
                                                        dadoAt.ordenarDados();
                                                        dadoDef.ordenarDados();
                                                        this.atacar(p1, dadoAt, p2, dadoDef);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "obtener": // Comandos sobre el mapa
                    if(checker >= 1){
                        if(partes.length!=3) {
                            Salida error = new Salida(101);
                            System.out.println(error.toString());

                        }else if(partes[1].equals("frontera")){
                            obtenerFronteras(partes[2]);  

                        }else if(partes[1].equals("continente")){
                            obtenerContinente(partes[2]);

                        }else if(partes[1].equals("color")){
                            obtenerColor(partes[2]);

                        }else if(partes[1].equals("paises")){
                            obtenerPaises(partes[2]);
                        }
                    }else{
                        Salida error = new Salida(106);
                        System.out.println(error.toString());
                    }
                    break;
                case "repartir":

                    //en cuanto repartamos ejercitos, debe crearse un nuevo jugador actual, ese sera el que tiene el turno en ese momentoy el que ejecutara los comandos 
                    if(partes.length == 2){
                        //comando que reparte todos y no puede ser ejecutado 2 veces
                        if(this.paisesAsignados(this.paises) == true){ //los comandos anteriores han sido ejecutados con exito se reparten
                            if(iniciar == true){ //los ejercitos ya han sido totalmente repartidos no puede volver a suceder
                                Salida error = new Salida(99);
                                System.out.println(error.toString());
                            }else{
                                //this.repartirEjercitosInicio(); // 1 ejercito en cada pais
                                //auto, esta es la importante parra empezar la partida, deben estar repartidos entre los paises pa empezar todo
                                this.repartirEjercitos();
                                this.jugadorActual.continentesJugador(this.continentes);
                                this.ejercitosTurno(jugadorActual);
                                checker = 5;
                                iniciar = this.partidaIniciada();
                                //partida iniciada con exito
                            }
                        }else{
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }
                    }else{
                        if(this.paisesAsignados(this.paises) == true){
                            repartirEjercitos(partes[2], partes[3]);
                            if(iniciar == false){
                                iniciar = this.partidaIniciada();
                            }
                            //this.ejercitosTurno(jugadorActual);
                            //a partir de aqui es cuando comienzan los turnos, pero, este comando se seguira utilizando no se que hacer
                        }else{
                            Salida error = new Salida(99);
                            System.out.println(error.toString());
                        }
                    }
                    break;
                default:
                    Salida error = new Salida(101);
                    System.out.println(error.toString());
            }
        }
        /*Salida s = new Salida();
        s.imprimirArchivo("\nEOF");*/
    }
    public boolean partidaIniciada(){
        if(this.mapa == null){
            return false;
        }
        if(this.jugadores == null){
            return false;
        }
        if(misionesAsignadas(this.jugadores) == false){
            return false;
        }
        if(paisesAsignados(this.paises) == false){
            return false;
        }
        if(tropasRepartidas(this.jugadores) == false){
            return false;
        }
        for(Jugador j : this.jugadores){
                j.setEjercitos_disponibles(0);
        }
        return true;
    }
    public boolean paisesAsignados(ArrayList<Pais> paises){
        for(Pais p : paises){
            if(p.getJugador() == null){
                return false;
            }
        }
        return true;
    }
    public boolean algunaMision(ArrayList<Jugador> jugadores){
        for(Jugador j : jugadores){
            if(j.getMision() != null){
                return true;
            }
        }
        return false;
    }
    public boolean algunaPais(ArrayList<Pais> paises){
        for(Pais p : paises){
            if(p.getJugador() != null){
                return true;
            }
        }
        return false;
    }
     public boolean tropasRepartidas(ArrayList<Jugador> jugadores){
        for(Jugador j : jugadores){
            if(j.getTropas() < 35){
                return false;
            }
        }
        return true;
    }
    public boolean misionesAsignadas(ArrayList<Jugador> jugadores){
        for(Jugador j : jugadores){
            if(j.getMision() == null){
                return false;
            }
        }
        return true;
    }
    public void checkCombi(Cartas carta1, Cartas carta2, Cartas carta3){
        int ejercitos = 0;
        if((carta1.getClase().equals("Infantería")) && (carta2.getClase().equals("Infantería")) && (carta3.getClase().equals("Infantería"))){  
            ejercitos = 6;
        }
        if((carta1.getClase().equals("Caballería")) && (carta2.getClase().equals("Caballería")) && (carta3.getClase().equals("Caballería"))){  
            ejercitos = 8;
        }
        if((carta1.getClase().equals("Artillería")) && (carta2.getClase().equals("Artillería")) && (carta3.getClase().equals("Artillería"))){ 
            ejercitos = 10;
        }
        if((carta1.getClase().equals("Infantería") || carta2.getClase().equals("Infantería") || carta3.getClase().equals("Infantería")) 
            && (carta1.getClase().equals("Caballería") || carta2.getClase().equals("Caballería") || carta3.getClase().equals("Caballería")) 
            && (carta1.getClase().equals("Artillería") || carta2.getClase().equals("Artillería") || carta3.getClase().equals("Artillería"))){ 
            ejercitos = 12;
        }
        if(ejercitos == 0){
            Salida error = new Salida(121);
            System.out.println(error.toString());
        }else{
            Pais p1 = existePais(this.jugadorActual.getPaises(), carta1.getPais());
            Pais p2 = existePais(this.jugadorActual.getPaises(), carta2.getPais());
            Pais p3 = existePais(this.jugadorActual.getPaises(), carta3.getPais());
            if(p1 != null){
                ejercitos +=1;
            }
            if(p2 != null){
                ejercitos +=1;
            }
            if(p3 != null){
                ejercitos +=1;
            }
            this.jugadorActual.addEjercitos_disponibles(ejercitos);
            this.jugadorActual.getCartas().remove(carta1);
            carta1.setJugador(null);
            this.jugadorActual.getCartas().remove(carta2);
            carta1.setJugador(null);
            this.jugadorActual.getCartas().remove(carta3);
            carta1.setJugador(null);
            String exito = "{\ncartasCambio: " + carta1.printCarta() + carta2.printCarta()
                    + carta3.printCarta() + ",\ncartasQuedan: " + this.jugadorActual.printCartas()
                    + ",\n numeroEjercitosCambiados: " + ejercitos + ",\nnumEjercitosRearme: " +
                    this.jugadorActual.getEjercitos_disponibles() + "\n}";
            System.out.println(exito);
            Salida s = new Salida();
            s.imprimirArchivo(exito);
        }
    }
    private int ejercitosTurno(Jugador jugador){
        int minimo;
        
        minimo = (jugador.getPaises().size())/3;
        
        //ahora debemos mirar si es dueño de algun continente

        for(Continente c : this.continentes){
            if(c.getNombre().equals("Asia") && jugador.esDueño(c)){
                minimo += 7;
            }
            if(c.getNombre().equals("Oceanía") && jugador.esDueño(c)){
                minimo += 2;
            }
            if(c.getNombre().equals("Europa") && jugador.esDueño(c)){
                minimo += 5;
            }
            if(c.getNombre().equals("América del Sur") && jugador.esDueño(c)){
                minimo += 2;
            }
            if(c.getNombre().equals("América del Norte") && jugador.esDueño(c)){
                minimo += 5;
            }
            if(c.getNombre().equals("África") && jugador.esDueño(c)){
                minimo += 3;
            }
        }
        
        return minimo;
    }
    private void rearme(Pais donante, Pais receptor, String Ejer){
        int ejercitos, ejerBefD, ejerAftD, ejerBefR, ejerAftR;
        
        ejercitos = Integer.parseInt(Ejer);
        
        ejerBefD = donante.getEjercitos();
        ejerBefR = receptor.getEjercitos();
        
        ejerAftD = ejerBefD - ejercitos;
        if(ejerAftD <= 0){
            ejerAftD = 1;
            donante.setEjercitos(ejerAftD);
            ejerAftR = ejerBefD - 1;
            receptor.setEjercitos(ejerAftR);
            
        }else{
            ejerAftR = ejerBefR + ejercitos;
            donante.setEjercitos(ejerAftD);
            receptor.setEjercitos(ejerAftR);
        }
        String exito = "{\nnumeroEjercitosInicialesOrigen: " + ejerBefD + 
                ",\nnumeroEjercitosInicialesDestino: " + ejerBefR + 
                ",\nnumeroEjercitosFinalesOrigen: " + ejerAftD + 
                ",\nnumeroEjercitosFinalesDestino: " + ejerAftR + "\n}";
        System.out.println(exito);
        Salida s = new Salida();
        s.imprimirArchivo(exito);
    }
    public void atacar(Pais att, Dados dadoAtt, Pais def, Dados dadoDef){
        int ejercitosPerdidos, ejercitosBefA=0, ejercitosAftA=0, ejercitosAftD=0, ejercitosBefD=0, victorias;
        String cont = "\"null\"";
        
        //en vez de comarar maximos, por victorias, si es 2, se eliminan 2 de los defensores, si es 1 se elimina 1 de cada y si es 0 se eliminan dos de los atacantes
        victorias = dadoAtt.compDado(dadoDef);
        if(victorias == 0){
            //ganan los defensores
            ejercitosPerdidos = dadoAtt.countDados();
            ejercitosBefA = att.getEjercitos();
            ejercitosAftA = ejercitosBefA - ejercitosPerdidos;
            if(ejercitosAftA <= 0){
                //pais atacante conquistado
                att.getJugador().getPaises().remove(att);
                att.setJugador(def.getJugador());
                att.addOcupacion();
                def.getJugador().setPaises(att);
                ejercitosPerdidos = dadoDef.countDados();
                ejercitosBefD = def.getEjercitos();
                ejercitosAftD = ejercitosBefD - ejercitosPerdidos;
                if(ejercitosAftD <= 0){
                    //se necesitan mover demasiados ejercitos
                    if(ejercitosBefD == 1){
                        ejercitosAftA = 1;
                        ejercitosAftD = 0;
                        att.setEjercitos(ejercitosAftA);
                        def.setEjercitos(ejercitosAftD);
                    }else{
                        ejercitosAftA = ejercitosBefD - 1;
                        ejercitosAftD = 1;
                        def.setEjercitos(1);
                        att.setEjercitos(ejercitosAftA);
                    }
                }else{
                    def.setEjercitos(ejercitosAftD);
                    att.setEjercitos(ejercitosPerdidos);
                    ejercitosAftA = ejercitosPerdidos;
                }
                if(def.getJugador().esDueño(att.getContinente())){
                    cont = att.getContinente().printNombre();
                }
                
            }else{
                att.setEjercitos(ejercitosAftA);
                ejercitosBefD = def.getEjercitos();
                ejercitosAftD = def.getEjercitos();
            }
        }else if(victorias == 1){
            ejercitosBefA = att.getEjercitos();
            ejercitosAftA = ejercitosBefA - 1;
            att.setEjercitos(ejercitosAftA);
            ejercitosBefD = def.getEjercitos();
            ejercitosAftD = ejercitosBefD - 1;
            def.setEjercitos(ejercitosAftD);
            if(att.getEjercitos() <= 0){
                //se conquista el atacante
                att.getJugador().getPaises().remove(att);
                att.setJugador(def.getJugador());
                att.addOcupacion();
                def.getJugador().setPaises(att);
                ejercitosPerdidos = dadoDef.countDados();
                ejercitosBefD = def.getEjercitos();
                ejercitosAftD = ejercitosBefD - ejercitosPerdidos;
                if(ejercitosAftD <= 0){
                    ejercitosAftA = ejercitosBefD - 1;
                    ejercitosAftD = 1;
                    def.setEjercitos(1);
                    att.setEjercitos(ejercitosAftA);
                }else{
                    def.setEjercitos(ejercitosAftD);
                    att.setEjercitos(ejercitosPerdidos);
                    ejercitosAftA = ejercitosPerdidos;
                }
                if(def.getJugador().esDueño(att.getContinente())){
                    cont = att.getContinente().printNombre();
                }
            }else if(def.getEjercitos() == 0){
                //se conquista el defensor
                this.conquisto = 1;//el jugador puede recibir cartas
                att.addEjercitos(1);
                def.getJugador().getPaises().remove(def);
                def.setJugador(att.getJugador());
                def.addOcupacion();
                att.getJugador().setPaises(def);
                ejercitosPerdidos = dadoAtt.countDados();
                ejercitosBefA = att.getEjercitos();
                ejercitosAftA = ejercitosBefA - ejercitosPerdidos;
                if(ejercitosAftA <= 0){
                    ejercitosAftD = ejercitosBefA - 1;
                    ejercitosAftA = 1;
                    att.setEjercitos(1);
                    def.setEjercitos(ejercitosAftD);
                }else{
                    att.setEjercitos(ejercitosAftA);
                    def.setEjercitos(ejercitosPerdidos);
                    ejercitosAftD = ejercitosPerdidos;
                }
                if(att.getJugador().esDueño(def.getContinente())){
                    cont = att.getContinente().printNombre();
                }
            }
        }else{
            //ganan los atacantes con 2 vicotrias
            this.conquisto = 1; //el jugador puede recibir cartas
            ejercitosPerdidos = dadoDef.countDados();
            ejercitosBefD = def.getEjercitos();
            ejercitosAftD = ejercitosBefD - ejercitosPerdidos;
            if(ejercitosAftD <= 0){
                //el pais defensor pasa a ser del jugador que ataco
                def.getJugador().getPaises().remove(def);
                def.setJugador(att.getJugador());
                def.addOcupacion();
                att.getJugador().setPaises(def);
                ejercitosPerdidos = dadoAtt.countDados();
                ejercitosBefA = att.getEjercitos();
                ejercitosAftA = ejercitosBefA - ejercitosPerdidos /*+ 1*/;
                if(ejercitosAftA <= 0){
                    ejercitosAftD = ejercitosBefA - 1;
                    ejercitosAftA = 2;
                    att.setEjercitos(1);
                    def.setEjercitos(ejercitosAftD);
                }else{
                    att.setEjercitos(ejercitosAftA);
                    def.setEjercitos(ejercitosPerdidos);
                    ejercitosAftD = ejercitosPerdidos;
                }
                if(att.getJugador().esDueño(def.getContinente())){
                    cont = att.getContinente().printNombre();
                }
            }else{
                def.setEjercitos(ejercitosAftD);
                ejercitosBefA = att.getEjercitos();
                ejercitosAftA = att.getEjercitos();
            }
        }
        //impresion de resultado
        int dadAtt = dadoAtt.countDados();
        int dadDef = dadoDef.countDados();
        String exito = "";
        exito += "{\ndadosAtaque: [ " + dadoAtt.printfDado(dadAtt) + "],\ndadosDefensa: [ " 
                + dadoDef.printfDado(dadDef) + "],\n ejercitosPaisAtaque: [ " + 
                ejercitosBefA + ", " + ejercitosAftA + "],\nejercitosPaisDefensa: [ " + 
                ejercitosBefD + ", " + ejercitosAftD + "],\npaisAtaquePerteneceA: "
                + att.getJugador().printNombre() + ",\npaisDefensaPerteneceA: " +
                def.getJugador().printNombre() + ",\ncontinenteConquistado: " + 
                cont + "\n}";
        //System.out.println(exito);
        consola.imprimir(exito);
        Salida s = new Salida();
        s.imprimirArchivo(exito);
        
    }
    public void selectDados(Pais paisAtt, Pais paisDef){
        int ejerAtt, ejerDef;
        Dados dadoAtt = new Dados();
        Dados dadoDef = new Dados();
        ejerAtt = paisAtt.getEjercitos();
        ejerDef = paisDef.getEjercitos();
        if(ejerAtt == 2){
            dadoAtt.genDados(1);
            if(ejerDef == 1){
                dadoDef.genDados(1);
            }else{
                dadoDef.genDados(2);
            }
            this.atacar(paisAtt, dadoAtt, paisDef, dadoDef);
        }else if(ejerAtt == 3){
            dadoAtt.genDados(2);
            if(ejerDef == 1){
                dadoDef.genDados(1);
            }else{
                dadoDef.genDados(2);
            }
            this.atacar(paisAtt, dadoAtt, paisDef, dadoDef);
        }else{
            dadoAtt.genDados(3);
            if(ejerDef == 1){
                dadoDef.genDados(1);
            }else{
                dadoDef.genDados(2);
            }
            this.atacar(paisAtt, dadoAtt, paisDef, dadoDef);
        }
        
    }
    public Jugador existeJugador(ArrayList<Jugador> jugadores, String jugador){ // en nj guardamos el jugador si este existeque existe
        if(jugadores.isEmpty()==true){
            
            return null;
        }else{
            for(Jugador j : jugadores){
                if(j.getNombre().equals(jugador)==true){
                    return j;
                    }    
            }
            return null;
        }
    }
    public Continente existeContinente(ArrayList<Continente> continentes, String abCont){ // en nj guardamos el jugador si este existeque existe
        if(continentes.isEmpty()==true){
            
            return null;
        }else{
            for(Continente c : continentes){
                if(c.getNombre().equals(abCont)==true){
                    return c;
                    }    
            }
            return null;
        }
    }
    public Pais existePais(ArrayList<Pais> paises, String pais){ // mismo que nj
        if(paises.isEmpty()){
            
            return null;
        }else{
            for(Pais p : paises){
                if(p.getAbreviatura().equals(pais)==true){
                 
                    return p;
                    }    
            }
            return null;
        }
        
    }
    public Cartas estaAsignada(ArrayList<Cartas> carta, String pais, String clase){
        //esta funcion retorna null si la carta no esta asignada, la carta si lo esta.
        if(cartas.isEmpty()){
            return null;
        }else{
            for(Cartas c : carta){
                if((c.getClase().equals(clase)==true) && (c.getPais().equals(pais))){
                    if(c.getJugador() == null){
                        return null;
                    }else{
                        return c;
                    }
                    }    
            }
            return null;
        }
        
    }
    public void asignarMisiones(File file){
        String codigoMision;
        String nombreJugador;

        String jugadorLeido = null;
        BufferedReader bufferLector = null;

        try {

            File fichero = new File("misiones.csv");
            FileReader lector = new FileReader(fichero);
            bufferLector = new BufferedReader(lector);

            while((jugadorLeido= bufferLector.readLine())!=null){

                String[] partes = jugadorLeido.split(";");
                nombreJugador = partes[0];
                codigoMision = partes[1];
                asignarMisiones(nombreJugador, codigoMision);

            }
        }
        catch (Exception excepcion){
            excepcion.printStackTrace();
        }

    }
    
    public void asignarMisiones(String Jugador, String Codigo){
        int checkJug=0, checkPais=0;
        Mision m = new Mision();
        if(existeJugador(this.jugadores, Jugador) == null){ 
                Salida error = new Salida(103);
                System.out.println(error.toString());
        }else if(m.existeMision(Codigo) == 1){
            for(Jugador j : this.jugadores){
                if(j.getNombre().equals(Jugador)==true){
                        if(j.getMision() == null){
                            if(m.getJugador() == null){
                                m = new Mision(Codigo, j);
                                j.setMision(m);
                                System.out.println(m.toString());
                                Salida salida = new Salida();
                                salida.imprimirArchivo(m.toString());
                            }else{
                                Salida error = new Salida(115);
                                System.out.println(error.toString());
                            }
                        }else{
                            Salida error = new Salida(117);
                            System.out.println(error.toString());
                        }

                }    
            }
        }else{
            Salida error = new Salida(116);
            System.out.println(error.toString());
        }
    }
    public void asignarPaises(File file) {
        // Código necesario para asignar países

        String nombrePais;
        String nombreJugador;

        String jugadorLeido = null;
        BufferedReader bufferLector = null;

        try {

            File fichero = new File("asignaciones.csv");
            FileReader lector = new FileReader(fichero);
            bufferLector = new BufferedReader(lector);

            while((jugadorLeido= bufferLector.readLine())!=null){

                String[] partes = jugadorLeido.split(";");
                nombreJugador = partes[0];
                nombrePais = partes[1];
                asignarPaises(nombreJugador, nombrePais);
            }
        }
        catch (Exception excepcion){
            excepcion.printStackTrace();
        }

    }

    /**
     * 
     * @param nombrePais
     * @param nombreJugador 
     */
    public void asignarPaises(String nombreJugador, String nombrePais) {
        // Código necesario para asignar un país a un jugador
        /*dos for, uno va entre todos los jugadores y comprueba que exista y otro 
        recorre los paises para ver si existe, si el nombre pasado coicide con
        alguno de los paises del array del mapa, se añade a jugador y se setea su
        jugador al nombre que ha pasado por la lista*/
        Pais pais = existePais(this.paises, nombrePais);
        Jugador jugador = existeJugador(this.jugadores, nombreJugador);

        if(jugador == null){
                Salida error = new Salida(103);
                System.out.println(error.toString());
            }else{
            //Si el jugador existe lo siguiente es comprobar que exista el pais
                if(pais == null){
                    Salida error = new Salida(109);
                    System.out.println(error.toString());
                }else{ // Existe el pais y existe el jugador, comprobar que ese pais no este asignado ya
                    //pasamos a ver si los paises ya estan en el jugador etc etc etc
                    
                            if(!pais.estaAsignado()){ // El pais no está asignado a ningun jugador, por lo que asignamos e imprimimos
                                pais.setJugador(jugador);
                                jugador.setPaises(pais);
                                pais.setEjercitos(1);
                                jugador.setEjercitos_disponibles(jugador.getEjercitos_disponibles()-1);
                                String fronteras = pais.fronterasToString();
                                String exito = "{\nnombre: " + jugador.printNombre() +",\npais: " + pais.printNombre() + ",\ncontinente: " + pais.getContinente().printNombre() + ",\nfrontera:" + fronteras + "\n}";
                                System.out.println(exito);
                                Salida salida = new Salida();
                                salida.imprimirArchivo(exito);
                                jugador.continentesJugador(this.continentes);
                            }else{ // COdigo de error pais asignado
                                Salida error = new Salida(113);
                                System.out.println(error.toString());
                            }
                        //}
                        
                    //}
// Queda pendiente error de misiones

                }
            }
        }


    /**
     * 
     */
    public void crearMapa() {
        // Código necesario para crear el mapa
        this.mapa = new Mapa();
        this.paises = this.mapa.getPaises();
        this.continentes = this.mapa.getContinentes();
        this.t = new Turno(this.paises);
        //System.out.println(mapa);
    }
    
    public void obtenerFronteras(String npais){
        Pais pais = existePais(this.paises,npais);
        if(pais == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String fronteras = "{\nfronteras: ";
            fronteras += pais.fronterasToString();
            fronteras += "\n}";
            System.out.println(fronteras);
            Salida salida = new Salida();
            salida.imprimirArchivo(fronteras);
        }
    }
    
    public void obtenerContinente(String npais){
        Pais pais = existePais(this.paises,npais);
        if(pais == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String continente = "{ continente: ";
            continente += pais.printCont() + " }";
            System.out.println(continente);
            Salida salida = new Salida();
            salida.imprimirArchivo(continente);
        }
}
    
    public void obtenerColor(String npais){
        Pais pais = existePais(this.paises, npais);
        if(pais == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String color = "{ color: ";
            color += pais.printColor() + " }";
            System.out.println(color);
            Salida salida = new Salida();
            salida.imprimirArchivo(color);
        }
    }

    public void obtenerPaises(String abCont){
        Continente c = existeContinente(this.continentes, abCont);
        if(c == null){
            Salida error = new Salida(109);
            System.out.println(error.toString());
        }else{
            String paises = "{\npaises: [ ";
            for(int i=0; i<c.getPaises().size(); i++){
                if(i==0){
                    paises += c.getPaises().get(i).printNombre();
                }else{
                    paises += ", " + c.getPaises().get(i).printNombre();
                }
                
            }
            paises += " ]\n}";
            System.out.println(paises);
            Salida salida = new Salida();
            salida.imprimirArchivo(paises);
        }
    }

    /**
     * 
     * @param file 
     */
    private void crearJugador(File file) {
        // Código necesario para crear a los jugadores del RISK
        //lo que vamos a hacer es leer el fichero, y en caso de tener jugadores en el fichero llamar a la funcion de crear jugador con nombre y color.
        String nombre;
        String color;
        
        String jugadorleido=null;
        BufferedReader bufferLector= null;
        try {
            File fichero=  new File("jugadores.csv");
            FileReader lector= new FileReader(fichero);
            bufferLector= new BufferedReader(lector);
            while((jugadorleido = bufferLector.readLine())!=null) {
                String[] partes = jugadorleido.split(";");
                nombre= partes[0];
                color= partes[1];
                crearJugador(nombre, color);
            }
            
        }catch(Exception excepcion) {
            excepcion.printStackTrace();
        }
    }
    
    /**
     * 
     * @param file
     */
    private void crearJugador(String nombre, String color) {
        // Código necesario para crear a un jugador a partir de su nombre y color
        //aqui es donde debemos hacer las comprobaciones de todo e imprimir los errores
        //lo primero es comprobar color
        int flag=0;
        if(!(color.equals("AMARILLO") || color.equals("ROJO") || color.equals("AZUL")
                || color.equals("CYAN") || color.equals("VERDE") || color.equals("VIOLETA"))){
            //aqui estaria guai `poner nombre y color del jugador que se ha intentado crear
            Salida error = new Salida(100);
            System.out.println(error.toString()); 
        }
        else if(jugadores.isEmpty()==true){ //si esta vacia metemos directo
            Jugador jugador= new Jugador(nombre, color);
            jugadores.add(jugador);
            this.t.addJugador(jugador);
            System.out.println(jugador.printColorNom());
            Salida salida = new Salida();
            salida.imprimirArchivo(jugador.printColorNom());
            jugador.setAllContinentes(continentes);
            //utilizamos el metodo que solo imprime nombre y color
        }else{
            for (int i=0;i<jugadores.size();i++) {
                if(jugadores.get(i).getNombre().equals(nombre)==true){
                    Salida error = new Salida(104);
                    System.out.println(error.toString()); 
                    flag++;
                }
                else if(jugadores.get(i).getColor().equals(color)){
                    Salida error = new Salida(114);
                    System.out.println(error.toString()); 
                    flag++;
                }
            }
            if(flag==0){
                    Jugador jugador= new Jugador(nombre, color);
                    jugadores.add(jugador);
                    this.t.addJugador(jugador);
                    System.out.println(jugador.printColorNom());
                    Salida salida = new Salida();
                    salida.imprimirArchivo(jugador.printColorNom());
                jugador.setAllContinentes(continentes);
                }
        }
    }

    private void setEjercitosInicio(){
        int numJugadores;
        numJugadores = contarJugadores(this.jugadores);
        
        switch (numJugadores){
                case 3:
                    for (Jugador j: jugadores){
                        //j.setTropas(35);
                        j.setEjercitos_disponibles(35);
                    }
                    break;

                case 4:
                    for (Jugador j: jugadores){
                        //j.setTropas(30);
                        j.setEjercitos_disponibles(30);
                    }
                    break;

                case 5:
                    for (Jugador j: jugadores){
                        //j.setTropas(25);
                        j.setEjercitos_disponibles(25);
                    }
                    break;

                case 6:
                    for (Jugador j: jugadores){
                        //j.setTropas(20);
                        j.setEjercitos_disponibles(20);
                    }
                    break;
            }
        
        
    }
    
    public void repartirEjercitosInicio(){
    
        // Asignar un ejercito a cada pais:
        for(Pais p:paises){
            p.setEjercitos(1);
            p.getJugador().setEjercitos_disponibles(p.getJugador().getEjercitos_disponibles() - 1); // se le resta 1 a los ejercitos disponibles del jugador al que pertenece ese pais
        }
    
    }
    
    
    public void repartirEjercitos(String num, String nombrePais) {

        int numJugadores;
        numJugadores = contarJugadores(this.jugadores);
        Pais pais = existePais(this.paises, nombrePais);
        
        
        int numEjer = Integer.parseInt(num);

        if (pais == null) {

            Salida error = new Salida(109);
            System.out.println(error.toString());
            //error de que pais no existe 109

        }else if(pais.getJugador().equals(this.jugadorActual)){
            
            int ejDisp = pais.getJugador().getEjercitos_disponibles();
            if(ejDisp == 0){
                Salida error = new Salida(119);
                System.out.println(error.toString());
                //error 119 y eso
            }else if(numEjer > ejDisp){
                pais.addEjercitos(ejDisp);
                pais.getJugador().setEjercitos_disponibles(0); // si se quieren asignar mas de los disponibles se asignan solo estos y pasa a haber 0 disponibles
                String exito = "{\npais: \"" + pais.getNombre() + "\",\njugador: \"" + 
                        pais.getJugador().getNombre() + "\",\nnumeroEjercitosAsignados: " 
                        + ejDisp + ",\nnumeroEjercitosTotales: " + pais.getEjercitos() + ",\npaisesOcupadosContinente: " + pais.getContinente().printPaisesEjer(pais) + "\n}";
                System.out.println(exito);
                Salida salida = new Salida();
                salida.imprimirArchivo(exito);
            }else { // caso todo correcto
                pais.addEjercitos(numEjer);
                pais.getJugador().setEjercitos_disponibles(ejDisp - numEjer);
                String exito = "{\npais: \"" + pais.getNombre() + "\",\njugador: \"" + 
                        pais.getJugador().getNombre() + "\",\nnumeroEjercitosAsignados: " 
                        + numEjer + ",\nnumeroEjercitosTotales: " + pais.getEjercitos() + ",\npaisesOcupadosContinente: " + pais.getContinente().printPaisesEjer(pais) + "\n}";
                System.out.println(exito);
                Salida salida = new Salida();
                salida.imprimirArchivo(exito);
            }
        }else{
            Salida error = new Salida(110);
            System.out.println(error.toString());
            // jugador no asignado 110
        }
    }


    public void describirContinente(String nombreContinente) {
        boolean existe = false;
        Continente continente = new Continente();
        for (Continente c : continentes) {
            if (c.getAbreviatura().equals(nombreContinente)) {
                existe = true;
                continente = c;
            }
        }
        if (existe) {

            String jugadoresContinente = "[";
            int i = 0;
            //jugadoresContinente += continente.getJugadores();
            for(Jugador j: this.jugadores){
                if(i == 0){
                    jugadoresContinente += " { " + j.getNombre() + ", " + j.ejercitosColocadosEnContinente(continente) + " }";
                    i++;
                }else{
                    jugadoresContinente += ", { " + j.getNombre() + ", " + j.ejercitosColocadosEnContinente(continente) + " }";
                }
                
            }
            jugadoresContinente += " ]";
            Salida salida = new Salida();
            String exito;
            exito = "{\nnombre: " + continente.getNombre() + ",\nabreviatura: "
                    + continente.getAbreviatura() + ",\njugadores: " + 
                    jugadoresContinente + ",\nnumeroEjercitos: " + 
                    continente.ejercitosColocadosEnContinenteTotal()+",\nrearmeContinente: "
                    + continente.getBonus() + "\n}";
            System.out.println(exito);
            salida.imprimirArchivo(exito);
        }else {// error de que no existe continente 
            
            Salida error = new Salida(102);
            System.out.println(error.toString());
        }
    }



    public int contarJugadores(ArrayList<Jugador> jugadores){
        int contador=0;

        for(Jugador j:jugadores){
            contador++;
        }
        return contador;
    }
    
    public void repartirEjercitos(){

        ArrayList<Continente> continentesOrdenadosMenosFronteras = new ArrayList<>();
        int numJugadores=0;
        // Asignar numero de tropas que tendrá cada jugador en funcion del numero de jugadores
        //ya lo hacemos al crear jugadores

     


      Continente cAux = new Continente();
        for(int k=0; k<continentes.size(); k++){
            cAux = this.continentes.get(1);
            
            //cAux = this.continentes.get(k); tienes que meter una declaracion tipo esto pero ns donde la querras meter
            for (int i = 0; i< continentes.size(); i++){
                while(continentesOrdenadosMenosFronteras.contains(continentes.get(i))){
                    i++;
                    if(i == continentes.size()-1) break;
                }
                if(continentesOrdenadosMenosFronteras.contains(continentes.get(i)) && i == 5){
                    break;
                }
                
                if(this.continentes.get(i).fronterasContinente() <= cAux.fronterasContinente()) 
                    cAux = continentes.get(i);

            }
            continentesOrdenadosMenosFronteras.add(cAux);
        }



        for(Jugador j: jugadores){ // J será el jugador al que se le iran aplicando los algoritmos

            int ejercitosDisponibles = 0;
            boolean hayEjercitos = false;
            Boolean R1 = false;
            Boolean R2 = false;
            Boolean R3 = false;
            Boolean R4 = false;
            Boolean R5 = false;
            Boolean R6 = false;
            Boolean R7 = false;
            Boolean R8 = false;
            Jugador jugadorMenosFronteras = new Jugador(); // jugador que tiene mas paises en el continente con menos fronteras
            Continente masPaisesOcupados = new Continente();
            Continente menosFronteras = new Continente();
            ArrayList<Continente> contCumpR1 = new ArrayList<>(); // continentes que cumplen la condicion R1
            ArrayList<Continente> contCumpR4 = new ArrayList<>(); // continentes que cumplen la condicion R4
            ArrayList<Continente> mismoPorcPaisOc = new ArrayList<>();


            numJugadores = contarJugadores(this.jugadores);
            int numPaisesJugEnC = 0; // numero de paises que tiene un jugador en cada continete

// R1
            for(Continente c:continentes) {
                numPaisesJugEnC = j.calcularNumPaisesEnC(c);
                if (numPaisesJugEnC > (c.getNumPaises() / 2)) { // Mismo jugador tiene mas de la mitad de los paises de un continente
                    contCumpR1.add(c); // Se añade continente
                }
            }

            if(contCumpR1.size() == 1){ // Solo un continente cumple la condicion
                for (Pais p:contCumpR1.get(0).getPaises()) { // en cada pais del continente se asigna el dato dado en el enunciado
                    if (p.getJugador().equals(j)) {
                        if (contCumpR1.get(0).getNombre().equals("Oceanía") || contCumpR1.get(0).getNombre().equals("Asur")) { // Cambia 1.5 o 1 dependiendo del continente
                            p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                            R1 = true;
                        } else {
                            p.addEjercitos((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC)));
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC))));
                            R1 = true;
                        }
                    }

                }
                // R2
            }else if(contCumpR1.size() > 1){ // mas de 1 continente cumple r1

                for(int i=0; i<contCumpR1.size(); i++){
                    if(i==0)  masPaisesOcupados = contCumpR1.get(i); // Primera iteracion
                    if(contCumpR1.get(i).porcentajePaisesOcupados() > masPaisesOcupados.porcentajePaisesOcupados()){
                        masPaisesOcupados = contCumpR1.get(i); // Si el siguiente continente de la lista tiene mas porcentaje de paises asignados este es el nuevo que mas tiene
                    }
                }
                mismoPorcPaisOc.add(masPaisesOcupados);
                for(int i=0; i < contCumpR1.size(); i++){ // Hay que comprobar si hay varios con el mismo porcentaje de ocupados
                    if(contCumpR1.get(i).porcentajePaisesOcupados() == masPaisesOcupados.porcentajePaisesOcupados()){
                        mismoPorcPaisOc.add(contCumpR1.get(i));
                    }
                }
                if(mismoPorcPaisOc.size()==1){ // Asignar ejercitos al continente que tiene el mayor porcentaje de paises ocupados
                    for (Pais p:mismoPorcPaisOc.get(0).getPaises()) { // recorremos paises de ese continente
                        if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                            if (mismoPorcPaisOc.get(0).getNombre().equals("Oceanía") || mismoPorcPaisOc.get(0).getNombre().equals("Asur")) { // Cambia 1.5 o 1 dependiendo del continente
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                                R2 = true;
                            } else {
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC))));
                                R2 = true;
                            }
                        }
                    }


                }else if (mismoPorcPaisOc.size()>1){ // Si hay varios con el mismo porcentaje de asignados hay que comprobar fronteras

                    for(int i=0; i<mismoPorcPaisOc.size(); i++){
                        if(i==0){
                            menosFronteras = mismoPorcPaisOc.get(i); // Primera iteracion
                        }
                        if(mismoPorcPaisOc.get(i).fronterasContinente() < menosFronteras.fronterasContinente()){
                            menosFronteras = mismoPorcPaisOc.get(i); // Si el siguiente continente de la lista tiene menos fronteras este es el nuevo que menos tiene
                        }
                    }

                    // Asignar ejercitos al pais que menos fronteras tiene
                    for (Pais p:menosFronteras.getPaises()) { // recorremos paises de ese continente
                        if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                            if (menosFronteras.getNombre().equals("Oceanía") || menosFronteras.getNombre().equals("Asur")) { // Cambia 1.5 o 1 dependiendo del continente
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                                R2 = true;
                            } else {
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1 * numPaisesJugEnC))));
                                R2 = true;
                            }
                        }
                    }
                }
            }
            // R3
            if(j.getEjercitos_disponibles() > 0){
                hayEjercitos = true;
            }

            // AHora si R3
            if((R1 || R2) && hayEjercitos){
                hayEjercitos = false; // Para recalcular para r6
                R3 = true;

                int paisesUnEjercito = 0;
                for (Pais p:j.getPaises()){
                    if(p.getEjercitos() == 1) paisesUnEjercito++;
                }

                if(paisesUnEjercito <= j.getEjercitos_disponibles()){ // Si hay mas o los mismos ejercitos disponibles que paises con un ejercito se asigna uno y ya
                    for (Pais p:j.getPaises()){
                        if(p.getEjercitos() == 1){
                            p.addEjercitos(1);
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                        }
                    }

                }else{ // mas paises con un ejercito que ejercitos disponibles
                    // Ir a continentes con menos fronteras
                    while (j.getEjercitos_disponibles() > 0){
                        for(Continente c: continentesOrdenadosMenosFronteras){
                            for(Pais p: c.getPaises()){
                                if(p.getJugador().equals(j)){
                                    if(p.getEjercitos() == 1){
                                        p.addEjercitos(1);
                                        j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                                    }
                                }
                            }

                        }
                    }
                }
            } // FIN R3

            // R4

            if(!R1 && !R2 && !R3){

                for(Continente c:continentes) {
                    numPaisesJugEnC = j.calcularNumPaisesEnC(c);
                    if ((numPaisesJugEnC > (c.getNumPaises() / 4)) && (numPaisesJugEnC < (c.getNumPaises() / 2))) { // Mismo jugador tiene mas de la mitad de los paises de un continente
                        contCumpR4.add(c); // Se añade continente
                    }
                }
                if(contCumpR4.size() == 1){ // Solo un continente cumple la condicion
                    for (Pais p:contCumpR4.get(0).getPaises()) { // en cada pais del continente se asigna el dato dado en el enunciado
                        if (p.getJugador().equals(j)) {
                            p.addEjercitos((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC)));
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (1.5 * numPaisesJugEnC))));
                            R4 = true;
                        }

                    }


                } // FIN R4
                // R5
                else if(contCumpR4.size() > 1){ // mas de 1 continente cumple r1

                    for(int i=0; i<contCumpR4.size(); i++){
                        if(i==0)  masPaisesOcupados = contCumpR4.get(i); // Primera iteracion
                        if(contCumpR4.get(i).porcentajePaisesOcupados() > masPaisesOcupados.porcentajePaisesOcupados()){
                            masPaisesOcupados = contCumpR4.get(i); // Si el siguiente continente de la lista tiene mas porcentaje de paises asignados este es el nuevo que mas tiene
                        }
                    }
                    mismoPorcPaisOc.add(masPaisesOcupados);
                    for(int i=0; i < contCumpR4.size(); i++){ // Hay que comprobar si hay varios con el mismo porcentaje de ocupados
                        if(contCumpR4.get(i).porcentajePaisesOcupados() == masPaisesOcupados.porcentajePaisesOcupados()){
                            mismoPorcPaisOc.add(contCumpR4.get(i));
                        }
                    }
                    if(mismoPorcPaisOc.size()==1){ // Asignar ejercitos al continente que tiene el mayor porcentaje de paises ocupados
                        for (Pais p:mismoPorcPaisOc.get(0).getPaises()) { // recorremos paises de ese continente
                            if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC))));
                                R5 = true;
                            }
                        }


                    }else if (mismoPorcPaisOc.size()>1){ // Si hay varios con el mismo porcentaje de asignados hay que comprobar fronteras

                        for(int i=0; i<mismoPorcPaisOc.size(); i++){
                            if(i==0){
                                menosFronteras = mismoPorcPaisOc.get(i); // Primera iteracion
                            }
                            if(mismoPorcPaisOc.get(i).fronterasContinente() < menosFronteras.fronterasContinente()){
                                menosFronteras = mismoPorcPaisOc.get(i); // Si el siguiente continente de la lista tiene menos fronteras este es el nuevo que menos tiene
                            }
                        }

                        // Asignar ejercitos al pais que menos fronteras tiene
                        for (Pais p:menosFronteras.getPaises()) { // recorremos paises de ese continente
                            if (p.getJugador().equals(j)) { // Si el jugador del pais es el mismo que el jugador que cumple la condicion de continetnte
                                p.addEjercitos((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC)));
                                j.setEjercitos_disponibles(j.getEjercitos_disponibles() - ((int) (j.getEjercitos_disponibles() / (2 * numPaisesJugEnC))));
                                R5 = true;

                            }
                        }
                    }
                } // fin R5
            }
            // R6
            if(j.getEjercitos_disponibles() > 0){
                hayEjercitos = true;
            }
            if((R5 || R4) && hayEjercitos){
                R6 = true;

                int paisesUnEjercito = 0;
                for (Pais p:j.getPaises()){
                    if(p.getEjercitos() == 1) paisesUnEjercito++;
                }

                if(paisesUnEjercito <= j.getEjercitos_disponibles()){ // Si hay mas o los mismos ejercitos disponibles que paises con un ejercito se asigna uno y ya
                    for (Pais p:j.getPaises()){
                        if(p.getEjercitos() == 1){
                            p.addEjercitos(1);
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                        }
                    }

                }else{ // mas paises con un ejercito que ejercitos disponibles
                    // Ir a continentes con menos fronteras
                    while (j.getEjercitos_disponibles() > 0){
                        for(Continente c: continentesOrdenadosMenosFronteras){
                            for(Pais p: c.getPaises()){
                                if(p.getJugador().equals(j)){
                                    if(p.getEjercitos() == 1){
                                        p.addEjercitos(1);
                                        j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                                    }
                                }
                            }

                        }
                    }
                }
            }
            // Fin R6

            // R7
            int contadorOcupaCont25 = 0;
            for(Continente c:continentes) {
                numPaisesJugEnC = j.calcularNumPaisesEnC(c);
                if (numPaisesJugEnC < (c.getNumPaises() / 4)) { // Mismo jugador tiene menos del 25% en cada cont
                    contadorOcupaCont25++; // Se añade continente
                }
            }


            if(contadorOcupaCont25 == 6){ // si se cumple la condicion en todos los continentes
                // AHora si R7
                for (Pais p: j.getPaises()){
                    if(jugadores.size() == 3 || jugadores.size() == 4){

                        p.addEjercitos(2 * j.calcularNumPaisesEnC(p.getContinente()));
                        j.setEjercitos_disponibles(j.getEjercitos_disponibles() - (2 * j.calcularNumPaisesEnC(p.getContinente())));
                        R7 = true;

                    }else if(jugadores.size() == 5 || jugadores.size() == 6){

                        p.addEjercitos(3 * j.calcularNumPaisesEnC(p.getContinente()));
                        j.setEjercitos_disponibles(j.getEjercitos_disponibles() - (3 * j.calcularNumPaisesEnC(p.getContinente())));
                        R7 = true;

                    }
                }
            }

            // FIn R7
            // R8
            if(j.getEjercitos_disponibles() > 0){
                hayEjercitos = true;
            }
            if(R7 && hayEjercitos){
                
                int paisesUnEjercito = 0;
                for (Pais p:j.getPaises()){
                    if(p.getEjercitos() == 1) paisesUnEjercito++;
                }

                if(paisesUnEjercito <= j.getEjercitos_disponibles()){ // Si hay mas o los mismos ejercitos disponibles que paises con un ejercito se asigna uno y ya
                    for (Pais p:j.getPaises()){
                        if(p.getEjercitos() == 1){
                            p.addEjercitos(1);
                            j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                        }
                    }

                }else{ // mas paises con un ejercito que ejercitos disponibles

                while (j.getEjercitos_disponibles() > 0){
                    for(Continente c: continentesOrdenadosMenosFronteras){
                        for(Pais p: c.getPaises()){
                            if(p.getJugador().equals(j)){
                                if(p.getEjercitos() == 1){
                                    p.addEjercitos(1);
                                    j.setEjercitos_disponibles(j.getEjercitos_disponibles() - 1);
                                }
                            }
                        }

                    }
                }

            }
        }
    }
        mapa.printMapa();
    }   
}