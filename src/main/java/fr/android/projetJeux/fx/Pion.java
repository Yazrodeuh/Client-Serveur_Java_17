package fr.android.projetJeux.fx;


import fr.android.projetJeux.App;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Pion {

    private Node pion;
    private final double x;
    private final double y;
    private final double size;

    private Cursor cursor = Cursor.cursor("HAND");

    private static final double offset = 10;

    public Pion(char pion, double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;

        switch (pion) {
            case 'O' -> this.pion = o();
            case 'X' -> this.pion = x();
        }
    }

    private Circle o() {
        Circle c = new Circle(x + size / 2, y + size / 2, (size - offset * 2) / 2);
        c.setFill(App.background);
        c.setStroke(Color.BLACK);
        c.setStrokeWidth(10);
        c.setCursor(cursor);

       return c;

    }

    private Group x() {
        Group group = new Group();

        Line diag1 = new Line(x + offset, y + offset, x + size - offset, y + size - offset);
        diag1.setStroke(Color.BLACK);
        diag1.setStrokeWidth(10);

        Line diag2 = new Line(x + size - offset, y + offset, x + offset, y + size - offset);
        diag2.setStroke(Color.BLACK);
        diag2.setStrokeWidth(10);

        group.getChildren().addAll(diag1, diag2);
        group.setCursor(cursor);

        return group;

    }

    public Node getPion() {
        return pion;
    }


    public void disable() {
        this.cursor = Cursor.cursor("");
    }
}
