package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Details;
import bean.tb_details;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ModifyRW
 */
@WebServlet("/ModifyRW")
public class ModifyRW extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyRW() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		  response.setContentType("text/html");  
	        response.setCharacterEncoding("UTF-8"); 
	        PrintWriter out = response.getWriter();  
	        request.setCharacterEncoding("UTF-8");  
	        String id = request.getParameter("id");
	        String what = request.getParameter("what");
	        String value = request.getParameter("value");
	        
	        tb_details tb_details = new tb_details();
	        Details details = new Details();
	        tb_details=details.selectByID(Integer.parseInt(id));
	        
	        String method =new String();
	    	
	        
	        if(what.equals("tx_time"))
	        	tb_details.setTx_time(value);
	        else if (what.equals("method")) {
	        	 if (value.equals("0")) {
		    		 method="ƒ÷¡Â";
					}else {
						 method="Œﬁ";
					}
	        	tb_details.setTx_method(method);
			}else if (what.equals("f")) {
				tb_details.setPerson_in_charge(Integer.parseInt(value));
			}
	        details.modify(tb_details);
	        JSONObject json=new JSONObject();  
	         json.accumulate("result", "ok");  
	         
	         out.println(json.toString());  
	         out.flush();  
	         out.close(); 
	         
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
