package fr.android.projetJeux.server.game.morpion;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Représente la grille de jeu
 */
public class GridGame implements Serializable {
    /**
     * nom du joueur courant
     */
    private String namePlayer;

    /**
     * grille
     */
    private char[][] grid;

    /**
     * Constructeur
     */
    public GridGame() {
        this.grid = new char[3][3];

        for (char[] chars : grid) {
            Arrays.fill(chars, ' ');
        }

    }

    /**
     * rempli la grille en fonction d'une représentation string de la grille
     * @param str grille au format string à parser
     * @return GridGame
     */
    public static GridGame parse(String str) {
        GridGame parsed = new GridGame();
        String[] lines = str.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] chars = lines[i].split(" \\| ");
            for (int j = 0; j < chars.length; j++) {
                parsed.setMovement(new Coords(i, j), chars[j].charAt(0));
            }
        }
        return parsed;
    }

    /**
     * getter NamePlayer
     * @return player name
     */
    public String getNamePlayer() {
        return namePlayer;
    }

    /**
     * getter Grid
     * @return Grid
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * setter grid
     * @param grid tableau de char représentant la grille
     */
    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    /**
     * Vérifie si la case est jouable
     * @param coord Coordonnée de la case
     * @return true si la case est jouable, false sinon
     */
    public boolean isValid(Coords coord) {
        return grid[coord.i][coord.j] == ' ';
    }

    /**
     * set la case jouée avec le pion du joueur
     * @param coord Coordonnée de la case jouée
     * @param pion charactère du pion (X ou O)
     */
    public void setMovement(Coords coord, char pion) {
        grid[coord.i][coord.j] = pion;
    }

    /**
     * setter name player
     * @param namePlayer player name
     */
    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }


    @Override
    public String toString() {
        return grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2] + "\n"
                + grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2] + "\n"
                + grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2] + "\n";
    }

    public static void main(String[] args) {
        String grid = "X | O | X\nX | O | X\nO | X | O\n";
        GridGame gridGame = GridGame.parse(grid);
        System.out.println(gridGame);
    }
}
