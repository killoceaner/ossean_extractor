package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CsdnBlogs_Model;
import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class CsdnBlogsTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;

	@Test
	public void TestForCNblogsQModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://blog.csdn.net/q1q1x2x2/article/details/42869849",								
				"http://blog.csdn.net/u011439689/article/details/42872869",				
				"http://blog.csdn.net/pistolove/article/details/42833739",
				"http://blog.csdn.net/ekeuy/article/details/42292589"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CsdnBlogs_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
}
