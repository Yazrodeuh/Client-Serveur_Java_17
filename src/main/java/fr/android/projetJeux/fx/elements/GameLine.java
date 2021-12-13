package fr.android.projetJeux.fx.elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Ligne de victoire
 */
public class GameLine extends Line {

    /**
     * Constructeur
     * @param x coordonnée x
     * @param y coordonnée y
     * @param w largeur
     * @param h hauteur
     */
    public GameLine(double x, double y, double w, double h) {
        super(x, y, w, h);
        setStroke(Color.RED);
        setStrokeWidth(5);
        setVisible(false);
    }
}
