package fr.umontpellier.iut.FX;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window extends Stage{

    private Group pane = new Group();

    public Window(String title) {
        setTitle(title);
        setScene(new Scene(pane));
        setHeight(700);
        setWidth(700);
        //setResizable(false);
        show();

    }

    public void add(Node... nodes) {
        pane.getChildren().addAll(nodes);
    }

}
