package robot.Modelo.Personajes;

import robot.Modelo.Vector2D;

public abstract class Robot extends Personaje{

    public abstract void perseguirPosicion(Vector2D posicion);
}
