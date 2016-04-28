package by.bsuir.station.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class Destination {
    private Integer destinationId;
    private Double distance;
    private String departure;
    private String arrive;
    private Long price;
    @JsonIgnore
    @JsonDeserialize
    private List<Route> routes;

    @JsonIgnore
    private boolean editable;

    public Destination() {
    }

    public Destination(Double distance, String departure, String arrive, Long price, List<Route> routes) {
        this.distance = distance;
        this.departure = departure;
        this.arrive = arrive;
        this.price = price;
        this.routes = routes;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Destination that = (Destination) o;

        if (destinationId != null ? !destinationId.equals(that.destinationId) : that.destinationId != null)
            return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null) return false;
        if (arrive != null ? !arrive.equals(that.arrive) : that.arrive != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return !(routes != null ? !routes.equals(that.routes) : that.routes != null);

    }

    @Override
    public int hashCode() {
        int result = destinationId != null ? destinationId.hashCode() : 0;
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrive != null ? arrive.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (routes != null ? routes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "destinationId=" + destinationId +
                ", distance=" + distance +
                ", departure='" + departure + '\'' +
                ", arrive='" + arrive + '\'' +
                ", price=" + price +
                ", routes=" + routes +
                '}';
    }
}
