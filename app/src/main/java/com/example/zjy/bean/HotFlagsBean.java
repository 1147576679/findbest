package com.example.zjy.bean;

import java.util.List;

/**
 * Created by zjy on 2016/12/29.
 */

public class HotFlagsBean {

    /**
     * status : 1
     * msg : ok
     * ts : 1482992742
     * data : ["手机壳","圣诞","圣诞礼物","礼物","保温杯","杯子","双肩包","充电宝","围巾","生日礼物","手表","加湿器","袜子","圣诞节","钱包"]
     */

    private int status;
    private String msg;
    private int ts;
    private List<String> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
