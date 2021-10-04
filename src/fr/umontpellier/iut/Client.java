package fr.umontpellier.iut;

import fr.umontpellier.iut.FX.Software;
import fr.umontpellier.iut.thread.Listen;
import fr.umontpellier.iut.thread.Send;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client extends Application {

    private static Send send;
    private static ObjectInputStream in;

    public static void main(String[] args) {
        launch();
    }

    public static Send getSend() {
        return send;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Software.start();

        try {
            Socket client;

            client = new Socket("127.0.0.1", 4000);

            send = new Send(client);

            ExecutorService es = Executors.newFixedThreadPool(10);
            es.execute(send);
            es.execute(new Listen(client));
        }catch (Exception e){
            Software.getMessages().setText("SERVER NOT FOUND\nPlease start the server, then restart the application");
        }


    }
}
