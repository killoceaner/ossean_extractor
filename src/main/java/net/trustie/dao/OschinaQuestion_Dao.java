package net.trustie.dao;

import net.trustie.model.OSChinaQuestion_Model;

import org.apache.ibatis.annotations.Insert;

public interface OschinaQuestion_Dao {

	@Insert("insert into oschina_question"
			+ "(`question_url`,`url_md5`,`question_id`,`question_title`,`question_content`,`question_type`,`question_tags`,`page_md5`,`reply_num`,`view_num`,`housed_num`,`laud_num`,`post_time`,`extract_time`,`author`,`author_url`,`history`)"
			+ " values(#{questionUrl},#{urlMD5},#{questionId},#{questionTitle},#{questionContent},#{questionType},#{tag},#{pageMD5},#{replyNum},#{viewNum},#{housedNum},#{laudNum},#{postTime},#{extractTime},#{author},#{authorUrl},#{history})")
	public int add(OSChinaQuestion_Model oscModel);
}
