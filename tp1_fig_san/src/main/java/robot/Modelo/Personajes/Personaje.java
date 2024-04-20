package robot.Modelo.Personajes;

import robot.Vec2D;

public abstract class Personaje {
    private Vec2D posicion;
    public Personaje(Vec2D posicion){
        this.posicion=posicion;
    }
    public abstract void moverse(Vec2D p);

    public Vec2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Vec2D posicion) {
        this.posicion = posicion;
    }
}
