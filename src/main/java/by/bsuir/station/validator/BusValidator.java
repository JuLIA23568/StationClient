package by.bsuir.station.validator;

import by.bsuir.station.entity.Bus;
import org.springframework.stereotype.Component;

@Component
public class BusValidator implements Validator<Bus> {
    public boolean validate(Bus bus) {
        if(bus.getCapacity() == null || bus.getCapacity() == 0) {
            return false;
        }
        if(bus.getDescription() == null || bus.getDescription().length() == 0) {
            return false;
        }
        if(bus.getGovId() == null || bus.getGovId().length() == 0) {
            return false;
        }
        if(bus.getTitle() == null || bus.getTitle().length() == 0) {
            return false;
        }

        return true;
    }
}
