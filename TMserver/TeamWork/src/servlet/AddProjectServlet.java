package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Members;
import Dao.Projects;
import bean.tb_members;
import bean.tb_projects;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddProjectServlet
 */
@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding("UTF-8");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String people_in_charge = request.getParameter("people_in_charge");
			String project_name = request.getParameter("project_name");
			String project_describe = request.getParameter("project_describe");
			Date start_time = sdf.parse(request.getParameter("start_time"));
			Date end_time = sdf.parse(request.getParameter("end_time"));
			String tx_method = "";

			Projects projects = new Projects();
			tb_projects tb_projects = new tb_projects(people_in_charge, project_name, project_describe, start_time,
					end_time, tx_method);
			projects.add(tb_projects);
			List<tb_projects> list = projects.selectBy2(people_in_charge, project_name);
			tb_members tb_m = new tb_members();

			tb_m.setProject_id(list.get(0).getProject_id());
			tb_m.setPerson(list.get(0).getPeople_in_charge());
			Members member = new Members();

			member.add(tb_m);

			JSONObject json = new JSONObject();
			json.accumulate("result", "ok");

			out.println(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.accumulate("result", "no");
			out.println(json.toString());
			out.flush();
			out.close();
			e.printStackTrace();

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
