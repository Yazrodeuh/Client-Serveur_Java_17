package fr.android.projetJeux.utils;

import fr.android.projetJeux.server.Server;

import java.io.IOException;

public class ClientExist implements Runnable{


    @Override
    public void run() {
        while (true){
            clientExist();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Server.players);
        }
    }



    public void clientExist(){

        Server.players.removeIf(
                p -> {
                    try {
                        return !p.getSocket().getInetAddress().isReachable(100);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
        );


    }



}
