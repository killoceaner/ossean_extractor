package net.trustie.extractor;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.CsdnQ_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import core.Site;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import extension.OsseanExtractor;

public class CsdnQ_Extractor {
	@SuppressWarnings("rawtypes")
	@Qualifier("CsdnDaoPipeline")  //此处不知道对不对 
	@Autowired
	private PageModelPipeline  modelPipeline;
	@Qualifier("generateRawPage")
	@Autowired
  private GenerateRawPage generateRawPage;
	public void begin(){
		generateRawPage.setTableName("存csdnQ的html的表");
		OsseanExtractor.create(Site.me(), modelPipeline, CsdnQ_Model.class).setUUID("Csdn Question").setDownloader(generateRawPage).start();
		
	}
	public static void main(String[] args){
		ApplicationContext aContext = new FileSystemXmlApplicationContext(
				"./resources/spring/applicationContext*.xml");
		
		final CsdnQ_Extractor extractor = aContext
				.getBean(CsdnQ_Extractor.class);
		       extractor.begin();
		
	}
}
