package fr.android.projetJeux.utils;

import fr.android.projetJeux.Server;
import fr.android.projetJeux.game.Player;

public class ClientExist implements Runnable{


    @Override
    public void run() {
        while (true){
            clientExist();
        }
    }



    public void clientExist(){

        Server.players.removeIf(p -> p.getSocket().isClosed());


    }



}
