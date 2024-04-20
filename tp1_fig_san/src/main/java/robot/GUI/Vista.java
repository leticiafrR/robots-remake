package robot.GUI;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Modelo.ListenerGameOver;

import java.util.Map;

public class Vista {

    final Map<KeyCode, Action> controlsMove = Map.of(
            KeyCode.LEFT, new AccionMovimiento(-1,0),
            KeyCode.RIGHT, new AccionMovimiento(+1,0),
            KeyCode.UP,new AccionMovimiento(0,1),
            KeyCode.DOWN, new AccionMovimiento(0,-1),
            KeyCode.ENTER, new AccionMovimiento(0,0)
         /*   KeyCode.W, new AccionMovimiento(0,1),
            KeyCode.S, new AccionMovimiento(0,-1),
            KeyCode.A, new AccionMovimiento(-1,0),
            KeyCode.D, new AccionMovimiento(1,0),
            KeyCode.SPACE, new AccionMovimiento(0,0),
            KeyCode.E, new AccionMovimiento(1,1),
            KeyCode.Q, new AccionMovimiento(-1,1),
            KeyCode.Z, new AccionMovimiento(-1,-1),
            KeyCode.C, new AccionMovimiento(1,-1)*/
//            KeyCode.R, new AccionStartGame()
    );
    private Stage window;
    private Scene firstScene;
    private Scene mainScene;
    private InitialScene preludio;
    private EstadoDeJuego gameState;

    private String nombreApp ="ROBOT";

    public Vista(Stage stage) {
        window = stage;
        preludio = new InitialScene(this);
        firstScene = preludio.getFirstScene();
        actualizarPantalla(firstScene);
        setStage();
    }

    public void goToMainScene(int filas, int columnas){
        gameState= new EstadoDeJuego(columnas, filas);
        ListenerGameOver listener = new ListenerGameOver() {
            @Override
            public void gameOver() {
                actualizarPantalla(preludio.getFirstScene());
            }
        };
        gameState.registrarListener(listener);
        actualizarPantalla(getmainScene(filas,columnas));
    }

    public Scene getmainScene(int filas, int columnas) {
        return new GameScene(columnas, filas, gameState).getPrincipal();
    }


    public void actualizarPantalla(Scene escena){

        window.setScene(escena);
    }

    private void setStage(){
        window.setTitle(nombreApp);
        /*
        Image icon = new Image("icon.png");
        window.getIcons().add(icon);
        window.setHeight(600);
        window.setWidth(600);
         */
        window.setResizable(false);
        window.setX(500);
        window.setY(50);
        actualizarPantalla(firstScene);
        window.show();
    }
}
