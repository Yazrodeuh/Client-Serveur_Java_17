package fr.android.projetJeux.game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player {

    private final String name;
    private final Socket socket;
    private int numRoom;

    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Player(String name, Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this(name, socket, -1, in, out);
    }

    public Player(String name, Socket socket, int numRoom, ObjectInputStream in, ObjectOutputStream out) {
        this.name = name;
        this.socket = socket;
        this.numRoom = numRoom;
        this.in = in;
        this.out = out;
    }

    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getNumRoom() {
        return numRoom;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", numRoom=" + numRoom +
                '}';
    }
}
