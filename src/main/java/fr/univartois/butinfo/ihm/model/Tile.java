package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * La classe Tile représente une tuile composant la carte du jeu Pacman.
 * Une fois créée, une telle tuile devient fixe sur la carte : c'est son
 * contenu qui change au cours du jeu, si cela s'avère nécessaire.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public class Tile {

    /**
     * La ligne où cette tuile est positionnée sur la carte.
     */
    private final int row;

    /**
     * La colonne où cette tuile est positionnée sur la carte.
     */
    private final int column;

    /**
     * Le contenu de cette tuile, sous forme de propriété.
     */
    private ObjectProperty<TileContent> content = new SimpleObjectProperty<>();

    /**
     * Construit une nouvelle instance de Tile.
     *
     * @param row    La ligne où la tuile est positionnée sur la carte.
     * @param column La colonne où la tuile est positionnée sur la carte.
     */
    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Donne la ligne où cette tuile est positionnée sur la carte.
     *
     * @return La ligne où cette tuile est positionnée sur la carte.
     */
    public int getRow() {
        return row;
    }

    /**
     * Donne la colonne où cette tuile est positionnée sur la carte.
     *
     * @return La colonne où cette tuile est positionnée sur la carte.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Donne le contenu de cette tuile.
     *
     * @return Le contenu de cette tuile.
     */
    public TileContent getContent() {
        return content.get();
    }

    /**
     * Change le contenu de cette tuile.
     *
     * @param content Le nouveau contenu de cette tuile.
     */
    public void setContent(TileContent content) {
        this.content.set(content);
    }

    /**
     * Donne la propriété correspondant au contenu de cette tuile.
     *
     * @return La propriété du contenu de cette tuile.
     */
    public ObjectProperty<TileContent> getContentProperty() {
        return content;
    }

    /**
     * Vérifie si cette tuile est vide.
     *
     * @return Si cette tuile est vide.
     */
    public boolean isEmpty() {
        return content.get().isEmpty();
    }

}
