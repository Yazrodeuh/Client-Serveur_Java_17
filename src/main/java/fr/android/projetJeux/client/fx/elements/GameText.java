package fr.android.projetJeux.client.fx.elements;

import javafx.scene.control.Label;

/**
 * Texte du jeu
 */
public class GameText extends Label {

    /**
     * Constructeur
     */
    public GameText() {

    }

    /**
     * Constructeur
     * @param text texte
     */
    public GameText(String text) {
        super(text);
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
     * Taille du texte
     * @param size taille
     */
    public void setFontSize(int size) {
        setStyle("-fx-font-size: " + size + "px");
    }
}
