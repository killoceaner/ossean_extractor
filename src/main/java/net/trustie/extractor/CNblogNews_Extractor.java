package net.trustie.extractor;

import java.sql.SQLException;
import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.CNblogsNews_Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import core.PageModelPipeline;
import core.Site;
import extension.OsseanExtractor;

public class CNblogNews_Extractor {
	@SuppressWarnings("rawtypes")
	@Qualifier("cnBlogNewsPipeline")
	@Autowired
	private PageModelPipeline modelPipeline;
	
	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;

	public void begin() {
		generateRawPage.setTableName("");
		OsseanExtractor
				.create(Site.me().setResultNum(100), modelPipeline,
						CNblogsNews_Model.class).setUUID("cnBlogNews")
				.setDownloader(generateRawPage).start();
	}

	public static void main(String[] args) throws SQLException {
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext*.xml");

		final CNblogNews_Extractor extractor = aContext
				.getBean(CNblogNews_Extractor.class);

		extractor.begin();
	}

}
