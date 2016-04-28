package by.bsuir.station.factory;

import by.bsuir.station.entity.Purchase;
import by.bsuir.station.exceptions.DataRetrieveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Component
public class PurchaseTemplateFactory extends DataFactory {

    @Value("${purchase.url}")
    private String URL;
    @Value("${purchase.login.url}")
    private String URL_BY_USER;
    @Value("${purchase.id.url}")
    private String URL_BY_ID;

    @Autowired private HttpEntityFactory entityFactory;

    public Purchase post(Purchase purchase) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL, HttpMethod.POST, entityFactory.getEntity(true, purchase), Purchase.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Post Purchase. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public Purchase delete(Integer purchaseId) throws DataRetrieveException {
        try {
            return getTemplate().exchange(URL_BY_ID, HttpMethod.DELETE, entityFactory.getEntity(true, null), Purchase.class, purchaseId).getBody();
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Delete Purchase. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public List<Purchase> getByUser(Integer userId) throws DataRetrieveException {
        try {
            return Arrays.asList(getTemplate().exchange(URL_BY_USER, HttpMethod.GET, entityFactory.getEntity(true, null), Purchase[].class, userId).getBody());
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Purchases. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }

    public List<Purchase> get() throws DataRetrieveException {
        try {
            return Arrays.asList(getTemplate().exchange(URL, HttpMethod.GET, entityFactory.getEntity(true, null), Purchase[].class).getBody());
        } catch (HttpClientErrorException e) {
            throw new DataRetrieveException("Can't Retrieve Purchases. Error Message Is: '" + e.getMessage()+":"+e.getStatusCode()+"'");
        }
    }
}
