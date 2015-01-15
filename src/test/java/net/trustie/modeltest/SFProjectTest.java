package net.trustie.modeltest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.SFProject;
import org.junit.Test;
import core.ConsolePipeline;
import core.Page;

public class SFProjectTest {
	
/*	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;
	
	@Test
	public void TestForSFProject(){
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://sourceforge.net/projects/aresgalaxy/?source=directory"));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				SFProject.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}*/
	private List<Page> pageList;
	private OsseanExtractorTest oExtraxtorTest;
	@SuppressWarnings("serial")
	@Test
	public void TestForCsdnModel2(){
		final String titleFile="C:\\/Users/Administrator/Desktop/work file/1.14/";
		Map<String,String>files=new LinkedHashMap<String,String>(){
			{
			put("http://sourceforge.net/projects/aresgalaxy/?source=directory",titleFile+"SFProjects.html");
			//put("http://sourceforge.net/projects/hibernate/?source=frontpage&position=1",titleFile+"hibernate.html");
		}
	};
	pageList=GetPage.getPagesFromFiles(files);
	oExtraxtorTest=OsseanExtractorTest.create(new ConsolePipeline(),SFProject.class);
	for(Page page:pageList){
	oExtraxtorTest.runExtractor(page);
		      }
	}

}
