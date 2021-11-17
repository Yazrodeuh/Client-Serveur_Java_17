package fr.android.projetJeux;

import fr.android.projetJeux.game.Games;
import fr.android.projetJeux.game.morpion.Coords;
import fr.android.projetJeux.game.morpion.GridGame;
import fr.android.projetJeux.utils.Code;
import fr.android.projetJeux.utils.ObjectOutputStreamRefactor;
import fr.android.projetJeux.utils.SaisieClavier;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;

public class Client {
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private String pseudo;
    private GridGame grid = new GridGame();

    public static void main(String[] args) {
        //launch();
        new Client().start();
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
                        System.out.println(grid);
                        grid.setNamePlayer(received[2]);

                        if (Objects.equals(grid.getNamePlayer(), pseudo)) {
                            Coords laCase = null;
                            boolean valid = false;
                            do {
                                try {
                                    System.out.println("Votre tour ! choisir case (i , j)");
                                    int i = Integer.parseInt(SaisieClavier.saisieTerminal());
                                    int j = Integer.parseInt(SaisieClavier.saisieTerminal());
                                    laCase = new Coords(i,j);
                                    if (!grid.isValid(laCase)) {
                                        System.out.println(laCase + " à déjà été joué");
                                    } else {
                                        out.writeObject(laCase);
                                        valid = true;
                                    }

                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println(laCase + " est hors du tableau, i et j doivent être inférieurs ou égaux à 2");
                                }

                            } while (!valid);

                        } else {
                            System.out.println("Au tour de votre adversiare ! Veuillez patienter");
                        }
                    }
                    case Code.WINNER -> {
                        GridGame grid = GridGame.parse(received[1]);
                        System.out.println(grid);
                        grid.setNamePlayer(received[2]);

                        finished = true;
                        System.out.println(received[2].equals(pseudo) ? "Félicitation vous avez gagné !" : "Vous avez perdu");
                    }
                    case Code.BEGIN -> System.out.println("Vous êtes " + received[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SERVER NOT FOUND\nPlease start the server, then restart the application");
            //Software.getMessages().setText();
        }
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

                System.out.println(message);
                pseudo = SaisieClavier.saisieTerminal();
                out.writeObject(pseudo);
                response = (String) in.readObject();
                System.out.println(response);
            } while (!response.equals("CONNECTED"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;

    }


}
