package net.trustie.dao;

import net.trustie.model.CNblogsQ_Model;

import org.apache.ibatis.annotations.Insert;

public interface CNblogsQ_Dao {
	@Insert("insert into cnblog_question"
			+ "(`question_id`,`question_title`,`url`,`question_content`,`urlMD5`,`tag`,`answer_num`,`score_bean`,`vote_num`,`view_num`,`post_time`,`extract_time`,`pageMD5`,`history`,`author`,`author_url`)"
			+ "values (#{questionId},#{questionTitle},#{questionUrl},#{questionContent},#{urlMD5},#{tag},#{answerNum},#{scoreBean},#{voteNum},#{viewNum},#{postTime},#{extractTime},#{pageMD5},#{history},#{author},#{authorUrl})")
	public int add(CNblogsQ_Model cNblogsQ_Model);
}
