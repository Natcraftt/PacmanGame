package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

/**
 * L'énumération Gum énumère les gommes que le joueur peut collecter dans le jeu Pacman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public enum Gum {

    /**
     * Une gomme simple, qui n'apporte que des points.
     */
    PACGUM,

    /**
     * Une méga-gomme, qui permet au joueur de s'attaquer aux fantômes.
     */
    MEGAGUM;

    /**
     * Donne le nom de cette gomme.
     *
     * @return Le nom de cette gomme.
     */
    public String getName() {
        return name().toLowerCase();
    }

}
