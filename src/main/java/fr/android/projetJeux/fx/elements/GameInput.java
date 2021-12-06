package fr.android.projetJeux.fx.elements;

import javafx.scene.control.TextField;

public class GameInput extends TextField {

    /**
     * Champs de Texte
     */
    public GameInput() {

    }

    /**
     * Constructeur
     * @param x coordonnée x
     * @param y coordonnée y
     * @param w largeur
     * @param h hauteur
     */
    public GameInput(double x, double y, double w, double h) {
        setPosition(x, y);
        setDimensions(w, h);
    }

    /**
     * Définit la position
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    /**
     * Définit la taille
     * @param w largeur
     * @param h hauteur
     */
    public void setDimensions(double w, double h) {
        setMinSize(w, h);
        setMaxSize(w, h);
    }
}
