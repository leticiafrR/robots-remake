package robot.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Modelo.Listeners.ListenerLevelUp;
import robot.Modelo.Listeners.ListenerTPSafe;

import java.util.Map;

public class GameScene {
    private Scene principal;
    private Grilla grilla;

    private EstadoDeJuego gameState;
    private int filas;
    private int columnas;

    private VBox verticalRoot =new VBox();
    private PanelPosterior pp;

    private HBox horizontalBox;


    final Map<KeyCode, Action> controlsMove = Map.of(
         /*   KeyCode.LEFT, new AccionMovimiento(-1,0),
            KeyCode.RIGHT, new AccionMovimiento(+1,0),
            KeyCode.UP,new AccionMovimiento(0,1),
            KeyCode.DOWN, new AccionMovimiento(0,-1),*/
            //KeyCode.ENTER, new AccionMovimiento(0,0),
            KeyCode.W, new AccionMovimiento(0,-1),
            KeyCode.S, new AccionMovimiento(0,1),
            KeyCode.A, new AccionMovimiento(-1,0),
            KeyCode.D, new AccionMovimiento(1,0),
            KeyCode.X, new AccionMovimiento(0,0),
            KeyCode.E, new AccionMovimiento(1,-1),
            KeyCode.Q, new AccionMovimiento(-1,-1),
            KeyCode.Z, new AccionMovimiento(-1,1),
            KeyCode.C, new AccionMovimiento(1,1)
//            KeyCode.R, new AccionStartGame()
    );


    public GameScene(int columnas, int filas, EstadoDeJuego gameState){
        this.gameState=gameState;
        this.filas=filas;
        this.columnas=columnas;
        grilla = new Grilla(columnas, filas,gameState);
        pp = new PanelPosterior(gameState.getCantSafeTeleport(), gameState.getNivel(),gameState.getPuntuacion());
        horizontalBox = pp.crearHBox(gameState, grilla);
        grilla.pintarGrilla(gameState);
        configurarTablero(grilla.getTablero());
        principal= new Scene(verticalRoot, (columnas*40), (filas*40)+50);
        controladoMainScene(gameState);
        sendListeners();
    }

    private void configurarTablero(GridPane tablero){
        VBox.setVgrow(tablero, Priority.ALWAYS);
        VBox.setVgrow(horizontalBox, Priority.ALWAYS);
        tablero.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); //Revisar adaptacion
        horizontalBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);//''       ''
        tablero.setAlignment(Pos.CENTER);
        verticalRoot.setAlignment(Pos.CENTER);
        tablero.setHgap(5);
        tablero.setVgap(5);
        verticalRoot.getChildren().add(tablero);
        verticalRoot.getChildren().add(horizontalBox);
    }

    public void controladoMainScene(EstadoDeJuego e){
        principal.setOnKeyReleased(keyEvent -> {
            var accion= controlsMove.get(keyEvent.getCode());
            if (accion!=null) {
                e.update(accion);
                grilla.pintarGrilla(e);
            }
        });

    }
    private void sendListeners(){
        sendListenerTp();
        sendListenerLevelUp();
    }

    private void sendListenerTp(){
        ListenerTPSafe listenerTPSafe= new ListenerTPSafe() {
            @Override
            public void tpUsado(String cantidadTPsSafe) {
                pp.actualizarContenido(cantidadTPsSafe,pp.getTpSafe());
            }
        };
        gameState.registrarListenerTPdisponibles(listenerTPSafe);
    }
    private void sendListenerLevelUp(){
        ListenerLevelUp listenerLevelUp =new ListenerLevelUp() {
            @Override
            public void levelUp(String nivelActualizado,String scoreActualizado,String tpSafeActualizado) {
                pp.actualizarContenido(nivelActualizado,pp.getNivel());
                pp.actualizarContenido(scoreActualizado,pp.getScore());
                pp.actualizarContenido(tpSafeActualizado,pp.getTpSafe());
            }
        };
        gameState.registrarListenerLevelUp(listenerLevelUp);
    }
    public Scene getPrincipal() {
        return principal;
    }

}
