package robot.Modelo;

import robot.Modelo.Acciones.Action;
import robot.Modelo.Personajes.RobotX1;
import robot.Modelo.Personajes.RobotX2;
import robot.Vec2D;

import java.util.ArrayList;

public class EstadoDeJuego {
    private int puntuacion;
    private Tablero tablero;
    private int cantSafeTeleport;
    private int nivel;

    private boolean jugable;
    private ListenerGameOver listenerGameOver;

    public  enum EtiquetasModelo{CANTIDAD_FIL,CANTIDAD_COL,NOMBRE,TP_SAFE,TLP_RANDOM,ESPERAR,SCORE,NIVEL,JUGAR};
    private static final String[] etiquetas = new String[]{"Cantidad de filas:","Cantidad de columnas:","ROBOT", "Teleport Safe","Teleport Random","Esperar","Score: ","nivel: ","JUGAR!"};

    public static String getEtiqueta(EtiquetasModelo etiqueta) { return etiquetas[etiqueta.ordinal()];}
    //PRE:
    //POST:
    public EstadoDeJuego(int largoX, int largoY){
        reset(largoX,largoY);
    }

    //PRE: tablero ya inicializado y nivel>0
    //POST:
    public void startGame(){
        tablero.startPoint(nivel);
    }

    //PRE:
    //POST: Inicializa atributos de Estado de Juego
    public void reset(int largoX, int largoY){
        puntuacion=0;
        cantSafeTeleport=1;
        nivel=1;
        tablero= new Tablero(largoX, largoY);
        jugable= true;
        startGame();
    }

    //PRE: tablero ya inicializado
    //POST: pregunta si se ganó o si se perdió
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
        actualizarEstadoJuego();
    }

    //PRE: direccion inicializada
    //POST: realiza la jugada y los posiciona
    private void posicionarScene(Vec2D nuevaPosicion){
        tablero.moverJugador(nuevaPosicion);
        tablero.perseguirJugador();
    }

    //PRE:
    //POST: Sube de nivel y reinicia el tablero
    public void nextLevel(){
        nivel++;
        cantSafeTeleport++;
        startGame();
    }

    //PRE:  direccion inicializada
    //POST:
    public void realizarJugada(Vec2D nuevaPosicion){
        posicionarScene(nuevaPosicion);
        actualizarEstadoJuego();
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

    //PRE:
    //POST:
    public boolean isJugable() {
        return jugable;
    }

    //PRE: listener inicializado
    //POST: le agrega el listener al estado de juego para saber cuando perdio
    public void registrarListener(ListenerGameOver listener){
        listenerGameOver = listener;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public Tablero getTablero() {
        return tablero;
    }
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    public int getCantSafeTeleport() {
        return cantSafeTeleport;
    }
    public void setCantSafeTeleport(int cantSafeTeleport) {
        this.cantSafeTeleport = cantSafeTeleport;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}