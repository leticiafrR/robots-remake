package robot.Modelo;

import robot.Modelo.Acciones.Action;
import robot.Vec2D;

import java.util.ArrayList;

public class EstadoDeJuego {
    private int puntuacion;
    private Tablero tablero;
    private int cantSafeTeleport;
    private int nivel;


    public EstadoDeJuego(int largoX, int largoY){
        reset(largoX,largoY);
    }
    public void startGame(){
        tablero.startPoint(nivel);
    }
    public void reset(int largoX, int largoY){
        puntuacion=0;
        cantSafeTeleport=1;
        nivel=1;
        tablero= new Tablero(largoX, largoY);
        startGame();
    }
    public void actualizarEstadoJuego(){
        if (tablero.win()){
            nextLevel();
        } else if (tablero.lose()) {
            gameOver();
        }
    }
    public void update(Action a){
        a.aplicar(this);
        actualizarEstadoJuego();
    }
    private void posicionarScene(Vec2D direccion){
        tablero.moverJugador(direccion);
        tablero.perseguirJugador();
    }

    public void gameOver(){
        nivel=1;
        puntuacion=0;
        startGame();
    }
    public void nextLevel(){
        nivel++;
        cantSafeTeleport++;
        startGame();
    }
    public void realizarJugada(Vec2D direccion){
        posicionarScene(direccion);
        actualizarEstadoJuego();
    }
    public Vec2D posicionJugador(){
        return tablero.posJug();
    }
    public Vec2D vecRandom(){
        return tablero.vectorRandom();
    }

    public ArrayList<Vec2D> posicionesFuego(){
        return tablero.getFuegos();
    }

    public ArrayList<Vec2D> posicionesRobotsX1(){
        return tablero.getPosicionesRobotX1();
    }

    public ArrayList<Vec2D> posicionesRobotsX2(){
        return tablero.getPosicionesRobotX2();
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