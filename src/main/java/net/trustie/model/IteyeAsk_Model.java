package net.trustie.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import core.AfterExtractor;
import core.Page;
import extension.StringHandler;



public class IteyeAsk_Model implements AfterExtractor{
	 @ExtractBy("//div[@class='sproblem_right']/h3/a/text()")
	    private String problem_title="";
	    @ExtractBy("//div[@class='new_content']/allText()")
	    private String problem_content="";
	    @ExtractBy("//span[@class='user_blog']/a/text()")
	    private String user="";
	    private String url="";
	    private String urlMD5="";
	    @ExtractBy("//div[@class='ask_label']/span/text()")
	    private String release_time="";
	    private String extract_time="";
	    private String tag;
	    @ExtractBy("//div[@class='tags']/allText1()")
	    private List<String>  tags;
	    @ExtractBy("//div[@id='main']")
	    private String main;
	    private String pageMD5="";
	    @ExtractBy("//h3[@id='num']/text()")
	    private String answer_count="0";
        private int history=0;
     @ExtractBy("//*[@id='main']/div[3]/dl/dd/div[1]/div[3]/span[1]/a/text()") 
        private String author;
     @ExtractBy("//*[@id='main']/div[3]/dl/dd/div[1]/div[3]/span[1]/a/@html()") 
        private String author_url;
    @Override
	public void afterProcess(Page page){
    	this.setUrl(page.getPageUrl());
    	 this.pageMD5= DigestUtils.md5Hex(this.main);
       //对tags进行处理
    
         this.tag=StringHandler.combineTags(tags);
         
         
         if (!this.answer_count.equals("目前还没有答案"))
         {
        	 String count="0";
             this.answer_count= count.toString();
         }
         //对extract_Time进行处理
         SimpleDateFormat bartDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         this.extract_time=bartDateFormat.format(new Date());

    }


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getProblem_title() {
		return problem_title;
	}


	public void setProblem_title(String problem_title) {
		this.problem_title = problem_title;
	}


	public String getProblem_content() {
		return problem_content;
	}


	public void setProblem_content(String problem_content) {
		this.problem_content = problem_content;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getUrlMD5() {
		return urlMD5;
	}


	public void setUrlMD5(String urlMD5) {
		this.urlMD5 = urlMD5;
	}


	public String getRelease_time() {
		return release_time;
	}


	public void setRelease_time(String release_time) {
		this.release_time = release_time;
	}


	public String getExtract_time() {
		return extract_time;
	}


	public void setExtract_time(String extract_time) {
		this.extract_time = extract_time;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public String getMain() {
		return main;
	}


	public void setMain(String main) {
		this.main = main;
	}


	public String getPageMD5() {
		return pageMD5;
	}


	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}


	public String getAnswer_count() {
		return answer_count;
	}


	public void setAnswer_count(String answer_count) {
		this.answer_count = answer_count;
	}


	public int getHistory() {
		return history;
	}


	public void setHistory(int history) {
		this.history = history;
	}
	
	}
