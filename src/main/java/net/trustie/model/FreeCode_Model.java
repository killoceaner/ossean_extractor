package net.trustie.model;

import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;

@ExtractBy("//*[@id='page']/div[@id='container']/div[@class='clearfix']")
public class FreeCode_Model implements AfterExtractor, ValidateExtractor {

	public void afterProcess(Page page) {
		// TODO Auto-generated method stub

	}

	public void validate(Page page) {
		// TODO Auto-generated method stub

	}

}
