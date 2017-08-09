package br.com.sp.intranet.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sp.intranet.model.GenericEntity;

@FacesConverter("genericConverter")
public class GenericConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			GenericEntity entity = (GenericEntity) component.getAttributes().get(value);
			return entity;
		} else {
			return "";
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof GenericEntity) {
			GenericEntity entity = (GenericEntity) value;
			if (entity != null && entity.getPrimaryKey() != null) {
				component.getAttributes().put(entity.getPrimaryKey().toString(), value);
				return entity.getPrimaryKey().toString();
			}
		}
		return "";
	}
}