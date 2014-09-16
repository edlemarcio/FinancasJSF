package br.org.financas.models;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import br.org.financas.entities.Usuario;

public class UsuarioModel implements IModel<Usuario> {
	
	private EntityManager manager;
	
	public UsuarioModel(){
		manager = this.getEntityManager();
	}
	
	@Override
	public void save(Usuario t) {
		// TODO Auto-generated method stub
		this.manager.persist(t);
	}

	@Override
	public void edit(Usuario t) {
		// TODO Auto-generated method stub
		this.manager.merge(t);
	}

	@Override
	public void remove(Usuario t) {
		// TODO Auto-generated method stub
		this.manager.remove(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		Query query = this.manager.createQuery("select e from users e");
		return query.getResultList();
	}

	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return this.manager.find(Usuario.class, id);
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
