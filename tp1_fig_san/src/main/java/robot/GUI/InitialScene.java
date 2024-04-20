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
import robot.Modelo.EstadoDeJuego;

import java.util.ArrayList;

public class InitialScene{
    private Scene mainScene;
    private Label filasLabel;
    private Label colLabel;
    private Button botonJugar;


    private final ArrayList<ChoiceBox<Integer>> sizesChoiceBoxes;
    private enum TypeSize {FILAS,COLUMNAS};
    private  final int[] defaultSizes = new int[]{15,15};


    public InitialScene(Vista vista){
        filasLabel = new Label(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.CANTIDAD_FIL));
        colLabel = new Label(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.CANTIDAD_COL));
        botonJugar = botonJugarConfigurated(vista);
        sizesChoiceBoxes =new ArrayList<>();
        setFirstScene();
    }

    //BOTON->GOTOMAINSCENE(X,Y)

    //PRE:
    //POST:
    //el valor predeterminado es 15 15
    private Button botonJugarConfigurated(Vista vista){
        var b = new Button(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.JUGAR));
        b.setPrefSize(150,50);
        b.setOnAction(e->crearTablero(vista));
        return b;
    }

    //PRE:
    //POST:
    private void crearTablero(Vista vista){
        vista.goToMainScene(obtenerSize(TypeSize.FILAS),obtenerSize(TypeSize.COLUMNAS));
    }

    //PRE:
    //POST:
    private int obtenerSize(TypeSize tipo){
        ChoiceBox<Integer> sizeBox = sizesChoiceBoxes.get(tipo.ordinal());
        int size =  defaultSizes[tipo.ordinal()];
        if (!sizeBox.getSelectionModel().isEmpty()){
            size=sizeBox.getValue();
        }
        return size;
    }

    //PRE:
    //POST:
    public Scene getFirstScene(){
        return mainScene;
    }

    //PRE:
    //POST:
    public void setFirstScene(){
        HBox hb1 = hboxConfigurated(filasLabel);
        HBox hb2 = hboxConfigurated(colLabel);

        VBox vb = new VBox();
        vb.setStyle("-fx-background-color: #FFC0CB;");
        vb.setSpacing(30);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(hb1,hb2,botonJugar);
        mainScene = new Scene(vb,600,600, Color.PURPLE);
    }

    //PRE:
    //POST:
    private HBox hboxConfigurated(Label etiqueta){
        etiqueta.setFont(new Font("Times New Roman",24));
        HBox hb=new HBox();
        hb.setStyle("-fx-background-color: cornsilk;");
        hb.setSpacing(30);
        hb.setAlignment(Pos.CENTER);

        ChoiceBox<Integer> optionSize =choiceBoxConfigurated();
        sizesChoiceBoxes.add(optionSize);
        hb.getChildren().add(etiqueta);
        hb.getChildren().add(optionSize);
        return hb;
    }

    //PRE:
    //POST:
    private ChoiceBox<Integer> choiceBoxConfigurated(){
        var cb = new ChoiceBox<Integer>();
        cb.getItems().addAll(10,11,12,13,14,15,16,17,18,19,20);
        return cb;
    }

}

