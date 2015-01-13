package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.trustie.core.ConsolePipeline;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CsdnTopic_Model;
import org.junit.Test;

import core.Page;

public class TestForCsdnTopic {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForCsdnTopicModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://bbs.csdn.net/topics/390958053",
				"http://bbs.csdn.net/topics/390306165",
				"http://bbs.csdn.net/topics/390952808",
				"http://bbs.csdn.net/topics/390956060",
				"http://bbs.csdn.net/topics/390958791",
				"http://bbs.csdn.net/topics/390941182",
				"http://bbs.csdn.net/topics/390958053",
				"http://bbs.csdn.net/topics/390960757"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CsdnTopic_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}

	@SuppressWarnings("serial")
	@Test
	public void TestForCsdnTopicModel2( ) {
		final String titleFile = "C:/Users/Administrator/Desktop/CsdnModel/page";
		Map<String, String> files = new LinkedHashMap<String, String>() {
			{
				put("http://bbs.csdn.net/topics/390958053", titleFile + "1.txt");
				put("http://bbs.csdn.net/topics/390306165", titleFile + "2.txt");
				put("http://bbs.csdn.net/topics/390952808", titleFile + "3.txt");
				put("http://bbs.csdn.net/topics/390956060", titleFile + "4.txt");
				put("http://bbs.csdn.net/topics/390958791", titleFile + "5.txt");
				put("http://bbs.csdn.net/topics/390941182", titleFile + "6.txt");
				put("http://bbs.csdn.net/topics/390958053", titleFile + "7.txt");
				put("http://bbs.csdn.net/topics/390958053", titleFile + "8.txt");
			}
		};
		pageList = GetPage.getPagesFromFiles(files);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CsdnTopic_Model.class);
		for (Page page : pageList) {
			oExtractorTest.runExtractor(page);
		}
	}

}
