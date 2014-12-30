package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.trustie.core.ConsolePipeline;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.OpenHubProject;

import org.junit.Test;

import core.Page;

public class OpenHubProjectTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

//	@Test
//	public void TestForCsdnTopicModel() {
//		List<String> urls = new ArrayList<String>();
//		urls.add("https://www.openhub.net/p/ubuntu");
//		pageList = GetPage.getPagesFromInternet(urls);
//		System.out.println(pageList.get(0));
//		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
//				OpenHubProject.class);
//		for (Page page : pageList)
//			oExtractorTest.runExtractor(page);
//	}

	@SuppressWarnings("serial")
	@Test
	public void TestForCsdnTopicModel2() {
		final String titleFile = "C:/Users/kg/Desktop/pages/";
		Map<String, String> files = new LinkedHashMap<String, String>() {
			{
				put("https://www.openhub.net/p/firefox", titleFile + "firefox.html");
			}
		};
		pageList = GetPage.getPagesFromFiles(files);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				OpenHubProject.class);
		for (Page page : pageList) {			
			oExtractorTest.runExtractor(page);
		}
	}

}
