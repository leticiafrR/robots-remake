package robot.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.Action;
import robot.Modelo.EstadoDeJuego;
import robot.Modelo.ListenerGameOver;
import robot.Modelo.ListenerTPSafe;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Map;

public class GameScene {
    private Scene principal;
    private Grilla grilla;


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
            KeyCode.SPACE, new AccionMovimiento(0,0),
            KeyCode.E, new AccionMovimiento(1,-1),
            KeyCode.Q, new AccionMovimiento(-1,-1),
            KeyCode.Z, new AccionMovimiento(-1,1),
            KeyCode.C, new AccionMovimiento(1,1)
//            KeyCode.R, new AccionStartGame()
    );

    private PanelPosterior pp;
    public GameScene(int columnas, int filas, EstadoDeJuego gameState){
        VBox caja = new VBox();
        grilla = new Grilla(columnas, filas,gameState);
        pp = new PanelPosterior(gameState.getCantSafeTeleport(), gameState.getNivel());
        //Stage gr= new Stage();
        HBox panelPost = pp.crearHBox(gameState, grilla);
        Stage conjunto= new Stage();
        grilla.pintarGrilla(gameState);

        GridPane tabla = grilla.getTablero();
        VBox.setVgrow(tabla, Priority.ALWAYS);
        VBox.setVgrow(panelPost, Priority.ALWAYS);
        tabla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); //Revisar adaptacion
        panelPost.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);//''       ''
        tabla.setAlignment(Pos.CENTER);
        tabla.setHgap(5);
        tabla.setVgap(5);
        caja.getChildren().add(tabla);
        caja.getChildren().add(panelPost);
        principal= new Scene(caja, (columnas*40), (filas*40)+75);
        controladoMainScene(gameState);
        ListenerTPSafe listenerTPSafe= new ListenerTPSafe() {
            @Override
            public void tpUsado(String cantidadTPsSafe) {
                pp.actualizarBotonTPsafe(cantidadTPsSafe);
            }
        };
        gameState.registrarListenerTPdisponibles(listenerTPSafe);

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
    public Scene getPrincipal() {
        return principal;
    }
}
