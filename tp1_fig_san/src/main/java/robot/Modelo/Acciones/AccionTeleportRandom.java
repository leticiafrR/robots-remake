package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;
import robot.Modelo.Vec2D;

import java.util.Random;

public class AccionTeleportRandom implements Action{
    @Override
    public void aplicar(EstadoDeJuego e) {
        e.realizarJugada(e.vecRandom());
    }
}
