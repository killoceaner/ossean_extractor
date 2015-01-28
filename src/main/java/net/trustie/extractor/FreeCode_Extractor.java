package net.trustie.extractor;

import java.sql.SQLException;

import net.trustie.downloader.DataBasePageErrorOutPut;
import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.FreeCode_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import core.PageModelPipeline;
import core.Site;
import extension.OsseanExtractor;

@Component
public class FreeCode_Extractor {
	@SuppressWarnings("rawtypes")
	@Qualifier("freeCodePipeline")
	@Autowired
	private PageModelPipeline modelPipeline;
	
	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;
	
	@Qualifier("errorPageToDB")
	@Autowired
	private DataBasePageErrorOutPut dbPageErrorOutPut;

	
	public void begin() {		
		generateRawPage.setTable("freecode_project");
		dbPageErrorOutPut.setTableName("freecode_project_error_page");
		
		
		OsseanExtractor
				.create(Site.me().setResultNum(100), modelPipeline,
						FreeCode_Model.class).setUUID("fcode")
				.setDownloader(generateRawPage).start();
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO 自动生成的方法存根
		ApplicationContext aContext = new FileSystemXmlApplicationContext(
				"./resources/spring/applicationContext*.xml");

		final FreeCode_Extractor extractor = aContext
				.getBean(FreeCode_Extractor.class);
		extractor.begin();
	}
}
