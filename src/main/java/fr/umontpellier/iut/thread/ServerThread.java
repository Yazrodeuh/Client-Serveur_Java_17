package fr.umontpellier.iut.thread;


import fr.umontpellier.iut.Message;
import fr.umontpellier.iut.utils.OOS;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{

    private final Socket socket;
    private final ObjectInputStream in;
    private ObjectOutputStream out;


    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }


    @Override
    public void run() {
        Message message;
        try {
            ArrayList<Socket> clients = Connexion.getClients();
            for (int i = 0; i < clients.size();i++) {
                    out = new OOS(clients.get(i).getOutputStream());
                    if (clients.get(i).equals(socket)) {
                        out.writeObject("" + (i + 1));
                    }
                    out.writeObject(clients.size());

            }
            out.writeObject(Connexion.getClients().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                message = (Message) in.readObject();

                ArrayList<Socket> clients = Connexion.getClients();
                int index = message.getNumClient() - 1;

                if (index == -1) {
                    for (Socket s : clients) {
                        if (!s.equals(socket)) {
                            out = new OOS(s.getOutputStream());
                            message.setNumClient(clients.indexOf(socket) + 1);
                            out.writeObject(message);
                        }
                    }
                } else {
                    out = new OOS(clients.get(index).getOutputStream());
                    message.setNumClient(clients.indexOf(socket) + 1);
                    out.writeObject(message);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
