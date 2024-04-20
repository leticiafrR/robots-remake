package robot.Modelo;

import robot.Modelo.Personajes.*;
import robot.Vec2D;

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


    //PRE:
    //POST: calcula la cantidad de robots x1 y x2 segun la proporcion y el tamaño del tablero
    //      crea y posiciona al jugador
    public Tablero(int largoX, int largoY){
        Tablero.largoX = largoX;
        Tablero.largoY = largoY;
        player = new Jugador(new Vec2D(largoX /2,largoY/2));
        cantRobotX2min = ((proportion[(Proportions.X2).ordinal()] * (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()]);
        cantRobotX1min = ((proportion[(Proportions.X1).ordinal()] * (largoX*largoY))/ proportion[(Proportions.CELDAS).ordinal()]);
    }

    //PRE: nivel mayor que 0
    //POST: incializa y da posicion a los robots, mueve al jugador al medio
    public void startPoint(int nivel){
        player.moverse(new Vec2D(largoX /2,largoY/2));
        fuegos = new ArrayList<>();
        robots = new ArrayList<>();
        crearRobots(cantRobotX1min+nivel,cantRobotX2min + (nivel/2));
    }

    //PRE:
    //POST: Crea los robots y les da posiciones desocupadas
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

    //PRE: posOcupadas incializada con posiciones ocupadas dentro del tablero
    //POST: Devuelve una posicion desocupada dentro del tablero
    private Vec2D posDesocupada(ArrayList<Vec2D> posOcupadas){
        Vec2D nuevaPosRobot = vectorRandom();
        while (nuevaPosRobot.estaContenido(posOcupadas) || nuevaPosRobot.equals(player.getPosicion())){
            nuevaPosRobot = vectorRandom();
        }
        return nuevaPosRobot;
    }

    //PRE: robots inicializados
    //POST: los robots persiguen al jugador y los colisiona
    public void perseguirJugador(){
        for (Robot robot: robots){
            robot.perseguirPosicion(player.getPosicion());
        }
        colisionarRobots();
    }

    //PRE: robots inicializados
    //POST: incendia a los robots que colisionaron, añade sus posiciones a fuegos y los elimina
    private void colisionarRobots(){
        for (Robot r1: robots) {
            if (incendiarRobot(r1)){continue;}
            for (Robot r2: robots){
                if (matarRobots(r1,r2)) { fuegos.add(r1.getPosicion());}
            }
        }
    }

    //PRE: robot inicializado
    //POST: incendia al robot si no habia fuego en su posicion, si no, lo elimina de robots
    private boolean incendiarRobot(Robot r){
        for (Vec2D f: fuegos){
            if (r.getPosicion().equals(f)){
                robots.remove(r);
                return true;
            }
        }
        return false;
    }

    //PRE: robots inicializados
    //POST: elimina a los si colisionaron
    private boolean matarRobots(Robot r1,Robot r2){
        if (r1.getPosicion().equals(r2.getPosicion())){
            robots.remove(r2);
            robots.remove(r1);
            return true;
        }
        return false;
    }

    //PRE:
    //POST:
    public void moverJugador(Vec2D posicion){
        player.moverse(player.getPosicion().sumarCon(posicion));
    }

    //PRE:
    //POST: indica si no queda ningun robot en el tablero
    public boolean win() { return robots.isEmpty(); }

    //PRE:
    //POST: indica si el jugador colisiono con un robot o fuego
    public boolean lose(){
        for (Robot r: robots){
            if (r.getPosicion().equals(player.getPosicion())) { return true;}
        }
        for (Vec2D f: fuegos){
            if (f.equals(player.getPosicion())) {return true;}
        }
        return false;
    }

    //PRE:
    //POST:
    public Vec2D posJug() { return player.getPosicion(); }
    public Vec2D vectorRandom() { return new Vec2D(rand.nextInt(largoX),rand.nextInt(largoY)); }
    public Jugador getPlayer() { return player;}

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    //PRE: clasebuscada es una clase valida para buscar
    //POST: devuelve las posiciones de los robot depende de su categoria (x1 o x2)
    public ArrayList<Vec2D> getPosicionesRobot(Class claseBuscada){
        ArrayList<Vec2D> pos= new ArrayList<>();
        for(Robot r: robots){
            if (r.getClass()== claseBuscada){
                pos.add(r.getPosicion());
            }
        }
        return pos;
    }

    //PRE:
    //POST: Devuelve las posiciones de los fuegos
    public ArrayList<Vec2D> getFuegos() {
        return fuegos;
    }
}
