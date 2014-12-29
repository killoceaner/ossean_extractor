package net.trustie.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;

import extension.StringHandler;


import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Source;

@ExtractBy("//*[@class='wraper']/div[@class='detailed']/table[1]/tbody")
public class CsdnTopic_Model implements AfterExtractor, ValidateExtractor {
	private int topicId = 0;

	private String topicUrl = "";

	private String topicUrlMD5 = "";

	private String pageMD5 = "";

	private String tag = "";

	private int history = 0;

	private String topicScore = "";

	private String extractTime = "";

	@ExtractBy("//*[@class='data']/span[@class='time']/text()")
	private String postTime;

	@ExtractBy("//*[@class='post_body']/div[@class='tag']/span/allText()")
	private List<String> tags;

	@ExtractBy("//*div[@class='control']/span[@class='return_time']/text()")
	private String replyNum = "0";

	@ExtractBy(value = "//*[@class='wraper']/div[@class='detail_tbox']/div[@class='detail_title']/html()", source = Source.RawHtml)
	private String topicTitle = "";

	@ExtractBy("//*div[@class='post_body']//allText()")
	private String topicContent = "";

	@ExtractBy("//dl/dd[@class='username']/a/text()")
	private String author = "";

	@ExtractBy("//dl/dd[@class='username']/a/@href")
	private String authorUrl = "";

	public void afterProcess(Page page) {
		// TODO Auto-generated method stub
		// 处理帖子的url
		this.topicUrl = page.getPageUrl();

		// 处理帖子的UrlMD5
		this.topicUrlMD5 = DigestUtils.md5Hex(this.topicUrl);

		// 处理帖子的ID
		this.topicId = (Integer.parseInt(topicUrl.substring(topicUrl
				.lastIndexOf("/") + 1)));

		if (this.topicTitle != null) {
			// 处理帖子分数
			this.topicScore = StringHandler.extractHtml(this.topicTitle,
					"//h1/allText()");
			this.topicScore = StringHandler.matchRightString(this.topicScore,
					"\\[问题点数：[0-9]+分");
			this.topicScore = StringHandler.matchRightString(this.topicScore,
					"[0-9]+").trim();

			// 处理帖子标题
			Document doc = Jsoup.parse(this.topicTitle);
			Elements eles = doc.select("h1 span.title")
					.select(".text_overflow");
			this.topicTitle = eles.first().text();
		} else
			this.topicTitle = null;

		// 处理帖子的回复次数
		this.replyNum = StringHandler.subString(this.replyNum, "回复次数：").trim();

		// 处理帖子的标签
		this.tag = StringHandler.combineTags(tags);

		// 处理帖子的内容
		this.topicContent = StringHandler.subString(this.topicContent,
				"更多 分享到：");

		// 处理帖子的提交时间
		this.postTime = StringHandler.subString(this.postTime, "发表于：").trim();

		// 处理extractorTime
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		this.extractTime = simpleDateFormat.format(new Date());

		// 处理pageMD5
		this.pageMD5 = DigestUtils.md5Hex(this.replyNum + this.topicTitle);
		
	}

	public void validate(Page page) {
		// TODO Auto-generated method stub
		if (StringHandler.isLeastOneBlank(this.topicTitle, this.author,
				this.authorUrl, this.topicUrl)) {
			page.setSkip(true);
			return;
		}

		if (!page.getResultItems().isSkip()) {
			if (!StringHandler.canFormatterInteger(this.replyNum,
					this.topicScore)) {
				page.setSkip(true);
				return;
			}

			if (!StringHandler
					.canFormatterDate(this.postTime, this.extractTime)) {
				page.setSkip(true);
			}
		}
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicUrl() {
		return topicUrl;
	}

	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}

	public String getTopicUrlMD5() {
		return topicUrlMD5;
	}

	public void setTopicUrlMD5(String topicUrlMD5) {
		this.topicUrlMD5 = topicUrlMD5;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public String getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	public String getTopicScore() {
		return topicScore;
	}

	public void setTopicScore(String topicScore) {
		this.topicScore = topicScore;
	}

	public String getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor_url() {
		return authorUrl;
	}

	public void setAuthor_url(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPostTime() {
		return postTime;
	}

	public String getExtractorTime() {
		return extractTime;
	}

	public String getPageMD5() {
		return pageMD5;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public void setExtractorTime(String extractorTime) {
		this.extractTime = extractorTime;
	}

	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public List<String> getTags() {
		return tags;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}
}
