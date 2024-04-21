package robot.GUI;

import javafx.scene.Scene;
import javafx.stage.Stage;
import robot.Modelo.EstadoDeJuego;
import robot.Modelo.Listeners.ListenerGameOver;

public class Vista {

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
        gameState.registrarListenerGameOver(listener);
        actualizarPantalla(getmainScene(filas,columnas));
        System.out.println("Sali de goToMainScene");
    }

    public Scene getmainScene(int filas, int columnas) {
        GameScene principal= new GameScene(columnas, filas, gameState);
        return principal.getPrincipal();
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
        //window.setResizable(false);
        window.setX(500);
        window.setY(50);
       // actualizarPantalla(firstScene);
        window.show();
    }
}
