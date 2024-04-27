package robot.Modelo.Personajes;

import robot.Vec2D;

public class Robot extends Personaje{
    public Robot(Vec2D posicion){
        super(posicion);
    }

    //PRE: posicion inicializada
    //POST: mueve al robot hacia la posicion del jugador
    public void perseguirPosicion(Vec2D posicion){
        Vec2D direccion= posicion.restarCon(this.getPosicion());
        double dx = (direccion.getX()>0) ? 1 :-1;
        if (direccion.getX()==0){dx=0;}
        double dy = (direccion.getY()>0) ? 1 :-1;
        if (direccion.getY()==0){dy=0;}
        Vec2D mov= new Vec2D(dx,dy);
        moverse(getPosicion().sumarCon(mov));
    }

    @Override
    public void moverse(Vec2D posicion) {
        super.setPosicion(posicion);
    }
}
