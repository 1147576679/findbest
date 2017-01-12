package com.example.zjy.bean;

/**
 * Created by zjy on 2017/1/1.
 */

public class UserBean {

    /**
     * user_id : 1089959
     * nickname : 弦外之音
     * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/001/08/99/59.jpg?v=8
     * is_official : 0
     * article_topic_count :
     * post_count : 222
     * attention_type : 0
     */

    private String user_id;
    private String nickname;
    private String avatar;
    private int is_official;
    private String article_topic_count;
    private String post_count;
    private int attention_type;

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

    public int getAttention_type() {
        return attention_type;
    }

    public void setAttention_type(int attention_type) {
        this.attention_type = attention_type;
    }
}
