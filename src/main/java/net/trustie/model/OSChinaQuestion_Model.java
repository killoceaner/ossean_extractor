package net.trustie.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Source;
import extension.StringHandler;

@ExtractBy("//*[@id='OSC_Content']/div[@class='Question']/div[@class='Body']/div[@class='main']")
public class OSChinaQuestion_Model implements AfterExtractor, ValidateExtractor {

	private String questionUrl = "";

	private String questionId = "0";

	private String extractTime = null;

	private String pageMD5 = "";

	private String urlMD5 = "";

	private int history = 0;

	private String tag = "";

	@ExtractBy("//*[@class='info']/span[@class='pinfo']/span/a[@class='answer_count']/text()")
	private String replyNum;

	@ExtractBy("//*[@class='info']/span[@class='pinfo']/span[2]/text()")
	private String viewNum;

	@ExtractBy("//*[@class='info']/span[@class='pinfo']/a[@class='Asker-Name']/text()")
	private String author;

	@ExtractBy("//*[@class='info']/span[@class='ainfo']/a/@href")
	private String authorUrl;

	@ExtractBy("//div[@class='Title']/div[@class='QTitle']/h1/a/text()")
	private String questionTitle;

	@ExtractBy("//*[@class='tags_toolbars']/div[@id='tags_nav']/a/allText()")
	private List<String> questionTags;

	@ExtractBy(value = "//*[@id='OSC_Banner']/div[@class='wp998']/dl/dd/a[2]/text()", source = Source.RawHtml)
	private String questionType;

	@ExtractBy("//*[@class='info']/span[@class='pinfo']/span[@class='pub_time']/text()")
	private String postTime;

	@ExtractBy("//*[@class='store']/a[@id='favor_trigger']/text()")
	private String housedNum;

	@ExtractBy("//[@id='Vote']/span[@class='q_vote_num']/text()")
	private String laudNum;

	@ExtractBy("//[@class='Content']/div[@class='detail']/allText()")
	private String questionContent = null;

	public void afterProcess(Page page) {
		// TODO Auto-generated method stub

		// 处理标签
		this.tag = StringHandler.combineTags(questionTags);

		// 处理收藏数
		this.housedNum = StringHandler.findRigthString(housedNum, "(", ")");

		// 处理浏览次数
		this.viewNum = StringHandler.matchRightString(this.viewNum, "[0-9]+");

		// 处理和验证帖子的Id
		this.questionId = StringHandler.matchRightString(this.questionUrl,
				"[0-9]+_[0-9]+");

		// 处理抽取时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		this.extractTime = simpleDateFormat.format(new Date());

		// 处理帖子的url、PageMD5和urlDM5
		this.questionUrl = page.getPageUrl();
		this.urlMD5 = DigestUtils.md5Hex(this.questionUrl);
		this.pageMD5 = DigestUtils.md5Hex(this.questionContent
				+ this.questionTitle);

		// 处理和验证postime
	}

	public void validate(Page page) {
		// TODO Auto-generated method stub
		if (!StringHandler.isAllNotBlank(this.questionTitle, this.questionType,
				this.author, this.authorUrl, this.questionId)) {
			page.setSkip(true);
			return;
		}

		if (!page.getResultItems().isSkip()
				&& !StringHandler.canFormatterInteger(this.replyNum,
						this.housedNum, this.laudNum, this.viewNum)) {
			page.setSkip(true);
			return;
		}

		if (!page.getResultItems().isSkip()
				&& StringHandler.canFormatterDate(this.postTime,
						this.extractTime))
			page.setSkip(true);
	}

	public String getQuestionUrl() {
		return questionUrl;
	}

	public String getQuestionId() {
		return questionId;
	}

	public String getReplyNum() {
		return replyNum;
	}

	public String getViewNum() {
		return viewNum;
	}

	public String getAuthor() {
		return author;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public List<String> getQuestionTags() {
		return questionTags;
	}

	public String getQuestionType() {
		return questionType;
	}

	public String getPostTime() {
		return postTime;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public String getHousedNum() {
		return housedNum;
	}

	public String getLaudNum() {
		return laudNum;
	}

	public String getPageMD5() {
		return pageMD5;
	}

	public String getUrlMD5() {
		return urlMD5;
	}

	public int getHistory() {
		return history;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionUrl(String questionUrl) {
		this.questionUrl = questionUrl;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public void setQuestionTags(List<String> questionTags) {
		this.questionTags = questionTags;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public void setHousedNum(String housedNum) {
		this.housedNum = housedNum;
	}

	public void setLaudNum(String laudNum) {
		this.laudNum = laudNum;
	}

	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}

	public void setUrlMD5(String urlMD5) {
		this.urlMD5 = urlMD5;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
