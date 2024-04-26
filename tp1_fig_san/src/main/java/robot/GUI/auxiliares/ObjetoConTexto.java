package robot.GUI.auxiliares;

import javafx.scene.text.TextAlignment;
import javafx.geometry.Pos;
import org.w3c.dom.Node;

public interface ObjetoConTexto {
    void setTexto(String contenido);
    void setMaxSize(double width, double height);
    void setTextAlignmentCustom(TextAlignment value);
    void setAlineamiento(Pos pos);
    void formatear();
}
