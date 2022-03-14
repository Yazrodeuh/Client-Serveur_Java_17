package fr.android.projetJeux.client.fx.elements;

import javafx.scene.control.Button;

/**
 * Bouton dans le jeu
 */
public class GameButton extends Button {

    /**
     * Constructeur
     * @param text texte du bouton
     */
    public GameButton(String text) {
        super(text);
    }

    /**
     * Constructeur
     * @param text texte du bouton
     * @param x coordonnée x
     * @param y coordonnée y
     * @param w largeur
     * @param h hauteur
     */
    public GameButton(String text, double x, double y, double w, double h) {
        this(text);
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
