package robot.GUI;


import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import robot.GUI.auxiliares.CustomButton;
import robot.GUI.auxiliares.CustomLabel;
import robot.GUI.auxiliares.ObjetoConTexto;
import robot.Modelo.Acciones.*;
import robot.Modelo.*;

public class PanelPosterior {
    private CustomButton tpRandom;
    private CustomButton tpSafe;
    private CustomButton esperar;
    private CustomLabel score;//?????
    private CustomLabel nivel;



    public PanelPosterior(int cantTpSafes, int nivelActual,int scoreActual){
        tpRandom= new CustomButton(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.TLP_RANDOM));
        tpSafe= new CustomButton(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.TP_SAFE)+" "+cantTpSafes);
        //tpSafe= new CustomButton(contentFormatted(EstadoDeJuego.EtiquetasModelo.TP_SAFE, cantTpSafes));
        esperar= new CustomButton(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.ESPERAR));
        score = new CustomLabel(contentFormatted(EstadoDeJuego.EtiquetasModelo.SCORE,scoreActual));
        nivel= new CustomLabel(contentFormatted(EstadoDeJuego.EtiquetasModelo.NIVEL,nivelActual));
        configPredet(tpRandom);
        configPredet(tpSafe);
        configPredet(esperar);
        configPredet(score);
        configPredet(nivel);

    }

    //para puntuación,nivel, tpsafe
    private String contentFormatted(EstadoDeJuego.EtiquetasModelo etiqueta, int v){
        return (EstadoDeJuego.getEtiqueta(etiqueta))+" "+v;
    }

    private void configPredet(ObjetoConTexto n){
        n.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //n.setMaxWidth(Double.MAX_VALUE);
        n.setTextAlignmentCustom(TextAlignment.CENTER);
        n.setAlineamiento(Pos.CENTER);
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
    public void actualizarLabelNivel(String contenido){
        nivel.setText(contenido);
    }

    public void actualizarScore(String contenido){
        score.setText(contenido);
    }

    public void actualizarContenido(String contenido,ObjetoConTexto o){
        o.setTexto(contenido);
    }

    //TPSAFE-> CLICKGRILLA (VEC2D)-> ACCIONTPSAFE(X,Y)...
    //TPSAFE-> GRILLA.TPSAFE(TRUE)

    //CLICKENGRILLA (VEC2D)--- TRUE{ACCION(VEC2D) TPSAFE(FALSE)}SINO{ACCION(A
    public CustomButton getTpRandom() {
        return tpRandom;
    }

    public void setTpRandom(CustomButton tpRandom) {
        this.tpRandom = tpRandom;
    }

    public CustomButton getTpSafe() {
        return tpSafe;
    }

    public void agotar(CustomButton tpSafe) {
        this.tpSafe = tpSafe;
    }

    public CustomButton getEsperar() {
        return esperar;
    }

    public void setEsperar(CustomButton esperar) {
        this.esperar = esperar;
    }

    public CustomLabel getScore() {
        return score;
    }

    public void setScore(CustomLabel score) {
        this.score = score;
    }

    public CustomLabel getNivel() {
        return nivel;
    }

    public void setNivel(CustomLabel nivel) {
        this.nivel = nivel;
    }
}
