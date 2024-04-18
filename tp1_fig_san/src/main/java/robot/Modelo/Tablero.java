package robot.Modelo;

import robot.Modelo.Personajes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;


public class Tablero {
    //palabra 'long' reservada del lenguaje alternativa:"Long"
    private static int largoX;
    private  static int largoY;

    public HashSet<Fuego> getFuegos() {
        return fuegos;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public Jugador getPlayer() {
        return player;
    }

    public enum proportions { X1, X2, CELDAS}
    private int[] p;
    private static Random rand;
    private Jugador player;
    private ArrayList<Robot> robots;
    private HashSet<Fuego> fuegos;

    public Tablero(int largoX, int largoY){
        p = new int[]{4,1,150};
        this.largoX = largoX;
        this.largoY = largoY;
        player = new Jugador(new Vec2D(largoX/2,largoY/2));
        robots=crearRobots(largoX*largoY);

    }
    private ArrayList<Robot> crearRobots(int cantCeldas){
        int cantX2 = (p[(proportions.X2).ordinal()] * cantCeldas)/ p[(proportions.CELDAS).ordinal()];
        int cantX1 = (p[(proportions.X1).ordinal()]* cantCeldas)/ p[(proportions.CELDAS).ordinal()];
        robots = new ArrayList<>();
        for (int i=0;i<cantX1+cantX2;i++){

            do{Vec2D pos vectorRandom();
            }while();


            if (i<cantX1){robots.add(new RobotX1(vectorRandom()))}
        }

    }

    private

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

    public boolean win(){
        return false;
    }

    public boolean lose(){
        return false;
    }

    private Vec2D vectorRandom(){
        return new Vec2D(rand.nextDouble((double)largoX),rand.nextDouble((double)largoY));
    }

    private boolean colission(){

    }
}
