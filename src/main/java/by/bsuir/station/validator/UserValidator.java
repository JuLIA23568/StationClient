package by.bsuir.station.validator;

import by.bsuir.station.entity.Destination;
import by.bsuir.station.entity.User;
import by.bsuir.station.exceptions.DataRetrieveException;
import by.bsuir.station.factory.UserTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User> {
    @Autowired private UserTemplateFactory userTemplateFactory;

    public boolean validate(User user) {
        if(user.getLogin() == null || user.getLogin().length() == 0) {
            return false;
        }
        if(user.getPassword() == null || user.getPassword().length() == 0) {
            return false;
        }

        return true;
    }
}
