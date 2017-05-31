package zucc.tm.jg.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 45773 on 2017-05-23.
 */

public class projectbean {
    String phone;
    String projectid;
    String projectname;
    String projectcon;
    String times;


    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    String phonename;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectcon() {
        return projectcon;
    }

    public void setProjectcon(String projectcon) {
        this.projectcon = projectcon;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTimee() {
        return timee;
    }

    public void setTimee(String timee) {
        this.timee = timee;
    }


    public ArrayList<HashMap> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<HashMap> friends) {
        this.friends = friends;
    }


    String timee;

    ArrayList<HashMap> friends=new ArrayList<>();

}
