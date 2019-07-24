package bean;

/**
 * 好友的JavaBean类
 *id1：发送者的id
 * id2:接收者的id
 * name1:发送者的名字
 * name2:接收者的名字
 * sendTime:消息发送的时间
 * message:发送的消息
 * result:好友添加请求的结果
 *
 */
public class Friend {
    private int id1;
    private int id2;
    private int result;
    private String name1;
    private String name2;
    private String sendTime;
    private String message;

    public Friend(int id1, int id2, int result, String sendTime) {
        this.id1 = id1;
        this.id2 = id2;
        this.result = result;
        this.sendTime = sendTime;
    }

    public Friend(int id1, int id2, int result, String name1, String name2, String sendTime) {
        this.id1 = id1;
        this.id2 = id2;
        this.result = result;
        this.name1 = name1;
        this.name2 = name2;
        this.sendTime = sendTime;
    }

    public Friend(int id1, int id2, String message) {
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
    }

    public Friend(int id1, int id2, int result) {
        this.id1 = id1;
        this.id2 = id2;
        this.result = result;
    }

    public Friend(int id1, int id2) {
        this.id1 = id1;
        this.id2 = id2;
    }


    public Friend() {
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Friend) {
            return (id1 == ((Friend) obj).id1 && id2 == ((Friend) obj).id2)
                    || (id1 == ((Friend) obj).id2 && id2 == ((Friend) obj).id1);
        }
        return false;
    }
}
