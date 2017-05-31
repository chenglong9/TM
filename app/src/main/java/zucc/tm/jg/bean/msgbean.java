package zucc.tm.jg.bean;

/**
 * Created by 45773 on 2017-05-30.
 */

public class msgbean {
    String id;

    public msgbean(String id, String name, String msg, String time) {
        this.id = id;
        this.name = name;
        this.msg = msg;
        this.time = time;
    }

    public msgbean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String name;
    String msg;
    String time;
}
