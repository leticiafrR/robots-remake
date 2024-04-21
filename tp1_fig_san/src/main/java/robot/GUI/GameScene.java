package robot.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import robot.Modelo.EstadoDeJuego;

public class GameScene {
    private Scene principal;
    private Grilla grilla;

    private PanelPosterior pp;
    public GameScene(int columnas, int filas, EstadoDeJuego gameState){
        VBox caja = new VBox();
        grilla = new Grilla(columnas, filas,gameState);
        pp = new PanelPosterior();
        //Stage gr= new Stage();
        HBox panelPost = pp.crearHBox(gameState, grilla);
        Stage conjunto= new Stage();
        grilla.pintarGrilla(gameState);

        GridPane tabla = grilla.getTablero();
        VBox.setVgrow(tabla, Priority.ALWAYS);
        VBox.setVgrow(panelPost, Priority.ALWAYS);
        tabla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); //Revisar adaptacion
        panelPost.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);//''       ''
        tabla.setAlignment(Pos.CENTER);
        tabla.setHgap(5);
        tabla.setVgap(5);
        caja.getChildren().add(tabla);
        caja.getChildren().add(panelPost);
        principal= new Scene(caja, (columnas*40), (filas*40)+75);
    }

    public Scene getPrincipal() {
        return principal;
    }
}
