package robot.GUI;

import javafx.scene.input.KeyCode;
import robot.Modelo.Acciones.AccionMovimiento;
import robot.Modelo.Acciones.AccionStartGame;
import robot.Modelo.Acciones.Action;

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

}
