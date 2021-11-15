package fr.android.projetJeux.game.morpion;

import fr.android.projetJeux.game.Games;
import fr.android.projetJeux.game.IGame;
import fr.android.projetJeux.game.Player;
import fr.android.projetJeux.utils.ObjectOutputStreamRefactor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Morpion implements IGame {

    private ArrayList<Player> gamers;
    private HashMap<Player, Character> pion;
    private String code = "[Morpion]";

    private GridGame grid;

    private ObjectInputStream[] ins = new ObjectInputStream[2];
    private ObjectOutputStream[] outs = new ObjectOutputStream[2];

    private int currentPlayerIndex;

    public Morpion(){
        grid = new GridGame();
        pion = new HashMap<>(2);
    }

    private void initIO(int first, int second) {
        currentPlayerIndex = first;
        outs[first] = gamers.get(0).getOut();
        outs[second] = gamers.get(1).getOut();

        ins[first] = gamers.get(0).getIn();
        ins[second] = gamers.get(1).getIn();

    }

    private void sendGrid() throws IOException {
        outs[0].writeObject(grid);
        outs[1].writeObject(grid);
    }

//    private boolean verif (Coords coords) {
//        for (int i = coords.y - 2; i < coords.y + 2; i++) {
//        }
//        for (int i = coords.y - 2; i < coords.y + 2; i++) {
//        }
//
//
//        return false;
//    }

    @Override
    public void start(ArrayList<Player> gamers) {

        this.gamers = gamers;
        pion.put(gamers.get(0), 'X');
        pion.put(gamers.get(1), 'O');

        try {

            if (Math.random() < 0.5) {
                initIO(0,1);
            } else {
                initIO(1,0);
            }

            grid.setNamePlayer(gamers.get(currentPlayerIndex).getName());

            while (true) {
                sendGrid();
                Coords coords = (Coords) ins[0].readObject();
                grid.setMovement(coords,'X');

                nextPlayer();

                sendGrid();
                coords = (Coords) ins[1].readObject();
                grid.setMovement(coords,'O');
                nextPlayer();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex == 1) ? 0 : 1;
        grid.setNamePlayer(gamers.get(currentPlayerIndex).getName());
    }

    @Override
    public void stop() {

    }


    private boolean win(@NotNull Coords coords){
        return grid.getGrid()[coords.i][coords.j] != ' ' && winV(coords.j) && winH(coords.i) && winD();
    }

    private boolean winV(int x){
        return Objects.equals(grid.getGrid()[0][x], grid.getGrid()[1][x]) &&
                Objects.equals(grid.getGrid()[1][x], grid.getGrid()[2][x]);
    }

    private boolean winH(int y){
        return Objects.equals(grid.getGrid()[y][0], grid.getGrid()[y][1]) &&
                Objects.equals(grid.getGrid()[y][1], grid.getGrid()[y][2]);
    }

    private boolean winD(){
        return (Objects.equals(grid.getGrid()[0][0], grid.getGrid()[1][1]) &&
                Objects.equals(grid.getGrid()[1][1], grid.getGrid()[2][2])) || (
                Objects.equals(grid.getGrid()[0][2], grid.getGrid()[1][1]) &&
                Objects.equals(grid.getGrid()[1][1], grid.getGrid()[2][0]));
    }




    @Override
    public String toString() {
        return "Morpion{}";
    }


    public static void main(String[] args) {
        Morpion morpion = new Morpion();

        System.out.println(morpion.grid);

        morpion.grid.getGrid()[1][1] = 'Z';

        System.out.println(morpion.win(new Coords(1, 1)));
        System.out.println(morpion.grid);

    }

}
