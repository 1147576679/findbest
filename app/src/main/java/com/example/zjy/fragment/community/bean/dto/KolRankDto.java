package com.example.zjy.fragment.community.bean.dto;

import com.example.zjy.fragment.community.bean.KolRankVo;
import com.example.zjy.fragment.community.bean.Transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2017/4/5.
 */

public class KolRankDto implements Transform<List<KolRankVo>> {

    private List<UserBean> user;

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    @Override
    public List<KolRankVo> transform() {
        List<KolRankVo> kolRankVoList = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            UserBean userBean = user.get(i);
            KolRankVo kolRankVo = new KolRankVo();
            kolRankVo.nickname = userBean.getNickname();
            kolRankVo.avatar = userBean.getAvatar();
            kolRankVo.rank = "NO."+(i+1);
            kolRankVo.rankCount = "总贡献值"+" "+userBean.getRank_count();
            kolRankVo.userId = userBean.getUser_id();

            kolRankVo.ivUrl = new ArrayList<>();
            kolRankVo.flag = new ArrayList<>();
            kolRankVo.id = new ArrayList<>();
            List<UserBean.PicsBean> pics = userBean.getPics();
            for (int i1 = 0; i1 < pics.size(); i1++) {
                kolRankVo.ivUrl.add(pics.get(i1).getUrl());
                int flags= (pics.get(i1).getObject_id() >100000) ? 2 : 1;
                kolRankVo.flag.add(flags);
                kolRankVo.id.add(pics.get(i1).getObject_id());
            }
            kolRankVoList.add(kolRankVo);
        }
        return kolRankVoList;
    }

    public static class UserBean {

        /**
         * user_id : 2197313
         * nickname : 不可逆的两只肉桂
         * avatar : http://7te7t9.com2.z0.glb.qiniucdn.com/002/19/73/13.jpg
         * is_official : 0
         * article_topic_count :
         * post_count :
         * attention_type : 0
         * pics : [{"url":"http://pic1.bantangapp.com/topic/201610/01/web_1475289151451_23937_711/w300","object_id":12051,"type_id":1},{"url":"http://pic1.bantangapp.com/topic/201609/25/web_1474782655722_87327_19421/w300","object_id":11804,"type_id":1},{"url":"http://pic1.bantangapp.com/topic/201609/24/web_1474712981529_13484_31771/w300","object_id":11779,"type_id":1}]
         * rank_count : 1240
         */

        private String user_id;
        private String nickname;
        private String avatar;
        private int is_official;
        private String article_topic_count;
        private String post_count;
        private int attention_type;
        private int rank_count;
        /**
         * url : http://pic1.bantangapp.com/topic/201610/01/web_1475289151451_23937_711/w300
         * object_id : 12051
         * type_id : 1
         */

        private List<PicsBean> pics;

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

        public int getRank_count() {
            return rank_count;
        }

        public void setRank_count(int rank_count) {
            this.rank_count = rank_count;
        }

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public static class PicsBean {
            private String url;
            private int object_id;
            private int type_id;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getObject_id() {
                return object_id;
            }

            public void setObject_id(int object_id) {
                this.object_id = object_id;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }
        }
    }
}
