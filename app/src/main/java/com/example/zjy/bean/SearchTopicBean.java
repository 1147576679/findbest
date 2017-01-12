package com.example.zjy.bean;

import java.util.List;

/**
 * Created by zjy on 2016/12/31.
 * 关键字搜索 文章的Fragment的实体类
 */

public class SearchTopicBean {
    /**
     * id : 7996
     * type : 2
     * type_id : 1
     * title : 逼格瞬间爆表——富士X100T开箱感言
     * pic : http://pic1.bantangapp.com/topic/201608/05/97985110_2484904_1.jpg/800x440
     * is_recommend : true
     * is_show_like : true
     * islike : false
     * ispraise : false
     * views : 36587
     * praises : 37
     * likes : 34
     * comments : 47
     * items : 1
     * update_time : 1470392071
     * dateline : 1470392071
     * create_time_str : 08月02日
     * datestr : 08月05日
     * pics : [{"url":"http://pic1.bantangapp.com/topic/201608/02/web_1470127185179_93101_95976/800x440"},{"url":"http://pic1.bantangapp.com/topic/201608/02/web_1470127279934_57909_11200/800x440"},{"url":"http://pic1.bantangapp.com/topic/201608/02/web_1470127290990_51092_73756/800x440"}]
     * user : {"user_id":"2484904","nickname":"灰色虎纹猫","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/48/49/04.jpg","is_official":0,"article_topic_count":"","post_count":""}
     */

    private String id;
    private String type;
    private String type_id;
    private String title;
    private String pic;
    private boolean is_recommend;
    private boolean is_show_like;
    private boolean islike;
    private boolean ispraise;
    private String views;
    private String praises;
    private String likes;
    private String comments;
    private String items;
    private String update_time;
    private String dateline;
    private String create_time_str;
    private String datestr;
    /**
     * user_id : 2484904
     * nickname : 灰色虎纹猫
     * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/002/48/49/04.jpg
     * is_official : 0
     * article_topic_count :
     * post_count :
     */

    private UserBean user;
    /**
     * url : http://pic1.bantangapp.com/topic/201608/02/web_1470127185179_93101_95976/800x440
     */

    private List<PicsBean> pics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(boolean is_recommend) {
        this.is_recommend = is_recommend;
    }

    public boolean isIs_show_like() {
        return is_show_like;
    }

    public void setIs_show_like(boolean is_show_like) {
        this.is_show_like = is_show_like;
    }

    public boolean isIslike() {
        return islike;
    }

    public void setIslike(boolean islike) {
        this.islike = islike;
    }

    public boolean isIspraise() {
        return ispraise;
    }

    public void setIspraise(boolean ispraise) {
        this.ispraise = ispraise;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPraises() {
        return praises;
    }

    public void setPraises(String praises) {
        this.praises = praises;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getCreate_time_str() {
        return create_time_str;
    }

    public void setCreate_time_str(String create_time_str) {
        this.create_time_str = create_time_str;
    }

    public String getDatestr() {
        return datestr;
    }

    public void setDatestr(String datestr) {
        this.datestr = datestr;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<PicsBean> getPics() {
        return pics;
    }

    public void setPics(List<PicsBean> pics) {
        this.pics = pics;
    }

    public static class UserBean {
        private String user_id;
        private String nickname;
        private String avatar;
        private int is_official;
        private String article_topic_count;
        private String post_count;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getIs_official() {
            return is_official;
        }

        public void setIs_official(int is_official) {
            this.is_official = is_official;
        }

        public String getArticle_topic_count() {
            return article_topic_count;
        }

        public void setArticle_topic_count(String article_topic_count) {
            this.article_topic_count = article_topic_count;
        }

        public String getPost_count() {
            return post_count;
        }

        public void setPost_count(String post_count) {
            this.post_count = post_count;
        }
    }

    public static class PicsBean {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
