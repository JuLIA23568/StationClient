package by.bsuir.station.bean;

import by.bsuir.station.entity.Bus;
import by.bsuir.station.exceptions.DataRetrieveException;
import by.bsuir.station.factory.BusTemplateFactory;
import by.bsuir.station.state.ErrorState;
import by.bsuir.station.validator.BusValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "busBean")
@Component
public class BusBean extends AbstractBean {
    @Autowired private BusTemplateFactory busTemplateFactory;
    @Autowired private BusValidator busValidator;

    private List<Bus> buses;
    private Bus bus;

    public BusBean() {
        bus = new Bus();
    }

    public String makeEditable(Bus bus) {
        editableIds.add(bus.getBusId());
        return null;
    }

    public String post() {
        try {
            if(busValidator.validate(bus)) {
                busTemplateFactory.post(bus);
            } else {
                setError(ErrorState.SAVE_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.SAVE_ERROR);
        }

        bus = new Bus();

        return "buses";
    }

    public String put(Bus bus) {
        try {
            if(busValidator.validate(bus)) {
                bus = busTemplateFactory.put(bus);
                bus.setEditable(false);
                editableIds.remove(bus.getBusId());
            } else {
                setError(ErrorState.EDIT_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.EDIT_ERROR);
        }

        return "buses";
    }

    public String delete(Bus bus) {
        try {
            bus = busTemplateFactory.delete(bus.getBusId());
            buses.remove(bus);
        } catch (DataRetrieveException e) {
            setError(ErrorState.DELETE_ERROR);
        }

        return "buses";
    }

    public String clearError() {
        setError(null);

        return "buses";
    }

    public List<Bus> getBuses() {
        try {
            if(editableIds.size() > 0) {
                for (Bus bus : buses) {
                    if(editableIds.contains(bus.getBusId())) {
                        bus.setEditable(true);
                    } else {
                        bus.setEditable(false);
                    }
                }
            } else {
                buses = busTemplateFactory.get();
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.READ_ERROR);
        }

        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

}
