package extension;

import java.io.UnsupportedEncodingException;

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
		try {
			this.html = new String(this.html.getBytes("UTF-8"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.warn(page.toString(), e);
		}
		if (StringUtils.isNotBlank(this.url)) {
			if (this.html != null && this.html.length() > 0) {
				page.setPageUrl(this.url);
				page.setRawText(this.html);
				page.setSkip(false);
			} else {
				page.setPageUrl(this.url);
				page.setSkip(true);
				logger.warn("Warnning:" + this.toString()
						+ " RawText Is Null OR Empty!");
			}
		} else {
			page.setSkip(true);
			logger.warn("Warnning:+" + this.toString()
					+ " Url Is　Null OR Empty!");
		}
		isExtracted = false;
		isStored = false;
	}

	public void printLogInfo(String message) {
		if (!isExtracted) {
			logger.warn(this.toString() + " Extracted Failed ## " + message);
			return;
		}

		if (!isStored)
			logger.warn(this.toString()
					+ " Extracted Successed,Stored Failed! ## " + message);
	}

	public void printLogInfo(Throwable e) {
		if (!isExtracted) {
			logger.warn(this.toString() + " Extracted Failed!", e);
			return;
		}

		if (!isStored)
			logger.warn(
					this.toString() + " Extracted Successed,Stored Failed!", e);
	}

	@Override
	public String toString() {
		return "Page{id=" + this.id + ",url=" + this.url + "}";
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
