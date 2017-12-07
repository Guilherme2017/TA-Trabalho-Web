package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Mecanico1;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@FacesConverter(value="converterMecanico1")
public class ConverterMecanico1 implements Converter, Serializable {
    
    @PersistenceContext(unitName = "TA-TrabalhoWebPU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Mecanico1.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null){
            return null; 
        }
        Mecanico1 obj = (Mecanico1) o;
        return obj.getId().toString();
    }
    
}
