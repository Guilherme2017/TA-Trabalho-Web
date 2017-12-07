package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class ClienteDAO<TIPO> extends DAOGenerico<Cliente> implements Serializable {
    
  public ClienteDAO(){
      super();
      classePersistente = Cliente.class;
      ordem = "nome";
      maximoObjetos = 5;
  }  
    
}
