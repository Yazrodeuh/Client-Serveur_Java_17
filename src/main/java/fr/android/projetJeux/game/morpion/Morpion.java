package fr.android.projetJeux.game.morpion;

import fr.android.projetJeux.game.Game;
import fr.android.projetJeux.game.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

public class Morpion extends Game {

    private Player player1;
    private Player player2;

    private GridGame grid;

    public Morpion(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;

        grid = new GridGame();

        System.out.println(player1);
        System.out.println(player2);
    }

    @Override
    public void start() {

        try {
            ObjectInputStream in1 = new ObjectInputStream(player1.getSocket().getInputStream());
            ObjectInputStream in2 = new ObjectInputStream(player2.getSocket().getInputStream());
            ObjectOutputStream out1 = new ObjectOutputStream(player1.getSocket().getOutputStream());
            ObjectOutputStream out2 = new ObjectOutputStream(player2.getSocket().getOutputStream());

            grid.setNamePlayer(Math.random() < 0.5 ? player1.getName() : player2.getName());

            out1.writeObject(grid);
            out2.writeObject(grid);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }


    @Override
    public String toString() {
        return "Morpion{}";
    }
}
