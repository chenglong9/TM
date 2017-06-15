package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="tb_members")
public class tb_members {
	private int id;
	private int project_id;
	private String person;
	private int stage_id;
	private int work_id;
	private String words;
	public tb_members() {
		super();
	}
	public tb_members( int project_id, String person, int stage_id, int work_id, String words) {
		super();
		this.project_id = project_id;
		this.person = person;
		this.stage_id = stage_id;
		this.work_id = work_id;
		this.words = words;
	}
	@Id
	@GeneratedValue(generator="identity" )
	@GenericGenerator(name="identity",strategy="identity")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="project_id")
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	@Column(name="person")
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	@Column(name="stage_id")
	public int getStage_id() {
		return stage_id;
	}
	public void setStage_id(int stage_id) {
		this.stage_id = stage_id;
	}
	@Column(name="work_id")
	public int getWork_id() {
		return work_id;
	}
	public void setWork_id(int work_id) {
		this.work_id = work_id;
	}
	@Column(name="words")
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	
	
}
