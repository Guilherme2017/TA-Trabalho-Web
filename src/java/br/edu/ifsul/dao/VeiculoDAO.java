package br.edu.ifsul.dao;
import br.edu.ifsul.modelo.Veiculo;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class VeiculoDAO<TIPO> extends DAOGenerico<Veiculo> implements Serializable {

    public VeiculoDAO(){
        super();
        classePersistente = Veiculo.class;
        ordem = "descricao";
    }
    
    public Veiculo getObjectById(Integer id) throws Exception {
        Veiculo obj = (Veiculo) em.find(classePersistente, id);
       
        obj.getOrdem().size();
        return obj;
    }        
        
}