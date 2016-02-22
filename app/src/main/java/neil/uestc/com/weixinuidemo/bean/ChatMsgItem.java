package neil.uestc.com.weixinuidemo.bean;

/**
 * Created by Neil on 2016/2/22.
 */
public class ChatMsgItem {
    /**
     * 0表示发送，1表示接收
     */
    private int msgType;
    private String msg;

    public ChatMsgItem(int isSent, String msg) {
        this.msgType = isSent;
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return this.getMsg();
    }
}
