package net.trustie.downloader;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import extension.PageDownloader;
import extension.RawPage;

@Component("generateRawPage")
public class GenerateRawPage implements PageDownloader {

	@Resource
	private PageDao pagedao;

	private String tableName;

	public List<RawPage> downloadPages(int begin_id, int end_id) {
		// TODO Auto-generated method stub
		return pagedao.selectScourcePage(this.tableName, begin_id, end_id);
	}

	public int getMinId() {
		// TODO Auto-generated method stub
		return pagedao.getMinId(this.tableName);
	}

	public void returnErrorPages(String url) {
		// TODO Auto-generated method stub
		/**
		 * add some Method
		 */
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
