package fr.android.projetJeux.network;

import fr.android.projetJeux.Server;
import fr.android.projetJeux.game.Games;
import fr.android.projetJeux.game.Player;
import fr.android.projetJeux.game.Room;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Connexion implements Runnable{

    private Socket socket;

    private static final ArrayList<Room> rooms = new ArrayList<>();
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Connexion(@NotNull Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void run() {

        try {
            Player player = newPlayer();

            out.writeObject("Choose a game : " + Arrays.toString(Games.values()));

            //String gameSelected = (String) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



        //startConnexion();

    }

    private boolean playerExists(String pseudo) throws IOException {
        for (Player p : Server.players) {
            if(Objects.equals(pseudo, p.getName())) {
                out.writeObject("Le joueur existe");
                return true;
            }
        }
        return false;
    }

    private Player newPlayer() throws IOException, ClassNotFoundException {
        String pseudo = "";
        do {
            pseudo = (String) in.readObject();
        }while (playerExists(pseudo));

        Player player = new Player(pseudo, socket);

        Server.players.add(player);

        out.writeObject("CONNECTED");

        return player;
    }

}
