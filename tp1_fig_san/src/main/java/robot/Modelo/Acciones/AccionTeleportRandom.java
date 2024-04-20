package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;

public class AccionTeleportRandom implements Action{

    //PRE: e inicializado
    //POST:realiza la jugada (mueve al jugador a una posicion aleatoria)
    @Override
    public void aplicar(EstadoDeJuego e) {
        e.realizarJugada(e.vecRandom());
    }
}
