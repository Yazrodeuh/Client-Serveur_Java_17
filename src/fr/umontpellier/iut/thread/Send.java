package fr.umontpellier.iut.thread;

import fr.umontpellier.iut.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Send implements Runnable{

    private final Socket socket;
    private final ObjectOutputStream out;
    private Message message;

    public Send(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public void run() {
        while(true) {
            try {
                try {
                    if (this.getMessage() != null) {
                        out.writeObject(this.getMessage());
                        this.setMessage(null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception ignored){}

        }
    }
}
