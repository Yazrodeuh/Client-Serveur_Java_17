package fr.umontpellier.iut.FX;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window extends Stage{

    private Pane pane = new Pane();
    private double sizeX, sizeY;

    public Window(String title) {
        setTitle(title);
        setScene(new Scene(pane));
        setHeight(500);
        setWidth(500);
        setResizable(false);
        sizeX = 500;
        sizeY = 500;
        show();
    }

    public void add(Node... nodes) {
        pane.getChildren().addAll(nodes);
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }
}
