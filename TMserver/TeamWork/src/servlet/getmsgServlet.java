package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import Dao.Notice;
import Dao.Projects;
import bean.tb_notice;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/getmsgServlet")
public class getmsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getmsgServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		Projects projects =new Projects();
		Notice notice = new Notice();
		List<tb_notice> tb_ns = notice.select("member_id", id);
		
		  JSONObject js=new JSONObject();  
		  
		JSONArray jsonArray=new JSONArray();
		
		for (int i = 0; i < tb_ns.size(); i++) {
			
			if (tb_ns.get(i).getType().equals("msg")) {
				JSONObject json = new JSONObject();
		
				json.accumulate("name",projects.selectByID(tb_ns.get(i).getProject_id()).getProject_name() );
				json.accumulate("project_id",tb_ns.get(i).getProject_id() );
				json.accumulate("id", tb_ns.get(i).getNotice_id());
				json.accumulate("con", tb_ns.get(i).getWords());
				json.accumulate("time",tb_ns.get(i).getNt_time());
				
				jsonArray.add(json);
			}
		}
		js.accumulate("tz", jsonArray);
		JSONArray jsa=new JSONArray();
		jsa.add(js);
		out.println(jsa.toString());
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
