package by.bsuir.station.factory;

import by.bsuir.station.entity.Bus;
import by.bsuir.station.exceptions.DataRetrieveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class BusTemplateFactory extends DataFactory {
    @Value("${bus.url}")
    private String URL;
    @Value("${bus.id.url}")
    private String URL_BY_ID;

    @Autowired private HttpEntityFactory entityFactory;

    public List<Bus> get() throws DataRetrieveException {
        try {
            return Arrays.asList(getTemplate().exchange(URL, HttpMethod.GET, entityFactory.getEntity(true, null), Bus[].class).getBody());
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Buses. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Bus post(Bus bus) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.POST, entityFactory.getEntity(true, bus), Bus.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Post Bus. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Bus put(Bus bus) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.PUT, entityFactory.getEntity(true, bus), Bus.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Put Bus. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Bus delete(Integer busId) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL_BY_ID, HttpMethod.DELETE, entityFactory.getEntity(true, null), Bus.class, busId).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Delete Bus. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }
}
