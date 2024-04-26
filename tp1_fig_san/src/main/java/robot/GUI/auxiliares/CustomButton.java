package robot.GUI.auxiliares;

import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

public class CustomButton extends javafx.scene.control.Button implements ObjetoConTexto{

    public CustomButton() {
        super();  // La llamada al constructor super debe ser la primera instrucción
    }
    public CustomButton(String s){
        super(s);
    }

    @Override
    public void setTexto(String contenido) {
        super.setText(contenido);
    }
    public void setMaxSize(double width, double height){super.setMaxSize(width,height);}

    public void setTextAlignmentCustom(TextAlignment value){super.setTextAlignment(value);};
    public void setAlineamiento(Pos pos){super.setAlignment(pos);}

}
