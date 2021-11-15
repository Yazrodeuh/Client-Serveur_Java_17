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

    private ObjectInputStream[] ins = new ObjectInputStream[2];
    private ObjectOutputStream[] outs = new ObjectOutputStream[2];

    public Morpion(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;

        grid = new GridGame();

        System.out.println(player1);
        System.out.println(player2);
    }

    private void initIO(int first, int sec) throws IOException {
        ins[first] = new ObjectInputStream(player1.getSocket().getInputStream());
        ins[sec] = new ObjectInputStream(player2.getSocket().getInputStream());
        outs[first] = new ObjectOutputStream(player1.getSocket().getOutputStream());
        outs[sec] = new ObjectOutputStream(player1.getSocket().getOutputStream());
    }

    private void sendGrid() throws IOException {
        outs[0].writeObject(grid);
        outs[1].writeObject(grid);
    }

    private boolean verif (Coords coords) {
        for (int i = coords.y - 2; i < coords.y + 2; i++) {
        }
        for (int i = coords.y - 2; i < coords.y + 2; i++) {
        }


        return false;
    }

    @Override
    public void start() {

        try {

            if (Math.random() < 0.5) {
                initIO(0,1);
            } else {
                initIO(1,0);
            }

            grid.setNamePlayer(Math.random() < 0.5 ? player1.getName() : player2.getName());


            while (true) {
                sendGrid();
                Coords coords;
                coords = (Coords) ins[0].readObject();
                grid.setMovement(coords,"X");

                sendGrid();
                coords = (Coords) ins[1].readObject();
                grid.setMovement(coords,"X");
            }


        } catch (IOException | ClassNotFoundException e) {
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
