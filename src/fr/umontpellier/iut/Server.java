package fr.umontpellier.iut;

import fr.umontpellier.iut.thread.Connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(4000);
        System.out.println("SERVER STARTED");
        try {
                try {
                    while (true) {
                        Socket client = server.accept();
                        System.out.println("NEW CLIENT CONNECTED");
                        ExecutorService es = Executors.newFixedThreadPool(10);
                        es.execute(new Connexion(client));
                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
