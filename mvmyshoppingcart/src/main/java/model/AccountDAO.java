package model;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AccountDAO {
	private SessionFactory  buildFactory() {
		 SessionFactory factory=null;          
  		 try {
  			 Configuration cf=new Configuration();
  			 //cf.addAnnotatedClass(Account.class);
  			 cf.configure();
  	         factory = cf.buildSessionFactory();
  	         return factory;
  	      } catch (Throwable ex) { 
  	         System.err.println("Failed to create sessionFactory object." + ex);
  	         //throw new ExceptionInInitializerError(ex);	        
  	      }		
  		 return null;
	}
    public boolean checkUser(Account  acct) {
    	
    	  List<Account>  data= null;
          SessionFactory factory=buildFactory();
          if(factory==null) {
        	  System.out.println("Failed to build Hibernate Session Factory");
        	  return false;
          }
  		  Session session = factory.openSession();
  	      Transaction tx = null;
  	      try {
  		         tx = session.beginTransaction();
  		         TypedQuery q=session.createQuery("FROM Account as acct where acct.userName =:user and acct.password =:pass");
  		         q.setParameter("user", acct.getUserName());
  		         q.setParameter("pass", acct.getPassword());
  		         List<Account> accts=q.getResultList();
  		         tx.commit();
  		        if(accts.size()>0)
  		            return  true;	        	 
  		        
  		      } catch (HibernateException e) {
  		         if (tx!=null) tx.rollback();
  		         e.printStackTrace(); 
  		      } finally {
  		         session.close(); 
  		         factory.close();
  		      }
          return   false;         
    }
}
