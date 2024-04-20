package robot.GUI;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Vec2D;

import java.util.ArrayList;
import java.util.Vector;

public class Grilla {
    GridPane tablero = new GridPane();
    boolean escuchandoTP = false;

    private int largoX;
    private int largoY;
    public void escucharTP(){ escuchandoTP=true;}

    public Grilla(int cantX, int cantY){
        largoX=cantX;
        largoY=cantY;

    }
    private void setTable(EstadoDeJuego e){
        double anchoCelda = 30;
        double largoCelda = 50;
        for (int i = 0;i < largoX;i++){
            for(int j = 0; j<largoY;j++){
                Rectangle celda = new Rectangle(anchoCelda,largoCelda,Color.BLUE);
                celda.setId(idUnico(i,j));
                tablero.add(celda,i,j);
                Vec2D pos = new Vec2D(i,j);
                celda.setOnMouseClicked(event-> mover(pos,e));
            }
        }

        Rectangle r = (Rectangle) tablero.lookup(idUnico(1,1));
    }
    private String idUnico(int i, int j){
        return String.format("#%d%d",i,j);
    }

    private void mover(Vec2D pos,EstadoDeJuego e){
        Action accion;
        if (escuchandoTP){
            accion= new AccionMovimiento(pos.getX(), pos.getY());
            escuchandoTP= false;
        }else{
            Vec2D mov= pos.restarCon(e.posicionJugador());
            double dx= asignarDiferenciales(mov.getX());
            double dy= asignarDiferenciales(mov.getY());
            accion= new AccionMovimiento(dx,dy);
        }
        e.update(accion);
    }
    private double asignarDiferenciales(double d){
        if (d>1){
            return 1;
        }else if(d<-1){
            return -1;
        }
        return 0;
    }

    public void pintarGrilla(EstadoDeJuego e){
        ArrayList<Rectangle> r1= new ArrayList<>();
        tablero.getCellBounds(1, 2);
        for(Node r: tablero.getChildren()){
            ((Rectangle) r).setFill(Color.TRANSPARENT);
        }
        colocarImagen(e.posicionesRobotsX1(), "robotx1");
        colocarImagen(e.posicionesRobotsX2(), "robotx2");
        colocarImagen(e.posicionesFuego(), "fuego");
        ArrayList<Vec2D> jugador= new ArrayList<>();
        jugador.add(e.posicionJugador());
        colocarImagen(jugador, "player");
    }
    private void colocarImagen(ArrayList<Vec2D> p, String nombre){
        for(Vec2D pint: p){
            int x= (int) pint.getX();
            int y= (int) pint.getY();
            Image imagen= new Image("imagen/"+nombre+".png");
            ((Rectangle) tablero.lookup(idUnico(x,y))).setFill(new ImagePattern(imagen));
        }
    }
}
