package robot.Modelo.Personajes;

import robot.Modelo.Vector2D;

public abstract class Personaje {
    private Vector2D posicion;
    public abstract void moverse(Vector2D p);
}
