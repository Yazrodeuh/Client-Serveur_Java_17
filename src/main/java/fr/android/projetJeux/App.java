package fr.android.projetJeux;

import fr.android.projetJeux.Client;
import fr.android.projetJeux.FX.Elements.GameButton;
import fr.android.projetJeux.FX.Elements.GameInput;
import fr.android.projetJeux.FX.Elements.GameLine;
import fr.android.projetJeux.FX.Elements.GameText;
import fr.android.projetJeux.FX.Pion;
import fr.android.projetJeux.FX.Spot;
import fr.android.projetJeux.game.morpion.Coords;
import fr.android.projetJeux.game.morpion.GridGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

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

    private static final GameLine[] lineList = new GameLine[8];

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(group, sizeX, sizeY, background));
        stage.setTitle("Morpion");
        stage.show();
        new Thread(() -> client.start()).start();

        error.setPosition(25,sizeY * 0.7);
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

        double size = 150;
        double xDepart = (sizeX - size * 3) / 2;
        double y = 25 + (sizeY - size * 3) / 2;

        text = new GameText(grid.getNamePlayer().equals(client.getPseudo()) ? "Votre tour choisir case" : "Au tour de votre adversaire, veuillez patienter");
        text.setPosition(xDepart,25);
        text.setFontSize(25);

        contentGroup.getChildren().add(text);

        double xLine = xDepart + size / 2;
        double yLine = y -25;
        double lineSize = size * 3 + 50;
        for (int i = 0; i < 3; i++) {
            lineList[i] = new GameLine(xLine, yLine,xLine,yLine + lineSize);
            xLine += size;
        }

        xLine = xDepart - 25;
        yLine = y + size / 2;
        for (int i = 3; i < 6; i++) {
            lineList[i] = new GameLine(xLine, yLine,xLine + lineSize,yLine);
            yLine += size;
        }

        xLine = xDepart - 25;
        yLine = y - 25;

        lineList[6] = new GameLine(xLine, yLine,xLine + lineSize,yLine + lineSize);
        lineList[7] = new GameLine(xLine + lineSize,yLine,xLine, yLine + lineSize);


        group.getChildren().addAll(Arrays.asList(lineList));

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

    public static void setWinner(int index) {
        lineList[index].setVisible(true);
        text.setText(grid.getNamePlayer().equals(client.getPseudo()) ? "Félicitation vous avez gagné" : "Vous avez perdu");
    }
    public static void setMatchNul() {
        text.setText("Match Nul");
    }


    public static void main(String[] args) {
        launch();
    }
}
