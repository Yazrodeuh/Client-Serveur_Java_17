package fr.android.projetJeux.fx.elements;

import javafx.scene.control.Label;

/**
 *
 */
public class GameText extends Label {

    /**
     *
     */
    public GameText() {

    }

    /**
     *
     * @param text
     */
    public GameText(String text) {
        super(text);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    /**
     *
     * @param size
     */
    public void setFontSize(int size) {
        setStyle("-fx-font-size: " + size + "px");
    }
}
