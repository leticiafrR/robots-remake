package robot.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import robot.Modelo.Acciones.*;
import robot.Modelo.*;

public class PanelPosterior {
    private Button tpRandom;
    private Button tpSafe;
    private Button esperar;
    private Label score;//?????
    private Label nivel;


    public PanelPosterior(int cantTpSafes, int nivelActual){
        tpRandom= new Button(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.TLP_RANDOM));
        tpSafe= new Button(contentFormatted(EstadoDeJuego.EtiquetasModelo.TP_SAFE, cantTpSafes));
        esperar= new Button(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.ESPERAR));
        score= new Label(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.SCORE));
        nivel= new Label(contentFormatted(EstadoDeJuego.EtiquetasModelo.NIVEL,nivelActual));

        configPredet(tpRandom);
        configPredet(tpSafe);
        configPredet(esperar);
        configPredet(score);
        configPredet(nivel);
    }

    //para puntuación,nivel, tpsafe
    private String contentFormatted(EstadoDeJuego.EtiquetasModelo etiqueta, int v){
        return (EstadoDeJuego.getEtiqueta(etiqueta))+v;
    }

    //PRE: boton n inicializado
    //POST: le asigna la configuracion predeterminada para que se adapte al HBox
    private void configPredet(Button n){
        n.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        n.setStyle("-fx-alignment: center;");

        n.setTextAlignment(TextAlignment.CENTER);

    }

    //PRE: etiqueta n inicializado
    //POST: le asigna la configuracion predeterminada para que se adapte al HBox
    private void configPredet(Label n){
        n.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        n.setTextAlignment(TextAlignment.CENTER);
    }

    //PRE: Estado de juego y grilla ya inicializados
    //POST: Devuelve el panel de botones ya configurado
    public HBox crearHBox(EstadoDeJuego e, Grilla grilla){
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
        controladorPanelpos(e, grilla);
        return panelAbajo;
    }

    //PRE: Estado de juego y grilla ya inicializados
    //POST: Asigna comportamientos a los botones del panel
    public void controladorPanelpos(EstadoDeJuego e, Grilla grilla){
        tpRandom.setOnAction(actionEvent -> {
            AccionTeleportRandom accion = new AccionTeleportRandom();
            e.update(accion);

            grilla.pintarGrilla(e);
        });
        tpSafe.setOnAction(actionEvent -> {
            grilla.escucharTP();
        });
        esperar.setOnAction(actionEvent -> {
            AccionMovimiento accion= new AccionMovimiento(0,0);
            e.update(accion);
            grilla.pintarGrilla(e);
        });
    }

    public void actualizarBotonTPsafe(String contenido){
        tpSafe.setText(contenido);
    }
    //TPSAFE-> CLICKGRILLA (VEC2D)-> ACCIONTPSAFE(X,Y)...
    //TPSAFE-> GRILLA.TPSAFE(TRUE)

    //CLICKENGRILLA (VEC2D)--- TRUE{ACCION(VEC2D) TPSAFE(FALSE)}SINO{ACCION(A
    public Button getTpRandom() {
        return tpRandom;
    }

    public void setTpRandom(Button tpRandom) {
        this.tpRandom = tpRandom;
    }

    public Button getTpSafe() {
        return tpSafe;
    }

    public void agotar(Button tpSafe) {
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
