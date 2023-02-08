package ch.albin.meisterschaften.train.assets;

import java.net.URL;

public enum Assets {

    AddLineView("addLineView.fxml"),
    MainView("mainView.fxml"),
    Srtclogo("srtclogo.png")
;
    final String filename;

    Assets(String filename){
        this.filename = filename;
    }

    public URL asUrl(){
        return Assets.class.getClassLoader().getResource(filename);
    }
}