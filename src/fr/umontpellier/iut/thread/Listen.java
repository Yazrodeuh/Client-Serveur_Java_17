package fr.umontpellier.iut.thread;

import fr.umontpellier.iut.FX.Software;
import fr.umontpellier.iut.Message;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Listen implements Runnable {

    private Socket socket;
    private final ObjectInputStream in;

    public Listen(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

        while (true) {
            try {
                Object received = in.readObject();
                if (received instanceof Message) {
                    Platform.runLater(() -> Software.messageReceived((Message) received));
                } else if (received instanceof Integer){
                    Platform.runLater(() -> Software.updateClientList((Integer) received));
                } else if (received instanceof String) {
                    Platform.runLater(() -> Software.updateTitle((String) received));
                }




            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
