package com.example.zjy.bean;

import java.util.List;

/**
 * Created by zjy on 2016/12/30.
 */

public class SearchSubClassBean {


    /**
     * status : 1
     * msg : 成功
     * ts : 1483083764
     * data : {"id":"122","pic":"http://7viiaj.com2.z0.glb.qiniucdn.com/newcategory/014/122.png?v=1478173312","name":"面部彩妆","class_list":[{"id":"114","name":"BB/CC霜"},{"id":"119","name":"粉底"},{"id":"120","name":"粉饼"},{"id":"118","name":"隔离/妆前乳"},{"id":"117","name":"气垫BB/CC霜"},{"id":"175","name":"腮红"},{"id":"121","name":"散粉/蜜粉"},{"id":"177","name":"修容"},{"id":"543","name":"喷雾"},{"id":"179","name":"高光"},{"id":"181","name":"遮瑕"},{"id":"550","name":"面妆套装"}]}
     */

    private String status;
    private String msg;
    private int ts;
    /**
     * id : 122
     * pic : http://7viiaj.com2.z0.glb.qiniucdn.com/newcategory/014/122.png?v=1478173312
     * name : 面部彩妆
     * class_list : [{"id":"114","name":"BB/CC霜"},{"id":"119","name":"粉底"},{"id":"120","name":"粉饼"},{"id":"118","name":"隔离/妆前乳"},{"id":"117","name":"气垫BB/CC霜"},{"id":"175","name":"腮红"},{"id":"121","name":"散粉/蜜粉"},{"id":"177","name":"修容"},{"id":"543","name":"喷雾"},{"id":"179","name":"高光"},{"id":"181","name":"遮瑕"},{"id":"550","name":"面妆套装"}]
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String pic;
        private String name;
        /**
         * id : 114
         * name : BB/CC霜
         */

        private List<ClassListBean> class_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }

        public static class ClassListBean {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
