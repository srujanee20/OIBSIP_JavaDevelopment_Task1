package io.valkycodes.trs.model.po;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Ticket {

    @Id
    private String pnr;

    private Integer trainNumber;
    private String username;
    private Integer numberOfSeats;
    private String travelClass;

    public Ticket() {
        this.pnr = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public String getPnr() {
        return pnr;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public String getUsername() {
        return username;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getTravelClass() {
        return travelClass;
    }
}
