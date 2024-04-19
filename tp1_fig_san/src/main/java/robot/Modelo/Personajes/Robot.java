package robot.Modelo.Personajes;

import robot.Modelo.Vec2D;

public class Robot extends Personaje{
    public Robot(Vec2D posicion){
        super(posicion);
    }
    public void perseguirPosicion(Vec2D posicion){
        Vec2D direccion= posicion.restarCon(this.getPosicion());
        double dx = (direccion.getX()>0) ? 1 :-1;
        if (direccion.getX()==0){dx=0;}
        double dy = (direccion.getY()>0) ? 1 :-1;
        if (direccion.getY()==0){dy=0;}
        Vec2D mov= new Vec2D(dx,dy);
        moverse(getPosicion().sumarCon(mov));
        /*
        if (dx ==0 && dy == 0) { return; } //Quieto
        if (dx == 0 && dy > 0){ moverse(getPosicion().sumarCon(mov));}
        //if (x == 0 && y > 0) { moverse(new Vec2D(this.getPosicion().getX(), this.getPosicion().getY() + 1)); return; } //Arriba
        if (dx == 0 && dy < 0) { moverse(new Vec2D(dx, dy -1)); return; }  //Abajo
        if (dx > 0 && dy == 0) { moverse(new Vec2D(dx + 1, dy)); return; } //Derecha
        if (dx < 0 && dy == 0) { moverse(new Vec2D(dx -1, dy)); return; }   //Izquierda
        if (dx > 0 && dy > 0) { moverse(new Vec2D(dx + 1, dy + 1)); return; } //DiagDerechaUp
        if (dx > 0 && dy < 0) { moverse(new Vec2D(dx + 1, dy -1)); return; } //DiagDerechaAbajo
        if (dx < 0 && dy < 0) { moverse(new Vec2D(dx -1, dy -1)); return; } //DiagIzqAbajo
        if (dx < 0 && dy > 0) { moverse(new Vec2D(dx - 1, dy +1)); return; } //DiagIzqUp
        */
    }

    @Override
    public void moverse(Vec2D posicion) {
        super.setPosicion(new Vec2D(posicion.getX(), posicion.getY()));
    }
}
