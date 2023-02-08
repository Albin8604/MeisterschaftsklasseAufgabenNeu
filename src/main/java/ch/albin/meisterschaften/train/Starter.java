package ch.albin.meisterschaften.train;

import ch.albin.meisterschaften.train.assets.Assets;
import ch.albin.meisterschaften.train.util.FXMLHelper;
import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = FXMLHelper.load(Assets.MainView);
        stage.show();
    }
}
