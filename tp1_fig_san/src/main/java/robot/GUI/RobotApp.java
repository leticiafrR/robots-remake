package robot.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import robot.Modelo.Acciones.AccionStartGame;
import robot.Modelo.EstadoDeJuego;


/**
 * JavaFX App
 */
public class RobotApp extends Application {

    @Override
    public void start(Stage stage) {
        Vista vista = new Vista(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}