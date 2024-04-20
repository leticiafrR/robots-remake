package robot.GUI;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import robot.Modelo.Acciones.AccionMovimiento;
import javafx.scene.Scene;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;

import java.util.Map;

public class Vista {
    private Stage window;
    private Scene actualScene;
    private Scene mainScene;
    private EstadoDeJuego gameState;

    private String nombreApp = "ROBOT";
    public Vista(Stage stage){
        window = stage;
        actualScene = setFirstScene();
        setStage();
    }
    private void setStage(){
        window.setTitle(nombreApp);
        window.setHeight(600);
        window.setWidth(600);
        window.setResizable(true);
        window.setScene(actualScene);
    }
    private Scene setFirtsScene(){

        return new Scene();
    }
    private void changeActualScene()

    final Map<KeyCode, Action> controlsMove = Map.of(
            KeyCode.LEFT, new AccionMovimiento(-1,0),
            KeyCode.RIGHT, new AccionMovimiento(+1,0),
            KeyCode.UP,new AccionMovimiento(0,1),
            KeyCode.DOWN, new AccionMovimiento(0,-1),
            KeyCode.ENTER, new AccionMovimiento(0,0),
//            KeyCode.R, new AccionStartGame()
    );

}
