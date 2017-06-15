package bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="tb_notice")
public class tb_notice {
	private int notice_id;
	private int project_id;
	private String member_id;
	private String type;
	private String words;
	private String nt_time;
	public tb_notice() {
		super();
	}
	public tb_notice( int project_id, String member_id, String type, String words, String nt_time) {
		super();
		this.project_id = project_id;
		this.member_id = member_id;
		this.type = type;
		this.words = words;
		this.nt_time = nt_time;
	}
	@Id
	@GeneratedValue(generator="identity" )
	@GenericGenerator(name="identity",strategy="identity")
	public int getNotice_id() {
		return notice_id;
	}
	
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	@Column(name="project_id")
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	@Column(name="member_id")
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="words")
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	@Column(name="nt_time")
	public String getNt_time() {
		return nt_time;
	}
	public void setNt_time(String nt_time) {
		this.nt_time = nt_time;
	}

	
	
}
