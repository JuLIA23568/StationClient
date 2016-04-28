package by.bsuir.station.validator;

import by.bsuir.station.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator implements Validator<Route> {
    @Autowired private BusValidator busValidator;
    @Autowired private DestinationValidator destinationValidator;

    public boolean validate(Route route) {
        if(route.getBus() == null || !busValidator.validate(route.getBus())) {
            return false;
        }
        if(route.getDestination() == null || !destinationValidator.validate(route.getDestination())) {
            return false;
        }
        if(route.getDateEnd() == null || route.getDateStart() == null) {
            return false;
        }
        if(route.getDateEnd().before(route.getDateStart())) {
            return false;
        }

        return true;
    }
}
