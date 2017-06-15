package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import net.sf.json.JSONObject;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.People;
import bean.tb_people;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8"); 
        PrintWriter out = response.getWriter();  
        request.setCharacterEncoding("UTF-8");  
        
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        
        People people=new People();
        tb_people resultx=people.selectByID(phone);
      
        
        //将要被返回到客户端的对象  
        JSONObject json=new JSONObject();  
        
        if (resultx!=null) {
        	if(resultx.getPwd().equals(pwd))
        	{
        	  String name=resultx.getName();
        	 json.accumulate("result", "ok");  
             json.accumulate("img", "ok");
             json.accumulate("name", name);
        	}else {
        		json.accumulate("result", "no2"); 
			}
		}
       else {
    	   json.accumulate("result", "no");  
       }
        
        out.println(json.toString());  
        out.flush();  
        out.close();  
    }  
  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
}
