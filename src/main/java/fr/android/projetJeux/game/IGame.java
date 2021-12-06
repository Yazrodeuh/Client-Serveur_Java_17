package fr.android.projetJeux.game;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public interface IGame {
    /**
     *
     * @param gamers
     */
    void start(ArrayList<Player> gamers);

    /**
     *
     * @param status
     * @throws IOException
     */
    void stop(String status) throws IOException;

}
