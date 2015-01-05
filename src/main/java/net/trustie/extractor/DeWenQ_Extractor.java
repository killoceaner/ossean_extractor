package net.trustie.extractor;

import java.sql.SQLException;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.DeWenQ_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import core.Site;
import extension.OsseanExtractor;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component
public class DeWenQ_Extractor {
	@SuppressWarnings("rawtypes")
	@Qualifier("deWenQ_pipeline")
	@Autowired
	private PageModelPipeline modelPipeline;
	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;

	public void begin() {
		generateRawPage.setTableName("html_test");
		OsseanExtractor.create(Site.me(), modelPipeline, DeWenQ_Model.class)
				.setUUID("DeWin Ques").setDownloader(generateRawPage).start();
	}

	public static void main(String[] args) throws SQLException {
		ApplicationContext aContext = new FileSystemXmlApplicationContext(
				"./resources/spring/applicationContext*.xml");
		final DeWenQ_Extractor extractor = aContext
				.getBean(DeWenQ_Extractor.class);
		extractor.begin();

	}

}
