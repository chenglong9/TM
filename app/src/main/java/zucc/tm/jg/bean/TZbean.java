package zucc.tm.jg.bean;

import java.util.Date;

/**
 * Created by 王泽豪 on 2017/5/31.
 */

public class TZbean {
    private int notice_id;
    private String project_id;
    private String project;
    private String member_id;
    private String type;
    private String words;
    private String nt_time;

    public TZbean(int notice_id, String project_id, String member_id, String type, String words, String nt_time) {
        this.notice_id = notice_id;
        this.project = project_id;
        this.member_id = member_id;
        this.type = type;
        this.words = words;
        this.nt_time = nt_time;
    }

    public TZbean() {
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getNt_time() {
        return nt_time;
    }

    public void setNt_time(String nt_time) {
        this.nt_time = nt_time;
    }
}
