package ch.albin.meisterschaften.train.controller;

import ch.albin.meisterschaften.train.data.DAO;
import ch.albin.meisterschaften.train.data.csv.Import;
import ch.albin.meisterschaften.train.model.TableModel;
import ch.albin.meisterschaften.train.model.TrainLine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class MainController implements Controller {
    private static final ObservableList<TableModel> TABLE_ITEMS = FXCollections.observableArrayList();

    public TableView<TableModel> table;
    public TableColumn<TableModel, String> nameCol;
    public TableColumn<TableModel, String> lineCol;
    public TableColumn<TableModel, Integer> stopCol;

    @Override
    public void init() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lineCol.setCellValueFactory(new PropertyValueFactory<>("line"));
        stopCol.setCellValueFactory(new PropertyValueFactory<>("stop"));

        table.setItems(TABLE_ITEMS);

        updateItemsFromDB();
    }

    private void updateItemsFromDB() {
        TABLE_ITEMS.clear();

        DAO<TrainLine> trainLineDAO = new DAO<>(TrainLine.class);
        List<TrainLine> trainLines = trainLineDAO.selectAll();

        for (TrainLine trainLine : trainLines) {
            TABLE_ITEMS.add(new TableModel(trainLine));
        }
    }

    public void newLine(ActionEvent actionEvent) {

    }

    public void deleteLine(ActionEvent actionEvent) {

    }

    public void dataImport(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));

        File file = fileChooser.showOpenDialog(table.getScene().getWindow());
        Import.read(file);

        updateItemsFromDB();
    }
}
