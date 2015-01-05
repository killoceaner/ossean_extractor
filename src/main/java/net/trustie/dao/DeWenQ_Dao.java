package net.trustie.dao;

import net.trustie.model.DeWenQ_Model;

import org.apache.ibatis.annotations.Insert;


public interface DeWenQ_Dao {
	@Insert("insert into ques_dewen" +
            "(`issueTitle`,`issueId`,`issueUrl`,`issueDetail`,`issueUrlMD5`,`tags`,`scanerNum`,`attentionNum`,`commentNum`,`postTime`,`extractTime`,`pageMD5`,`author`,`author_url`,`history`)" +
             "values (#{issueTitle},#{issueId},#{issueUrl},#{issueDetail},#{issueUrlMD5},#{tags},#{scanerNum},#{attentionNum},#{commentNum},#{postTime},#{extractTime},#{pageMD5},#{author},#{author_url},#{history})") 
             public int add(DeWenQ_Model deWenQ_Model);
}
