package ch.albin.meisterschaften.train.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "train_line")
public class TrainLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "type", nullable = false)
    private TrainType type;

    @Column(name = "color_code", nullable = false, length = 8)
    private String colorCode;

    @ManyToMany(mappedBy = "trainLines", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Stop> stops = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public TrainLine setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public TrainType getType() {
        return type;
    }

    public void setType(TrainType type) {
        this.type = type;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "TrainLine{" +
                "id=" + id +
                ", number=" + number +
                ", type=" + type +
                ", colorCode='" + colorCode + '\'' +
                ", stops=" + stops +
                '}';
    }
}