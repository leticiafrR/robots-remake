package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;
import robot.Vec2D;

public class AccionTeleport implements Action{
    private Vec2D nuevaPosicion;
    public AccionTeleport(double x, double y){
        nuevaPosicion= new Vec2D(x,y);
    }

    //PRE: e inicializado
    //POST: realiza la jugada (Mueve al jugador hacia la posicion indicada)
    @Override
    public void aplicar(EstadoDeJuego e) {
        if (e.usarTpSafe()) { e.realizarJugada(nuevaPosicion);}
    }
}
