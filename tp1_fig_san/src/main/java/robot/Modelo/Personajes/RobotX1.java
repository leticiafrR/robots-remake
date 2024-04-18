package robot.Modelo.Personajes;

import robot.Modelo.Vec2D;

public class RobotX1 extends Robot{

    public RobotX1(Vec2D posicion){
        super(posicion);
    }
    @Override
    public void perseguirPosicion(Vec2D posicion) {
        super.perseguirPosicion(posicion);
    }
    @Override
    public void moverse(Vec2D posicion){
        super.setPosicion(posicion);
    }
}
