package com.example.zjy.util;

/**
 * Created by zjy on 2016/12/17.
 */

public interface Constants {
    String[] TAB = {"单品","文章"};

    String[] KEYWORD_TAB = {"单品","文章","晒单","用户"};
    /**
     * 头部和标签
     */
    String URL_HEAD_TAB = "http://open4.bantangapp.com/recommend/operationElement?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19";
    /**
     * 头部广告点击详情url
     */
    String URL_BANNER_DETAIL = "http://open4.bantangapp.com/topics/topic/listByIds?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&ids=%s";

    /**
     * 精选和原创 有分页
     */
    String URL_JINXUAN_YUANCHUANG = "http://open4.bantangapp.com/recommend/index?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&page=%d&pagesize=20";

    /**
     * 热门 没有分页
     */
    String URL_HOT = "http://open4.bantangapp.com/topics/topic/listByIds?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&ids=%s";

    /**
     * 美穿以及其他 有分页
     */
    String URL_OTHER = "http://open4.bantangapp.com/topics/topic/listByScene?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&page=%d&pagesize=20&id=%s";

    /**
     * Item的详情页
     */
    String URL_ITEM_DETAIL = "http://open4.bantangapp.com/topic/newInfo?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&id=%s&trace_id=&relate_pid=&statistics_uv=0&is_night=0";

    /**
     * Item的详情页评论
     */
    String URL_ITEM_COMMENT = "http://open4.bantangapp.com/topics/comment/list?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&page=0&pagesize=20&id=%s";

    /**
     * Item的详情页大家都在看
     */
    String URL_ITEM_WATCH = "http://open4.bantangapp.com/topics/topic/relationRec?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&id=%s";

    /**
     * webview
     */
    String URL_WEBVIEW = "http://m.ibantang.com/topic/%s/";

    String URL_SEARCH_SINGLE = "http://open4.bantangapp.com/category/list?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&is_new=1";
    String URL_SEARCH_ARTICLE = "http://open4.bantangapp.com/category/scene?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19";

    String URL_HOTTAG = "http://open4.bantangapp.com/base/search/hottags?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19";

    /**
     * search 子标签 动态添加的
     */

    String URL_SUBCLASSINFO = "http://open4.bantangapp.com/category/subclassInfo?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&id=%s";

    /**
     * search 标签产品列表
     */
    String URL_SEARCH_PRODUCT = "http://open4.bantangapp.com/search/product/listBySubclass?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&page=0&pagesize=20&sort_type=0&id=%s";

    /**
     * subclass 子标进行搜索
     */

    String URL_LISTSBYUBCLASS = "http://open4.bantangapp.com/search/product/listByClass?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&page=0&pagesize=20&sort_type=0&id=%s";

    /**
     * 关键字搜索
     */

    String URL_KEYWORD_SEARCH = "http://open4.bantangapp.com/search/topic/listByKeyword?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&keyword=%s&page=0&pagesize=20";

    /**
     * 关键字搜索单品,文章，晒单，用户
     */
    String URL_KEYWORD_SEARCH_SINGLE_TOPIC_USER_POST= "http://open4.bantangapp.com/search/%s/listByKeyword?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=85584f845fe4691e8b254a48ff4ab6d0&track_deviceid=864394010748616&track_device_info=Nexus+6&channel_name=bantang&app_installtime=1481879391&app_versions=5.8.7&os_versions=4.4.2&screensize=720&v=19&keyword=%s&sort_type=0&page=0&pagesize=20";

}
