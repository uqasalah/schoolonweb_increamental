package alt.sow.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alt.sow.domain.Question;

/**
 * 
 * @author leonidas
 */
public class GenericDAO<T, ID extends Serializable> {

	protected void closeSession(Session s) {
		if (s != null) {
			try {
				s.flush();
				s.close();
			} catch (Exception e) {
				System.out.println("on session closed ");
				e.printStackTrace();
			}
		}
	}

	protected void commitTransaction(Transaction t) {
		try {
			if (t != null || t.isActive() || !t.wasCommitted()) {
				t.commit();
			}
		} catch (Exception e) {
			System.out.println("Transaction error");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param entity
	 *            object to be deleted
	 * @return same object if successful ,null if failed
	 */
	public T delete(T entity) {
		Session s = getSession();
		Transaction t = null;
		if (s != null) {
			try {
				t = s.beginTransaction();
				s.delete(entity);
			} catch (Exception e) {
				e.printStackTrace();
				entity = null;
				if (t != null) {
					t.rollback();
				}
			} finally {
				commitTransaction(t);
				closeSession(s);
			}
		} else {
			entity = null;
		}
		return entity;
	}

	/**
	 * 
	 * @param clazz
	 *            Class of Object
	 * @return List of object if successful ,null if failed
	 */
	public List<?> findAll(Class clazz) {
		Session s = getSession();
		List<?> T = null;
		if (s != null) {
			Transaction t = s.beginTransaction();
			try {
				Query query = s.createQuery("from " + clazz.getName());
				T = query.list();
			} catch (Exception e) {
				e.printStackTrace();
				if (t != null) {
					t.rollback();
				}
			} finally {
				commitTransaction(t);
				closeSession(s);
			}
		}
		return T;
	}

	/**
	 * 
	 * @param clazz
	 *            Class of target object
	 * @param id
	 *            id of target object
	 * @return object if successful ,null if failed
	 */
	public T findByID(Class clazz, Integer id) {
		Session s = getSession();
		T t = null;
		if (s != null) {
			Transaction tn = s.beginTransaction();
			try {
				t = (T) s.get(clazz, id);
			} catch (Exception e) {
				e.printStackTrace();
				if (tn != null) {
					tn.rollback();
				}
			}
			commitTransaction(tn);
			closeSession(s);
		}
		return t;
	}

	/**
	 * 
	 * @param query
	 *            will executed
	 * @return List object if successful ,null if failed
	 */
	public List<T> findMany(Query query) {
		Session s = getSession();
		List<T> returnList = null;
		if (s != null) {
			Transaction tn = s.beginTransaction();
			try {
				returnList = query.list();
			} catch (Exception e) {
				e.printStackTrace();
				if (tn != null) {
					tn.rollback();
				}
			}
			commitTransaction(tn);
			closeSession(s);
		}
		return returnList;
	}

	/**
	 * 
	 * @param session
	 *            session on which query will run
	 * @param query
	 *            query to be executed
	 * @return
	 */
	public T findOne(Session session, Query query)
			throws NonUniqueResultException {
		Transaction tn = session.beginTransaction();
		T t = null;
		NonUniqueResultException nonUniq = null;
		try {
			t = (T) query.uniqueResult();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			nonUniq = e;
		} catch (Exception e) {
			e.printStackTrace();
			if (tn != null) {
				tn.rollback();
			}
		} finally {
			commitTransaction(tn);
			closeSession(session);
		}
		if (nonUniq != null) {
			throw nonUniq;
		}
		return t;
	}

	public Object findUnique(String hql) {
		Session s = getSession();
		return findOne(s, s.createQuery(hql));
	}

	public Object getOrCreate(Object entity, Class clazz, Integer id) {
		Object tmp = findByID(clazz, id);
		if (tmp == null) {
			tmp = saveObject(entity);
		}
		return tmp;
	}

	protected Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * 
	 * @param entity
	 *            Object to be merged
	 * @return same object if successful ,null if failed
	 */
	public T merge(T entity) {
		Session s = getSession();
		Transaction t = null;
		if (s != null) {
			try {
				t = s.beginTransaction();
				s.merge(entity);
			} catch (Exception e) {
				e.printStackTrace();
				// entity = null;
				if (t != null) {
					t.rollback();
				}
			} finally {
				commitTransaction(t);
				closeSession(s);
			}
		} else {
			entity = null;
		}
		return entity;
	}

	/**
	 * 
	 * @param sql
	 *            HQL Query to be execute
	 * @return List of object if successful ,null if failed
	 */
	public List<?> runSQLQuery(String sql, Class class1) {
		Session s = getSession();
		List<?> list = null;
		if (s != null) {
			Transaction t = s.beginTransaction();
			try {
				SQLQuery query = s.createSQLQuery(sql);
				// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				query.addEntity(class1);
				list = query.list();
			} catch (Exception e) {
				e.printStackTrace();
				if (t != null) {
					t.rollback();
				}
			}
			commitTransaction(t);
			closeSession(s);
		}
		return list;
	}

	/**
	 * 
	 * @param hql
	 *            HQL Query to be execute
	 * @return List of object if successful ,null if failed
	 */
	public List<?> runQuery(String hql) {
		Session s = getSession();
		List<?> list = null;
		if (s != null) {
			Transaction t = s.beginTransaction();
			try {
				Query query = s.createQuery(hql);
				list = query.list();
			} catch (Exception e) {
				e.printStackTrace();
				if (t != null) {
					t.rollback();
				}
			}
			commitTransaction(t);
			closeSession(s);
		}
		return list;
	}

	/**
	 * 
	 * @param entity
	 *            Object to be saved
	 * @return same object if successful ,null if failed
	 */
	public T save(T entity) {
		Session s = getSession();
		Transaction t = null;
		if (s != null) {
			try {
				System.out.println("try");
				t = s.beginTransaction();
				s.saveOrUpdate(entity);
				System.out.println("saved");
			} catch (Exception e) {
				System.out.println("Duplicate");
				e.printStackTrace();
				entity = null;
				if (t != null) {
					t.rollback();
				}
			} finally {
				System.out.println("finally");
				commitTransaction(t);
				closeSession(s);
				return entity;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param entity
	 *            Object to be saved
	 * @return same object if successful ,null if failed
	 */
	public Object saveObject(Object entity) {
		Session s = getSession();
		Transaction t = null;
		if (s != null) {
			try {
				System.out.println("try");
				t = s.beginTransaction();
				s.saveOrUpdate(entity);
				System.out.println("saved");
			} catch (Exception e) {
				System.out.println("Duplicate");
				e.printStackTrace();
				entity = null;
				if (t != null) {
					t.rollback();
				}
			} finally {
				System.out.println("finally");
				commitTransaction(t);
				closeSession(s);
				return entity;
			}
		} else {
			return null;
		}
	}
}
