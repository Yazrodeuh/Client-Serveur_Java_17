package fr.umontpellier.iut.FX.Elements;

import fr.umontpellier.iut.FX.Software;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

public class ListClient extends ScrollPane {

    double posX, posY, sizeX, sizeY;

    public ListClient(){
        setPosition(Software.window.getWidth() * 0.7 + 20,10);
        setPrefSize(200, 200);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        Group group = new Group();
        group.getChildren().addAll(ajoutButton("btn1", 10),ajoutButton("btn2", 20),ajoutButton("btn3", 30),ajoutButton("btn4", 40),ajoutButton("btn5", 50));
        setContent(group);
    }

    public Button ajoutButton(String text, int y){

        Button button = new Button();
        button.setText(text);
        button.setPrefSize(50, 20);
        button.setLayoutY(y);

        return button;
    }



    public double getSizeY() {
        return sizeY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        setLayoutX(posX);
    }

    public void setPosY(double posY) {
        this.posY = posY;
        setLayoutY(posY);
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
        setWidth(sizeX);
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
        setHeight(sizeY);
    }

    public void setDimensions(double w, double h) {
        setSizeX(w);
        setSizeY(h);
    }

    public void setPosition(double x, double y) {
        setPosX(x);
        setPosY(y);
    }





}
