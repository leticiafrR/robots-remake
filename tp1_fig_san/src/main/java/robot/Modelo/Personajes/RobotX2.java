package robot.Modelo.Personajes;

import robot.Vec2D;

public class RobotX2 extends Robot{
    public RobotX2(Vec2D posicion){
        super(posicion);
    }
    @Override
    public void moverse(Vec2D posicion){
        super.setPosicion(new Vec2D(posicion.getX(), posicion.getY()));
    }

    @Override
    public void perseguirPosicion(Vec2D posicion) {

        super.perseguirPosicion(posicion);
        super.perseguirPosicion(posicion);
    }
}
