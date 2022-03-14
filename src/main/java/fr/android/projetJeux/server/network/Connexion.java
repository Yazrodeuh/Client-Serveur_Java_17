package fr.android.projetJeux.server.network;

import fr.android.projetJeux.security.SecurityDES;
import fr.android.projetJeux.security.SecurityRSA;
import fr.android.projetJeux.server.Server;
import fr.android.projetJeux.server.game.Player;
import fr.android.projetJeux.server.game.Room;
import fr.android.projetJeux.server.game.morpion.Morpion;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
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
    
    
    public Key addSecurityLayer(Player p1, Player p2) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        Key des = SecurityDES.generateKey();
        byte[] rsaP1 = SecurityRSA.cipherKey(des,p1.getPublicKey());
        p1.out.writeObject(rsaP1);
        byte[] rsaP2 = SecurityRSA.cipherKey(des,p2.getPublicKey());
        p2.out.writeObject(rsaP2);
        return des;
    }


    @Override
    public void run() {

        try {
            if (Server.players.size() == Server.nbPlayers - 1) {
                out.writeObject("SERVER FULL");
            } else {
                Player player = newPlayer();
                player.setPublicKey((PublicKey) in.readObject());

                for (Player p : Server.players) {
                    if (!Objects.equals(p.getName(), player.getName()) && p.getNumRoom() == -1) {
                        ArrayList<Player> gamers = new ArrayList<>();
                        
                        Key key = addSecurityLayer(player,p);

                        System.out.println(key);

                        gamers.add(player);
                        gamers.add(p);
                        Room room = new Room(new Morpion(key), gamers);
                        room.setDesKey(key);
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
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
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
