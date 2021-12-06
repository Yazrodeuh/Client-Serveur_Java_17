package fr.android.projetJeux.game.morpion;

import java.io.Serializable;
import java.util.Arrays;

public class GridGame implements Serializable {

    private String namePlayer;
    private char[][] grid;

    public GridGame() {
        this.grid = new char[3][3];

        for (char[] chars : grid) {
            Arrays.fill(chars, ' ');
        }

    }

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

    public String getNamePlayer() {
        return namePlayer;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public boolean isValid(Coords coord) {
        return grid[coord.i][coord.j] == ' ';
    }

    public void setMovement(Coords coord, char pion) {
        grid[coord.i][coord.j] = pion;
    }

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
