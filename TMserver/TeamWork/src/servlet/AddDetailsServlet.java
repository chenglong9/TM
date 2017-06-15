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
import javax.xml.soap.Detail;

import Dao.Details;
import Dao.Projects;
import bean.tb_details;
import bean.tb_projects;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddDetailsServlet
 */
@WebServlet("/AddDetailsServlet")
public class AddDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDetailsServlet() {
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
	         
	         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	         
	     	// int stage_id = Integer.parseInt(request.getParameter("stage_id"));
	    	 int project_id  = Integer.parseInt(request.getParameter("project_id"));
	    	 String project_name = request.getParameter("project_name");
	    	 String describes = request.getParameter("describes");
	    	 Date start_time  = sdf.parse(request.getParameter("start_time"));
		 	 Date end_time  = sdf.parse(request.getParameter("end_time"));   
	    	// String types  = request.getParameter("types"); 
	    	// int person_in_charge = Integer.parseInt(request.getParameter("people_in_charge")) ;
	    	 String tx_time  = request.getParameter("tx_time"); 
	    	 String tx_method  = request.getParameter("tx_method"); 
	    	 String method =new String();
	    	 if (tx_method.equals("0")) {
	    		 method="ƒ÷¡Â";
				}else {
					 method="Œﬁ";
				}
	    	 
	    	 Details details= new Details();
	        tb_details tb_details = new tb_details( project_id, project_name, describes, start_time, end_time, "", 0,tx_time,method);
	        details.add(tb_details);
	     	 
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
