package robot.GUI.auxiliares;

import javafx.scene.text.TextAlignment;

public interface ObjetoConTexto {
    void setTexto(String contenido);
    void setMaxSize(double width, double height);
    void setTextAlignmentCustom(TextAlignment value);
    void setMaxWidth(double value);
}
