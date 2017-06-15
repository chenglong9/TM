package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import com.mysql.jdbc.Statement;

import bean.tb_members;
import bean.tb_notice;
import hibernate.HibernateUtil;

public class Notice {
	public void add(tb_notice tb_notice){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.save(tb_notice);
		tx.commit();
		System.out.println("saved");
	}
	
	public void delete(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_notice tb_notice =(tb_notice)session.get(tb_notice.class,id);
		if(tb_notice!=null)
		session.delete(tb_notice);
		tx.commit();
		System.out.println("deleted");
	}
	
	public void modify(tb_notice tb_notice){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.update(tb_notice);
		tx.commit();
		System.out.println("updated");
	}
	
	public tb_notice selectByID(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_notice tb_notice =(tb_notice)session.get(tb_notice.class,id);
		tx.commit();
        return tb_notice;
	}
	
	public List<tb_notice>  selectAll(){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_notice");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
	
	public List<tb_notice> select (String key1,String key2){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_notice where "+key1+" = '"+key2+"'");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
	public void deleteno(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.doWork(new Work() {
            @Override
            public void execute(Connection arg0) throws SQLException {  
                String sql = "delete from tb_notice where project_id = "+id;   
                Statement st = (Statement) arg0.createStatement();  
                st.executeUpdate(sql);  
            }
        });  
		tx.commit();
	
	}
}
