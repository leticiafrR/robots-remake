package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;

public class AccionStartGame implements Action{

    private int tamX;
    private int tamY;

    public AccionStartGame(int tamX, int tamY) {
        this.tamX = tamX;
        this.tamY = tamY;
    }

    //PRE: e inicializado
    //POST: Ejecuta el reset (reinicia el juego)
    @Override
    public void aplicar(EstadoDeJuego e) {
        e.reset(tamX,tamY);
    }
}
