package zucc.tm.jg.bean;

import java.util.Date;

/**
 * Message
 *
 * @author: Allen
 * @time: 2016/9/13 20:58
 */
public class MessageBean {
    public static final int RECEIVE = 0;
    public static final int SEND = 1;

    private int type;
    private String content;
    private Date msgDate;

    public MessageBean(String content, int type, Date date) {
        this.content = content;
        this.type = type;
        this.msgDate = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }
}
