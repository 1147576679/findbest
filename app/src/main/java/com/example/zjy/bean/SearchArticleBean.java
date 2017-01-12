package com.example.zjy.bean;

/**
 * Created by zjy on 2016/12/29.
 * 搜索分类界面文章的实体类
 */

public class SearchArticleBean {

    /**
     * id : 16
     * name : 设计感
     * en_name : Design
     * icon : http://7xiwnz.com2.z0.glb.qiniucdn.com/scene/201612/57100501.jpg
     */

    private String id;
    private String name;
    private String en_name;
    private String icon;

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

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
