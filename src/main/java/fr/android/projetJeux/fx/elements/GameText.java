package fr.android.projetJeux.fx.elements;

import javafx.scene.control.Label;

public class GameText extends Label {

    public GameText() {

    }

    public GameText(String text) {
        super(text);
    }

    public void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    public void setFontSize(int size) {
        setStyle("-fx-font-size: " + size + "px");
    }
}
