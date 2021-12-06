package fr.android.projetJeux.game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 */
public class Player {

    /**
     *
     */
    private final String name;
    /**
     *
     */
    private final Socket socket;
    /**
     *
     */
    private int numRoom;

    /**
     *
     */
    private final ObjectOutputStream out;

    /**
     *
     */
    private final ObjectInputStream in;

    /**
     *
     * @param name
     * @param socket
     * @param in
     * @param out
     */
    public Player(String name, Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this(name, socket, -1, in, out);
    }

    /**
     *
     * @param name
     * @param socket
     * @param numRoom
     * @param in
     * @param out
     */
    public Player(String name, Socket socket, int numRoom, ObjectInputStream in, ObjectOutputStream out) {
        this.name = name;
        this.socket = socket;
        this.numRoom = numRoom;
        this.in = in;
        this.out = out;
    }

    /**
     *
     * @param numRoom
     */
    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     *
     * @return
     */
    public int getNumRoom() {
        return numRoom;
    }

    /**
     *
     * @return
     */
    public ObjectInputStream getIn() {
        return in;
    }

    /**
     *
     * @return
     */
    public ObjectOutputStream getOut() {
        return out;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", numRoom=" + numRoom +
                '}';
    }
}
