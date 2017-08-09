package br.com.sp.intranet.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "facesLocalDateConverter")
public class FacesLocalDateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		LocalDate dateValue = (LocalDate) value;

		return dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}