package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe Player représente le personnage du joueur, donc Pacman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public class Player extends AbstractCharacter {

    /**
     * Le score du joueur, sous forme de propriété.
     */
    private final IntegerProperty score = new SimpleIntegerProperty(0);

    /**
     * Crée une nouvelle instance de Player.
     *
     * @param game La partie en cours.
     */
    public Player(PacmanGame game) {
        super(3, game);
    }


    @Override
    public String getName() {
        return "pacman";
    }

    /**
     * Donne le score actuel du joueur.
     *
     * @return Le score du joueur.
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Donne la propriété correspondant au score du joueur.
     *
     * @return La propriété du score.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Ajoute un point au score du joueur.
     */
    public void addPoint() {
        score.set(score.get() + 1);
    }

    /**
     * Remet le score du joueur à zéro.
     */
    public void resetScore() {
        score.set(0);
    }

}
