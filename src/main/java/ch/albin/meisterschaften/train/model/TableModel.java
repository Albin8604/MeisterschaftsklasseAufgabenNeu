package ch.albin.meisterschaften.train.model;

public class TableModel {
    private final TrainLine trainLine;

    public TableModel(TrainLine trainLine) {
        this.trainLine = trainLine;
    }

    public String getName() {
        return trainLine.getType().getName() + trainLine.getNumber();
    }

    public String getLine() {
        return trainLine.getStops().get(0).getName() + "-" + trainLine.getStops().get(trainLine.getStops().size() - 1).getName();
    }

    public Integer getStop() {
        return trainLine.getStops().size();
    }
}
