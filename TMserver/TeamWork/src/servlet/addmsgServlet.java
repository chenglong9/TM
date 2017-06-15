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
@WebServlet("/addmsgServlet")
public class addmsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public addmsgServlet() {
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
        String name = request.getParameter("name");
        String memberid = request.getParameter("memberid");
        String type = request.getParameter("type");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        People people=new People();
        tb_people tb_p=people.selectByID(memberid);
        
        JSONObject json=new JSONObject();  
        if (tb_p==null) {
        	 json.accumulate("result", "no");
             out.println(json.toString());  
             out.flush();  
             out.close();  
		}else
		{
        Notice notice=new Notice();
        
        try {
        tb_notice tb_n=new tb_notice(Integer.parseInt(id),memberid,type,con,sdf.format(new Date()));
        notice.add(tb_n);
        //将要被返回到客户端的对象  
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        json.accumulate("result", "ok");
        json.accumulate("name",  tb_p.getName());
        
        out.println(json.toString());  
        out.flush();  
        out.close();  
		}
    }  
  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
}
