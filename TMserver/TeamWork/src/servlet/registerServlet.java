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
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public registerServlet() {
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
        String name = request.getParameter("name");
        
        People people=new People();
        tb_people t=people.selectByID(phone);
        JSONObject json=new JSONObject(); 
        
        if (t==null) {
        	 tb_people tb_p=new tb_people();
             tb_p.setName(name);
             tb_p.setPhone(phone);
             tb_p.setPwd(pwd);
             people.add(tb_p);
             //将要被返回到客户端的对象  
             json.accumulate("result", "ok");  
		}else {
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
