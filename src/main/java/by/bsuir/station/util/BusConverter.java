package by.bsuir.station.util;

import by.bsuir.station.entity.Bus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.io.IOException;

@FacesConverter(value = "by.bsuir.station.bus", forClass = Bus.class)
public class BusConverter implements Converter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            return objectMapper.readValue(s, Bus.class);
        } catch (IOException e) {
            throw new ConverterException("Error converting");
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new ConverterException("Error converting");
        }
    }

}
