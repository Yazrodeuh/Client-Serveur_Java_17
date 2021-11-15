package fr.android.projetJeux.game.morpion;

import java.io.Serializable;

public class Coords implements Serializable {

    public int i,j;

    public Coords (int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "(" + i + " " + j + ")";
    }
}
