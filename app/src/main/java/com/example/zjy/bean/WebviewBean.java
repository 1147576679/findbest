package com.example.zjy.bean;

/**
 * Created by zjy on 2016/12/24.
 */

public class WebviewBean {

    private DataBean data;

    public void setData(DataBean data) {
        this.data = data;
    }

    public DataBean getData() {
        return data;
    }

    public static class DataBean {
        private String share_url;

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getShare_url() {
            return share_url;
        }
    }
}
