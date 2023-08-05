package com.example.july2023.model;

/*
{
    "msgid":123,
    "msg":"TESTING POST MESSAGES"
}
 */
public class Message {

    private int msgid;
    private String msg;

    public int getMsgid() {
        return msgid;
    }

    public void setMsgid(int msgid) {
        this.msgid = msgid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
