package net.trustie.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import core.Page;
import core.AfterExtractor;
import core.ValidateExtractor;
import extension.StringHandler;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Source;

public class CsdnQ_Model implements AfterExtractor,ValidateExtractor{
    private int issueId=0;
    
    private String issueUrl="";
    @ExtractBy("//*div[@class='questions_detail_con']/dl/dt/text()")    
    private String issueTitle="";
    @ExtractBy("//*[@id='cut_intro']/allText()")    
    private String issueContent="";
    @ExtractBy("//*div/a[@class='approve']/text()")
    private String voteNum;
    private String  tag;
    @ExtractBy(value="//*/div[@class='tags']/a/allText()")
    private List<String>  tags;
    @ExtractBy("//*div[@class='persion_article']/div[@class='mod_user_info']/div[@class='user_info']/div[@class='info_box clearfix']/p/text()")
    private String askTime="";
    @ExtractBy(value="//*div[@class='persion_article']/div[@class='mod_user_info']/div[@class='user_info']/div[@class='info_box clearfix']/dl[@class='person_info']/dt/a/text()",source =Source.RawHtml)
    private String author="";
    @ExtractBy(value="//*dl[@class='person_info']/dt/a/@href",source=Source.RawHtml)
    private String  author_url;
    private int history=0;
    private String urlMD5="";
    private String pageMD5="";
    private String extractTime;
	


public void afterProcess(Page page){
	
	//对extractTime进行处理
	 SimpleDateFormat bartDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     this.extractTime = bartDateFormat.format(new Date());
     this.issueUrl=page.getPageUrl();
	this.urlMD5=this.urlMD5 = DigestUtils.md5Hex(issueUrl);
	this.pageMD5= DigestUtils.md5Hex(voteNum+issueContent);
	this.issueId=(Integer.parseInt(issueUrl.substring(issueUrl.lastIndexOf("/")+1)));
	//对tags进行处理	
	this.tag=StringHandler.combineTags(tags);
   
   //对发布时间进行处理
   String time=this.askTime;
   time =time.replace(".","-").replace("创建自","").trim()+":00";
   this.askTime=time;
}



public String getTag() {
	return tag;
}

public void setTag(String tag) {
	this.tag = tag;
}

public void setExtractTime(String extractTime) {
	this.extractTime = extractTime;
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



public String getAskTime() {
	return askTime;
}



public void setAskTime(String askTime) {
	this.askTime = askTime;
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



public int getHistory() {
	return history;
}



public void setHistory(int history) {
	this.history = history;
}



public String getUrlMD5() {
	return urlMD5;
}



public void setUrlMD5(String urlMD5) {
	this.urlMD5 = urlMD5;
}



public String getPageMD5() {
	return pageMD5;
}



public void setPageMD5(String pageMD5) {
	this.pageMD5 = pageMD5;
}



public String getExtractTime() {
	return extractTime;
}



@Override
public void validate(Page page) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
			if (StringHandler.isAtLeastOneBlank(this.issueTitle, this.author,
					this.author_url)) {
				page.setResultSkip(this, true);
				return;
			}

			if (!page.getResultItems().isSkip()) {
				if (!StringHandler.canFormatterInteger(this.voteNum)) {
					page.setResultSkip(this, true);
					return;
				}

				if (!StringHandler
						.canFormatterDate(this.askTime, this.extractTime)) {
					page.setResultSkip(this,true);
				}
	
}

}
}
