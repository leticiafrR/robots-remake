package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;

public class AccionTeleportRandom implements Action{
    @Override
    public void aplicar(EstadoDeJuego e) {
        e.realizarJugada(e.vecRandom());
    }
}
