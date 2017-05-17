package com.example.zjy.fragment.community.bean.dto;

import com.example.zjy.fragment.community.bean.CommunityPostVO;
import com.example.zjy.fragment.community.bean.Transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2017/4/1.
 */

public class CommunityPostDTO implements Transform<CommunityPostVO>{

    /**
     * status : 1
     * msg : 成功
     * ts : 1491027253
     * data : {"post":{"id":"160162","content":"海蓝之谜💐精粹液保湿舒缓紧致 美白水提亮肤色 粉水补水适合年轻肌肤✌️贵妇级别的护肤品 正装1000➕ 小样只要几十块 关于海蓝之谜这个品牌你们可以去微博做功课","datestr":"2小时前","post_type":"1","share_url":"http://m.ibantang.com/post/160162/","is_recommend":false,"status":1,"pics":[{"url":"http://pic1.bantangapp.com/post/201704/01/575257543725_2855620_1.jpg/w600"}],"dynamic":{"iscollect":false,"ispraise":false,"likes":"","praises":"","comments":"","views":"36"},"user":{"user_id":"2855620","nickname":"口红控","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/85/56/20.jpg?v=2","is_official":0,"article_topic_count":"1","post_count":"7","attention_type":0},"brand_product":[],"comments":[],"relation_activity":{}}}
     */

    private String status;
    private String msg;
    private int ts;
    /**
     * post : {"id":"160162","content":"海蓝之谜💐精粹液保湿舒缓紧致 美白水提亮肤色 粉水补水适合年轻肌肤✌️贵妇级别的护肤品 正装1000➕ 小样只要几十块 关于海蓝之谜这个品牌你们可以去微博做功课","datestr":"2小时前","post_type":"1","share_url":"http://m.ibantang.com/post/160162/","is_recommend":false,"status":1,"pics":[{"url":"http://pic1.bantangapp.com/post/201704/01/575257543725_2855620_1.jpg/w600"}],"dynamic":{"iscollect":false,"ispraise":false,"likes":"","praises":"","comments":"","views":"36"},"user":{"user_id":"2855620","nickname":"口红控","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/85/56/20.jpg?v=2","is_official":0,"article_topic_count":"1","post_count":"7","attention_type":0},"brand_product":[],"comments":[],"relation_activity":{}}
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

    @Override
    public CommunityPostVO transform() {
        CommunityPostVO communityPostVO = new CommunityPostVO();
        communityPostVO.ivUrl = new ArrayList<>();
        List<DataBean.PostBean.PicsBean> pics = data.getPost().getPics();
        for (DataBean.PostBean.PicsBean pic : pics) {
            communityPostVO.ivUrl.add(pic.getUrl());
        }
        communityPostVO.avatar = data.getPost().getUser().getAvatar();
        communityPostVO.content = data.getPost().getContent();
        communityPostVO.praises = data.getPost().getDynamic().getPraises();
        communityPostVO.username = data.getPost().getUser().getNickname();
        communityPostVO.dataStr = data.getPost().getDatestr();
        return communityPostVO;
    }

    public static class DataBean {
        /**
         * id : 160162
         * content : 海蓝之谜💐精粹液保湿舒缓紧致 美白水提亮肤色 粉水补水适合年轻肌肤✌️贵妇级别的护肤品 正装1000➕ 小样只要几十块 关于海蓝之谜这个品牌你们可以去微博做功课
         * datestr : 2小时前
         * post_type : 1
         * share_url : http://m.ibantang.com/post/160162/
         * is_recommend : false
         * status : 1
         * pics : [{"url":"http://pic1.bantangapp.com/post/201704/01/575257543725_2855620_1.jpg/w600"}]
         * dynamic : {"iscollect":false,"ispraise":false,"likes":"","praises":"","comments":"","views":"36"}
         * user : {"user_id":"2855620","nickname":"口红控","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/85/56/20.jpg?v=2","is_official":0,"article_topic_count":"1","post_count":"7","attention_type":0}
         * brand_product : []
         * comments : []
         * relation_activity : {}
         */

        private PostBean post;

        public PostBean getPost() {
            return post;
        }

        public void setPost(PostBean post) {
            this.post = post;
        }

        public static class PostBean {
            private String id;
            private String content;
            private String datestr;
            private String post_type;
            private String share_url;
            private boolean is_recommend;
            private int status;
            /**
             * iscollect : false
             * ispraise : false
             * likes :
             * praises :
             * comments :
             * views : 36
             */

            private DynamicBean dynamic;
            /**
             * user_id : 2855620
             * nickname : 口红控
             * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/002/85/56/20.jpg?v=2
             * is_official : 0
             * article_topic_count : 1
             * post_count : 7
             * attention_type : 0
             */

            private UserBean user;
            /**
             * url : http://pic1.bantangapp.com/post/201704/01/575257543725_2855620_1.jpg/w600
             */

            private List<PicsBean> pics;
            private List<?> brand_product;
            private List<?> comments;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public List<?> getComments() {
                return comments;
            }

            public void setComments(List<?> comments) {
                this.comments = comments;
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
