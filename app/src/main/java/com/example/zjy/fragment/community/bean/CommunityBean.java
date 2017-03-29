package com.example.zjy.fragment.community.bean;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2017/3/14.
 */

public class CommunityBean implements Transform<List<CommunityVo>> {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public List<CommunityVo> transform() {
        List<CommunityVo> communityVos = new ArrayList<>();
        Log.i("tag", "transform: "+list);
        for (ListBean data : list) {
            CommunityVo communityVo = new CommunityVo();
            communityVo.content = (data.getPost() == null ? data.getTopic().getTitle() : data.getPost().getContent());
            communityVo.username = (data.getPost() == null ? data.getTopic().getUser().getNickname() : data.getPost().getUser().getNickname());
            communityVo.avatar = (data.getPost() == null ? data.getTopic().getUser().getAvatar() : data.getPost().getUser().getAvatar());
            communityVo.ivUrl = (data.getPost() == null ? data.getTopic().getPics().get(0).getUrl() : data.getPost().getPics().get(0).getUrl());
            communityVo.publish_time = (data.getPost() == null ? data.getTopic().getDatestr() : data.getPost().getDatestr());
            communityVo.shareUrl = (data.getPost() == null ? data.getTopic().getShare_url(): data.getPost().getShare_url());
            communityVo.views = (data.getPost() == null ? data.getTopic().getViews() : data.getPost().getDynamic().getViews());
            communityVos.add(communityVo);
        }
        return communityVos;
    }

    public static class ListBean {


        /**
         * id : 14305
         * type_id : 4
         * post : {"id":"159130","content":"uha味觉糖～被微博种草的～一次买零食就顺带买了～ 怎么说呢？感觉味道好是不错的～但是有点甜～是越吃越好吃的东西～ 就是和葡萄的口感差别有点多\u2026","datestr":"4小时前","post_type":"1","middle_pic_url":"http://pic1.bantangapp.com/post/201703/16/995456976263_261890_1.jpg/w450","share_url":"http://m.ibantang.com/post/159130/","is_recommend":false,"pics":[{"url":"http://pic1.bantangapp.com/post/201703/16/995456976263_261890_1.jpg/w600"}],"dynamic":{"iscollect":false,"ispraise":false,"likes":"","praises":"","comments":"","views":"25"},"user":{"user_id":"261890","nickname":"Love-song-forever","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/000/26/18/90.jpg","is_official":0,"article_topic_count":"","post_count":"28","attention_type":0},"relation_activity":{"id":"78","title":"最爱那一抹绿","pic":"http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_pic/20170310/1750072619.jpg","content":"天气越来越暖，春意也越来越浓，在这个散发着无限生气的季节，一起晒一晒清新又充满生机的绿色好物吧！","activity_type":"2","users":"138","is_expired":false,"user_list":[],"cover":"http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_cover/20170310/1750077710.jpg"}}
         */

        private String id;
        private String type_id;
        /**
         * id : 159130
         * content : uha味觉糖～被微博种草的～一次买零食就顺带买了～ 怎么说呢？感觉味道好是不错的～但是有点甜～是越吃越好吃的东西～ 就是和葡萄的口感差别有点多…
         * datestr : 4小时前
         * post_type : 1
         * middle_pic_url : http://pic1.bantangapp.com/post/201703/16/995456976263_261890_1.jpg/w450
         * share_url : http://m.ibantang.com/post/159130/
         * is_recommend : false
         * pics : [{"url":"http://pic1.bantangapp.com/post/201703/16/995456976263_261890_1.jpg/w600"}]
         * dynamic : {"iscollect":false,"ispraise":false,"likes":"","praises":"","comments":"","views":"25"}
         * user : {"user_id":"261890","nickname":"Love-song-forever","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/000/26/18/90.jpg","is_official":0,"article_topic_count":"","post_count":"28","attention_type":0}
         * relation_activity : {"id":"78","title":"最爱那一抹绿","pic":"http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_pic/20170310/1750072619.jpg","content":"天气越来越暖，春意也越来越浓，在这个散发着无限生气的季节，一起晒一晒清新又充满生机的绿色好物吧！","activity_type":"2","users":"138","is_expired":false,"user_list":[],"cover":"http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_cover/20170310/1750077710.jpg"}
         */

        private PostBean post;
        private TopicBean topic;

        public TopicBean getTopic() {
            return topic;
        }

