package fr.android.projetJeux.utils;

public enum Code {

    BEGIN("[begin]"),
    INFOS("[infos]"),
    WINNER("[winner]"),
    NUL("[nul]"),
    SEPARATOR("#");

    /**
     *
     */
    private final String codeValue;

    /**
     *
     * @param codeValue
     */
    Code(String codeValue){
        this.codeValue = codeValue;
    }

    /**
     *
     * @return
     */
    public String getCodeValue() {
        return codeValue;
    }
}
