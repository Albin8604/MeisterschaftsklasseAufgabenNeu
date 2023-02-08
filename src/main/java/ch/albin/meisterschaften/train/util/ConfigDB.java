package ch.albin.meisterschaften.train.util;

import ch.albin.meisterschaften.train.model.Stop;
import ch.albin.meisterschaften.train.model.TrainLine;
import ch.albin.meisterschaften.train.model.TrainType;

import java.util.List;

public class ConfigDB {
    public static String DB_NAME = "traindb";
    public static String HOST = "localhost";

    public static String USER = "meisterschaftsUser";
    public static String PASSWORD = "meisterschaft217";
    public static List<Class<?>> ANNOTATED_CLASSES = List.of(
            Stop.class,
            TrainLine.class,
            TrainType.class
    );
}
