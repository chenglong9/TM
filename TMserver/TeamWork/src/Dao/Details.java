package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.tb_details;
import hibernate.HibernateUtil;


public class Details {
	
	public void add(tb_details tb_details){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.save(tb_details);
		tx.commit();
		System.out.println("saved");

	}
	
	public void delete(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_details customer =(tb_details)session.get(tb_details.class,id);
		if(customer!=null)
		session.delete(customer);
		tx.commit();
		System.out.println("deleted");
	}
	
	public void modify(tb_details tb_details){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.update(tb_details);
		tx.commit();
		System.out.println("updated");
	}
	
	public tb_details selectByID(int id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_details customer =(tb_details)session.get(tb_details.class,id);
		tx.commit();
        return customer;
	}
	
	public List<tb_details> select (String key1,String key2){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_details where "+key1+" = '"+key2+"'");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
	
	public List<tb_details>  selectAll(){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_details");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
}
