package net.trustie.modeltest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.trustie.core.ConsolePipeline;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.DeWenQ_Model;

import org.junit.Test;

import core.Page;



public class DeWenQuestionTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtraxtorTest;
	@SuppressWarnings("serial")
	@Test
	public void TestForCsdnModel2(){
		final String titleFile="C:/Users/Administrator/Desktop/work file/1.06/";
		Map<String,String>files=new LinkedHashMap<String,String>(){
			{
			put("http://www.dewen.io/q/17378",titleFile+"DewenQuestion.html");
		}
	};
	pageList=GetPage.getPagesFromFiles(files);
	oExtraxtorTest=OsseanExtractorTest.create(new ConsolePipeline(),DeWenQ_Model.class);
	for(Page page:pageList){
	oExtraxtorTest.runExtractor(page);
		 }
	}
}
