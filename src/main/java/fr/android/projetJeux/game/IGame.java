package fr.android.projetJeux.game;

import java.io.IOException;
import java.util.ArrayList;

public interface IGame {

    void start(ArrayList<Player> gamers);
    void stop(String status) throws IOException;

}
