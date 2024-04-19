package robot.Modelo.Acciones;

import robot.Modelo.*;

public class AccionMovimiento implements Action{
    private Vec2D direccion;
    public AccionMovimiento(double x, double y){
        direccion= new Vec2D(x,y);
    }
    @Override
    public void aplicar(EstadoDeJuego e) {
        e.realizarJugada(e.posicionJugador().sumarCon(direccion));
    }
}
