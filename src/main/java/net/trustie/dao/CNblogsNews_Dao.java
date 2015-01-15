package net.trustie.dao;


import net.trustie.model.CNblogsNews_Model;

import org.apache.ibatis.annotations.Insert;

public interface  CNblogsNews_Dao {
	@Insert("insert into news_cnblogs " +
            "(`news_poster`,`news_poster_url`,`news_title`,`news_body`,`relative_time`,`extractTime`,`comment_num`,`view_num`,`come_from`,`diggnum`,`burynum`,`pageMD5`,`urlMD5`,`history`,`url`,`tags`)" +
            "values (#{news_poster},#{news_poster_url},#{news_title},#{news_body},#{relative_time},#{extractTime},#{comment_num},#{view_num},#{come_from},#{diggnum},#{burynum},#{pageMD5},#{urlMD5},#{history},#{url},#{tags})")
    public int add(CNblogsNews_Model cnblogsnews_Model);

}
