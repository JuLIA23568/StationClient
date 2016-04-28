package by.bsuir.station.factory;

import by.bsuir.station.entity.Route;
import by.bsuir.station.entity.Route;
import by.bsuir.station.exceptions.DataRetrieveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Component
public class RouteTemplateFactory extends DataFactory {

    @Value("${route.search.url}")
    private String URL_SEARCH;
    @Value("${route.url}")
    private String URL;
    @Value("${route.id.url}")
    private String URL_BY_ID;

    @Autowired
    private HttpEntityFactory entityFactory;

    public List<Route> get() throws DataRetrieveException {
        try {
            return Arrays.asList(getTemplate().exchange(URL, HttpMethod.GET, entityFactory.getEntity(false, null), Route[].class).getBody());
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Routes. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Route get(Integer routeId) throws DataRetrieveException{
        try {
            return getTemplate().exchange(URL_BY_ID, HttpMethod.GET, entityFactory.getEntity(false, null), Route.class, routeId).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Route. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public List<Route> get(String searchQuery) throws DataRetrieveException {
        try {
            return Arrays.asList(getTemplate().exchange(URL_SEARCH, HttpMethod.GET, entityFactory.getEntity(false, null), Route[].class, searchQuery).getBody());
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Routes. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Route post(Route route) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.POST, entityFactory.getEntity(true, route), Route.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Post Route. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Route put(Route route) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.PUT, entityFactory.getEntity(true, route), Route.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Put Route. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Route delete(Integer routeId) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL_BY_ID, HttpMethod.DELETE, entityFactory.getEntity(true, null), Route.class, routeId).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Delete Route. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }
}
