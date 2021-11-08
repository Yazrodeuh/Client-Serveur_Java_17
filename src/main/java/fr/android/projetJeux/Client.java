package fr.android.projetJeux;

import fr.umontpellier.iut.FX.Software;
import fr.umontpellier.iut.thread.Listen;
import fr.umontpellier.iut.thread.Send;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client extends Application {

    public static void main(String[] args) {
        //launch();
        new Client().start();
    }

    public void start(){

        try {
            Socket client = new Socket("127.0.0.1", 4000);

            setIdentifiant();

            ExecutorService es = Executors.newFixedThreadPool(10);
            es.execute(new Send(client));
            es.execute(new Listen(client));
        }catch (Exception e){
            System.out.println("SERVER NOT FOUND\nPlease start the server, then restart the application");
            //Software.getMessages().setText();
        }



    }


    @Override
    public void start(Stage stage) {


    }


    private boolean setIdentifiant(){






        return false;


    }



}
