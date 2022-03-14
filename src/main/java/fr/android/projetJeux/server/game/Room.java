package fr.android.projetJeux.server.game;

import java.util.ArrayList;

/**
 * Représente la salle de jeu
 */
public class Room implements Runnable {
    /**
     * identifiant de la prochaine room qui sera créer (automatisation de l'identifiant)
     */
    public static int nextIdRoom = 0;

    /**
     * identifiant de la room
     */
    public int id;

    /**
     * jeu
     */
    private final IGame game;
    /**
     * liste des joueurs de la Room
     */
    private final ArrayList<Player> roomPlayers;

    /**
     * Constructeur
     * @param game jeu
     * @param roomPlayers liste des joueurs de la Room
     */
    public Room(IGame game, ArrayList<Player> roomPlayers) {
        this.game = game;
        this.roomPlayers = roomPlayers;
        id = nextIdRoom;
        nextIdRoom++;

        for (Player p : roomPlayers) {
            p.setNumRoom(id);
        }

    }

    @Override
    public void run() {
        game.start(roomPlayers);
    }
}
