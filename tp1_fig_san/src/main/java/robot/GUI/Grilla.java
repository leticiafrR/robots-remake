package robot.GUI;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Vec2D;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Grilla {
    private GridPane tablero = new GridPane();
    boolean escuchandoTP = false;

    private int largoX;
    private int largoY;
    public void escucharTP(){ escuchandoTP=true;}

    public Grilla(int cantX, int cantY, EstadoDeJuego e){
        largoX=cantX;
        largoY=cantY;
        setTable(e);
    }
    private void setTable(EstadoDeJuego e){
        double anchoCelda = 30;
        double largoCelda = 50;
        for (int i = 0;i < largoX;i++){
            for(int j = 0; j<largoY;j++){
                Rectangle celda = new Rectangle(anchoCelda,largoCelda,Color.BLUE);
                celda.setId(idUnico(i,j));
                celda.setHeight(Double.MAX_VALUE); //Revisar Como se adaptan en la grilla
                celda.setWidth(Double.MAX_VALUE);  // ''      ''  ''  ''    ''  ''     ''
                GridPane.setVgrow(celda, Priority.ALWAYS);
                GridPane.setHgrow(celda, Priority.ALWAYS);
                tablero.add(celda,i,j);
                Vec2D pos = new Vec2D(i,j);
                celda.setOnMouseClicked(event-> mover(pos,e));
            }
        }
    }
    private String idUnico(int i, int j){
        return String.format("#%d%d",i,j);
    }

    private void mover(Vec2D pos, EstadoDeJuego e){
        Action accion;
        if (escuchandoTP){
            accion= new AccionMovimiento(pos.getX(), pos.getY());
            escuchandoTP=!escuchandoTP;
        }else{
            Vec2D mov= pos.restarCon(e.posicionJugador());
            double dx= asignarDiferenciales(mov.getX());
            double dy= asignarDiferenciales(mov.getY());
            accion= new AccionMovimiento(dx,dy);
        }
        e.update(accion);
        pintarGrilla(e);
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
        for(Node r: tablero.getChildren()){
            ((Rectangle) r).setFill(Color.BLUE);
        }
        colocarImagen(e.posicionesRobotsX1(), "robotx1.png");
        colocarImagen(e.posicionesRobotsX2(), "robotx2.png");
        colocarImagen(e.posicionesFuego(), "fuego.png");
        ArrayList<Vec2D> jugador= new ArrayList<>();
        jugador.add(e.posicionJugador());
        colocarImagen(jugador, "player.png");
    }

    private void colocarImagen(ArrayList<Vec2D> p, String nombre){
        for(Vec2D pint: p){
            int x= (int) pint.getX();
            int y= (int) pint.getY();
            Image imagen= new Image(nombre);
            ((Rectangle) tablero.lookup(idUnico(x,y))).setFill(new ImagePattern(imagen));
        }
    }

    public GridPane getTablero() {
        return tablero;
    }
}
