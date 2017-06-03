package com.nic.pojo;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nic on 2017/5/23.
 * event
 */
@ExcelTarget(value = "event")
public class Event implements Serializable {
    private static final long serialVersionUID = 6372587065040802279L;
    private int seqence;
    @Excel(name="故障类型")
    private int type;
    @Excel(name="设备类型")
    private int device;
    @Excel(name="code")
    private int code;
    private String description;
    @Excel(name="故障时间")
    private Date time;

    public int getSeqence() {
        return seqence;
    }

    public void setSeqence(int seqence) {
        this.seqence = seqence;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
