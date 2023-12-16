package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ba/unsa/etf/rpr/fxml/korisnici.fxml"));
        KorisniciModel model = new KorisniciModel();
        model.napuni();
        Controller ctrl = new Controller(model);
        loader.setController(ctrl);
        Parent root = loader.load();

        primaryStage.setTitle("Korisnici");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
