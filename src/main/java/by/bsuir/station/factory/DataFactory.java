package by.bsuir.station.factory;

import org.springframework.web.client.RestTemplate;

public abstract class DataFactory {
    protected RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
