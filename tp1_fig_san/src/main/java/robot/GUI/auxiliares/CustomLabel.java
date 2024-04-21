package robot.GUI.auxiliares;


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
}
