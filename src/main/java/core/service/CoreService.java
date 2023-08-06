package core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import static core.util.HibernateUtil.getSessionFactory;;

public interface CoreService {
	
	private Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	default Transaction beginTransaction() {
		return getSession().beginTransaction();
		
	}
	
	default void commit() {
		getSessionFactory().getCurrentSession().getTransaction().commit();
	}
	
	default void rollback() {
		getSessionFactory().getCurrentSession().getTransaction().rollback();
	}	

}