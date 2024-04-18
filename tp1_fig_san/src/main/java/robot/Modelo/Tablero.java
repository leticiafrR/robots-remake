package robot.Modelo;

import robot.Modelo.Personajes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class Tablero {
    private static int largoX;
    private  static int largoY;

    public enum Proportions { X1, X2, CELDAS}
    private final int[] p;
    private static Random rand;
    private final Jugador player;
    private ArrayList<Robot> robots;
    private final HashSet<Fuego> fuegos;
    private final HashSet<Vec2D> posicionesRobots;

    public Tablero(int largoX, int largoY){
        p = new int[]{4,1,150};
        Tablero.largoX = largoX;
        Tablero.largoY = largoY;
        rand = new Random();
        posicionesRobots = new HashSet<>();
        fuegos = new HashSet<>();
        player = new Jugador(new Vec2D(largoX /2,largoY/2));
        robots = crearRobots(largoX*largoY);
    }

    private ArrayList<Robot> crearRobots(int cantCeldas){
        int cantX2 = (p[(Proportions.X2).ordinal()] * cantCeldas)/ p[(Proportions.CELDAS).ordinal()];
        int cantX1 = (p[(Proportions.X1).ordinal()]* cantCeldas)/ p[(Proportions.CELDAS).ordinal()];
        robots = new ArrayList<>();
        for (int i=0;i<cantX1+cantX2;i++){
            Vec2D pos = posDesocupada();
            if (i<cantX1){ robots.add(new RobotX1(pos));} else { robots.add(new RobotX2(pos));}
            posicionesRobots.add(pos);
        }
        return robots;
    }

    private Vec2D posDesocupada(){
        Vec2D nuevaPosRobot = vectorRandom();
        while (posicionesRobots.contains(nuevaPosRobot) && (nuevaPosRobot.compareTo(player.getPosicion())!=0)){
            nuevaPosRobot = vectorRandom();
        }
        return nuevaPosRobot;
    }
    private Vec2D vectorRandom(){
        return new Vec2D(rand.nextDouble(largoX),rand.nextDouble(largoY));
    }
    public void startPoint(int nivel){

    }

    public boolean colisionRobots(){
        return false;
    }

    public boolean perseguirJugador(){
        return false;
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



    private boolean colission(){

    }
}
