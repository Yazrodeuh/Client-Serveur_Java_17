package fr.android.projetJeux.game.morpion;

import fr.android.projetJeux.game.IGame;
import fr.android.projetJeux.game.Player;
import fr.android.projetJeux.utils.Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Morpion implements IGame {
    /**
     *
     */
    private ArrayList<Player> players;

    /**
     *
     */
    private final HashMap<Player, Character> pion;

    /**
     *
     */
    private int winline = 0;

    /**
     *
     */
    private final GridGame grid;

    /**
     *
     */
    private Player currentPlayer;

    /**
     *
     */
    private boolean finished = false;

    /**
     *
     */
    public Morpion() {
        grid = new GridGame();
        //preRemplissageDeLaGrillePourNePasAvoirAFaireUnePartieCompleteAvantDArriverALaPartieATester();
        pion = new HashMap<>(2);
    }


    /**
     *
     * @param code
     * @throws IOException
     */
    private void sendInfos(String code) throws IOException {
        for (Player p : players) {
            p.getOut().writeObject(code +
                    Code.SEPARATOR.getCodeValue() +
                    grid +
                    Code.SEPARATOR.getCodeValue() +
                    currentPlayer.getName() +
                    Code.SEPARATOR.getCodeValue() +
                    winline
            );
        }
    }

    /**
     *
     * @param gamers
     */
    @Override
    public void start(ArrayList<Player> gamers) {

        this.players = gamers;

        try {

            if (Math.random() < 0.5) {
                currentPlayer = players.get(0);
            } else {
                currentPlayer = players.get(1);
            }

            pion.put(currentPlayer, 'X');
            pion.put(getNextPlayer(), 'O');

            grid.setNamePlayer(currentPlayer.getName());

            while (!finished) {
                sendInfos(Code.INFOS.getCodeValue());
                Coords coords = (Coords) currentPlayer.getIn().readObject();
                grid.setMovement(coords, pion.get(currentPlayer));
                if (win(coords)) {
                    stop("win");
                } else if (matchNul()) {
                    stop("nul");
                } else {
                    System.out.println("ici");
                    nextPlayer();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    private Player getNextPlayer() {
        return players.get((players.indexOf(currentPlayer) + 1) % 2);
    }

    /**
     *
     */
    private void nextPlayer() {
        currentPlayer = getNextPlayer();
        grid.setNamePlayer(currentPlayer.getName());
    }

    /**
     *
     * @param status
     * @throws IOException
     */
    @Override
    public void stop(String status) throws IOException {
        finished = true;
        sendInfos(Objects.equals(status, "nul") ? Code.BEGIN.getCodeValue() : Code.WINNER.getCodeValue());

    }

    /**
     *
     * @return
     */
    private boolean matchNul() {
        char[][] grille = grid.getGrid();
        System.out.println(grid);
        boolean free = false;
        for (int i = 0; i < grille.length && !free; i++) {
            for (int j = 0; j < grille[i].length && !free; j++) {
                System.out.println(i + " " + j);
                free = grille[i][j] == ' ';
            }
        }
        return !free;
    }

    /**
     *
     * @param coords
     * @return
     */
    private boolean win(Coords coords) {
        return winV(coords.j) || winH(coords.i) || winD(coords);
    }

    /**
     *
     * @param j
     * @return
     */
    private boolean winV(int j) {
        boolean win = Objects.equals(grid.getGrid()[0][j], grid.getGrid()[1][j]) &&
                Objects.equals(grid.getGrid()[1][j], grid.getGrid()[2][j]);
        if (win) {
            winline = j;
            return true;
        }
        return false;
    }

    /**
     *
     * @param i
     * @return
     */
    private boolean winH(int i) {
        boolean win = Objects.equals(grid.getGrid()[i][0], grid.getGrid()[i][1]) &&
                Objects.equals(grid.getGrid()[i][1], grid.getGrid()[i][2]);

        if (win) {
            winline = i + 3;
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    private boolean winRD() {
        boolean win = Objects.equals(grid.getGrid()[0][0], grid.getGrid()[1][1]) &&
                Objects.equals(grid.getGrid()[1][1], grid.getGrid()[2][2]);

        if (win) {
            winline = 6;
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    private boolean winLD() {
        boolean win = Objects.equals(grid.getGrid()[0][2], grid.getGrid()[1][1]) &&
                Objects.equals(grid.getGrid()[1][1], grid.getGrid()[2][0]);

        if (win) {
            winline = 7;
            return true;
        }
        return false;
    }

    /**
     *
     * @param coords
     * @return
     */
    private boolean winD(Coords coords) {
        boolean won = false;
        if (coords.equals(new Coords(0, 0)) || coords.equals(new Coords(2, 2))) {
            won = winRD();
        } else if (coords.equals(new Coords(0, 2)) || coords.equals(new Coords(2, 0))) {
            won = winLD();
        } else if (coords.equals(new Coords(1, 1))) {
            won = winRD() || winLD();
        }
        return won;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Morpion{}";
    }

    /**
     *
     */
    private void preRemplissageDeLaGrillePourNePasAvoirAFaireUnePartieCompleteAvantDArriverALaPartieATester() {
        char[][] chars = {
                {'O', 'O', ' '},
                {'O', 'X', 'O'},
                {'X', 'X', 'O'}
        };

        grid.setGrid(chars);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Morpion morpion = new Morpion();

        System.out.println(morpion.grid);

        morpion.grid.getGrid()[1][1] = 'Z';

        System.out.println(morpion.win(new Coords(1, 1)));
        System.out.println(morpion.grid);

    }

}
