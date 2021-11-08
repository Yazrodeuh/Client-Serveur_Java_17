package fr.android.projetJeux.game.morpion;

import java.io.Serializable;

public class GridGame implements Serializable {

    private String namePlayer;
    private String[][] grid;

    public GridGame(){
        this.grid = new String[3][3];
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setMovement(int x, int y, String pion){
        grid[y][x] = pion;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }
}
