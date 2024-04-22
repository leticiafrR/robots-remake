package robot.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Modelo.Listeners.ListenerLevelUp;
import robot.Modelo.Listeners.ListenerTPSafe;

import java.util.Map;

public class GameScene {
    private Scene principal;
    private Grilla grilla;
    private GridPane tablero;
    private EstadoDeJuego gameState;
    private int filas;
    private int columnas;

    private VBox verticalRoot =new VBox();
    private PanelPosterior pp;

    private HBox horizontalBox;

    //Controles de movimientos
    final Map<KeyCode, Action> controlsMove = Map.of(
            KeyCode.W, new AccionMovimiento(0,-1),
            KeyCode.S, new AccionMovimiento(0,1),
            KeyCode.A, new AccionMovimiento(-1,0),
            KeyCode.D, new AccionMovimiento(1,0),
            KeyCode.X, new AccionMovimiento(0,0),
            KeyCode.E, new AccionMovimiento(1,-1),
            KeyCode.Q, new AccionMovimiento(-1,-1),
            KeyCode.Z, new AccionMovimiento(-1,1),
            KeyCode.C, new AccionMovimiento(1,1)
    );


    public GameScene(int columnas, int filas, EstadoDeJuego gameState){
        this.gameState=gameState;
        this.filas=filas;
        this.columnas=columnas;
        grilla = tableroVisualGrilla();
        crearPanelInferior();

        crearCajaContendoraMain();
        principal= new Scene(verticalRoot, (columnas*40), (filas*40)+50);
        controladoMainScene();
        sendListeners();
    }

    //PRE: gameState, grilla y horizontalBox inicalizadas
    //POST: Crea el panel de botones del juego (panel inferior)
    private void crearPanelInferior(){
        pp = new PanelPosterior(gameState.getCantSafeTeleport(), gameState.getNivel(),gameState.getPuntuacion());
        horizontalBox = pp.crearHBox(gameState, grilla);
        //VBox.setVgrow(horizontalBox, Priority.ALWAYS);
        horizontalBox.setMaxSize(Double.MAX_VALUE, 75);
    }

    //PRE:columnas y filas validas, gameState inicializado
    //POST: inicializa el tablero visual
    private Grilla tableroVisualGrilla(){
        var grilla = new Grilla(columnas, filas,gameState);
        grilla.pintarGrilla(gameState);
        tablero= grilla.getTablero();
        configurarTablero();
        return grilla;
    }

    //PRE: tablero inicializado
    //POST: configura la adaptacion del tablero a la ventana del juego
    private void configurarTablero(){
        VBox.setVgrow(tablero, Priority.ALWAYS);
        tablero.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tablero.setAlignment(Pos.CENTER);
        tablero.setHgap(5);
        tablero.setVgap(5);
    }

    //PRE: tablero y panel inferior inicializados
    //POST:Añade a la caja principal (VBox) el tablero y el panel inferior
    private void crearCajaContendoraMain(){
        verticalRoot.setAlignment(Pos.CENTER);
        verticalRoot.getChildren().add(tablero);
        verticalRoot.getChildren().add(horizontalBox);
    }

    //PRE:Escena principal, controlsMove y gamestate inicializados
    //POST: Añade comportamiento de movimiento por teclas
    public void controladoMainScene(){
        principal.setOnKeyReleased(keyEvent -> {
            var accion= controlsMove.get(keyEvent.getCode());
            if (accion!=null) {
                gameState.update(accion);
                grilla.pintarGrilla(gameState);
            }
        });
    }

    //PRE:gameState y pp (panel posterior) inicializados
    //POST:Envia listeners a los respectivos
    private void sendListeners(){
        sendListenerTp();
        sendListenerLevelUp();
    }

    //PRE: pp y gameState inicializados
    //POST: Le da comportamiento de actualizacion al contenido de TPSeguro
    private void sendListenerTp(){
        ListenerTPSafe listenerTPSafe= new ListenerTPSafe() {
            @Override
            public void tpUsado(String cantidadTPsSafe) {
                pp.actualizarContenido(cantidadTPsSafe,pp.getTpSafe());
            }
        };
        gameState.registrarListenerTPdisponibles(listenerTPSafe);
    }

    //PRE: pp y gameState inicializados
    //POST: Le da comportamiento de actualizacion al contenido del Panel inferior del juego
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
