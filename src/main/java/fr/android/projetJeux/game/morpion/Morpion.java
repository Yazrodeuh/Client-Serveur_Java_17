package fr.android.projetJeux.game.morpion;

import fr.android.projetJeux.game.Game;
import fr.android.projetJeux.game.Player;

public class Morpion extends Game {

    private Player player1;
    private Player player2;

    public Morpion(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;

        System.out.println(player1);
        System.out.println(player2);

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


}
