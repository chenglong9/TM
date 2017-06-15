package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Details;
import bean.tb_details;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteDetailsServlet
 */
@WebServlet("/DeleteDetailsServlet")
public class DeleteDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	 	 try {
		 	    response.setContentType("text/html");  
		         response.setCharacterEncoding("UTF-8"); 
		         PrintWriter out = response.getWriter();  
		         request.setCharacterEncoding("UTF-8");
		     	 int id = Integer.parseInt(request.getParameter("id"));
		    	 Details details= new Details();        
		    	 details.delete(id);	     	 
		         JSONObject json=new JSONObject();  
		         json.accumulate("result", "ok");  
		         out.println(json.toString());  
		         out.flush();  
		         out.close(); 
		     		} catch (Exception e) {
		     			// TODO Auto-generated catch block	 
		     			PrintWriter out = response.getWriter();  
		     			JSONObject json=new JSONObject();  
		     			   json.accumulate("result", "no");  
		     		        out.println(json.toString());  
		     		        out.flush();  
		     		        out.close(); 
		     			e.printStackTrace();
		     		
		     		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
