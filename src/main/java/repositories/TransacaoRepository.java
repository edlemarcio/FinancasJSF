package repositories;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.TipoTransacao;
import model.Transacao;

public class TransacaoRepository {

	private EntityManager manager;
	
	/**
	 * Construtor
	 * Inicia o entityManage
	 * @param manager
	 */
	public TransacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	/**
	 * Adiciona uma nova Ttransação
	 * @param t
	 */
	public void adiciona(Transacao t) {
		if (t.getTipo() == TipoTransacao.Tipo.SAIDA){
			t.setValor(t.getValor().negate());
		}
		this.manager.persist(t);
	}
	
	/**
	 * Remove uma determinada transa��o
	 * @param id
	 */
	public void remove(Long id) {
		Transacao t = this.procura(id);
		this.manager.remove(t);
	}
	
	/**
	 * Edita uma transação
	 * @param t
	 */
	public void edita(Transacao t) {
		if (t.getTipo() == TipoTransacao.Tipo.SAIDA){
			t.setValor(t.getValor().negate());
		}
		this.manager.merge(t);
	}
	
	/**
	 * Busca uma transação baseada no id recebido
	 * @param id
	 * @return
	 */
	public Transacao procura(Long id) {
		return this.manager.find(Transacao.class, id);
	}
	
	/**
	 * Busca todas as transações salvas no banco de dados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Transacao> buscaTodos() {
		Query query = this.manager.createQuery("select e from Transacao e ORDER BY e.data DESC");
		return query.getResultList();
	}
	
	/**
	 * Busca todas as transações salvas no banco de dados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Transacao> buscaTodosToChart() {
		Query query = this.manager.createQuery("select e.data, sum(valor) from Transacao e group by e.data order by e.data desc");
		return query.getResultList();
	}
	
	/**
	 * Rertorna o total em caixa no mmomento.
	 * @return BigDecimal utilizado pra moeda
	 */
	public BigDecimal buscaTotal(){								
		BigDecimal total;
		total = this.manager.createQuery("select sum(valor) from Transacao x", BigDecimal.class).getSingleResult();
		
		if(total == null){
			return BigDecimal.ZERO;
		} else {
			return total;
		}
		
	}
	
}
