package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import net.trustie.model.CNblogsNews_Model;
import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class CNblogsNewsTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForCNblogNewsModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://news.cnblogs.com/n/44862/",
				"http://news.cnblogs.com/n/36871/",
				"http://news.cnblogs.com/n/36889/",
				"http://news.cnblogs.com/n/36870/",
				"http://news.cnblogs.com/n/36890/",
				"http://news.cnblogs.com/n/36867/",
				"http://news.cnblogs.com/n/36869/",
				"http://news.cnblogs.com/n/36866/",
				"http://news.cnblogs.com/n/36849/",
				"http://news.cnblogs.com/n/36851/",
				"http://news.cnblogs.com/n/36855/",
				"http://news.cnblogs.com/n/36856/"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CNblogsNews_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
}
