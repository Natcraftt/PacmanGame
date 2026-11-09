package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

/**
 * La classe Ghost représente les fantômes, adversaires du joueur dans le jeu Pacman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public class Ghost extends AbstractCharacter {

    /**
     * La couleur de ce fantôme.
     */
    private String color;

    /**
     * L'animation de déplacement du fantôme.
     */
    private Timeline timeline;

    /**
     * Le générateur de nombres aléatoires pour les déplacements.
     */
    private static final Random RANDOM = new Random();

    /**
     * Crée une nouvelle instance de Ghost.
     *
     * @param color La couleur du fantôme.
     * @param game  La partie en cours.
     */
    public Ghost(String color, PacmanGame game) {
        super(Integer.MAX_VALUE, game);
        this.color = color;
    }

    @Override
    public String getName() {
        return color;
    }

    /**
     * Déplace ce fantôme dans une direction aléatoire.
     */
    public void moveRandomly() {
        int direction = RANDOM.nextInt(4);
        switch (direction) {
            case 0 -> getGame().moveUp(this);
            case 1 -> getGame().moveDown(this);
            case 2 -> getGame().moveLeft(this);
            case 3 -> getGame().moveRight(this);
        }
    }

    /**
     * Lance l'animation de déplacement automatique du fantôme.
     */
    public void animate() {
        stopAnimation();
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> moveRandomly()));
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     * Arrête l'animation de déplacement du fantôme.
     */
    public void stopAnimation() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
    }

}
