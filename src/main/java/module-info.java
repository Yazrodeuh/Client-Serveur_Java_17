module Client.Serveur.Java{
    requires javafx.controls;
    requires javafx.fxml;

    exports fr.android.projetJeux;
    opens fr.android.projetJeux to javafx.fxml;

}