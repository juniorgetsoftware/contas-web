package br.edu.uncq.ppw.contasweb.infra.jsf.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.uncq.ppw.contasweb.model.Categoria;

@FacesConverter(forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent componente, String value) {
		return value != null ? componente.getAttributes().get(value) : null;
	}

	public String getAsString(FacesContext ctx, UIComponent componente, Object value) {
		if (value != null) {
			Categoria categoria = (Categoria) value;
			if (categoria.getId() != null) {
				this.addAttribute(componente, categoria);
				if (categoria.getId() != null) {
					return String.valueOf(categoria.getId());
				}
				return (String) value;
			}
		}

		return "";
	}

	private void addAttribute(UIComponent componente, Categoria o) {
		this.getAttributesFrom(componente).put(o.getId().toString(), o);
	}

	private Map<String, Object> getAttributesFrom(UIComponent componente) {
		return componente.getAttributes();
	}

}