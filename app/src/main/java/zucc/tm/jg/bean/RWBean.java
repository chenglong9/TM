package zucc.tm.jg.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by 王泽豪 on 2017/5/29.
 */

public class RWBean {
    private String stage_id;
    private String project_id;
    private String project_name;
    private String describes;
    private String start_time;
    private String end_time;
    private String types;
    private String person_in_charge;
    private String tx_time;
    private String tx_method;

    ArrayList<HashMap> friends=new ArrayList<>();

    public ArrayList<HashMap> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<HashMap> friends) {
        this.friends = friends;
    }

    public RWBean(){}

    public RWBean(String stage_id, String project_id, String project_name, String describes, String start_time, String end_time, String types, String person_in_charge, String tx_time, String tx_method) {
        this.stage_id = stage_id;
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

    public String getTx_method() {
        return tx_method;
    }

    public void setTx_method(String tx_method) {
        this.tx_method = tx_method;
    }

    public String getTx_time() {
        return tx_time;
    }

    public void setTx_time(String tx_time) {
        this.tx_time = tx_time;
    }

    public String getStage_id() {
        return stage_id;
    }

    public void setStage_id(String stage_id) {
        this.stage_id = stage_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getPerson_in_charge() {
        return person_in_charge;
    }

    public void setPerson_in_charge(String person_in_charge) {
        this.person_in_charge = person_in_charge;
    }
}
