module Client.Serveur.Java{
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.android.projetJeux.FX to javafx.fxml;
    exports fr.android.projetJeux.FX;

}