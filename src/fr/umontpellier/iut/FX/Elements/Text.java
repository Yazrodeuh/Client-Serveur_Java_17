package fr.umontpellier.iut.FX.Elements;

import javafx.scene.control.Label;

public class Text extends Label {

    double posX, posY;

    public Text(double posX, double posY) {
        setPosition(posX,posY);
    }

    public void setPosX(double posX) {
        this.posX = posX;
        setLayoutX(posX);
    }

    public void setPosY(double posY) {
        this.posY = posY;
        setLayoutY(posY);
    }

    public void setPosition(double x, double y) {
        setPosX(x);
        setPosY(y);
    }
}
