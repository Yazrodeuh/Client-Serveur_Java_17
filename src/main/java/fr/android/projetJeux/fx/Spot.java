package fr.android.projetJeux.fx;

import fr.android.projetJeux.App;
import fr.android.projetJeux.game.morpion.Coords;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Représente une case de la grille sur l'interface graphique
 */
public class Spot extends Rectangle {

    /**
     * Coordonnées de la case
     */
    private final Coords coords;

    /**
     * Constructeur
     * @param c coordonnée de la case
     * @param x coordonnée x de la case dans la fenêtre
     * @param y coordonnée y de la case dans la fenêtre
     * @param size taille de la case dans la fenêtre
     */
    public Spot(Coords c, double x, double y, double size) {
        super(x, y, size, size);

        coords = c;

        setFill(App.background);
        setStroke(Color.BLACK);
        setStrokeWidth(5);
        setCursor(Cursor.cursor("HAND"));

        setOnMouseClicked(mouseEvent -> {
            try {
                App.sendCoords(coords);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
