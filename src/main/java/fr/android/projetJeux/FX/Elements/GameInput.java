package fr.android.projetJeux.FX.Elements;

import javafx.scene.control.TextField;

public class GameInput extends TextField {

    public GameInput() {

    }

    public GameInput(double x, double y,double w, double h) {
        setPosition(x,y);
        setDimensions(w,h);
    }

    public void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    public void setDimensions(double w, double h) {
        setMinSize(w,h);
        setMaxSize(w,h);
    }
}