        public void setTopic(TopicBean topic) {
            this.topic = topic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public PostBean getPost() {
            return post;
        }

        public void setPost(PostBean post) {
            this.post = post;
        }


        public static class TopicBean {

            /**
             * id : 17387
             * type : 2
             * type_id : 1
             * title : 驴仔的口红分享第四弹💚
             * pic :
             * is_recommend : false
             * is_show_like : true
             * islike : false
             * ispraise : false
             * views : 111
             * praises : 19
             * likes : 12
             * comments : 5
             * items : 11
             * update_time : 1489651394
             * dateline : 1489651394
             * create_time_str : 7小时前
             * datestr : 6小时前
             * pics : [{"url":"http://pic1.bantangapp.com/topic/201703/16/52100501_413935_11.jpg/800x440"},{"url":"http://pic1.bantangapp.com/topic/201703/16/52100509_413935_1.jpg/800x440"},{"url":"http://pic1.bantangapp.com/topic/201703/16/52100509_413935_2.jpg/800x440"}]
             * user : {"user_id":"413935","nickname":"\u201cSimpliFy","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/000/41/39/35.jpg?v=17","is_official":0,"article_topic_count":"","post_count":"","attention_type":0}
             * share_url : http://m.ibantang.com/topic/17387/
             * share_pic : http://pic1.bantangapp.com/topic/201703/16/52100501_413935_11.jpg/800x440
             * relation_activity : {"id":"75","title":"开学新装备","pic":"http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_pic/20170306/1600306013.jpg","content":"新学期新气象，糖果们为新学期买了什么好物呢？赶紧拿出来晒晒吧！","activity_type":"1","users":"","is_expired":true,"user_list":[],"cover":"http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_cover/20170306/1600304194.jpg"}
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
             * user_id : 413935
             * nickname : “SimpliFy
             * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/000/41/39/35.jpg?v=17
             * is_official : 0
             * article_topic_count :
             * post_count :
             * attention_type : 0
             */

            private UserBean user;
            private String share_url;
            private String share_pic;
            /**
             * id : 75
             * title : 开学新装备
             * pic : http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_pic/20170306/1600306013.jpg
             * content : 新学期新气象，糖果们为新学期买了什么好物呢？赶紧拿出来晒晒吧！
             * activity_type : 1
             * users :
             * is_expired : true
             * user_list : []
             * cover : http://7xiwnz.com2.z0.glb.qiniucdn.com/activity_cover/20170306/1600304194.jpg
             */

            private RelationActivityBean relation_activity;
            /**
             * url : http://pic1.bantangapp.com/topic/201703/16/52100501_413935_11.jpg/800x440
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

            public RelationActivityBean getRelation_activity() {
                return relation_activity;
            }

            public void setRelation_activity(RelationActivityBean relation_activity) {
                this.relation_activity = relation_activity;
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

            public static class RelationActivityBean {
                private String id;
                private String title;
                private String pic;
                private String content;
                private String activity_type;
                private String users;
                private boolean is_expired;
                private String cover;
                private List<?> user_list;

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

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getActivity_type() {
                    return activity_type;
                }

                public void setActivity_type(String activity_type) {
                    this.activity_type = activity_type;
                }

                public String getUsers() {
                    return users;
                }

                public void setUsers(String users) {
                    this.users = users;
                }

                public boolean isIs_expired() {
                    return is_expired;
                }

                public void setIs_expired(boolean is_expired) {
                    this.is_expired = is_expired;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public List<?> getUser_list() {
                    return user_list;
                }

                public void setUser_list(List<?> user_list) {
                    this.user_list = user_list;
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

        public static class PostBean {

            /**
             * id : 159121
             * content : 入了美容花洒 开心 🔥📢最近很火的MTG OBLEU Microbubble 美容淋浴莲蓬头由一个花洒和一支胶原蛋白美容液组成，里面的美容液自己可以替换，结合使用能够在沐浴的时候给肌肤充分的滋润和按摩，花洒喷出的水将会释放约3300万个微细泡沫，通过微泡沫的力量深层清洁肌肤，于此同时，美肌精华水能深入毛孔，水润肌肤，照顾到你每一寸肌肤。
             * datestr : 6小时前
             * post_type : 1
             * middle_pic_url : http://pic1.bantangapp.com/post/201703/16/509753109727_513095_1.jpg/w450
             * share_url : http://m.ibantang.com/post/159121/
             * is_recommend : true
             * pics : [{"url":"http://pic1.bantangapp.com/post/201703/16/509753109727_513095_1.jpg/w600"}]
             * dynamic : {"iscollect":false,"ispraise":false,"likes":"","praises":"1","comments":"3","views":"16"}
             * user : {"user_id":"513095","nickname":"JeWeL小怪獸","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/000/51/30/95.jpg?v=6","is_official":0,"article_topic_count":"","post_count":"220","attention_type":0}
             */

            private String id;
            private String content;
            private String datestr;
            private String post_type;
            private String middle_pic_url;
            private String share_url;
            private boolean is_recommend;
            /**
             * iscollect : false
             * ispraise : false
             * likes :
             * praises : 1
             * comments : 3
             * views : 16
             */

            private DynamicBean dynamic;
            /**
             * user_id : 513095
             * nickname : JeWeL小怪獸
             * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/000/51/30/95.jpg?v=6
             * is_official : 0
             * article_topic_count :
             * post_count : 220
             * attention_type : 0
             */

            private UserBean user;
            /**
             * url : http://pic1.bantangapp.com/post/201703/16/509753109727_513095_1.jpg/w600
             */

            private List<PicsBean> pics;

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

            public String getPost_type() {
                return post_type;
            }

            public void setPost_type(String post_type) {
                this.post_type = post_type;
            }

            public String getMiddle_pic_url() {
                return middle_pic_url;
            }

            public void setMiddle_pic_url(String middle_pic_url) {
                this.middle_pic_url = middle_pic_url;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public boolean isIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(boolean is_recommend) {
                this.is_recommend = is_recommend;
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

            public List<PicsBean> getPics() {
                return pics;
            }

            public void setPics(List<PicsBean> pics) {
                this.pics = pics;
            }

            public static class DynamicBean {
                private boolean iscollect;
                private boolean ispraise;
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

                public boolean isIspraise() {
                    return ispraise;
                }

                public void setIspraise(boolean ispraise) {
                    this.ispraise = ispraise;
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
}
