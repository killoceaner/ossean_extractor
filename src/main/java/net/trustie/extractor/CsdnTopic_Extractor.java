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

import core.PageModelPipeline;
import core.Site;
import extension.OsseanExtractor;

@SuppressWarnings("unused")
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
		generateRawPage.setTableName("csdn_topic_html_detail");
		OsseanExtractor
				.create(Site.me().setResultNum(1000), modelPipeline,
						CsdnTopic_Model.class).setUUID("csdnTopics")
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