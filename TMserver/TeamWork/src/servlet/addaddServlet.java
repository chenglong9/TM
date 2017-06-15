package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Members;
import Dao.Projects;
import bean.tb_members;
import bean.tb_projects;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/addaddServlet")
public class addaddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public addaddServlet() {
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
        String phone = request.getParameter("phone");
        String workid = request.getParameter("workid");
        tb_members tb_m=new tb_members();
        tb_m.setProject_id(Integer.parseInt(id));
        tb_m.setPerson(phone);
        try {
        	   tb_m.setWork_id(Integer.parseInt(workid) );
		} catch (Exception e) {
			// TODO: handle exception
			   tb_m.setWork_id(0);
		}
     
        Members member=new Members();
        member.add(tb_m);
       
        //将要被返回到客户端的对象  
        JSONObject json=new JSONObject();  
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
