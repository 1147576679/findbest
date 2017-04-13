package com.example.zjy.fragment.message.bean.dto;

import com.example.zjy.fragment.community.bean.Transform;
import com.example.zjy.fragment.message.bean.PushVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2017/4/5.
 */

public class PushDto implements Transform<List<PushVo>>{
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public List<PushVo> transform() {
        List<PushVo> pushVoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ListBean listBean = list.get(i);
            PushVo pushVo = new PushVo();
            pushVo.id = listBean.getId();
            pushVo.extend = listBean.getExtend();
            pushVo.ivUrl = listBean.getPic();
            pushVo.dataStr = listBean.getDatestr();
            pushVo.title = listBean.getTitle();
            pushVo.type = listBean.getType();
            pushVoList.add(pushVo);
        }
        return pushVoList;
    }

    public static class ListBean {

        /**
         * id : 508729584
         * title : 8款超级显瘦显身材的运动女装推荐
         * type : topic_detail
         * extend : 1606
         * browser_url :
         * pic : http://bt.img.17gwx.com/topic/0/16/c1deYg
         * datestr : 2小时前
         * dateline : 1491365253
         * topic_type :
         */

        private String id;
        private String title;
        private String type;
        private String extend;
        private String browser_url;
        private String pic;
        private String datestr;
        private String dateline;
        private String topic_type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExtend() {
            return extend;
        }

        public void setExtend(String extend) {
            this.extend = extend;
        }

        public String getBrowser_url() {
            return browser_url;
        }

        public void setBrowser_url(String browser_url) {
            this.browser_url = browser_url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDatestr() {
            return datestr;
        }

        public void setDatestr(String datestr) {
            this.datestr = datestr;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getTopic_type() {
            return topic_type;
        }

        public void setTopic_type(String topic_type) {
            this.topic_type = topic_type;
        }
    }
}
