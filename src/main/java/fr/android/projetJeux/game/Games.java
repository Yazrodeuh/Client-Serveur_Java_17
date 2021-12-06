package fr.android.projetJeux.game;

import java.util.Arrays;

/**
 *
 */
public enum Games {

    Morpion(2);

    /**
     *
     */
    private final int nbGamers;

    /**
     *
     * @param nbGamers
     */
    Games(int nbGamers) {
        this.nbGamers = nbGamers;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Games.values()));
    }
}