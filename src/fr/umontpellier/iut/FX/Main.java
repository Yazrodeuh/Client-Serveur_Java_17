package fr.umontpellier.iut.FX;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Software.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
