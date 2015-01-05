package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CNblogsQ_Model;
import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class CNblogsQTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;
	
	@Test
	public void TestForCNblogsQModel(){
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://q.cnblogs.com/q/68821/",
				"http://q.cnblogs.com/q/68815/",
				"http://q.cnblogs.com/q/68811/",
				"http://q.cnblogs.com/q/68800/",
				"http://q.cnblogs.com/q/68794/",
				"http://q.cnblogs.com/q/68791/",
				"http://q.cnblogs.com/q/68788/",
				"http://q.cnblogs.com/q/68820/"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CNblogsQ_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
}
