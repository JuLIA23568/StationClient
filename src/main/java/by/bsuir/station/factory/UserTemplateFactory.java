package by.bsuir.station.factory;

import by.bsuir.station.entity.User;
import by.bsuir.station.exceptions.DataRetrieveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Component
public class UserTemplateFactory extends DataFactory {

    @Value("${user.login.url}")
    private String URL_BY_LOGIN;
    @Value("${user.url}")
    private String URL;
    @Value("${user.id.url}")
    private String URL_BY_ID;

    @Autowired
    private HttpEntityFactory entityFactory;

    public User get(String login) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL_BY_LOGIN, HttpMethod.GET, entityFactory.getEntity(true, null), User.class, login).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve User. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public User post(User user) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.POST, entityFactory.getEntity(true, user), User.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Post User. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public List<User> get() throws DataRetrieveException {
        try {
            return Arrays.asList(getTemplate().exchange(URL, HttpMethod.GET, entityFactory.getEntity(true, null), User[].class).getBody());
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Users. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public User put(User user) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.PUT, entityFactory.getEntity(true, user), User.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Put User. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public User delete(Integer id) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL_BY_ID, HttpMethod.DELETE, entityFactory.getEntity(true, null), User.class, id).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Delete User. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

}
