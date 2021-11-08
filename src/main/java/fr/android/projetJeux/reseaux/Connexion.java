package fr.android.projetJeux.reseaux;

import fr.umontpellier.iut.thread.ServerThread;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connexion implements Runnable{

    Socket socket;

    private static final HashMap<Integer, Socket> clients = new HashMap<>();

    public Connexion(Socket socket) {
        this.socket = socket;
        clients.put(placeLibre(), socket);
    }

    public static HashMap<Integer, Socket> getClients() {
        return clients;
    }

    @Override
    public void run() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        try {
            es.execute(new ServerThread(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int placeLibre(){
        for (Map.Entry<Integer, Socket> client : clients.entrySet()){
            if(client.getValue() == null){
                return client.getKey();
            }
        }
        return clients.size() + 1;
    }










}
