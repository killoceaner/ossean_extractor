package net.trustie.extractor;

import java.sql.SQLException;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.CsdnTopic_Model;

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
public class CsdnTopic_Extractor {

	@SuppressWarnings("rawtypes")
	@Qualifier("CsdnDaoPipeline")
	@Autowired
	private PageModelPipeline modelPipeline;

	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;

	public void begin() {
		generateRawPage.setTableName("html_test");
		OsseanExtractor
				.create(Site.me().setResultNum(50), modelPipeline,
						CsdnTopic_Model.class).setUUID("OschinaTopics")
				.setDownloader(generateRawPage).start();
	}

	public static void main(String[] args) throws SQLException {
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext*.xml");
		final CsdnTopic_Extractor extractor = aContext
				.getBean(CsdnTopic_Extractor.class);
		extractor.begin();
	}
}