package fr.android.projetJeux.game;

import java.net.Socket;

public class Player {

    private String name;
    private Socket socket;
    private int numRoom;

    public Player (String name, Socket socket){
        this(name, socket, -1);
    }

    public Player(String name, Socket socket, int numRoom){
        this.name = name;
        this.socket = socket;
        this.numRoom = numRoom;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", numRoom=" + numRoom +
                '}';
    }
}
