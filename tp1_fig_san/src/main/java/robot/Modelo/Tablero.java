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
    private ArrayList<Robot> robots ;
    private ArrayList<Fuego> fuegos;


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

    //elimina robots colisionados, crea fuegos
    private void colisionarRobots(){
        ArrayList<Vec2D> posicionesRobots = new ArrayList<>();
        for (Robot robot: robots) {
            if (robot.getPosicion().estaContenido(posicionesRobots)) {
                matarRobot(robot);
            }
            posicionesRobots.add(robot.getPosicion());
        }
        for (Robot robot)
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
    moverAPLICAR-> POS=(RAND)   EstadoDEJUEGO.REALIZAARJUGADA(POS) // REALIZARJUGADA-> TABLERO.MOVERJUGADOR(personaje,POS), PERSEGUIR(), COLISIONAR() // ACTUALIZARESTADO
    moverTELEPORT-> POS     ESTADODEJUEGO.REALIZARJUGA
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
