package com.example.zjy.util;

/**
 * Created by zjy on 2016/12/17.
 */

public interface Constants {
    String[] TAB = {"单品","文章"};

    String[] MESSAGETAB = {"通知","评论","推送"};

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

    String URL_COMMUNITY = "http://open4.bantangapp.com/post/index/listByNew?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=96fc7c7373d2dc9aebe5e5d73e93256f&track_deviceid=864394010748616&channel_name=UMENG_CHANNEL&app_installtime=1483542714&app_versions=5.9.6.1&os_versions=4.4.2&screensize=720&v=24&track_device_info=vivo+X510t&page=%d&pagesize=20";

    String URL_COMMUNITY_DETAIL = "http://open4.bantangapp.com/topic/newInfo?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=bcd5dd56b6addf0d31a89c889e14c94f&track_deviceid=864394010748616&channel_name=UMENG_CHANNEL&app_installtime=1483542714&app_versions=5.9.6.1&os_versions=4.4.2&screensize=720&v=24&track_device_info=vivo+X510t&id=%s&trace_id=&search_key=&statistics_uv=0&is_night=0";

    String URL_COMMUNITY_POST_DETAIL = "http://open4.bantangapp.com/post/post/info?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=bcd5dd56b6addf0d31a89c889e14c94f&track_deviceid=864394010748616&channel_name=UMENG_CHANNEL&app_installtime=1483542714&app_versions=5.9.6.1&os_versions=4.4.2&screensize=720&v=24&track_device_info=vivo+X510t&id=%s&trace_id=&search_key=";

    String URL_KOL_RANK = "http://m.ibantang.com/toplist/userRankData?sortType=2";

    String URL_TOPIC_RANK = "http://open4.bantangapp.com/topics/topic/listByRank?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=bcd5dd56b6addf0d31a89c889e14c94f&track_deviceid=864394010748616&channel_name=UMENG_CHANNEL&app_installtime=1483542714&app_versions=5.9.6.1&os_versions=4.4.2&screensize=720&v=24&track_device_info=vivo+X510t&page=0&pagesize=20&sort_type=3";

    String URL_POST_RANK = "http://open4.bantangapp.com/post/post/listByRank?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=bcd5dd56b6addf0d31a89c889e14c94f&track_deviceid=864394010748616&channel_name=UMENG_CHANNEL&app_installtime=1483542714&app_versions=5.9.6.1&os_versions=4.4.2&screensize=720&v=24&track_device_info=vivo+X510t&page=0&pagesize=10&sort_type=3";

    String URL_MESSAGE_PUSH = "http://open4.bantangapp.com/users/push/list?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=bcd5dd56b6addf0d31a89c889e14c94f&track_deviceid=864394010748616&channel_name=UMENG_CHANNEL&app_installtime=1483542714&app_versions=5.9.6.1&os_versions=4.4.2&screensize=720&v=24&track_device_info=vivo+X510t&page=0&pagesize=20&type_id=1%2C2%2C3%2C4%2C5%2C6";

    String URL_NOTIFICAITON = "http://open4.bantangapp.com/users/notice/list?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2766969&oauth_token=bcd5dd56b6addf0d31a89c889e14c94f&track_deviceid=864394010748616&channel_name=UMENG_CHANNEL&app_installtime=1483542714&app_versions=5.9.6.1&os_versions=4.4.2&screensize=720&v=24&track_device_info=vivo+X510t&page=0&pagesize=20&type_id=1%2C2%2C13%2C12%2C16%2C15%2C18%2C17%2C6%2C19%2C8%2C5&is_merge=1&is_message=1";
}
