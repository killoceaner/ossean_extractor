package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.OSChinaQuestion_Model;

import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class OSChinaQuestionTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForOSChinaQuestionModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://www.oschina.net/question/1013116_150368",
				"http://www.oschina.net/question/6556_150338",
				"http://www.oschina.net/question/1376388_150394",
				"http://www.oschina.net/question/12_150380",
				"http://www.oschina.net/question/1763928_153465",
				"http://www.oschina.net/question/1538193_150408",
				"http://www.oschina.net/question/1763928_153465",
				"http://www.oschina.net/question/1376388_150394"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				OSChinaQuestion_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
}
