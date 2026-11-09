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
 * La classe AbstractCharacter est la classe parente des différents personnages pouvant se
 * déplacer dans le jeu Pacman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public abstract class AbstractCharacter {

    /**
     * La ligne où se trouve ce personnage, sous forme de propriété.
     */
    private IntegerProperty row = new SimpleIntegerProperty();

    /**
     * La colonne où se trouve ce personnage, sous forme de propriété.
     */
    private IntegerProperty column = new SimpleIntegerProperty();

    /**
     * Les points de vie restants pour ce personnage.
     */
    private final IntegerProperty health;

    /**
     * La partie en cours.
     */
    private PacmanGame game;

    /**
     * Crée une nouvelle instance de AbstractCharacter.
     *
     * @param initialHealth Les points de vie initiaux du personnage.
     * @param game          La partie en cours.
     */
    protected AbstractCharacter(int initialHealth, PacmanGame game) {
        this.health = new SimpleIntegerProperty(initialHealth);
        this.game = game;
    }

    /**
     * Donne le nom de ce personnage.
     *
     * @return Le nom de ce personnage.
     */
    public abstract String getName();

    /**
     * Donne la ligne où se trouve ce personnage.
     *
     * @return La ligne où se trouve ce personnage.
     */
    public int getRow() {
        return row.get();
    }

    /**
     * Donne la propriété correspondant à la ligne de ce personnage.
     *
     * @return La propriété de la ligne de ce personnage.
     */
    public IntegerProperty rowProperty() {
        return row;
    }

    /**
     * Donne la colonne où se trouve ce personnage.
     *
     * @return La colonne où se trouve ce personnage.
     */
    public int getColumn() {
        return column.get();
    }

    /**
     * Donne la propriété correspondant à la colonne de ce personnage.
     *
     * @return La propriété de la colonne de ce personnage.
     */
    public IntegerProperty columnProperty() {
        return column;
    }

    /**
     * Modifie la position de ce personnage.
     *
     * @param row    La ligne où se trouve maintenant ce personnage.
     * @param column La colonne où se trouve maintenant ce personnage.
     */
    public void setPosition(int row, int column) {
        this.row.set(row);
        this.column.set(column);
    }

    /**
     * Donne les points de vie restants pour ce personnage.
     *
     * @return Les points de vie restants pour ce personnage.
     */
    public int getHealth() {
        return health.get();
    }

    /**
     * Donne la propriété correspondant aux points de vie de ce personnage.
     *
     * @return La propriété des points de vie de ce personnage.
     */
    public IntegerProperty healthProperty() {
        return health;
    }

    /**
     * Diminue les points de vie de ce personnage.
     */
    public void decHealth() {
        health.set(health.get() - 1);
    }

    /**
     * Donne la partie en cours.
     *
     * @return La partie en cours.
     */
    public PacmanGame getGame() {
        return game;
    }

}
