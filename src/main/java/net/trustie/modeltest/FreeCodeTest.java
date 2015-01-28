package net.trustie.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CNblogsQ_Model;
import net.trustie.model.FreeCode_Model;

import org.junit.Test;

import core.ConsolePipeline;
import core.Page;

public class FreeCodeTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;
//	@Test
//	public void TestForFreeCodeModel(){
//		List<String> urls = new ArrayList<String>(Arrays.asList(
//				"http://freecode.com/projects/glpi",
//				"http://freecode.com/projects/lpar2rrd",
//				"http://freecode.com/projects/php-oauth-api",
//				"http://freecode.com/projects/wro4j",
//				"http://freecode.com/projects/quartzdesk"
//				));
//		pageList = GetPage.getPagesFromInternet(urls);
//		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
//				FreeCode_Model.class);
//		for (Page page : pageList)
//			oExtractorTest.runExtractor(page);
//	}
	
	@Test
	public void TestForDeWenModel2(){
		final String titleFile = "F:\\workspace\\pageTest\\FreeCode\\page\\";
		@SuppressWarnings("serial")
		Map<String, String> files = new LinkedHashMap<String, String>() {
			{
				put("http://www.dewen.io/q/17538/BlockingQueue", titleFile + "1.html");
//				put("http://www.dewen.io/q/17537/AS3%E4%B8%AD%E5%A6%82%E4%BD%95%E6%8D%95%E8%8E%B7alt%E9%94%AE%E4%BA%8B%E4%BB%B6%EF%BC%9F", titleFile + "2.html");
//				put("http://www.dewen.io/q/9437/%E4%BA%8C%E7%BB%B4%E7%A0%81%E4%B8%AD%E7%9A%84%E4%BF%A1%E6%81%AF%E5%8A%A0%E5%AF%86%E6%96%B9%E5%BC%8F", titleFile + "3.html");							
			}
		};
		pageList = GetPage.getPagesFromFiles(files);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				FreeCode_Model.class);
		System.out.println("从文件获取Page\n========================");
		for (Page page : pageList) {
			oExtractorTest.runExtractor(page);
		}
	}
}
