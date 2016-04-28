package by.bsuir.station.validator;

import by.bsuir.station.entity.Bus;
import by.bsuir.station.entity.Destination;
import org.springframework.stereotype.Component;

@Component
public class DestinationValidator implements Validator<Destination> {
    public boolean validate(Destination destination) {
        if(destination.getDistance() == null || destination.getDistance() == 0.0) {
            return false;
        }
        if(destination.getPrice() == null || destination.getPrice() == 0L) {
            return false;
        }
        if(destination.getArrive() == null || destination.getArrive().length() == 0) {
            return false;
        }
        if(destination.getDeparture() == null || destination.getDeparture().length() == 0) {
            return false;
        }

        return true;
    }
}
