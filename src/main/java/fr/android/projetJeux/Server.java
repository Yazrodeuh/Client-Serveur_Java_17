package fr.android.projetJeux;

import fr.android.projetJeux.game.Player;
import fr.android.projetJeux.game.Room;
import fr.android.projetJeux.network.Connexion;
import fr.android.projetJeux.utils.ObjectOutputStreamRefactor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class Server {

    public static ArrayList<Player> players;
    public static ArrayList<Room> rooms;
    public static int nbPlayers = 20;

    /**
     *
     */
    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(4000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert server != null;

        players = new ArrayList<>();
        rooms = new ArrayList<>();

        System.out.println("SERVER STARTED");
        ExecutorService es = Executors.newFixedThreadPool(Server.nbPlayers);



        try {
                try {
                    while (true) {

                        Socket client = server.accept();
                        if(Server.players.size() < Server.nbPlayers){
                            es.execute(new Connexion(client));
                        }else if (Server.players.size() == Server.nbPlayers){

                        }

                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
