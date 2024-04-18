package robot.Modelo;

import robot.Modelo.Acciones.Action;

import java.util.ArrayList;

public class EstadoDeJuego {
    private int puntuacion;
    private Tablero tablero;
    private int cantSafeTeleport;
    private int nivel;

    public void actualizarEstadoJuego(){
        if (tablero.win()){
            nextLevel();
        } else if (tablero.lose()) {
            gameOver();
        }

    }
    public void startGame(){
        tablero.startPoint(nivel);
    }
    public void gameOver(){
        nivel=1;
        startGame();
    }
    public void nextLevel(){
        nivel++;
        startGame();
    }
    private void posicionarEscena(){

    }
    public void realizarJugada(){

    }
    public void update(Iterable<Action> acciones){
        for(Action accion: acciones){
            accion.aplicar(this);
        }
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
