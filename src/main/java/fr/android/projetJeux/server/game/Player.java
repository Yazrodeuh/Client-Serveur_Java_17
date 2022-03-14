package fr.android.projetJeux.server.game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Répresente le joueur
 */
public class Player {

    /**
     * pseudo du joueur
     */
    private final String name;
    /**
     * socket du client associé au joueur
     */
    private final Socket socket;
    /**
     * numéro de la room dans laquelle le joueur est présent
     */
    private int numRoom;

    /**
     * flux de sortie
     */
    public final ObjectOutputStream out;

    /**
     * flux d'entrée
     */
    public final ObjectInputStream in;

    /**
     *
     */
    private PublicKey publicKey;

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Constructeur
     * @param name pseudo du joueur
     * @param socket socket du client associé au joueur
     * @param in flux d'entrée
     * @param out flux de sortie
     */
    public Player(String name, Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this(name, socket, -1, in, out);
    }

    /**
     *
     * @param name pseudo du joueur
     * @param socket socket du client associé au joueur
     * @param in flux d'entrée
     * @param out flux de sortie
     * @param numRoom numéro de la room dans laquel le joueur est présent
     */
    public Player(String name, Socket socket, int numRoom, ObjectInputStream in, ObjectOutputStream out) {
        this.name = name;
        this.socket = socket;
        this.numRoom = numRoom;
        this.in = in;
        this.out = out;
    }

    /**
     * setter numRoom
     * @param numRoom numéro de la room dans laquel le joueur est présent
     */
    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    /**
     * getter name
     * @return pseudo
     */
    public String getName() {
        return name;
    }

    /**
     * getter socket
     * @return socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * getter numRoom
     * @return numRoom
     */
    public int getNumRoom() {
        return numRoom;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", numRoom=" + numRoom +
                '}';
    }
}
