package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.tb_details;
import bean.tb_projects;
import hibernate.HibernateUtil;

public class Projects {
	public void add(tb_projects tb_projects){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.save(tb_projects);
		tx.commit();
		System.out.println("saved");
	}
	
	public void delete(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_projects tb_projects =(tb_projects)session.get(tb_projects.class,id);
		if(tb_projects!=null)
		session.delete(tb_projects);
		tx.commit();
		System.out.println("deleted");
	}
	
	public void modify(tb_projects tb_projects){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.update(tb_projects);
		tx.commit();
		System.out.println("updated");
	}
	
	public tb_projects selectByID(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_projects tb_people =(tb_projects)session.get(tb_projects.class,id);
		tx.commit();
        return tb_people;
	}
	public List<tb_projects> selectBy2(String key1,String key2){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_projects where people_in_charge = '"+key1+"' and project_name = '"+key2+"'");
		java.util.List list = qry.list();  
		tx.commit();
        return list;
	}
	public List<tb_projects> select (String key1,String key2){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_projects where "+key1+" = '"+key2+"'");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
	
	public List<tb_projects>  selectAll(){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_projects");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
}
