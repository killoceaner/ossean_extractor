package net.trustie.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;
import extension.StringHandler;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Source;

@ExtractBy("//*div[@class='container']/div[@class='qa_lft top20']") //限定抽取区域
public class DeWenQ_Model  implements AfterExtractor,ValidateExtractor{
     private int issueId=0;
	 private String issueUrl="";
	@ExtractBy("//*h1[@id='title' ]/text()")
	 private String issueTitle="";
   @ExtractBy("//*div[@class='que_con']/p/text()")
	 private String issueDetail="";
	 private String issueUrlMD5="";
	 private String tag;
   @ExtractBy("//*div[@id='topic']/allText()")
	 private List<String> tags;
   @ExtractBy(value="//*div[@class='stats']/p/b/text()",source=Source.RawHtml)
	 private String scanerNum="";
   @ExtractBy(value="//*div[@class='follow_questions']/h2/span/text()",source=Source.RawHtml)
	 private String  attentionNum="";
   @ExtractBy("//*div[@class='function_items']/a/b/text()")
	 private String commentNum="";
   @ExtractBy("//*div[@class='function_items']/span/text()")
	 private String  postTime="";
	 private String pageMD5="";
	@ExtractBy("//*[@id='changebg']/div[1]/ul/li[@class='list_tt']/p[@class='lftp']/a/text()")
	 private String author="";
	@ExtractBy("//*[@id='changebg']/div[1]/ul/li[@class='list_tt']/p[@class='lftp']/a/@href")
	 private String author_url;
	 private int history=0;
	 private String extractTime;
	 
	@Override
	public void afterProcess(Page page){
		//对issueUrl进行处理
		this.issueUrl= page.getPageUrl();
		//对issueId进行处理
	/*	String s1=issueTitle.substring(0, issueTitle.lastIndexOf("/"));*/
		String s2=issueUrl.substring(issueUrl.lastIndexOf("/")+1);
	    this.issueId=Integer.parseInt(s2.trim());
		//对issueUrlMD5进行处理
		this.issueUrlMD5=DigestUtils.md5Hex(issueUrl);
		//对tags进行处理
		this.tag=StringHandler.combineTags(tags);
		//对postTime进行处理
		this.postTime=postTime+" 00:00:00";
		//对extractTime进行处理
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		this.extractTime = simpleDateFormat.format(new Date());
		this.author_url="http://www.dewen.io".trim()+this.author_url.trim();
		this.scanerNum=this.scanerNum.replace("(", "").replace(")","").trim();
		this.attentionNum=attentionNum.replace("（", "").replace("）","").replace("人","").trim();
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssueUrl() {
		return issueUrl;
	}

	public void setIssueUrl(String issueUrl) {
		this.issueUrl = issueUrl;
	}

	public String getIssueTitle() {
		return issueTitle;
	}

	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}

	public String getIssueDetail() {
		return issueDetail;
	}

	public void setIssueDetail(String issueDetail) {
		this.issueDetail = issueDetail;
	}

	public String getIssueUrlMD5() {
		return issueUrlMD5;
	}

	public void setIssueUrlMD5(String issueUrlMD5) {
		this.issueUrlMD5 = issueUrlMD5;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getScanerNum() {
		return scanerNum;
	}

	public void setScanerNum(String scanerNum) {
		this.scanerNum = scanerNum;
	}

	public String getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(String attentionNum) {
		this.attentionNum = attentionNum;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getPageMD5() {
		return pageMD5;
	}

	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor_url() {
		return author_url;
	}

	public void setAuthor_url(String author_url) {
		this.author_url = author_url;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	@Override
	public void validate(Page page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if (StringHandler.isLeastOneBlank(this.issueTitle, this.author,
						this.author_url)) {
					page.setResultSkip(this, true);
					return;
				}

				if (!page.getResultItems().isSkip()) {
					if (!StringHandler.canFormatterInteger(this.commentNum,
							this.scanerNum)) {
						page.setResultSkip(this, true);
						return;
					}

					if (!StringHandler
							.canFormatterDate(this.extractTime, this.postTime)) {
						page.setResultSkip(this,true);
					}
		
	}
	

}
}
