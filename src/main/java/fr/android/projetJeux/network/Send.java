package fr.android.projetJeux.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Send implements Runnable{

    private final ObjectOutputStream out;


    public Send(Socket socket) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        while (true) {

        }
    }
}
