package fr.umontpellier.iut.FX;

import fr.umontpellier.iut.Client;
import fr.umontpellier.iut.FX.Elements.Div;
import fr.umontpellier.iut.FX.Elements.Input;
import fr.umontpellier.iut.FX.Elements.Select;
import fr.umontpellier.iut.FX.Elements.Text;
import fr.umontpellier.iut.Message;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Software {

    public static Window window;
    private static final Text messages = new Text(20, 20);
    private static Select selectDest;
    private static int num;

    public static void start() {
        window = new Window("chat");

        Div messageDisplay = new Div(10, 10, 460, 350);
        messageDisplay.setStrokeWidth(3);
        messageDisplay.setStroke(Color.BLACK);
        messageDisplay.setFill(Color.WHITE);

        Input enterMessage = new Input(messageDisplay.getPosX(), messageDisplay.getPosY() + messageDisplay.getSizeY() + 10, messageDisplay.getSizeX() - 110, 50);
        //Input enterDest = new Input(enterMessage.getPosX() + enterMessage.getSizeX() + 10, enterMessage.getPosY(), 50, enterMessage.getSizeY());
        selectDest = new Select(enterMessage.getPosX() + enterMessage.getSizeX() + 10, enterMessage.getPosY(), 90, enterMessage.getSizeY());
        selectDest.getItems().add("Broadcast");
        selectDest.setValue("Broadcast");

        Button send = new Button("Send");
        send.setLayoutX(enterMessage.getPosX());
        send.setLayoutY(enterMessage.getPosY() + enterMessage.getSizeY() + 10);
        send.setOnAction(actionEvent -> sendMessage(enterMessage.getText(), selectDest.getValue()));

        window.add(messageDisplay, enterMessage, selectDest, send, messages);

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
