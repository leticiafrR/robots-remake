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
       eliminarIncendiados();
       eliminarColisionados();
    }

    //PRE:
    //POST: encuentra los robots que colisionaron y los manda a eliminar
    private void eliminarColisionados(){
        ArrayList<Robot> muertos=new ArrayList<>();
        int i=0;
        for (Robot r:robots){
            int j =0;
            for (Robot r1: robots){
                if (( j>i) && r1.getPosicion().equals(r.getPosicion())){muertos.add(r);muertos.add(r1);}
                j++;
            }
            i++;
        }
        vaciarMuertos(muertos);
    }

    //PRE:
    //POST: Encuentra los robots que colisionan con fuego y los manda a eliminar
    private void eliminarIncendiados(){
        ArrayList<Robot> muertos=new ArrayList<>();
        for (Robot r:robots){
            for (Vec2D f:fuegos){
                if (f.equals(r.getPosicion())){
                    muertos.add(r);
                }
            }
        }
        vaciarMuertos(muertos);
    }

    //PRE:
    //POST: Elimina los robots muertos y pone un fuego en su lugar
    private void vaciarMuertos(ArrayList<Robot> muertos){
        for (Robot r:muertos){
            fuegos.add(r.getPosicion());
            robots.remove(r);
        }
    }

    //PRE:
    //POST:
    public void moverJugador(Vec2D posicion){
        player.moverse(posicion);
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

    //PRE:rand inicializado
    //POST: genera un vector random dentro del tablero
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

    //PRE:
    //POST:
    public static int getLargoX() {
        return largoX;
    }

    //PRE:
    //POST:
    public static int getLargoY() {
        return largoY;
    }
}
