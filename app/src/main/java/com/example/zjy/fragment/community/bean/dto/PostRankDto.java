package com.example.zjy.fragment.community.bean.dto;

import com.example.zjy.fragment.community.bean.PostRankVo;
import com.example.zjy.fragment.community.bean.Transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2017/4/5.
 */

public class PostRankDto  implements Transform<List<PostRankVo>>{

    private List<PostBean> post;

    public List<PostBean> getPost() {
        return post;
    }

    public void setPost(List<PostBean> post) {
        this.post = post;
    }

    @Override
    public List<PostRankVo> transform() {
        List<PostRankVo> postRankVoList = new ArrayList<>();
        for (int i = 0; i < post.size(); i++) {
            PostRankVo postRankVo = new PostRankVo();
            PostBean postBean = post.get(i);
            postRankVo.id = postBean.getId();
            postRankVo.username = postBean.getUser().getNickname();
            postRankVo.avatar = postBean.getUser().getAvatar();
            postRankVo.content = postBean.getContent();
            postRankVo.publish_time = postBean.getDatestr();
            postRankVo.ivUrl = postBean.getPics().get(0).getUrl();
            postRankVo.views = postBean.getDynamic().getViews();
            postRankVo.shareUrl = postBean.getShare_url();
            postRankVoList.add(postRankVo);
        }
        return postRankVoList;
    }

    public static class PostBean {

        /**
         * id : 142489
         * content : 强势安利你们一款身体乳！自己用了好就忍不住分享出来😚 暑假去了趟台湾玩，去海边🍹那次 单纯的我以为抹上厚厚的防晒霜就万无一失了 没撑伞🌂，妈蛋！！！结果被晒伤了！！！ 当天回酒店胳膊就轻微掉皮，皮肤发红，我拼命敷芦荟胶💦，虽然缓解了泛红，接下来晒印却越来越明显😭 后来在药妆店扫货，可爱的台妹导购就跟我推荐了这个森田身体乳，说美白效果很好👊 抱着试试看的心理就买了。回家后我断断续续抹了有一个多月吧，发现脖子的晒印慢慢在淡化，短裤印也消了很多 好久不见的人都说我白回来了！真觉得太神了啊👍！！！ 而且这一款身体乳吸收很快 夏天用觉得非常舒服😤😤打算万年囤货了💪 这一瓶足足有400ml 够用好久好久了😜
         * datestr : 08月23日
         * post_type : 1
         * middle_pic_url : http://pic1.bantangapp.com/post/201608/22/52569810_1156889_1.jpg/w450
         * share_url : http://m.ibantang.com/post/142489/
         * is_recommend : true
         * pics : [{"url":"http://pic1.bantangapp.com/post/201608/22/52569810_1156889_1.jpg/w600"},{"url":"http://pic1.bantangapp.com/post/201608/22/53495110_1156889_2.jpg/w600"}]
         * dynamic : {"ispraise":false,"likes":"11947","praises":"12281","comments":"59","views":"1444"}
         * user : {"user_id":"1156889","nickname":"Mr Frank","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/001/15/68/89.jpg","is_official":0,"article_topic_count":"","post_count":"1","attention_type":0}
         * brand_product : []
         * comments : [{"id":"332218","conent":"没什么美白的作用，但是滋润效果还是可以","datetime":"1479352830","datestr":"11月17日","user":{"user_id":"2181992","nickname":"文雨文","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/18/19/92.jpg","is_official":0,"article_topic_count":"","post_count":""},"at_comment":{},"product":{}},{"id":"326631","conent":"很香的味道！","datetime":"1478256934","datestr":"11月04日","user":{"user_id":"2709405","nickname":"188****70","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/70/94/05.jpg","is_official":0,"article_topic_count":"","post_count":""},"at_comment":{},"product":{}},{"id":"324452","conent":"可以美白吗","datetime":"1477818862","datestr":"10月30日","user":{"user_id":"2702157","nickname":"女神💝","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/70/21/57.jpg?v=4","is_official":0,"article_topic_count":"","post_count":""},"at_comment":{},"product":{}}]
         * relation_activity : {}
         */

        private String id;
        private String content;
        private String datestr;
        private String post_type;
        private String middle_pic_url;
        private String share_url;
        private boolean is_recommend;
        /**
         * ispraise : false
         * likes : 11947
         * praises : 12281
         * comments : 59
         * views : 1444
         */

        private DynamicBean dynamic;
        /**
         * user_id : 1156889
         * nickname : Mr Frank
         * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/001/15/68/89.jpg
         * is_official : 0
         * article_topic_count :
         * post_count : 1
         * attention_type : 0
         */

        private UserBean user;
        /**
         * url : http://pic1.bantangapp.com/post/201608/22/52569810_1156889_1.jpg/w600
         */

        private List<PicsBean> pics;
        private List<?> brand_product;
        /**
         * id : 332218
         * conent : 没什么美白的作用，但是滋润效果还是可以
         * datetime : 1479352830
         * datestr : 11月17日
         * user : {"user_id":"2181992","nickname":"文雨文","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/18/19/92.jpg","is_official":0,"article_topic_count":"","post_count":""}
         * at_comment : {}
         * product : {}
         */

        private List<CommentsBean> comments;

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

        public List<?> getBrand_product() {
            return brand_product;
        }

        public void setBrand_product(List<?> brand_product) {
            this.brand_product = brand_product;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class DynamicBean {
            private boolean ispraise;
            private String likes;
            private String praises;
            private String comments;
            private String views;

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

        public static class CommentsBean {
            private String id;
            private String conent;
            private String datetime;
            private String datestr;
            /**
             * user_id : 2181992
             * nickname : 文雨文
             * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/002/18/19/92.jpg
             * is_official : 0
             * article_topic_count :
             * post_count :
             */

            private UserBean user;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getConent() {
                return conent;
            }

            public void setConent(String conent) {
                this.conent = conent;
            }

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
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
        }
    }
}
