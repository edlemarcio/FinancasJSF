package controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import repositories.TransacaoRepository;

import model.Transacao;

/**
 * MBean das transações
 * 
 * @author cezar
 * 
 */
@ManagedBean
@RequestScoped
public class TransacaoMBean {
	
	private Transacao t = new Transacao();
	private TransacaoRepository repository = new TransacaoRepository();
	/**
	 * Usa essa lista de transações pra poder usar o sort na tabela selecionada
	 */
	private List<Transacao> transacoesList;

	/**
	 * Instancia de model/transação
	 * 
	 * @return
	 */
	public Transacao getT() {
		return t;
	}

	public void setT(Transacao t) {
		this.t = t;
	}

	/**
	 * Salvar ou editar uma transação
	 */
	public void save() {

		if (t.getId() == 0) {
			if (this.isValid()) {
				repository.adiciona(t);
				t = new Transacao();
			}
		} else {
			if (this.isValid()) {
				repository.edita(t);
				t = new Transacao();
			}
		}
	}

	/**
	 * Cancela a edição ou a inserção de uma nova transação
	 */
	public void cancel() {
		t = new Transacao();
	}

	/**
	 * Carrega no form da páginas os dados pra edição
	 * 
	 * @param id
	 */
	public void preparaEdit(Long id) {
		
		this.t = repository.procura(id);
	}

	/**
	 * Remove uma transação
	 */
	public void removeTransacao(Long id) {
		repository.remove(id);
		this.t = null;
	}

	/**
	 * Retorna um lista com todo as transações
	 * 
	 * @return
	 */
	public List<Transacao> getTransacoes() {

		// verificar se a transações list ja está preenchida, se não tiver busca
		// os dados
		// se tiver retorna ela
		if (transacoesList == null) {
			this.transacoesList = repository.buscaTodos();
			return transacoesList;
		} else {
			return this.transacoesList;
		}
	}

	/**
	 * Retorna o saldo total Somandos as entradas e subtraindo as saidas
	 * 
	 * @return
	 */
	public BigDecimal getTotal() {
		return repository.buscaTotal();
	}

	
	
	/**
	 * Verifica se todos os campos foram preenchidos corretamente.
	 * 
	 * @return
	 */
	private boolean isValid() {
		FacesContext context = FacesContext.getCurrentInstance();

		// Validando o campo valor
		if (this.t.getValor().toString().equals("0")
				|| this.t.getValor().toString().equals("")
				|| this.t.getValor().toString().equals("0.00")
				|| this.t.getValor().toString().equals("0.0")) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Campo inválido",
					"O campo valor deve ser preenchido corretamente"));
			return false;
		} else {
			return true;
		}
	}
}
