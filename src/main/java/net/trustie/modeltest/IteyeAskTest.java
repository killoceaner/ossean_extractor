package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.IteyeAsk_Model;

import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class IteyeAskTest {

	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForIteyeAskModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://www.iteye.com/problems/100560",
				"http://www.iteye.com/problems/100581",
				"http://www.iteye.com/problems/99384",
				"http://www.iteye.com/problems/99400",
				"http://www.iteye.com/problems/96985",
				"http://www.iteye.com/problems/96899",
				"http://www.iteye.com/problems/95754",
				"http://www.iteye.com/problems/94639"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				IteyeAsk_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}

}
