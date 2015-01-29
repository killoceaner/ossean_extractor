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
				"http://www.iteye.com/problems/141163",
				"http://www.iteye.com/problems/137799",
				"http://www.iteye.com/problems/137534",
				"http://www.iteye.com/problems/137501"
				));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				IteyeAsk_Model.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}
	
//	@Test
//	public void TestForDeWenModel2(){
//		final String titleFile = "D:/java_workspace/pageTest/"+"iteyeAsk/page";
//		@SuppressWarnings("serial")
//		Map<String, String> files = new LinkedHashMap<String, String>() {
//			{
//				put("http://www.iteye.com/problems/131829", titleFile + "1.html");
//				put("http://www.iteye.com/problems/132083", titleFile + "2.html");
//				put("http://www.iteye.com/problems/132092", titleFile + "3.html");
//				put("http://www.iteye.com/problems/131827", titleFile + "4.html");
//			}
//		};
//		pageList = GetPage.getPagesFromFiles(files);
//		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
//				IteyeAsk_Model.class);
//		System.out.println("从文件获取Page\n========================");
//		for (Page page : pageList) {
//			oExtractorTest.runExtractor(page);
//		}
//	}


}
