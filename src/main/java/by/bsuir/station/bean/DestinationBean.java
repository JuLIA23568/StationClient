package by.bsuir.station.bean;

import by.bsuir.station.entity.Destination;
import by.bsuir.station.exceptions.DataRetrieveException;
import by.bsuir.station.factory.DestinationTemplateFactory;
import by.bsuir.station.state.ErrorState;
import by.bsuir.station.validator.DestinationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "destinationBean")
@Component
public class DestinationBean extends AbstractBean {
    @Autowired private DestinationTemplateFactory destinationTemplateFactory;
    @Autowired private DestinationValidator destinationValidator;

    private List<Destination> destinations;
    private Destination destination;

    public DestinationBean() {
        destination = new Destination();
    }

    public String makeEditable(Destination destination) {
        editableIds.add(destination.getDestinationId());
        return null;
    }

    public String post() {
        try {
            if(destinationValidator.validate(destination)) {
                destinationTemplateFactory.post(destination);
            } else {
                setError(ErrorState.SAVE_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.SAVE_ERROR);
        }

        destination = new Destination();

        return "destinations";
    }

    public String put(Destination destination) {
        try {
            if(destinationValidator.validate(destination)) {
                destination = destinationTemplateFactory.put(destination);
                destination.setEditable(false);
                editableIds.remove(destination.getDestinationId());
            } else {
                setError(ErrorState.EDIT_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.EDIT_ERROR);
        }

        return "destinations";
    }

    public String delete(Destination destination) {
        try {
            destinationTemplateFactory.delete(destination.getDestinationId());
        } catch (DataRetrieveException e) {
            setError(ErrorState.DELETE_ERROR);
        }

        return "destinations";
    }

    public String clearError() {
        setError(null);

        return "destinations";
    }

    public List<Destination> getDestinations() {
        try {
            if(editableIds.size() > 0) {
                for (Destination destination : destinations) {
                    if(editableIds.contains(destination.getDestinationId())) {
                        destination.setEditable(true);
                    } else {
                        destination.setEditable(false);
                    }
                }
            } else {
                destinations = destinationTemplateFactory.get();
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.READ_ERROR);
        }

        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
