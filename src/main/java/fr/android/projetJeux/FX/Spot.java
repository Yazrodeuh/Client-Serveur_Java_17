package fr.android.projetJeux.FX;

import fr.android.projetJeux.game.morpion.Coords;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Spot extends Rectangle {
    private final Coords coords;

    public Spot(Coords c, double x, double y, int size) {
        super(x,y,size,size);

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
