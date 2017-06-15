package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Members;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteProjectMember
 */
@WebServlet("/DeleteProjectMember")
public class DeleteProjectMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProjectMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
		 	    response.setContentType("text/html");  
		         response.setCharacterEncoding("UTF-8"); 
		         PrintWriter out = response.getWriter();  
		         request.setCharacterEncoding("UTF-8");
		     	 int id = Integer.parseInt(request.getParameter("id"));
		     	 String phone =request.getParameter("phone");
		    	 Members members= new Members();  
		    	 
		    	 members.deletep(id, phone);
		    	 
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
