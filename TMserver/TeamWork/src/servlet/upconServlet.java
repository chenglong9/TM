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

import Dao.Projects;
import bean.tb_people;
import bean.tb_projects;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/upconServlet")
public class upconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public upconServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String con = request.getParameter("con");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name");
		try {
			Date times = sdf.parse(request.getParameter("times"));
			Date timee = sdf.parse(request.getParameter("timee"));

			Projects projects = new Projects();
			tb_projects tb = new tb_projects();
			tb_projects tb_p = projects.selectByID(Integer.parseInt(id));
			tb_p.setProject_describe(con);
			tb_p.setProject_name(name);
			tb_p.setStart_time(times);
			projects.modify(tb_p);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 将要被返回到客户端的对象
		JSONObject json = new JSONObject();
		json.accumulate("result", "ok");

		out.println(json.toString());
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
