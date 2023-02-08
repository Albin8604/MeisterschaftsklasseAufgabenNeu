package ch.albin.meisterschaften.train.util;

import ch.albin.meisterschaften.train.assets.Assets;
import ch.albin.meisterschaften.train.controller.Controller;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class FXMLHelper {
    public static <T> T load(Assets asset) {
        FXMLLoader fxmlLoader = new FXMLLoader(asset.asUrl());
        T result;

        try {
            result = fxmlLoader.load();
            ((Controller)fxmlLoader.getController()).init();
        } catch (IOException | UnsupportedOperationException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}