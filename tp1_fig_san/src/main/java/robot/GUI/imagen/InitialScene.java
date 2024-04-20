package robot.GUI.imagen;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InitialScene {
    Scene mainScene;
    Label filasLabel;
    Label colLabel;

    public InitialScene(){
        filasLabel = new Label("Cantidad de filas");
        colLabel = new Label("Cantidad de columnas");
    }
    private Scene getMainScene(){
        HBox hb1=new HBox();
        ChoiceBox<Integer> filas=paneOptionsConfigurated();
        hb1.getChildren().add(filasLabel);
        hb1.getChildren().add(filas);

        HBox hb2 = new HBox();
        ChoiceBox<Integer> col = paneOptionsConfigurated();
        hb2.getChildren().add(colLabel);
        hb2.getChildren().add(col);

        VBox vb = new VBox();
        vb.getChildren().addAll(hb1,hb2);
        // gestorLayout.getChildren()
        return new Scene(vb,600,50);
    }
    private ChoiceBox<Integer> paneOptionsConfigurated(){
        var cb = new ChoiceBox<Integer>();
        cb.getItems().addAll(5,6,7,8,9,10,11,12,13);
        return cb;
    }

}

