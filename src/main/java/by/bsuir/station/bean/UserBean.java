package by.bsuir.station.bean;

import by.bsuir.station.entity.User;
import by.bsuir.station.entity.User;
import by.bsuir.station.exceptions.DataRetrieveException;
import by.bsuir.station.factory.UserTemplateFactory;
import by.bsuir.station.state.DataState;
import by.bsuir.station.state.ErrorState;
import by.bsuir.station.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "userBean")
@Component
public class UserBean extends AbstractBean {
    @Autowired private UserTemplateFactory userTemplateFactory;
    @Autowired private UserValidator userValidator;
    @Autowired private DataState dataState;

    private List<User> users;
    private User user;

    public UserBean() {
        user = new User();
    }

    @PostConstruct
    public void initialization() {
        dataState.setUser(new User());
    }

    public String login() {
        dataState.setUser(user);
        try {
            String password = dataState.getUser().getPassword();
            dataState.setUser(userTemplateFactory.get(dataState.getUser().getLogin()));
            dataState.getUser().setPassword(password);
        } catch (Throwable e) {
            dataState.setUser(new User());
            setError(ErrorState.READ_ERROR);
            return "index";
        }
        user = new User();

        if(dataState.getUser().getLogin().length() > 0) {
            if(dataState.getUser().getRights().equals("ROLE_ADMIN")) {
                return "admin";
            } else {
                return "index";
            }
        } else {
            return "index";
        }
    }

    public String registration() {
        dataState.setUser(new User());

        return "registration";
    }

    public String logout() {
        user = new User();
        dataState.setUser(new User());

        return "index";
    }

    public String register() {
        user.setRights("ROLE_USER");

        try {
            String password = user.getPassword();
            dataState.setUser(userTemplateFactory.post(user));
            dataState.getUser().setPassword(password);
        } catch (DataRetrieveException e) {
            setError(ErrorState.SAVE_ERROR);
        }

        return "index";
    }

    public String makeEditable(User user) {
        editableIds.add(user.getUserId());
        return null;
    }

    public String post() {
        try {
            if(userValidator.validate(user)) {
                userTemplateFactory.post(user);
            } else {
                setError(ErrorState.SAVE_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.SAVE_ERROR);
        }

        user = new User();

        return "users";
    }

    public String put(User user) {
        try {
            if(userValidator.validate(user)) {
                user = userTemplateFactory.put(user);
                user.setEditable(false);
                editableIds.remove(user.getUserId());
            } else {
                setError(ErrorState.EDIT_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.EDIT_ERROR);
        }
        user = new User();

        return "users";
    }

    public String delete(User user) {
        try {
            user = userTemplateFactory.delete(user.getUserId());
        } catch (DataRetrieveException e) {
            setError(ErrorState.DELETE_ERROR);
        }

        return "users";
    }

    public String clearError() {
        setError(null);

        return "users";
    }

    public List<User> getUsers() {
        try {
            if(editableIds.size() > 0) {
                for (User user : users) {
                    if(editableIds.contains(user.getUserId())) {
                        user.setEditable(true);
                    } else {
                        user.setEditable(false);
                    }
                }
            } else {
                users = userTemplateFactory.get();
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.READ_ERROR);
        }

        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
