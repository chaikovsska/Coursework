package com.example.train_schedule.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String stationArrival;
    private String stationDeparture;
    private String dateArrival;
    private String dateDeparture;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getStationArrival() {
        return stationArrival;
    }
    public String getStationDeparture() {
        return stationDeparture;
    }
    public String getDateArrival() {
        return dateArrival;
    }
    public String getDateDeparture() {
        return dateDeparture;
    }


    public void setName(String newName) {
        this.name = newName;
    }
    public void setStationArrival(String newStationArrival) {
        this.stationArrival = newStationArrival;
    }
    public void setStationDeparture(String newStationDeparture) {
        this.stationDeparture = newStationDeparture;
    }
    public void setDateDeparture(String newDateDeparture) {
        this.dateDeparture = newDateDeparture;
    }

    public void setDateArrival(String newDateArrival) {
        this.dateArrival = newDateArrival;
    }

}
