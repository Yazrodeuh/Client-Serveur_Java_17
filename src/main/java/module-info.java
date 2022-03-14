module Client.Serveur.Java{
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;

    exports fr.android.projetJeux.client;
    opens fr.android.projetJeux.client to javafx.fxml;
    exports fr.android.projetJeux.server;
    opens fr.android.projetJeux.server to javafx.fxml;

}