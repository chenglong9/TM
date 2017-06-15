package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import com.mysql.jdbc.Statement;

import bean.tb_details;
import bean.tb_members;
import hibernate.HibernateUtil;

public class Members {
	public void add(tb_members tb_members){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.save(tb_members);
		tx.commit();
		System.out.println("saved");
	}
	
	public void delete(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_members tb_members =(tb_members)session.get(tb_members.class,id);
		if(tb_members!=null)
		session.delete(tb_members);
		tx.commit();
		System.out.println("deleted");
	}
	public void deletepro(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.doWork(new Work() {  
            
            @Override  
            public void execute(Connection arg0) throws SQLException {  
                String sql = "delete from tb_members where project_id = "+id;   
                Statement st = (Statement) arg0.createStatement();  
                st.executeUpdate(sql);  
            }

		
        });  
		tx.commit();
	
	}
	public void deletep(int id,String phone){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.doWork(new Work() {  
            
            @Override  
            public void execute(Connection arg0) throws SQLException {  
                String sql = "delete from tb_members where project_id = "+id +" and person ='"+phone+"'";   
                Statement st = (Statement) arg0.createStatement();  
                st.executeUpdate(sql);  
            }

		
        });  
		tx.commit();	
	}
	public void deletew(int id,String phone){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.doWork(new Work() {  
            
            @Override  
            public void execute(Connection arg0) throws SQLException {
                String sql = "delete from tb_members where work_id = "+id +" and person ='"+phone+"'";   
                Statement st = (Statement) arg0.createStatement();  
                st.executeUpdate(sql);  
            }

		
        });  
		tx.commit();	
	}
	public void modify(tb_members tb_members){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.update(tb_members);
		tx.commit();
		System.out.println("updated");
	}
	
	public tb_members selectByID(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_members tb_members =(tb_members)session.get(tb_members.class,id);
		tx.commit();
        return tb_members;
	}
	
	public List<tb_members>  selectAll(){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_members");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
	
	public List<tb_members> select (String key1,String key2){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_members where "+key1+" = '"+key2+"'");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
}
