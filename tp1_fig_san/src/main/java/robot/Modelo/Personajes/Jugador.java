package robot.Modelo.Personajes;

import robot.Modelo.Vec2D;

public class Jugador extends Personaje{
    public Jugador(Vec2D posicion){
        super(posicion);
    }
    public void moverse(Vec2D posicion){
        super.setPosicion(posicion);
    }

}
