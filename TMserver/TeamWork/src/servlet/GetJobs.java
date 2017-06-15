package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Dao.Details;
import Dao.Members;
import Dao.People;
import Dao.Projects;
import bean.tb_details;
import bean.tb_members;
import bean.tb_people;
import bean.tb_projects;



/**
 * Servlet implementation class GetJobs
 */
@WebServlet("/GetJobs")
public class GetJobs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetJobs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 try {
			    response.setContentType("text/html");  
		        response.setCharacterEncoding("UTF-8"); 
		        PrintWriter out = response.getWriter();  
		        request.setCharacterEncoding("UTF-8");
		        
		        String id = request.getParameter("id");
		        People people=new People();
		    	Details details = new Details();
		    	Projects projects = new Projects();
		    	Members members = new Members();
		    //	List<tb_members> list =members.select("person", id);
		    /*	Set<Integer> set = new HashSet<Integer>();
		    	
		    	for(int i = 0; i <list.size();i++){
		    		if (list.get(i).getWork_id()!=0) {
		    			set.add(list.get(i).getWork_id());
					}
		    	}*/
		    	JSONArray js_id = new JSONArray(id);
		    	
		    	JSONArray jsonArray = new JSONArray();
		    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		    	
		    	for(int index = 0; index <js_id.length();index++){
		    		List<tb_details> tb_detailss=  details.select("project_id", js_id.getString(index));
		    		for(tb_details tb_details : tb_detailss){
		    	  JSONObject json=new JSONObject();  
		    	  json.accumulate("stage_id",tb_details.getStage_id());
		    	  json.accumulate("project_id",tb_details.getProject_id());
		    	  json.accumulate("project_name",tb_details.getProject_name());
		    	  json.accumulate("describes",tb_details.getDescribes());
		    	  
		    	  String str=sdf.format(tb_details.getStart_time());  
		    	  json.accumulate("start_time",str);
		    	  str=sdf.format(tb_details.getEnd_time());  
		    	  json.accumulate("end_time",str);
		    	  json.accumulate("types",tb_details.getTypes());
		    	  json.accumulate("person_in_charge",tb_details.getPerson_in_charge());
		    	  json.accumulate("tx_time",tb_details.getTx_time().replace("/", " "));
		    	  json.accumulate("tx_method",tb_details.getTx_method());
		    	  
		    	   List<tb_members> mList=members.select("work_id", tb_details.getStage_id()+"");
			          
			          JSONArray jArray = new JSONArray();
				    	
			          for(tb_members tbm : mList){
			        	  JSONObject js=new JSONObject(); 
			        	  js.accumulate("mphone", tbm.getPerson());
			          	List<tb_people> tb_p=people.select("phone",tbm.getPerson());
			          	if (tb_p.size()!=0) {
			          		js.accumulate("mname", tb_p.get(0).getName());
						}
			          	jArray.put(js);
			          }
			          
		    	  json.accumulate("friend",jArray);
		    	  
		    	  jsonArray.put(json);
		    	}	
		    		}    	 
		        out.println(jsonArray.toString());  
		        out.flush();  
		        out.close(); 
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block	 
		    			PrintWriter out = response.getWriter();  
		    			JSONObject json=new JSONObject();  
		    			   try {
							json.accumulate("result", "no");
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
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
