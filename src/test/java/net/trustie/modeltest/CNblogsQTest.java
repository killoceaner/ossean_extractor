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
				"http://q.cnblogs.com/q/69344/",
				"http://q.cnblogs.com/q/69335/",
				"http://q.cnblogs.com/q/68075/",
				"http://q.cnblogs.com/q/68350/",
				"http://q.cnblogs.com/q/65504/",
				"http://q.cnblogs.com/q/67343/",
				"http://q.cnblogs.com/q/63808/",
				"http://q.cnblogs.com/q/61260/"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CNblogsQ_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
}
