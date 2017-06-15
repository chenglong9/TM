package bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="tb_projects")
public class tb_projects {
	private int project_id;
	private String people_in_charge;
	private String project_name;
	private String project_describe;
	private Date start_time;
	private Date end_time;
	private String tx_method;
	public tb_projects( String people_in_charge, String project_name, String project_describe,
			Date start_time, Date end_time, String tx_method) {
		super();
		this.people_in_charge = people_in_charge;
		this.project_name = project_name;
		this.project_describe = project_describe;
		this.start_time = start_time;
		this.end_time = end_time;
		this.tx_method = tx_method;
	}
	public tb_projects() {
		super();
	}
	@Id
	@GeneratedValue(generator="identity" )
	@GenericGenerator(name="identity",strategy="identity")
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	@Column(name="people_in_charge")
	public String getPeople_in_charge() {
		return people_in_charge;
	}
	public void setPeople_in_charge(String people_in_charge) {
		this.people_in_charge = people_in_charge;
	}
	@Column(name="project_name")
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	@Column(name="project_describe")
	public String getProject_describe() {
		return project_describe;
	}
	public void setProject_describe(String project_describe) {
		this.project_describe = project_describe;
	}
	@Column(name="start_time")
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	@Column(name="end_time")
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	@Column(name="tx_method")
	public String getTx_method() {
		return tx_method;
	}
	public void setTx_method(String tx_method) {
		this.tx_method = tx_method;
	}


	
}
