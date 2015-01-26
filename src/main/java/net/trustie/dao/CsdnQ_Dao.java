package net.trustie.dao;

import net.trustie.model.CsdnQ_Model;

import org.apache.ibatis.annotations.Insert;

public interface CsdnQ_Dao {
	@Insert("insert into csdnblog_news"
			+ "(`issueId`,`issueUrl`,`issueTitle`,`tag`,`issueContent`,`voteNum`,`answerNum`,`postTime`,`extractTime`,`author`,`authorUrl`,`pageMD5`,`history`)"
			+ " values (#{issueId},#{issueUrl},#{issueTitle},#{tag},#{issueContent},#{voteNum},#{answerNum},#{postTime},#{extractTime},#{author},#{authorUrl},#{pageMD5},#{history})")
	public int add(CsdnQ_Model csdnQ_Model);
}
