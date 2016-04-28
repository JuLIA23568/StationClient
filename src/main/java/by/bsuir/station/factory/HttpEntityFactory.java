package by.bsuir.station.factory;

import by.bsuir.station.entity.User;
import by.bsuir.station.state.DataState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.util.Arrays;

@Component
@Scope(value = "prototype")
public class HttpEntityFactory {

    @Autowired private DataState dataState;

    public<T> HttpEntity<T> getEntity(boolean authorize, T body) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        if(authorize) {
            User user = dataState.getUser();
            if(user != null && user.getLogin() != null && user.getLogin().length() > 0 && user.getPassword().length() > 0 && user.getUserId() != null) {
                httpHeaders.set("Authorization", "Basic " + new BASE64Encoder().encode((user.getLogin() + ":" + user.getPassword()).getBytes()));
            } else {
                httpHeaders.set("Authorization", "Basic " + new BASE64Encoder().encode(("admin:123456").getBytes()));
            }
        }

        return new HttpEntity<T>(body, httpHeaders);
    }

}
