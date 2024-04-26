package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;

public interface Action {
    void aplicar(EstadoDeJuego e);
}
