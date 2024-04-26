package robot.GUI;

import javafx.application.Application;
import javafx.stage.Stage;



/**
 * JavaFX App
 */

public class RobotApp extends Application {

    @Override
    public void start(Stage stage) {

        Vista vista = new Vista(stage);
        vista.iniciarJuego();
    }

    public static void main(String[] args) {
        launch();
    }

}