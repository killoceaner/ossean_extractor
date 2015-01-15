package net.trustie.extractor;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.SFProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import core.PageModelPipeline;
import core.Site;
import extension.OsseanExtractor;

public class SFProjects_Extractor {
	@SuppressWarnings("rawtypes")
	@Qualifier("SFProjectsDaoPipeline")  
	@Autowired
	private PageModelPipeline  modelPipeline;
	@Qualifier("generateRawPage")
	@Autowired
  private GenerateRawPage generateRawPage;
	public void begin(){
		generateRawPage.setTableName("sourceforge_html");
		OsseanExtractor.create(Site.me(), modelPipeline, 	SFProject.class).setUUID("source project").setDownloader(generateRawPage).start();
		
	}
	public static void main(String[] args){
		ApplicationContext aContext = new FileSystemXmlApplicationContext(
				"./resources/spring/applicationContext*.xml");
		
		final SFProjects_Extractor extractor = aContext
				.getBean(SFProjects_Extractor.class);
		       extractor.begin();
		
	}
}
