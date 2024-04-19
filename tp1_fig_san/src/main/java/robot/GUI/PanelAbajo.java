package robot.GUI;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

public class PanelAbajo{
    private Button tpRandom;
    private Button tpSafe;
    private Button esperar;
    private Label score;
    private Label nivel;
    public PanelAbajo(){

        tpRandom= new Button("TPRandom");
        tpSafe= new Button("TPSafe");
        esperar= new Button("Esperar");
        score= new Label("score: ");
        nivel= new Label("Nivel: ");

        configPredet(tpRandom);
        configPredet(tpSafe);
        configPredet(esperar);
        configPredet(score);
        configPredet(nivel);
    }

    private void configPredet(Button n){
        n.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        n.setTextAlignment(TextAlignment.CENTER);
    }
    private void configPredet(Label n){
        n.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        n.setTextAlignment(TextAlignment.CENTER);
    }
    public HBox crearHBox(){
        HBox panelAbajo= new HBox();
        HBox.setHgrow(tpRandom, Priority.ALWAYS);
        HBox.setHgrow(tpSafe, Priority.ALWAYS);
        HBox.setHgrow(esperar, Priority.ALWAYS);
        HBox.setHgrow(score, Priority.ALWAYS);
        HBox.setHgrow(nivel, Priority.ALWAYS);
        panelAbajo.getChildren().add(tpRandom);
        panelAbajo.getChildren().add(tpSafe);
        panelAbajo.getChildren().add(esperar);
        panelAbajo.getChildren().add(score);
        panelAbajo.getChildren().add(nivel);
        return panelAbajo;
    }

    public Button getTpRandom() {
        return tpRandom;
    }

    public void setTpRandom(Button tpRandom) {
        this.tpRandom = tpRandom;
    }

    public Button getTpSafe() {
        return tpSafe;
    }

    public void setTpSafe(Button tpSafe) {
        this.tpSafe = tpSafe;
    }

    public Button getEsperar() {
        return esperar;
    }

    public void setEsperar(Button esperar) {
        this.esperar = esperar;
    }

    public Label getScore() {
        return score;
    }

    public void setScore(Label score) {
        this.score = score;
    }

    public Label getNivel() {
        return nivel;
    }

    public void setNivel(Label nivel) {
        this.nivel = nivel;
    }
}
