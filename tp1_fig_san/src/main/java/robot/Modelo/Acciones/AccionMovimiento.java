package robot.Modelo.Acciones;

import robot.Modelo.*;

public class AccionMovimiento implements Action{
    private Vec2D direccion;

    @Override
    public void aplicar(EstadoDeJuego e) {
        e.realizarJugada(direccion);
    }
}
