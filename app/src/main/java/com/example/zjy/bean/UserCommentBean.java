package com.example.zjy.bean;

import java.util.List;

/**
 * Created by zjy on 2016/12/27.
 */

public class UserCommentBean {

    /**
     * status : 1
     * msg : 成功
     * ts : 1482828267
     * data : [{"id":"349390","conent":"最近种草纪梵希禁忌之吻了，在攒钱买。。。","datetime":"1482575385","datestr":"12月24日","user":{"user_id":"2456240","nickname":"郑好一个玥💨","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/45/62/40.jpg?v=3","is_official":0,"article_topic_count":"","post_count":""},"at_user":{}},{"id":"349169","conent":"好看！","datetime":"1482503734","datestr":"12月23日","user":{"user_id":"413935","nickname":"\u201cSimpliFy","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/000/41/39/35.jpg?v=17","is_official":0,"article_topic_count":"","post_count":""},"at_user":{}},{"id":"349160","conent":"po主嘴真好看😳皮肤也白","datetime":"1482501067","datestr":"12月23日","user":{"user_id":"2573049","nickname":"白酒","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/57/30/49.jpg?v=2","is_official":0,"article_topic_count":"","post_count":""},"at_user":{}},{"id":"349060","conent":"嘴好漂酿！！","datetime":"1482477483","datestr":"12月23日","user":{"user_id":"2415349","nickname":"白瘦子☀","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/41/53/49.jpg","is_official":0,"article_topic_count":"","post_count":""},"at_user":{}},{"id":"349059","conent":"种草娇兰，po主试色棒！","datetime":"1482477456","datestr":"12月23日","user":{"user_id":"272050","nickname":"打倒老亚","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/000/27/20/50.jpg","is_official":0,"article_topic_count":"","post_count":""},"at_user":{}}]
     */

    private String status;
    private String msg;
    private int ts;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "UserCommentBean{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", ts=" + ts +
                ", data=" + data +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public int getTs() {
        return ts;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        /**
         * id : 349390
         * conent : 最近种草纪梵希禁忌之吻了，在攒钱买。。。
         * datetime : 1482575385
         * datestr : 12月24日
         * user : {"user_id":"2456240","nickname":"郑好一个玥💨","avatar":"http://7te7t9.com2.z0.glb.qiniucdn.com/002/45/62/40.jpg?v=3","is_official":0,"article_topic_count":"","post_count":""}
         * at_user : {}
         */

        private String id;
        private String conent;
        private String datetime;
        private String datestr;
        private UserBean user;

        public void setId(String id) {
            this.id = id;
        }

        public void setConent(String conent) {
            this.conent = conent;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public void setDatestr(String datestr) {
            this.datestr = datestr;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getId() {
            return id;
        }

        public String getConent() {
            return conent;
        }

        public String getDatetime() {
            return datetime;
        }

        public String getDatestr() {
            return datestr;
        }

        public UserBean getUser() {
            return user;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", conent='" + conent + '\'' +
                    ", datetime='" + datetime + '\'' +
                    ", datestr='" + datestr + '\'' +
                    ", user=" + user +
                    '}';
        }

        public static class UserBean {
            /**
             * user_id : 2456240
             * nickname : 郑好一个玥💨
             * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/002/45/62/40.jpg?v=3
             * is_official : 0
             * article_topic_count :
             * post_count :
             */

            private String user_id;
            private String nickname;
            private String avatar;
            private int is_official;
            private String article_topic_count;
            private String post_count;

            @Override
            public String toString() {
                return "UserBean{" +
                        "user_id='" + user_id + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", is_official=" + is_official +
                        ", article_topic_count='" + article_topic_count + '\'' +
                        ", post_count='" + post_count + '\'' +
                        '}';
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setIs_official(int is_official) {
                this.is_official = is_official;
            }

            public void setArticle_topic_count(String article_topic_count) {
                this.article_topic_count = article_topic_count;
            }

            public void setPost_count(String post_count) {
                this.post_count = post_count;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getNickname() {
                return nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public int getIs_official() {
                return is_official;
            }

            public String getArticle_topic_count() {
                return article_topic_count;
            }

            public String getPost_count() {
                return post_count;
            }
        }
    }
}
