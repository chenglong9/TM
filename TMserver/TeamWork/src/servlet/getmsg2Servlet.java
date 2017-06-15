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
@WebServlet("/getmsg2Servlet")
public class getmsg2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public getmsg2Servlet() {
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
		String type = request.getParameter("type");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		Notice notice = new Notice();
		List<tb_notice> tb_ns = notice.select("project_id", id);
		
		JSONArray jsonArray=new JSONArray();
		for (int i = 0; i < tb_ns.size(); i++) {
			if (tb_ns.get(i).getType().equals("msg2")) {
				JSONObject json = new JSONObject();
				json.accumulate("id", tb_ns.get(i).getNotice_id());
				json.accumulate("con", tb_ns.get(i).getWords());
				String str=tb_ns.get(i).getNt_time();
				json.accumulate("time",str);
				jsonArray.add(json);
			}
		}

		out.println(jsonArray.toString());
        out.flush();  
        out.close();  
    }  
  
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
}
