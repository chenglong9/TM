package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Details;
import Dao.Members;
import Dao.Notice;
import Dao.Projects;
import bean.tb_members;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteProjectServlet
 */
@WebServlet("/DeleteProjectServlet")
public class DeleteProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProjectServlet() {
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
		    	 Projects projects= new Projects();        
		    	 projects.delete(id);
		    	 
		         Members member=new Members();
		         member.deletepro(id);;
		    	 
		         Notice noti=new Notice();
		         noti.deleteno(id);;
		    	 
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
