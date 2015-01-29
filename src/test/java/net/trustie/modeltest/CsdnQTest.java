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
				"http://blog.csdn.net/helloboat/article/details/42418085"								
				));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				CsdnAsk_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}

}
