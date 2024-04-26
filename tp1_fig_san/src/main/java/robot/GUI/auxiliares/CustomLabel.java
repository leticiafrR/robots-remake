package robot.GUI.auxiliares;


import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

public class CustomLabel extends javafx.scene.control.Label implements ObjetoConTexto {
    public CustomLabel() {
        super();
    }
    public CustomLabel(String s){
        super(s);
    }
    @Override
    public void setTexto(String contenido) {
        super.setText(contenido);
    }
    public void setMaxSize(double width, double height){super.setMaxSize(width,height);}
    public void setTextAlignmentCustom(TextAlignment value){super.setTextAlignment(value);}
    public void setAlineamiento(Pos pos) {super.setAlignment(pos);}
    public void formatear(){
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setTextAlignmentCustom(TextAlignment.CENTER);
        this.setAlineamiento(Pos.CENTER);
    }
}
