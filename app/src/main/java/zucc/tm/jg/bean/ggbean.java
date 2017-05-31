package zucc.tm.jg.bean;

/**
 * Created by 45773 on 2017-05-29.
 */

public class ggbean {
    public ggbean(String con, String time,String id) {
        this.con = con;
        this.time = time;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    public ggbean() {
    }
    String con;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    String time;
}
