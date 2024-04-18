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
        //se coloca otra vez al player al centro
        int cantRobotX2 = ((proportion[(Proportions.X2).ordinal()] * (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()])+nivel;
        int cantRobotX1 = (proportion[(Proportions.X1).ordinal()]* (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()];
        if (nivel%3==0){cantRobotX1+=1;}
        robots = crearRobots(cantRobotX1,cantRobotX2);
    }

    //mueve a los robots y si hay colision avisa
    public boolean colisionRobots(){
        HashSet<Vec2D> posRobots = new HashSet<>();
        boolean foundColission = false;
        for (Robot robot : robots){
            robot.perseguirPosicion(player.getPosicion());
            if (posRobots.contains(robot.getPosicion())){

            }
            posRobots.add(robot.getPosicion());
        }
        for (Robot robot: robots){
            if
        }
    }

    public boolean perseguirJugador(){
        boolean exito = false;
        for (Robot robot: robots){
            robot.perseguirPosicion(player.getPosicion());
            if (robot.getPosicion()==player.getPosicion()){
                exito = true;
            }
        }
        return exito;
    }

    public void moverPersonaje(Personaje p){

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
        return false;
    }

    private ArrayList<Robot> crearRobots(int cantX1, int cantX2){
        HashSet<Vec2D> posicionesRobots = new HashSet<>();
        robots = new ArrayList<>();
        for (int i=0;i<cantX1;i++){
            Vec2D pos = posDesocupada(posicionesRobots);
            robots.add(new RobotX1(pos));
            posicionesRobots.add(pos);
        }
        for (int i=0;i<cantX2;i++){
            Vec2D pos = posDesocupada(posicionesRobots);
            robots.add(new RobotX2(pos));
            posicionesRobots.add(pos);
        }
        return robots;
    }

    private Vec2D posDesocupada(HashSet<Vec2D> posicionesOcupadas {
        Vec2D nuevaPosRobot = vectorRandom();
        while (posicionesOcupadas.contains(nuevaPosRobot) && (nuevaPosRobot.compareTo(player.getPosicion())!=0)){
            nuevaPosRobot = vectorRandom();
        }
        return nuevaPosRobot;
    }
    private Vec2D vectorRandom(){
        return new Vec2D(rand.nextDouble(largoX),rand.nextDouble(largoY));
    }

}
