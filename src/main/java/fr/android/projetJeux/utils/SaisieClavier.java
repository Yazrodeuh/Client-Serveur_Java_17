package fr.android.projetJeux.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaisieClavier {

    /**
     * @return String|null
     */
    public static String saisieTerminal() {

        try {
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            return clavier.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    /**
     *
     */
    public static void main(String[] args) {
        String saisie = SaisieClavier.saisieTerminal();
        System.out.println("la saisie est : " + saisie);
    }
}
