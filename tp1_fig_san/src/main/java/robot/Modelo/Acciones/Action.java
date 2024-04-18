package robot.Modelo.Acciones;

import robot.Modelo.EstadoDeJuego;

public interface Action {
    public void aplicar(EstadoDeJuego e);
}
