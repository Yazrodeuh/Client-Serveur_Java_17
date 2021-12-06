package fr.android.projetJeux.FX.Elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GameLine extends Line {

    public GameLine(double x, double y, double w, double h) {
        super(x, y, w, h);
        setStroke(Color.RED);
        setStrokeWidth(5);
        setVisible(false);
    }
}
