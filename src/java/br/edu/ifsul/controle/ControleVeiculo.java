package br.edu.ifsul.controle;

import br.edu.ifsul.dao.VeiculoDAO;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.Mecanico1DAO;
import br.edu.ifsul.modelo.Veiculo;
import br.edu.ifsul.modelo.Ordem_Servico;
import br.edu.ifsul.modelo.Mecanico1;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleVeiculo")
@SessionScoped
public class ControleVeiculo implements Serializable {

    @EJB
    private VeiculoDAO<Veiculo> dao;
    private Veiculo objeto;
    private Boolean editando;
    @EJB
    private ClienteDAO<Cliente> daoCliente;
    @EJB
    private Mecanico1DAO<Mecanico1> daoMecanico;
    private Boolean editandoOrdem;
    private Ordem_Servico ordem;
    private Boolean novaOrdem;

    public ControleVeiculo() {
        editando = false;
        editandoOrdem = false;
    }

    public String listar() {
        editando = false;
        editandoOrdem = false;
        return "/privado/veiculo/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        objeto = new Veiculo();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoOrdem = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (getObjeto().getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
            editandoOrdem = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }

    public void novaOrdem() {
        ordem = new Ordem_Servico();
        editandoOrdem= true;
        novaOrdem = true;
    }

    public void salvarOrdem() {
         if(novaOrdem){
            objeto.adicionarOrdem(ordem);
        }
        editandoOrdem = false;
        novaOrdem = false;
        Util.mensagemInformacao("Ordem adicionada com sucesso! ");
    }

    public void alterarOrdem(int index) {
       ordem = objeto.getOrdem().get(index);
       editandoOrdem = true;
       novaOrdem= false;  
    }

    public void excluirOrdem(int index) {
        objeto.removerOrdem(index);
        Util.mensagemInformacao("Ordem removida com sucesso! ");
    }

    public VeiculoDAO<Veiculo> getDao() {
        return dao;
    }

    public void setDao(VeiculoDAO<Veiculo> dao) {
        this.dao = dao;
    }

    public Veiculo getObjeto() {
        return objeto;
    }

    public void setObjeto(Veiculo objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public ClienteDAO<Cliente> getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDAO<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }

    public Mecanico1DAO<Mecanico1> getDaoMecanico() {
        return daoMecanico;
    }

    public void setDaoMecanico(Mecanico1DAO<Mecanico1> daoMecanico) {
        this.daoMecanico = daoMecanico;
    }

    public Boolean getEditandoOrdem() {
        return editandoOrdem;
    }

    public void setEditandoOrdem(Boolean editandoOrdem) {
        this.editandoOrdem = editandoOrdem;
    }

    public Ordem_Servico getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem_Servico ordem) {
        this.ordem = ordem;
    }

    public Boolean getNovaOrdem() {
        return novaOrdem;
    }

    public void setNovaOrdem(Boolean novaOrdem) {
        this.novaOrdem = novaOrdem;
    }
}
