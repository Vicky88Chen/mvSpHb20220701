package demo.controller;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.*;


@Controller
public class AccountController {
	
	@RequestMapping(value="/account",method = RequestMethod.GET)
	   public String viewAccount(ModelMap model) {
		  model.addAttribute("id","1");		  
		  model.addAttribute("userName","Mary");
		  model.addAttribute("password","m135");
	      return  "account";
	   }
	@RequestMapping(value="/check",method = RequestMethod.POST)	
	 public String checkAccount(@ModelAttribute("account") Account acct, HttpSession session) {         
       AccountDAO  dao=new AccountDAO();
       boolean flag=dao.checkUser(acct);
       if(flag)
    	   return "success";
       else
    	   return   "failed";         
    } 
	
	@RequestMapping("/allaccount")
	@ResponseBody  
	public List<Account> generatehbAccount(Model model) {         
        List<Account>  data= null;
        SessionFactory factory=null;
		 try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex);	        
	      }		
		
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try {
		         tx = session.beginTransaction();
		         data =(List<Account>)session.createQuery("FROM Account").list(); 		       
		         tx.commit();
		         return data;
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		         factory.close();
		      }
        return   data;         
    }
}
