package zucc.tm.jg.bean;

/**
 * Created by 45773 on 2017-05-23.
 */

public class mybean {
    public mybean(String name, String phone, String pwd) {
        this.phone = phone;
        this.name = name;
        this.pwd = pwd;
    }

    public mybean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String name;
    String phone;
    String pwd;
}
