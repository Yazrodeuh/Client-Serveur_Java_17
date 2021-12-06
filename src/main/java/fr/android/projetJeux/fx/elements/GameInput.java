package fr.android.projetJeux.fx.elements;

import javafx.scene.control.TextField;

public class GameInput extends TextField {

    /**
     *
     */
    public GameInput() {

    }

    /**
     *
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public GameInput(double x, double y, double w, double h) {
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
