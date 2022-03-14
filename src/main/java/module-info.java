module Client.Serveur.Java{
    requires javafx.controls;
    requires javafx.fxml;

    exports fr.android.projetJeux;
    opens fr.android.projetJeux to javafx.fxml;
    exports fr.android.projetJeux.client;
    opens fr.android.projetJeux.client to javafx.fxml;
    exports fr.android.projetJeux.server;
    opens fr.android.projetJeux.server to javafx.fxml;

}