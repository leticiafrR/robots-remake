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

    private static int MINIMO_CELDAS= 10;
    private static int MAXIMO_CELDAS= 20;
    private final ArrayList<ChoiceBox<Integer>> sizesChoiceBoxes;
    private enum TypeSize {FILAS,COLUMNAS};
    private  final int[] defaultSizes = new int[]{15,15};


    public InitialScene(Vista vista){
        filasLabel = new Label(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.CANTIDAD_FIL));
        colLabel = new Label(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.CANTIDAD_COL));
        botonJugar = botonJugarConfigurated(vista);
        sizesChoiceBoxes =new ArrayList<>();
    }


    //PRE:
    //POST: crea y devuelve el boton que me lleva al juego
    private Button botonJugarConfigurated(Vista vista){
        var b = new Button(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.JUGAR));
        b.setPrefSize(150,50);
        b.setOnAction(e->crearTablero(vista));
        return b;
    }

    //PRE: vista inicializada
    //POST: manda mensaja e al vista para ir a la escena principla del juego
    private void crearTablero(Vista vista){
        vista.goToMainScene(obtenerSize(TypeSize.FILAS),obtenerSize(TypeSize.COLUMNAS));
    }

    //PRE: TypeSize inicializado
    //POST: Si hay una opcion seleccionada del size indicado en el tipo (fil o col) en los choicesBoxes, lo devuelve, sino
    //      asigna el tamaño default
    private int obtenerSize(TypeSize tipo){
        ChoiceBox<Integer> sizeBox = sizesChoiceBoxes.get(tipo.ordinal());
        int size =  defaultSizes[tipo.ordinal()];
        if (!sizeBox.getSelectionModel().isEmpty()){
            size=sizeBox.getValue();
        }
        return size;
    }

    //PRE:
    //POST: setea la primera escena del juego y la devuelve
    public Scene getFirstScene(){
        setFirstScene();
        return mainScene;
    }

    //PRE:
    //POST: instancia la escena inicial con su respectivo nodo padre (vbRoot) y sus nodos hijos (choices boxes)
    public void setFirstScene(){
        HBox hbFilas = hboxConfigurated(filasLabel);
        HBox hbCol = hboxConfigurated(colLabel);
        VBox vbRoot = new VBox();
        vbRoot.setStyle("-fx-background-color: #FFC0CB;");
        vbRoot.setSpacing(30);
        vbRoot.setAlignment(Pos.CENTER);
        vbRoot.getChildren().addAll(hbFilas,hbCol,botonJugar);
        mainScene = new Scene(vbRoot,600,600, Color.PURPLE);
    }

    //PRE: Label inicializada
    //POST: crea y devueleve el gestor de layout (Hbox) de la medida indicada en la etiqueta (filas, columnas)
    private HBox hboxConfigurated(Label etiqueta){
        etiqueta.setFont(new Font("Times New Roman",24));
        HBox hb=new HBox();
        hb.setStyle("-fx-background-color: cornsilk;");
        hb.setSpacing(30);
        hb.setAlignment(Pos.CENTER);
        ChoiceBox<Integer> optionSize = choiceBoxConfigurated();
        sizesChoiceBoxes.add(optionSize);
        hb.getChildren().add(etiqueta);
        hb.getChildren().add(optionSize);
        return hb;
    }

    //PRE:
    //POST: crea y devuelve un choice box con los posibles tamaños para la tabla.
    private ChoiceBox<Integer> choiceBoxConfigurated(){
        var cb = new ChoiceBox<Integer>();
        int celdas= MINIMO_CELDAS;
        while (celdas<=MAXIMO_CELDAS){
            cb.getItems().add(celdas);
            celdas++;
        }
        return cb;
    }

}

