package robot.Modelo.Personajes;

import robot.Vec2D;

public class Jugador extends Personaje{
    public Jugador(Vec2D posicion){
        super(posicion);
    }

    //PRE: posicion inicializada
    //POST:
    public void moverse(Vec2D posicion){
        super.setPosicion(posicion);
    }

}
