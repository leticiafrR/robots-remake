package robot.Modelo.Personajes;

import robot.Modelo.Vec2D;

public class Robot extends Personaje{
    public Robot(Vec2D posicion){
        super(posicion);
    }
    public void perseguirPosicion(Vec2D posicion){
        double x = posicion.getX() - this.getPosicion().getX();
        double y = posicion.getY() - this.getPosicion().getY();
        if (x ==0 && y == 0) { return; } //Quieto
        if (x == 0 && y > 0) { moverse(new Vec2D(x, y + 1)); return; } //Arriba
        if (x == 0 && y < 0) { moverse(new Vec2D(x, y -1)); return; }  //Abajo
        if (x > 0 && y == 0) { moverse(new Vec2D(x + 1, y)); return; } //Derecha
        if (x < 0 && y == 0) { moverse(new Vec2D(x -1, y)); return; }   //Izquierda
        if (x > 0 && y > 0) { moverse(new Vec2D(x + 1, y + 1)); return; } //DiagDerechaUp
        if (x > 0 && y < 0) { moverse(new Vec2D(x + 1, y -1)); return; } //DiagDerechaAbajo
        if (x < 0 && y < 0) { moverse(new Vec2D(x -1, y -1)); return; } //DiagIzqAbajo
        if (x < 0 && y > 0) { moverse(new Vec2D(x - 1, y +1)); return; } //DiagIzqUp
    }
    @Override
    public void moverse(Vec2D posicion) {
        super.setPosicion(new Vec2D(posicion.getX(), posicion.getY()));
    }
}
