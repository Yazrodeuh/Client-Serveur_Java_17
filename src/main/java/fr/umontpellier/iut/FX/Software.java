package fr.umontpellier.iut.FX;

import fr.umontpellier.iut.Client;
import fr.umontpellier.iut.FX.Elements.*;
import fr.umontpellier.iut.Message;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;

import javax.swing.*;

public class Software {

    public static Window window;
    private static final Text messages = new Text(20, 20);
    private static Select selectDest;
    private static int num;

    public static void start() {
        window = new Window("chat");

        Div messageDisplay = new Div();

        Input enterMessage = new Input();
        //Input enterDest = new Input(enterMessage.getPosX() + enterMessage.getSizeX() + 10, enterMessage.getPosY(), 50, enterMessage.getSizeY());
        selectDest = new Select(window.getWidth() * 0.75, window.getHeight() * 0.3, window.getWidth() * 0.2, enterMessage.getSizeY());
        selectDest.getItems().add("Broadcast");
        selectDest.setValue("Broadcast");

        Button send = new Button("Send");
        send.setLayoutX(enterMessage.getPosX());
        send.setLayoutY(enterMessage.getPosY() + enterMessage.getSizeY() + 10);
        send.setOnAction(actionEvent -> sendMessage(enterMessage.getText(), selectDest.getValue()));

        ListClient clients = new ListClient();

        window.add(messageDisplay, enterMessage, selectDest, send, messages, clients);

        /*try {
            Client.starConnection();
        } catch (Exception e){
            messages.setText("SERVER NOT FOUND\nPlease start the server, then restart the application");
        }*/


    }

    public static void messageReceived(Message msg) {
        messages.setText(messages.getText() + "Client " + msg.getNumClient() + ": " + msg.getMessage() + "\n");

    }

    public static void sendMessage(String text, String dest) {
        Message msg = new Message(text, dest.equals("Broadcast") ? 0 : Integer.parseInt(dest));
        Client.getSend().setMessage(msg);
        messages.setText(messages.getText() + "Vous: " + msg.getMessage() + " --> " + (!dest.equals("Broadcast") ? "Client " + dest : dest) + "\n");
    }

    public static void updateClientList(int nbClients) {
        selectDest.getItems().clear();
        selectDest.getItems().add("Broadcast");

        for (int i = 0; i < nbClients; i++) {
            if (i + 1 != num) {
                selectDest.getItems().add("" + (i + 1));
            }

        }
    }

    public static void updateTitle(String title) {
        num = Integer.parseInt(title);
        window.setTitle("Client " + title);
    }

    public static Text getMessages() {
        return messages;
    }
}
