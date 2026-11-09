package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import java.util.Random;

/**
 * La classe GameMapFactory est une classe utilitaire qui permet de créer
 * différentes cartes pour le jeu Pacman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public class GameMapFactory {

    /**
     * Le générateur de nombres pseudo-aléatoires.
     */
    private static final Random RANDOM = new Random();

    /**
     * Empêche la création d'instances de cette classe.
     */
    private GameMapFactory() {
        throw new AssertionError("No GameMapFactory instances for you!");
    }

    /**
     * Crée une carte vide, sur laquelle les murs se trouvent uniquement sur les côtés.
     *
     * @param height Le nombre de lignes dans la carte.
     * @param width  Le nombre de colonnes dans la carte.
     *
     * @return La carte qui a été créée.
     */
    public static GameMap createEmptyMap(int height, int width) {
        GameMap map = new GameMap(height, width);

        for (int i = 0; i < width; i++) {
            map.get(0, i).setContent(TileContent.WALL);
            map.get(height - 1, i).setContent(TileContent.WALL);
        }

        for (int i = 0; i < height; i++) {
            map.get(i, 0).setContent(TileContent.WALL);
            map.get(i, width - 1).setContent(TileContent.WALL);
        }

        for (int i = 1; i < (height - 1); i++) {
            for (int j = 1; j < (width - 1); j++) {
                map.get(i, j).setContent(TileContent.PATH);
            }
        }

        return map;
    }

    /**
     * Crée une carte avec des murs positionnés à intervalle régulier.
     *
     * @param height Le nombre de lignes dans la carte.
     * @param width  Le nombre de colonnes dans la carte.
     *
     * @return La carte qui a été créée.
     */
    public static GameMap createMapWithRegularIntermediateWall(int height, int width) {
        GameMap map = createEmptyMap(height, width);

        for (int i = 2; i < (height - 1); i += 2) {
            for (int j = 2; j < (width - 2); j++) {
                map.get(i, j).setContent(TileContent.WALL);
            }
        }

        return map;
    }

    /**
     * Crée une carte avec des murs réguliers et des murs aléatoires.
     *
     * @param height Le nombre de lignes dans la carte.
     * @param width  Le nombre de colonnes dans la carte.
     *
     * @return La carte qui a été créée.
     */
    public static GameMap createMapWithRandomWalls(int height, int width) {
        GameMap map = createMapWithRegularIntermediateWall(height, width);

        for (int i = 3; i < height - 2; i += 2) {
            map.get(i, RANDOM.nextInt(1, width - 1)).setContent(TileContent.WALL);
        }

        return map;
    }

}
