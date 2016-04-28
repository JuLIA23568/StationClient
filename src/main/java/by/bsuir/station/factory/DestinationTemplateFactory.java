package by.bsuir.station.factory;

import by.bsuir.station.entity.Destination;
import by.bsuir.station.exceptions.DataRetrieveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Component
public class DestinationTemplateFactory extends DataFactory {
    @Value("${destination.url}")
    private String URL;
    @Value("${destination.id.url}")
    private String URL_BY_ID;

    @Autowired private HttpEntityFactory entityFactory;

    public List<Destination> get() throws DataRetrieveException {
        try {
            return Arrays.asList(getTemplate().exchange(URL, HttpMethod.GET, entityFactory.getEntity(true, null), Destination[].class).getBody());
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Destinations. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Destination post(Destination destination) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.POST, entityFactory.getEntity(true, destination), Destination.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Post Destination. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Destination put(Destination destination) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.PUT, entityFactory.getEntity(true, destination), Destination.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Put Destination. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Destination delete(Integer destinationId) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL_BY_ID, HttpMethod.DELETE, entityFactory.getEntity(true, null), Destination.class, destinationId).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Delete Destination. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }
}
