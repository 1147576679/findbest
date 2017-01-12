package com.example.zjy.bean;

/**
 * Created by zjy on 2017/1/1.
 */

public class SearchPostBean {

    /**
     * id : 150162
     * content : 冲着复古亮骚的外形买的x100t，果然没让我失望，拿出去扫街回头率满满。女朋友也非常喜欢，说这个相机好漂亮啊，完全不像以前那些大块头那么丑。。。而且拍出来的照片都很清楚，aps-c画幅的感光元件，尺寸够大，镜头转换到135画幅就是35mm的焦段，真是人文利器，希望可以用它出好多大片！买来就迫不及待的给它配了个红色的快门按钮帽，红配黑，就是帅！
     * datestr : 12月02日
     * mini_pic_url : http://pic1.bantangapp.com/post/201611/22/10256102_568563_1.jpg/w200
     * middle_pic_url : http://pic1.bantangapp.com/post/201611/22/10256102_568563_1.jpg/w450
     * dynamic : {"iscollect":false,"likes":"7","praises":"139","comments":"3","views":"490"}
     * user : {"user_id":"568563","nickname":"阿咩","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/000/56/85/63.jpg","is_official":0,"article_topic_count":"","post_count":"7","attention_type":0}
     */

    private String id;
    private String content;
    private String datestr;
    private String mini_pic_url;
    private String middle_pic_url;
    /**
     * iscollect : false
     * likes : 7
     * praises : 139
     * comments : 3
     * views : 490
     */

    private DynamicBean dynamic;
    /**
     * user_id : 568563
     * nickname : 阿咩
     * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/000/56/85/63.jpg
     * is_official : 0
     * article_topic_count :
     * post_count : 7
     * attention_type : 0
     */

    private UserBean user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatestr() {
        return datestr;
    }

    public void setDatestr(String datestr) {
        this.datestr = datestr;
    }

    public String getMini_pic_url() {
        return mini_pic_url;
    }

    public void setMini_pic_url(String mini_pic_url) {
        this.mini_pic_url = mini_pic_url;
    }

    public String getMiddle_pic_url() {
        return middle_pic_url;
    }

    public void setMiddle_pic_url(String middle_pic_url) {
        this.middle_pic_url = middle_pic_url;
    }

    public DynamicBean getDynamic() {
        return dynamic;
    }

    public void setDynamic(DynamicBean dynamic) {
        this.dynamic = dynamic;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class DynamicBean {
        private boolean iscollect;
        private String likes;
        private String praises;
        private String comments;
        private String views;

        public boolean isIscollect() {
            return iscollect;
        }

        public void setIscollect(boolean iscollect) {
            this.iscollect = iscollect;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getPraises() {
            return praises;
        }

        public void setPraises(String praises) {
            this.praises = praises;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }
    }

    public static class UserBean {
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
}
