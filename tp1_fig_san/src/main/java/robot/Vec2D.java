package robot;

import java.util.ArrayList;

public class Vec2D {
    public static final Vec2D ZERO = new Vec2D(0, 0);

    public final double x;
    public final double y;

    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Vec2D other) {
        return this.x == other.x && this.y == other.y;
    }

    public boolean estaContenido(ArrayList<Vec2D> vectores){
        for (Vec2D v: vectores){
            if (this.equals(v)){return true;}
        }
        return false;
    }
   // @Override


    /*public String toString() {
        return "(%f, %f)".formatted(x, y);
    }*/

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vec2D sumarCon(Vec2D v){
        return new Vec2D(this.x+v.getX(),this.y+v.getY());
    }

    public Vec2D restarCon(Vec2D v){
        return new Vec2D(this.x-v.getX(), this.y-v.getY());
    }

}
