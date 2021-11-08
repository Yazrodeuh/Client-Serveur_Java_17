package fr.android.projetJeux;

import fr.android.projetJeux.utils.SaisieClavier;
import fr.umontpellier.iut.FX.Software;
import fr.umontpellier.iut.thread.Listen;
import fr.umontpellier.iut.thread.Send;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    /*@Override
    public void start(Stage stage) {


    }*/


    private boolean setIdentifiant(Socket socket) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String response;
            do {
                System.out.println("Entrez un pseudo: ");
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
