package net.trustie.extractor;

import java.sql.SQLException;

import net.trustie.downloader.GenerateRawPage;
import net.trustie.model.IteyeAsk_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import core.PageModelPipeline;
import core.Site;
import extension.OsseanExtractor;

@Component
public class IteyeAsk_Extractor {
	@	SuppressWarnings("rawtypes")
	@Qualifier("IteyeAskDaoPipeline")
	@Autowired
	private PageModelPipeline modelPipeline;
	@Qualifier("generateRawPage")
	@Autowired
	private GenerateRawPage generateRawPage;
	public void begin(){
		generateRawPage.setTableName("此处表名");
		OsseanExtractor.create(Site.me(), modelPipeline, IteyeAsk_Model.class).setUUID("IteyeAsk").setDownloader(generateRawPage).start();
	}
	public static void main(String[] args)throws SQLException{
		ApplicationContext aContext=new FileSystemXmlApplicationContext("./resources/spring/applicationContext*.xml");
		final IteyeAsk_Extractor extractor=aContext.getBean(IteyeAsk_Extractor.class);
		extractor.begin();
	}

}
