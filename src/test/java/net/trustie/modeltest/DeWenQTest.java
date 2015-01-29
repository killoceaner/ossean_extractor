package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.DeWenQ_Model;

import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class DeWenQTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForDeWenModel() {		
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://www.dewen.io/q/17538/BlockingQueue",
				"http://www.dewen.io/q/17537/AS3%E4%B8%AD%E5%A6%82%E4%BD%95%E6%8D%95%E8%8E%B7alt%E9%94%AE%E4%BA%8B%E4%BB%B6%EF%BC%9F",				
				"http://www.dewen.io/q/17290/%E5%BE%AE%E5%8D%9A%E8%BD%AC%E5%8F%91%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AF%A5%E5%A6%82%E4%BD%95%E8%AE%BE%E8%AE%A1+%E5%9C%A8%E4%B8%AA%E4%BA%BA%E9%A6%96%E9%A1%B5%E8%BF%98%E9%9C%80%E8%A6%81%E6%9F%A5%E5%87%BA+%E6%89%80%E6%9C%89%E7%9A%84%E5%BE%AE%E5%8D%9A"));		
		pageList = GetPage.getPagesFromInternet(urls);		
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				DeWenQ_Model.class);
		System.out.println("从Internet获取Page\n========================");
		for (Page page : pageList){
			oExtractorTest.runExtractor(page);
		}
		
	}
	
	@Test
	public void TestForDeWenModel2(){
		final String titleFile = "D:/java_workspace/pageTest/"+"DeWen/page";
		@SuppressWarnings("serial")
		Map<String, String> files = new LinkedHashMap<String, String>() {
			{
				put("http://www.dewen.io/q/17538/BlockingQueue", titleFile + "1.html");
				put("http://www.dewen.io/q/17537/AS3%E4%B8%AD%E5%A6%82%E4%BD%95%E6%8D%95%E8%8E%B7alt%E9%94%AE%E4%BA%8B%E4%BB%B6%EF%BC%9F", titleFile + "2.html");
				put("http://www.dewen.io/q/9437/%E4%BA%8C%E7%BB%B4%E7%A0%81%E4%B8%AD%E7%9A%84%E4%BF%A1%E6%81%AF%E5%8A%A0%E5%AF%86%E6%96%B9%E5%BC%8F", titleFile + "3.html");							
			}
		};
		pageList = GetPage.getPagesFromFiles(files);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				DeWenQ_Model.class);
		System.out.println("从文件获取Page\n========================");
		for (Page page : pageList) {
			oExtractorTest.runExtractor(page);
		}
	}

}
