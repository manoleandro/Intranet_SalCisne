package br.com.sp.intranet.model.administrador.vo.rh;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.model.DualListModel;
import org.primefaces.component.picklist.PickList;

@FacesConverter("departamentoConverter")
public class DepartamentoConverter implements Converter {
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) { 
		Object ret = null; 
		if (arg1 instanceof PickList) { 
			Object dualList = ((PickList) arg1).getValue(); 
			DualListModel dl = (DualListModel) dualList; 
			for (Iterator iterator = dl.getSource().iterator(); iterator.hasNext();) { 
				Object o = iterator.next(); 
				
				String id          = parseValue(((Departamento)o).getPk());
				String parsedValue = parseValue(value);
				
				if (parsedValue.equals(id)) { 
					ret = o; 
					break; 
				} 
			} 

			if (ret == null) { 
				for (Iterator iterator1 = dl.getTarget().iterator(); iterator1.hasNext();) { 
					Object o = iterator1.next(); 
					
					String id          = parseValue(((Departamento)o).getPk());
					String parsedValue = parseValue(value);
					
					if (parsedValue.equals(id)) { 
						ret = o; 
						break; 
					} 
				}
			} 
		} 
		return ret; 
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) { 
		String str = ""; 
		if (value instanceof Departamento){
			Departamento departamento = ((Departamento) value);
			str = departamento.getPk().getId().toString() + "|" + departamento.getPk().getFilial().toString() + " - " + departamento.getDescricao();
		}	
		return str; 
	} 
	
	public String parseValue(DepartamentoPK pk){
		String retorno = "";
		retorno = pk.getId().toString();
		retorno += "|"+ pk.getFilial().toString();
		return retorno;
	}
	
	public String parseValue(String value){
		String retorno = "";
		String split[] = value.split("-");
		retorno = split[0].trim();
		return retorno;
	}
}