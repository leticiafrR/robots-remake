package robot.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoWindow {
    private Stage window;
    private Scene mainScene;
    private String[] instrucciones;
    private String stageName = "Información de juego";
    private StackPane tittleBox;
    private String titulo;


    public InfoWindow(String titulo, String[] instructionGame, String nombreApp, Stage ventanaPadre){
        this.titulo=titulo;
        stageName = stageName+" - "+nombreApp;
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

    //PRE:
    //POST: construye y devuelve la esecena principal, crea nodo padre (Vbox) con sus nodos hijos (titulo y cuerpo)
    private Scene buildMainScene(){
        VBox root = new VBox();
        root.setBackground(Background.fill(Color.BLACK));
        setTittleBox();
        root.getChildren().add(tittleBox);
        root.getChildren().add(buildInstructions());
        return new Scene(root);
    }

    //PRE:
    //POST: setea gestor del titulo de la ventana de informacion (personalizado)
    private void setTittleBox(){
        Label lbTittle=labelInstruction();
        tittleBox =new StackPane();
        tittleBox.getChildren().add(lbTittle);
    }

    //PRE:
    //POST: crea y devuelve el titulo de la ventana de informacion (personalizado) con la configuración standart
    private Label labelInstruction(){
        Label lav = new Label(titulo);
        lav.setBackground(Background.fill(Color.BLACK));
        lav.setTextFill(Color.WHITE);
        lav.setFont(new Font("Times New Roman",28));
        return lav;
    }

    //PRE:
    //POST: crea y devuelve el gestor del cuerpo de la ventana de información (Vbox)
    private VBox buildInstructions(){
        VBox vb =new VBox(10);
        vb.setBackground((Background.fill(Color.BLACK)));
        vb.getChildren().add(new Label("\n"));
        for (String inst:instrucciones){
            Label instruction = labelCuerpoConfigurated(inst);
            vb.getChildren().add(instruction);
        }
        StackPane sgtWindow = new StackPane(labelCuerpoConfigurated("Cierre la ventana para continuar :)"));
        vb.getChildren().add(sgtWindow);
        return vb;
    }

    //PRE: texto inicializado
    //POST: crea y devuelve un label con la configuración standart del cuerpo y el contenido indicado
    private Label labelCuerpoConfigurated(String text){
        var l = new Label(text);
        l.setBackground(Background.fill(Color.BLACK));
        l.setStyle("-fx-padding: 0 0 0 10;");
        l.setFont(new Font("Times New Roman",16));
        l.setTextFill(Color.WHITE);
        return l;
    }


    //PRE:
    //POST: setea configuración de fondo de la ventana
    private void setStage(){
        window.setTitle(stageName);
        window.setHeight(360);
        window.setWidth(500);
        window.setResizable(false);
        Image icon = new Image("logo.png");
        window.getIcons().add(icon);

        window.setX(500);
        window.setY(50);
        window.setScene(mainScene);
        window.showAndWait();
    }
}