package robot.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InitialScene {
    Scene mainScene;
    Label filasLabel;
    Label colLabel;
    Button botonJugar;

    public InitialScene(){
        filasLabel = new Label("Cantidad de filas");
        colLabel = new Label("Cantidad de columnas");
        botonJugar = new Button("JUGAR");
        botonJugar.setPrefSize(150,50);
    }
    private Scene getMainScene(){

        HBox hb1 = hboxConfigurated(filasLabel);
        HBox hb2 = hboxConfigurated(colLabel);

        VBox vb = new VBox();
        vb.setStyle("-fx-background-color: #FFC0CB;");
        vb.setSpacing(30);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(hb1,hb2,botonJugar);
        return new Scene(vb,600,50, Color.PURPLE);
    }

    private HBox hboxConfigurated(Label etiqueta){
        etiqueta.setFont(new Font("Times New Roman",24));
        HBox hb=new HBox();
        hb.setStyle("-fx-background-color: cornsilk;");
        hb.setSpacing(30);
        hb.setAlignment(Pos.CENTER);

        ChoiceBox<Integer> option =paneOptionsConfigurated();
        hb.getChildren().add(etiqueta);
        hb.getChildren().add(option);
        return hb;
    }

    private ChoiceBox<Integer> paneOptionsConfigurated(){
        var cb = new ChoiceBox<Integer>();
        cb.getItems().addAll(5,6,7,8,9,10,11,12,13);
        return cb;
    }

}

