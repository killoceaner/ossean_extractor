package net.trustie.downloader;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ErrorPageDao {

	@Insert("insert into ${table} (`url`,`type`) values(#{url},#{type})")
	public void insertErrorPage(@Param("table") String table,
			@Param("url") String url, @Param("type") String type);
}
