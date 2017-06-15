package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.tb_notice;
import bean.tb_people;
import hibernate.HibernateUtil;

public class People {
	public void add(tb_people tb_people){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.save(tb_people);
		tx.commit();
		System.out.println("saved");
	}
	
	public void delete(String id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_people tb_people =(tb_people)session.get(tb_people.class,id);
		if(tb_people!=null)
		session.delete(tb_people);
		tx.commit();
		System.out.println("deleted");
	}
	
	public void modify(tb_people tb_people){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		session.update(tb_people);
		tx.commit();
		System.out.println("updated");
	}
	
	public tb_people selectByID(String id){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		tb_people tb_people =(tb_people)session.get(tb_people.class,id);
		tx.commit();
        return tb_people;
	}
	
	public List<tb_people>  selectAll(){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_people");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
	
	public List<tb_people> select (String key1,String key2){
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from tb_people where "+key1+" = '"+key2+"'");
		java.util.List list = qry.list();  
		tx.commit();
		return list;
	}
}
