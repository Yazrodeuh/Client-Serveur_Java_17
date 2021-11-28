package fr.android.projetJeux.FX.Elements;

import javafx.scene.control.Button;

public class GameButton extends Button {

    public GameButton(String text) {
        super(text);
    }

    public GameButton(String text,double x, double y,double w, double h) {
        this(text);
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
