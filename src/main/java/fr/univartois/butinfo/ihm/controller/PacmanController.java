package fr.univartois.butinfo.ihm.controller;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import fr.univartois.butinfo.ihm.model.*;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class PacmanController implements TileContent.IPacmanController {

    /**
     * La taille (en pixels) des images affichées sur la carte.
     */
    private static final int IMAGE_SIZE = 32;

    /**
     * La grille dans laquelle la carte du jeu est affichée.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Le label affichant le score du joueur.
     */
    @FXML
    private Label scoreLabel;

    /**
     * Le label affichant les vies du joueur.
     */
    @FXML
    private Label lifeLabel;

    /**
     * La partie en cours.
     */
    private PacmanGame game;

    /**
     * La scène de l'application (pour écouter les touches du clavier).
     */
    private Scene scene;

    /**
     * Crée une nouvelle instance de PacmanController.
     * Initialise la partie et lie ce contrôleur à la partie.
     */
    public PacmanController() {
        this.game = new PacmanGame();
        this.game.setController(this);
    }

    /**
     * Méthode d'initialisation appelée automatiquement par JavaFX après le chargement FXML.
     */
    @FXML
    public void initialize() {
        this.game.startGame();
    }

    /**
     * Associe la scène à ce contrôleur pour écouter les événements clavier.
     *
     * @param scene La scène principale de l'application.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP    -> game.movePlayerUp();
                case DOWN  -> game.movePlayerDown();
                case LEFT  -> game.movePlayerLeft();
                case RIGHT -> game.movePlayerRight();
            }
        });
    }

    @Override
    public void initMap(GameMap map) {
        gridPane.getChildren().clear();
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                Tile tile = map.get(i, j);
                ImageView imageView = new ImageView();
                imageView.setFitWidth(IMAGE_SIZE);
                imageView.setFitHeight(IMAGE_SIZE);

                imageView.imageProperty().bind(
                    Bindings.createObjectBinding(
                        () -> loadImage(tile.getContent().getName()),
                        tile.getContentProperty()
                    )
                );

                gridPane.add(imageView, j, i);
            }
        }
    }

    @Override
    public void bindCharacter(AbstractCharacter character) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);
        imageView.setImage(loadImage(character.getName()));

        GridPane.setConstraints(imageView, character.getColumn(), character.getRow());
        gridPane.getChildren().add(imageView);

        character.rowProperty().addListener((observable, oldValue, newValue) -> {
            GridPane.setConstraints(imageView, character.getColumn(), newValue.intValue());
        });

        character.columnProperty().addListener((observable, oldValue, newValue) -> {
            GridPane.setConstraints(imageView, newValue.intValue(), character.getRow());
        });
    }


    @Override
    public void bindPlayerStats(Player player) {
        if (scoreLabel != null) {
            scoreLabel.textProperty().unbind();
            scoreLabel.textProperty().bind(
                Bindings.concat("Score : ", player.scoreProperty().asString())
            );
        }
        if (lifeLabel != null) {
            lifeLabel.textProperty().unbind();
            lifeLabel.textProperty().bind(
                Bindings.concat("Vies : ", player.healthProperty().asString())
            );
        }
    }
    @Override
    public void decLives() {
    }

    /**
     * Gère le clic sur le bouton "Recommencer".
     */
    @FXML
    private void handleRestart() {
        game.startGame();
    }

    /**
     * Charge l'image correspondant au nom donné.
     *
     * @param name Le nom de l'image à charger (sans extension).
     * @return L'image chargée, ou null si l'image n'existe pas.
     */
    private Image loadImage(String name) {
        var stream = getClass().getResourceAsStream("/images/" + name + ".png");
        if (stream == null) {
            return null;
        }
        return new Image(stream);
    }

}
