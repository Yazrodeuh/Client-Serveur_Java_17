package fr.android.projetJeux.network;

import fr.android.projetJeux.game.Game;
import fr.android.projetJeux.game.Player;
import fr.android.projetJeux.game.Room;
import fr.android.projetJeux.game.morpion.Morpion;
import fr.umontpellier.iut.thread.ServerThread;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connexion implements Runnable{

    private Socket socket;

    private static final ArrayList<Player> players = new ArrayList<>();
    private static final ArrayList<Player> waiting = new ArrayList<>();
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

    private boolean playerExists(String pseudo) throws IOException {
        for (Player p : players) {
            if(Objects.equals(pseudo, p.getName())) {
                out.writeObject("Le joueur existe");
                return true;
            }
        }
        out.writeObject("CONNECTED");
        return false;
    }


    @Override
    public void run() {


        try {
            newPlayer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (waiting.size() == 2){
                rooms.add(
                        new Room(
                                new Morpion(
                                        waiting.get(0),
                                        waiting.get(1)
                                )
                        )
                );
            }

        //startConnexion();

    }


    private boolean startConnexion(){
        /*ExecutorService es = Executors.newSingleThreadExecutor();
        try {
            es.execute(new ServerThread(socket));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }*/
        return false;
    }


    private void newPlayer() throws IOException, ClassNotFoundException {
        String pseudo = "";
        do {
            pseudo = (String) in.readObject();
        }while (playerExists(pseudo));

        Player player = new Player(pseudo, socket);
        players.add(player);
        waiting.add(player);

        out.writeObject("CONNECTED");
    }

}
