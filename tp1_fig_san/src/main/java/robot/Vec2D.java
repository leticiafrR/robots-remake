package robot;

import java.util.ArrayList;

public class Vec2D {
    public static final Vec2D ZERO = new Vec2D(0, 0);

    private final double x;
    private final double y;

    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //PRE: Vector other inicializado
    //POST: Devuelve si other tiene la misma posicion que el vector que llama
    public boolean equals(Vec2D other) {
        return this.x == other.x && this.y == other.y;
    }

    //PRE: vectores inicializado
    //POST: indica si el vector está contenido en los vectores que pasaron
    public boolean estaContenido(ArrayList<Vec2D> vectores){
        for (Vec2D v: vectores){
            if (this.equals(v)){return true;}
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //PRE: v inicializado
    //POST:Devuelve el VECTOR SUMA entre este vector y v
    public Vec2D sumarCon(Vec2D v){
        return new Vec2D(this.x+v.getX(),this.y+v.getY());
    }

    //PRE:v inicializado
    //POST:Devuelve el VECTOR RESTA entre este vector y v
    public Vec2D restarCon(Vec2D v){
        return new Vec2D(this.x-v.getX(), this.y-v.getY());
    }

}
