package robot.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import robot.Modelo.*;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.AccionTeleport;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Vec2D;

import java.util.ArrayList;


public class AdminGrilla {
    private GridPane grilla;
    boolean escuchandoTp = false;

    private int largoX;
    private int largoY;

    public AdminGrilla(int cantX, int cantY, EstadoDeJuego e){
        largoX = cantX;
        largoY = cantY;
        grilla = crearGrilla(e);

    }

    //PRE: Estado de juego inicializado
    //POST: Crea y devuelve la grilla con sus respectivas celdas con comportamiento
    private GridPane crearGrilla (EstadoDeJuego e){
        GridPane g = new GridPane();
        g.setStyle("-fx-background-color: #343333;");
        for (int i = 0;i < largoX;i++){
            for(int j = 0; j<largoY;j++){
                var celda = celdaConfigurada( i, j);
                g.getChildren().add(celda);
                celda.setOnMouseClicked(eMover(new Vec2D(i,j),e));
            }
        }
        return g;
    }

    //PRE: Estado de juego inicializado, nombres de archivos png existentes
    //POST: Actualiza con imagenes la tabla
    public void pintarGrilla(EstadoDeJuego e){
        for(Node r: grilla.getChildren()){
            ((Label) r).setBackground(Background.fill(Color.BLACK));
        }
        colocarImagen(e.posicionesRobotsX1(), Vista.getRuta(Vista.RutaFotos.ROBOTX1));
        colocarImagen(e.posicionesRobotsX2(), Vista.getRuta(Vista.RutaFotos.ROBOTX2));
        colocarImagen(e.posicionesFuego(), Vista.getRuta(Vista.RutaFotos.FUEGO));
        ArrayList<Vec2D> jugador= new ArrayList<>();
        jugador.add(e.posicionJugador());
        colocarImagen(jugador, Vista.getRuta(Vista.RutaFotos.JUGADOR));
    }

    //PRE: vector y estado de juego inicializados
    //POST: devueleve un evento ante un click: se mueve al jugador (sea teleport o movimiento)
    private EventHandler<MouseEvent> eMover(Vec2D pos,EstadoDeJuego e){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Action accion;
                if (dejarDeEscucharTp()){
                    accion = new AccionTeleport(pos.getX(), pos.getY());
                }else{
                    Vec2D mov = pos.restarCon(e.posicionJugador());
                    accion = new AccionMovimiento(asignarDiff(mov.getX()), asignarDiff(mov.getY()));
                }
                e.update(accion);
                pintarGrilla(e);
            }
        };
    }

    //PRE:
    //POST: deja de escuhar un posible Teleport, si no se estaba escuchando devuelve false, si sí true
    private boolean dejarDeEscucharTp(){
        if (escuchandoTp){
            escuchandoTp = false;
            return (!escuchandoTp);
        }
        return escuchandoTp;
    }

    //PRE: x e y mayores o iguales que 0
    //POST: crea y devuelve una celda con la configuracion predeterminada de adaptacion y lugar
    private Label celdaConfigurada(int x, int y){
        Label celda = new Label(" ");
        celda.setId(idUnico(x,y));
        celda.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setVgrow(celda, Priority.ALWAYS);
        GridPane.setHgrow(celda, Priority.ALWAYS);
        GridPane.setConstraints(celda,x,y);
        return celda;
    }

    //PRE: i y j mayor o igual a 0
    //POST: devuelve un ID en base a la posicion
    private String idUnico(int i, int j){
        return ("#"+i+"-"+j);
    }

    //PRE:
    //POST: devuelve cuantas celdas debe moverse el jugador segun el numero entrante
    private double asignarDiff(double d){
        if (d==0){return d;}
        d = (d>0) ? 1 :-1;
        return d;
    }


    //PRE:vector p inicializado, nombre de ruta dentro del proyecto
    //POST: coloca la imagen de la ruta "nombre" en el vector p
    private void colocarImagen(ArrayList<Vec2D> p, String nombre){
        for(Vec2D pint: p){
            int x= (int) pint.getX();
            int y= (int) pint.getY();
            Image imagen= new Image(nombre);
            for(Node r: grilla.getChildren()){
                if( r.getId().equals(idUnico(x,y))){
                    ((Label) r).setBackground(Background.fill(new ImagePattern(imagen)) );
                    break;
                }
            }
        }
    }

    public GridPane getGrilla() {
        return grilla;
    }

    public void escucharTp(){ escuchandoTp =true;}
}
