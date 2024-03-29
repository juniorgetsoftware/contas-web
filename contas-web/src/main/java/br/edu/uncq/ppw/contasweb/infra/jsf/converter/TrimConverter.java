package br.edu.uncq.ppw.contasweb.infra.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = String.class)
public class TrimConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		String trimmed = (submittedValue != null) ? submittedValue.trim().replaceAll("\\u0020{1,}", " ") : null;
		return (trimmed == null || trimmed.isEmpty()) ? null : trimmed;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		return (modelValue != null) ? modelValue.toString() : "";
	}
}