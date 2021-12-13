package fr.android.projetJeux.game.morpion;

import fr.android.projetJeux.game.IGame;
import fr.android.projetJeux.game.Player;
import fr.android.projetJeux.utils.Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Morpion implements IGame {
    /**
     * Liste des joueurs connectés.
     */
    private ArrayList<Player> players;

    /**
     * liste des charactères possibles
     */
    private final HashMap<Player, Character> pion;

    /**
     * numéro de la ligne gagnante (vaut 0 tant que le jeu n'est pas terminé)
     */
    private int winline = 0;

    /**
     * Grille de la partie.
     */
    private final GridGame grid;

    /**
     * Joueur qui doit jouer.
     */
    private Player currentPlayer;

    /**
     * booléen qui indique si le jeu est en cours ou terminé
     */
    private boolean finished = false;

    /**
     * Contructeur
     */
    public Morpion() {
        grid = new GridGame();
        //preRemplissageDeLaGrillePourNePasAvoirAFaireUnePartieCompleteAvantDArriverALaPartieATester();
        pion = new HashMap<>(2);
    }


    /**
     * Envoie les infos au client
     * @param code Code qui indique au client la nature du message
     * @throws IOException en cas de problème d'entrée / sortie
     */
    private void sendInfos(String code) throws IOException {
        for (Player p : players) {
            p.getOut().writeObject(code +
                    Code.SEPARATOR.getCodeValue() +
                    grid +
                    Code.SEPARATOR.getCodeValue() +
                    currentPlayer.getName() +
                    Code.SEPARATOR.getCodeValue() +
                    winline
            );
        }
    }

    /**
     * Démmare le jeu
     * @param gamers liste des joueurs de la partie
     */
    @Override
    public void start(ArrayList<Player> gamers) {

        this.players = gamers;

        try {

            if (Math.random() < 0.5) {
                currentPlayer = players.get(0);
            } else {
                currentPlayer = players.get(1);
            }

            pion.put(currentPlayer, 'X');
            pion.put(getNextPlayer(), 'O');

            grid.setNamePlayer(currentPlayer.getName());

            while (!finished) {
                sendInfos(Code.INFOS.getCodeValue());
                Coords coords = (Coords) currentPlayer.getIn().readObject();
                grid.setMovement(coords, pion.get(currentPlayer));
                if (win(coords)) {
                    stop("win");
                } else if (matchNul()) {
                    stop("nul");
                } else {
                    System.out.println("ici");
                    nextPlayer();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère le joueur suivant
     * @return Joueur suivant
     */
    private Player getNextPlayer() {
        return players.get((players.indexOf(currentPlayer) + 1) % 2);
    }

    /**
     * change la valeur du joueur courant
     */
    private void nextPlayer() {
        currentPlayer = getNextPlayer();
        grid.setNamePlayer(currentPlayer.getName());
    }

    /**
     * Arrête le jeu et envoie la grille ainsi que le pseudo du gagnant
     * @param status win ou nul, indique s'il y a un gagnant ou si le match est nul pour envoi l'info correspondante
     * @throws IOException en cas de problème d'E/S
     */
    @Override
    public void stop(String status) throws IOException {
        finished = true;
        sendInfos(Objects.equals(status, "nul") ? Code.NUL.getCodeValue() : Code.WINNER.getCodeValue());

    }

    /**
     * Vérifie si le match est nul
     * @return true si match null, false sinon
     */
    private boolean matchNul() {
        char[][] grille = grid.getGrid();
        System.out.println(grid);
        boolean free = false;
        for (int i = 0; i < grille.length && !free; i++) {
            for (int j = 0; j < grille[i].length && !free; j++) {
                System.out.println(i + " " + j);
                free = grille[i][j] == ' ';
            }
        }
        return !free;
    }

    /**
     * Vérifie s'il y a un gagnant
     * @param coords Cooordonnée de la dernière case jouée
     * @return true si c'est gagné, false sinon
     */
    private boolean win(Coords coords) {
        return winV(coords.j) || winH(coords.i) || winD(coords);
    }

    /**
     * Vérification des colonnnes
     * @param j num de la colonne
     * @return true si c'est gagné, false sinon
     */
    private boolean winV(int j) {
        boolean win = Objects.equals(grid.getGrid()[0][j], grid.getGrid()[1][j]) &&
                Objects.equals(grid.getGrid()[1][j], grid.getGrid()[2][j]);
        if (win) {
            winline = j;
            return true;
        }
        return false;
    }

    /**
     * Vérification des lignes
     * @param i num de la ligne
     * @return true si c'est gagné, false sinon
     */
    private boolean winH(int i) {
        boolean win = Objects.equals(grid.getGrid()[i][0], grid.getGrid()[i][1]) &&
                Objects.equals(grid.getGrid()[i][1], grid.getGrid()[i][2]);

        if (win) {
            winline = i + 3;
            return true;
        }
        return false;
    }

    /**
     * Vérification de la diagonale droite
     * @return true si c'est gagné, false sinon
     */
    private boolean winRD() {
        boolean win = Objects.equals(grid.getGrid()[0][0], grid.getGrid()[1][1]) &&
                Objects.equals(grid.getGrid()[1][1], grid.getGrid()[2][2]);

        if (win) {
            winline = 6;
            return true;
        }
        return false;
    }

    /**
     * Vérification de la diagonale gauche
     * @return true si c'est gagné, false sinon
     */
    private boolean winLD() {
        boolean win = Objects.equals(grid.getGrid()[0][2], grid.getGrid()[1][1]) &&
                Objects.equals(grid.getGrid()[1][1], grid.getGrid()[2][0]);

        if (win) {
            winline = 7;
            return true;
        }
        return false;
    }

    /**
     * Vérification des diagonales
     * @return true si c'est gagné, false sinon
     */
    private boolean winD(Coords coords) {
        boolean won = false;
        if (coords.equals(new Coords(0, 0)) || coords.equals(new Coords(2, 2))) {
            won = winRD();
        } else if (coords.equals(new Coords(0, 2)) || coords.equals(new Coords(2, 0))) {
            won = winLD();
        } else if (coords.equals(new Coords(1, 1))) {
            won = winRD() || winLD();
        }
        return won;
    }

    @Override
    public String toString() {
        return "Morpion{}";
    }

    /**
     * pre Remplissage De La Grille Pour Ne Pas Avoir A Faire Une Partie Complete Avant D'Arriver A La Partie A Tester
     */
    private void preRemplissageDeLaGrillePourNePasAvoirAFaireUnePartieCompleteAvantDArriverALaPartieATester() {
        char[][] chars = {
                {'O', 'O', ' '},
                {'O', 'X', 'O'},
                {'X', 'X', 'O'}
        };

        grid.setGrid(chars);
    }

    public static void main(String[] args) {
        Morpion morpion = new Morpion();

        System.out.println(morpion.grid);

        morpion.grid.getGrid()[1][1] = 'Z';

        System.out.println(morpion.win(new Coords(1, 1)));
        System.out.println(morpion.grid);

    }

}
