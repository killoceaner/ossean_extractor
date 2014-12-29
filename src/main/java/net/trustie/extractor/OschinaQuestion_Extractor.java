package net.trustie.extractor;

import java.sql.SQLException;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.OSChinaQuestion_Model;
import net.trustie.pipeline.OSChinaQuestion_pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import core.Site;

import extension.OsseanExtractor;

@Component
public class OschinaQuestion_Extractor {
	@Qualifier("OSChinaQuestionPipeline")
	@Autowired
	private OSChinaQuestion_pipeline oChinaQuestionPipeline;

	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;

	public void begin() {
		generateRawPage.setTableName("ques_oschina");
		OsseanExtractor
				.create(Site.me().setResultNum(500), oChinaQuestionPipeline,
						OSChinaQuestion_Model.class).setUUID("OschinaQuestion")
				.setDownloader(generateRawPage).start();
	}

	public static void main(String[] args) throws SQLException {
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext*.xml");
		final OschinaQuestion_Extractor extractor = aContext
				.getBean(OschinaQuestion_Extractor.class);
		extractor.begin();
	}
}
