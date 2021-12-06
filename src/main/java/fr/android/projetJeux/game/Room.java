package fr.android.projetJeux.game;

import java.util.ArrayList;

public class Room implements Runnable {

    public static int nextIdRoom = 0;
    public int id;
    private final IGame game;
    private final ArrayList<Player> roomPlayers;

    public Room(IGame game, ArrayList<Player> roomPlayers) {
        this.game = game;
        this.roomPlayers = roomPlayers;
        id = nextIdRoom;
        nextIdRoom++;

        for (Player p : roomPlayers) {
            p.setNumRoom(id);
        }

    }

    @Override
    public void run() {
        game.start(roomPlayers);
    }
}
