package fr.univartois.butinfo.ihm;

/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import fr.univartois.butinfo.ihm.controller.PacmanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe PacmanApplication est la classe principale de l'application Pacman.
 * Elle reste dans le paquetage principal conformément aux exigences de JavaFX.
 *
 * @author Etudiant BUT Informatique
 * @version 0.1.0
 */
public class PacmanApplication extends Application {

    /**
     * Démarre l'application JavaFX.
     *
     * @param stage La fenêtre principale de l'application.
     * @throws Exception Si une erreur survient lors du chargement du fichier FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Chargement de la vue
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fr/univartois/butinfo/ihm/view/hello-view.fxml"));
        Scene scene = new Scene(loader.load());
        PacmanController controller = loader.getController();
        controller.setScene(scene);

        // Affichage de la fenêtre
        stage.setScene(scene);
        stage.setTitle("Pacman");
        stage.show();
    }

    /**
     * Point d'entrée de l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
