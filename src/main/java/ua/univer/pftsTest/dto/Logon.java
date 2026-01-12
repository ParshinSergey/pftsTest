package ua.univer.pftsTest.dto;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "LOGON")
public class Logon {

    private String firmid;
    private String msg;
    private int sid;

    public Logon() {
    }

    @XmlAttribute(name = "firmid")
    public String getFirmid() {
        return firmid;
    }

    public void setFirmid(String firmid) {
        this.firmid = firmid;
    }

    @XmlAttribute(name = "msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @XmlAttribute(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Logon [firmid=" + firmid + ", msg=" + msg + ", sid=" + sid + "]";
    }
}



