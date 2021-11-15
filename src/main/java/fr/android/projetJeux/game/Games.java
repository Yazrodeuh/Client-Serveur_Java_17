package fr.android.projetJeux.game;

import fr.android.projetJeux.game.morpion.Morpion;
import fr.android.projetJeux.game.pong.Pong;

import java.util.ArrayList;
import java.util.Arrays;

public enum Games {

    Morpion(2);

    private int nbGamers;

    Games(int nbGamers){
        this.nbGamers = nbGamers;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Games.values()));
    }
}
