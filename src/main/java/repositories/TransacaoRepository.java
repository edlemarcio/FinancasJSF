package repositories;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import model.TipoTransacao;
import model.Transacao;

/**
 * Classe que trata os dados de transação
 * @author ccruz
 *
 */
public class TransacaoRepository implements IRepository<Transacao> {
	/**
	 * Eentity manager
	 */
	private EntityManager manager;
	
	/**
	 * Construtor
	 * Inicia o entityManage
	 * @param manager
	 */
	public TransacaoRepository() {
		manager = this.getEntityManager();
	}
			
	@Override
	public void save(Transacao t) {
		if (t.getTipo() == TipoTransacao.Tipo.SAIDA)
			t.setValor(t.getValor().negate());
		
		this.manager.persist(t);	
	}

	@Override
	public void edit(Transacao t) {
		if (t.getTipo() == TipoTransacao.Tipo.SAIDA){
			t.setValor(t.getValor().negate());
		}
		this.manager.merge(t);		
	}

	@Override
	public void remove(Transacao t) {		
		this.manager.remove(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transacao> findAll() {
		Query query = this.manager.createQuery("select e from Transacao e ORDER BY e.data DESC");
		return query.getResultList();
	}

	@Override
	public Transacao findById(Long id) {
		return this.manager.find(Transacao.class, id);
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
	
	/**
	 * Instancia o EM
	 * 
	 * @return
	 */
	private final EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request
				.getAttribute("EntityManager");
		return manager;
	}
	
}
