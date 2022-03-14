package fr.android.projetJeux.server.game;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 */
public interface IGame {
    /**
     * @param gamers
     */
    void start(ArrayList<Player> gamers);

    /**
     * @param status
     * @throws IOException
     */
    void stop(String status) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

}
