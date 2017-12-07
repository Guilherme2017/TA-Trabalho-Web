package br.edu.ifsul.controle;

import br.edu.ifsul.dao.Mecanico1DAO;
import br.edu.ifsul.modelo.Mecanico1;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleMecanico1")
@ViewScoped
public class ControleMecanico1 implements Serializable {
    
    @EJB
    private Mecanico1DAO<Mecanico1> dao;
    private Mecanico1 objeto;
    private Boolean editando;
    
    public ControleMecanico1(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/mecanico/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Mecanico1();
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
    
    public Mecanico1DAO<Mecanico1> getDao() {
        return dao;
    }

    public void setDao(Mecanico1DAO<Mecanico1> dao) {
        this.dao = dao;
    }

    public Mecanico1 getObjeto() {
        return objeto;
    }

    public void setObjeto(Mecanico1 objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
}
