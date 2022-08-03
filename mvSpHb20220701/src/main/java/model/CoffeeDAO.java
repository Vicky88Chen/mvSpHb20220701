package model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CoffeeDAO {

	private static final Logger logger = Logger.getLogger(CoffeeDAO.class.getName());

	private final static SessionFactory sessionFactory = getSessionFactory();

	protected static SessionFactory getSessionFactory() {
		try {
			return  new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Error Open Config:"+e.getMessage());
			return null;
		}
	}
    public List<Coffee> getAll(){
    	
		Session session = sessionFactory.openSession();
       // Query query = session.createQuery("from Coffees where price > :id ");		
		// query.setParameter("id", 0);
		List<Coffee> list = (List<Coffee>)session.createQuery("FROM Coffee").list();
        return list;
    }
	public void persist(Coffee transientInstance) {  //add
		logger.log(Level.INFO, "persisting Coffees instance");
		Session  ss=  sessionFactory.openSession();
		try {
			Transaction tx=ss.beginTransaction();
			ss.persist(transientInstance);
			tx.commit();
			logger.log(Level.INFO, "persist successful");
		} catch (Exception re) {
			logger.log(Level.SEVERE, "persist failed", re);			
		}finally {
			ss.close();
		}
	}

	public void delete(Coffee persistentInstance) {
		logger.log(Level.INFO, "deleting Coffees instance");
		Session  ss=  sessionFactory.openSession();
		try {
			//sessionFactory.getCurrentSession().delete(persistentInstance);
			Transaction tx=ss.beginTransaction();
			ss.delete(persistentInstance);
			tx.commit();
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}

	public Coffee merge(Coffee detachedInstance) {
		logger.log(Level.INFO, "merging Coffees instance");
		Session  ss=  sessionFactory.openSession();
		System.out.println("Merge:"+detachedInstance.toString());
		try {
			//Coffees result = (Coffees) sessionFactory.getCurrentSession().merge(detachedInstance);
			Transaction tx=ss.beginTransaction();
			Coffee result = (Coffee)ss.merge(detachedInstance);
			tx.commit();
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			return null;
		}finally {
			ss.close();
		}
		
	}

	public Coffee findById(String cofName) {
		logger.log(Level.INFO, "getting Coffees instance with id: " + cofName);
		Session  ss=  sessionFactory.openSession();
		try {	
			Transaction tx=ss.beginTransaction();
			Coffee instance = (Coffee) ss.find(model.Coffee.class, cofName);
			tx.commit();
			if (instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}finally {
			ss.close();
		}
	}
	
}
