package fr.android.projetJeux;

import fr.android.projetJeux.network.Connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class Server {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(4000);
        System.out.println("SERVER STARTED");
        ExecutorService es = Executors.newFixedThreadPool(10);

        try {
                try {
                    while (true) {
                        Socket client = server.accept();
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
