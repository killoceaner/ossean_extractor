package net.trustie.model;

import java.util.List;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Source;
import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;

@ExtractBy("")
public class CsdnQ_Model implements AfterExtractor, ValidateExtractor {
	private int issueId = 0;

	private String issueUrl = "";
	
	private String pageMD5;
	
	private String history;
	
	private String tag;
	
	private String extractTime;
	
	@ExtractBy("//*div[@class='questions_detail_con']/dl/dt/text()")
	private String issueTitle = "";
	
	@ExtractBy("//*[@id='cut_intro']/allText()")
	private String issueContent = "";
	
	@ExtractBy("//*div/a[@class='approve']/text()")
	private String voteNum;	
	
	@ExtractBy(value = "//*/div[@class='tags']/a/allText()")
	private List<String> tags;
	
	@ExtractBy("//*div[@class='persion_article']/div[@class='mod_user_info']/div[@class='user_info']/div[@class='info_box clearfix']/p/text()")
	private String postTime = "";
	
	@ExtractBy(value = "//*div[@class='persion_article']/div[@class='mod_user_info']/div[@class='user_info']/div[@class='info_box clearfix']/dl[@class='person_info']/dt/a/text()", source = Source.RawHtml)
	private String author = "";
	
	@ExtractBy(value = "//*dl[@class='person_info']/dt/a/@href", source = Source.RawHtml)
	private String author_url;	

	public void afterProcess(Page page) {
	
	}

	@Override
	public void validate(Page page) {	
		
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}	

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssueTitle() {
		return issueTitle;
	}

	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getIssueUrl() {
		return issueUrl;
	}

	public void setIssueUrl(String issueUrl) {
		this.issueUrl = issueUrl;
	}

	public String getAuthor_url() {
		return author_url;
	}

	public void setAuthor_url(String author_url) {
		this.author_url = author_url;
	}

	public String getPageMD5() {
		return pageMD5;
	}

	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}	
}
