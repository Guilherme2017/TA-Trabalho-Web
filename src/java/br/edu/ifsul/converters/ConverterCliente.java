package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@FacesConverter(value = "converterCliente")
public class ConverterCliente implements Serializable, Converter {

    @PersistenceContext(unitName = "TA-TrabalhoWebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        return em.find(Cliente.class, Integer.parseInt(string));
    }


    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Cliente obj = (Cliente) o;
        return obj.getId().toString();
    }

}
