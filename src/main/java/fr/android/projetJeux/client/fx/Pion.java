package fr.android.projetJeux.client.fx;


import fr.android.projetJeux.client.App;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Pion {

    /**
     * affichage du pion
     */
    private Node pion;
    /**
     * cooordonée x
     */
    private final double x;
    /**
     * cooordonée y
     */
    private final double y;
    /**
     * taille
     */
    private final double size;

    /**
     * cursor de survol
     */
    private Cursor cursor = Cursor.cursor("HAND");

    /**
     * décalage du bord de la case
     */
    private static final double offset = 10;

    /**
     * Constructeur
     * @param pion affichage du pion
     * @param x cooordonée x
     * @param y cooordonée y
     * @param size taille
     */
    public Pion(char pion, double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;

        switch (pion) {
            case 'O' -> this.pion = o();
            case 'X' -> this.pion = x();
        }
    }

    /**
     * Le pion devient un cercle
     * @return cercle
     */
    private Circle o() {
        Circle c = new Circle(x + size / 2, y + size / 2, (size - offset * 2) / 2);
        c.setFill(App.background);
        c.setStroke(Color.BLACK);
        c.setStrokeWidth(10);
        c.setCursor(cursor);

       return c;

    }

    /**
     * Le pion devient une croix
     * @return croix
     */
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

    /**
     * getter pion
     * @return pion
     */
    public Node getPion() {
        return pion;
    }

    /**
     *
     */
    public void disable() {
        this.cursor = Cursor.cursor("");
    }
}
