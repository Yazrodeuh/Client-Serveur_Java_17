package fr.android.projetJeux;

import fr.android.projetJeux.FX.App;
import fr.android.projetJeux.game.morpion.Coords;
import fr.android.projetJeux.game.morpion.GridGame;
import fr.android.projetJeux.utils.Code;
import fr.android.projetJeux.utils.SaisieClavier;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private String pseudo;
    private String status = "setup";

    public static void main(String[] args) {
        //launch();
        new Client().start();
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void start() {

        try {
            Socket client = new Socket("127.0.0.1", 4000);

            setIdentifiant(client);

            boolean finished = false;

            while (!finished) {
                String[] received = ((String) in.readObject()).split(Code.SEPARATOR);


                switch (received[0]) {
                    case Code.INFOS -> {
                        GridGame grid = GridGame.parse(received[1]);
                        grid.setNamePlayer(received[2]);

                        Platform.runLater(() -> App.setGrid(grid));

                    }
                    case Code.WINNER -> {
                        GridGame grid = GridGame.parse(received[1]);
                        grid.setNamePlayer(received[2]);

                        Platform.runLater(() -> App.setGrid(grid));
                        Platform.runLater(App::setWinner);
                        finished = true;
                        System.out.println(received[2].equals(pseudo) ? "Félicitation vous avez gagné !" : "Vous avez perdu");
                    }
                    case Code.BEGIN -> System.out.println("Vous êtes " + received[1]);
                    case Code.NUL -> {
                        GridGame grid = GridGame.parse(received[1]);
                        grid.setNamePlayer(received[2]);

                        Platform.runLater(() -> App.setGrid(grid));
                        Platform.runLater(App::setMatchNul);
                        finished = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> App.displayErrorMessage("SERVER NOT FOUND\nPlease start the server, then restart the application"));
            //Software.getMessages().setText();
        }
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void send(Coords c) throws IOException {
        out.writeObject(c);

    }


    private boolean setIdentifiant(Socket socket) {

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            String response;
            do {
                String message = (String) in.readObject();



                if(Objects.equals(message, "SERVER FULL")){
                    System.out.println(message);
                    socket.close();
                    break;
                }

                Platform.runLater(App::displayPseudoField);

                response = (String) in.readObject();

                Platform.runLater(App::hidePseudo);
                if (!response.equals("CONNECTED")) {
                    Platform.runLater(() -> App.displayErrorMessage("Ce pseudo existe déjà"));
                }

            } while (!response.equals("CONNECTED"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;

    }


}
