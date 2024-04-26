package robot.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
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
    private String nombreApp;

    public enum RutaFotos{JUGADOR, ROBOTX1, ROBOTX2, FUEGO, LOGO}
    private static final String[] rutas = new String[]{"player.png","robotx1.png","robotx2.png","fuego.png","logo.png"};
    public static String getRuta(RutaFotos ruta){
        return rutas[ruta.ordinal()];
    }

    public Vista(Stage stage) {
        window = stage;
        preludio = new InitialScene(this);
        firstScene = preludio.getFirstScene();
        nombreApp = EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.NOMBRE);
    }

    //PRE:
    //POST:
    public void iniciarJuego(){
        actualizarPantallaPrincipal(firstScene);
        var instruction = new InfoWindow(tittleEmergente[TittleEmergentes.INSTRUCTION.ordinal()],EstadoDeJuego.getInstruccionesModelo(), nombreApp,window);
        var controles = new InfoWindow(tittleEmergente[TittleEmergentes.CONTROLS.ordinal()],EstadoDeJuego.InstructionsControlGame(), nombreApp,window);
        setStage();
    }

    //PRE: fila y columnas inicializadas
    //POST: instancia al modelo, registra el listener ante un gameOver y muestra en la pantalla la escena principal del juego
    public void goToMainScene(int filas, int columnas){
        gameState = new EstadoDeJuego(columnas, filas);
        ListenerGameOver listener = new ListenerGameOver() {
            @Override
            public void gameOver() {
                crearAlertaGameOver();
                actualizarPantallaPrincipal(preludio.getFirstScene());
            }
        };
        gameState.registrarListenerGameOver(listener);
        actualizarPantallaPrincipal(getmainScene(filas,columnas));
    }

    //PRE: filas y columnas inicializadas
    //POST: devuelve la escena del juego (tablero y botones de juego)
    public Scene getmainScene(int filas, int columnas) {
        GameScene principal= new GameScene(columnas, filas, gameState);
        return principal.getPrincipal();
    }

    //PRE:
    //POST:
    public void crearAlertaGameOver(){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("PERDISTE");
        alert.setContentText("INTENTALO DE NUEVO");
        alert.show();
    }

    //PRE: window incializado
    //POST: actualiza pantalla principal
    public void actualizarPantallaPrincipal(Scene escena){
        window.setScene(escena);
    }

    //PRE: window incializado
    //POST: Le da los valores predeterminados al escenario
    private void setStage(){
        window.setTitle(nombreApp);
        Image icon = new Image(getRuta(RutaFotos.LOGO));
        window.getIcons().add(icon);
        window.setX(500);
        window.setY(50);
        window.setMinWidth(500);
        window.setMinHeight(500);
        window.show();
    }
}
