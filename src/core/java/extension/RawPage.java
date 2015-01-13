package extension;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.Page;

public class RawPage {

	private int id;

	private String url;

	private boolean isExtracted;

	private boolean isStored;

	private String html;

	private Page page;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void generatPage() {
		this.page = new Page();
		if (StringUtils.isNotBlank(this.url)) {
			if (this.html != null && this.html.length() > 0) {
				page.setPageUrl(this.url);
				page.setRawText(this.html);
				page.setSkip(false);
			} else {
				page.setPageUrl(this.url);
				page.setSkip(true);
				logger.info("Warnning:Page{id=" + this.id + ",url=" + this.url
						+ "} RawText Is Null OR Empty!");
			}
		} else {
			page.setSkip(true);
			logger.info("Warnning:Page{id=" + this.id + "url=" + this.url
					+ "} Url Is　Null OR Empty!");
		}
		isExtracted = false;
		isStored = false;
	}

	public void printLogInfo() {
		if (isExtracted) {
			if (isStored)
				logger.info("Page{id=" + this.id + ",url=" + this.url
						+ "} Extracted And Stored Successed!");
			else {
				logger.error("Page{id=" + this.id + ",url=" + this.url
						+ "} Extracted Successed,But Stored Failed!");
			}
		} else {
			logger.error("Page{id=" + this.id + ",url=" + this.url
					+ "} Extracted Failed!");
		}
	}

	@Override
	public String toString() {
		return page.toString();
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getRawText() {
		return html;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setRawText(String rawText) {
		html = rawText;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public boolean isExtracted() {
		return isExtracted;
	}

	public void setExtracted(boolean isExtracted) {
		this.isExtracted = isExtracted;
	}

	public boolean isStored() {
		return isStored;
	}

	public void setStored(boolean isStored) {
		this.isStored = isStored;
	}

}
