package fr.android.projetJeux.network;

import fr.umontpellier.iut.thread.ServerThread;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connexion implements Runnable{

    private Socket socket;

    private static final HashMap<String, Socket> clients = new HashMap<>();
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
