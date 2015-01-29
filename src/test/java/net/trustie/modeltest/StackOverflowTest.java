package net.trustie.modeltest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.StackOverflow;
import core.ConsolePipeline;
import core.Page;
public class StackOverflowTest{
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;
	@Test
	public void TestForStackOverFlowModel(){
		final String titleFile = "C:/Users/Administrator/Desktop/CsdnModel/page";
		@SuppressWarnings("serial")
		Map<String, String> files = new LinkedHashMap<String, String>() {
			{
				put("http://stackoverflow.com/questions/79/how-can-you-get-subclipse-in-aptana-to-work-with-the-newest-release-of-subversio", titleFile + "1.txt");
				put("http://stackoverflow.com/questions/109/decoding-t-sql-cast-in-c-vb-net", titleFile + "2.txt");
				put("http://stackoverflow.com/questions/27706080/is-there-a-performance-penalty-for-having-tables-in-two-databases", titleFile + "3.txt");
				put("http://stackoverflow.com/questions/27705831/how-does-priority-queue-compare-and-store-the-values-during-the-push-operation", titleFile + "4.txt");				
			}
		};
		pageList = GetPage.getPagesFromFiles(files);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				StackOverflow.class);
		for (Page page : pageList) {
			oExtractorTest.runExtractor(page);
		}
	}
}
