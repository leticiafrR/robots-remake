package robot.Modelo;

import robot.Modelo.Personajes.*;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private static int largoX;
    private  static int largoY;

    // se actualiza en cada startPoint
    private Jugador player;
    private ArrayList<Robot> robots ;
    private ArrayList<Vec2D> fuegos;


    //generacion de robots
    public enum Proportions { X1, X2, CELDAS}
    private final int[] proportion = new int[]{4,1,150};
    private final Random rand = new Random();;
    private int cantRobotX1min;
    private int cantRobotX2min;


    public Tablero(int largoX, int largoY){
        Tablero.largoX = largoX;
        Tablero.largoY = largoY;
        player = new Jugador(new Vec2D(largoX /2,largoY/2));
        cantRobotX2min = ((proportion[(Proportions.X2).ordinal()] * (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()]);
        cantRobotX1min = ((proportion[(Proportions.X1).ordinal()] * (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()]);
    }

    public void startPoint(int nivel){
        player.moverse(new Vec2D(largoX /2,largoY/2));
        fuegos = new ArrayList<>();
        robots = new ArrayList<>();
        crearRobots(cantRobotX1min+nivel,cantRobotX2min + (nivel/2));
    }

    private void crearRobots (int cantX1, int cantX2){
        ArrayList<Vec2D> posicionesRobots = new ArrayList<>();
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
    private Vec2D posDesocupada(ArrayList<Vec2D> posOcupadas){
        Vec2D nuevaPosRobot = vectorRandom();
        while (nuevaPosRobot.estaContenido(posOcupadas) || nuevaPosRobot.equals(player.getPosicion())){
            nuevaPosRobot = vectorRandom();
        }
        return nuevaPosRobot;
    }


    public void perseguirJugador(){
        for (Robot robot: robots){
            robot.perseguirPosicion(player.getPosicion());
        }
        colisionarRobots();
    }

    private void colisionarRobots(){
        for (Robot r1: robots) {
            if (incendiarRobot(r1)){continue;}
            for (Robot r2: robots){
                if (matarRobots(r1,r2)) { fuegos.add(r1.getPosicion());}
            }
        }
    }
    private boolean incendiarRobot(Robot r){
        for (Vec2D f: fuegos){
            if (r.getPosicion().equals(f)){
                robots.remove(r);
                return true;
            }
        }
        return false;
    }
    private boolean matarRobots(Robot r1,Robot r2){
        if (r1.getPosicion().equals(r2.getPosicion())){
            robots.remove(r2);
            robots.remove(r1);
            return true;
        }
        return false;
    }

    public void moverJugador(Vec2D posicion){
        player.moverse(player.getPosicion().sumarCon(posicion));
    }

    public boolean win() { return robots.isEmpty(); }

    public boolean lose(){
        for (Robot r: robots){
            if (r.getPosicion().equals(player.getPosicion())) { return true;}
        }
        for (Vec2D f: fuegos){
            if (f.equals(player.getPosicion())) {return true;}
        }
        return false;
    }
    public Vec2D posJug() { return player.getPosicion(); }
    public Vec2D vectorRandom() { return new Vec2D(rand.nextDouble(largoX),rand.nextDouble(largoY)); }
    public Jugador getPlayer() { return player;}

    public ArrayList<Robot> getRobots() {
        return robots;
    }
}
