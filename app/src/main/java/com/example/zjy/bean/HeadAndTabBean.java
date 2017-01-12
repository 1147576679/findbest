package com.example.zjy.bean;

import java.util.List;

/**
 * Created by zjy on 2016/12/17.
 */

public class HeadAndTabBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1278
         * title : 有颜有品的圣诞礼物都在这儿
         * sub_title :
         * type : topic_list
         * topic_type :
         * photo : http://7xiwnz.com2.z0.glb.qiniucdn.com/element1/201612/10251101.jpg?v=1481885087
         * extend : 14676,13816,14541,14808,1709,14045,7412,14514
         * browser_url :
         * index : 207
         * parent_id : 1
         * photo_width : 900
         * photo_height : 432
         * is_show_icon : 0
         * topic : {}
         * activity : {}
         * subject : {}
         */

        private List<BannerBean> banner;
        /**
         * id : 99999999
         * title : 精选
         * sub_title :
         * type : topic_main
         * topic_type :
         * photo :
         * extend :
         * is_show_icon : 0
         */

        private List<CategoryElementBean> category_element;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryElementBean> getCategory_element() {
            return category_element;
        }

        public void setCategory_element(List<CategoryElementBean> category_element) {
            this.category_element = category_element;
        }

        public static class BannerBean {
            private String id;
            private String title;
            private String sub_title;
            private String type;
            private String topic_type;
            private String photo;
            private String extend;
            private String browser_url;
            private int index;
            private String parent_id;
            private String photo_width;
            private String photo_height;
            private int is_show_icon;
            private TopicBean topic;
            private ActivityBean activity;
            private SubjectBean subject;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTopic_type() {
                return topic_type;
            }

            public void setTopic_type(String topic_type) {
                this.topic_type = topic_type;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getExtend() {
                return extend;
            }

            public void setExtend(String extend) {
                this.extend = extend;
            }

            public String getBrowser_url() {
                return browser_url;
            }

            public void setBrowser_url(String browser_url) {
                this.browser_url = browser_url;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getPhoto_width() {
                return photo_width;
            }

            public void setPhoto_width(String photo_width) {
                this.photo_width = photo_width;
            }

            public String getPhoto_height() {
                return photo_height;
            }

            public void setPhoto_height(String photo_height) {
                this.photo_height = photo_height;
            }

            public int getIs_show_icon() {
                return is_show_icon;
            }

            public void setIs_show_icon(int is_show_icon) {
                this.is_show_icon = is_show_icon;
            }

            public TopicBean getTopic() {
                return topic;
            }

            public void setTopic(TopicBean topic) {
                this.topic = topic;
            }

            public ActivityBean getActivity() {
                return activity;
            }

            public void setActivity(ActivityBean activity) {
                this.activity = activity;
            }

            public SubjectBean getSubject() {
                return subject;
            }

            public void setSubject(SubjectBean subject) {
                this.subject = subject;
            }

            public static class TopicBean {
            }

            public static class ActivityBean {
            }

            public static class SubjectBean {
            }
        }

        public static class CategoryElementBean {
            private String id;
            private String title;
            private String sub_title;
            private String type;
            private String topic_type;
            private String photo;
            private String extend;
            private int is_show_icon;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTopic_type() {
                return topic_type;
            }

            public void setTopic_type(String topic_type) {
                this.topic_type = topic_type;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getExtend() {
                return extend;
            }

            public void setExtend(String extend) {
                this.extend = extend;
            }

            public int getIs_show_icon() {
                return is_show_icon;
            }

            public void setIs_show_icon(int is_show_icon) {
                this.is_show_icon = is_show_icon;
            }
        }
    }
}
