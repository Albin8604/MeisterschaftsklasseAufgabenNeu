module MeisterschaftsklasseAufgabenNeu {
    requires javafx.graphics;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;

    opens ch.albin.meisterschaften.train.model;
    opens ch.albin.meisterschaften.train.controller to javafx.fxml;

    exports ch.albin.meisterschaften.train to javafx.graphics;
    exports ch.albin.meisterschaften.train.controller to javafx.fxml;
}