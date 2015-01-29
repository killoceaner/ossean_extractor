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
				"http://blog.csdn.net/helloboat/article/details/42418085",								
				"http://blog.csdn.net/luoye7422/article/details/42322017",
				"http://blog.csdn.net/luoye7422/article/details/42321941",
				"http://blog.csdn.net/liubinblog/article/details/42399331",
				"http://blog.csdn.net/jiasike/article/details/42362119",
				"http://blog.csdn.net/ekeuy/article/details/42292589"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CsdnBlogs_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
}
