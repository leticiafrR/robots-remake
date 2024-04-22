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
    private enum TittleEmergentes{INSTRUCTION,CONTROLS};
    private final String[] tittleEmergente = new String[] {"Instrucciones de juego","Controles de juego"};

    private String nombreApp ="ROBOT";

    public Vista(Stage stage) {
        window = stage;
        preludio = new InitialScene(this);
        firstScene = preludio.getFirstScene();
        actualizarPantallaPrincipal(firstScene);
        String nameGame=EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.NOMBRE);
        InfoWindow instruction = new InfoWindow(tittleEmergente[TittleEmergentes.INSTRUCTION.ordinal()],EstadoDeJuego.getInstruccionesModelo(), nameGame,window);
        InfoWindow controles = new InfoWindow(tittleEmergente[TittleEmergentes.CONTROLS.ordinal()],EstadoDeJuego.getInstruccionesModelo(), nameGame,window);
        setStage();
    }

    public void goToMainScene(int filas, int columnas){
        gameState= new EstadoDeJuego(columnas, filas);
        ListenerGameOver listener = new ListenerGameOver() {
            @Override
            public void gameOver() {
                actualizarPantallaPrincipal(preludio.getFirstScene());
            }
        };
        gameState.registrarListenerGameOver(listener);
        actualizarPantallaPrincipal(getmainScene(filas,columnas));
        System.out.println("Sali de goToMainScene");
    }

    public Scene getmainScene(int filas, int columnas) {
        GameScene principal= new GameScene(columnas, filas, gameState);
        return principal.getPrincipal();
    }


    public void actualizarPantallaPrincipal(Scene escena){
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
