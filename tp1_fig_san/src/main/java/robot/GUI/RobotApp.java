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
        EstadoDeJuego game = null;


        var label = new Label("Hello, JavaFX "  + ", running on Java "  + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}