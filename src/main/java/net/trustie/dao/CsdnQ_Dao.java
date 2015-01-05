package net.trustie.dao;

import net.trustie.model.CsdnQ_Model;

import org.apache.ibatis.annotations.Insert;

public interface CsdnQ_Dao {
	@Insert("insert into ques_csdn" +
            "(`issueId`,`issueTitle`,`issueUrl`,`issueContent`,`author`,`author_url`,`voteNum`,`tags`,`extractTime`,`askTime`,`urlMD5`,`pageMD5`,`history`)" +
             
            "values (#{issueId},#{issueTitle},#{issueUrl},#{issueContent},#{author},#{author_url},#{voteNum},#{tags},#{extractTime},#{askTime},#{urlMD5},#{pageMD5},#{history})" )
	
  public int add(CsdnQ_Model   cQmodel);

}
