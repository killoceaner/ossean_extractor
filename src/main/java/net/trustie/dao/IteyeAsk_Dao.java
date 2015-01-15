package net.trustie.dao;


import net.trustie.model.IteyeAsk_Model;

import org.apache.ibatis.annotations.Insert;

public interface  IteyeAsk_Dao {
	    @Insert("insert into iteye_ask " +
	           "(`problem_title`,`problem_content`,`url`,`tags`,`urlMD5`,`release_time`,`pageMD5`,`user`,`extract_time`,`answer_count`,`auhtor`,`author_url`)" +
	            "values (#{problem_title},#{problem_content},#{url},#{tags},#{urlMD5},#{release_time},#{pageMD5},#{user},#{crawler_time},#{answer_count},#{author},#{author_url})")
	    public int add(IteyeAsk_Model  iteyeaskmodel);
}
