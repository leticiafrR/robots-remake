package robot.GUI;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.AccionTeleport;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Vec2D;

import java.util.ArrayList;


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

    //PRE: Estado de juego inicializado
    //POST: Crea el tablero visual y le asigna comportamiento
    private void setTable(EstadoDeJuego e){
        double anchoCelda = 30;
        double largoCelda = 30;
        tablero.setStyle("-fx-background-color: #343333;");
      //  tablero.getColumnConstraints().add(new ColumnConstraints(Double.MAX_VALUE)); //Prueba adaptacion celdas
       // tablero.getRowConstraints().add(new RowConstraints(Double.MAX_VALUE));//Prueba adaptacion celdas
        for (int i = 0;i < largoX;i++){
            for(int j = 0; j<largoY;j++){
                Rectangle celda = new Rectangle(anchoCelda,largoCelda);
                configPredet(celda, i, j);
             //   celda.widthProperty().bind(tablero.widthProperty());//Prueba adaptacion celdas
              //  celda.heightProperty().bind(tablero.heightProperty());//Prueba adaptacion celdas
                tablero.getChildren().add(celda);
                Vec2D pos = new Vec2D(i,j);
                celda.setOnMouseClicked(event-> mover(pos,e));
            }
        }
    }

    //PRE: Celda ya inicializada, x e y mayores o iguales que 0
    //POST: Le da a la celda la configuracion predeterminada de adaptacion y lugar
    private void configPredet(Rectangle celda,int x, int y){
        celda.setId(idUnico(x,y));
        GridPane.setVgrow(celda, Priority.ALWAYS);
        GridPane.setHgrow(celda, Priority.ALWAYS);
        GridPane.setConstraints(celda,x,y);
    }
    //PRE: i y j mayor o igual a 0
    //POST: devuelve un ID en base a la posicion
    private String idUnico(int i, int j){
        return ("#"+i+"-"+j);
    }

    //PRE:pos y estado de juego inicializado
    //POST: Asigna el comportamiento de moverse a las celdas seleccionadas y actualiza la tabla
    private void mover(Vec2D pos, EstadoDeJuego e){
        Action accion;
        if (escuchandoTP){
            accion= new AccionTeleport(pos.getX(), pos.getY());
            escuchandoTP=!escuchandoTP;
        }else{
            //Discutir movimiento de personaje
            Vec2D mov= pos.restarCon(e.posicionJugador());
            double dx= asignarDiferenciales(mov.getX());
            double dy= asignarDiferenciales(mov.getY());
            accion= new AccionMovimiento(dx,dy);
        }
        e.update(accion);
        pintarGrilla(e);
    }

    //PRE:
    //POST: devuelve cuantas celdas debe moverse el jugador segun el numero entrante
    private double asignarDiferenciales(double d){
        if (d>1){
            return 1;
        }else if(d<-1){
            return -1;
        }
        return 0;
    }

    //PRE: Estado de juego inicializado
    //POST: Actualiza con imagenes la tabla
    public void pintarGrilla(EstadoDeJuego e){
        for(Node r: tablero.getChildren()){
       //     ((Rectangle) r).setStyle("-fx-background-color: #617061;");
            ((Rectangle) r).setFill(Color.BLACK);
        }
        colocarImagen(e.posicionesRobotsX1(), "robotx1.png");
        colocarImagen(e.posicionesRobotsX2(), "robotx2.png");
        colocarImagen(e.posicionesFuego(), "fuego.png");
        ArrayList<Vec2D> jugador= new ArrayList<>();
        jugador.add(e.posicionJugador());
        colocarImagen(jugador, "player.png");
    }

    //PRE:vector p inicializado, nombre de ruta dentro del proyecto
    //POST: coloca la imagen de la ruta "nombre" en el vector p
    private void colocarImagen(ArrayList<Vec2D> p, String nombre){
        for(Vec2D pint: p){
            int x= (int) pint.getX();
            int y= (int) pint.getY();
            Image imagen= new Image(nombre);
            for(Node r: tablero.getChildren()){
                if( r.getId().equals(idUnico(x,y))){
                    ((Rectangle) r).setFill(new ImagePattern(imagen));
                    break;
                }
            }
        }
    }

    //PRE:
    //POST:
    public GridPane getTablero() {
        return tablero;
    }
}
