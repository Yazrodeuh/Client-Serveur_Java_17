package fr.android.projetJeux;

import fr.android.projetJeux.utils.SaisieClavier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static void main(String[] args) {
        //launch();
        new Client().start();
    }

    public void start() {

        try {
            Socket client = new Socket("127.0.0.1", 4000);

            setIdentifiant(client);

            /*ExecutorService es = Executors.newFixedThreadPool(10);
            es.execute(new Send(client));
            es.execute(new Listen(client));*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SERVER NOT FOUND\nPlease start the server, then restart the application");
            //Software.getMessages().setText();
        }
    }


    private boolean setIdentifiant(Socket socket) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String response;
            do {
                String message = (String) in.readObject();

                if(Objects.equals(message, "SERVER FULL")){
                    socket.close();
                    break;
                }
                out.writeObject(SaisieClavier.saisieTerminal());
                response = (String) in.readObject();
                System.out.println(response);
            } while (!response.equals("CONNECTED"));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;

    }


}
