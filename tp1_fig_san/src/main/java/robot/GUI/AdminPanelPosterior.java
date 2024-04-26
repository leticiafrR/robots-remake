package robot.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import robot.GUI.auxiliares.CustomButton;
import robot.GUI.auxiliares.CustomLabel;
import robot.GUI.auxiliares.ObjetoConTexto;
import robot.Modelo.Acciones.*;
import robot.Modelo.*;

public class AdminPanelPosterior {
    private final CustomButton tpRandom;
    private final CustomButton tpSafe;
    private final CustomButton esperar;
    private final CustomLabel score;
    private final CustomLabel nivel;

    public AdminPanelPosterior(int cantTpSafes, int nivelActual, int scoreActual){
        tpRandom = new CustomButton(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.TLP_RANDOM));
        tpSafe = new CustomButton(contentFormatted(EstadoDeJuego.EtiquetasModelo.TP_SAFE,cantTpSafes));
        esperar = new CustomButton(EstadoDeJuego.getEtiqueta(EstadoDeJuego.EtiquetasModelo.ESPERAR));
        score = new CustomLabel(contentFormatted(EstadoDeJuego.EtiquetasModelo.SCORE,scoreActual));
        nivel = new CustomLabel(contentFormatted(EstadoDeJuego.EtiquetasModelo.NIVEL,nivelActual));
        formatearTextos(new ObjetoConTexto[] {tpRandom,tpSafe,esperar,score,nivel});
    }

    private HBox crearHBox(Node[] nodes){
        HBox panelPosterior= new HBox();
        for (Node n: nodes){
            panelPosterior.getChildren().add(n);
            HBox.setHgrow(n, Priority.ALWAYS);
        }
        return panelPosterior;
    }

    private void formatearTextos(ObjetoConTexto[] objetosConTexto){
        for (ObjetoConTexto o: objetosConTexto){
            o.formatear();
        }
    }

    private String contentFormatted(EstadoDeJuego.EtiquetasModelo etiqueta, int v){
        return (EstadoDeJuego.getEtiqueta(etiqueta))+" "+v;
    }

    public void asignarEventos(EventHandler<ActionEvent> eRandom,EventHandler<ActionEvent> eSafe,EventHandler<ActionEvent> eWait){
        tpRandom.setOnAction(eRandom);
        tpSafe.setOnAction(eSafe);
        esperar.setOnAction(eWait);
    }

    public void actualizarContenido(String contenido,ObjetoConTexto o){
        o.setTexto(contenido);
    }

    public CustomButton getTpSafe() {
        return tpSafe;
    }

    public CustomLabel getScore() {
        return score;
    }

    public CustomLabel getNivel() {
        return nivel;
    }

    public HBox getHb() {
        return crearHBox(new Node[]{tpRandom,tpSafe,esperar,score,nivel});
    }
}
