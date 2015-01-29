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
				"http://www.oschina.net/question/202626_218331",
				"http://www.oschina.net/question/1390076_218051",
				"http://www.oschina.net/question/1050447_218480",
				"http://www.oschina.net/question/816727_218368",
				"http://www.oschina.net/question/1863482_218522",
				"http://www.oschina.net/question/1378920_218960",
				"http://www.oschina.net/question/1444646_218948",
				"http://www.oschina.net/question/115983_218961"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				OSChinaQuestion_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
}
