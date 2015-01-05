package net.trustie.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import extension.StringHandler;

@ExtractBy("//*[@id='questions-show']/div[3]")
public class CsdnQ_Model implements AfterExtractor{
	

    private int issueId=0;
    private String issueUrl=" ";
    @ExtractBy(" //*[@id='questions-show']/div[3]/div[2]/div[1]/div[1]/dl/dt/text()")
    private String issueTitle="";
    @ExtractBy("//*[@id='cut_intro']/p/text()")
    private String issueContent="";
    @ExtractBy("//*[@id='questions-show']/div[3]/div[3]/div[1]/div/div/dl/dt/a/text()")
    private String author="";
    @ExtractBy("//*[@id='question_159202_vote']/a/text()")
    private int voteNum=0;
    @ExtractBy(value="//*[@id='questions-show']/div[3]/div[2]/div[1]/div[1]/dl/div/allText()")
    private String tag;
    private List<String>  tags;
    @ExtractBy("//*[@id='questions-show']/div[3]/div[3]/div[1]/div/div/p/text()")
    private String askTime="";
     @ExtractBy("//*[@id='questions-show']/div[3]/div[3]/div[1]/div/div/dl/dt/a/@href()")
    private String author_url;
    private int history=0;
    private String urlMD5="";
    private String pageMD5="";
    private String extractTime;
	


public void afterProcess(Page page){
	
	//对extractTime进行处理
	 SimpleDateFormat bartDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     this.extractTime = bartDateFormat.format(new Date());
     this.issueUrl=page.getRequest().getUrl();
	
	this.urlMD5=this.urlMD5 = DigestUtils.md5Hex(issueUrl);
	
	this.pageMD5= DigestUtils.md5Hex(voteNum+issueContent);
	
	this.issueId=(Integer.parseInt(issueUrl.substring(issueUrl.lastIndexOf("/")+1)));
	//对tags进行处理	
 /*StringHandler SH=new StringHandler();
 SH.combineTags(tags);*/
	this.tag=StringHandler.combineTags(tags);
   
   //对发布时间进行处理
   String time=this.askTime;
   time =time.replace(".","-")+":00";
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



public int getVoteNum() {
	return voteNum;
}



public void setVoteNum(int voteNum) {
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

}
