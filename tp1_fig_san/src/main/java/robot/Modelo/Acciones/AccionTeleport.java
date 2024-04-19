package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;
import robot.Modelo.Vec2D;

public class AccionTeleport implements Action{
    private Vec2D nuevaPosicion;
    public AccionTeleport(double x, double y){
        nuevaPosicion= new Vec2D(x,y);
    }
    @Override
    public void aplicar(EstadoDeJuego e) {
        e.realizarJugada(nuevaPosicion);
    }
}
