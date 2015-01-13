package net.trustie.modeltest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import core.ConsolePipeline;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CNblogsNews_Model;
import core.Page;
public class CNblogsNewsTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;
	@SuppressWarnings("serial")
	@Test
	public void TestForCsdnTopicModel2() {
		final String titleFile = "C:\\/Users/Administrator/Desktop/work file/1.06/";
		Map<String, String> files = new LinkedHashMap<String, String>() {
			{
				put("http://news.cnblogs.com/n/512506/", titleFile + "CNblogsNews.html");
			}
		};
		pageList = GetPage.getPagesFromFiles(files);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CNblogsNews_Model.class);
		for (Page page : pageList) {			
			oExtractorTest.runExtractor(page);
		}
	}
}
