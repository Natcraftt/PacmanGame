package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

/**
 * L'énumération TileContent énumère les différents éléments qui constituent
 * la carte du jeu Pacman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public enum TileContent {

    /**
     * Le contenu d'une tuile de chemin.
     * Les personnages peuvent se déplacer sur une tuile de ce type.
     */
    PATH,

    /**
     * Le contenu d'une tuile de mur.
     * Les personnages ne peuvent pas se déplacer sur une tuile de ce type.
     */
    WALL,

    /**
     * Le contenu d'une tuile contenant une pacgum.
     */
    PACGUM;

    /**
     * Donne le nom de cet élément de la carte.
     *
     * @return Le nom de cet élément.
     */
    public String getName() {
        return name().toLowerCase();
    }

    /**
     * Vérifie si cet élément est considéré comme vide (traversable).
     *
     * @return Si cet élément est vide.
     */
    public boolean isEmpty() {
        return this == PATH || this == PACGUM;
    }

    /**
     * L'interface IPacmanController définit le contrat entre le modèle et la vue
     * dans le jeu Pacman. Elle permet au modèle de rester indépendant de
     * l'implémentation JavaFX du contrôleur.
     *
     * @author Etudiant BUT Informatique
     * @version 0.1.0
     */
    public static interface IPacmanController {

        /**
         * Initialise l'affichage de la carte du jeu.
         *
         * @param map La carte du jeu à afficher.
         */
        void initMap(GameMap map);

        /**
         * Crée et lie l'affichage d'un personnage à sa position sur la carte.
         *
         * @param character Le personnage à afficher.
         */
        void bindCharacter(AbstractCharacter character);

        /**
         * Lie les statistiques du joueur (score, vies) aux labels de l'interface.
         *
         * @param player Le joueur dont on lie les stats.
         */
        void bindPlayerStats(Player player);

        /**
         * Met à jour l'affichage des vies du joueur.
         */
        void decLives();

    }
}
