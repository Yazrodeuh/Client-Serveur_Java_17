package fr.umontpellier.iut;

import java.io.Serializable;

public class Message implements Serializable {

    private int numClient;
    private String message;

    public Message(String message, int numClient) {
        this.numClient = numClient;
        this.message = message;
    }

    public int getNumClient() {
        return numClient;
    }

    public String getMessage() {
        return message;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }
}
