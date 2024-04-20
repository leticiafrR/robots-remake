package robot.GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.AccionStartGame;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;

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
    private Group root;
    private String nombreApp = "ROBOT";
    private int largoX = 20;
    private int largoY = 20;

    public Vista(Stage stage) {
        window = stage;
        //actualScene = setPrincipalScene();
        actualScene = setFirstScene(new);
    }

    private Scene setFirstScene(){
        Group root = new Group();

    }
    private Scene setPrincipalScene(){
        //desarrollo arbol de escena principal (parte desde raiz)
        root = new Group();
        root.getChildren().add(textPrincipal());
        root.getChildren().add(lineaMedia());

        //trabajando escena que contendrá el arbol
        return  new Scene(root, 600, 600, Color.PURPLE);
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
