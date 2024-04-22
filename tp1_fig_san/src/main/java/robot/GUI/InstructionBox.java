package robot.GUI;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
public class InstructionBox {
    private Stage window;
    private Scene mainScene;
    private String[] instrucciones;
    private String stageName ="Instrucciones de juego";

    public InstructionBox(String[] instructionGame, String nombreApp,Stage ventanaPadre){

        window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(ventanaPadre);
        instrucciones=instructionGame;
        setMainScene();
        setStage();

    }
    private void setMainScene(){
        VBox vb = new VBox();
        
    }
    private void setStage(){
        window.setTitle(stageName);
        window.setHeight(500);
        window.setWidth(500);

        window.setX(500);//en qué parte de la pantalla se colocará la ventana (X)
        window.setY(50);//en qué parte de la pantalla se colocrá la ventana (Y)
        window.setScene(mainScene);
        window.showAndWait();
    }
}
