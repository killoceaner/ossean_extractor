package net.trustie.core;

import core.Page;
import extension.StringHandler;

public class GeneratePage {
	public static Page createPage(String url, String rawText) {
		Page page = new Page();
		if (StringHandler.isAllNotBlank(url, rawText)) {
			page.setRawText(rawText);
			page.setSkip(false);
			page.setPageUrl(url);
		}
		return page;
	}
}
