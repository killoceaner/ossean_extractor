package net.trustie.modeltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import net.trustie.core.GetPage;
import net.trustie.core.OsseanExtractorTest;
import net.trustie.model.SFProject;
import core.ConsolePipeline;
import core.Page;
public class SFProjectTest {
	private List<Page> pageList;
	private OsseanExtractorTest oExtractorTest;
	@Test
	public void TestForSFProjectModel() {
		List<String> urls = new ArrayList<String>(Arrays.asList(
				"http://sourceforge.net/projects/dvdstyler/?source=frontpage&position=1"
				/*"http://sourceforge.net/projects/tortoisesvn/?source=directory-featured",
				"http://sourceforge.net/projects/eclipse-cs/?source=directory-featured",
				"http://sourceforge.net/projects/hibernate/?source=directory-featured",
				"http://sourceforge.net/projects/gsoap2/?source=directory-featured",
				"http://sourceforge.net/projects/exo/?source=directory-featured",
				"http://sourceforge.net/projects/gsoap2/?source=directory-featured",
				"http://sourceforge.net/projects/logicaldoc/?source=directory-featured",
				"http://sourceforge.net/projects/logicaldoc/?source=directory-featured",
				"http://sourceforge.net/projects/npppluginmgr/?source=directory",
				"http://sourceforge.net/projects/mingw/?source=directory",*/
				/*"http://sourceforge.net/projects/portableapps/?source=directory"*/));
		pageList = GetPage.getPagesFromInternet(urls);
		oExtractorTest = OsseanExtractorTest.create(new ConsolePipeline(),
				SFProject.class);
		for (Page page : pageList)
			oExtractorTest.runExtractor(page);
	}

}
