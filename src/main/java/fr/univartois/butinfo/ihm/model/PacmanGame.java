package fr.univartois.butinfo.ihm.model;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe PacmanGame gère le déroulement d'une partie du jeu Pacman.
 * <p>
 * Cette classe joue le rôle de <b>façade</b> du modèle : elle est le point
 * d'entrée unique pour toutes les interactions entre la vue et le modèle.
 * Toutes les communications avec le contrôleur passent exclusivement par cette classe.
 * </p>
 *
 * @author Etudiant BUT Informatique
 * @version 0.1.0
 */
public class PacmanGame {

    /**
     * Le nombre de lignes de la carte.
     */
    private static final int MAP_HEIGHT = 15;

    /**
     * Le nombre de colonnes de la carte.
     */
    private static final int MAP_WIDTH = 19;

    /**
     * Le nombre de fantômes dans la partie.
     */
    private static final int NB_GHOSTS = 4;

    /**
     * Le générateur de nombres aléatoires.
     */
    private final Random random = new Random();

    /**
     * La carte du jeu.
     */
    private GameMap map;

    /**
     * Le personnage du joueur.
     */
    private Player player;

    /**
     * La liste des fantômes.
     */
    private List<Ghost> ghosts = new ArrayList<>();

    /**
     * Le contrôleur de l'application (référencé via l'interface pour respecter le MVC).
     */
    private TileContent.IPacmanController controller;

    /**
     * Indique si la partie est en cours.
     */
    private boolean gameRunning = false;

    /**
     * Modifie le contrôleur associé à cette partie.
     *
     * @param controller Le contrôleur de l'application.
     */
    public void setController(TileContent.IPacmanController controller) {
        this.controller = controller;
    }

    /**
     * Donne le joueur de la partie.
     *
     * @return Le joueur.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Démarre une nouvelle partie.
     * Crée la carte, place le joueur et les fantômes.
     */
    public void startGame() {
        // Arrête les anciens fantômes
        for (Ghost ghost : ghosts) {
            ghost.stopAnimation();
        }
        ghosts.clear();

        // Crée la carte et place les pacgums
        map = GameMapFactory.createMapWithRandomWalls(MAP_HEIGHT, MAP_WIDTH);
        placePacgums();
        controller.initMap(map);

        // Crée et place le joueur
        player = new Player(this);
        placeCharacter(player);
        controller.bindCharacter(player);
        controller.bindPlayerStats(player);

        // Crée et place les fantômes
        String[] colors = {"red", "pink", "blue", "orange"};
        for (int i = 0; i < NB_GHOSTS; i++) {
            Ghost ghost = new Ghost(colors[i % colors.length], this);
            ghosts.add(ghost);
            placeCharacter(ghost);
            controller.bindCharacter(ghost);
            ghost.animate();
        }

        gameRunning = true;
    }

    /**
     * Place un personnage sur une tuile vide choisie aléatoirement.
     *
     * @param character Le personnage à placer sur la carte.
     */
    public void placeCharacter(AbstractCharacter character) {
        List<Tile> emptyTiles = map.getEmptyTiles();
        if (character == player && !ghosts.isEmpty()) {
            emptyTiles = emptyTiles.stream()
                    .filter(tile -> ghosts.stream().noneMatch(
                            g -> g.getRow() == tile.getRow() && g.getColumn() == tile.getColumn()))
                    .collect(java.util.stream.Collectors.toList());
        }

        Tile tile = emptyTiles.get(random.nextInt(emptyTiles.size()));
        character.setPosition(tile.getRow(), tile.getColumn());
    }

    /**
     * Déplace un personnage vers le haut.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveUp(AbstractCharacter character) {
        int newRow = character.getRow() - 1;
        int col = character.getColumn();
        if (map.isOnMap(newRow, col) && map.get(newRow, col).isEmpty()) {
            character.setPosition(newRow, col);
            afterMove(character);
        }
    }

    /**
     * Déplace un personnage vers le bas.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveDown(AbstractCharacter character) {
        int newRow = character.getRow() + 1;
        int col = character.getColumn();
        if (map.isOnMap(newRow, col) && map.get(newRow, col).isEmpty()) {
            character.setPosition(newRow, col);
            afterMove(character);
        }
    }

    /**
     * Déplace un personnage vers la gauche.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveLeft(AbstractCharacter character) {
        int row = character.getRow();
        int newCol = character.getColumn() - 1;
        if (map.isOnMap(row, newCol) && map.get(row, newCol).isEmpty()) {
            character.setPosition(row, newCol);
            afterMove(character);
        }
    }

    /**
     * Déplace un personnage vers la droite.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveRight(AbstractCharacter character) {
        int row = character.getRow();
        int newCol = character.getColumn() + 1;
        if (map.isOnMap(row, newCol) && map.get(row, newCol).isEmpty()) {
            character.setPosition(row, newCol);
            afterMove(character);
        }
    }

    /**
     * Déplace le joueur vers le haut (appelé par le contrôleur).
     */
    public void movePlayerUp()    { if (gameRunning) moveUp(player); }

    /**
     * Déplace le joueur vers le bas (appelé par le contrôleur).
     */
    public void movePlayerDown()  { if (gameRunning) moveDown(player); }

    /**
     * Déplace le joueur vers la gauche (appelé par le contrôleur).
     */
    public void movePlayerLeft()  { if (gameRunning) moveLeft(player); }

    /**
     * Déplace le joueur vers la droite (appelé par le contrôleur).
     */
    public void movePlayerRight() { if (gameRunning) moveRight(player); }

    /**
     * Place les pacgums sur toutes les cases de chemin de la carte.
     */
    private void placePacgums() {
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                if (map.get(i, j).getContent() == TileContent.PATH) {
                    map.get(i, j).setContent(TileContent.PACGUM);
                }
            }
        }
    }

    /**
     * Vérifie si le joueur se trouve sur une pacgum et la collecte si c'est le cas.
     */
    private void checkPacgum() {
        Tile tile = map.get(player.getRow(), player.getColumn());
        if (tile.getContent() == TileContent.PACGUM) {
            tile.setContent(TileContent.PATH);
            player.addPoint();
        }
    }

    /**
     * Vérifie si le joueur entre en collision avec un fantôme.
     */
    private void checkGhostCollisions() {
        for (Ghost ghost : ghosts) {
            if (ghost.getRow() == player.getRow() && ghost.getColumn() == player.getColumn()) {
                player.decHealth();
                if (player.getHealth() <= 0) {
                    endGame();
                } else {
                    placeCharacter(player);
                }
                return;
            }
        }
    }

    /**
     * Termine la partie.
     */
    private void endGame() {
        gameRunning = false;
        for (Ghost ghost : ghosts) {
            ghost.stopAnimation();
        }
    }

    /**
     * Effectue les vérifications après chaque déplacement.
     *
     * @param character Le personnage qui vient de se déplacer.
     */
    private void afterMove(AbstractCharacter character) {
        if (!gameRunning) return;
        if (character == player) {
            checkPacgum();
        }
        checkGhostCollisions();
    }

}
