

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import model.*;
/**
 * Servlet implementation class CoffeeServlet
 */
@WebServlet("/CoffeeServlet")
public class CoffeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoffeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String cname=request.getParameter("cname");
		String price=request.getParameter("price");
		String sale=request.getParameter("sale");
		String total=request.getParameter("total");
		String method=request.getParameter("method");		
		CoffeeDAO home=new CoffeeDAO();
		if(method.equals("add")) {
			Coffee c1=new Coffee(cname,Integer.parseInt(id),Double.parseDouble(price),Integer.parseInt(sale),Integer.parseInt(total));
			home.persist(c1);
			//response.getWriter().append("Save Success");
		}
		if(method.equals("update")) {
			Coffee c1=new Coffee(cname,Integer.parseInt(id),Double.parseDouble(price),Integer.parseInt(sale),Integer.parseInt(total));
			Coffee cx=home.merge(c1);			
//			if(cx !=null)
//			    response.getWriter().append(cx.toString());
//			else
//				response.getWriter().append("Update Error");
		}
		if(method.equals("find")) {
		   Coffee c=home.findById(cname);
		   if(c!=null)
		       response.getWriter().append(c.toString());
		   else
			   response.getWriter().append(cname+" Not found");
		   return;
		}
		if(method.equals("remove")) {
			Coffee c1=new Coffee(cname,Integer.parseInt(id),Double.parseDouble(price),Integer.parseInt(sale),Integer.parseInt(total));
			home.delete(c1);
			//response.getWriter().append("Save Success");
		}
        List<Coffee> data=  home.getAll();
        request.setAttribute("coffees", data);
		request.getRequestDispatcher("viewCoffee.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
