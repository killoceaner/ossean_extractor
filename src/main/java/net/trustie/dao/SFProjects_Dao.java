package net.trustie.dao;

import net.trustie.model.SFProject;

import org.apache.ibatis.annotations.Insert;

public interface  SFProjects_Dao {
	@Insert("insert into SFProject"
			+ "(`name`,`maintainers`,`stars`,`downloadCount`,`lastUpdate`,`platform`,`desc`,`categories`,`license`,`feature`,`language`,`interdedAudience`,`userInterface`,`programmingLanguage`,`registeredTime`,`collectTime`,`url`,`urlMd5`,`pageMd5`,`history`,`html`)"
			+ "values (#{name},#{maintainers},#{stars},#{downloadCount},#{lastUpdate},#{platform},#{desc},#{categories},#{license},#{feature},#{language},#{interdedAudience},#{userInterface},#{programmingLanguage},#{registeredTime},#{collectTime},#{url},#{urlMd5},#{pageMd5},#{history},#{html})")
	public int add(SFProject sfp);
}
