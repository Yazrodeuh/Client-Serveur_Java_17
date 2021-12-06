package fr.android.projetJeux;

import fr.android.projetJeux.game.morpion.Coords;
import fr.android.projetJeux.game.morpion.GridGame;
import fr.android.projetJeux.utils.Code;
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
    private final String status = "setup";

    public static void main(String[] args) {
        new Client().start();
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void start() {

        try {
            Socket client = new Socket("192.168.158.166", 4000);

            setIdentifiant(client);

            boolean finished = false;

            while (!finished) {
                String[] received = ((String) in.readObject()).split(Code.SEPARATOR.getCodeValue());

                setGrid(received[1],received[2]);

                if (Objects.equals(received[0], Code.WINNER.getCodeValue())){
                    Platform.runLater(() -> App.setWinner(Integer.parseInt(received[3])));
                    finished = true;
                    System.out.println(received[2].equals(pseudo) ? "Félicitation vous avez gagné !" : "Vous avez perdu");
                }else if(Objects.equals(received[0], Code.BEGIN.getCodeValue())){
                    System.out.println("Vous êtes " + received[1]);
                }else if(Objects.equals(received[0], Code.NUL.getCodeValue())) {
                    GridGame grid = GridGame.parse(received[1]);
                    grid.setNamePlayer(received[2]);

                    Platform.runLater(() -> App.setGrid(grid));
                    Platform.runLater(App::setMatchNul);
                    finished = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> App.displayErrorMessage("SERVER NOT FOUND\nPlease start the server, then restart the application"));
            //Software.getMessages().setText();
        }
    }

    public void setGrid(String received,String name) {
        GridGame grid = GridGame.parse(received);
        grid.setNamePlayer(name);

        Platform.runLater(() -> App.setGrid(grid));
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    private boolean setIdentifiant(Socket socket) {

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            String response;
            do {
                String message = (String) in.readObject();


                if (Objects.equals(message, "SERVER FULL")) {
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
