package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleCliente")
@ViewScoped
public class ControleCliente implements Serializable {
    
    @EJB
    private ClienteDAO<Cliente> dao;
    private Cliente objeto;
    private Boolean editando;
    
    public ControleCliente(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/cliente/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Cliente();
    }
    
    public void alterar(Integer id){
        try{
            objeto = dao.getObjectById(id);
            editando = true;
        } catch(Exception e){
           Util.mensagemErro("Erro ao recuperar objeto" + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Integer id){
        try{
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch(Exception e){
            Util.mensagemErro("Erro ao remover objeto" + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            } else{
                dao.merge(objeto);
            }
            editando = false;
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto");
        }
    }
    
    public ClienteDAO<Cliente> getDao() {
        return dao;
    }

    public void setDao(ClienteDAO<Cliente> dao) {
        this.dao = dao;
    }

    public Cliente getObjeto() {
        return objeto;
    }

    public void setObjeto(Cliente objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
}
