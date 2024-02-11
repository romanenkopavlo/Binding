package org.example.rectangle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Slider sliderHauteur;
    @FXML
    Slider sliderLargeur;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Rectangle rectangle;
    @FXML
    TextField hauteurRectangle;
    @FXML
    TextField largeurRectangle;
    @FXML
    TextField perimetreRectangle;
    @FXML
    TextField surfaceRectangle;
    DoubleProperty hauteur = new SimpleDoubleProperty();
    DoubleProperty largeur = new SimpleDoubleProperty();
    DoubleProperty perimetre = new SimpleDoubleProperty();
    DoubleProperty surface = new SimpleDoubleProperty();
    StringConverter roundedConverter = new RoundedDoubleStringConverter(2);
    final double SEUIL_P = 3000.0;
    final double SEUIL_S = 5000.0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        perimetreRectangle.setDisable(true);
        surfaceRectangle.setDisable(true);

        hauteurRectangle.textProperty().bindBidirectional(hauteur, roundedConverter);
        largeurRectangle.textProperty().bindBidirectional(largeur, roundedConverter);

        perimetreRectangle.textProperty().bindBidirectional(perimetreProperty(), roundedConverter);
        surfaceRectangle.textProperty().bindBidirectional(surfaceProperty(), roundedConverter);

        rectangle.heightProperty().bind(hauteur);
        rectangle.widthProperty().bind(largeur);

        sliderHauteur.setMax(450);
        sliderHauteur.valueProperty().bindBidirectional(hauteur);

        sliderLargeur.setMax(350);
        sliderLargeur.valueProperty().bindBidirectional(largeur);

        perimetreRectangle.backgroundProperty().bind(Bindings.when(perimetreProperty().greaterThan(SEUIL_P))
                .then(new Background(new BackgroundFill(Color.RED, null, null)))
                .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null))));

        surfaceRectangle.backgroundProperty().bind(Bindings.when(surfaceProperty().greaterThan(SEUIL_S))
                .then(new Background(new BackgroundFill(Color.RED, null, null)))
                .otherwise(new Background(new BackgroundFill(Color.AQUA, null, null))));
    }
    public DoubleProperty perimetreProperty() {
        perimetre.bind((largeur.add(hauteur)).multiply(2));
        return perimetre;
    }
    public DoubleProperty surfaceProperty() {
        surface.bind((largeur.multiply(hauteur)));
        return surface;
    }
}