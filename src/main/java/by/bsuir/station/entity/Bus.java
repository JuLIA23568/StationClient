package by.bsuir.station.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class Bus {
    private Integer busId;
    private String govId;
    private Integer capacity;
    private String description;
    private String title;
    @JsonIgnore
    @JsonDeserialize
    private Integer routesAmount;
    @JsonIgnore
    @JsonDeserialize
    private List<Route> routes;

    @JsonIgnore
    private boolean editable;

    public Bus() {
    }

    public Bus(String govId, Integer capacity, String description, String title, List<Route> routes) {
        this.govId = govId;
        this.capacity = capacity;
        this.description = description;
        this.title = title;
        this.routes = routes;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getGovId() {
        return govId;
    }

    public void setGovId(String govId) {
        this.govId = govId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Integer getRoutesAmount() {
        return routesAmount;
    }

    public void setRoutesAmount(Integer routesAmount) {
        this.routesAmount = routesAmount;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;

        Bus bus = (Bus) o;

        if (busId != null ? !busId.equals(bus.busId) : bus.busId != null) return false;
        if (capacity != null ? !capacity.equals(bus.capacity) : bus.capacity != null) return false;
        if (description != null ? !description.equals(bus.description) : bus.description != null) return false;
        if (govId != null ? !govId.equals(bus.govId) : bus.govId != null) return false;
        if (title != null ? !title.equals(bus.title) : bus.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = busId != null ? busId.hashCode() : 0;
        result = 31 * result + (govId != null ? govId.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", govId='" + govId + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", routesAmount=" + routesAmount +
                ", routes=" + routes +
                '}';
    }
}
