package com.nic.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nic on 2017/5/27.
 * 查询结果返回集
 */
public class Resultmap implements Serializable {
    private static final long serialVersionUID = 3947120621825708087L;
    private String address;
    private Date time;
    private String name;
    private String note;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
