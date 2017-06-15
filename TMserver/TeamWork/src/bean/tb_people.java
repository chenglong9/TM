package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="tb_people")
public class tb_people {
	private String phone;
	private String weixin;
	private String name;
	private String pwd;
	public tb_people() {
		super();
	}
	public tb_people(String phone, String weixin, String name, String pwd) {
		super();
		this.phone = phone;
		this.weixin = weixin;
		this.name = name;
		this.pwd = pwd;
	}
	@Id
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="weixin")
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	@Column(name="pwd")
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
