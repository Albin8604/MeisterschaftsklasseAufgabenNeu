package ch.albin.meisterschaften.train.controller;

import ch.albin.meisterschaften.train.model.Stop;
import ch.albin.meisterschaften.train.model.TrainType;
import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;


public class AddLineController {
    public Spinner<Integer> numberInput;
    public ComboBox<TrainType> typeInput;
    public ColorPicker colorInput;
    public ComboBox<Stop> stopsComboBox;
    public ListView<Stop> stopListView;

    public void cancel(ActionEvent actionEvent) {

    }

    public void addLine(ActionEvent actionEvent) {

    }

    public void addStop(ActionEvent actionEvent) {

    }

    public void removeStop(ActionEvent actionEvent) {

    }
}
