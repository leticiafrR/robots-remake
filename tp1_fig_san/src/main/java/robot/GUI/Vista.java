package robot.GUI;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.AccionStartGame;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Modelo.Tablero;

import java.util.ArrayList;
import java.util.Map;

public class Vista {

    final Map<KeyCode, Action> controlsMove = Map.of(
            KeyCode.LEFT, new AccionMovimiento(-1,0),
            KeyCode.RIGHT, new AccionMovimiento(+1,0),
            KeyCode.UP,new AccionMovimiento(0,1),
            KeyCode.DOWN, new AccionMovimiento(0,-1),
            KeyCode.ENTER, new AccionMovimiento(0,0),
//            KeyCode.R, new AccionStartGame()
    );
    private Stage window;
    private Scene actualScene;
    private EstadoDeJuego gameState;
    private int largoX = 20;
    private int largoY = 20;
    private String nombreApp ="ROBOT";

    public Vista(Stage stage) {
        window = stage;
    }


    private void addChildrrens(Pane root, ArrayList<Node> hijos){
        for (Node n: hijos){
            root.getChildren().add(n);
        }
    }

    private void setStage(){
        window.setTitle(nombreApp);
        Image icon = new Image("icon.png");//este es un nodo que se conecta al stage
        window.getIcons().add(icon);
        window.setHeight(600);
        window.setWidth(600);
        window.setResizable(false);
        window.setX(500);//en qué parte de la pantalla se colocará la ventana (X)
        window.setY(50);//en qué parte de la pantalla se colocrá la ventana (Y)
        window.setScene(actualScene);
        window.show();
    }
}
