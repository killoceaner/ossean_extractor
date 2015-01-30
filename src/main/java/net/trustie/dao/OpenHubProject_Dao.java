package net.trustie.dao;

import net.trustie.model.OpenHubProject_Model;

import org.apache.ibatis.annotations.Insert;

public interface OpenHubProject_Dao {
	@Insert("insert into openhub_project"
			+"(`name`,`activity`,`useCount`,`description`,`tags`,`organization`,`projectLinks`,`codeLocation`,`licenses`,`similarProjects`,`managers`,`commitNum`,`contributorNum`,`codeLinesNum`,`mostWrittenIn`,`commentsPercentage`,`codebaseStatus`,`teamScale`,`commitStatus`,`estimateEffortTime`,`firstCommitTime`,`lastCommitTime`,`languagePercentages`,`activityDayTime`,`daysCommitNumber`,`daysContributorNumber`,`newContributor`,`newContriNum`,`activityMonthTime`,`monthsCommitNumber`,`monthsContributorNumber`,`theCommitTrend`,`theContriTrend`,`rateLevel`)"
			+" values (#{name},#{activity},#{useCount},#{description},#{tags},#{organization},#{projectLinks},#{codeLocation},#{licenses},#{similarProjects},#{managers},#{commitNum},#{contributorNum},#{codeLinesNum},#{mostWrittenIn},#{commentsPercentage},#{codebaseStatus},#{teamScale},#{commitStatus},#{estimateEffortTime},#{firstCommitTime},#{lastCommitTime},#{languagePercentages},#{activityDayTime},#{daysCommitNumber},#{daysContributorNumber},#{newContributor},#{newContriNum},#{activityMonthTime},#{monthsCommitNumber},#{monthsContributorNumber},#{theCommitTrend},#{theContriTrend},#{rateLevel})") 
	public int add(OpenHubProject_Model OpHmodel);
}
