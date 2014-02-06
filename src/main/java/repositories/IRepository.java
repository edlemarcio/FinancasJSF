package repositories;

import java.util.List;

/**
 * Interface
 * @author cezar
 *
 * @param <T>
 */
public interface IRepository<T> {
	/**
	 * Salva um objeto
	 * @param t
	 */
	void save(T t);
	
	/**
	 * Edita um objeto
	 * @param t
	 */
	void edit(T t);
	
	/**
	 * Remove um objeto
	 * @param t
	 */
	void remove(T t);
	
	/**
	 * Retorna uma list de objetos
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * Retorna um objeto buscado pelo id
	 * @param id
	 * @return
	 */
	T findById(Long id);
}
