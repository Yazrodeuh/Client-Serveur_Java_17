package fr.android.projetJeux.utils;

public enum Code {

    BEGIN("[begin]"),
    INFOS("[infos]"),
    WINNER("[winner]"),
    NUL("[nul]"),
    SEPARATOR("#");

    private final String codeValue;

    Code(String codeValue){
        this.codeValue = codeValue;
    }

    public String getCodeValue() {
        return codeValue;
    }
}
