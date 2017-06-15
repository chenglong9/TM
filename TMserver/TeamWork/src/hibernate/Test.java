package hibernate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import Dao.*;
import bean.*;

public class Test {
	public static void main(String[] args) {
		Projects projects = new Projects();
		tb_projects tb_projects= new tb_projects();
		tb_projects.setProject_id(2);
		//tb_projects.setPeople_in_charge(233);
		Date date=new Date();
		DateFormat df1 = DateFormat.getDateInstance();//
		
		//tb_projects.setStart_time( date);
		 System.out.println(df1.format(date));
		//tb_projects.setName("bo");
		//tb_notice.setStage_id(1);
		
		//tb_people.setProject_id(100);
		//projects.add(tb_projects);
	
		//projects.delete(1);
		projects.modify(tb_projects);
		 System.out.println(projects.selectByID(2).getPeople_in_charge());
		//List<tb_projects> list=projects.select("people_in_charge","123");
		//List<tb_people> list=people.selectAll();
	/*	 for(int i=0;i<list.size();i++){
			 System.out.println(list.get(i).getProject_id());
		 }*/
		 
		 
	 HibernateUtil.getSessionFactory().close();
	}
	
}
