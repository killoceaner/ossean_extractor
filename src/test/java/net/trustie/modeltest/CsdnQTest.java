package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CsdnAsk_Model;
import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class CsdnQTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForCsdnQModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://ask.csdn.net/questions/163842",
				"http://ask.csdn.net/questions/163837",
				"http://ask.csdn.net/questions/163854",
				"http://ask.csdn.net/questions/163853"
				));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CsdnAsk_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}

}
