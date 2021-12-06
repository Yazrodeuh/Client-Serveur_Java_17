package fr.android.projetJeux.fx.elements;

import javafx.scene.control.Button;

public class GameButton extends Button {

    /**
     *
     * @param text
     */
    public GameButton(String text) {
        super(text);
    }

    /**
     *
     * @param text
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public GameButton(String text, double x, double y, double w, double h) {
        this(text);
        setPosition(x, y);
        setDimensions(w, h);
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
     * @param w
     * @param h
     */
    public void setDimensions(double w, double h) {
        setMinSize(w, h);
        setMaxSize(w, h);
    }
}
