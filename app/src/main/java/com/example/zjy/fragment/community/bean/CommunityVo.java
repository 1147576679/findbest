package com.example.zjy.fragment.community.bean;

/**
 * Created by zjy on 2017/3/20.
 */

public class CommunityVo {
    public String content;
    public String username;
    public String avatar;
    public String ivUrl;
    public String publish_time;
    public String views;
    public String shareUrl;
    public int flag;
    public String id;

    @Override
    public String toString() {
        return "CommunityVo{" +
                "content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", ivUrl='" + ivUrl + '\'' +
                ", publish_time='" + publish_time + '\'' +
                ", views='" + views + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                '}';
    }

}
