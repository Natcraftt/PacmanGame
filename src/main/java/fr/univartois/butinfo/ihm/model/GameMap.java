package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * La classe GameMap représente la carte du jeu Pacman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public class GameMap {

    /**
     * Le nombre de lignes de tuiles dans cette carte.
     */
    private int height;

    /**
     * Le nombre de colonnes de tuiles dans cette carte.
     */
    private int width;

    /**
     * Les tuiles qui constituent cette carte.
     */
    private Tile[][] tiles;

    /**
     * Crée une nouvelle instance de GameMap.
     *
     * @param height Le nombre de lignes de tuiles dans la carte.
     * @param width  Le nombre de colonnes de tuiles dans la carte.
     */
    public GameMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.tiles = new Tile[height][width];
        init();
    }

    /**
     * Crée les tuiles qui constituent cette carte.
     */
    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Donne le nombre de lignes de tuiles dans cette carte.
     *
     * @return Le nombre de lignes de tuiles dans cette carte.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Donne le nombre de colonnes de tuiles dans cette carte.
     *
     * @return Le nombre de colonnes de tuiles dans cette carte.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Vérifie si une position se trouve sur cette carte.
     *
     * @param row    L'indice de ligne à vérifier.
     * @param column L'indice de colonne à vérifier.
     *
     * @return Si la position se trouve bien sur la carte.
     */
    public boolean isOnMap(int row, int column) {
        return ((0 <= row) && (row < height))
                && ((0 <= column) && (column < width));
    }

    /**
     * Donne une tuile à une position donnée.
     *
     * @param row    La ligne de la tuile à récupérer.
     * @param column La colonne de la tuile à récupérer.
     *
     * @return La tuile à la position donnée.
     */
    public Tile get(int row, int column) {
        return tiles[row][column];
    }

    /**
     * Donne la liste des tuiles qui sont vides sur cette carte.
     *
     * @return La liste des tuiles vides.
     */
    public List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].isEmpty()) {
                    emptyTiles.add(tiles[i][j]);
                }
            }
        }

        return emptyTiles;
    }

}
