package fr.android.projetJeux.FX;

import fr.android.projetJeux.Client;
import fr.android.projetJeux.FX.Elements.GameButton;
import fr.android.projetJeux.FX.Elements.GameInput;
import fr.android.projetJeux.FX.Elements.GameText;
import fr.android.projetJeux.game.morpion.Coords;
import fr.android.projetJeux.game.morpion.GridGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static Color background = Color.GRAY;
    public static double sizeX = 600;
    public static double sizeY = 600;
    public static Group group = new Group();
    public static Group contentGroup = new Group();
    public static Group errorGroup = new Group();

    public static Client client = new Client();
    public static GridGame grid;
    private static GameText text;
    private static final GameText error = new GameText();

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(group, sizeX, sizeY, background));
        stage.setTitle("Morpion");
        stage.show();
        new Thread(() -> client.start()).start();

        error.setLayoutX(25);
        error.setLayoutY(sizeY * 0.7);
        error.setTextFill(Color.RED);
        error.setFontSize(25);
        errorGroup.getChildren().add(error);

        group.getChildren().addAll(contentGroup,errorGroup);

    }

    public static void displayPseudoField() {
        contentGroup.getChildren().clear();

        GameText label = new GameText("Pseudo");
        label.setPosition(15,sizeY / 2);
        label.setFontSize(25);

        GameInput field = new GameInput(100, sizeY / 2, sizeX - 200,40);

        GameButton submit = new GameButton("Valider");
        submit.setPosition(field.getMinWidth() + field.getLayoutX() - 60,field.getLayoutY() + 45);
        submit.setOnAction(actionEvent -> {
            try {
                client.setPseudo(field.getText());
                client.getOut().writeObject(field.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        contentGroup.getChildren().addAll(field,label,submit);
    }

    public static void hidePseudo() {
        contentGroup.getChildren().clear();

        GameText message = new GameText("En attente d'adversaire");
        message.setFontSize(30);
        message.setPosition(sizeX / 3,sizeY / 2);

        contentGroup.getChildren().add(message);
    }

    public static void displayErrorMessage(String message) {
        error.setText(message);
    }

    public static void hideError() {
        error.setText("");
    }

    public static void setGrid(GridGame g) {
        grid = g;
        contentGroup.getChildren().clear();
        hideError();

        int size = 150;
        double xDepart = (sizeX - size * 3) / 2;
        double y = 25 + (sizeY - size * 3) / 2;

        text = new GameText(grid.getNamePlayer().equals(client.getPseudo()) ? "Votre tour choisir case" : "Au tour de votre adversaire, veuillez patienter");
        text.setPosition(xDepart,25);
        text.setFontSize(25);

        contentGroup.getChildren().add(text);

        int i = 0, j = 0;
        for (char[] row : grid.getGrid()) {
            double x = xDepart;
            for (char pion : row) {
                Spot spot = new Spot(new Coords(i,j),x, y, size);
                contentGroup.getChildren().add(spot);
                if (pion != ' ') {
                    Pion p = new Pion(pion, x, y, size);
                    contentGroup.getChildren().add(p.getPion());
                }
                x += size;
                j++;
            }
            j = 0;
            y += size;
            i++;
        }
    }

    public static void sendCoords(Coords c) throws IOException {

        if (grid.isValid(c)) {
            if (grid.getNamePlayer().equals(client.getPseudo())) {
                client.getOut().writeObject(c);
            }

        } else {
            System.out.println("déjà été joué");
        }

    }

    public static void setWinner() {
        text.setText(grid.getNamePlayer().equals(client.getPseudo()) ? "Félicitation vous avez gagné" : "Vous avez perdu");
    }
    public static void setMatchNul() {
        text.setText("Match Nul");
    }


    public static void main(String[] args) {
        launch();
    }
}
