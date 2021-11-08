package fr.android.projetJeux.game;

import java.util.ArrayList;

public class Room implements Runnable{

    public static int nextIdRoom = 0;
    public int id;
    private Game game;

    public Room(Game game){
        this.game = game;
        id = nextIdRoom;
        nextIdRoom++;

    }

    @Override
    public void run() {

    }
}
