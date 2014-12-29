package net.trustie.dao;

import net.trustie.model.CsdnTopic_Model;

import org.apache.ibatis.annotations.Insert;

public interface CsdnTopic_Dao {

	@Insert("insert into bbs_csdn"
			+ "(`topicId`,`topicTitle`,`topicUrl`,`topicContent`,`topicUrlMD5`,`tags`,`topicScore`,`replyNum`,`postTime`,`extractTime`,`pageMD5`,`history`,`author`,`author_url`)"
			+ "values (#{topicId},#{topicTitle},#{topicUrl},#{topicContent},#{topicUrlMD5},#{tag},#{topicScore},#{replyNum},#{postTime},#{extractTime},#{pageMD5},#{history},#{author},#{author_url})")
	public int add(CsdnTopic_Model cModel);
}
