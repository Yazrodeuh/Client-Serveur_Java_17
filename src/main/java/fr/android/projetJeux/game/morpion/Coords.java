package fr.android.projetJeux.game.morpion;

import java.io.Serializable;

public class Coords implements Serializable {

    public int x,y;

    public Coords (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
