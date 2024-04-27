package robot.Modelo;

import robot.Modelo.Acciones.Action;
import robot.Modelo.Listeners.ListenerGameOver;
import robot.Modelo.Listeners.ListenerLevelUp;
import robot.Modelo.Listeners.ListenerTPSafe;
import robot.Modelo.Personajes.RobotX1;
import robot.Modelo.Personajes.RobotX2;
import robot.Vec2D;
import java.util.ArrayList;

public class EstadoDeJuego {
    private int score;
    private Tablero tablero;
    private int cantSafeTeleport;
    private int nivel;

    private ListenerGameOver listenerGameOver;
    private ListenerTPSafe listenerTPSafe;
    private ListenerLevelUp listenerLevelUp;


    public enum EtiquetasModelo{CANTIDAD_FIL,CANTIDAD_COL,NOMBRE,TP_SAFE,TLP_RANDOM,ESPERAR,SCORE,NIVEL,JUGAR};
    private static final String[] etiquetas = new String[]{"Cantidad de filas:","Cantidad de columnas:","ROBOT", "Teleport Safe:","Teleport Random","Esperar","Score:","nivel:","JUGAR!"};

    public EstadoDeJuego(int largoX, int largoY){
        reset(largoX,largoY);
    }

    //PRE:
    //POST: devuelve el string que representa las etiquetas del modelo.
    public static String getEtiqueta(EtiquetasModelo etiqueta) { return etiquetas[etiqueta.ordinal()];}

    //PRE: tablero ya inicializado y nivel>0
    //POST: pone al tablero en el punto incial de acuerdo al nivel
    public void startLevel(){
        tablero.startPoint(nivel);
    }

    //PRE:
    //POST: Inicializa atributos de Estado de Juego sensibles a una nueva partida (score,cantidad Teleports safes,nivel y tablero) e inicia la partida
    public void reset(int largoX, int largoY){
        score =0;
        cantSafeTeleport=3;
        nivel=1;
        tablero= new Tablero(largoX, largoY);
        startLevel();
    }

    //PRE: tablero ya inicializado
    //POST: actualiza estado de juego si se ganó o perdió
    public void actualizarEstadoJuego(){
        if (tablero.win()){
            nextLevel();
        } else if (tablero.lose()) {
            listenerGameOver.gameOver();
        }
    }

    //PRE:Accion inicializada
    //POST: Ejecuta la accion
    public void update(Action a){
        a.aplicar(this);
    }

    //PRE: direccion inicializada
    //POST: realiza la jugada y los posiciona
    private void posicionarScene(Vec2D nuevaPosicion){
        tablero.moverJugador(nuevaPosicion);
        tablero.perseguirJugador();
    }

    //PRE:
    //POST: Sube de nivel (e incrementa score y teleports seguros) y reinicia el tablero
    public void nextLevel(){
        score+=100*nivel;
        nivel++;
        cantSafeTeleport++;
        startLevel();
        listenerLevelUp.levelUp(etiquetaAct(EtiquetasModelo.NIVEL,nivel),etiquetaAct(EtiquetasModelo.SCORE,score),etiquetaAct(EtiquetasModelo.TP_SAFE,cantSafeTeleport));
    }
    private String etiquetaAct(EtiquetasModelo etiqueta, int v){
        return getEtiqueta(etiqueta)+" "+v;
    }

    public void registrarListenerLevelUp(ListenerLevelUp listener){listenerLevelUp=listener;}

    //PRE:  direccion inicializada
    //POST:
    public void realizarJugada(Vec2D nuevaPosicion){
        if (posicionFueraDelTablero(nuevaPosicion)){
            return;
        }
        posicionarScene(nuevaPosicion);
        actualizarEstadoJuego();
    }

    //PRE: tablero inicializado
    //POST: comprueba si la posicion que debe moverse esta fuera del tablero
    private boolean posicionFueraDelTablero(Vec2D pos){
        int x=(int)pos.getX();
        int y=(int)pos.getY();
        return (x>= tablero.getLargoX() || x< 0 ||y>= tablero.getLargoY() ||y<0);
    }

    //PRE:  tablero inicializado
    //POST:
    public Vec2D posicionJugador(){
        return tablero.posJug();
    }

    //PRE: tablero inicializado
    //POST: retorna un vector aleatorio dentro del tablero
    public Vec2D vecRandom(){
        return tablero.vectorRandom();
    }

    //PRE:  tablero inicializado
    //POST:
    public ArrayList<Vec2D> posicionesFuego(){
        return tablero.getFuegos();
    }

    //PRE: tablero inicializado
    //POST:
    public ArrayList<Vec2D> posicionesRobotsX1() { return tablero.getPosicionesRobot(RobotX1.class);}

    //PRE: tablero inicializado
    //POST:
    public ArrayList<Vec2D> posicionesRobotsX2(){
        return tablero.getPosicionesRobot(RobotX2.class);
    }


    //PRE: listener inicializado
    //POST: le agrega el listener al estado de juego para saber cuando perdio
    public void registrarListenerGameOver(ListenerGameOver listener){
        listenerGameOver = listener;
    }

    //PRE: listener inicializado
    //POST: le agrega el listener al estado de juego para saber cuantos tpSafe disponibles hay
    public void registrarListenerTPdisponibles(ListenerTPSafe listener){
        listenerTPSafe= listener;
    }

    //PRE: listenerTPSafe inicializado y cantSafeTeleport mayor o igual a 0
    //POST: Devuelve true si se puede usar TPsafe y actualiza su valor, si no devuelve falso
    public boolean usarTpSafe(){
        if (cantSafeTeleport==0){
            return false;
        }
        cantSafeTeleport--;
        listenerTPSafe.tpUsado(getEtiqueta(EtiquetasModelo.TP_SAFE)+" "+cantSafeTeleport);
        return true;
    }


    public int getPuntuacion() {
        return score;
    }

    public int getCantSafeTeleport() {
        return cantSafeTeleport;
    }

    public int getNivel() {
        return nivel;
    }


    public static String[] getInstruccionesModelo(){
        return new String[]{ "Para no perder ante los robots ten en cuenta:",
                "- No dejes que te alcancen, los robots te persiguen.",
                "- Hay robots que avanzan de a dos celdas, y robots que avanzan de a una.",
                "- Logras que dos robots mueran si colisionan.",
                "- Por el producto de la muerte de un robot se incencia la celda.",
                "- Pisar una celda incendiada te mata vos y cualquier robot.",
                "- Pasas de nivel cuando todos los robots han muerto.",
                "- Inicialemente tienes 1 un teleport.",
                "- Cuando avanzas de nivel, se te suma un teleport en base a los que tienes.  ",
        };
    }
    public static String[] InstructionsControlGame(){
        return new String[]{"Para moverte puedes: ",
                "- Clickear a donde querés ir (movimiento de a 1)  ",
                "- W: Arriba,S: Abajo,A: Izquierda,D: Derecha",
                "- E: Diagonal derecha superior",
                "- Q: Diagonal izquierda superior",
                "- C: Diagonal derecha inferior",
                "- Z: Diagonal izquierda inferior",
                "- X: Esperar"};
    }
}