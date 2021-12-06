package fr.android.projetJeux.utils;

/**
 * Code servant à définir la nature d'une infos envoyée
 */
public enum Code {

    /**
     * envoi en partie non terminée
     */
    INFOS("[infos]"),
    /**
     * envoi lors ce que la partie vient de se terminer avec un gagnant
     */
    WINNER("[winner]"),
    /**
     * envoi lors ce que la partie vient de se terminer en match nul
     */
    NUL("[nul]"),
    /**
     * séparateur entre les infos envoyées
     */
    SEPARATOR("#");

    /**
     * valeur du code
     */
    private final String codeValue;

    /**
     * Constructeur
     * @param codeValue valeur du code
     */
    Code(String codeValue){
        this.codeValue = codeValue;
    }

    /**
     * getter codeValue
     * @return codeValue
     */
    public String getCodeValue() {
        return codeValue;
    }
}
