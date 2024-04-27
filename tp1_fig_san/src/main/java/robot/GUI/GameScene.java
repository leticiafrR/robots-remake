package robot.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.AccionTeleportRandom;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Modelo.Listeners.ListenerLevelUp;
import robot.Modelo.Listeners.ListenerTPSafe;


import java.util.Map;

public class GameScene {
    private EstadoDeJuego gameState;
    private Scene principal;
    private AdminGrilla adminGrilla;
    private GridPane grilla;
    private AdminPanelPosterior adminPanelPost;
    private HBox panelPost;
    private int filas;
    private int columnas;
    private VBox verticalRoot = new VBox();




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
        crearGrilla();
        crearPanelInferior();
        crearCajaContendoraMain();
        principal= new Scene(verticalRoot, (columnas*40), (filas*40)+50);
        controladorMainScene();
        sendListenersModelo();
    }

    //PRE: gameState inicalizado
    //POST: inicializa al administrador  del panelPosterior e instancia al panelPosterior de botones (lo configura)
    private void crearPanelInferior(){
        adminPanelPost = new AdminPanelPosterior(gameState.getCantSafeTeleport(), gameState.getNivel(),gameState.getPuntuacion());
        panelPost = adminPanelPost.getHb();
        adminPanelPost.registrarListeners(listenerTpRandom(), listenerTpSafe(), listenerEsperar());
        panelPost.setMaxSize(Double.MAX_VALUE, 60);
        panelPost.setMinHeight(60);
    }


    //PRE: gameState inicializado
    //POST: inicializa al administrador dela grilla y a la misma grilla del juego
    private void crearGrilla(){
        adminGrilla = new AdminGrilla(columnas, filas,gameState);
        adminGrilla.pintarGrilla(gameState);
        grilla = adminGrilla.getGrilla();
        configurarTablero();
    }

    //PRE: grilla inicializada
    //POST: configura la adaptacion del tablero a la ventana del juego
    private void configurarTablero(){
        VBox.setVgrow(grilla, Priority.ALWAYS);
        grilla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        grilla.setAlignment(Pos.CENTER);
        grilla.setHgap(5);
        grilla.setVgap(5);
    }

    //PRE: grilla y panelPosterior inicializados
    //POST: iniciliza el gestor de layout principal y agrega los nodos respectivos (grilla y panelPosterior)
    private void crearCajaContendoraMain(){
        verticalRoot = new VBox();
        verticalRoot.setAlignment(Pos.CENTER);
        verticalRoot.getChildren().add(grilla);
        verticalRoot.getChildren().add(panelPost);
    }

    //PRE:Escena principal, controlsMove y gamestate inicializados
    //POST: Añade comportamiento de movimiento por teclas
    public void controladorMainScene(){
        principal.setOnKeyReleased(keyEvent -> {
            var accion= controlsMove.get(keyEvent.getCode());
            if (accion!=null) {
                gameState.update(accion);
                adminGrilla.pintarGrilla(gameState);
            }
        });
    }

    //PRE:
    //POST: envia listener al modelo
    private void sendListenersModelo(){
        sendListenerTp();
        sendListenerLevelUp();
    }

    //PRE: adminPanelPosterior y gameState inicializados
    //POST: crea y envia listener ante el evento teleport seguro al modelo
    private void sendListenerTp(){
        ListenerTPSafe listenerTPSafe= new ListenerTPSafe() {
            @Override
            public void tpUsado(String cantidadTPsSafe) {
                adminPanelPost.actualizarContenido(cantidadTPsSafe, adminPanelPost.getTpSafe());
            }
        };
        gameState.registrarListenerTPdisponibles(listenerTPSafe);
    }

    //PRE: adminPanelPosterior y gameState inicializados
    //POST: crea y envia listener ante el evento level up al modelo
    private void sendListenerLevelUp(){
        ListenerLevelUp listenerLevelUp =new ListenerLevelUp() {
            @Override
            public void levelUp(String nivelActualizado,String scoreActualizado,String tpSafeActualizado) {
                crearAlertaNextLevel();
                adminPanelPost.actualizarContenido(nivelActualizado, adminPanelPost.getNivel());
                adminPanelPost.actualizarContenido(scoreActualizado, adminPanelPost.getScore());
                adminPanelPost.actualizarContenido(tpSafeActualizado, adminPanelPost.getTpSafe());
            }
        };
        gameState.registrarListenerLevelUp(listenerLevelUp);
    }

    //PRE:
    //POST:
    public void crearAlertaNextLevel(){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AVANZASTE DE NIVEL");
        alert.setContentText("SIGUE ASI");
        alert.show();
    }

    public Scene getPrincipal() {
        return principal;
    }

    //PRE: gameState y adminGrilla inicializados
    //POST: devuelve evento ante un teleport random
    private EventHandler<ActionEvent> listenerTpRandom(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AccionTeleportRandom accion = new AccionTeleportRandom();
                gameState.update(accion);
                adminGrilla.pintarGrilla(gameState);
            }
        };
    }

    //PRE: gameState y adminGrilla inicializados
    //POST: devuelve evento ante un "esperar" del jugador
    private EventHandler<ActionEvent> listenerEsperar(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AccionMovimiento accion= new AccionMovimiento(0,0);
                gameState.update(accion);
                adminGrilla.pintarGrilla(gameState);
            }
        };
    }

    //PRE: adminGrilla inicializado
    //POST: devuelve un evento ante un "teleport safe" del jugador
    private EventHandler<ActionEvent> listenerTpSafe(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                adminGrilla.escucharTp();
            }
        };
    }

}
