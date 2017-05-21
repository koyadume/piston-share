package in.koyad.piston.core.dao.context;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import in.koyad.piston.common.basic.StringUtil;
import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.core.dao.utils.DBConstants;
import in.koyad.piston.core.dao.utils.JPAEMFactory;
import in.koyad.piston.dao.model.AbstractEntity;
import lombok.Getter;

/**
*
* 
* @author Shailendra Singh
* @since 2.0
*/
public class ORMContext {
	
	private static final String HINT_FETCH_GRAPH = "javax.persistence.fetchgraph";

	@Getter
	private Object owner;
	private EntityManager em;
	private EntityTransaction tx;
	
	private ORMContext() {}
	
	public static ORMContext getInstance(Object owner) throws FrameworkException {
		return getInstance(owner, DBConstants.PERSISTENT_UNIT_PISTON);
	}
	
	public static ORMContext getInstance(Object owner, String persistentUnit) throws FrameworkException {
		ORMContext ctx = new ORMContext();
		ctx.owner = owner;
		ctx.em = JPAEMFactory.createEntityManager(persistentUnit);
		return ctx;
	}

	//entity manager operations
	private void close(Object owner) {
		if(this.owner == owner) {
			em.close();
		}
	}
	public <T extends AbstractEntity> void persist(T entity) {
		em.persist(entity);
	}
	public <T extends AbstractEntity> T merge(T entity) {
		return em.merge(entity);
	}
	public <T extends AbstractEntity> void remove(T entity) {
		em.remove(entity);
	}
	public <T extends AbstractEntity> void detach(T entity) {
		em.detach(entity);
	}
	public void flush() {
		em.flush();
	}
	public Query createQuery(String query) {
		return em.createQuery(query);
	}
	public Query createNativeQuery(String query) {
		return em.createNativeQuery(query);
	}
	public <T> Query createNativeQuery(String query, Class<T> resultClass) {
		return em.createNativeQuery(query, resultClass);
	}
	public <T extends AbstractEntity> TypedQuery<T> createQuery(String query, Class<T> entityClass) {
		return createQuery(query, entityClass, "");
	}
	public <T extends AbstractEntity> TypedQuery<T> createQuery(String query, Class<T> entityClass, String entityGraphName) {
		TypedQuery<T> typedQuery = em.createQuery(query, entityClass);
		if(!StringUtil.isEmpty(entityGraphName)) {
			typedQuery.setHint(HINT_FETCH_GRAPH, em.getEntityGraph(entityGraphName));
		}
		return typedQuery;
	}
	private Map<String, Object> getHints(String entityGraphName) {
		EntityGraph graph = em.getEntityGraph(entityGraphName);
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put(HINT_FETCH_GRAPH, graph);
		
		return hints;
	}
	public <T extends AbstractEntity> T find(Class<T> entityClass, String id) {
		return em.find(entityClass, id);
	}
	public <T extends AbstractEntity> T find(Class<T> entityClass, String id, String entityGraphName) {
		return em.find(entityClass, id, getHints(entityGraphName));
	}
	
	//transaction operations
	public void beginTransaction() {
		if(null == tx || !tx.isActive()) {
			tx = em.getTransaction();
			tx.begin();
		}
	}
	public void commit(Object owner) {
		if(this.owner == owner) {
			tx.commit();
		}
	}
	private void rollback(Object owner) {
		if(this.owner == owner) {
			if(null != tx && tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
	public void dispose(Object owner) {
		rollback(owner);
		close(owner);
	}
	
}
