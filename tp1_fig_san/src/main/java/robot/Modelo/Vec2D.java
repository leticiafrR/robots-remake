package robot.Modelo;

public class Vec2D implements Comparable<Vec2D> {
    public static final Vec2D ZERO = new Vec2D(0, 0);

    public final double x;
    public final double y;

    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Vec2D other) {
        return this.compareTo(other) == 0;
    }
    @Override

    public String toString() {
        return "(%f, %f)".formatted(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(Vec2D o) {
        if ((o.getX()==x)&&(o.getY())==y){return 0;}
        return 1;
    }
}
