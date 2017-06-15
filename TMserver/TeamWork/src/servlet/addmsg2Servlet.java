package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Notice;
import Dao.People;
import Dao.Projects;
import bean.tb_notice;
import bean.tb_people;
import bean.tb_projects;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/addmsg2Servlet")
public class addmsg2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public addmsg2Servlet() {
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
        String con = request.getParameter("con");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        
    
        JSONObject json=new JSONObject();  
       
        Notice notice=new Notice();
        
        tb_notice tb_n=new tb_notice(Integer.parseInt(id),"","msg2",con,sdf.format(new Date()));
        notice.add(tb_n);
      
    
        json.accumulate("result", "ok");
   
        
        out.println(json.toString());  
        out.flush();  
        out.close();  
		
    }  
  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
}
