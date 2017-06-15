package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

import Dao.Members;
import Dao.People;
import Dao.Projects;
import bean.tb_members;
import bean.tb_people;
import bean.tb_projects;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetProjectServlet
 */
@WebServlet("/GetProjectServlet")
public class GetProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		 try {
			    response.setContentType("text/html");  
		        response.setCharacterEncoding("UTF-8"); 
		        PrintWriter out = response.getWriter();  
		        request.setCharacterEncoding("UTF-8");
		        
	
		        String id = request.getParameter("id");
		       
		    	Projects projects = new Projects();
		    	Members members = new Members();
		    	People people=new People();
		    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		    	
		    	List<tb_members> list =members.select("person", id);
		    	Set<Integer> set = new HashSet<Integer>();
		    	
		    	for(int i = 0; i <list.size();i++){
		    		if (list.get(i).getWork_id()==0) {
		    			set.add(list.get(i).getProject_id());
					}
		    	}
		    	JSONArray jsonArray = new JSONArray();
		    	
		    	for(Integer i : set){
		    	
		    		tb_projects tb_projects=  projects.selectByID(i);
		    	  JSONObject json=new JSONObject();  
		    	  json.accumulate("people_in_charge",tb_projects.getPeople_in_charge());
		    	  json.accumulate("project_id",tb_projects.getProject_id());
		    	  json.accumulate("project_name",tb_projects.getProject_name());
		    	  json.accumulate("project_describe",tb_projects.getProject_describe());
		    	  String str=sdf.format(tb_projects.getStart_time());  
		    	  json.accumulate("start_time",str);
		    	  str=sdf.format(tb_projects.getEnd_time());  
		    	  json.accumulate("end_time",str);
		    	  
		          List<tb_members> mList=members.select("project_id", tb_projects.getProject_id()+"");
		          
		          JSONArray jArray = new JSONArray();
			    	
		          for(tb_members tbm : mList){
		        	  JSONObject js=new JSONObject(); 
		        	  js.accumulate("mphone", tbm.getPerson());
		          	List<tb_people> tb_p=people.select("phone",tbm.getPerson());
		          	if (tb_p.size()!=0) {
		          		js.accumulate("mname", tb_p.get(0).getName());
					}
		          	jArray.add(js);
		          }
		          json.accumulate("friend",jArray);
		    	  jsonArray.add(json);
		    	}	    	 
		        out.println(jsonArray.toString());  
		        out.flush();  
		        out.close(); 
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block	 
		    			PrintWriter out = response.getWriter();  
		    			JSONObject json=new JSONObject();  
		    			   json.accumulate("result", "no");  
		    		        out.println(json.toString());  
		    		        out.flush();  
		    		        out.close(); 
		    			e.printStackTrace();
		    		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
