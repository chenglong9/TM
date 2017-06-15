package servlet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_details")
public class tb_details {
	private int stage_id;
	private int project_id;
	private String project_name ;
	private String describes;
	private Date start_time;
	private Date end_time;
	private String types;
	private int person_in_charge;
	private String tx_time;
	private String tx_method;

	
	
	public tb_details( int project_id, String project_name, String describes, Date start_time,
			Date end_time, String types, int person_in_charge, String tx_time, String tx_method) {
		super();
		
		this.project_id = project_id;
		this.project_name = project_name;
		this.describes = describes;
		this.start_time = start_time;
		this.end_time = end_time;
		this.types = types;
		this.person_in_charge = person_in_charge;
		this.tx_time = tx_time;
		this.tx_method = tx_method;
	}
	public tb_details(){
		
	}
	@Id
	@GeneratedValue(generator="identity" )
	@GenericGenerator(name="identity",strategy="identity")
	public int getStage_id() {
		return stage_id;
	}
	public void setStage_id(int stage_id) {
		this.stage_id = stage_id;
	}
	@Column(name="project_id")
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
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
	@Column(name="person_in_charge")
	public int getPerson_in_charge() {
		return person_in_charge;
	}
	public void setPerson_in_charge(int person_in_charge) {
		this.person_in_charge = person_in_charge;
	}
	@Column(name="describes")
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	@Column(name="types")
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	@Column(name="project_name")
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	@Column(name="tx_time")
	public String getTx_time() {
		return tx_time;
	}
	public void setTx_time(String tx_time) {
		this.tx_time = tx_time;
	}
	@Column(name="tx_method")
	public String getTx_method() {
		return tx_method;
	}
	public void setTx_method(String tx_method) {
		this.tx_method = tx_method;
	}
   
	
}
