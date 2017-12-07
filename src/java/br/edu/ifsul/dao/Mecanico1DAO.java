package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Mecanico1;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class Mecanico1DAO<TIPO> extends DAOGenerico<Mecanico1> implements Serializable {
   
    public Mecanico1DAO(){
      super();
      classePersistente = Mecanico1.class;
      ordem = "nome";
      maximoObjetos = 5;
  }  
}
