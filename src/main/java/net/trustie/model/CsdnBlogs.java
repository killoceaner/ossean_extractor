package net.trustie.model;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Source;

@ExtractBy("//*[@id='main']/div[@class='main']/div[@id='article_details']")
public class CsdnBlogs implements AfterExtractor {
	private int blogId;

	private String blogPageMD5;

	private String blogUrlMD5;

	private String extractTime;

	private int history = 0;

	private String blogTag;

	private String blogCategory;

	@ExtractBy("//div[@class='article_title']/h1/span[@class='link_title']/a/text()")
	private String blogTitle;

	@ExtractBy("//*div[@id='article_content']/allText()")
	private String blogContent;

	@ExtractBy("//*div[@class='tag2box']/a/allText()")
	private List<String> blogTags;

	@ExtractBy("//*div[@class='article_manage']/span[@class='link_view']/text()")
	private String readNum;

	@ExtractBy("//*div[@class='article_manage']/span[@class='link_comments']/text()")
	private String commentNum;

	@ExtractBy(value = "//*[@id='panel_Profile']/ul[@class='panel_body profile']/div[@id='blog_userface']/span/a/text()", source = Source.RawHtml)
	private String author;

	@ExtractBy(value = "//*[@id='panel_Profile']/ul[@class='panel_body profile']/div[@id='blog_userface']/span/a/@href", source = Source.RawHtml)
	private String authorUrl;

	@ExtractBy("//*div[@class='article_manage']/span[@class='link_postdate']/text()")
	private String postTime;

	@ExtractBy("//*div[@class='article_manage']/span[@class='link_categories']/a/allText()")
	private List<String> blogCategories;

	@ExtractBy("")
	private String supportNum;

	@ExtractBy("")
	private String opposeNum;

	public void afterProcess(Page arg0) {
		// TODO Auto-generated method stub
		//
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogPageMD5() {
		return blogPageMD5;
	}

	public void setBlogPageMD5(String blogPageMD5) {
		this.blogPageMD5 = blogPageMD5;
	}

	public String getBlogUrlMD5() {
		return blogUrlMD5;
	}

	public void setBlogUrlMD5(String blogUrlMD5) {
		this.blogUrlMD5 = blogUrlMD5;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public List<String> getBlogTags() {
		return blogTags;
	}

	public void setBlogTags(List<String> blogTags) {
		this.blogTags = blogTags;
	}

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(String supportNum) {
		this.supportNum = supportNum;
	}

	public String getOpposeNum() {
		return opposeNum;
	}

	public void setOpposeNum(String opposeNum) {
		this.opposeNum = opposeNum;
	}

	public String getBlogTag() {
		return blogTag;
	}

	public void setBlogTag(String blogTag) {
		this.blogTag = blogTag;
	}

	public String getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}

	public List<String> getBlogCategories() {
		return blogCategories;
	}

	public void setBlogCategories(List<String> blogCategories) {
		this.blogCategories = blogCategories;
	}

}
