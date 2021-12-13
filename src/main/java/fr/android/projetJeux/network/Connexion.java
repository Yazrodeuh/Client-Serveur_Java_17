package fr.android.projetJeux.network;

import fr.android.projetJeux.Server;
import fr.android.projetJeux.game.Player;
import fr.android.projetJeux.game.Room;
import fr.android.projetJeux.game.morpion.Morpion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connexion implements Runnable {

    /**
     * socket du client
     */
    private final Socket socket;

    /**
     * liste des salles de jeux
     */
    private static final ArrayList<Room> rooms = new ArrayList<>();
    /**
     * flux de sortie
     */
    private ObjectOutputStream out;

    /**
     * flux d'entrée
     */
    private ObjectInputStream in;

    /**
     * Constructeur
     * @param socket socket du client
     */
    public Connexion(Socket socket) {
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
            if (Server.players.size() == Server.nbPlayers - 1) {
                out.writeObject("SERVER FULL");
            } else {
                Player player = newPlayer();

                for (Player p : Server.players) {
                    if (!Objects.equals(p.getName(), player.getName()) && p.getNumRoom() == -1) {
                        ArrayList<Player> gamers = new ArrayList<>();
                        gamers.add(player);
                        gamers.add(p);
                        Room room = new Room(new Morpion(), gamers);
                        Server.rooms.add(room);
                        System.out.println("STARTING ROOM with: " + gamers);
                        ExecutorService es = Executors.newSingleThreadExecutor();
                        es.execute(room);
                    }
                }

                //out.writeObject("Choose a game : " + Arrays.toString(Games.values()));

                //String gameSelected = (String) in.readObject();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        //startConnexion();

    }

    /**
     * Vérifie si le pseudo entré existe déjà
     * @param pseudo pseudo du joueur
     * @return true si existe, false sinon
     * @throws IOException en case de problème d'E/S
     */
    private boolean playerExists(String pseudo) throws IOException {
        for (Player p : Server.players) {
            if (Objects.equals(pseudo, p.getName())) {
                out.writeObject("Le joueur existe");
                return true;
            }
        }
        return false;
    }

    /**
     * Créer un nouveau joueur associé au client
     * @return Player
     * @throws IOException  en case de problème d'E/S
     * @throws ClassNotFoundException
     */
    private Player newPlayer() throws IOException, ClassNotFoundException {
        String pseudo;

        do {
            out.writeObject("Entrez un pseudo");
            pseudo = (String) in.readObject();
        } while (playerExists(pseudo));

        Player player = new Player(pseudo, socket, in, out);

        Server.players.add(player);

        out.writeObject("CONNECTED");

        return player;
    }

}
