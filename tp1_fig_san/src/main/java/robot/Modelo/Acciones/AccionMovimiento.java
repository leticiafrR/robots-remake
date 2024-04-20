package robot.Modelo.Acciones;

import robot.Modelo.*;
import robot.Vec2D;

public class AccionMovimiento implements Action{
    private Vec2D direccion;
    public AccionMovimiento(double x, double y){
        direccion= new Vec2D(x,y);
    }

    //PRE:e inicializado
    //POST: Ejecuta la jugada y los movimientos de los personajes dentro del tablero
    @Override
    public void aplicar(EstadoDeJuego e) {
        e.realizarJugada(e.posicionJugador().sumarCon(direccion));
    }
}
