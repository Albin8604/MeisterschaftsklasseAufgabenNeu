package ch.albin.meisterschaften.train.controller;

import ch.albin.meisterschaften.train.model.TableModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {
    public TableView<TableModel> table;
    public TableColumn<TableModel, String> nameCol;
    public TableColumn<TableModel, String> lineCol;
    public TableColumn<TableModel, String> stopCol;

    public void newLine(ActionEvent actionEvent) {

    }

    public void deleteLine(ActionEvent actionEvent) {

    }

    public void dataImport(ActionEvent actionEvent) {

    }
}
