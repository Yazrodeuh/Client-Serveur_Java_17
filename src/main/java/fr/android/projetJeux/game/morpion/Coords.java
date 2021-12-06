package fr.android.projetJeux.game.morpion;

import java.io.Serializable;
import java.util.Objects;

/**
 * Couple i,j pour des coordonnées de tableau
 */
public class Coords implements Serializable {

    /**
     * ligne
     */
    public int i;

    /**
     * colonne
     */
    public int j;

    /**
     * Constructeur
     * @param i ligne
     * @param j colonne
     */
    public Coords(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return i == coords.i && j == coords.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "(" + i + " " + j + ")";
    }
}
