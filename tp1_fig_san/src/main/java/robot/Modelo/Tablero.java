package robot.Modelo;

import robot.Modelo.Personajes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class Tablero {

    private static int largoX;
    private  static int largoY;

    // se actualiza en cada startPoint
    private Jugador player;
    private ArrayList<Robot> robots;
    private final HashSet<Fuego> fuegos = new HashSet<>();


    //generacion de robots
    public enum Proportions { X1, X2, CELDAS}
    private final int[] proportion = new int[]{4,1,150};
    private final Random rand = new Random();;


    public Tablero(int largoX, int largoY){
        Tablero.largoX = largoX;
        Tablero.largoY = largoY;
        player = new Jugador(new Vec2D(largoX /2,largoY/2));
    }

    public void startPoint(int nivel){
        player.moverse(new Vec2D(largoX /2,largoY/2));
        int cantRobotX2 = ((proportion[(Proportions.X2).ordinal()] * (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()]);
        int cantRobotX1 = (proportion[(Proportions.X1).ordinal()]* (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()]+nivel;
        if (nivel%3==0){cantRobotX2+=1;}
        crearRobots(cantRobotX1,cantRobotX2);
    }

    private void crearRobots (int cantX1, int cantX2){
        robots = new ArrayList<>();
        HashSet<Vec2D> posicionesRobots = new HashSet<>();
        for (int i = 0;i< cantX1;i++){
            Vec2D pos = posDesocupada(posicionesRobots);
            robots.add(new RobotX1(pos));
            posicionesRobots.add(pos);
        }
        for (int i = 0;i<cantX2;i++){
            Vec2D pos = posDesocupada(posicionesRobots);
            robots.add(new RobotX2(pos));
            posicionesRobots.add(pos);
        }
    }
    private Vec2D posDesocupada(HashSet<Vec2D> posicionesOcupadas){
        Vec2D nuevaPosRobot = vectorRandom();
        while (posicionesOcupadas.contains(nuevaPosRobot) || (nuevaPosRobot==player.getPosicion())){
            nuevaPosRobot = vectorRandom();
        }
        return nuevaPosRobot;
    }

    //elimina robots colisionados, crea fuegos
    private void colisionarRobots(){
        HashSet<Vec2D> posicionesRobots = new HashSet<>();
        for (Robot robot: robots){
            if (posicionesRobots.contains(robot.getPosicion())){
                matarRobot(robot);
            }
           posicionesRobots.add(robot.getPosicion());
        }

    }
    private void matarRobot(Robot robot){
        robots.remove(robot);
        fuegos.add(new Fuego(robot.getPosicion()));
    }

    public void perseguirJugador(){
        for (Robot robot: robots){
            robot.perseguirPosicion(player.getPosicion());
        }
    }
    /*
    moverAPLICAR-> POS=(RAND)   EstadoDEJUEGO.REALIZAARJUGADA(POS) // REALIZARJUGADA-> TABLERO.MOVERJUGADOR(personaje,POS), PERSEGUIR(), COLISIONAR()
    moverTELEPORT->
     */
    public void moverJugador(Vec2D posicion){
        player.moverse(posicion);
    }
    public HashSet<Fuego> getFuegos() {
        return fuegos;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public Jugador getPlayer() {
        return player;
    }
    public boolean win(){
        return false;
    }

    public boolean lose(){
        for (Robot robot: robots){
            if (robot.getPosicion()==player.getPosicion()){
                return true;
            }
        }
        for (Fuego fuego: fuegos){
            if (fuego.getPosicion()==player.getPosicion()){
                return true;
            }
        }
        return false;
    }

    private Vec2D vectorRandom(){
        return new Vec2D(rand.nextDouble(largoX),rand.nextDouble(largoY));
    }

}
