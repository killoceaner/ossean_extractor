package net.trustie.modeltest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import net.trustie.core.ConsolePipeline;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.CsdnQ_Model;
import core.Page;

public class CsdnQuestionTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtraxtorTest;
	@SuppressWarnings("serial")
	@Test
	public void TestForCsdnModel2(){
		final String titleFile="C:\\/Users/Administrator/Desktop/work file/2015.01.05/";
		Map<String,String>files=new LinkedHashMap<String,String>(){
			{
			put("http://ask.csdn.net/questions/160522",titleFile+"csdn_q_test.html");
		}
	};
	pageList=GetPage.getPagesFromFiles(files);
	oExtraxtorTest=OsseanExtractorTest.create(new ConsolePipeline(),CsdnQ_Model.class);
	for(Page page:pageList){
	oExtraxtorTest.runExtractor(page);
		      }
	}
}
