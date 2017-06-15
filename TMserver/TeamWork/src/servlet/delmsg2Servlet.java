package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Notice;
import bean.tb_notice;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/delmsg2Servlet")
public class delmsg2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public delmsg2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8"); 
        PrintWriter out = response.getWriter();  
        request.setCharacterEncoding("UTF-8");  
    	String id = request.getParameter("id");
		
		Notice notice = new Notice();
		notice.delete(Integer.parseInt(id));
		
		out.println();
        out.flush();  
        out.close();  
    }  
  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
}
