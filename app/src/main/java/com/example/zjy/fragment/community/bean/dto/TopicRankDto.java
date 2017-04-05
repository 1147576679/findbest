package com.example.zjy.fragment.community.bean.dto;

import com.example.zjy.fragment.community.bean.TopicRankVo;
import com.example.zjy.fragment.community.bean.Transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2017/4/5.
 */

public class TopicRankDto implements Transform<List<TopicRankVo>> {
    private List<TopicBean> topic;

    public List<TopicBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    @Override
    public List<TopicRankVo> transform() {
        List<TopicRankVo> topicRankVoList = new ArrayList<>();
        for (int i = 0; i < topic.size(); i++) {
            TopicRankVo topicRankVo = new TopicRankVo();
            TopicBean topicBean = topic.get(i);
            topicRankVo.id = topicBean.getId();
            topicRankVo.content = topicBean.getTitle();
            topicRankVo.avatar = topicBean.getUser().getAvatar();
            topicRankVo.publish_time = topicBean.getCreate_time_str();
            topicRankVo.ivUrl= topicBean.getShare_pic();
            topicRankVo.shareUrl = topicBean.getShare_url();
            topicRankVo.username = topicBean.getUser().getNickname();
            topicRankVo.views = topicBean.getViews();
            topicRankVoList.add(topicRankVo);
        }
        return topicRankVoList;
    }

    public static class TopicBean {
        /**
         * id : 15796
         * type : 2
         * type_id : 1
         * title : 80都嫌贵！30款真·平价好物（护肤篇）
         * pic : http://pic1.bantangapp.com/topic/201701/17/54495457_2756389_1.jpg/800x440
         * is_recommend : true
         * is_show_like : true
         * islike : false
         * ispraise : false
         * views : 61648
         * praises : 26972
         * likes : 26585
         * comments : 100
         * items : 27
         * update_time : 1484651100
         * dateline : 1484651100
         * create_time_str : 01月12日
         * datestr : 01月17日
         * pics : [{"url":"http://pic1.bantangapp.com/topic/201701/12/web_1484220936920_44066_82901/800x440"},{"url":"http://pic1.bantangapp.com/topic/201701/13/web_1484288025011_26113_87507/800x440"},{"url":"http://pic1.bantangapp.com/topic/201701/13/web_1484288033565_5988_36764/800x440"}]
         * user : {"user_id":"2756389","nickname":"脸很大的喵酱","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/75/63/89.jpg?v=4","is_official":0,"article_topic_count":"","post_count":"","attention_type":0}
         * share_url : http://m.ibantang.com/topic/15796/
         * share_pic : http://pic1.bantangapp.com/topic/201701/17/54495457_2756389_1.jpg/800x440
         * relation_activity : {}
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
         * user_id : 2756389
         * nickname : 脸很大的喵酱
         * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/002/75/63/89.jpg?v=4
         * is_official : 0
         * article_topic_count :
         * post_count :
         * attention_type : 0
         */

        private UserBean user;
        private String share_url;
        private String share_pic;
        /**
         * url : http://pic1.bantangapp.com/topic/201701/12/web_1484220936920_44066_82901/800x440
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

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getShare_pic() {
            return share_pic;
        }

        public void setShare_pic(String share_pic) {
            this.share_pic = share_pic;
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
}
