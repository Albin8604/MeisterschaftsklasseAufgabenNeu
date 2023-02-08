package ch.albin.meisterschaften.train.data.csv;

import ch.albin.meisterschaften.train.data.DAO;
import ch.albin.meisterschaften.train.model.Stop;
import ch.albin.meisterschaften.train.model.TrainLine;
import ch.albin.meisterschaften.train.model.TrainType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Import {
    static DAO<TrainLine> trainLineDAO;
    static DAO<TrainType> trainTypeDAO;

    private static final List<TrainType> TRAIN_TYPES = new ArrayList<>();
    private static final List<TrainLine> TRAIN_LINES = new ArrayList<>();
    private static final String DELIMITER = ",";
    public static void read(File file){
        trainLineDAO = new DAO<>(TrainLine.class);
        trainTypeDAO = new DAO<>(TrainType.class);

        TRAIN_TYPES.addAll(trainTypeDAO.selectAll());

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] data = line.split(DELIMITER);

                TrainLine trainLine = getLineByNumber(Integer.parseInt(data[0]));
                Stop stop = new Stop();

                trainLine.setType(getTypeByName(data[1]));
                trainLine.setColorCode(data[2]);

                stop.setName(data[3]);
                stop.setLongitude(Double.parseDouble(data[5]));
                stop.setLatitude(Double.parseDouble(data[6]));
                stop.setShortCode(data[7]);

                trainLine.getStops().add(stop);

                trainLineDAO.update(trainLine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static TrainType getTypeByName(String name){
        for (TrainType trainType : TRAIN_TYPES) {
            if (trainType.getName().equalsIgnoreCase(name)){
                return trainType;
            }
        }
        return null;
    }
    private static TrainLine getLineByNumber(Integer number){
        for (TrainLine trainLine : TRAIN_LINES) {
            if (trainLine.getNumber() == number){
                return trainLine;
            }
        }
        return new TrainLine().setNumber(number);
    }
}
