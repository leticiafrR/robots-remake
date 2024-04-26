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


    public  enum EtiquetasModelo{CANTIDAD_FIL,CANTIDAD_COL,NOMBRE,TP_SAFE,TLP_RANDOM,ESPERAR,SCORE,NIVEL,JUGAR};
    private static final String[] etiquetas = new String[]{"Cantidad de filas:","Cantidad de columnas:","ROBOT", "Teleport Safe:","Teleport Random","Esperar","Score:","nivel:","JUGAR!"};

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
        score =0;
        cantSafeTeleport=50;
        nivel=1;
        tablero= new Tablero(largoX, largoY);
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
        score+=100*nivel;
        nivel++;
        cantSafeTeleport++;
        startGame();
        listenerLevelUp.levelUp(EtiquetasModelo.NIVEL+" "+nivel,EtiquetasModelo.SCORE+" "+score,EtiquetasModelo.TP_SAFE+" "+cantSafeTeleport);
    }

    public void registrarListenerLevelUp(ListenerLevelUp listener){listenerLevelUp=listener;}

    //PRE:  direccion inicializada
    //POST:
    public void realizarJugada(Vec2D nuevaPosicion){
        if (posicionFueraDelTablero((int) nuevaPosicion.getX(), (int) nuevaPosicion.getY())){
            return;
        }
        posicionarScene(nuevaPosicion);
        actualizarEstadoJuego();
    }

    //PRE: tablero inicializado
    //POST: comprueba si la posicion que debe moverse esta fuera del tablero
    private boolean posicionFueraDelTablero(int x, int y){
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

    public void registrarListenerTPdisponibles(ListenerTPSafe listener){
        listenerTPSafe= listener;
    }

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
    public void setPuntuacion(int puntuacion) {
        this.score = puntuacion;
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


    //falta terminar xd
    public static String[] getInstruccionesModelo(){
        return new String[]{ "Para no perder ante los robots ten en cuenta:",
                "- No dejes que te alcancen, los robots te persiguen.",
                "- Hay robots que avanzan de a dos celdas, y robots que avanzan de a una.",
                "- Lográs que dos robots mueran si colisionan.",
                "- Producto de la muerte de un robot hay celdas incendiadas.",
                "- Pisar una celda incendiada te mata vos y cualquier robot",
                "- pasas de nivel cuando todos los robots muerieron",
                "- inicialemente tienes 1 un teleport ",
        };
    }
    public static String[] InstructionsControlGame(){
        return new String[]{"Para moverte puedes: ",
                "- Clickear a donde querés ir (movimiento de a 1)",
                "- W: Arriba,S: Abajo,A: Izquierda,D: Derecha",
                "- E: Diagonal derecha superior",
                "- Q: Diagonal izquierda superior",
                "- C: Diagonal derecha inferior",
                "- Z: Diagonal izquierda inferior",
                "- X: Esperar"};
    }
}