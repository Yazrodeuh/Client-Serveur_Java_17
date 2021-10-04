package fr.umontpellier.iut.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connexion implements Runnable{

    Socket socket;

    private static final ArrayList<Socket> clients = new ArrayList<>();

    public Connexion(Socket socket) {
        this.socket = socket;
        clients.add(socket);
    }

    public static ArrayList<Socket> getClients() {
        return clients;
    }

    @Override
    public void run() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        try {
            es.execute(new ServerThread(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
