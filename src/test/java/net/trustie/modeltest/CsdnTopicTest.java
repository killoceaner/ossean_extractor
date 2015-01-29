package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CsdnTopic_Model;

import org.junit.Test;

import core.ConsolePipeline;
import core.Page;

public class CsdnTopicTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForCsdnTopicModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://bbs.csdn.net/topics/390701880",
				"http://bbs.csdn.net/topics/390354975",
				"http://bbs.csdn.net/topics/390354932",
				"http://bbs.csdn.net/topics/390329107"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CsdnTopic_Model.class);
		for (Page page : pageList) {
			oExtractorTest.runExtractor(page);
		}
	}
}
