package com.aurorahernandez.web.palms.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Aurora Hernandez
 */
@FacesConverter("localDateGetParamConverter")
public class LocalDateParameterConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isBlank()) {
            return LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof LocalDate) {
            return DateTimeFormatter.ISO_DATE.format((LocalDate)value);
        } else {
            return null;
        }
    }
    
    
}
