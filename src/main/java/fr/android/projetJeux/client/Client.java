package fr.android.projetJeux.client;

import fr.android.projetJeux.server.game.morpion.GridGame;
import fr.android.projetJeux.utils.Code;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class Client {

    /**
     * flux de sortie
     */
    private ObjectOutputStream out;

    /**
     * flux d'entrée
     */
    private ObjectInputStream in;

    /**
     * pseudo du client
     */
    private String pseudo;


    /**
     * getter out
     *
     * @return out
     */
    public ObjectOutputStream getOut() {
        return out;
    }

    /**
     * démarre le client
     */
    public void start() {

        try {
            Socket client = new Socket("localhost", 4000);

            setIdentifiant(client);

            boolean finished = false;

            while (!finished) {

                byte[] gridReceived = ((byte[]) in.readObject());

                String[] received = new String(SecurityDES.decode(gridReceived, key)).split(Code.SEPARATOR.getCodeValue());

                setGrid(received[1], received[2]);

                if (Objects.equals(received[0], Code.WINNER.getCodeValue())) {
                    Platform.runLater(() -> App.setWinner(Integer.parseInt(received[3])));
                    finished = true;
                    System.out.println(received[2].equals(pseudo) ? "Félicitation vous avez gagné !" : "Vous avez perdu");
                } else if (Objects.equals(received[0], Code.NUL.getCodeValue())) {
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

    /**
     * Envoie la grille récu à l'app pour l'afficher
     *
     * @param received grille
     * @param name     pseudo du joueur courant
     */
    public void setGrid(String received, String name) {
        GridGame grid = GridGame.parse(received);
        grid.setNamePlayer(name);

        Platform.runLater(() -> App.setGrid(grid));
    }

    /**
     * getter pseudo
     *
     * @return pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * setter pseudo
     *
     * @param pseudo pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * échange avec le server pour le pseudo
     *
     * @param socket socket du client
     * @return true
     */
    private boolean setIdentifiant(Socket socket) {

        try {

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

    public Key getKey() {
        return key;
    }

}
