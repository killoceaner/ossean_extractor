package net.trustie.extractor;

import java.sql.SQLException;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.CNblogsQ_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import core.Site;

import us.codecraft.webmagic.pipeline.PageModelPipeline;
import extension.OsseanExtractor;

@Component
public class CNblogQ_Extractor {

	@SuppressWarnings("rawtypes")
	@Qualifier("cnblogQPipeline")
	@Autowired
	private PageModelPipeline modelPipeline;

	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;

	public void begin() {
		generateRawPage.setTableName("test_tags");
		OsseanExtractor
				.create(Site.me().setResultNum(50), modelPipeline,
						CNblogsQ_Model.class).setUUID("cnblogQ")
				.setDownloader(generateRawPage).start();
	}

	public static void main(String[] args) throws SQLException {
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext*.xml");
		final CNblogQ_Extractor extractor = aContext
				.getBean(CNblogQ_Extractor.class);
		extractor.begin();
	}
}
