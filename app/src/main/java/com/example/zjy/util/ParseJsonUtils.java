package com.example.zjy.util;

import com.example.zjy.bean.HeadAndTabBean;
import com.example.zjy.bean.HomeContentBean;
import com.example.zjy.bean.IdsBean;
import com.example.zjy.bean.ItemDetailBean;
import com.example.zjy.bean.SearchArticleBean;
import com.example.zjy.bean.SearchPostBean;
import com.example.zjy.bean.SearchProductBean;
import com.example.zjy.bean.SearchSingleBean;
import com.example.zjy.bean.SearchSubClassBean;
import com.example.zjy.bean.SearchTopicBean;
import com.example.zjy.bean.UserBean;
import com.example.zjy.bean.UserCommentBean;
import com.example.zjy.bean.WebviewBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zjy on 2016/12/17.
 */

public class ParseJsonUtils {
    public static HeadAndTabBean parse(String json){
        return new Gson().fromJson(json,HeadAndTabBean.class);
    }

    /**
     * 头部广告点击详情url解析
     */

    public static List<IdsBean> parseIds(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONObject("data").getJSONArray("topic");
            TypeToken<List<IdsBean>> typeToken = new TypeToken<List<IdsBean>>(){};
            return new Gson().fromJson(jsonArray.toString(),typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析主页内容的数据
     * @param json
     * @return
     */
    public static List<HomeContentBean> parseContent(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONObject("data").getJSONArray("topic");
            TypeToken<List<HomeContentBean>> typeToken = new TypeToken<List<HomeContentBean>>(){};
            return new Gson().fromJson(jsonArray.toString(),typeToken.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析wenview的url
     */

    public static WebviewBean parseWebViewUrl(String json){
        return new Gson().fromJson(json,WebviewBean.class);
    }

    /**
     * 解析item详情的json
     */

    public static ItemDetailBean parseItemDetail(String json){
        return new Gson().fromJson(json,ItemDetailBean.class);
    }

    /**
     * 解析用户评论
     */

    public static UserCommentBean parseUserComment(String json){
        return new Gson().fromJson(json,UserCommentBean.class);
    }

    /**
     * 搜索单品url解析
     */

    public static SearchSingleBean parseSearchSingle(String json){
        return new Gson().fromJson(json,SearchSingleBean.class);
    }

    /**
     * 搜索文章url解析
     */
    public  static List<SearchArticleBean> parseSearchArticle(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<SearchArticleBean>> typeToken = new TypeToken<List<SearchArticleBean>>(){};
            return new Gson().fromJson(jsonArray.toString(),typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析热门标签
     */

    public static List<String> parseHotTag(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<String>> typeToken = new TypeToken<List<String>>(){};
            return new Gson().fromJson(jsonArray.toString(),typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 搜索产品SubClassInfo子标签解析
     */
    public static SearchSubClassBean parseSearchSubClass(String json){
        return new Gson().fromJson(json,SearchSubClassBean.class);
    }

    /**
     * 搜索产品SearchProduct解析
     */
    public static SearchProductBean parseSearchProduct(String json){
        return new Gson().fromJson(json,SearchProductBean.class);
    }


    /**
     * 关键字搜索文章解析
     */

    public static List<SearchTopicBean> parseSearchTopic(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<SearchTopicBean>> typeToken = new TypeToken<List<SearchTopicBean>>(){};
            return new Gson().fromJson(jsonArray.toString(),typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 关键字搜索晒单解析
     */

    public static List<SearchPostBean> parseSearchPost(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<SearchPostBean>> typeToken = new TypeToken<List<SearchPostBean>>(){};
            return new Gson().fromJson(jsonArray.toString(),typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关键字搜索用户解析
     */

    public static List<UserBean> parseSearchUser(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<UserBean>> typeToken = new TypeToken<List<UserBean>>(){};
            return new Gson().fromJson(jsonArray.toString(),typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
