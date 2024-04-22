package robot.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoWindow {
    private Stage window;
    private Scene mainScene;
    private String[] instrucciones;
    private String stageName ="Información de juego";
    private StackPane tittleBox;
    private String titulo;


    public InfoWindow(String titulo, String[] instructionGame, String nombreApp, Stage ventanaPadre){
        this.titulo=titulo;
        stageName=stageName+" - "+nombreApp;
        window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(ventanaPadre);
        instrucciones=instructionGame;
        mainScene = buildMainScene();
        setStage();

    }

    public Stage getWindow() {
        return window;
    }

    private Scene buildMainScene(){
        VBox root = new VBox();
        root.setBackground(Background.fill(Color.BLACK));
        setTittleBox();
        root.getChildren().add(tittleBox);
        root.getChildren().add(buildInstructions());
        return new Scene(root);
    }
    private void setTittleBox(){
        Label lbTittle=labelInstruction();
        tittleBox =new StackPane();
        tittleBox.getChildren().add(lbTittle);
    }

    private VBox buildInstructions(){
        VBox vb =new VBox(10);
        vb.setBackground((Background.fill(Color.BLACK)));
        vb.getChildren().add(new Label("\n"));
        for (String inst:instrucciones){
            Label instruction = labelConfigurated(inst);
            vb.getChildren().add(instruction);
        }
        StackPane sgtWindow = new StackPane(labelConfigurated("To be continued ..."));
        vb.getChildren().add(sgtWindow);
        return vb;
    }
    private Label labelConfigurated(String text){
        var l = new Label(text);
        l.setBackground(Background.fill(Color.BLACK));
        l.setStyle("-fx-padding: 0 0 0 10;");
        l.setFont(new Font("Times New Roman",12));
        l.setTextFill(Color.WHITE);
        return l;
    }
    private Label labelInstruction(){
        Label lav = new Label(titulo);
        lav.setBackground(Background.fill(Color.BLACK));
        lav.setTextFill(Color.WHITE);
        lav.setFont(new Font("Times New Roman",24));
        //lav.setAlignment(Pos.CENTER);
        return lav;
    }

    private void setStage(){
        window.setTitle(stageName);
        window.setHeight(320);
        window.setWidth(400);
        window.setResizable(false);

        window.setX(500);//en qué parte de la pantalla se colocará la ventana (X)
        window.setY(50);//en qué parte de la pantalla se colocrá la ventana (Y)
        window.setScene(mainScene);
        window.showAndWait();
    }
}